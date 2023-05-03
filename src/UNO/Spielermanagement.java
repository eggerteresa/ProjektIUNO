package UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Spielermanagement {


    private ArrayList<Spieler> spielergruppe = new ArrayList<>();


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

    // Collections.shuffle(unsere Collection);


}
