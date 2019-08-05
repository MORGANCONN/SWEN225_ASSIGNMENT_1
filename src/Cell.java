/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// line 35 "model.ump"
// line 114 "model.ump"
public class Cell {

    //------------------------
    // ENUMERATIONS
    //------------------------


    public enum Room {Hallway, Kitchen, Ballroom, Conservatory, BillardRoom, Hall, Study, Library, Lounge, DiningRoom;}

    private boolean isDoor = false;

    public enum WallDirections {North, South, East, West;}

    private HashSet<WallDirections> walls;

    //------------------------
    // MEMBER VARIABLES
    //------------------------
    //Cell Attributes
    private Room cellRoom;

    private MoveableObject item;
    //Cell Associations
    private HashMap<Room, String> roomColors;
    private Board board;

    //------------------------
    // CONSTRUCTOR
    //------------------------
    public Cell(Room aCellRoom) {
        cellRoom = aCellRoom;
        walls = new HashSet<>();
        generateRoomColors();
    }

    /**
     *  designates a color for each room and stores in in a hashmap
     */
    private void generateRoomColors() {
        roomColors = new HashMap<>();
        roomColors.put(Room.Hallway,ConsoleColors.WHITE);
        roomColors.put(Room.Ballroom,ConsoleColors.BLUE);
        roomColors.put(Room.Hall,ConsoleColors.BLUE);
        roomColors.put(Room.BillardRoom,ConsoleColors.PURPLE);
        roomColors.put(Room.Study,ConsoleColors.PURPLE);
        roomColors.put(Room.Kitchen,ConsoleColors.PURPLE);
        roomColors.put(Room.Conservatory,ConsoleColors.GREEN);
        roomColors.put(Room.Lounge,ConsoleColors.GREEN);
        roomColors.put(Room.DiningRoom,ConsoleColors.BLUE);
        roomColors.put(Room.Library,ConsoleColors.GREEN);
    }


    //------------------------
    // INTERFACE
    //------------------------

    /**
     *  returns the item in the cell
     *  @return the movable object in the cell
     */
    public MoveableObject getItem() {
        return item;
    }

/**
     *  sets the item in the cell
     * @param item either a weapon or a player
     */
    public void setItem(MoveableObject item) {
        this.item = item;
    }

    /**
     *  returns the room the cell is in
     * @return returns the enum of the room
     */
    public Room getCellRoom() {
        return cellRoom;
    }

/**
     *  sets the room in the cell
     * @param cellRoom the new room of the cell
     */
    public void setCellRoom(Room cellRoom) {
        this.cellRoom = cellRoom;
    }

    /**
     *  adds a wall in a direction to the cell
     * @param direction of the wall added
     */
    public void addWall(WallDirections directions) {
        walls.add(directions);
    }

/**
     *  returns a hash set of all the walls the cell contains
     * @return hashSet of walls
     */
    public HashSet<WallDirections> getWalls() {
        return walls;
    }

    /**
     *  returns if the cell is a door or not
     * @return true or false
     */
    public boolean isDoor() {
        return isDoor;
    }
    /**
     *  sets a cell to a door or not
     * @param door if its a door
     */
    public void setDoor(boolean door) {
        isDoor = door;
    }

    /* Code from template association_GetOne */

    /**
     *  color of the rooms and first letter of the rooms name
     * if cell is empty it will be represented by _
     *  @return character value of the room
     */
    public String toString() {
        return (isDoor?ConsoleColors.RED:roomColors.get(cellRoom))+(item!=null?item.toString():(cellRoom.equals(Room.Hallway)?'_':cellRoom.toString().charAt(0)))+ConsoleColors.RESET;
    }
}