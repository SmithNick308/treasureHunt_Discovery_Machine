import java.util.Scanner;

public class gameSetup {
    private static final int Max = 10;
    private static Scanner in = new Scanner(System.in);

    private char[][] board;
    private int numMoves;
    private Player player;
    private boolean done;

    public gameSetup() {
        board = new char[Max][Max];
        numMoves = getDifficulty();
        initialize(board);
        int[] pos = findPlayer(board);
        player = new Player(pos[0], pos[1], board[pos[0]][pos[1]]);
        done = false;
    }

    public void playGame() {
        display(board, numMoves);

        while (numMoves > 0 && !done) {
            int[] result = oneMove(board, player.getX(), player.getY());
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
        char temp = Character.toUpperCase(in.next().charAt(0));

        while (temp != 'E' && temp != 'M' && temp != 'H') {
            System.out.print("Enter first letter: E)asy M)edium H)ard ");
            temp = Character.toUpperCase(in.next().charAt(0));
        }

        if (temp == 'E') 
            return 56;
        if (temp == 'M') 
            return 36;
        if (temp == 'H')
            return 26;
        else 
           System.out.println("Error: Invalid difficulty. Defaulting to Easy.");
           return 56;
    }

    private static char getMove() {
        System.out.print("\nEnter your move: R)ight L)eft S)earchlight G)o Q)uit ");
        char temp = Character.toUpperCase(in.next().charAt(0));

        while ("RLSGQ".indexOf(temp) == -1) {
            System.out.print("Enter letter: R)ight L)eft S)earchlight G)o Q)uit ");
            temp = Character.toUpperCase(in.next().charAt(0));
        }
        return temp;
    }

    private static int getSpaces() {
        System.out.print("Go ahead how many spaces? ");
        int temp = in.nextInt();

        while (temp <= 0) {
            System.out.print("Enter a positive integer number of spaces: ");
            temp = in.nextInt();
        }
        return temp;
    }

    private static void display(char[][] board, int numMoves){
        System.out.print("\n  ");
        System.out.println("   Number of moves left: " + numMoves);
        for (int col = 0; col < Max; col++){
            for (int row = 0; row < Max; row++) {
                    char cell = board[row][col];
                    if (cell == 'o' || cell == 't') System.out.print("X ");
                    else System.out.print(cell + " ");
                }
            System.out.println();
            }
        }

    private static void reveal(char[][] board) {
        System.out.print("\n  ");
        for (int col = 0; col < Max; col++) 
            System.out.print(col + " ");
        System.out.println();

        for (int row = 0; row < Max; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < Max; col++) {
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

    private static int[] oneMove(char[][] board, int currentX, int currentY) {
        char move = getMove();
        char playerChar = board[currentX][currentY];
        boolean done = false;

        if (move == 'S') {
            shine(board, currentX, currentY, playerChar);
        } 
        else if (move == 'G') {
            int spaces = getSpaces();
            int[] result = go(board, currentX, currentY, playerChar, spaces);
            currentX = result[0];
            currentY = result[1];
            done = (result[2] == 1);
        } 
        else if (move == 'Q') {
            done = true;
        }
        return new int[]{currentX, currentY, done ? 1 : 0};
    }

    private static void initialize(char[][] board) {
        java.util.Random rand = new java.util.Random();

        for (int r = 0; r < Max; r++)
            for (int c = 0; c < Max; c++)
                board[r][c] = 'X';

        int numObstacles = randInt(5, 6);
        for (int i = 0; i < numObstacles; i++) {
            int length = randInt(1, 4);
            int direction = randInt(0, 1);
            int x = randInt(0, 8);
            int y = randInt(0, 8);

            if (direction == 0) {
                for (int col = y; col < y + length && col < Max; col++)
                    board[x][col] = 'o';
            } else {
                for (int row = x; row < x + length && row < Max; row++)
                    board[row][y] = 'o';
            }
        }

        placeRandom(board, 't');
        placeRandom(board, randomDirection());
    }

    private static void placeRandom(char[][] board, char player) {
        java.util.Random rand = new java.util.Random();
        while (true) {
            int x = rand.nextInt(Max);
            int y = rand.nextInt(Max);
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
        for (int r = 0; r < Max; r++)
            for (int c = 0; c < Max; c++)
                if ("^<v>".indexOf(board[r][c]) != -1)
                    return new int[]{r, c};
        return new int[]{0, 0};
    }

    private static int randInt(int low, int high) {
        return low + (int)(Math.random() * (high - low + 1));
    }

   
}
