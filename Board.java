

// class to store the game configuration (number of tiles, and tiles with actions);
public class Board {

    // create a String array tiles from which we can know the tiles' actions;
    private final String[] tiles;
    private final int[] fine;
    private final int[] fall;

    public Board(String[] tiles, int[] fine, int[] fall) {
        this.tiles = tiles;
        this.fine = fine;
        this.fall = fall;

    }

    // fill the array tiles with EMPTY on normal tiles and with the correspondent word on tiles with actions;
    public void makeBoard() {
        for (int i = 0; i < tiles.length; i ++) {
            tiles[i] = "EMPTY";
        }

        for (int a = 0; a < fine.length; a ++) {
            tiles[fine[a]] = "FINE";
        }

        for (int b = 0; b < fall.length; b ++) {
            tiles[fall[b]] = "FALL";
        }

        for (int c = 1; c < tiles.length - 2; c ++) {
            if ((c % 9) == 0) {
                tiles[c] = "BIRD";
            }
        }

    }


}
