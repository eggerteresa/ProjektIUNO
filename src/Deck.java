import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards = new ArrayList<>();
    Card r0 = new Card(Type.RED, 0);
    Card r1 = new Card(Type.RED, 1);
    Card r2 = new Card(Type.RED, 2);
    Card r3 = new Card(Type.RED, 3);
    Card r4 = new Card(Type.RED, 4);
    Card r5 = new Card(Type.RED, 5);
    Card r6 = new Card(Type.RED, 6);
    Card r7 = new Card(Type.RED, 7);
    Card r8 = new Card(Type.RED, 8);
    Card r9 = new Card(Type.RED, 9);


    public void addcards(Card c) {
        cards.add(c);
    }
}
