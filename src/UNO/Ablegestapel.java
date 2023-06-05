package UNO;

import java.util.ArrayList;

public class Ablegestapel {
    ArrayList<Card> ablegeStapel;


    public void addCard(Card c) {
        ablegeStapel.add(c);

    }


    public Card lastCardShow() {
        System.out.println("Oberste Karte im Ablegestapel: ");
        return ablegeStapel.get(ablegeStapel.size() - 1);
    }

    public Ablegestapel() {
        this.ablegeStapel = new ArrayList<>();
    }

    public ArrayList<Card> getAblegeStapel() {
        return ablegeStapel;
    }

    //Arrayliste
    //Letzte Karte immer anzeigen (get.)
    //Erste Karte hier speicher
    //Wenn Abhebstapel leer ist --> hier rein speicher

}


