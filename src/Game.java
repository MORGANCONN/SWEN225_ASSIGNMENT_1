/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.zip.InflaterInputStream;

// line 8 "model.ump"
// line 101 "model.ump"
public class Game {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    private Map<Cell.Room, Point> SpawnPoints = new HashMap<Cell.Room, Point>() {{
        put(Cell.Room.Kitchen, new Point(0, 1)); /// Kitchen
        put(Cell.Room.Ballroom, new Point(8, 2)); /// Ball Room
        put(Cell.Room.Conservatory, new Point(18, 1)); /// Conservatory
        put(Cell.Room.BillardRoom, new Point(18, 8)); /// Billiard Room
        put(Cell.Room.Library, new Point(18, 14)); /// Library
        put(Cell.Room.Study, new Point(21, 17)); /// Study
        put(Cell.Room.Hall, new Point(9, 18)); /// Hall
        put(Cell.Room.Lounge, new Point(0, 19)); /// Lounge
        put(Cell.Room.DiningRoom, new Point(0, 10)); /// Dining Room
    }};
    //
    private CharacterCard.Character suggChar;
    private WeaponCard.WeaponType suggWeap;
    private Cell.Room suggRoom;
    //Game Attributes
    private int numberOfPlayers;
    private ArrayList<Player> listOfPlayers = new ArrayList<>();

    //Game Associations
    private Board board;
    private Card[] envelope = new Card[3];
    //------------------------
    // CONSTRUCTOR
    //------------------------

    Game(int aNumberOfPlayers) {
        numberOfPlayers = aNumberOfPlayers;
        setUpPlayers();
        board = new Board(listOfPlayers);
        beginGame();

    }

  /**
   * starts the game and runs rounds continuously through each player
   * */
    private void beginGame() {
        while (true) {
            for (Player p : listOfPlayers) {
                round(p);
            }
        }
    }

