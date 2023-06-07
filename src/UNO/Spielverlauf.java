import java.util.ArrayList;
import java.util.List;


//package UNO;
//
//import java.util.Scanner;
//
//public class Spielverlauf {
//    //int aktuellerSpieler=1;
//    //  int aktuellerSpieler = 0;
//    // WIRD das benötigt???? ist einfach eine int-variable und nicht der Spieler
//
//    public void rundeSpielen(Ablegestapel ablegeStapel, CardDeck abhebestapel, Spielermanagement spielermanagement) {
//        Scanner scanner = new Scanner(System.in);
//        Spieler aktuellerSpieler = spielermanagement.getPlayerByID(1);
//
//        while (gewinnerFestlegen(spielermanagement) == null) { // Solange die GewinnerMethode null zurückliefert, soll das folgende Programm laufen:
//
//            System.out.println("Aktueller Spieler ist: " + aktuellerSpieler);
//            System.out.println(ablegeStapel.lastCardShow());
//            System.out.println(" Was möchtest du tun? (1 = Karte spielen, 2 = Karte abheben) ");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//
//                case 1:
//                    //karte mit kartenID auswählen
//                    System.out.println("Welche Karte möchtest du spielen?");
//                    Card zuspielendeKarte;
//                    System.out.println("Bitte gib die KartenID ein: ");
//                    int eingabe = scanner.nextInt();
//                    zuspielendeKarte = aktuellerSpieler.getCardByID(eingabe);
//
//                    do {
//
//                        for (Card c : aktuellerSpieler.kartenprospieler) {
//
//                            if (c.getKartenID() == eingabe) {
//
//                               aktuellerSpieler.karteSpielen(zuspielendeKarte,ablegeStapel);
//
//
//                            }
//                            System.out.println(ablegeStapel.lastCardShow());
//                        }
//                        if (!aktuellerSpieler.kartenprospieler.contains(zuspielendeKarte)) {
//                            System.out.println("Karte mit der ID nicht in deinen Karten enthalten, versuche es nochmal ");
//                            //TODO wie kommen wir nochmal nach oben?
//                        }
//
//                    }
//                    while (!ablegeStapel.getAblegeStapel().contains(zuspielendeKarte));
//
//
////ToDO Regeln noch weiter implementieren!!!!!!!!!
//
//
//                    // wenn kein weiterer Spielzug möglich
//
//
//                    //zu nächstem Spieler wechseln
//                    if (aktuellerSpieler.getId() == 1) {
//                        aktuellerSpieler = spielermanagement.getPlayerByID(2);
//                    }
//                    if (aktuellerSpieler.getId() == 2) {
//                        aktuellerSpieler = spielermanagement.getPlayerByID(3);
//                    }
//                    if (aktuellerSpieler.getId() == 3) {
//                        aktuellerSpieler = spielermanagement.getPlayerByID(4);
//                    }
//                    if (aktuellerSpieler.getId() == 4) {
//                        aktuellerSpieler = spielermanagement.getPlayerByID(1);
//
//                    }
//                    break;
//
//                case 2:
//                    //Karte abheben
//                    //wenn gewünscht, ebendiese Karte wieder ausspielen
//                    aktuellerSpieler.kartenprospieler.add(abhebestapel.dealCard());
//
//                    //nur letzte karte ausgeben:
//                    System.out.println("neue Karte" + aktuellerSpieler.lastCardHand());
//
//                    System.out.println("Möchtest du die Karte ausspielen? (1 = Ja, 2 = Nein)");
//
//                    int antwort = scanner.nextInt();
//                    if (antwort == 1) {
//                        do {
//                            System.out.println("Bitte gib die KartenID ein: ");
//                            eingabe = scanner.nextInt();
//                            zuspielendeKarte = aktuellerSpieler.getCardByID(eingabe);
//                            for (Card c : aktuellerSpieler.kartenprospieler) {
////
//                                if (c.getKartenID() == eingabe) {
//                                    aktuellerSpieler.kartenprospieler.remove(zuspielendeKarte);
//                                    ablegeStapel.addCard(zuspielendeKarte);
//
//                                    //ablegeStapel.lastCardShow();
//                                }
//                            }
//                            if (!aktuellerSpieler.kartenprospieler.contains(zuspielendeKarte)) {
//                                System.out.println("Karte mit der ID nicht in deinen Karten enthalten, versuche es nochmal ");
//                            }
//                        }
//                        while (!aktuellerSpieler.kartenprospieler.contains(zuspielendeKarte));
//                    }
//                    // nächster Spieler folgt.
//                    spielerReihenfolgeWeiter(aktuellerSpieler, spielermanagement);
//                    break;
//
//                default:
//                    System.out.println("Falsche Eingabe. Bitte nochmal versuchen.");
//            }
//
//
//            System.out.println(ablegeStapel.lastCardShow());
//
//            System.out.println("Karten im Ablegestapel: " + ablegeStapel.ablegeStapel.size());
//            System.out.println("Karten im Abhebestapel: " + abhebestapel.cards.size());
//
//            if (gewinnerFestlegen(spielermanagement) != null) {
//                System.out.println(" Der Rundengewinner ist " + gewinnerFestlegen(spielermanagement));
//                System.out.println("Soll eine weitere Runde gespielt werden? (1 = ja, 2 = nein)");
//                int rundeweiter = scanner.nextInt();
//
//                //TODO weiteren switch hier einbauen??
//                // wie springen wir dann wieder in die while schleife?
//                //oder while schleife als Methode ausbauen???
//            }
//        }
//
//
//    }
//
//
//    public void spielerReihenfolgeWeiter(Spieler aktuellerSpieler, Spielermanagement spielermanagement) { // wenn wir später aktuellerSpieler mit 1 beginnen möchten - hier noch anpassen, derzeit aktuellerSpieler=0
//        if (aktuellerSpieler == null) {
//            aktuellerSpieler = spielermanagement.getPlayerByID(1);
//        }
//        if (aktuellerSpieler.getId() == 1) {
//            aktuellerSpieler = spielermanagement.getPlayerByID(2);
//        }
//        if (aktuellerSpieler.getId() == 2) {
//            aktuellerSpieler = spielermanagement.getPlayerByID(3);
//        }
//        if (aktuellerSpieler.getId() == 3) {
//            aktuellerSpieler = spielermanagement.getPlayerByID(4);
//        }
//        if (aktuellerSpieler.getId() == 4) {
//            aktuellerSpieler = spielermanagement.getPlayerByID(1);
//
//        }
//    }
////Hier würde ich eine Methode schreiben, die a) den aktuellen Spieler neu festlegt und b) checkt,
////TODO ob der Spieler gerade ziehen darf (boolean)
//
//    public Spieler gewinnerFestlegen(Spielermanagement sm) {
//        Spieler rundengewinner = null;
//
//        for (Spieler s : sm.getSpielergruppe()) {
//            if (s.getKartenprospieler().isEmpty() == true) {
//
//                rundengewinner = s;
//            }
//        }
//        if (rundengewinner != null) {
//            System.out.println("Der Rundengewinner ist " + rundengewinner);
//        }
//        return rundengewinner;
//    }
//}