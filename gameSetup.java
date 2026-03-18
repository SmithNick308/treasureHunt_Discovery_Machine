import java.util.Scanner;

public class gameSetup {
    private static int rowSize = 10;
    private static int colSize = 10;
    private static Scanner in = new Scanner(System.in);
    private char[][] board;
    private int numMoves;
    private Player player;
    private boolean done;

    public int getRowSize() {
        return rowSize;
    }
    public int getColSize() {
        return colSize;
    }

    public void setRowSize(int rowSize) {
        gameSetup.rowSize = rowSize;
    }
    public void setColSize(int colSize) {
        gameSetup.colSize = colSize;
    }
    
    public gameSetup() {
        board = new char[rowSize][colSize];
        numMoves = getDifficulty();
        initialize(board);
        int[] pos = findPlayer(board);
        player = new Player(pos[0], pos[1], board[pos[0]][pos[1]]);
        done = false;
    }

    public void playGame() {
        display(board, numMoves);

        while (numMoves > 0 && !done) {
            int[] result = player.oneMove(board);
            player.setX(result[0]);
            player.setY(result[1]);
            done = (result[2] == 1);

            numMoves--;
            display(board, numMoves);
        }

        System.out.println("\nGame is over. Here is the final board revealed:");
        reveal(board);
    }

    private static int getDifficulty() {
        System.out.print("Degree of difficulty: E)asy M)edium H)ard ");
        char gameLevel = Character.toUpperCase(in.next().charAt(0));

        int easyGame = 56;
        int mediumGame = 36;
        int hardGame = 26;

        while (gameLevel != 'E' && gameLevel != 'M' && gameLevel != 'H') {
            System.out.print("Enter first letter: E)asy M)edium H)ard ");
            gameLevel = Character.toUpperCase(in.next().charAt(0));
        }

        if (gameLevel == 'E') 
            return easyGame;
        if (gameLevel == 'M') 
            return mediumGame;
        if (gameLevel == 'H')
            return hardGame;
        else 
           System.out.println("Error: Invalid difficulty. Defaulting to Easy.");
           return easyGame;
    }

    private static void display(char[][] board, int numMoves){
        System.out.print("\n  ");
        System.out.println("   Number of moves left: " + numMoves);
        for (int col = 0; col < colSize; col++){
            for (int row = 0; row < rowSize; row++) {
                    char cell = board[row][col];
                    if (cell == 'o' || cell == 't') System.out.print("X ");
                    else System.out.print(cell + " ");
                }
            System.out.println();
            }
        }

    private static void reveal(char[][] board) {
        System.out.print("\n  ");
        for (int col = 0; col < colSize; col++) 
            System.out.print(col + " ");
        System.out.println();

        for (int row = 0; row < rowSize; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < colSize; col++) {
                char cell = board[row][col];
                if (cell == 'o') 
                    System.out.print("o ");
                else if (cell == 't') 
                    System.out.print("t ");
                else if (cell == 'X') 
                    System.out.print("  ");
                else 
                    System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static void initialize(char[][] board) {

        for (int r = 0; r < rowSize; r++)
            for (int c = 0; c < colSize; c++)
                board[r][c] = 'X';

        int numObstacles = randInt(5, 6);
        for (int i = 0; i < numObstacles; i++) {
            int length = randInt(1, 4);
            int direction = randInt(0, 1);
            int x = randInt(0, 8);
            int y = randInt(0, 8);

            if (direction == 0) {
                for (int col = y; col < y + length && col < colSize; col++)
                    board[x][col] = 'o';
            } else {
                for (int row = x; row < x + length && row < rowSize; row++)
                    board[row][y] = 'o';
            }
        }

        placeRandom(board, 't');
        placeRandom(board, randomDirection());
    }

    private static void placeRandom(char[][] board, char player) {
        java.util.Random rand = new java.util.Random();
        while (true) {
            int x = rand.nextInt(rowSize);
            int y = rand.nextInt(colSize);
            if (board[x][y] == 'X') {
                board[x][y] = player;
                return;
            }
        }
    }

    private static char randomDirection() {
        char[] dirs = {'^', '<', 'v', '>'};
        return dirs[new java.util.Random().nextInt(4)];
    }

    private static int[] findPlayer(char[][] board) {
        for (int r = 0; r < rowSize; r++)
            for (int c = 0; c < colSize; c++)
                if ("^<v>".indexOf(board[r][c]) != -1)
                    return new int[]{r, c};
        return new int[]{0, 0};
    }

    private static int randInt(int low, int high) {
        return low + (int)(Math.random() * (high - low + 1));
    }
	
}