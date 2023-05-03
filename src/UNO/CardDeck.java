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

    public void createredcards(Type RED) {
        int number = 0;
        for (int i = 0; i < 10; i++) {
            cards.add(new Card(RED, number++));
        }
        number = 1;
        for (int i = 0; i < 9; i++) {
            cards.add(new Card(RED, number++));
        }
    }

    public void creategreencards(Type GREEN) {
        int number = 0;
        for (int i = 0; i < 10; i++) {
            cards.add(new Card(GREEN, number++));
        }
        number = 1;
        for (int i = 0; i < 9; i++) {
            cards.add(new Card(GREEN, number++));
        }
    }

    public void createbluecards(Type BLUE) {
        int number = 0;
        for (int i = 0; i < 10; i++) {
            cards.add(new Card(BLUE, number++));
        }
        number = 1;
        for (int i = 0; i < 9; i++) {
            cards.add(new Card(BLUE, number++));
        }
    }

    public void createyellow(Type YELLOW) {
        int number = 0;
        for (int i = 0; i < 10; i++) {
            cards.add(new Card(YELLOW, number++));
        }
        number = 1;
        for (int i = 0; i < 9; i++) {
            cards.add(new Card(YELLOW, number++));
        }
    }

    public void createActionCards(Type type) {
        for (int i = 0; i < 4; i++) {
            cards.add(new Actioncard(Type.BLACK, "Zieh 4 "));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Actioncard(Type.BLACK, "Farbwahl"));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.GREEN, "Zieh 2"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.BLUE, "Zieh 2"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.YELLOW, "Zieh 2"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.RED, "Zieh 2"));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.GREEN, "Retour"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.BLUE, "Retour"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.YELLOW, "Retour"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.RED, "Retour"));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.GREEN, "Pass"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.BLUE, "Pass"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.YELLOW, "Pass"));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Actioncard(Type.RED, "Pass"));
        }


    }
    public void shuffleCards(){
        Collections.shuffle(cards);
        System.out.println("Karten sind gemischt, c'est parti!");
    }

// Method Karten abziehen
    public Card dealCard(){
      return cards.remove(0);
    }
}









