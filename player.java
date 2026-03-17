public class player {
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




}