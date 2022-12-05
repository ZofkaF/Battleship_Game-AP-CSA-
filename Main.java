import java.util.ArrayList;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    

    //creating boards
    
    String[][][]userBoard = new String[3][10][10];
    String[][][]boardComputer = new String[3][10][10];
    intFillBoard(userBoard,0);
    intFillBoard(boardComputer,1);
    
    
    //moves history
    ArrayList<Move> moveHistComputer= new ArrayList<Move>();
    ArrayList<String> usersShips= new ArrayList<String>();
    ArrayList<String> ComputerShips= new ArrayList<String>();
    shipLeftFill(usersShips);
    shipLeftFill(ComputerShips);
    Scanner enter = new Scanner(System.in);
    space();
    
    
    System.out.print("\n\n\tWelcome to battleship game for AP CSA \n \n\n\n\n\n\tThis is your battleship board. Now place your ships.\n\n");
    printBoard(userBoard,0);
    System.out.print("\n\nPRESS ENTER TO PLAY\n");
    String wait = enter.nextLine();
    
    //hide ships in hiddenComputer board
    //hides user ships
    
    CompInputShips.autoFill(boardComputer);
    UserInputShips.userFill(userBoard);
    int hit=-1;
    boolean gameWon=false;
    
    for(int r = 0;r<10;r++){
      for(int c = 0; c<10;c++){
        boardComputer[2][r][c]=boardComputer[0][r][c];
        userBoard[2][r][c]=userBoard[1][r][c];
      }
    }
    
    while(!gameWon){
      hit=UserGuess.UserInput(userBoard,boardComputer,usersShips,ComputerShips,moveHistComputer,hit);
      if(ComputerShips.size()==0){
        gameWon= true;
      }
      if(!gameWon){
        Computer.ComputerMove(userBoard,boardComputer,usersShips,ComputerShips,moveHistComputer);
        if(usersShips.size()==0){
          gameWon= true;
        }
      }
    }
  }
  
  
  //Creates default board
  public static void intFillBoard(String[][][] grid, int e){
    for(int r = 0;r<10;r++){
      for(int c = 0; c<10;c++){
        grid [e][r][c] = "-";
      }
    }
  }
  
  //Numbering the axis
  public static void printBoard(String[][][] grid, int a){
    for(int r = 0;r<10;r++){
      System.out.print(10-r +"   ");
      if(r!=0){
        System.out.print(" ");
      }
      for(int c = 0; c<10;c++){
        System.out.print(grid[a][r][c] + " ");
      }
      System.out.print("\n");
    }
    System.out.print("\n     1 2 3 4 5 6 7 8 9 10");
  }
  
  //Making it pretty 
  public static void space(){
    for (int i=1;i<=20;i++){
      System.out.print("\n");
    }
  }
  //fills ship list 
  public static void shipLeftFill(ArrayList<String> list){
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");
  }
  
  //adjust input coordinates to array index
  public static int gTOaX(int graphX){
    return graphX-1;
  }
  //cause its from 10 to 1
  public static int gTOaY(int graphY){
    return 10-graphY;
  }
}
