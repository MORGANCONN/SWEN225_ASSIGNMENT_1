/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;

// line 27 "model.ump"
// line 107 "model.ump"
public class Board
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Room { Hallway, Kitchen, Ballroom, Conservatory, BillardRoom, Hall, Study, Library, Lounge, DiningRoom }
  public enum Direction { Left, Right, Up, Down }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private Cell[][] cells = new Cell[25][24];
  private List<Player> players;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(int numberOfPlayers)
  {
    players = generatePlayers(numberOfPlayers);
    generateBoard();
  }

  private List<Player> generatePlayers(int numberOfPlayers) {
  }

  private void generateBoard() {
  }

  // line 31 "model.ump"
  public void movePlayer(Player player, Direction direction){
    
  }

  // line 32 "model.ump"
  public void moveObjectTo(MoveableObject object, Point location){
    
  }

}