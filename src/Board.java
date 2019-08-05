/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;


public class Board {

      /**
     *  returns cell at location
     * @param location of cell
     * @return Cell
     */
    public Cell getLocation(Point location) {
        return cells[location.y][location.x];
    }

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Room {Hallway, Kitchen, Ballroom, Conservatory, BillardRoom, Hall, Study, Library, Lounge, DiningRoom}

    public enum Direction {Left, Right, Up, Down}

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    // spawn points hashmap
    private HashMap<CharacterCard.Character, Point> spawnPoints = new HashMap<>();
    private HashMap<Weapon, Point> weaponSpawn = new HashMap<>();

    private Cell[][] cells = new Cell[25][24];
    private List<Player> players;
    private List<Weapon> weapons = new ArrayList<>();

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Board(List<Player> playerList) {
        players = playerList;
        setSpawnPoints();
        setWeaponPoints();
        generateBoard();
        generatePlayers(players);
        generateWeapons();
    }
    /**
     *  Spawns weapons at cells on the board
     */
    private void generateWeapons() {
        for (Weapon w : weaponSpawn.keySet()) {
            Point wp = weaponSpawn.get(w);
            w.setLocation(wp);
            cells[wp.y][wp.x].setItem(w);
            weapons.add(w);
        }
    }

    /**
     *  Spawns Players at cells on the board if the player is in the list
     * @param players list of all players in the game
     */
    private void generatePlayers(List<Player> players) {
        for (Player p : players) {
            if (spawnPoints.containsKey(p.getPlayerName())) {
                Point sp = spawnPoints.get(p.getPlayerName());
                p.setLocation(sp);
                cells[sp.y][sp.x].setItem(p);
            }
        }
    }

    /**
     *  adds spawn points for every room into a list then shuffling and assigning to weapons
     */
    private void setWeaponPoints() {
        List<Point> wPoints = new ArrayList<>();
        wPoints.add(new Point(2, 1)); /// Kitchen
        wPoints.add(new Point(11, 2)); /// Ball Room
        wPoints.add(new Point(21, 1)); /// Conservatory
        wPoints.add(new Point(21, 10)); /// Billiard Room
        wPoints.add(new Point(21, 16)); /// Library
        wPoints.add(new Point(21, 22)); /// Study
        wPoints.add(new Point(12, 21)); /// Hall
        wPoints.add(new Point(3, 21)); /// Lounge
        wPoints.add(new Point(3, 12)); /// Dining Room
        Collections.shuffle(wPoints);
        int i = 0;
        for (Weapon.Weapons w : Weapon.Weapons.values()) {
            Weapon weaponObj = new Weapon(w);
            weaponSpawn.put(weaponObj, wPoints.get(i++));
        }
    }

    /**
     *  adds the spawn points of every character into a map
     */
    private void setSpawnPoints() {
        spawnPoints.put(CharacterCard.Character.MrsWhite, new Point(9, 0));
        spawnPoints.put(CharacterCard.Character.MrGreen, new Point(14, 0));
        spawnPoints.put(CharacterCard.Character.MrsPeacock, new Point(23, 6));
        spawnPoints.put(CharacterCard.Character.ProfessorPlum, new Point(23, 19));
        spawnPoints.put(CharacterCard.Character.MissScarlett, new Point(7, 24));
        spawnPoints.put(CharacterCard.Character.ColonelMustard, new Point(0, 17));
    }

    /**
     *  Populates the empty cells adding walls around the edges of the map then fills in
     *  each room
     */
    private void generateBoard() {
        //Set all as hallways
        for (int row = 0; row < 25; row++) {
            for (int col = 0; col < 24; col++) {
                cells[row][col] = new Cell(Cell.Room.Hallway);
                fixWall(row, col, 0, 0, 24, 23);
            }
        }
        // Make Impassable Blocks
        clearImpassableBlocks();

        // Make each room
        generateLounge();
        generateHall();
        generateStudy();
        generateLibrary();
        generateDiningRoom();
        generateKitchen();
        generateBallRoom();
        generateBilliardRoom();
        generateConservatory();
        generateCellar();

    }

