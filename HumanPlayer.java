import java.util.Scanner;
public class HumanPlayer extends Player {


    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }
    
    public static int turn = 0;

    public static int getTurn(){
        return turn;
    }

    public char getSymbol(){
        return symbol;
    }

    public void makeMove(Board board) {
        
        Scanner scan = new Scanner(System.in);  
        System.out.println(name + ", please input your move: ");

        int col = scan.nextInt();  
       

        if(board.colCheck(col)){
            System.out.println("Invalid: ");
            col = scan.nextInt();
        }    

        board.humanTurn(col, symbol);
        
        turn+=1;
        
            
    }
}
