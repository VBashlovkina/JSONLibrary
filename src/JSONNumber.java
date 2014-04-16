public class JSONNumber
    implements
      JSONVal
{
  // Started saving number as a string for convenience.
  // Now I think it's a good idea, actually. Parse on get?
  // We probably ought to do them all as strings. Except arrays...
  String number;

  public JSONNumber (String number)
  {
    this.number = number;

  } // JSONNumber

}
