package UNO;

import java.util.Scanner;

public class Spielverlauf {
    public void rundeSpielen(int aktuellerSpieler,AblegeStapel ablegeStapel, CardDeck abhebestapel,Spielermanagement spielermanagement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aktueller Spieler ist: "+ aktuellerSpieler+ " " +spielermanagement.getPlayerByIndex(aktuellerSpieler)+
                " Was möchtest du tun? (1 = Karte spielen, 2 = Karte abheben" );
        int choice = 0;

        switch (choice){
            case 1:
             //   spielermanagement.getPlayerByIndex(aktuellerSpieler).getKartenprospieler().remove
                //Methode nicht fertig- wie auf Karten zugreifen?
                break;
            case 2:
                ablegeStapel.addCard(abhebestapel.dealCard());

                break;
            default:
                System.out.println("Falsche Eingabe. Bitte nochmal versuchen.");
        }
        System.out.println(ablegeStapel.lastCardShow());
        System.out.println(ablegeStapel.ablegeStapelcards.size());
        System.out.println(abhebestapel.cards.size());
    }
}