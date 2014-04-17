import java.math.BigDecimal;
import java.math.BigInteger;

public class JSONNumber
    implements
      JSONVal
{
  // Started saving number as a string for convenience.
  // Now I think it's a good idea, actually. Parse on get?
  // We probably ought to do them all as strings. Except arrays...
  String number;
  boolean isDecimal;

  public JSONNumber (String number, boolean decimal)
  {
    this.number = number;
    this.isDecimal = decimal;

  } // JSONNumber

  public Object
    get ()
  {
    if (this.isDecimal)
      {
        return new BigDecimal (this.number);
      } // if

    return new BigInteger (this.number);
  } // get ()

  public String
    toString ()
  {
    return number;
  }//toString()
  
}//JSONNumber class
