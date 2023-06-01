package UNO;

import java.util.ArrayList;

public class AblegeStapel {

    ArrayList<Card> ablegeStapelcards = new ArrayList<>();

  

        public void addCard (Card c) {
            ablegeStapelcards.add(c);

        }

       public Card lastCardShow () {
          return ablegeStapelcards.get(ablegeStapelcards.size()-1);
       }




    //Arrayliste
        //Letzte Karte immer anzeigen (get.)
        //Erste Karte hier speicher
        //Wenn Abhebstapel leer ist --> hier rein speicher

    }

