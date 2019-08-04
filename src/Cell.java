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
    public MoveableObject getItem() {
        return item;
    }

    public void setItem(MoveableObject item) {
        this.item = item;
    }

    public Room getCellRoom() {
        return cellRoom;
    }

    public void setCellRoom(Room cellRoom) {
        this.cellRoom = cellRoom;
    }

    public void addWall(WallDirections directions) {
        walls.add(directions);
    }

    public boolean isDoor() {
        return isDoor;
    }

    public void setDoor(boolean door) {
        isDoor = door;
    }

    /* Code from template association_GetOne */

    public String toString() {
        return (isDoor?ConsoleColors.RED:roomColors.get(cellRoom))+(item!=null?item.toString():(cellRoom.equals(Room.Hallway)?'_':cellRoom.toString().charAt(0)))+ConsoleColors.RESET;
    }
}