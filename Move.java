class Move{

  //Inputed by Computer or User
  private int xCoord;
  private int yCoord;

  //false or true, hit or miss
  private boolean hit;
  //ship data
  private boolean shipDestroy;
  private String shipType;
  
  public Move(int x, int y, boolean missORhit, boolean sD, String ship){
    xCoord=x;
    yCoord=y;
    hit=missORhit;
    shipDestroy=sD;
    shipType=ship;
  }
  
  public boolean getHit(){
    return hit;
  }
  
  public boolean getshipDestroy(){
    return shipDestroy;
  }
  
  public int getX(){
    return xCoord;
  }
  
  public int getY(){
    return yCoord;
  }
  
  public String getShip(){
    return shipType;
  }
  
}