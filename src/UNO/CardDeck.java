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
            cards.add(new Card(Type.BLACK, Aktionkarten.PLUSFOUR.getValue(), Aktionkarten.PLUSFOUR.getNumber()));
            cards.add(new Card(Type.BLACK, Aktionkarten.FOURCOLOURS.getValue(), Aktionkarten.FOURCOLOURS.getNumber()));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Card(Type.GREEN, Aktionkarten.ZIEHZWEI.getValue(), Aktionkarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.BLUE, Aktionkarten.ZIEHZWEI.getValue(), Aktionkarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.YELLOW, Aktionkarten.ZIEHZWEI.getValue(), Aktionkarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.RED, Aktionkarten.ZIEHZWEI.getValue(), Aktionkarten.ZIEHZWEI.getNumber()));
            cards.add(new Card(Type.GREEN, Aktionkarten.REVERSE.getValue(), Aktionkarten.REVERSE.getNumber()));
            cards.add(new Card(Type.BLUE, Aktionkarten.REVERSE.getValue(), Aktionkarten.REVERSE.getNumber()));
            cards.add(new Card(Type.YELLOW, Aktionkarten.REVERSE.getValue(), Aktionkarten.REVERSE.getNumber()));
            cards.add(new Card(Type.RED, Aktionkarten.REVERSE.getValue(), Aktionkarten.REVERSE.getNumber()));
            cards.add(new Card(Type.GREEN, Aktionkarten.PASS.getValue(), Aktionkarten.PASS.getNumber()));
            cards.add(new Card(Type.BLUE, Aktionkarten.PASS.getValue(), Aktionkarten.PASS.getNumber()));
            cards.add(new Card(Type.YELLOW, Aktionkarten.PASS.getValue(), Aktionkarten.PASS.getNumber()));
            cards.add(new Card(Type.RED, Aktionkarten.PASS.getValue(), Aktionkarten.PASS.getNumber()));

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








