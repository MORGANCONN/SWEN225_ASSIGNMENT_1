/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;

// line 41 "model.ump"
// line 119 "model.ump"
public class Player extends MoveableObject
{
  public void addCard(Card remove) {
    cards.add(remove);
  }

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  private CharacterCard.Character playerName;
  //Player Associations
  private List<Card> cards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(/**Point aLocation,**/ CharacterCard.Character playerName) {
    super(/**aLocation**/);
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

  public CharacterCard.Character getPlayerName() {
    return playerName;
  }

  public void setPlayerName(CharacterCard.Character playerName) {
    this.playerName = playerName;
  }

  @Override
  public String toString() {
    return Character.toString(playerName.toString().charAt(0));
  }
}