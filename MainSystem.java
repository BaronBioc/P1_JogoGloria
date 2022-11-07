

//class to operate the players according to the Main.java inputs through Player.java and Board.java methods;
public class MainSystem {

    private final int squares;
    private final String entry;
    private Player[] players;
    private Player player1, player2, player3;
    private int next = 0;
    private final String[] game;
    private final int[] fine;
    private final int[] fall;


    public MainSystem(int squares, String entry, String[] game, int[] fine, int[] fall) {
        this.squares = squares;
        this.entry = entry;
        this.game = game;
        this.fine = fine;
        this.fall = fall;

    }


    //create a new Board object with the specified FINE and FALL squares and create the 3 new players with each letter;
    public void setUpGame() {
        Board board = new Board(game, fine, fall);
        board.makeBoard();

        player1 = new Player(String.valueOf((entry.charAt(0))));
        player2 = new Player(String.valueOf((entry.charAt(1))));
        player3 = new Player(String.valueOf((entry.charAt(2))));

        players = new Player[]{player1, player2, player3};

    }

    //check if any player has reached the last tile / if any player has won;
    public boolean isGameOn() {
        return (Math.max(player1.getPosition(), Math.max(player2.getPosition(), player3.getPosition())) < squares);

    }

    //get the player that has reached the last tile / has won;
    public Player getWinner() {
        Player winner = null;

        for (int i = 0; i < players.length; i ++) {
            if (players[i].getPosition() == squares) {
                winner = players[i];
            }
        }

        return winner;

    }

    //pick the corresponding Player from the letter input;
    public Player selectPlayer(String target) {
        Player selected = null;

        for (int i = 0; i < players.length; i ++) {
            if (target.equals(players[i].getName())) {
                selected = players[i];
            }
        }

        return selected;
    }

    //return the next Player to play keeping the order and fines;
    public Player getRunnerUp() {
        Player runnerUp = null;
        int idx = next;
        int[] fines = {player1.getFines(), player2.getFines(), player3.getFines()};

        while (runnerUp == null) {
            if (fines[idx] != 0) {
                fines[idx] --;
            }
            else {
                runnerUp = players[idx];
            }

            idx ++;
            if (idx == 3) {
                idx = 0;
            }
        }

        return runnerUp;

    }

    //checks if the next player can play and if not determines the next player without fines;
    public void getNextPlayer() {

        while (players[next].hasFines()) {
            players[next].cutFines();
            next ++;
            if (next == 3) {
                next = 0;
            }
        }

    }

    //check if Player has fines, returns true to 0 fines;
    public boolean getStatus(Player player) {
        return !player.hasFines();

    }

    //change the Player's position with the dice input and check if the Player is on a special tile and apply it if so;
    public void playRound(int dice1, int dice2, String[] tiles) {
        Player turnPlayer = players[next];
        int sum = dice1 + dice2;

        turnPlayer.moveDice(sum, squares);

        if (tiles[turnPlayer.getPosition()].equals("FINE")) {
            turnPlayer.applyFines();
        }
        else {
            if (tiles[turnPlayer.getPosition()].equals("BIRD")) {
                turnPlayer.applyBird(squares);
            }
            else {
                if (tiles[turnPlayer.getPosition()].equals("FALL")) {
                    turnPlayer.applyFall(sum);
                }
            }
        }
        next ++;
        if (next == 3) {
            next = 0;
        }
        getNextPlayer();
        
    }


}





