package UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        int aktuellerSpieler = 0;
        Spielermanagement spielermanagement = new Spielermanagement();

        spielermanagement.spielerfestlegen();

        System.out.println(spielermanagement);


        CardDeck abhebestapel = new CardDeck();
        abhebestapel.createcards(Type.RED);
        abhebestapel.createcards(Type.YELLOW);
        abhebestapel.createcards(Type.BLUE);
        abhebestapel.createcards(Type.GREEN);

        abhebestapel.createActionCards(Type.BLACK);
//        System.out.println(abhebestapel.cards);
        System.out.println(abhebestapel.cards.size());

        //Karten mischen
        abhebestapel.shuffleCards();
//        System.out.println(abhebestapel.cards);

        distributeCards(spielermanagement, abhebestapel);
        AblegeStapel ablegeStapel = new AblegeStapel();

//        System.out.println(abhebestapel.cards);

        ablegeStapel.addCard(abhebestapel.dealCard());
        System.out.println(ablegeStapel.lastCardShow());

       //System.out.println(ablegeStapel.ablegeStapelcards);

       // firstCard(abhebestapel);

        System.out.println(abhebestapel.cards.size());

        System.out.println(spielermanagement.getSpielergruppe());
        reihenfolgeFestlegen(spielermanagement);

        spielerReihenfolgeAnzeigen(spielermanagement);

        aktuellerSpieler = 1;
        Spielverlauf spielverlauf = new Spielverlauf();
        spielverlauf.rundeSpielen(aktuellerSpieler,ablegeStapel,abhebestapel,spielermanagement);

    }

    public static void distributeCards(Spielermanagement sm, CardDeck cd) {

        for (Spieler spieler : sm.getSpielergruppe()) {
            for (int j = 0; j < 7; j++) {
                Card c = cd.dealCard();
                spieler.karteHinzufügen(c);

            }
            System.out.println(spieler);
        }
    }

    public static void firstCard(CardDeck abhebestapel) {

        Card c1 = abhebestapel.dealCard();
        System.out.println(c1);

    }
    public static void reihenfolgeFestlegen(Spielermanagement sm){
        Collections.shuffle(sm.getSpielergruppe());
        System.out.println("Spielerreihenfolge wurde festgelegt.");
    }
    public static void spielerReihenfolgeAnzeigen(Spielermanagement sm){
        int counter = 1;
        for(Spieler s : sm.getSpielergruppe()){
            System.out.println("Spieler "+ counter++ +": "+  s.getName());
            s.setId(counter-1);

        }
    }


}

