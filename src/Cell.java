/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 35 "model.ump"
// line 114 "model.ump"
public class Cell
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Room { Hallway, Kitchen, Ballroom, Conservatory, BillardRoom, Hall, Study, Library, Lounge, DiningRoom }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Cell Attributes
  private Room cellRoom;

  //Cell Associations
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cell(Room aCellRoom, Board aBoard)
  {
    cellRoom = aCellRoom;
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create cell due to board. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCellRoom(Room aCellRoom)
  {
    boolean wasSet = false;
    cellRoom = aCellRoom;
    wasSet = true;
    return wasSet;
  }

  public Room getCellRoom()
  {
    return cellRoom;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setBoard(Board aBoard)
  {
    boolean wasSet = false;
    //Must provide board to cell
    if (aBoard == null)
    {
      return wasSet;
    }

    //board already at maximum (600)
    if (aBoard.numberOfCells() >= Board.maximumNumberOfCells())
    {
      return wasSet;
    }
    
    Board existingBoard = board;
    board = aBoard;
    if (existingBoard != null && !existingBoard.equals(aBoard))
    {
      boolean didRemove = existingBoard.removeCell(this);
      if (!didRemove)
      {
        board = existingBoard;
        return wasSet;
      }
    }
    board.addCell(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Board placeholderBoard = board;
    this.board = null;
    if(placeholderBoard != null)
    {
      placeholderBoard.removeCell(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "cellRoom" + "=" + (getCellRoom() != null ? !getCellRoom().equals(this)  ? getCellRoom().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null");
  }
}