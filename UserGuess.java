
import java.util.ArrayList;
import java.util.Scanner;


class UserGuess{
  public static int UserInput(String[][][]userBoard,String[][][]compBoard,ArrayList<String> userShips,ArrayList<String> aiShips,ArrayList<Move> moveHistAI,int hit){
    boolean gotXCoord=false;
    boolean gotYCoord=false;
    boolean cordsSet=false;

    //done with point
    boolean done=false;
    int xCoord=0;
    int yCoord=0;
    Main.space();
    while(!cordsSet){
      gotXCoord=false;
      gotYCoord=false;
      if(hit==0){
        System.out.print("\tYou missed\n");
      }
      else if(hit==1){
        System.out.print("\tYou hit a ship\n");
      }
      else if(hit==2){
        System.out.print("\tYou sunk a ship\n");
      }
      hit=-1;
      while(!gotXCoord){
        selectScreen(userBoard,compBoard);
        System.out.print("\n(");
        xCoord= getCoord();
        if(xCoord!=-1){
          gotXCoord=true;
        }
      }
      
      Main.space();

      //allows you to change your point
      while(!gotYCoord){
        selectScreen(userBoard,compBoard);
        System.out.print("If you want to change your point type in \"CANCEL\" ");
        System.out.print("\n("+xCoord+",");
        yCoord= getCoord();
        
      //coordinates check
        if(yCoord>0){
          gotYCoord=true;

          //checking we are not repeating your guessed point
          if((compBoard[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)]==null)||!compBoard[0][Main.gTOaY(yCoord)][Main.gTOaX(xCoord)].equals("ged")){
            cordsSet=true;
            done=true;
            gotYCoord=true;
          }
          else{
            yCoord=-1;
          }
        }
        Main.space();
        if(yCoord==-1){
          System.out.print("\tYou already guessed that point\n");
        }
        else if(yCoord<0){
          System.out.print("\tThat was not a valid coordinate.\n");
        }
        if(yCoord<=-1){
          done=false;
          gotYCoord=true;
        }
      }
      if(!done){
        gotXCoord=false;
        gotYCoord=false;
        cordsSet=false;
        xCoord=0;
        yCoord=0;
      }
    }
    return guessResults(compBoard,aiShips,moveHistAI,xCoord,yCoord);
  }

  /*
    RED = "\u001B[31m";
    GREEN = "\u001B[32m";
    YELLOW = "\u001B[33m";
    BLUE = "\u001B[34m";
    PURPLE = "\u001B[35m";
    */
  public static int guessResults(String[][][]adjBoard,ArrayList<String> shipList,ArrayList<Move> moveHist,int x,int y){
    x=Main.gTOaX(x);
    y=Main.gTOaY(y);
    String boardPlace=adjBoard[0][y][x];
    int hit;
    adjBoard[0][y][x]="ged";
    if(boardPlace==null){
      hit=0;
      adjBoard[1][y][x]="O";
    }
    else{
      if(shipContain(adjBoard,boardPlace)){
        hit=1;//hit ship not sink
        adjBoard[1][y][x]="\u001B[31mX\u001B[0m";
      }
      else{
        String colorShip="";
        hit=2;
        int length=0;
        if (boardPlace.equals("a")){
          colorShip="\u001B[31m"; //red
        }
        else if(boardPlace.equals("b")){
          colorShip="\u001B[32m"; //green
        }
        else if(boardPlace.equals("c")){
          colorShip="\u001B[33m"; //yellow
        }
        else if(boardPlace.equals("s")){
          colorShip="\u001B[36m"; //blue
        }
        else if(boardPlace.equals("d")){
          colorShip="\u001B[35m"; //purple
        }

        //scans the whole board and makes sure that you do not know what type of ship was hit
        for(int r = 0;r<10;r++){
          for(int c = 0; c<10;c++){
            try{
              if(adjBoard[2][r][c].equals(boardPlace)){
                adjBoard[1][r][c] = (colorShip+"o\u001B[0m");//white
              }
            }
            catch(Exception e){}
          }
        }
        shipList.remove(shipList.indexOf(boardPlace));
      }
    }
    return hit;
  }
  
  
  public static int getCoord(){
    Scanner coordInput = new Scanner(System.in);
    String stringCoord = coordInput.nextLine();
    try{
      int newCoord = Integer.parseInt(stringCoord);
      if(newCoord>10||newCoord<1){
        throw new Exception();
      }
      return newCoord;
    }
    catch(Exception e){
      Main.space();
      System.out.print("\tEnter a valid number (from 0 to 10).\n");
      stringCoord=stringCoord.toUpperCase();
      if(stringCoord.equals("CANCEL")){
        return -2;
      }
    }
    return -1;
  }
  
  public static boolean shipContain(String[][][]board,String ship){
    for(int r = 0;r<10;r++){
      for(int c = 0; c<10;c++){
        try{
          if(board[0][r][c].equals(ship)){
            return true;
          }
        }
        catch(Exception e){}
      }
    }
    return false;
  }
  
  //default after every move
  public static void selectScreen(String[][][]userBoard,String[][][]compBoard){
    System.out.print("\nIt is your move now!\n\nYour board\n");
    Main.printBoard(userBoard,0);
    System.out.print("\n\nComputer's Board\n");
    Main.printBoard(compBoard,1);
    System.out.print("\n\n\tWhat point would you like to guess?\n");
  }

}
