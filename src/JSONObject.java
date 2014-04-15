import java.util.ArrayList;

public class JSONObject
{
  // Fields
  ArrayList<String> keys;
  ArrayList<JSONObject> values;

  // Constructors
  public JSONObject(String jsonIn)
  {
    keys = new ArrayList<String>();
    values = new ArrayList<JSONObject>();
  } // JSONObject

  // Methods

  public JSONObject parse(String json)
  {
    // String key = "..the first few letters";
    // String value = "..whatever comes after the :";
    // if (value starts with "{")
    // value = parse(value)
    // else
    // just add it to values

    int stringLen = json.length();
    boolean isKey = true;
    boolean keyStart = false;
    char ch;
    String keyTemp = "";
    String valueTemp = "";
    char[] stopChars = { '\n', '\t' };

    for (int i = 0; i < stringLen; i++)
      {
        ch = json.charAt(i);

        if (isKey)
          {

            if (keyStart)
              {
                if (ch != '"')
                  {
                    // The key has started, and isn't '"'
                    keyTemp += ch;
                  } // if
                else if (keyStart)
                  {
                    // '"' encountered, and keyStart is true
                    keyStart = false;
                  } // else if
                else
                  {
                    // '"' encountered, and keyStart is false
                    keyStart = true;
                  } // else
              } // if
            else if (ch == '"')
              {
                keyStart = true;
              }
            // Looking for ":" from now
            else if (ch == ':')
              {
                // Next comes value
                isKey = false;
                // Push key to array
                this.keys.add(keyTemp);
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
                  this.values.add(parse(json.substring(i)));
                  break;
                case ',':
                  isKey = true;
                  break;
                case '}':
                  // Push to the value array
                  this.toString();
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

  public boolean inCharArray(char[] haystack, char needle)
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
  public JSONObject jsonEncode(String json)
  {

    return null;
  } //

  public JSONObject get(String key)
  {
    // If name is null, just return the string, or number
    // STUB
    return null;
  } // get (String)

  public String toString()
  {
    // STUB
    int jsonLen = this.keys.size();

    for (int i = 0; i < jsonLen; i++)
      {
        System.out.println("Key is " + this.keys.get(i));
      } // for

    return "";
  } // toString()
}
