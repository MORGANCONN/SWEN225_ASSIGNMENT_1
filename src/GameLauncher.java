/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 84 "model.ump"
// line 145 "model.ump"
public class GameLauncher
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameLauncher Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameLauncher(Game aGame)
  {
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create GameLauncher due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGame(Game aNewGame)
  {
    boolean wasSet = false;
    if (aNewGame != null)
    {
      game = aNewGame;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    game = null;
  }

}