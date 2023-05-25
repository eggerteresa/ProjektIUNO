package UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Spielermanagement {


    private ArrayList<Spieler> spielergruppe = new ArrayList<>();

    public ArrayList<Spieler> getSpielergruppe() {
        return spielergruppe;
    }

    public Spieler getPlayerByIndex(int index) {
        return spielergruppe.get(index - 1);
    }


    public void setSpielergruppe(ArrayList<Spieler> spielergruppe) {
        this.spielergruppe = spielergruppe;
    }

    //    public Spielermanagement() {
//        this.spielergruppe = new ArrayList<>();
//    }

    @Override
    public String toString() {
        return "Spielermanagement{" +
                "spielergruppe=" + spielergruppe +
                '}';
    }


//    public void add(Spieler player) {
//        spielergruppe.add(player);
//    }
//
//    public void remove (Spieler player) {
//        spielergruppe.remove(player);
//    }

    public String spielerName() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie Ihren Namen ein: ");
        String name = scanner.next();
        return name;

    }

    public void spielerfestlegen() {

        for (int i = 0; i < 4; i++) {

            Spieler player = new Spieler(spielerName());
            spielergruppe.add(player);

        }

    }


//    public void distributeCards() {
//
//        for (int i = 0; i<spielergruppe.size(); i++){
//        for (int j = 0; j<7; j++) {
//
//
//
//
//
//
//        }
//
//        }
//
//
//    }

    // Collections.shuffle(unsere Collection);


}
