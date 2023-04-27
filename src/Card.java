public class Card {
    private Type type;
    private int number;

    private int value;

    private String name;


    public Card(Type type, int number, int value, String name) {
        this.type = type;
        this.number = number;
        this.value = value;
        this.name = name;
    }

    public Card(Type type, int number, int value) {
        this.type = type;
        this.number = number;
        this.value = value;
    }

    public Card(Type type, int number) {
        this.type = type;
        this.number = number;
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
}
