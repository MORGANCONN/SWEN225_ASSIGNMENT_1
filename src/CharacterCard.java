/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4584.3d417815a modeling language!*/



// line 65 "model.ump"
// line 135 "model.ump"
public class CharacterCard extends Card
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Character { MissScarlett, ColonelMustard, MrsWhite, MrGreen, MrsPeacock, ProfessorPlum }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CharacterCard Attributes
  private Character name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CharacterCard(Character aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(Character aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public Character getName()
  {
    return name;
  }

  // line 70 "model.ump"
   public String toString(){
    return name.toString();
  }

}