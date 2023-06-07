package UNO;

import java.util.List;
import java.util.Scanner;

public class GameMethods {
    private CardDeck cardDeck = new CardDeck();
    private DiscardPile discardPile= new DiscardPile();
    private PlayerList playerList = new PlayerList();
    private int currentPlayerIndex;

    private Card playedCard;

    private Player currentPlayer;
    boolean clockwise;


    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    public static void setCurrentPlayerIndex(int currentPlayerIndex) {
       currentPlayerIndex = currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
      isChosenCardValid();

    }

    public void updateState(){
        //print;
        //nextplayer();
    }



    // Methode, um zu überprüfen, ob der jew. Spieler eine spielbare Karte in der Hand hat:
// TODO falls false, wird Spieler aufgefordert, eine Karte zu ziehen

    public boolean hasValidCardToPlay(Player player) {
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();
        for (Card card : currentPlayer.cardsInHand) {
            if (card.getType().equals(typeOfCardOnTable) || card.getNumber() == numberOfCardOnTable) {
                return true;
            }
        }
        return false;
    }

    //Methode, um zu überprüfen, ob die gespielte Karte überhaupt gespielt werden darf:
    public boolean isChosenCardValid() {
        Card card = getPlayedCard();
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();

        if (typeOfCardOnTable.equals(card.getType()) || numberOfCardOnTable == card.getNumber()) {
            return true;
        }
        return false;
    }


    //Methode, die überprüft, ob eine Reversekarte oben auf dem DiscardPile liegt
    public boolean IsReverseCard() {
        Type type = discardPile.showLastCard().getType();
        if (type.equals(Type.YELLOW_REVERSE) || type.equals(Type.BLUE_REVERSE) || type.equals(Type.RED_REVERSE) || type.equals(Type.GREEN_REVERSE)) {
            return true;
        }
        return false;
    }

    public boolean isPassCard() {
        Type type = discardPile.showLastCard().getType();
        if (type.equals(Type.YELLOW_PASS) || type.equals(Type.BLUE_PASS) || type.equals(Type.RED_PASS) || type.equals(Type.GREEN_PASS)) {
            return true;
        }
        return false;
    }

    //Wenn eine Karte im DiscardDeck liegt und noch keine Karte abgehoben wurde, ist Player 1 an der Reihe (= allererste Runde)
    //TODO boolean reverse oder aussetzen implementieren

//    public Player showCurrentPlayer() {
//        Player currentPlayer = playerList.getPlayerlist().get(currentPlayerIndex);
//        return currentPlayer;
//    }


    public void playerPlaysCard() {
        Player currentPlayer = getCurrentPlayer();
        Scanner input = new Scanner(System.in);
        Card cardToPlay = null;
        if (hasValidCardToPlay(currentPlayer)) {
            System.out.println(currentPlayer.getName() + " ,your move:");
            int intCardID = input.nextInt();

           cardToPlay = currentPlayer.getCardByID(intCardID);

        } else {
            System.out.println("You don't habe a valid card to play. Please draw a card.");
            // drawACard();
            if (hasValidCardToPlay(currentPlayer)){
                // remove card from hand, add to card to discaod pile
            }else {
                //sorry but you still dont have a card to play this turn.
            }
        }

        if(cardToPlay != null) {
            setPlayedCard(cardToPlay);
        }

    }


    public void nextTurn() {
        Player currentPlayer = getCurrentPlayer();
        Card topCard = discardPile.showLastCard();

        if (isPassCard()) {
            //         isReverse();
        }
    }

//    public void isReverse(){
//        int temp = getCurrentPlayer().getId();
//        int next;
//
//        if(temp == 4 && clockwise){
//            next = 3;
//            setClockwise(false);
//        } else if (temp == 1 && clockwise) {
//            next
//        }
//    }


}