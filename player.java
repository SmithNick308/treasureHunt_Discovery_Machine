import java.util.Scanner;

public class player {
  
    static Scanner in = new Scanner(System.in);
    int x;
    int y;
    char player;

    player(int x, int y, char player) {
        this.x = x;
        this.y = y;
        this.player = player;
    } 

    void turnLeft(){
        switch (player){
            case '^': 
                player = '<';
                break;
            case '<':
                player = 'v';
                break;
            case 'v': 
                player = '>';
                break;
            case '>': 
                player = '^';
                break;
            default:
                System.out.println("Error: Invalid player.");
        }
    }

    void turnRight(){
        switch (player){
            case '^': 
                player = '>';
                break;
            case '>':
                player = 'v';
                break;
            case 'v': 
                player = '<';
                break;
            case '<': 
                player = '^';
                break;
            default:
                System.out.println("Error: Invalid player.");
        }
    }
    static int[] oneMove(char[][] board, int currentX, int currentY) {
        char move = getMove();
        char player = board[currentX][currentY];
        boolean done = false;

        if (move == 'S') {
            shine(board, currentX, currentY, player);
        } 
        else if (move == 'G') {
            int spaces = getSpaces();
            int[] result = go(board, currentX, currentY, player, spaces);
            currentX = result[0];
            currentY = result[1];
            done = (result[2] == 1);
        } 
        else if (move == 'Q') {
            done = true;
        }
        return new int[]{currentX, currentY, done ? 1 : 0};
    }
    static char getMove() {
        System.out.print("\nEnter your move: R)ight L)eft S)earchlight G)o Q)uit ");
        char temp = Character.toUpperCase(in.next().charAt(0));

        while ("RLSGQ".indexOf(temp) == -1) {
            System.out.print("Enter letter: R)ight L)eft S)earchlight G)o Q)uit ");
            temp = Character.toUpperCase(in.next().charAt(0));
        }
        return temp;
    }
    
   
    static int[] go(char[][] board, int x, int y, char player, int spaces){
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
       if (spaces > x){
            System.out.println("Move rejected -- you would be off the edge.");
            return new int[]{x, y, 0};
        }

        for (int col = x - 1; col >= x - spaces; col--){
            char ch = board[y][col];
            if (ch == 'o' || ch == 'O') {
                System.out.println("You lost. You hit an obstacle.");
                return new int[]{y, col, 1};
            }
            if (ch == 't' || ch == 'T') {
                System.out.println("You won! You reached the treasure!");
                return new int[]{y, col, 1};
            }
            board[x][col] = ' ';
        }
        board[x][y] = ' ';
        y -= spaces;
        board[x][y] = '<';
        return new int[]{x, y, 0}; }

    private static int[] goRight(char[][] board, int x, int y, int spaces) {
      if (spaces > x) {
            System.out.println("Move rejected -- you would be off the edge.");
            return new int[]{x, y, 0};
        }

        for (int col = x - 1; col >= x - spaces; col++) {
            char ch = board[y][col];
            if (ch == 'o' || ch == 'O') {
                System.out.println("You lost. You hit an obstacle.");
                return new int[]{y, col, 1};
            }
            if (ch == 't' || ch == 'T') {
                System.out.println("You won! You reached the treasure!");
                return new int[]{y, col, 1};
            }
            board[x][col] = ' ';
        }

        board[x][y] = ' ';
        y += spaces;
        board[x][y] = '>';
        return new int[]{x, y, 0}; }

    private static int[] goDown(char[][] board, int x, int y, int spaces){
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

     static void shine(char[][] board, int x, int y, char player) {
        if  (player == '^') 
            shineUp(board, x, y);
        else if  (player == '<') 
            shineLeft(board, x, y);
        else if  (player == 'v') 
            shineDown(board, x, y);
        else if  (player == '>') 
            shineRight(board, x, y);
        else 
            System.out.println("Error: Invalid player .");
    }

    private static void shineRight(char[][] board, int x2, int y2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shineRight'");
    }

    private static void shineLeft(char[][] board, int x2, int y2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shineLeft'");
    }

    private static void shineDown(char[][] board, int x2, int y2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shineDown'");
    }

    private static void shineUp(char[][] board, int x2, int y2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shineUp'");
    }
    
}