    /**
     * Creates an impassable cellar at the center of the map
     */
    private void generateCellar() {
        for (int row = 10; row <= 16 ; row++) {
            for (int col = 10; col <= 14 ; col++) {
                cells[row][col] = null;
            }
        }
    }

    /**
     *  sets up all the Conservatory cells, adding walls and entrances
     */
    private void generateConservatory() {
        for (int row = 1; row < 6; row++) {
            for (int col = 18; col < 24; col++) {
                if (row == 5 && (col == 18 || col == 23)) {
                    continue;
                }
                if (col == 18) {
                    cells[row][col].addWall(Cell.WallDirections.West);
                    if (row == 4) {
                        cells[row][col].setDoor(true);
                    }
                }
                if (row == 5) {
                    if (col == 19) {
                        cells[row][col].addWall(Cell.WallDirections.West);
                    }
                    cells[row][col].addWall(Cell.WallDirections.South);
                }
                cells[row][col].setCellRoom(Cell.Room.Conservatory);
            }

        }
    }

    /**
     *  sets up all the Billiard Room cells, adding walls and entrances
     */
    private void generateBilliardRoom() {
        for (int row = 8; row < 13; row++) {
            for (int col = 18; col < 24; col++) {
                cells[row][col].setCellRoom(Cell.Room.BillardRoom);
                if (row == 8) {
                    cells[row][col].addWall(Cell.WallDirections.North);
                }
                if (row == 12) {
                    if (col != 22) {
                        cells[row][col].addWall(Cell.WallDirections.South);
                    } else {
                        cells[row][col].setDoor(true);
                    }
                }
                if (col == 18) {
                    if (row != 9) {
                        cells[row][col].addWall(Cell.WallDirections.West);
                    } else {
                        cells[row][col].setDoor(true);
                    }
                }
            }
        }
    }

    /**
     *  sets up all the Library cells, adding walls and entrances
     */
    private void generateLibrary() {
        for (int row = 14; row < 19; row++) {
            for (int col = 17; col < 24; col++) {
                if ((row == 14 && col == 17) || (row == 14 && col == 23) || (row == 18 && col == 17) || (row == 18 && col == 23)) {
                    continue;
                }
                cells[row][col].setCellRoom(Cell.Room.Library);
                if (row == 14 && (col <= 18 || col >= 21)) {
                    if (col == 18) {
                        cells[row][col].addWall(Cell.WallDirections.West);
                    }
                    cells[row][col].addWall(Cell.WallDirections.North);
                } else if (row == 14 && col == 20) {
                    cells[row][col].setDoor(true);
                }
                if (row == 18) {
                    if (col == 18) {
                        cells[row][col].addWall(Cell.WallDirections.West);
                    }
                    cells[row][col].addWall(Cell.WallDirections.South);
                }
                if (col == 17) {
                    if (row == 15) {
                        cells[row][col].addWall(Cell.WallDirections.North);
                        cells[row][col].addWall(Cell.WallDirections.West);
                    } else if (row == 16) {
                        cells[row][col].setDoor(true);
                    } else {
                        cells[row][col].addWall(Cell.WallDirections.South);
                        cells[row][col].addWall(Cell.WallDirections.West);
                    }
                }
            }
        }
    }

    /**
     *  sets up all the Study cells, adding walls and entrances
     */
    private void generateStudy() {
        for (int row = 21; row < 25; row++) {
            for (int col = 17; col < 24; col++) {
                if (col == 17 && row == 24) {
                    continue;
                }
                cells[row][col].setCellRoom(Cell.Room.Study);
                if (col == 17) {
                    cells[row][col].addWall(Cell.WallDirections.West);
                }
                if (row == 21 && col > 17) {
                    cells[row][col].addWall(Cell.WallDirections.North);
                } else if (row == 21) {
                    cells[row][col].setDoor(true);
                }
            }
        }
    }

