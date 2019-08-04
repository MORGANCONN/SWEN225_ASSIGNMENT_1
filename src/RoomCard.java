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
  private Room roomName;


  public RoomCard(Room roomName)
  {
    this.roomName = roomName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(Room aLocation)
  {
    boolean wasSet = false;
    roomName = aLocation;
    wasSet = true;
    return wasSet;
  }

  public Room getRoomName()
  {
    return roomName;
  }

  // line 80 "model.ump"
   public String toString(){
    return roomName.toString();
  }

}