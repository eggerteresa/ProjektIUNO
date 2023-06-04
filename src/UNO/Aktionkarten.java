package UNO;

public enum Aktionkarten {
    REVERSE(20,10), PASS(20, 11), ZIEHZWEI(20, 12), PLUSFOUR(50, 13), FOURCOLOURS(50, 14);
    private final int value;
    private final int number;


    Aktionkarten(int value, int number) {
        this.value = value;
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }
}