    /**
     *  sets up all the Hall cells, adding walls and entrances
     */
    private void generateHall() {
        for (int row = 18; row < 25; row++) {
            for (int col = 9; col < 15; col++) {
                cells[row][col].setCellRoom(Cell.Room.Hall);
                if (row == 18 && (col <= 10 || col >= 13)) {
                    cells[row][col].addWall(Cell.WallDirections.North);
                } else if (row == 18 && col > 10) {
                    cells[row][col].setDoor(true);
                }
                if (col == 9) {
                    cells[row][col].addWall(Cell.WallDirections.West);
                }
                if (col == 14 && (row <= 19 || row >= 21)) {
                    cells[row][col].addWall(Cell.WallDirections.East);
                } else if (col == 14 && row > 19) {
                    cells[row][col].setDoor(true);
                }
            }
        }
    }

    /**
     *  sets up all the Lounge cells, adding walls and entrances
     */
    private void generateLounge() {
        for (int row = 19; row < 25; row++) {
            for (int col = 0; col < 7; col++) {
                // bottom corner
                if (row == 24 && col == 6) {
                    continue;
                }
                cells[row][col].setCellRoom(Cell.Room.Lounge);
                if (col <= 5 && row == 19) {
                    cells[row][col].addWall(Cell.WallDirections.North);
                }
                if (col == 6 && row <= 23) {
                    cells[row][col].addWall(Cell.WallDirections.East);
                }
                if (col == 6 && row == 19) {
                    cells[row][col].setDoor(true);
                }
            }
        }
    }

    /**
     *  sets up all the Dining Room cells, adding walls and entrances
     */
    private void generateDiningRoom() {
        // Generate First Half
        for (int row = 9; row <= 15; row++) {
            for (int col = 0; col <= 4; col++) {
                if (row == 9) {
                    cells[row][col].addWall(Cell.WallDirections.North);
                    if (col == 4) {
                        cells[row][col].addWall(Cell.WallDirections.East);
                    }
                }
                if (row == 15) {
                    cells[row][col].addWall(Cell.WallDirections.South);
                }
                cells[row][col].setCellRoom(Cell.Room.DiningRoom);
            }
        }
        // Generate Second half
        for (int row = 10; row <= 15; row++) {
            for (int col = 5; col <= 7; col++) {
                if (row == 10) {
                    cells[row][col].addWall(Cell.WallDirections.North);
                }
                if (row == 15) {
                    cells[row][col].addWall(Cell.WallDirections.South);
                }
                if (col == 7) {
                    if (row == 12) {
                        cells[row][col].setDoor(true);
                    } else {
                        cells[row][col].addWall(Cell.WallDirections.East);
                    }
                }
                cells[row][col].setCellRoom(Cell.Room.DiningRoom);
            }
        }
    }

    /**
     *  sets up all the Kitchen cells, adding walls and entrances
     */
    private void generateKitchen() {
        for (int row = 1; row <= 6; row++) {
            for (int col = 0; col <= 5; col++) {
                if (col == 0 && row == 6) {
                    continue;
                }
                if (col == 5) {
                    cells[row][col].addWall(Cell.WallDirections.East);
                }
                if (row == 6) {
                    if (col != 4) {
                        cells[row][col].addWall(Cell.WallDirections.South);
                    } else {
                        cells[row][col].setDoor(true);
                    }
                }
                cells[row][col].setCellRoom(Cell.Room.Kitchen);
            }
        }
    }

    /**
     *  sets up all the Ballroom cells, adding walls and entrances
     */
    private void generateBallRoom() {
        // Generate Most Of Ball Room
        for (int row = 2; row <= 7; row++) {
            for (int col = 8; col <= 15; col++) {
                if (row == 3) {
                    if (col == 8 || col == 9 || col == 14 || col == 15) {
                        cells[row][col].addWall(Cell.WallDirections.North);
                    }
                }
                if (row == 7) {
                    if (col == 9 || col == 14) {
                        cells[row][col].setDoor(true);
                    } else {
                        cells[row][col].addWall(Cell.WallDirections.South);
                    }
                }
                if (row == 5) {
                    if (col == 8 || col == 15) {
                        cells[row][col].setDoor(true);
                    }
                }
                if (col == 15) {
                    if (!cells[row][col].isDoor())
                        cells[row][col].addWall(Cell.WallDirections.East);
                }
                if (col == 8) {
                    if (!cells[row][col].isDoor())
                        cells[row][col].addWall(Cell.WallDirections.West);
                }
                cells[row][col].setCellRoom(Cell.Room.Ballroom);
            }
        }
        // Generate Remaining Section Of Ballroom
        for (int col = 10; col <= 13; col++) {
            if (col == 10) {
                cells[1][col].addWall(Cell.WallDirections.West);
            }
            if (col == 13) {
                cells[1][col].addWall(Cell.WallDirections.East);
            }
            cells[1][col].setCellRoom(Cell.Room.Ballroom);
        }
    }


