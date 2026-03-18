public class player {
  
    private int x;
    private int y;
    private char playerDirection;

    public player(int x, int y, char playerDirection) {
        this.x = x;
        this.y = y;
        this.playerDirection = playerDirection;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return playerDirection;
    }

    public void setDirection(char playerDirection) {
        this.playerDirection = playerDirection;
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

     private static void shine(char[][] board, int x, int y, char player) {
        if  (player == '^') shineUp(board, x, y);
        else if  (player == '<') shineLeft(board, x, y);
        else if  (player == 'v') shineDown(board, x, y);
        else if  (player == '>') shineRight(board, x, y);
        else System.out.println("Error: Invalid player .");
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
      if (x + spaces >= Max) {
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
        if (x + spaces >= Max) {
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

    private static void shineDown(char[][] board, int x, int y) {
        // TODO: Implement shineDown
        System.out.println("Shine down not implemented yet.");
    }

    private static void shineLeft(char[][] board, int x, int y) {
        // TODO: Implement shineLeft
        System.out.println("Shine left not implemented yet.");
    }

    private static void shineRight(char[][] board, int x, int y) {
        // TODO: Implement shineRight
        System.out.println("Shine right not implemented yet.");
    }
}