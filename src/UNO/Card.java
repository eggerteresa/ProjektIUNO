package UNO;

import UNO.Type;

public class Card {
    private Type type;
    private int number;

    private int value;



    public Card(Type type, int number, int value) {
        this.type = type;
        this.number = number;
        this.value = value;
    }


    @Override
    public String toString() {
        return "Card{" +
                "type=" + type +
                ", number=" + number +
                ", value=" + value +
                '}';
    }

    public Type getColor() {
        return type;
    }

    public void setColor(Type type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
//
}
