package UNO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PlayerList {


    private ArrayList<Player> playerlist = new ArrayList<>();

    public ArrayList<Player> getPlayerlist() {
        return playerlist;
    }

//    public Player getPlayerByIndex(int index) {
//        return playerlist.get(index - 1);
//    }

    public Player getPlayerByID(int id) {
        Player result = null;
        for (Player spieler : playerlist) {
            if (id == spieler.id) {
                result = spieler;
            }
        }
        return result;
    }


    public void setPlayerlist(ArrayList<Player> playerlist) {
        this.playerlist = playerlist;
    }

    public PlayerList() {
        this.playerlist = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "PlayerList{" +
                "playerlist=" + playerlist +
                '}';
    }


    public void addPlayer(){
        for(int i = 0; i < 4; i++){
            Player player = new Player(setPlayerNames());
            playerlist.add(player);
        }
    }

    public String setPlayerNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in your name: ");
        String name = scanner.next();
        return name;
    }

        public void setInitialPlayerTurn(PlayerList playerList) {
        Collections.shuffle(playerList.getPlayerlist());
        int idsetter = 1;
        System.out.println("Setting each player's turn...:");
        for (Player spieler : playerList.getPlayerlist()) {
            spieler.setId(idsetter++);
            System.out.println("Player " + spieler.id + " : " + spieler.name);
        }
    }


}

