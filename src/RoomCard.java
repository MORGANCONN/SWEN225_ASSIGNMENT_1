/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 75 "model.ump"
// line 140 "model.ump"
public class RoomCard extends Card
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Room { Hallway, Kitchen, Ballroom, Conservatory, BillardRoom, Hall, Study, Library, Lounge, DiningRoom }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RoomCard Attributes
  private Room location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RoomCard(Player aPlayer, Room aLocation)
  {
    super(aPlayer);
    location = aLocation;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(Room aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public Room getLocation()
  {
    return location;
  }

  public void delete()
  {
    super.delete();
  }

  // line 80 "model.ump"
   public String toString(){
    // Return and Enum
  }

}