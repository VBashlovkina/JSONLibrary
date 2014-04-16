public class JSONBoolean implements JSONVal
{

  boolean isTrue = false;

  public JSONBoolean (char specialIn)
  {
    if (specialIn == 't')
      {
        isTrue = true;
      } // if
  } // JSONSpecial

  public boolean
    get ()
  {
    return isTrue;
  }

} // class JSONSpecial