  /**
   * simulates a round for each player, it rolls the dice the asks which direction
   * the player would like to move in and how far. Once the player is out of moves it will
   * give the option for the player to accuse or suggest if they are in a room
   * @param p current player in the round
   * */
    private void round(Player p) {
        System.out.println("/-------------------------------------------------------------/");
        System.out.printf("/--------------------Start Of Player %d's Turn (%s %s)--------------------/\n", listOfPlayers.indexOf(p),p.getPlayerName(),(p.getPlayerColors().get(p.getPlayerName())+ Character.toLowerCase(p.getPlayerName().toString().charAt(0))+boardColoration.RESET));
        System.out.println("/-------------------------------------------------------------/");
        System.out.println(toString());
        Random diceRoll = new Random();
        int diceRollNumber = diceRoll.nextInt((12 - 2) + 1) + 2;
        //Process Player Movement
        while (diceRollNumber > 0) {
            System.out.println("How many places do you want to move? You have " + diceRollNumber + " squares of movement left:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            int moveNumber = 0;
            try {
                s = br.readLine();
                int i = GameLauncher.validateInt(s);
                while (true) {
                    if (i > diceRollNumber || i < 1) {
                        System.out.println("Invalid Movement Number! You have " + diceRollNumber + " squares of movement left:");
                        i = GameLauncher.validateInt(br.readLine());
                    } else {
                        moveNumber = i;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("In what direction do you want to move(w,e,n,s)");
            br = new BufferedReader(new InputStreamReader(System.in));
            String move = null;
            try {
                move = br.readLine();
                while (true) {
                    if (!(move.equals("w") || move.equals("e") || move.equals("s") || move.equals("n"))) {
                        System.out.println("Invalid movement direction please use(w,e,n,s):");
                        move = br.readLine();
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(move(moveNumber, move, p)) {
                diceRollNumber = diceRollNumber - moveNumber;
            }
            System.out.println("Do you want to move again and forfit your %i squares of movement?(y/n)");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String decision = "";
            try {
                decision = input.readLine();
                while (true) {
                    if (!(decision.equals("y") || decision.equals("n"))) {
                        System.out.println("Invalid Input, Please Use (y/n)):");
                        decision = input.readLine();
                    } else {
                        if(decision.equals("y")){
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(toString());
        }

        //Process Suggestions And Accusation
        if (p.getCanMakeAccusations()) {
            if (board.getLocation(p.location).getCellRoom() != Cell.Room.Hallway) {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Do you want to make a suggestion?(y/n)");
                String decision = "";
                try {
                    decision = input.readLine();
                    while (true) {
                        if (!(decision.equals("y") || decision.equals("n"))) {
                            System.out.println("Invalid Input, Please Use (y/n)):");
                            decision = input.readLine();
                        } else {
                            if(decision.equals("y")){
                                suggest(p);
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                System.out.println("Do you want to make an accusation?(y/n)");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String decision = input.readLine();
                while (true) {
                    if (!(decision.equals("y") || decision.equals("n"))) {
                        System.out.println("Invalid decision please use(y/n):");
                        decision = input.readLine();
                    } else {
                        if(decision.equals("y")){
                            accuse(p);
                            break;
                        } else{
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("/---------------------------------------------------/");
        System.out.println("/--------------------End Of Turn--------------------/");
        System.out.println("/---------------------------------------------------/");
    }

  /**
   * creates the number of players playing the game
   * then calls distribute cards
   * */
    private void setUpPlayers() {
        int count = 1;
        for (CharacterCard.Character c : CharacterCard.Character.values()) {
            if (count > numberOfPlayers) {
                break;
            }
            Player p = new Player(c);
            listOfPlayers.add(p);
            count++;
        }
        distributeCards();
    }

    //------------------------
    // INTERFACE
    //------------------------

      /**
   * returns the board
   * @return the board
   * */
    public Board getBoard() {
        return board;
    }


      /**
   * creates three lists of cards and shuffles them. The first card
   * from each list is then put into the envelope. The remaining cards are
   * merged and shuffled together, then get distributed to all players in
   * the game
   * */
    private void distributeCards() {
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
        System.out.printf("Weapon:%s Room:%s Character:%s\n",envelope[0],envelope[1],envelope[2]);
        // Merge remaining cards, shuffle
        ArrayList<Card> mergedCards = new ArrayList<>();
        mergedCards.addAll(WeaponCards);
        mergedCards.addAll(RoomCards);
        mergedCards.addAll(CharacterCards);
        Collections.shuffle(mergedCards);
        // Distribute Cards
        while (!mergedCards.isEmpty()) {
            for (Player P : listOfPlayers) {
                if (!mergedCards.isEmpty())
                    P.addCard(mergedCards.remove(0));
            }
        }
    }

      /**
   * creates a list of all the rooms cards and returns it
   * @return list of room cards
   * */
    private ArrayList<RoomCard> generateRoomCards() {
        ArrayList<RoomCard> rooms = new ArrayList<>();
        for (RoomCard.Room R : RoomCard.Room.values()) {
            rooms.add(new RoomCard(R));
        }
        return rooms;
    }

    /**
   * creates a list of all the weapon cards and returns it
   * @return list of weapon cards
   * */
    private ArrayList<WeaponCard> generateWeaponCards() {
        ArrayList<WeaponCard> weapons = new ArrayList<>();
        for (WeaponCard.WeaponType W : WeaponCard.WeaponType.values()) {
            weapons.add(new WeaponCard(W));
        }
        return weapons;
    }

    /**
   * creates a list of all the character cards and returns it
   * @return list of character cards
   * */
    private ArrayList<CharacterCard> generateCharacterCard() {
        ArrayList<CharacterCard> characterCards = new ArrayList<>();
        for (CharacterCard.Character C : CharacterCard.Character.values()) {
            characterCards.add(new CharacterCard(C));
        }
        return characterCards;
    }
/**
   * allows the player to suggest who they think the murderer is
   * and what weapon they think is used. this can only be called if the
   * player is in a room. It will then ask the other players if they would like to
   * refute the suggestion
   * @param player player making the suggestion
   * */
    private void suggest(Player player) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        suggRoom = board.getLocation(player.getLocation()).getCellRoom();

        for (CharacterCard.Character c : CharacterCard.Character.values()) {
            System.out.println(c.ordinal() + ": " + c.toString());
        }
        System.out.println("Please enter the number of the suspected murderer: \n");
        int murderer = 0;
        try {
            murderer = GameLauncher.validateInt(input.readLine());
            while (true) {
                if (0 > murderer || murderer > 5) {
                    System.out.println("Invalid murderer number, Please enter a number between 0 and 5:");
                    murderer = GameLauncher.validateInt(input.readLine());
                } else {
                    suggChar = CharacterCard.Character.values()[murderer];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = new BufferedReader(new InputStreamReader(System.in));
        for (WeaponCard.WeaponType w : WeaponCard.WeaponType.values()) {
            System.out.println(w.ordinal() + ": " + w.toString());
        }
        System.out.println("Please enter the number of the suspected weapon: \n");
        int weapon = 0;
        try {
            weapon = GameLauncher.validateInt(input.readLine());
            while (true) {
                if (0 > weapon || weapon > 5) {
                    System.out.println("Invalid weapon number, Please enter a number between 0 and 5:");
                    weapon = GameLauncher.validateInt(input.readLine());
                } else {
                    suggWeap = WeaponCard.WeaponType.values()[weapon];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Player p : listOfPlayers) {
            Point startingP = SpawnPoints.get(suggRoom);
            if (p.getPlayerName() == suggChar) {
                for (int roomLocation = startingP.x; roomLocation <= startingP.x + 5; startingP.x++) {
                    if (board.getLocation(startingP).getItem() == null) {
                        board.getLocation(startingP).setItem(p);
                        board.getLocation(p.getLocation()).setItem(null);
                        p.setLocation(startingP);
                        break;
                    }
                }
            }
        }
        for(Weapon w : board.getWeapons()){
            Point startingP = SpawnPoints.get(suggRoom);
            if(w.getWeapon().toString().equals(suggWeap.toString())){
                for (int roomLocation = startingP.x; roomLocation <= startingP.x + 5; startingP.x++) {
                    if (board.getLocation(startingP).getItem() == null) {
                        board.getLocation(startingP).setItem(w);
                        board.getLocation(w.getLocation()).setItem(null);
                        w.setLocation(startingP);
                        break;
                    }
                }
            }
        }
//        System.out.println(player.getPlayerName().toString() + " decided to make a suggestion\n");
//        System.out.println("They suggest the murderer is: " + suggChar.toString() + "\n");
//        System.out.println("in the: " + suggRoom.toString() + "\n");
//        System.out.println("Using the: " + suggWeap.toString() + "\n");

        /// begin refute
        System.out.println("Players can now refute the suggestion");
        int i = listOfPlayers.indexOf(player)+1;
        try {
            while (i < listOfPlayers.size() - 1) {
                input = new BufferedReader(new InputStreamReader(System.in));
                System.out.println(listOfPlayers.get(i).toString() + " would you like to refute? (y/n)");
                String ans = input.readLine();
                while (true) {
                    if (!(ans.equals("y") || ans.equals("n"))) {
                        System.out.println("Invalid decision, Please enter y or n in lower case:");
                        ans = input.readLine();
                    } else {
                        if(ans.equals("y")) {
                            refute(listOfPlayers.get(i));
                            break;
                        } else {
                            break;
                        }
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        i = 0;
        try {
            while (i < listOfPlayers.indexOf(player)) {
                input = new BufferedReader(new InputStreamReader(System.in));
                System.out.println(listOfPlayers.get(i).toString() + " would you like to refute? (y/n)");
                String ans = input.readLine();
                while (true) {
                    if (!(ans.equals("y") || ans.equals("n"))) {
                        System.out.println("Invalid ans, Please enter y or n in lower case:");
                        ans = input.readLine();
                    } else {
                        refute(listOfPlayers.get(i));
                        break;
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
   * the player can make an accusation of who they think did the
   * murder, the weapon used, and the room it was in. Note: the player
   * does not need to be in a room to make an accusation. If the accusation is
   * correct then the player wins the game. If it isn't then the player can no
   * longer suggest or accuse for the rest of the game.
   * @param p player making the accusation
   * */
    private void accuse(Player p) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        CharacterCard.Character accuseCharacter = null;
        int ans = 0;
        try {
            System.out.println("/----------Characters----------/");
            for (int i = 0; i < CharacterCard.Character.values().length; i++) {
                System.out.printf("Index: %d Character:%s\n",i, CharacterCard.Character.values()[i].toString());
            }
            System.out.println("Please enter the index of the character you want to accuse");
            ans = GameLauncher.validateInt(input.readLine());
            while (true) {
                if (ans < 0 || ans > CharacterCard.Character.values().length-1) {
                    System.out.printf("Invalid index, please enter an index that is above 0 and below %d\n", CharacterCard.Character.values().length);
                    ans = GameLauncher.validateInt(input.readLine());
                } else {
                    accuseCharacter = CharacterCard.Character.values()[ans];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = new BufferedReader(new InputStreamReader(System.in));
        RoomCard.Room accuseRoom = null;
        try {
            System.out.println("/----------Rooms----------/");
            for (int i = 0; i < RoomCard.Room.values().length-1; i++) {
                System.out.printf("Index: %d Room:%s\n",i, RoomCard.Room.values()[i].toString());
            }
            System.out.println("Please enter the index of the room you want to use");
            ans = GameLauncher.validateInt(input.readLine());
            while (true) {
                if (ans < 0 || ans > RoomCard.Room.values().length) {
                    System.out.printf("Invalid index, please enter an index that is above 0 and below %d\n", RoomCard.Room.values().length);
                    ans = GameLauncher.validateInt(input.readLine());
                } else {
                    accuseRoom = RoomCard.Room.values()[ans];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = new BufferedReader(new InputStreamReader(System.in));
        WeaponCard.WeaponType accuseWeapon = null;
        try {
            System.out.println("/----------Weapons----------/");
            for (int i = 0; i < WeaponCard.WeaponType.values().length -1; i++) {
                System.out.printf("Index: %d Weapon:%s\n",i, WeaponCard.WeaponType.values()[i].toString());
            }            ans = GameLauncher.validateInt(input.readLine());
            while (true) {
                if (ans < 0 || ans >= p.getCards().size()) {
                    System.out.printf("Invalid index, please enter an index that is above 0 and below %d\n", WeaponCard.WeaponType.values().length-1);
                    ans = GameLauncher.validateInt(input.readLine());
                } else {
                    accuseWeapon = WeaponCard.WeaponType.values()[ans];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (accuseWeapon == ((WeaponCard) envelope[0]).getWeapon() && accuseRoom == ((RoomCard) envelope[1]).getRoomName() && accuseCharacter == ((CharacterCard) envelope[2]).getName()) {
            System.out.printf("Correct %s was the murderer, %s used a %s in the %s.\nThe authorities have been contacted, you were also arrested due to your tampering with evidence", accuseCharacter, accuseCharacter, accuseWeapon, accuseRoom);
            System.exit(0);
        } else {
            System.out.println("Incorrect, your reckless accusations have deeply hurt the rest of the players.\nYou cannot make a suggestion or an accusation again");
            p.setCanMakeAccusations(false);
        }
    }
/**
   * a player can refute a suggestion made by another player. If the refuting player
   * has any relevant cards they can use to refute, they will be displayed.
   * @param player refuting the suggestion from another player
   * */
    private void refute(Player player) {
        List<Card> hand = player.getCards();
        List<Card> refuteCards = new ArrayList<>();
        for (Card c : hand) {
            String card = c.toString();
            if (card.equals(suggChar.toString()) || card.equals(suggRoom.toString()) || card.equals(suggWeap.toString())) {
                refuteCards.add(c);
            }
        }
        if (refuteCards.isEmpty()) {
            System.out.println("You have no cards to refute!");
        } else {
            System.out.println("/--------------------Your Refute Cards--------------------/");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            int count = 0;
            for (Card c : refuteCards) {
                System.out.println(count++ + ": " + c.toString());
            }
            try {
                int refuteNum = GameLauncher.validateInt(input.readLine());
                while (true) {
                    if (refuteNum < 0 || refuteNum > count) {
                        System.out.println("Invalid number, Please enter a number between 0 and " + count + " ");
                        refuteNum = GameLauncher.validateInt(input.readLine());
                    } else {
                        Card refuteCard = refuteCards.get(refuteNum);
                        System.out.println(player + " has refuted the suggesting by playing" + refuteCard.toString());
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
   * moves a player x amount of steps in a direction while checking
   * to see if the moves are valid.
   * @param moveNum number of moves in a direction
   * @param direction direction the player wants to move
   * @param player player moving
   * */
    private boolean move(int moveNum, String direction, Player player) {
        Point movementDirection = convertMovementDirection(direction);
        Point currentTempPos = new Point(player.getLocation());
        boolean isValid = true;
        for (int i = 0; i < moveNum; i++) {
            currentTempPos.x = movementDirection.x + currentTempPos.x;
            currentTempPos.y = movementDirection.y + currentTempPos.y;
            if (board.getLocation(currentTempPos) == null) {
                return false;
            }
            if (currentTempPos.x < 0 || currentTempPos.y < 0 || currentTempPos.x>23||currentTempPos.y>24) {
                return false;
            }
            if (!board.getLocation(currentTempPos).getCellRoom().equals(board.getLocation(player.getLocation()).getCellRoom())) {
                if(!board.getLocation(currentTempPos).isDoor()){
                    return false;
                }
            }
            if (board.getLocation(currentTempPos).getItem() != null) {
                return false;
            }
        }
        if (isValid) {
            board.getLocation(currentTempPos).setItem(player);
            board.getLocation(player.getLocation()).setItem(null);
            player.setLocation(currentTempPos);
            return true;
        }
        return false;
    }
    /**
   * convets the direction as a string to a point
   * which moves in that direction.
   * @param direction the direction as a string
   * @return a point moving in that direction
   * */
    private Point convertMovementDirection(String direction) {
        Point toReturn = null;
        switch (direction) {
            case "w":
                toReturn = new Point(-1, 0);
                break;
            case "e":
                toReturn = new Point(1, 0);
                break;
            case "n":
                toReturn = new Point(0, -1);
                break;
            case "s":
                toReturn = new Point(0, 1);
                break;

        }
        return toReturn;
    }

    /**
   * finds the inverse wall direction from a given string of the direction
   * @param direction String representing a direction
   * @return the inverse direction of the direction inputted
   * */
    private Cell.WallDirections getInverseWallDirection(String direction) {
        Cell.WallDirections toReturn = null;
        switch (direction) {
            case "w":
                toReturn = Cell.WallDirections.East;
                break;
            case "e":
                toReturn = Cell.WallDirections.West;
                break;
            case "n":
                toReturn = toReturn = Cell.WallDirections.South;
                break;
            case "s":
                toReturn = toReturn = Cell.WallDirections.North;
                break;
        }
        return toReturn;
    }

    /**
   * returns the boards to string method
   * @return returns the boards to string method
   * */
    public String toString() {
        return board.toString();
    }
}