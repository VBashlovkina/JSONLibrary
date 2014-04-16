import java.util.ArrayList;

public class JSONObject
    extends
      JSONVal
{
  // Fields
  // Keys are only strings.
  ArrayList<String> keys;
  // A JSONVal can contain objects, numbers, strings, etc.
  ArrayList<JSONVal> vals;
  int i;

  // Constructors
  public JSONObject ()
  {
    keys = new ArrayList<String> ();
    vals = new ArrayList<JSONVal> ();
  } // JSONObject

  // Methods

  // Add a key to the key list
  public void
    addKey (String keyIn)
  {
    this.keys.add (keyIn);
  } // addKey (String)

  public void
    addVal (JSONVal val)
  {
    vals.add (val);
  } // addVal (JSONVal)

  public void
    printKeys ()
  {
    for (int i = 0; i < this.keys.size (); i++)
      {
        System.out.println (this.keys.get (i));
      } // for
  } // printKeys ()

  public String
    toString ()
  {
    // STUB
    return "";
  } // toString()
}