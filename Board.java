public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	

	private char [][] bGame = new char[NUM_OF_ROW][NUM_OF_COLUMNS];

	public int win = 0;
	public int block = 0;
	public Board() {
	this.bGame = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
	

	for(int i = 0; i < this.bGame.length; i++){
		for(int j = 0; j < this.bGame[0].length; j++){
			this.bGame[i][j] = '-';
		}
	}
	}	


	public char[][] getBoard(){
		return bGame;
	}

	public int getRows(){
		return NUM_OF_ROW;
	}

	public int getCols(){
		return NUM_OF_COLUMNS;
	}
	

	public void printBoard() {
		for (int i = 0; i < getRows(); i++){
			System.out.print("|");
		  for (int j = 0; j < getCols(); j++){
			  System.out.print(bGame[i][j]);
			  System.out.print("|");
		  }
		  System.out.println();
	}
	}
	
	public boolean containsWin() {

		int x = bGame.length;
		int y = bGame[0].length;

		while(y != 0){
			if(bGame[x-1][y-1] == bGame[x-2][y-1] && bGame[x-2][y-1] == bGame[x-3][y-1] 
			&& bGame[x-3][y-1] == bGame[x-4][y-1]&& bGame[x-1][y-1] != '-'){
				return true;
			}
			else{
				x--;
			}
			if (x == 3){
				x = bGame.length;
				y -= 1;
			}
			
		}

		x = bGame.length;
		y = bGame[0].length;

		while(x != 0){
			if(bGame[x-1][y-1] == bGame[x-1][y-2] && bGame[x-1][y-2] == bGame[x-1][y-3] 
			&& bGame[x-1][y-3] == bGame[x-1][y-4]&& bGame[x-1][y-1] != '-'){
				return true;
			}
			else{
				y--;
			}
			if (y == 3){
				y = bGame[0].length;
				x -= 1;
			}
			
		}
		
		x = 4;
		y = 1;

		while(x != bGame.length +1){
			if(bGame[x-1][y-1] == bGame[x-2][y] && bGame[x-2][y] == bGame[x-3][y+1] 
			&& bGame[x-3][y+1] == bGame[x-4][y+2]&& bGame[x-1][y-1] != '-'){
				return true;
			}
			else{
				y++;
			}
			if (y + 2 > bGame[0].length-1){
				y = 1;
				x += 1;
			}
			
		}
		x = bGame.length;
		y = bGame[0].length;

		while(x != 3){
			if(bGame[x-1][y-1] == bGame[x-2][y-2] && bGame[x-2][y-2] == bGame[x-3][y-3] 
			&& bGame[x-3][y-3] == bGame[x-4][y-4]&& bGame[x-1][y-1] != '-'){
				return true;
			}
			else{
				y--;
			}
			if (y - 4 < 0){
				y = bGame[0].length;
				x -= 1;
			}
			
		}

		
	return false;
	}


	public boolean isTie() {
		if(HumanPlayer.getTurn() + AIPlayer.getTurn() == NUM_OF_COLUMNS * NUM_OF_ROW){
			return true;
		}
		return false;
	}

	public boolean colCheck(int x){
		if (bGame[0][x-1] != '-'){
			return true;
			}
		
		return false;
		
	}

	public void humanTurn(int y, char s){

		int x = bGame.length;

		if (bGame[x-1][y-1] == '-'){
            bGame[x-1][y-1] = s;
        }
        else{
            while(x != 0){
                if(bGame[x-1][y-1] == '-' && bGame[x][y-1] != '-'){
                    bGame[x-1][y-1] = s;
                    return;
                }
                else{
                    x--;
                }
            }
        }
	}

	public void AITurn(int y, char s){
		if (bGame[bGame.length-1][y] == '-'){
            bGame[bGame.length-1][y] = s;
        }
        else{
            int x = bGame.length;
            while(x != 0){
                if(bGame[x-1][y] == '-' && bGame[x][y] != '-'){
                    bGame[x-1][y] = s;
                    return;
                }
                else{
                    x--;
                }
            }
        }
	}

	public void AIWinCheck(char s){
		
		for(int i = 3; i < bGame.length; i++){
			for(int j = 0; j < bGame[0].length-3; j++){
				if (bGame[i][j] == s && 
					bGame[i-1][j+1] == s && bGame[i-2][j+2] == s
					&& bGame[i-3][j+3] == '-' && bGame[i-2][j+3] != '-'){
					
                    bGame[i-3][j+3] = s;
					win +=1;
                    return;
				}
				else if (bGame[i][j] == s && bGame[i-1][j+1] == s  && 
				bGame[i-3][j+3] == s&&
				bGame[i-2][j+2] == '-' && 
				bGame[i-1][j+2] != '-'){
					bGame[i-2][j+2] = s;
					win += 1;
					return;
				}
				else if (bGame[i][j] == s && bGame[i-2][j+2] == s && 
				bGame[i-3][j+3] == s &&
				bGame[i-1][j+1] == '-' &&
				bGame[i][j+1] != '-'){
					bGame[i-1][j+1] = s;
					win += 1;
					return;
				}
				else if (bGame[i-1][j+1] == s && bGame[i-2][j+2] == s &&  
				bGame[i-3][j+3] == s &&
				bGame[i][j] == '-' && i != bGame.length - 1
				&& bGame[i+1][j] != '-'){
					bGame[i][j] = s;
					win += 1;
					return;
			}

				else if (bGame[i-1][j+1] == s && bGame[i-2][j+2] == s && 
				bGame[i-3][j+3] == s &&
				bGame[i][j] == '-' && i == bGame.length - 1){
					bGame[i][j] = s;
					win += 1;
					return;
				}
				else{
					continue;
				}
			}
		}

		for(int i = 0; i < bGame.length - 3; i++){
			for(int j = 0; j < bGame[0].length - 3; j++){
				if (bGame[i][j] == s && bGame[i+1][j+1] == s && 
					bGame[i+2][j+2] == s &&
			 		bGame[i+3][j+3] == '-' && bGame[i+4][j+3] != '-' && i != bGame.length - 4){
					bGame[i+3][j+3] = s;
					win += 1;
					return;
				}
				else if(bGame[i][j] == s && bGame[i+1][j+1] == s && 
				bGame[i+2][j+2] == s &&
				bGame[i+3][j+3] == '-' && i == bGame.length - 4){
					bGame[i+3][j+3] = s;
					win += 1;
					return;
				}
				else if(bGame[i][j] == s && bGame[i+1][j+1] == s && 
				bGame[i+3][j+3] == s &&
				bGame[i+2][j+2] == '-' && bGame[i+3][j+2] != '-'){
					bGame[i+2][j+2] = s;
					win += 1;
					return;
				}
				else if(bGame[i][j] == s && bGame[i+2][j+2] == s && 
				bGame[i+3][j+3] == s &&
				bGame[i+1][j+1] == '-' && bGame[i+2][j+1] != '-'){
					bGame[i+1][j+1] = s;
					win += 1;
					return;
				}
				else if(bGame[i+1][j+1] == s && bGame[i+2][j+2] == s && 
				bGame[i+3][j+3] == s &&
				bGame[i][j] == '-' && bGame[i+1][j] != '-'){
					bGame[i][j] = s;
					win += 1;
					return;
				}
				else{
					continue;
				}
			}
		}


		
		
		for(int i = 0; i < bGame.length; i++){
			for(int j = 0; j < bGame[0].length; j++){
				if (i > 2) {
					break;
				}
				if(bGame[i+3][j] == s &&
                bGame[i+2][j] == s &&
				bGame[i+1][j] == s && 
				bGame[i][j] == '-'){
                    bGame[i][j] = s;
                    return;
                }
                
                else{
                    continue;
                }
			}
		}

		for(int i = 0; i < bGame.length; i++){
            for(int j = 0; j < bGame[i].length-3; j++){
                if(i !=5 && bGame[i][j] == s &&
                bGame[i][j+1] == s && bGame[i][j+2] == s
                && bGame[i][j+3] == '-' && bGame[i+1][j+3] != '-' ){
                    bGame[i][j+3] = s;
					win +=1;
                    return;
                }
                else if(bGame[i][j] == s && bGame[i][j+1] == s 
						&& bGame[i][j+2] == s && bGame[i][j+3] == '-' && i == 5){
                    	bGame[i][j+3] = s;
						win +=1;
                    	return;
                }
				else if(bGame[i][j] == s &&
                   bGame[i][j+1] == s && bGame[i][j+3] == s 
                   && bGame[i][j+2] == '-' && i == 5){
                    bGame[i][j+2] = s;
					win +=1;
                    return;
                }
                else if(i !=5 && bGame[i][j] == s &&
                bGame[i][j+1] == s && bGame[i][j+3] == s 
                && bGame[i][j+2] == '-' && bGame[i+1][j+2] != '-' ){
                    bGame[i][j+2] = s;
					win +=1;
                    return;
                }
				else if(bGame[i][j] == s &&
                   bGame[i][j+2] == s && bGame[i][j+3] == s
                   && bGame[i][j+1] == '-' && i == 5){
                    bGame[i][j+1] = s;
					win +=1;

                    return;
                }
                else if(i !=5 && bGame[i][j] == s &&
                bGame[i][j+2] == s && bGame[i][j+3] == s && 
                bGame[i][j+1] == '-' && bGame[i+1][j+1] != '-' ){
                    bGame[i][j+1] = s;
					win +=1;
                    return;
                }
				else if(bGame[i][j+1] == s &&
                   bGame[i][j+2] == s && bGame[i][j+3] == s && 
                   bGame[i][j] == '-' && i == 5){
                    bGame[i][j] = s;
					win +=1;
                    return;
                }
                else if(i !=5 && bGame[i][j+1] == s &&
                bGame[i][j+2] == s && bGame[i][j+3] == s &&
                bGame[i][j] == '-' && bGame[i+1][j] != '-' ){
                    bGame[i][j] = s;
					win +=1;
                    return;
                }
                else{
                    continue;
				}
			}
		}

	}

	public void blockWin(char s){

		for(int i = 0; i < bGame.length; i++){
			for(int j = 0; j < bGame[0].length; j++){
				if (i > 2) {
					break;
				}
				if(bGame[i+3][j] == bGame[i+2][j] &&
				bGame[i+2][j] == bGame[i+1][j] && 
				bGame[i][j] == '-' && bGame[i+3][j] != '-'){
                    bGame[i][j] = s;
					block += 1;
                    return;
                }
                
                else{
                    continue;
                }
			}
		}

		for(int i = 0; i < bGame.length; i++){
            for(int j = 0; j < bGame[i].length-3; j++){
                if(i !=5 && bGame[i][j] == bGame[i][j+1]&&
                bGame[i][j+1] == bGame[i][j+2] && bGame[i][j] != '-'
                && bGame[i][j+3] == '-' && bGame[i+1][j+3] != '-' ){
                    bGame[i][j+3] = s;
					block += 1;
                    return;
                }
                else if(bGame[i][j] == bGame[i][j+1]&&
                   bGame[i][j+1] == bGame[i][j+2] && bGame[i][j] != '-'
                   && bGame[i][j+3] == '-' && i == 5){
                    bGame[i][j+3] = s;
					block += 1;
					
                    return;
                }
                else if(bGame[i][j] == bGame[i][j+1]&&
                   bGame[i][j+1] == bGame[i][j+3] && bGame[i][j] != '-'
                   && bGame[i][j+2] == '-' && i == 5){
                    bGame[i][j+2] = s;
					block += 1;
                    return;
                }
                else if(i !=5 && bGame[i][j] == bGame[i][j+1]&&
                bGame[i][j+1] == bGame[i][j+3] && bGame[i][j] != '-'
                && bGame[i][j+2] == '-' && bGame[i+1][j+2] != '-' ){
                    bGame[i][j+2] = s;
					block += 1;
                    return;
                }
                else if(bGame[i][j] == bGame[i][j+2]&&
                   bGame[i][j+2] == bGame[i][j+3] && bGame[i][j] != '-'
                   && bGame[i][j+1] == '-' && i == 5){
                    bGame[i][j+1] = s;
					block += 1;

                    return;
                }
                else if(i !=5 && bGame[i][j] == bGame[i][j+2]&&
                bGame[i][j+2] == bGame[i][j+3] && bGame[i][j] != '-'
                && bGame[i][j+1] == '-' && bGame[i+1][j+1] != '-' ){
                    bGame[i][j+1] = s;
					block += 1;
                    return;
                }
                else if(bGame[i][j+1] == bGame[i][j+2]&&
                   bGame[i][j+2] == bGame[i][j+3] && bGame[i][j+1] != '-'
                   && bGame[i][j] == '-' && i == 5){
                    bGame[i][j] = s;
					block += 1;
                    return;
                }
                else if(i !=5 && bGame[i][j+1] == bGame[i][j+2]&&
                bGame[i][j+2] == bGame[i][j+3] && bGame[i][j+1] != '-'
                && bGame[i][j] == '-' && bGame[i+1][j] != '-' ){
                    bGame[i][j] = s;
					block += 1;
                    return;
                }
                else{
                    continue;
                }
                
            }
        }

		for(int i = 3; i < bGame.length; i++){
			for(int j = 0; j < bGame[0].length-3; j++){
				if (bGame[i][j] == bGame[i-1][j+1]  && 
					bGame[i-1][j+1] == bGame[i-2][j+2] &&
					bGame[i-3][j+3] == '-' && bGame[i][j]!= '-' &&
                    bGame[i-2][j+3] != '-'){
					
                    bGame[i-3][j+3] = s;
					block +=1;
                    return;
				}
				
				else if (bGame[i][j] == bGame[i-1][j+1]  && 
				bGame[i-1][j+1] == bGame[i-3][j+3] &&
				bGame[i-2][j+2] == '-' && bGame[i][j]!= '-' &&
				bGame[i-1][j+2] != '-'){
					bGame[i-2][j+2] = s;
					block += 1;
					return;
				}
				else if (bGame[i][j] == bGame[i-2][j+2]  && 
				bGame[i-2][j+2] == bGame[i-3][j+3] &&
				bGame[i-1][j+1] == '-' && bGame[i][j]!= '-' &&
				bGame[i][j+1] != '-'){
					bGame[i-1][j+1] = s;
					block += 1;
					return;
				}
				else if (bGame[i-1][j+1] == bGame[i-2][j+2]  && 
				bGame[i-2][j+2] == bGame[i-3][j+3] &&
				bGame[i][j] == '-' && bGame[i-1][j+1] != '-' && i != bGame.length - 1
				&& bGame[i+1][j] != '-'){
					bGame[i][j] = s;
					block += 1;
					return;
			}

				else if (bGame[i-1][j+1] == bGame[i-2][j+2]  && 
				bGame[i-2][j+2] == bGame[i-3][j+3] &&
				bGame[i][j] == '-' && bGame[i-1][j+1] != '-' && i == bGame.length - 1){
					bGame[i][j] = s;
					block += 1;
					return;
				}
				else{
					continue;
				}
		}
		}

		for(int i = 0; i < bGame.length - 3; i++){
			for(int j = 0; j < bGame[0].length - 3; j++){
				if (bGame[i][j] == bGame[i+1][j+1] && 
					bGame[i+1][j+1] == bGame[i+2][j+2] &&
					bGame[i][j]!= '-' && bGame[i+3][j+3] == '-' && bGame[i+4][j+3] != '-' && i != bGame.length - 4){
					bGame[i+3][j+3] = s;
					block += 1;
					return;
				}
				else if(bGame[i][j] == bGame[i+1][j+1] && 
				bGame[i+1][j+1] == bGame[i+2][j+2] &&
				bGame[i][j]!= '-' && bGame[i+3][j+3] == '-' && i == bGame.length - 4){
					bGame[i+3][j+3] = s;
					block += 1;
					return;
				}
				else if(bGame[i][j] == bGame[i+1][j+1] && 
				bGame[i+1][j+1] == bGame[i+3][j+3] &&
				bGame[i][j]!= '-' && bGame[i+2][j+2] == '-' && bGame[i+3][j+2] != '-'){
					bGame[i+2][j+2] = s;
					block += 1;
					return;
				}
				else if(bGame[i][j] == bGame[i+2][j+2] && 
				bGame[i+2][j+2] == bGame[i+3][j+3] &&
				bGame[i][j]!= '-' && bGame[i+1][j+1] == '-' && bGame[i+2][j+1] != '-'){
					bGame[i+1][j+1] = s;
					block += 1;
					return;
				}
				else if(bGame[i+1][j+1] == bGame[i+2][j+2] && 
				bGame[i+2][j+2] == bGame[i+3][j+3] &&
				bGame[i+1][j+1]!= '-' && bGame[i][j] == '-' && bGame[i+1][j] != '-'){
					bGame[i][j] = s;
					block += 1;
					return;
				}
				else{
					continue;
				}


			}
		}
				

	}

	
	public void reset() {
		
		for(int i = 0; i < this.bGame.length; i++){
			for(int j = 0; j < this.bGame[0].length; j++){
				this.bGame[i][j] = '-';
			}
		}

	}

	
	
}
