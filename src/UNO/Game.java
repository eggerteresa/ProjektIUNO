package UNO;

import java.util.Collections;

public class Game {
    public static void main(String[] args) {

        Spielermanagement spielermanagement = new Spielermanagement();

        spielermanagement.spielerHinzufuegen();

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
        Ablegestapel ablegeStapel = new Ablegestapel();

//        System.out.println(abhebestapel.cards);

        ablegeStapel.addCard(abhebestapel.dealCard());
        System.out.println(ablegeStapel.lastCardShow());

        //System.out.println(ablegeStapel.ablegeStapelcards);

        // firstCard(abhebestapel);

        System.out.println(abhebestapel.cards.size());

        System.out.println(spielermanagement.getSpielergruppe());

        spielermanagement.reihenfolgeFestlegen(spielermanagement);

        //spielerReihenfolgeAnzeigen(spielermanagement);

        // spielerReihenfolge, Spieler um 1 weiter
        // spielerReihenfolgeWeiter(aktuellerSpieler);

        Spielverlauf spielverlauf = new Spielverlauf();
        spielverlauf.rundeSpielen(spielverlauf.aktuellerSpieler, ablegeStapel, abhebestapel, spielermanagement);

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



   /* public static void spielerReihenfolgeAnzeigen(Spielermanagement sm) {
        int counter = 0;
        for (Spieler s : sm.getSpielergruppe()) {
            System.out.println("Spieler " + counter++ + ": " + s.getName());
            //TODO Bedeutung?
            s.setId(counter-1);

        }
        */
}










