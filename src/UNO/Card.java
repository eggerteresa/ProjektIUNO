package UNO;

public class Card {
    private Type type;
    private int number;

    private int value;
    private static int counter = 0;

    private int kartenID;

    public int getKartenID() {
        return kartenID;
    }

    public Card(Type type, int number, int value) {
        this.type = type;
        this.number = number;
        this.value = value;
        this.kartenID = counter++;
    }

    public Card(Type type, int value) {
        this.type = type;
        this.value = value;
        this.kartenID = counter++;
    }

    @Override
    public String toString() {

        if (getType().equals(Type.BLUE) || getType().equals(Type.GREEN) || getType().equals(Type.YELLOW) || getType().equals(Type.RED)) {

            return "Card {" +
                    "type=" + type  +
                    ", number=" + number +
                    ", kartenID=" + kartenID + " }" +
                    "\n";

        } else {

            return "Card {" +
                    "type=" + type + " }" +
                    ", kartenID=" + kartenID + " }" +
                    "\n";
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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