/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 55 "model.ump"
// line 130 "model.ump"
public class WeaponCard extends Card
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WeaponType { Candlestick, Dagger, LeadPipe, Revolver, Rope, Spanner }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeaponCard Attributes
  private WeaponType weapon;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeaponCard(WeaponType aWeapon)
  {
    weapon = aWeapon;
  }

  //------------------------
  // INTERFACE
  //------------------------

      /**
     *  sets weapon
     * @aWeapon - Type of weapon
     */
  public boolean setWeapon(WeaponType aWeapon)
  {
    boolean wasSet = false;
    weapon = aWeapon;
    wasSet = true;
    return wasSet;
  }

    /**
     *  returns weapon
     * @return weaponType
     */
  public WeaponType getWeapon()
  {
    return weapon;
  }

  /**
     *  returns the weapons to string
     * @return returns the weapon enum string
     */
   public String toString(){
    return weapon.toString();
  }

}