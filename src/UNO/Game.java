package UNO;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Spielermanagement spielermanagement = new Spielermanagement();

        spielermanagement.spielerfestlegen();

        System.out.println(spielermanagement);


        CardDeck abhebenstapel = new CardDeck();
        abhebenstapel.createcards(Type.RED);
        abhebenstapel.createcards(Type.YELLOW);
        abhebenstapel.createcards(Type.BLUE);
        abhebenstapel.createcards(Type.GREEN);

        abhebenstapel.createActionCards(Type.BLACK);
        System.out.println(abhebenstapel.cards);
        System.out.println(abhebenstapel.cards.size());

        //Karten mischen
        abhebenstapel.shuffleCards();
        System.out.println(abhebenstapel.cards);

        distributeCards(spielermanagement, abhebenstapel);
        AblegeStapel a = new AblegeStapel();

        System.out.println(abhebenstapel.cards);

        a.addCard(abhebenstapel.dealCard());
        System.out.println(a.lastCardShow());

        System.out.println(a.ablegeStapelcards);

        firstCard(abhebenstapel);

        System.out.println(abhebenstapel.cards.size());

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

    public static void firstCard(CardDeck abhebenstapel) {

        Card c1 = abhebenstapel.dealCard();
        System.out.println(c1);
    }
}

