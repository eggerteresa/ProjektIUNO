package UNO;

import java.util.Random;
import java.util.Scanner;


public class GameMethods {
    private static Card playedCard;
    private CardDeck cardDeck = new CardDeck();
    private static DiscardPile discardPile = new DiscardPile();
    private static PlayerList playerList = new PlayerList();
    private static boolean penaltyGiven; //NEW
    public static String color;
    private static Player currentPlayer;
    boolean isClockwise = true;
    static int currentPlayerIndex;
    static boolean blocked;

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }


    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        GameMethods.currentPlayer = currentPlayer;
    }

    public static DiscardPile getDiscardPile() {
        return discardPile;
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        GameMethods.color = color;
    }

    public static boolean isPenaltyGiven() {
        return penaltyGiven;
    }

    public static void setPenaltyGiven(boolean penaltyGiven) {
        GameMethods.penaltyGiven = penaltyGiven;
    }

    public static boolean isBlocked() {
        return blocked;
    }

    public static void setBlocked(boolean blocked) {
        GameMethods.blocked = blocked;
    }

    public static Card getPlayedCard() {
        return playedCard;
    }

    public static void setPlayedCard(Card card) {
        playedCard = card;
    }

    public void prepareGame() {
        cardDeck.createCards(Type.YELLOW);
        cardDeck.createCards(Type.RED);
        cardDeck.createCards(Type.BLUE);
        cardDeck.createCards(Type.GREEN);
        cardDeck.createActionCards();
        cardDeck.shuffleCards();
        playerList.addPlayer();
        System.out.println();
        cardDeck.distributeCards(playerList, cardDeck);
        setBlocked(false);
        System.out.println();
        firstPlayer();
        System.out.println();
        firstCard();

    }

    // wird nur am Anfang des Spiels festgelegt
    public void firstPlayer() {
        System.out.println("Setting each player's turn...:");
        Random rand = new Random();
        int initialPlayerIndex = rand.nextInt(3);
        setCurrentPlayerIndex(initialPlayerIndex);
        setCurrentPlayer(getPlayerByIndex(initialPlayerIndex));
        System.out.println(getPlayerByIndex(currentPlayerIndex).getName() + ",you start the game. ");
    }


    public void firstCard() {
        // todo: Regeln implementieren, falls erste Karte eine plus4, colorchange, reverse, oder pass card ist
        //Wenn eine Farbenwahl Karte zu Beginn des Spiels gezogen wird, entscheidet der Spieler zur Linken
        //des Gebers, welche Farbe als nächstes gelegt werden soll
        Card firstCard = cardDeck.dealCard();
        discardPile.addCardIn(0, firstCard);

    }

    public static void colorChangeCard() { //method for when a player used a COLORCHANGE card
        Player currentPlayer = getCurrentPlayer();
        Card playedCard = getPlayedCard();
        try {
            if (playedCard.getType().equals(Type.PLUS_4) || playedCard.getType().equals(Type.COLORCHANGE)) {
                System.out.println(currentPlayer.getName() + " please choose a color: ");
                Scanner input = new Scanner(System.in);
                String color = input.nextLine();
                setColor(color);
            }
        } catch (NullPointerException e) {
            System.out.printf("..."); //a NullPointerEx can happen if the previous player skipped or was blocked. You can decide how to handle this situation
        }
    }

    public void checkIfCurrentPlayerMustBePenalized() {
        Player currentPlayer = getCurrentPlayer();
        Card card = discardPile.showLastCard();

        if (card.getType().equals(Type.PLUS_4) && !isPenaltyGiven()){
            System.out.println(getCurrentPlayer().getName() + ", you get 4 penalty cards and you are block from playing this turn.");
            plus4Card();
            currentPlayer.printCardsInHand();
            setPenaltyGiven(true); //this is to tell the program that the penalty has been "claimed"
            setBlocked(true); //this is to block the player from making a turn.
        } else if ((card.getType().equals(Type.RED_PLUS2) || card.getType().equals(Type.YELLOW_PLUS2)
                || card.getType().equals(Type.GREEN_PLUS2) || card.getType().equals(Type.BLUE_PLUS2)) && !isPenaltyGiven()) {

            System.out.println(getCurrentPlayer().getName() + ", you get 2 penalty cards and you are block form playing this turn.");
            plus2Card();
            currentPlayer.printCardsInHand();
            setPenaltyGiven(true);
            setBlocked(true);
        }
    }
    public void plus4Card() { //simple but important method to be called in the checkIfCurrentPlayerMustBePenalized() method.
        Player currentPlayer = getCurrentPlayer();
        Card topDiscardCard = discardPile.showLastCard();
        if (topDiscardCard.getType().equals(Type.PLUS_4)) {
            currentPlayer.takeCard(cardDeck.dealCard());
            currentPlayer.takeCard(cardDeck.dealCard());
            currentPlayer.takeCard(cardDeck.dealCard());
            currentPlayer.takeCard(cardDeck.dealCard());
        }
    }

    public void plus2Card() { //simple but important method to be called in the checkIfCurrentPlayerMustBePenalized() method.
        Player currentPlayer = getCurrentPlayer();
        Card topDiscardCard = discardPile.showLastCard();

        if (topDiscardCard.getType().equals(Type.RED_PLUS2) || topDiscardCard.getType().equals(Type.YELLOW_PLUS2)
                || topDiscardCard.getType().equals(Type.GREEN_PLUS2) || topDiscardCard.getType().equals(Type.BLUE_PLUS2)) {
            currentPlayer.takeCard(cardDeck.dealCard());
            currentPlayer.takeCard(cardDeck.dealCard());
        }
    }


    //Methode, um zu überprüfen, ob die gespielte Karte überhaupt gespielt werden darf:
    public boolean isChosenCardValid() { //card validation
        boolean chosenCardValid = false;
        Card card = getPlayedCard();
        Card discard = discardPile.showLastCard();
        try {
            if (discard.getType() == card.getType() || discard.getNumber() == card.getNumber()
                    || card.getType().equals(getColor()) || card.getType().name().charAt(0) == getColor().charAt(0)
                    || card.getType().equals(Type.PLUS_4) || card.getType().equals(Type.COLORCHANGE)
                    || isSameColor() || PassCardCheck() || plus2Check()) {
                chosenCardValid = true;
            } else {
                chosenCardValid = false;
                System.out.println("Sorry, this is not a valid move. Now you have to draw a penalty card!");
                //there should be a method that gives one penalty card for invalid move here...
            }
            return chosenCardValid;
        } catch (NullPointerException e) {
            System.out.println( getCurrentPlayer().getName() + ", cant make a move this round.");
            return false;
        }
    }


    //Methode, die überprüft, ob eine Reversekarte oben auf dem DiscardPile liegt
    public int isReverseCard() { //This method is to decide who has the next turn when the card "<->" is played
        int indexOfTheCurrentPlayer = getCurrentPlayerIndex(); //index of the current player

        if (indexOfTheCurrentPlayer == 0) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 3;
                isClockwise = false;
            } else {
                indexOfTheCurrentPlayer = 1;
                isClockwise = true;
            }
        } else if (indexOfTheCurrentPlayer == 3) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 2;
                isClockwise = false;
            } else {
                indexOfTheCurrentPlayer = 0;
                isClockwise = true;
            }
        } else {
            if (isClockwise) {
                indexOfTheCurrentPlayer--;
                isClockwise = false;
            } else {
                indexOfTheCurrentPlayer++;
                isClockwise = true;
            }
        }
        return indexOfTheCurrentPlayer;
    }


    public int isPassCard() { //This method is to decide who has the next turn when the passcard  is played
        //boolean nicht besser?
        int indexOfTheCurrentPlayer = getCurrentPlayerIndex(); //index of the current player

        if (indexOfTheCurrentPlayer == 1) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 3;
            } else {
                indexOfTheCurrentPlayer = 3;
            }
        } else if (indexOfTheCurrentPlayer == 3) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 1;
            } else {
                indexOfTheCurrentPlayer = 1;
            }
        } else if (indexOfTheCurrentPlayer == 2) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 0;
            } else {
                indexOfTheCurrentPlayer = 0;
            }
        } else {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 2;
            } else {
                indexOfTheCurrentPlayer = 2;

            }
        }
        return indexOfTheCurrentPlayer;
    }

    public int isRegularCard() { //This method is to decide who has the next turn when a normal card is played
        int indexOfTheCurrentPlayer = getCurrentPlayerIndex(); //index of the current player

        if (indexOfTheCurrentPlayer == 1) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 2;
            } else {
                indexOfTheCurrentPlayer = 0;
            }
        } else if (indexOfTheCurrentPlayer == 0) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 1;
            } else {
                indexOfTheCurrentPlayer = 3;
            }
        } else if (indexOfTheCurrentPlayer == 3) {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 0;
            } else {
                indexOfTheCurrentPlayer = 2;
            }
        } else {
            if (isClockwise) {
                indexOfTheCurrentPlayer++;
            } else {
                indexOfTheCurrentPlayer--;

            }
        }
        return indexOfTheCurrentPlayer;
    }


    public void playerPlaysCard() {
        Player currentPlayer = getCurrentPlayer();
        Scanner input = new Scanner(System.in);

        if (penaltyGiven) { //true only if previous player already "claimed" the penalty
            setPenaltyGiven(false);
        } else {
            checkIfCurrentPlayerMustBePenalized(); //before a player makes a move, it will be checked if the player must receive a penalty.
        }

        if (!blocked) {
            if (discardPile.getDiscardPile().size() == 1) {
                initialPlayerPlaysCard();
            } else {
                if (hasValidCardToPlay()) {
                    System.out.println(currentPlayer);
                    currentPlayer.printCardsInHand();
                    System.out.println(currentPlayer.getName() + " , your move! Type in the ID of the card you would like to play: ");
                    int intCardID = input.nextInt();
                    Card cardToPlay = currentPlayer.getCardByID(intCardID);
                    setPlayedCard(cardToPlay);
                    colorChangeCard();
                } else {
                    System.out.println("Sorry, " + currentPlayer.getName() + ", you don't have a valid card to play. Please draw a card.");
                    //current player nimmt eine Karte vom Deck und fügt sie seinen Karten hinzu
                    currentPlayer.cardsInHand.add(cardDeck.dealCard());
                    currentPlayer.printCardsInHand();
                    if (hasValidCardToPlay()) {
                        // remove card from hand, add to card to discard pile = play this card
                        System.out.println(currentPlayer.getName() + " , your move! Type in the ID of the card you would like to play");
                        int intCardID = input.nextInt();
                        Card cardToPlay = currentPlayer.getCardByID(intCardID);
                        setPlayedCard(cardToPlay);
                        colorChangeCard();
                    } else {
                        System.out.println("Sorry, " + currentPlayer.getName() + " you STILL don't have a card to play out this turn.");
                    }
                }
            }
        }
    }

    public static Player getPlayerByIndex(int playerIndex) {
        Player result;
        result = playerList.getPlayerlist().get(playerIndex);
        return result;
    }


    public void nextTurn() {
        Card topCard = discardPile.showLastCard();

        try {
            if (topCard.getType().equals(Type.YELLOW_REVERSE) || topCard.getType().equals(Type.BLUE_REVERSE)
                    || topCard.getType().equals(Type.RED_REVERSE) || topCard.getType().equals(Type.GREEN_REVERSE)) {
                currentPlayerIndex = isReverseCard();
            } else if (topCard.getType().equals(Type.YELLOW_PASS) || topCard.getType().equals(Type.BLUE_PASS)
                    || topCard.getType().equals(Type.RED_PASS) || topCard.getType().equals(Type.GREEN_PASS)) {
                currentPlayerIndex = isPassCard();
            } else {
                currentPlayerIndex = isRegularCard();
            }
        }
        catch (NullPointerException e) {
            System.out.println("The previous player skipped a turn because he/she is penalized or blocked."); //or has no card to play
            currentPlayerIndex = isRegularCard();
        }
        setCurrentPlayer(getPlayerByIndex(currentPlayerIndex));
    }



    public void initialPlayerPlaysCard() { //change the method name ....this will be run just once every round
        Player currentPlayer = getCurrentPlayer();
        Scanner input = new Scanner(System.in);
        Card cardToPlay = null;
        Card cardToCheck = discardPile.showLastCard();

        if (cardToCheck.getType().equals(Type.PLUS_4)) { //chose another random card
            Card newFirstCard = cardDeck.dealCard();
            discardPile.addCardIn(0, newFirstCard);
            System.out.println("First card on the table is: " + newFirstCard);
        }else if (cardToCheck.getType().equals(Type.RED_PASS) || cardToCheck.getType().equals(Type.GREEN_PASS) ||
                cardToCheck.getType().equals(Type.BLUE_PASS) || cardToCheck.getType().equals(Type.YELLOW_PASS)) {
            System.out.println(currentPlayer.getName() + ", you have skip  this turn.");
            setBlocked(true);
        } else if (cardToCheck.equals(Type.RED_PLUS2) || cardToCheck.equals(Type.YELLOW_PLUS2)
                || cardToCheck.equals(Type.GREEN_PLUS2) || cardToCheck.equals(Type.BLUE_PLUS2)) {
            System.out.println(currentPlayer.getName() + ", you have to draw 2 penalty cards and have to skip this turn.");
            plus2Card();
            setBlocked(true);
        } else if (cardToCheck.getType().equals(Type.COLORCHANGE)) { // current player will set the color but the player on the left (nextPlayer) will resume the game
            System.out.println(currentPlayer.getName() + ", choose a color:");
            String color = input.nextLine().toUpperCase();
            setColor(color);
            setPlayedCard(cardToCheck); // player didn't play any card. just set/added the newColor for the COLORCHANGE card
            setBlocked(true);
        } else {
            if (hasValidCardToPlay()) {
                currentPlayer.printCardsInHand();
                System.out.println(currentPlayer.getName() + " ,your move! Type in the ID of the card you would like to play");
                int intCardID = input.nextInt();
                cardToPlay = currentPlayer.getCardByID(intCardID);
                setPlayedCard(cardToPlay);
                colorChangeCard(); //to handle COLORCHANGE cards in case player used it
            } else {
                System.out.println("Sorry, " + currentPlayer.getName() + ", you don't have a valid card to play. Please draw a card.");
                //current player nimmt eine Karte vom Deck und fügt sie seinen Karten hinzu
                currentPlayer.cardsInHand.add(cardDeck.dealCard());
                System.out.println(currentPlayer.cardsInHand);
                if (hasValidCardToPlay()) {
                    // remove card from hand, add to card to discard pile = play this card
                    System.out.println(currentPlayer.getName() + " ,your move! Type in the ID of the card you would like to play");
                    int intCardID = input.nextInt();
                    cardToPlay = currentPlayer.getCardByID(intCardID);
                    setPlayedCard(cardToPlay);
                    colorChangeCard();
                } else {
                    System.out.println("Sorry, " + currentPlayer.getName() + " you don't have a card to play out this turn.");
                }
            }
        }
    }

    public void printTopCardOfDiscardPile() { // just used another color so it is easier to find it on the console
        Card card = discardPile.showLastCard();
        String specialFontColor = "\u001B[35m"; // ANSI escape sequence for pink color
        String resetDefaultFontColor = "\u001B[0m"; // Reset the color back to default

        System.out.println();

        System.out.print(specialFontColor + "DISCARD PILE: ");
        if (card.getType().equals(Type.COLORCHANGE) && !getColor().isEmpty()) { //if COLORCHANGE, the newColor must be printed too.
            System.out.print(card + " New Color: " + getColor());
        } else if (!card.getType().equals("Color")) {
            System.out.print(card);
        } else {
            System.out.print(card + " New Color: " + getColor());
        }

        System.out.println(resetDefaultFontColor);
    }

    public boolean isSameColor() {
        Card c1 = getPlayedCard();
        Card c2 = discardPile.showLastCard();
        boolean samecolor = false;
        char firstLetterCard1 = c1.getType().name().charAt(0);
        char firstLetterCard2 = c2.getType().name().charAt(0);
        if (firstLetterCard1 == firstLetterCard2) {
            samecolor = true;
        }
        return samecolor;
    }

    public boolean PassCardCheck() {
        Card c1 = getPlayedCard();
        Card c2 = discardPile.showLastCard();
        boolean botharepassCards = false;
        boolean lastLetterCard1 = c1.getType().name().endsWith("PASS");
        boolean lastLetterCard2 = c2.getType().name().endsWith("PASS");
        if (lastLetterCard1 == true && lastLetterCard2 == true) {
            botharepassCards = true;
        }
        return botharepassCards;
    }

    public boolean plus2Check() {
        Card c1 = getPlayedCard();
        Card c2 = discardPile.showLastCard();
        boolean botharepassCards = false;
        boolean lastLetterCard1 = c1.getType().name().endsWith("2");
        boolean lastLetterCard2 = c2.getType().name().endsWith("2");
        if (lastLetterCard1 == true && lastLetterCard2 == true) {
            botharepassCards = true;
        }
        return botharepassCards;
    }

    public boolean hasValidCardToPlay() {
        boolean isValid = false;
        Player currentPlayer = getCurrentPlayer();
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();

        //Farbe mit FirstLetter vergleichen
        char firstLetterCardOnTheTable = typeOfCardOnTable.name().charAt(0);

        // PassCard mit LastLetter vergleichen
        boolean lastLetterCardOnTheTable = typeOfCardOnTable.name().endsWith("PASS");

        //Plus2Card mit LastSign verleichen
        boolean lastSignCardOnTheTable = typeOfCardOnTable.name().endsWith("2");

        for (Card card : currentPlayer.cardsInHand) {
            // Farbe wird hier vergliechen
            if (firstLetterCardOnTheTable == (char) card.getType().name().charAt(0)) {
                isValid = true;
            }
            //Check if passCard
            if (lastLetterCardOnTheTable == true && (card.getType().name().endsWith("PASS") == true)) {
                isValid = true;
            }
            //Check if +2 Card
            if (lastLetterCardOnTheTable == true && (card.getType().name().endsWith("2") == true)) {
                isValid = true;
            }

            if (typeOfCardOnTable.equals(Type.GREEN_REVERSE) && card.getType().equals(Type.GREEN)) {
                isValid = true;
            }
            if (typeOfCardOnTable.equals(Type.RED_REVERSE) && card.getType().equals(Type.RED)) {
                isValid = true;
            }
            if (typeOfCardOnTable.equals(Type.BLUE_REVERSE) && card.getType().equals(Type.BLUE)) {
                isValid = true;
            }
            if (typeOfCardOnTable.equals(Type.YELLOW_REVERSE) && card.getType().equals(Type.YELLOW)) {
                isValid = true;
            }
            if (card.getType().equals(typeOfCardOnTable) || card.getNumber() == numberOfCardOnTable
                    || card.getType().equals(Type.COLORCHANGE) || card.getType().equals(Type.PLUS_4)) {
                isValid = true;
                break;
            } else {
                isValid = false;

            }
        }
        return isValid;
    }




    public static void acceptPlayersInput() { //this method will take the playedCard from the player's hand and add it to the DISCARD DECK.
        Player currentPlayer = getCurrentPlayer();
        Card playedCard = getPlayedCard();
        discardPile.addCardIn(0, playedCard);
        currentPlayer.cardsInHand.remove(playedCard);
        setBlocked(false); //IMPORTANT! Resets this value after every player's turn.
        setPlayedCard(null); // reset

    }
}
