import java.util.ArrayList;
class Computer{
  public static void ComputerMove(String[][][]userBoard,String[][][]compBoard,ArrayList<String> userShips,ArrayList<String> ComputerShips,ArrayList<Move> moveHist){
   
    //turns true if ship falls within the grid
    boolean validShip=false;

    //cause index does not match
    int rowCoord=-1;
    int colCoord=-1;

    //generating computer ships
    while(!validShip){
      rowCoord=(int)(Math.random()*10);
      colCoord=(int)(Math.random()*10);
      if((rowCoord+colCoord)%2==0){
        validShip=true;
      }
      try{
        if(userBoard[1][rowCoord][colCoord].equals("ged")){
          validShip=false;
        }
      }
      catch(Exception e){}
    }
    String shipPlace= userBoard[2][rowCoord][colCoord];
    int shipHit;
    boolean shipHitB;
    boolean shipDes;
    shipHit=guessResultComputer(userBoard,userShips,moveHist,colCoord,rowCoord);

    //making the difference bewteen hitting and sinking the ship 
    if(shipHit==1){
      shipHitB= true;
      shipDes= false;
    }
    else if(shipHit==2){
      shipHitB=true;
      shipDes=true;
    }
    else{
      shipHitB=false;
      shipDes=false;
    }
    moveHist.add(new Move(colCoord,rowCoord,shipHitB,shipDes,shipPlace));
  }

public static int guessResultComputer(String[][][]adjBoard,ArrayList<String> shipList,ArrayList<Move> moveHist,int x,int y){
    String boardPlace=adjBoard[1][y][x];
    int shipHit=0;
    adjBoard[1][y][x]="ged";

    //When Computer misses
    if(boardPlace==null){
      adjBoard[0][y][x]="O";
    }


    //when computer hits
    else{
      shipHit=1;
      adjBoard[0][y][x]="\u001B[31mX\u001B[0m";
      if(!shipContainComputer(adjBoard,boardPlace)){
        shipList.remove(shipList.indexOf(boardPlace));
        shipHit=2;
      }
    }
    return shipHit;
  }


  //scanning the board 
  public static boolean shipContainComputer(String[][][]board,String ship){
    for(int r = 0;r<10;r++){
      for(int c = 0; c<10;c++){
        try{
          if(board[1][r][c].equals(ship)){
            return true;
          }
        }
        catch(Exception e){}
      }
    }
    return false;
  }
  
}
