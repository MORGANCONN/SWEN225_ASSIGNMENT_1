/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 8 "model.ump"
// line 101 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int numberOfPlayers;

  //Game Associations
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aNumberOfPlayers, Board aBoard)
  {
    numberOfPlayers = aNumberOfPlayers;
    if (!setBoard(aBoard))
    {
      throw new RuntimeException("Unable to create Game due to aBoard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfPlayers(int aNumberOfPlayers)
  {
    boolean wasSet = false;
    numberOfPlayers = aNumberOfPlayers;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfPlayers()
  {
    return numberOfPlayers;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBoard(Board aNewBoard)
  {
    boolean wasSet = false;
    if (aNewBoard != null)
    {
      board = aNewBoard;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    board = null;
  }

  // line 13 "model.ump"
  public void distributeCards(){
    ArrayList WeaponCards = generateWeaponCards();
  ArrayList RoomCards = generateRoomCards();
  ArrayList CharacterCards = generateCharacterCard;
    // Choose Weapon, Character And Room to go into envelope
    // Merge remaining cards, shuffle and distrubute among players
  ArrayList mergedCards
  }

  // line 21 "model.ump"
  public void generateRoomCards(){
    
  }

  // line 22 "model.ump"
  public void generateWeaponCards(){
    
  }

  // line 23 "model.ump"
  public void generateCharacterCard(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfPlayers" + ":" + getNumberOfPlayers()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null");
  }
}