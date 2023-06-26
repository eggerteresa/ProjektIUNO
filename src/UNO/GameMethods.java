package UNO;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GameMethods {
    private CardDeck cardDeck = new CardDeck();
    private static DiscardPile discardPile = new DiscardPile();
    private static PlayerList playerList = new PlayerList();

    private Card playedCard;

    private static Player currentPlayer;
    boolean isClockwise = true;

    static int currentPlayerIndex;

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
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
        System.out.println();
        firstPlayer();
        System.out.println();
        firstCard();

    }

    public void readPlayersInput() {
        playerPlaysCard();
    }

    public void updateState() {
        nextTurn();
        System.out.println(discardPile.showLastCard());
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
        //des Gebers, welche Farbe als nächstes gelegt werden soll --> geht eigentlich nicht, also entscheidet Spieler,
        //der dran ist, oder?
        Card firstCard = cardDeck.dealCard();
        discardPile.addCard(firstCard);
        if (firstCard.getType().equals(Type.PLUS_4)) {
            cardDeck.add(firstCard);
            Card newFirstCard = cardDeck.dealCard();
            System.out.println("First card on the table is: " + newFirstCard);

        }
        if (firstCard.getType().equals(Type.RED_PASS) || firstCard.getType().equals(Type.GREEN_PASS) ||
                firstCard.getType().equals(Type.BLUE_PASS) || firstCard.getType().equals(Type.YELLOW_PASS)) {
            System.out.println("Player 2 will start the game.");
            nextTurn();

        }
        System.out.println("First card on the table is: " + firstCard);

    }


    // Methode, um zu überprüfen, ob der jew. Spieler eine spielbare Karte in der Hand hat, oder abheben
