package UNO;

import java.util.ArrayList;

public class Spieler implements SpielerMethoden {

    protected String name;
    protected int id; // wirklich notwendig?
    protected int points;
    protected static int counter = 0;
    protected ArrayList<Card> kartenprospieler = new ArrayList<>();

    public Spieler(String name) {
        this.name = name;
        this.id = ++counter;
        this.points = 0;
    }

    @Override
    public String toString() {
        return "Spieler{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", points=" + points +
                ", kartenprospieler=" + kartenprospieler +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Card> getKartenprospieler() {
        return kartenprospieler;
    }

    public void setKartenprospieler(ArrayList<Card> kartenprospieler) {
        this.kartenprospieler = kartenprospieler;

    }
    public void karteHinzufügen(Card card){
        kartenprospieler.add(card);
    }
    //Methode Karten nehmen
}
