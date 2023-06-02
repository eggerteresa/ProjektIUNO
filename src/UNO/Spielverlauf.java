package UNO;

import java.util.ArrayList;
import java.util.Scanner;

public class Spielverlauf {
    //int aktuellerSpieler=1;


 int aktuellerSpieler = 1;
    // WIRD das benötigt???? ist einfach eine int-variable und nicht der Spieler

    public void rundeSpielen(AblegeStapel ablegeStapel, CardDeck abhebestapel, Spielermanagement spielermanagement) {
        Scanner scanner = new Scanner(System.in);

      //  Spieler aktuellerspieler = spielermanagement.getPlayerByIndex(0);



        while (gewinnerFestlegen(spielermanagement) == null) { // Solange die GewinnerMethode null zurückliefert, soll das folgende Programm laufen:

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


//ToDO Regeln noch weiter implementieren!!!!!!!!!



                    // wenn kein weiterer Spielzug möglich



                    //zu nächstem Spieler wechseln
                    spielerReihenfolgeWeiter(aktuellerSpieler);
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
                    // nächster Spieler folgt.
                    spielerReihenfolgeWeiter(aktuellerSpieler);
                    break;
                case 3:
                    //nächster Spieler ist an der Reihe
                    spielerReihenfolgeWeiter(aktuellerSpieler);
                    break;

                default:
                    System.out.println("Falsche Eingabe. Bitte nochmal versuchen.");
            }


            System.out.println(ablegeStapel.lastCardShow());

            System.out.println("Karten im Ablegestapel: " + ablegeStapel.ablegeStapelcards.size());
            System.out.println("Karten im Abhebestapel: " + abhebestapel.cards.size());


        }

    }



    public void spielerReihenfolgeWeiter(int aktuellerSpieler) { // wenn wir später aktuellerSpieler mit 1 beginnen möchten - hier noch anpassen, derzeit aktuellerSpieler=0
        if (aktuellerSpieler < 5) {
            aktuellerSpieler = aktuellerSpieler + 1;
        }
        if (aktuellerSpieler == 4) {
            aktuellerSpieler = 1;
        }

    }


    public Spieler gewinnerFestlegen(Spielermanagement sm) {
       Spieler rundengewinner = null;

        for (Spieler s : sm.getSpielergruppe()) {
            if (s.getKartenprospieler().isEmpty()) {

                rundengewinner = s;
            }
        }
       return rundengewinner;

    }
}