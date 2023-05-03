package UNO;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Spielermanagement spielermanagement = new Spielermanagement();

        spielermanagement.spielerfestlegen();

        System.out.println(spielermanagement);


        CardDeck cd = new CardDeck();
        cd.createredcards(Type.RED);
        cd.createbluecards(Type.BLUE);
        cd.creategreencards(Type.GREEN);
        cd.createyellow(Type.YELLOW);
        cd.createActionCards(Type.BLACK);
        System.out.println(cd.cards);
        System.out.println(cd.cards.size());

        //Karten mischen
        cd.shuffleCards();
        System.out.println(cd.cards);

        distributeCards(spielermanagement, cd);


    }

    public static void distributeCards(Spielermanagement sm, CardDeck cd) {

        for (Spieler spieler : sm.getSpielergruppe()) {
            for (int j = 0; j < 7; j++) {
                Card c = cd.dealCard();
                spieler.karteHinzufügen(c);


            }

        }


    }


}

