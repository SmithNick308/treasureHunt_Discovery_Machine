public class Player {
  
    private int x;
    private int y;
    private char direction;

    public Player(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void turnLeft(){
        switch (direction){
            case '^': 
                direction = '<';
                break;
            case '<':
                direction = 'v';
                break;
            case 'v': 
                direction = '>';
                break;
            case '>': 
                direction = '^';
                break;
            default:
                System.out.println("Error: Invalid direction.");
        }
    }

    public void turnRight(){
        switch (direction){
            case '^': 
                direction = '>';
                break;
            case '>':
                direction = 'v';
                break;
            case 'v': 
                direction = '<';
                break;
            case '<': 
                direction = '^';
                break;
            default:
                System.out.println("Error: Invalid direction.");
        }
    }
}