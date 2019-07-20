/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 89 "model.ump"
// line 150 "model.ump"
public class MoveableObject
{

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

  public boolean setLocation(Point aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public Point getLocation()
  {
    return location;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "location" + "=" + (getLocation() != null ? !getLocation().equals(this)  ? getLocation().toString().replaceAll("  ","    ") : "this" : "null");
  }
}