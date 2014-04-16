import java.util.ArrayList;

public class JSONObject extends JSONVal
{
  // Fields
  ArrayList<String> keys;
  ArrayList<JSONObject> values;
  int i;

  // Constructors
  public JSONObject ()
  {
    keys = new ArrayList<String> ();
    values = new ArrayList<JSONObject> ();
  } // JSONObject

  // Methods

  public JSONObject
    parse (String json)
  {
    JSONObject jsonObject;
    int i = 0;
    if (json.charAt(i) == '{')
      {
        // Looks valid
      jsonObject  = new JSONObject();
      i++;
       } // if
    else
      {
        // Throw an error?
       return null; 
      }
    
    // First of all, what if it's null?
    if (json.charAt (i) == '}')
      {
      return jsonObject;
      } // if
      
//    String key;
//    do
//      {
//        key = parseKey(json);
//        
//      }
//    while (json.charAt (i) == ',')
//      {
//        
//      }
    
    // String key = "..the first few letters";
    // String value = "..whatever comes after the :";
    // if (value starts with "{")
    // value = parse(value)
    // else
    // just add it to values

    int stringLen = json.length ();

    // Flags to track where we are in the structure
    boolean isKey = true;
    boolean keyStart = false;
    char ch;

    // Temporary values for the key
    String keyTemp = "";
    String valueTemp = "";

    // Are there characters that we ought to skip?
    char[] stopChars = { '\n', '\t' };
    
    // First, get the key
    String key;
    do
      {
       key = parseKey(json);  
      }
    
    for (int i = 0; i < stringLen; i++)
      {
        ch = json.charAt (i);

        if (isKey)
          {
            
              } // if
            else if (ch == '"')
              {
                keyStart = true;
              } // else if
            // Looking for ":" from now
            else if (ch == ':')
              {
                // Next comes value
                isKey = false;
                // Push key to array
                System.out.println ("k, " + keyTemp);
                this.keys.add (keyTemp);
                keyTemp = "";
                // Key has no '"', but could have spaces and nwln chars
              } // else
          } // if
        else
          {
            switch (ch)
              {
                case '{':
                  // recurse
                  // push the recursion to value array
                  this.values.add (parse (json.substring (i)));
                  break;
                case ',':
                  isKey = true;
                  break;
                case '}':
                  // Push to the value array
                  this.toString ();
                  return this;
                default:
                  valueTemp += ch;
                  // return ch;
                  break;
              } // switch
          } // for
      }
    // end
    return null;
  }

  public boolean
    inCharArray (char[] haystack, char needle)
  {
    int len = haystack.length;
    for (int i = 0; i < len; i++)
      {
        if (haystack[i] == needle)
          {
            return true;
          }
      } // for
    return false;
  } // inCharArray

  /**
   * Takes in a string, and returns JSON object that it represents.
   * 
   * @param json
   * @return JSONObject
   */
  public JSONObject
    jsonEncode (String json)
  {

    return null;
  }

  public JSONObject
    get (String key)
  {
    // If name is null, just return the string, or number
    // STUB
    return null;
  } // get (String)

  public String
    toString ()
  {
    // STUB
    int jsonLen = this.keys.size ();

    for (int i = 0; i < jsonLen; i++)
      {
        System.out.println ("Key is " + this.keys.get (i));
      } // for

    return "";
  } // toString()
}