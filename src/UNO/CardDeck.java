package UNO;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {

    //4 For schleifen, um Karten zu erzeugen, für Spieler ein Interface, eine Klasse für Ablegestapel,
    //eine Klassse für Abhebestapel
    // public void addcards (UNO.Card c) {
//       cards.add(c);
//    }
    ArrayList<Card> cards = new ArrayList<>();

    public void createcards(Type Colour) {
        for (int i = 0; i < 10; i++) {
            cards.add(new Card(Colour, i, i));
        }

        for (int i = 1; i < 10; i++) {
            cards.add(new Card(Colour, i, i));
        }
    }

    public void createActionCards(Type type) {

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Type.BLACK, AktionKarten.PLUSFOUR.getValue(), AktionKarten.PLUSFOUR.getNumber()));
            cards.add(new Card(Type.BLACK, AktionKarten.FOURCOLOURS.getValue(), AktionKarten.FOURCOLOURS.getNumber()));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Card(Type.GREEN, AktionKarten.ZIEHZWEI.getValue(), AktionKarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.BLUE, AktionKarten.ZIEHZWEI.getValue(), AktionKarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.YELLOW, AktionKarten.ZIEHZWEI.getValue(), AktionKarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.RED, AktionKarten.ZIEHZWEI.getValue(), AktionKarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.GREEN, AktionKarten.REVERSE.getValue(), AktionKarten.REVERSE.getNumber()));
            cards.add(new Card(Type.BLUE, AktionKarten.REVERSE.getValue(), AktionKarten.REVERSE.getNumber()));
            cards.add(new Card(Type.YELLOW, AktionKarten.REVERSE.getValue(), AktionKarten.REVERSE.getNumber()));
            cards.add(new Card(Type.RED, AktionKarten.REVERSE.getValue(), AktionKarten.REVERSE.getNumber()));
            cards.add(new Card(Type.GREEN, AktionKarten.PASS.getValue(), AktionKarten.PASS.getNumber()));
            cards.add(new Card(Type.BLUE, AktionKarten.PASS.getValue(), AktionKarten.PASS.getNumber()));
            cards.add(new Card(Type.YELLOW, AktionKarten.PASS.getValue(), AktionKarten.PASS.getNumber()));
            cards.add(new Card(Type.RED, AktionKarten.PASS.getValue(), AktionKarten.PASS.getNumber()));

        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
        System.out.println("Karten sind gemischt, c'est parti!");
    }

    // Method Karten abziehen
    public Card dealCard() {
        return cards.remove(0);
    }


}









