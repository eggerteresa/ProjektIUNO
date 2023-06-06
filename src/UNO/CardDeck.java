package UNO;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {

    ArrayList<Card> cards = new ArrayList<>();

    public void createCards(Type type) {
        for (int i = 0; i < 10; i++) {
            if (type == Type.YELLOW || type == Type.BLUE || type == Type.GREEN || type == Type.RED) {
                cards.add(new Card(type, i, i));
            }
        }

        for (int i = 1; i < 10; i++) {
            if (type == Type.YELLOW || type == Type.BLUE || type == Type.GREEN || type == Type.RED) {
                cards.add(new Card(type, i, i));
            }

        }
    }

    public void createActionCards() {

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Type.PLUS_4, 50));
            cards.add(new Card(Type.COLORCHANGE, 50));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Card(Type.GREEN_PLUS2, 20));
            cards.add(new Card(Type.GREEN_PASS, 20));
            cards.add(new Card(Type.GREEN_REVERSE, 20));
            cards.add(new Card(Type.BLUE_PASS, 20));
            cards.add(new Card(Type.BLUE_PLUS2, 20));
            cards.add(new Card(Type.BLUE_REVERSE, 20));
            cards.add(new Card(Type.RED_PASS, 20));
            cards.add(new Card(Type.RED_PLUS2, 20));
            cards.add(new Card(Type.RED_REVERSE, 20));
            cards.add(new Card(Type.YELLOW_PASS, 20));
            cards.add(new Card(Type.YELLOW_PLUS2, 20));
            cards.add(new Card(Type.YELLOW_REVERSE, 20));

        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
        System.out.println("Cards are shuffled!");
    }

    // Method Karten abziehen
    public Card dealCard() {
        return cards.remove(0);
    }

    public void distributeCards(PlayerList playerList, CardDeck cardDeck) {

        for (Player player : playerList.getPlayerlist()) {
            for (int j = 0; j < 7; j++) {
                Card c = cardDeck.dealCard();
                player.takeCard(c);

            }
            System.out.println(player);
        }

    }
    public int getSizeofCardDeck(){
        return cards.size();
    }

}




