package UNO;

import java.util.Scanner;

public class Spieler implements SpielerMethoden{

    protected String name;
protected int id;
protected int points;
protected static int counter =0;

    public Spieler( String name) {
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




}
