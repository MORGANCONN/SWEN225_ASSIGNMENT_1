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

  public WeaponCard(Player aPlayer, WeaponType aWeapon)
  {
    super(aPlayer);
    weapon = aWeapon;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeapon(WeaponType aWeapon)
  {
    boolean wasSet = false;
    weapon = aWeapon;
    wasSet = true;
    return wasSet;
  }

  public WeaponType getWeapon()
  {
    return weapon;
  }

  public void delete()
  {
    super.delete();
  }

  // line 60 "model.ump"
   public String toString(){
    // Return and Enum
  }

}