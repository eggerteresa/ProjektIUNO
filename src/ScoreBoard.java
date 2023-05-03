public class ScoreBoard {

    /* Mithilfe dieser Klasse sind wir in der Lage die aktuelle Scores der Player zu checken. was dieser Klasse tun soll ist folgende; increment players score, find score for particular player, and detect the winner at the end of the game.

     */
    private String [] PlayerList;
    private int [] scores;

    public ScoreBoard(String playerList[]){  // creating new Scoreboard object
       scores = new int[PlayerList.length];
       for (int i = 0; i < PlayerList.length; i++){
           scores [i] = 0;
       }
       this.PlayerList = playerList;
    }


    public void addToScore(int player, int points){ // award points to a particular player
        scores[player] += points;
    }


    public String[] getPlayerList() {  // return the number of players in game.
        return PlayerList;
    }

    public String toString() {
        String rueckgabe = "";
        for (int i=0; i<scores.length; i++) {
           rueckgabe += "Player No" + i + ": " + scores[i];
        }
        return rueckgabe;
    }

    public int getWinner() {
        int winner = 0; // setze ich mal auf null!
        int topScore = scores[0]; //index arr

        for (int i=1; i<scores.length; i++) {
            if (scores[i] > topScore) {
                topScore = scores[i];
                winner = topScore;

            }
        }
        return winner;
    }
    public int getNumPlayers() {
       return PlayerList.length;
    }
}








}
