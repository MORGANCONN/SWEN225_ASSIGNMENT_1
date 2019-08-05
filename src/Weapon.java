/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


// line 94 "model.ump"
// line 155 "model.ump"
public class Weapon extends MoveableObject
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  public enum Weapons{
    Candlestick,Dagger,LeadPipe,Revolver,Rope,Spanner
  }

  private Weapons weapon;
  //------------------------
  // CONSTRUCTOR
  //------------------------


  public Weapon(Weapons weapon)
  {
    this.weapon = weapon;
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
     *  returns the weapon
     * @return the weapon
     */
  public Weapons getWeapon() {
    return weapon;
  }

  /**
     *  sets the weapon
     * @param weapon the enum of weapon
     */
  public void setWeapon(Weapons weapon) {
    this.weapon = weapon;
  }

/**
     *  toString method
     * @return lower case cyan colored indicator of which weapon it is
     */
  @Override
  public String toString() {
    return boardColoration.CYAN+Character.toString(weapon.toString().charAt(0)).toLowerCase()+ boardColoration.RESET;
  }
}