package UNO;

import java.io.PrintStream;
import java.util.Scanner;

import static UNO.GameMethods.*;
import static UNO.GameMethods.acceptPlayersInput;

public class App {
    private final Scanner input;
    private final PrintStream output;
    private boolean exit = false;


    GameMethods gameMethods = new GameMethods();

    public App(Scanner input, PrintStream output){
        this.input = input;
        this.output = output;
    }

    //die Gameloop
    public void Run() {
        initialize();
        printState();

        while(!exit) {
            readUserInput();
            updateState();
            printState();
        }
    }

    private void initialize() {
        gameMethods.prepareGame();
    }

    private void readUserInput() {
        gameMethods.playerPlaysCard();
    }

    private void updateState() {
        gameMethods.isChosenCardValid();
        acceptPlayersInput();
        gameMethods.nextTurn();
    }


    private void printState() {
        gameMethods.printTopCardOfDiscardPile();
    }

}