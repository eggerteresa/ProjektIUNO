package UNO;

import java.util.ArrayList;

public class DiscardPile {
    ArrayList<Card> discardPile;


    public void addCard(Card c) {
        discardPile.add(c);

    }

    public void firstCard(CardDeck cardDeck) {
        // todo: Regeln implementieren, falls erste Karte eine plus4, colorchange, reverse, oder pass card ist
        Card c1 = cardDeck.dealCard();
        discardPile.add(c1);
        System.out.println("First card on the table is: "+ c1);

    }

    public Card showLastCard() {
        return discardPile.get(discardPile.size()-1);
    }

    public int getSizeofDiscardPile(){
        return discardPile.size();
    }

    public DiscardPile() {
        this.discardPile = new ArrayList<>();
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    //erste Karte wird aufgedeckt




}


