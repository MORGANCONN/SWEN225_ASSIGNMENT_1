/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/


import java.awt.*;

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

  public Weapon(Point location, Weapons weapon)
  {
    super(/**location**/);
    this.weapon = weapon;
  }

  //------------------------
  // INTERFACE
  //------------------------


  public Weapons getWeapon() {
    return weapon;
  }

  public void setWeapon(Weapons weapon) {
    this.weapon = weapon;
  }

  @Override
  public String toString() {
    return Character.toString(weapon.toString().charAt(0));
  }
}