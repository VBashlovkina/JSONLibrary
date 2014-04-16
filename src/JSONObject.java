import java.util.ArrayList;

public class JSONObject
    implements
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
    printKeys ();
    System.out.println ("Key added");
  } // addKey (String)

  public void
    addVal (JSONVal val)
  {
    vals.add (val);
  } // addVal (JSONVal)

  public void
    printKeys ()
  {
    System.out.println ("There are " + this.keys.size () + " keys");

    for (int i = 0; i < this.keys.size (); i++)
      {
        System.out.println (this.keys.get (i));
      } // for
  } // printKeys ()

  /**
   * returns the value associated to the given key. Eff. is O(n), n = number of
   * keys
   * 
   * @param key
   * @return
   */
  public Object
    get (String keyIn)
  {
    for (int i = 0; i < this.keys.size (); i++)
      {
        if (this.keys.get (i).compareTo (keyIn) == 0)
          {
            System.out.println ("key found at " + i);
            System.out.println (this.vals.get (i));
            return this.vals.get (i);
          } // if
      } // for
    return null; // no such key was found.
  } // get()

  public String
    toString ()
  {
    // STUB
    return "";
  } // toString()

  @Override
  public Object
    get ()
  {
    return "[Object]";
  }
}