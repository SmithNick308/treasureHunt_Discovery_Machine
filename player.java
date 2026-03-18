import java.util.Scanner;

public class player{
    private static Scanner in = new Scanner(System.in);
    private int x;
    private int y;
    private char playerDirection;

    public player(int x, int y, char playerDirection){
        this.x = x;
        this.y = y;
        this.playerDirection = playerDirection;
    }

    static gameSetup game = new gameSetup();
    static int rowSize = game.getRowSize();
    static int colSize = game.getColSize();

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public static char getMove() {
        System.out.print("\nEnter your move: R)ight L)eft S)earchlight G)o Q)uit ");
        char playerMove = Character.toUpperCase(in.next().charAt(0));

        while ("RLSGQ".indexOf(playerMove) == -1) {
            System.out.print("Enter letter: R)ight L)eft S)earchlight G)o Q)uit ");
            playerMove = Character.toUpperCase(in.next().charAt(0));
        }
        return playerMove;
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
    
    public char getDirection(){
        return playerDirection;
    }

    public void setDirection(char playerDirection){
        this.playerDirection = playerDirection;
    }
    
    private static int[] oneMove(char[][] board, int currentX, int currentY) {
        
        char move = getMove();
        char playerChar = board[currentX][currentY];
        boolean done = false;

        if (move == 'S') {
            // Shine/searchlight: reveal a line in the direction facing
            shine(board, currentX, currentY, playerChar, rowSize, colSize);
        } 
        else if (move == 'G') {
            // Go: move forward N spaces
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

    public void turnLeft(){
        switch (playerDirection){
            case '^': 
                playerDirection = '<';
                break;
            case '<':
                playerDirection = 'v';
                break;
            case 'v': 
                playerDirection = '>';
                break;
            case '>': 
                playerDirection = '^';
                break;
            default:
                System.out.println("Error: Invalid direction.");
        }
    }

    public void turnRight(){
        switch (playerDirection){
            case '^': 
                playerDirection = '>';
                break;
            case '>':
                playerDirection = 'v';
                break;
            case 'v': 
                playerDirection = '<';
                break;
            case '<': 
                playerDirection = '^';
                break;
            default:
                System.out.println("Error: Invalid direction.");
        }
    }
  
     private static int[] go(char[][] board, int x, int y, char player, int spaces){
        if ( player == '^') 
            return goUp(board, x, y, spaces);
        if (player == '<') 
            return goLeft(board, x, y, spaces);
        if (player == 'v') 
            return goDown(board, x, y, spaces);
        else if (player == '>') 
            return goRight(board, x, y, spaces);   
        else{
            System.out.println("Error: Invalid player.");
            return new int[]{x, y, 0};
        }
    }
    
    private static int[] goLeft(char[][] board, int x, int y, int spaces){
       if (spaces > y){
            System.out.println("Move rejected -- you would be off the edge.");
            return new int[]{x, y, 0};
        }

        for (int col = y - 1; col >= y - spaces; col--){
            char ch = board[x][col];
            if (ch == 'o' || ch == 'O') {
                System.out.println("You lost. You hit an obstacle.");
                return new int[]{x, col, 1};
            }
            if (ch == 't' || ch == 'T') {
                System.out.println("You won! You reached the treasure!");
                return new int[]{x, col, 1};
            }
            board[x][col] = ' ';
        }
        board[x][y] = ' ';
        y -= spaces;
        board[x][y] = '<';
        return new int[]{x, y, 0}; }

    private static int[] goRight(char[][] board, int x, int y, int spaces) {
      if (x + spaces >= colSize) {
            System.out.println("Move rejected -- you would be off the edge.");
            return new int[]{x, y, 0};
        }

        for (int col = y + 1; col <= y + spaces; col++) {
            char ch = board[x][col];
            if (ch == 'o' || ch == 'O') {
                System.out.println("You lost. You hit an obstacle.");
                return new int[]{x, col, 1};
            }
            if (ch == 't' || ch == 'T') {
                System.out.println("You won! You reached the treasure!");
                return new int[]{x, col, 1};
            }
            board[x][col] = ' ';
        }

        board[x][y] = ' ';
        y += spaces;
        board[x][y] = '>';
        return new int[]{x, y, 0}; }

    private static int[] goDown(char[][] board, int x, int y, int spaces){
        if (x + spaces >= rowSize) {
            System.out.println("Move rejected -- you would be off the edge.");
            return new int[]{x, y, 0};
        }

        for (int row = x + 1; row <= x + spaces; row++){
            char ch = board[row][y];
            if (ch == 'o' || ch == 'O'){
                System.out.println("You lost. You hit an obstacle.");
                return new int[]{row, y, 1};
            }
            if (ch == 't' || ch == 'T'){
                System.out.println("You won! You reached the treasure!");
                return new int[]{row, y, 1};
            }
            board[row][y] = ' ';
        }

        board[x][y] = ' ';
        x += spaces;
        board[x][y] = 'v';
        return new int[]{x, y, 0};
    }

    private static int[] goUp(char[][] board, int x, int y, int spaces){
        if (spaces > x) {
            System.out.println("Move rejected -- you would be off the edge.");
            return new int[]{x, y, 0};
        }

        for (int row = x - 1; row >= x - spaces; row--){
            char ch = board[row][y];
            if (ch == 'o' || ch == 'O'){
                System.out.println("You lost. You hit an obstacle.");
                return new int[]{row, y, 1};
            }
            if (ch == 't' || ch == 'T'){
                System.out.println("You won! You reached the treasure!");
                return new int[]{row, y, 1};
            }
            board[row][y] = ' ';
        }

        board[x][y] = ' ';
        x -= spaces;
        board[x][y] = '^';
        return new int[]{x, y, 0};}

    private static void shine(char[][] board, int x, int y, char player, int rowSize, int colSize) {
        if (player == '^') 
            shineUp(board, x, y, colSize);
        else if (player == '<') 
            shineLeft(board, x, y, colSize);
        else if (player == 'v') 
            shineDown(board, x, y, rowSize);
        else if (player == '>') 
            shineRight(board, x, y, colSize);
        else 
            System.out.println("Error: Invalid player direction.");
    }

    private static void shineUp(char[][] board, int x, int y, int colSize) {
        int Col;
        char Ch;

        for (Col = y + 1; Col <= colSize; Col++){
            Ch = board[x][Col];
            if ((Ch == 'O') || (Ch == 'o')){
            board[x ][Col] = 'O';
            break;  
        }
        else if ((Ch == 'T') || (Ch == 't')){
            board[x][Col] = 'T';
            break;   
        }
        else
            board[x][Col] = ' ';
}
    }

    private static void shineDown(char[][] board, int x, int y, int rowSize) {
        char Ch;
        for (int row = x - 1; row >= 0; row--){
        Ch = board[x][y];
            if ((Ch == 'O') || (Ch == 'o')){
                board[x][y] = 'O';
                break;
            }
            else if ((Ch == 'T') || (Ch == 't')){
                board[x][y] = 'T';
                break;
            }
            else
                board[x][y] = ' ';
            }
    }

    private static void shineLeft(char[][] board, int x, int y,int colSize) {
        char Ch;
        for (int col = y - 1; col >= 0; col--){
            Ch = board[x][col];
            if ((Ch == 'O') || (Ch == 'o')){
                board[x][col] = 'O';
                break;
            }
            else if ((Ch == 'T') || (Ch == 't')){
                board[x][col] = 'T';
                break;
            }
            else
                board[x][col] = ' ';
        }
    }

    private static void shineRight(char[][] board, int x, int y, int colSize) {
        char Ch;
        for (int col = y + 1; col < colSize; col++){
            Ch = board[x][col];
            if ((Ch == 'O') || (Ch == 'o')){
                board[x][col] = 'O';
                break;
            }
            else if ((Ch == 'T') || (Ch == 't')){
                board[x][col] = 'T';
                break;
            }
            else
                board[x][col] = ' ';
        }
    }
}