// TODO verfeinern, wenn zb. reverse card oben auf liegt
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
            if(firstLetterCardOnTheTable == (char) card.getType().name().charAt(0)){
                isValid = true;
            }
            //Check if passCard
            if(lastLetterCardOnTheTable == true && (card.getType().name().endsWith("PASS") == true)){
                isValid = true;
            }
            //Check if +2 Card
            if(lastLetterCardOnTheTable == true && (card.getType().name().endsWith("2") == true)){
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

    //Methode, um zu überprüfen, ob die gespielte Karte überhaupt gespielt werden darf:
    public boolean isChosenCardValid() {
        boolean chosenCardValid;
        Card card = getPlayedCard();
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();
        if (isSameColor()) {
            return true;
        }
        if (PassCardCheck()) {
            return true;
        }
        if (plus2Check()) {
            return true;
        }
        if (numberOfCardOnTable == card.getNumber()
                || card.getType().equals(Type.PLUS_4) || card.getType().equals(Type.COLORCHANGE)) {
            chosenCardValid = true;
        } else {
            chosenCardValid = false;
            System.out.println("Sorry, this is not a valid move. Now you have to draw a penalty card!");
        }
        return chosenCardValid;

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
        //   setCurrentPlayerIndex(indexOfTheCurrentPlayer); todo wirklich notwendig?
        return indexOfTheCurrentPlayer;
    }


    public int isPassCard() { //This method is to decide who has the next turn when the passcard  is played
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
        //  setTurn(indexOfTheCurrentPlayer);
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
        // setTurn(indexOfTheCurrentPlayer);
        return indexOfTheCurrentPlayer;
    }


    public void playerPlaysCard() {
        Player currentPlayer = getCurrentPlayer();
        Scanner input = new Scanner(System.in);
        Card cardToPlay = null;

        //TODO muss noch ergänzt werden
        if(discardPile.getDiscardPile().size() ==1){
            firstCard();

        }
        if (hasValidCardToPlay()) {
            System.out.println(currentPlayer.cardsInHand);
            System.out.println(currentPlayer.getName() + " ,your move! Type in the ID of the card you would like to play");
            int intCardID = input.nextInt();
            cardToPlay = currentPlayer.getCardByID(intCardID);
            setPlayedCard(cardToPlay);
            if (isChosenCardValid()) {
                playCard(cardToPlay);
            }

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

                if (isChosenCardValid()) {
                    playCard(cardToPlay);
                }


            } else {
                System.out.println("Sorry, " + currentPlayer.getName() + " you don't have a card to play out this turn.");
            }
        }

        setPlayedCard(cardToPlay);
        //     if(cardToPlay != null)
        //       setPlayedCard(cardToPlay);
        //    }

    }

    public static Player getPlayerByIndex(int playerIndex) {
        Player result;
        result = playerList.getPlayerlist().get(playerIndex);
        return result;
    }

    //todo eventuell cardtoplay umbenennen
    public void playCard(Card cardtoplay) {
        getPlayerByIndex(currentPlayerIndex).cardsInHand.remove(cardtoplay);
        discardPile.addCard(cardtoplay);
    }

    public void nextTurn() {
        Card topCard = discardPile.showLastCard();

        if (topCard.getType().equals(Type.YELLOW_REVERSE) || topCard.getType().equals(Type.BLUE_REVERSE)
                || topCard.getType().equals(Type.RED_REVERSE) || topCard.getType().equals(Type.GREEN_REVERSE)) {
            currentPlayerIndex = isReverseCard();
        } else if (topCard.getType().equals(Type.YELLOW_PASS) || topCard.getType().equals(Type.BLUE_PASS)
                || topCard.getType().equals(Type.RED_PASS) || topCard.getType().equals(Type.GREEN_PASS)) {
            currentPlayerIndex = isPassCard();
        } else {
            currentPlayerIndex = isRegularCard();
        }

        setCurrentPlayer(getPlayerByIndex(currentPlayerIndex));
    }

    public boolean isSameColor() {
        Card c1 = getPlayedCard();
        Card c2 = discardPile.showLastCard();
        boolean samecolor = false;
        char firstletterCard1 = c1.getType().name().charAt(0);
        char firstletterCard2 = c2.getType().name().charAt(0);
        if (firstletterCard1 == firstletterCard2) {
            samecolor = true;
        }
        return samecolor;
    }

    public boolean PassCardCheck() {
        Card c1 = getPlayedCard();
        Card c2 = discardPile.showLastCard();
        boolean botharepassCards = false;
        boolean lastletterCard1 = c1.getType().name().endsWith("PASS");
        boolean lastletterCard2 = c2.getType().name().endsWith("PASS");
        if (lastletterCard1 == true && lastletterCard2 == true) {
            botharepassCards = true;
        }
        return botharepassCards;
    }

    public boolean plus2Check() {
        Card c1 = getPlayedCard();
        Card c2 = discardPile.showLastCard();
        boolean botharepassCards = false;
        boolean lastletterCard1 = c1.getType().name().endsWith("2");
        boolean lastletterCard2 = c2.getType().name().endsWith("2");
        if (lastletterCard1 == true && lastletterCard2 == true) {
            botharepassCards = true;
        }
        return botharepassCards;
    }
}
/*
    public void nextTurn() {
        Player currentPlayer;
        Card topCard = discardPile.showLastCard();

        if (topCard.getType().equals(Type.YELLOW_REVERSE) || topCard.getType().equals(Type.BLUE_REVERSE)
                || topCard.getType().equals(Type.RED_REVERSE) || topCard.getType().equals(Type.GREEN_REVERSE)) {
            currentPlayer = getPlayerByIndex(isReverseCard());
            //     setCurrentPlayer(playerList.getPlayerlist().get(isReverseCard()));
        } else if (topCard.getType().equals(Type.YELLOW_PASS) || topCard.getType().equals(Type.BLUE_PASS)
                || topCard.getType().equals(Type.RED_PASS) || topCard.getType().equals(Type.GREEN_PASS)) {
            currentPlayer = getPlayerByIndex(isPassCard());
            //     setCurrentPlayer(playerList.getPlayerlist().get(isPassCard()));
        } else {
            currentPlayer = getPlayerByIndex(isRegularCard());
            //     setCurrentPlayer(playerList.getPlayerlist().get(isRegularCard()));
        }
        setCurrentPlayer(currentPlayer);
    }
*/
