class CompInputShips{
  public static void autoFill(String[][][] grid){

    //definitions
    int rowCoord=0;
    int colCoord=0;
    int dir=0;
    int finalRowCoord=0;
    int finalColCoord=0;
    int shipLength;
    String shipChar="";
    boolean validShip;
    
    
    //going trough all the ships 
    for(int i=0;i<5;i++){
      validShip=false;
      if(i==0){
        shipChar="a";
        shipLength=5;
      }
      else if(i==1){
        shipChar="b";
        shipLength=4;
      }
      else if(i==2){
        shipChar="c";
        shipLength=3;
      }
      else if(i==3){
        shipChar="d";
        shipLength=3;
      }
      else{
        shipChar="e";
        shipLength=2;
      }
      
      while(!validShip){

        //initial random point
        rowCoord=(int)(Math.random()*10);
        colCoord=(int)(Math.random()*10);

        //random direction
        dir=(int)(Math.random()*4);
        /*
        direction index 
          0= row+ (right)
          1= row- (left)
          2= col- (up) 
          3= col+ (down)
        */

        //placing the ship based on direction
        if(dir==0||dir==1){
          if(dir==0){
            finalRowCoord= (shipLength-1)+rowCoord;
          }
          else{
            finalRowCoord= rowCoord-(shipLength-1);
          }
          validShip=validShipRow(grid,finalRowCoord,rowCoord,colCoord);
        }
        else{
          if(dir==2){
            finalColCoord= colCoord-(shipLength-1);
          }
          else{
            finalColCoord= (shipLength-1)+colCoord;
          }
          validShip=validShipCol(grid,finalColCoord,colCoord,rowCoord);
        }
      }
      
      
      if(dir==0||dir==1){
        for(int q=rowCoord;q<=finalRowCoord;q++){
          grid[0][colCoord][q]=shipChar;
        }
        for(int q=rowCoord;q>=finalRowCoord;q--){
          grid[0][colCoord][q]=shipChar;
        }
      }
      else{
        for(int q=colCoord;q<=finalColCoord;q++){
          grid[0][q][rowCoord]=shipChar;
        }
        for(int q=colCoord;q>=finalColCoord;q--){
          grid[0][q][rowCoord]=shipChar;
        }
      }
    }
  }
  public static boolean validShipRow(String[][][] grid,int finalPos,int startPosRow,int startPosCol){
    for(int i=startPosRow;i<=finalPos;i++){
      try{
        String a= grid[0][startPosCol][i];
        if(a!=null){
          return false;
        }
      }
      catch(Exception e){
        return false;
      }
    }
    for(int i=startPosRow;i>=finalPos;i--){
      try{
        String a= grid[0][startPosCol][i];
        if(a!=null){
          return false;
        }
      }
      catch(Exception e){
        return false;
      }
    }
    return true;
  }
  
  public static boolean validShipCol(String[][][] grid,int finalPos,int startPosCol,int startPosRow){
    for(int i=startPosCol;i<=finalPos;i++){
      try{
        String a= grid[0][i][startPosRow];
        if(a!=null){
          return false;
        }
      }
      catch(Exception e){
        return false;
      }
    }
    for(int i=startPosCol;i>=finalPos;i--){
      try{
        String a= grid[0][i][startPosRow];
        if(a!=null){
          return false;
        }
      }
      catch(Exception e){
        return false;
      }
    }
    return true;
  }
  
}