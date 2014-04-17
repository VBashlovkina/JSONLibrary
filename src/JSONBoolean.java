public class JSONBoolean
    implements
      JSONVal
{

  boolean isTrue = false;

  public JSONBoolean (char specialIn)
  {
    if (specialIn == 't')
      {
        isTrue = true;
      } // if
  } // JSONSpecial

  public Object
    get ()
  {
    return this.isTrue;
  }

  public String
    toString ()
  {
    return String.valueOf (this.isTrue);
  }

} // class JSONSpecial
