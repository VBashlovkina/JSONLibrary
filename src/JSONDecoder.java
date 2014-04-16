public class JSONDecoder
{
  String jsonString;
  int i = 0;

  // Constructor
  public JSONDecoder (String stringIn)
  {
    this.jsonString = stringIn;
    
    parseObject();
  } // JSONDecoder ()

  /*
   * DECIPHER TYPE
   */
  public JSONVal
    parseFromType ()
  {
    char ch = this.stringIn.charAt (this.i);

    /*
     * What are the basic types? According to json.org, they are:
     * 
     * string, number, object, array, true, false, null
     */
    // Check if it's a number
    switch (ch)
      {
      // If it's a number:
        case '0':
        case '1':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          return parseNumber ();
          // If it's a string
        case '"':
          return parseString ();
          // Array
        case '[':
          return parseArray ();
          // New object
        case '{':
          return parseObject ();
          // True
        case 't': // has to be true
          return parseTrue ();
          // False
        case 'f': // has to be false
          return parseFalse ();
          // Null
        case 'n': // has to be null
          return parseNull ();
      }

    return null;
  }

  public JSONObject
    parseObject ()
  {
    JSONObject object = new JSONObject();
    // make sure that the first char is a {
    if (this.jsonString.charAt(this.i++) != '{'
        || this.jsonString.charAt(this.i) == '}')
      {
        return null;
      }
    // We have moved i once.
    
    // Get the key
    String key = parseKey();
    object.addKey(key);
    
    // STUB
    return null;
  } // parseObject

  public JSONObject
    parseTrue ()
  {
    // STUB
    return null;
  }

  public JSONObject
    parseFalse ()
  {
    // STUB
    return null;

  }

  public JSONObject
    parseNull ()
  {
    // STUB
    return null;

  }

  public JSONString
    parseString ()
  {
    String output = "";

    return new JSONString (output);
  } // parseString ()

  public JSONArray
    parseArray ()
  {
    // STUB
    return null;
  }

  public JSONNumber
    parseNumber ()
  {
    // STUB
    return null;
  }
  
  /*
   * PARSE KEY
   */
  public String
    parseKey ()
  {
    String stringOut = "";
    char ch;
    while ((ch = this.jsonString.charAt (this.i++)) != '"')
      {
        if (ch == '\\')
          {
            if ((ch = this.jsonString.charAt (++this.i)) == '"')
              {
              stringOut += ch;
              } // if
          } // if
        else
          {
            // Probably a valid part of a string. Add it.
            stringOut += ch;
          } // else
      } // while not end of key
    return stringOut; // this is the key
  } // parseKey (String)
}