    /**
     *  sets up all the conservatory cells, adding walls and entrances
     */
    private void clearImpassableBlocks() {
        // Clear top row apart from spawn points
        for (int col = 0; col < 24; col++) {
            if (col == 9 || col == 14) {
                cells[0][col].addWall(Cell.WallDirections.North);
                cells[0][col].addWall(Cell.WallDirections.East);
                cells[0][col].addWall(Cell.WallDirections.West);
            } else {
                cells[1][col].addWall(Cell.WallDirections.North);
                cells[0][col] = null;
            }
        }
        // Clear apropriate cells from second to top row
        for (int col = 0; col < 24; col++) {
            if (col == 6 || col == 17) {
                cells[2][col].addWall(Cell.WallDirections.North);
                cells[1][col - 1].addWall(Cell.WallDirections.East);
                cells[1][col + 1].addWall(Cell.WallDirections.West);
                cells[1][col] = null;
            }
        }
        // Clear appropraite bottom row cells
        for (int col = 0; col < 24; col++) {
            if (col == 6 || col == 8 || col == 17 || col == 15) {
                cells[24][col] = null;
                cells[23][col].addWall(Cell.WallDirections.South);
                cells[24][col - 1].addWall(Cell.WallDirections.East);
                cells[24][col + 1].addWall(Cell.WallDirections.West);
            }
        }
        // Clear appropraite far left row cells
        for (int row = 0; row < 25; row++) {
            if (row == 6 || row == 8 || row == 16 || row == 18) {
                cells[row - 1][0].addWall(Cell.WallDirections.South);
                cells[row][1].addWall(Cell.WallDirections.West);
                cells[row + 1][0].addWall(Cell.WallDirections.North);
                cells[row][0] = null;
            }
        }
        // Clear appropriate far right cells
        for (int row = 0; row < 25; row++) {
            if (row == 5 || row == 7 || row == 13 || row == 18 || row == 20) {
                cells[row - 1][23].addWall(Cell.WallDirections.South);
                cells[row][22].addWall(Cell.WallDirections.West);
                cells[row + 1][23].addWall(Cell.WallDirections.North);
                cells[row][23] = null;
            } else if (row == 14) {
                cells[row][22].addWall(Cell.WallDirections.West);
                cells[row + 1][23].addWall(Cell.WallDirections.North);
                cells[row][23] = null;

            }
        }
    }

    /**
     * Makes the edges of the board
     * @param row current row
     * @param col current col
     * @param minRow far left row
     * @param minCol top col
     * @param maxRow far right row
     * @param maxCol bottom col
     */
    private void fixWall(int row, int col, int minRow, int minCol, int maxRow, int maxCol) {
        if (row == minRow) {
            cells[row][col].addWall(Cell.WallDirections.West);
        }
        if (row == maxRow) {
            cells[row][col].addWall(Cell.WallDirections.East);
        }
        if (col == minCol) {
            cells[row][col].addWall(Cell.WallDirections.North);
        }
        if (col == maxCol) {
            cells[row][col].addWall(Cell.WallDirections.South);
        }
    }

    /**
     *  toString method
     * @return visual representation of the board
     */
    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int row = 0; row < 25; row++) {
            for (int col = 0; col < 24; col++) {
                if (cells[row][col] != null) {
                    board.append(cells[row][col].toString());
                } else {
                    board.append('X');
                }
            }
            board.append('\n');
        }
        return board.toString();
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}