package UNO;

import java.util.List;
import java.util.Scanner;

public class GameMethods {
    private CardDeck cardDeck = new CardDeck();
    private DiscardPile discardPile = new DiscardPile();
    private PlayerList playerList = new PlayerList();

    private Card playedCard;

    private static Player currentPlayer;
    boolean isClockwise;
    int turn;

    public int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        turn = turn;
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
        playerList.setInitialPlayerTurn();
        System.out.println();
        discardPile.firstCard(cardDeck);

    }

    public void readPlayersInput() {
        playerPlaysCard();

    }

    public void updateState() {
        nextTurn();
        System.out.println(discardPile.showLastCard());
    }


    // Methode, um zu überprüfen, ob der jew. Spieler eine spielbare Karte in der Hand hat:

    public boolean hasValidCardToPlay() {
        boolean isValid = false;
        Player p = getCurrentPlayer();
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();
        for (Card card : p.cardsInHand) {
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
        boolean chosenCardValid = false;
        Card card = getPlayedCard();
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();

        if (typeOfCardOnTable.equals(card.getType()) || numberOfCardOnTable == card.getNumber()
                || card.getType().equals(Type.PLUS_4) || card.getType().equals(Type.COLORCHANGE)) {
            chosenCardValid = true;
        } else {
            chosenCardValid = false;
            System.out.println("Sorry, this is not a valid move. Now you have to draw a penalty card, ha!");
        }
        return chosenCardValid;

    }


    //Methode, die überprüft, ob eine Reversekarte oben auf dem DiscardPile liegt
    public int isReverseCard() { //This method is to decide who has the next turn when the card "<->" is played
            int indexOfTheCurrentPlayer = getTurn(); //index of the current player

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
            setTurn(indexOfTheCurrentPlayer);
            return indexOfTheCurrentPlayer;
        }


    public int isPassCard() { //This method is to decide who has the next turn when the passcard  is played
        int indexOfTheCurrentPlayer = getTurn(); //index of the current player

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
        } else {
            if (isClockwise) {
                indexOfTheCurrentPlayer = 2;
            } else {
                indexOfTheCurrentPlayer = 2;

            }
        }
        setTurn(indexOfTheCurrentPlayer);
        return indexOfTheCurrentPlayer;
    }

    public int isRegularCard() { //This method is to decide who has the next turn when a normal card is played
        int indexOfTheCurrentPlayer = getTurn(); //index of the current player

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
        setTurn(indexOfTheCurrentPlayer);
        return indexOfTheCurrentPlayer;
    }




    //TODO boolean reverse oder aussetzen implementieren

//    public Player showCurrentPlayer() {
//        Player currentPlayer = playerList.getPlayerlist().get(currentPlayerIndex);
//        return currentPlayer;
//    }


    public void playerPlaysCard() {
        Player currentPlayer = getCurrentPlayer();
        Scanner input = new Scanner(System.in);
        Card cardToPlay = null;
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
            System.out.println("Sorry, " + currentPlayer.getName()+ ", you don't habe a valid card to play. Please draw a card.");
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

        //     if(cardToPlay != null)
        //       setPlayedCard(cardToPlay);
        //    }

    }

    public void playCard(Card cardtoplay) {

        currentPlayer.cardsInHand.remove(cardtoplay);
        discardPile.addCard(cardtoplay);

    }

    public void nextTurn() {
        Player currentPlayer = getCurrentPlayer();
        Card topCard = discardPile.showLastCard();

        if (topCard.getType().equals(Type.YELLOW_REVERSE) || topCard.getType().equals(Type.BLUE_REVERSE)
                || topCard.getType().equals(Type.RED_REVERSE) || topCard.getType().equals(Type.GREEN_REVERSE))  {

            setCurrentPlayer(playerList.getPlayerlist().get(isReverseCard()));
        }
        else if (topCard.getType().equals(Type.YELLOW_PASS) || topCard.getType().equals(Type.BLUE_PASS)
                || topCard.getType().equals(Type.RED_PASS) || topCard.getType().equals(Type.GREEN_PASS)) {

            setCurrentPlayer(playerList.getPlayerlist().get(isPassCard()));
        }
        else {
            setCurrentPlayer(playerList.getPlayerlist().get(isRegularCard()));
        }

    }


}