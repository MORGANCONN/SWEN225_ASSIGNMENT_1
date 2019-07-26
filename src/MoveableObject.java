/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;

// line 89 "model.ump"
// line 150 "model.ump"
public class MoveableObject
{
public enum Direction{
  NORTH,SOUTH,EAST,WEST
}
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MoveableObject Attributes
  private Point location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MoveableObject(Point aLocation)
  {
    location = aLocation;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setLocation(Point aLocation)
  {
    location = new Point(aLocation);
  }

  public Point getLocation()
  {
    return location;
  }

  public void move(Direction direction){
    switch (direction){
      case NORTH:
        location.y--;
      case SOUTH:
        location.y++;
      case EAST:
        location.x++;
      case WEST:
        location.x--;
    }
  }

  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "location" + "=" + (getLocation() != null ? !getLocation().equals(this)  ? getLocation().toString().replaceAll("  ","    ") : "this" : "null");
  }
}