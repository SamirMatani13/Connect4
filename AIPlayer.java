import java.lang.Math;
public class AIPlayer extends Player {

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    
    public static int turn = 0;

    public static int getTurn(){
        return turn;
    }

    public void makeMove(Board board) {
        
        int min = 0;
        int max = 6;

        int y = (int)Math.floor(Math.random()*(max-min+1)+min);
        
        System.out.println(y);
        
        board.AIWinCheck(symbol);
        if(board.win == 1){
            return;
        }

        board.blockWin(symbol);
        if(board.block == 1){
            board.block -= 1;
            return;
        }
		
       
        while(board.getBoard()[0][y] != '-' ){
            y = (int)Math.floor(Math.random()*(max-min+1)+min);
        }      

        board.AITurn(y, symbol);
        
        
        turn += 1;
        

}               
 }
        


       


