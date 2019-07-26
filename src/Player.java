/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;

// line 41 "model.ump"
// line 119 "model.ump"
public class Player extends MoveableObject
{
public enum playerName{
  MissScarlett,ColonelMustard, MrsWhite, MrGreen, MrsPeacock,ProfessorPlum
}
  //------------------------
  // MEMBER VARIABLES
  //------------------------
  private playerName playerName;
  //Player Associations
  private List<Card> cards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(Point aLocation, playerName playerName) {
    super(aLocation);
    this.playerName = playerName;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

  public Player.playerName getPlayerName() {
    return playerName;
  }

  public void setPlayerName(Player.playerName playerName) {
    this.playerName = playerName;
  }
}