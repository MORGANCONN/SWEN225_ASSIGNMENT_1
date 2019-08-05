/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;



public class Player extends MoveableObject

{
     /**
     *  adds a card to the hand
     */
  public void addCard(Card remove) {
    cards.add(remove);
  }


  private CharacterCard.Character playerName;
  private Boolean canMakeAccusations = true;

  private List<Card> cards = new ArrayList<>();
  private HashMap<CharacterCard.Character,String> playerColors;


  public Player(CharacterCard.Character playerName) {
    this.playerName = playerName;
    playerColors = new HashMap<>();
    generatePlayerColors();
  }

  //------------------------
  // INTERFACECard

  //------------------------
  /* Code from template association_GetMany */

    /**
     *  returns card at that index   
     * *param index index of card in players hard
     * @return card object
     */
  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

    /**
     *  returns the character the player is
     * @return name of the player
     */
  public CharacterCard.Character getPlayerName() {
    return playerName;
  }

    /**
     *  sets the name of the player
     * @param playerName name of the player
     */
  public void setPlayerName(CharacterCard.Character playerName) {
    this.playerName = playerName;
  }

    /**
     *  adds unique colors for every player
     */
  private void generatePlayerColors() {
    playerColors = new HashMap<>();
    playerColors.put(CharacterCard.Character.MrsPeacock,ConsoleColors.BLUE);
    playerColors.put(CharacterCard.Character.ProfessorPlum,ConsoleColors.PURPLE);
    playerColors.put(CharacterCard.Character.ColonelMustard,ConsoleColors.YELLOW);
    playerColors.put(CharacterCard.Character.MissScarlett,ConsoleColors.RED);
    playerColors.put(CharacterCard.Character.MrGreen,ConsoleColors.GREEN);
    playerColors.put(CharacterCard.Character.MrsWhite,ConsoleColors.WHITE);
  }

    /**
     *  returns status of accusations
     * @return whether the player can make accusations or not
     */
    public Boolean getCanMakeAccusations() {
        return canMakeAccusations;
    }

    /**
     *  sets status of accusations
     * @param canMakeAccusations either true or false
     */
    public void setCanMakeAccusations(Boolean canMakeAccusations) {
        this.canMakeAccusations = canMakeAccusations;
    }

    /**
     *  returns a list of the players hand
     * @return list of cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     *  to string method
     * @return string
     */
    @Override
  public String toString() {
    return playerColors.get(playerName)+Character.toString(playerName.toString().charAt(0)).toLowerCase()+ConsoleColors.RESET;
  }
}