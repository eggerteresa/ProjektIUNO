package UNO;

import java.util.ArrayList;

public class DiscardPile {
    ArrayList<Card> discardPile;


    public void addCard(Card c) {
        discardPile.add(c);

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


