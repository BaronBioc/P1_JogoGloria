

// class to store each Player's information (name, fines, position) and change them according to the game progress;
// all players start on square 1 and with 0 fines;
public class Player {

    private final int FINES = 2;
    private final int BIRD = 9;
    private String name;
    private int position = 1;
    private int fines = 0;


    public Player(String name) {
        this.name = name;
    }

    // return the Player's designated name/letter;
    public String getName() {
        return name;
    }

    // return the Player's current position;
    public int getPosition() {
        return position;
    }

    // return Player's current position/tile;
    public int getFines() {
        return fines;
    }

    // return if Player has fines or not;
    public boolean hasFines() {
        return fines > 0;
    }

    //add fines to the Player;
    public void applyFines() {
        fines += FINES;
    }

    //remove one fine;
    public void cutFines() {
        fines --;
    }

    //add to the Player's position the BIRD's bonus;
    public void applyBird(int squares) {
        if ((position + BIRD) >= squares) {
            position = squares;
        }
        else {
            position += BIRD;
        }
    }

    //add the dice numbers to the Player's position;
    public void moveDice(int dice, int squares) {
        if ((position + dice) >= squares) {
            position = squares;
        }
        else {
            position += dice;
        }

    }

    //remove to the Player's position the FALL's penalty;
    public void applyFall(int dice) {
        if (position > 2 * dice) {
            position -= 2 * dice;
        }
        else {
            position = 1;
        }

    }


}
