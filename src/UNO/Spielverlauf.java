package UNO;

import java.util.Scanner;

public class Spielverlauf {
    public void rundeSpielen(int aktuellerSpieler, AblegeStapel ablegeStapel, CardDeck abhebestapel, Spielermanagement spielermanagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aktueller Spieler ist: " + aktuellerSpieler + " " + spielermanagement.getPlayerByIndex(aktuellerSpieler));
        System.out.println(" Was möchtest du tun? (1 = Karte spielen, 2 = Karte abheben, 3= nächster Spieler)");
        int choice = scanner.nextInt();


        switch (choice) {
            case 1:


                //karte mit kartenID auswählen

                System.out.println("Welche Karte möchtest du spielen?");
                Card zuspielendeKarte = null;


                do {
                    System.out.println("Bitte gib die KartenID ein: ");
                    int eingabe = scanner.nextInt();
                    zuspielendeKarte = spielermanagement.getPlayerByIndex(aktuellerSpieler).getCardByID(eingabe);
                    for (Card c : spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler) {
//
                        if (c.getKartenID() == eingabe) {
                            ablegeStapel.addCard(zuspielendeKarte);
                            ablegeStapel.lastCardShow();

                        }

                    }
                    if (!spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler.contains(zuspielendeKarte)) {
                        System.out.println("Karte mit der ID nicht in deinen Karten enthalten, versuche es nochmal ");
                    }
                }
                while (!spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler.contains(zuspielendeKarte));


                // System.out.println("Bitte gib die KartenID ein: ");


                // System.out.println("Falsche ID, versuche es nochmal!");
                // Regeln implementieren
                // wenn kein weiterer Spielzug möglich
                //zu nächstem Spieler wechseln
                break;
            case 2:
                //Karte abheben
                //wenn gewünscht, ebendiese Karte wieder ausspielen
                spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler.add(abhebestapel.dealCard());

                //nur letzte karte ausgeben:
                System.out.println("neue Karte" + spielermanagement.getPlayerByIndex(aktuellerSpieler).lastCardHand());

                System.out.println("Möchtest du die Karte ausspielen? (1 = Ja, 2 = Nein)");

                int antwort = scanner.nextInt();
                if (antwort == 1) {
                    do {
                        System.out.println("Bitte gib die KartenID ein: ");
                        int eingabe = scanner.nextInt();
                        zuspielendeKarte = spielermanagement.getPlayerByIndex(aktuellerSpieler).getCardByID(eingabe);
                        for (Card c : spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler) {
//
                            if (c.getKartenID() == eingabe) {
                                ablegeStapel.addCard(zuspielendeKarte);
                                //ablegeStapel.lastCardShow();

                            }

                        }
                        if (!spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler.contains(zuspielendeKarte)) {
                            System.out.println("Karte mit der ID nicht in deinen Karten enthalten, versuche es nochmal ");
                        }
                    }
                    while (!spielermanagement.getPlayerByIndex(aktuellerSpieler).kartenprospieler.contains(zuspielendeKarte));

                }
                break;

            default:
                System.out.println("Falsche Eingabe. Bitte nochmal versuchen.");
        }
        System.out.println(ablegeStapel.lastCardShow());

        System.out.println("Karten im Ablegestapel: " + ablegeStapel.ablegeStapelcards.size());
        System.out.println("Karten im Abhebestapel: " + abhebestapel.cards.size());
    }
}