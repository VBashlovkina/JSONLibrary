import java.util.ArrayList;

public class JSONArray
    implements
      JSONVal
{
  // Make an arraylist of JSONVals

  ArrayList<JSONVal> array;

  public JSONArray (String arrayIn)
  {
    this.array = new ArrayList<JSONVal> ();
    addItems (arrayIn);
  } // JSONArray(String)

  // Take the string and parse it into a real array
  public void
    addItems (String arrayIn)
  {

  } // addItems(String)

  @Override
  public Object
    get ()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
