package UNO;

import java.util.ArrayList;

public class Player {

    protected String name;
    protected int id; // wirklich notwendig?
    protected int points;

    protected static int counter = 0;
    protected ArrayList<Card> cardsInHand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.id = ++counter;
        this.points = 0;
        this.cardsInHand = new ArrayList<>();

    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", points=" + points + "\n"+
                ", kartenprospieler=" + "\n" + cardsInHand +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;

    }
    public void takeCard(Card card){
        cardsInHand.add(card);
    }
    //Methode Karten nehmen
    public void playCard(Card card, DiscardPile discardDeck){
     cardsInHand.remove(card);
     discardDeck.addCard(card);
    }
    public Card getCardByID(int eingabe) {
        Card result = null;
        for (Card c : cardsInHand) {
            if (c.getCardID() == eingabe) {
                result = c;
            }
        }
        return result;
    }

    public Card lastCardHand() {

        return cardsInHand.get(cardsInHand.size() - 1);
    }




}