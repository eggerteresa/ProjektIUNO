package UNO;

import java.util.Scanner;

public class GameMethods {
    private CardDeck cardDeck = new CardDeck();
    private DiscardPile discardPile = new DiscardPile();
    private PlayerList playerList = new PlayerList();
    private int roundCounter = 1;

    private Player currentPlayer;
    boolean clockwise;

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
        playerList.setInitialPlayerTurn(playerList);
        setPlayers();
        System.out.println();
        discardPile.firstCard(cardDeck);

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
    public boolean isChosenCardValid(Card playedCard) {
        Type typeOfCardOnTable = discardPile.showLastCard().getType();
        int numberOfCardOnTable = discardPile.showLastCard().getNumber();

        if (typeOfCardOnTable.equals(playedCard.getType()) || numberOfCardOnTable == playedCard.getNumber()) {
            return true;
        }
        return false;
    }

    //Ich weiß nicht, ob diese Methode wirklich gebraucht wird:
    public void setPlayers() {
        Player Player_1 = playerList.getPlayerByID(1);
        Player Player_2 = playerList.getPlayerByID(2);
        Player Player_3 = playerList.getPlayerByID(3);
        Player Player_4 = playerList.getPlayerByID(4);

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
    public Player showCurrentPlayer() {
        Player currentplayer = null;
        Player Player_1 = playerList.getPlayerByID(1);
        Player Player_2 = playerList.getPlayerByID(2);
        Player Player_3 = playerList.getPlayerByID(3);
        Player Player_4 = playerList.getPlayerByID(4);
        if (discardPile.getSizeofDiscardPile() == 1 && cardDeck.cards.size() == 79) {
            currentplayer = Player_1;
        }
        return currentplayer;
    }


    public void playerPlaysCard() {
        Player currentPlayer = getCurrentPlayer();

        Scanner input = new Scanner(System.in);
        System.out.println("Your move:");
        int intCardID = input.nextInt();

        Card cardToPlay = currentPlayer.getCardByID(intCardID);

        if(isChosenCardValid(cardToPlay)) {
            //removes card  from hand, add this card on the discardpile
        }
        else{
            System.out.println();
        }
    }

    public void nextTurn() {
        Player currentPlayer = getCurrentPlayer();
        Card topCard = discardPile.showLastCard();

        if(isPassCard()) {
            isReverse();
        }
    }

    public void isReverse(){
        int temp = getCurrentPlayer().getId();
        int next;

        if(temp == 4 && clockwise){
            next = 3;
            setClockwise(false);
        } else if (temp == 1 && clockwise) {
            next
        }
    }



}