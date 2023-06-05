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

    public Spieler getPlayerByID(int id){
        Spieler result = null;
        for(Spieler spieler : spielergruppe){
            if(id == spieler.id){
                result = spieler;
            }
        }
        return result;
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



//
//    public void remove (Spieler player) {
//        spielergruppe.remove(player);
//    }

    public String spielerNamefestlegen() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie Ihren Namen ein: ");
        String name = scanner.next();
        return name;

    }

    public void spielerHinzufuegen() {

        for (int i = 0; i < 4; i++) {

            Spieler player = new Spieler(spielerNamefestlegen());
            spielergruppe.add(player);

        }

    }
    public void reihenfolgeFestlegen(Spielermanagement sm) {
        Collections.shuffle(sm.getSpielergruppe());
        int idsetter = 1;
        System.out.println("Spielerreihenfolge wurde festgelegt:");
        for (Spieler spieler:sm.getSpielergruppe()){
            spieler.setId(idsetter++);
            System.out.println("Spieler " + spieler.id+ " : "+ spieler.name);
        }
    }

}