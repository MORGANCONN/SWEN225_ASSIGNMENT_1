/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// line 8 "model.ump"
// line 101 "model.ump"
public class Game {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Game Attributes
    private int numberOfPlayers;
    private ArrayList<Player> listOfPlayers = new ArrayList<>();

    //Game Associations
    private Board board;
    private Card[] envelope = new Card[3];
    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Game(int aNumberOfPlayers, Board aBoard) {
        numberOfPlayers = aNumberOfPlayers;
        if (!setBoard(aBoard)) {
            throw new RuntimeException("Unable to create Game due to aBoard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setNumberOfPlayers(int aNumberOfPlayers) {
        boolean wasSet = false;
        numberOfPlayers = aNumberOfPlayers;
        wasSet = true;
        return wasSet;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /* Code from template association_GetOne */
    public Board getBoard() {
        return board;
    }

    /* Code from template association_SetUnidirectionalOne */
    public boolean setBoard(Board aNewBoard) {
        boolean wasSet = false;
        if (aNewBoard != null) {
            board = aNewBoard;
            wasSet = true;
        }
        return wasSet;
    }

    public void delete() {
        board = null;
    }

    // line 13 "model.ump"
    public void distributeCards() {
        ArrayList<WeaponCard> WeaponCards = generateWeaponCards();
        Collections.shuffle(WeaponCards);
        ArrayList<RoomCard> RoomCards = generateRoomCards();
        Collections.shuffle(RoomCards);
        ArrayList<CharacterCard> CharacterCards = generateCharacterCard();
        Collections.shuffle(CharacterCards);
        // Put cards into envelope
        envelope[0] = WeaponCards.remove(0);
        envelope[1] = RoomCards.remove(0);
        envelope[2] = CharacterCards.remove(0);
        // Merge remaining cards, shuffle
        ArrayList<Card> mergedCards = new ArrayList<>();
        mergedCards.addAll(WeaponCards);
        mergedCards.addAll(RoomCards);
        mergedCards.addAll(CharacterCards);
        Collections.shuffle(mergedCards);
        // Distribute Cards
        while (!mergedCards.isEmpty()) {
            for (Player P : listOfPlayers) {
                P.addCard(mergedCards.remove(0));
            }
        }
    }

    // line 21 "model.ump"
    public ArrayList<RoomCard> generateRoomCards() {
        ArrayList<RoomCard> rooms = new ArrayList<>();
        for (RoomCard.Room R : RoomCard.Room.values()) {
            rooms.add(new RoomCard(R));
        }
        return rooms;
    }

    // line 22 "model.ump"
    public ArrayList<WeaponCard> generateWeaponCards() {
        ArrayList<WeaponCard> weapons = new ArrayList<>();
        for (WeaponCard.WeaponType W : WeaponCard.WeaponType.values()) {
            weapons.add(new WeaponCard(W));
        }
        return weapons;
    }

    // line 23 "model.ump"
    public ArrayList<CharacterCard> generateCharacterCard() {
      ArrayList<CharacterCard> characterCards = new ArrayList<>();
      for (CharacterCard.Character C : CharacterCard.Character.values()) {
        characterCards.add(new CharacterCard(C));
      }
      return characterCards;
    }


    public String toString() {
        return super.toString() + "[" +
                "numberOfPlayers" + ":" + getNumberOfPlayers() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "board = " + (getBoard() != null ? Integer.toHexString(System.identityHashCode(getBoard())) : "null");
    }
}