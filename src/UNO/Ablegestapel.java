package UNO;

import java.util.ArrayList;

public class Ablegestapel {
    ArrayList<Card> ablegeStapelcards;


    public void addCard(Card c) {
        ablegeStapelcards.add(c);

    }


    public Card lastCardShow() {
        System.out.println("Oberste Karte im Ablegestapel: ");
        return ablegeStapelcards.get(ablegeStapelcards.size() - 1);
    }

    public Ablegestapel() {
        this.ablegeStapelcards = new ArrayList<>();
    }

    public ArrayList<Card> getAblegeStapelcards() {
        return ablegeStapelcards;
    }

    //Arrayliste
    //Letzte Karte immer anzeigen (get.)
    //Erste Karte hier speicher
    //Wenn Abhebstapel leer ist --> hier rein speicher

}


