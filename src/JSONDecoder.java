public class JSONDecoder
{
  String jsonString;
  int i;

  // Constructor
  public JSONDecoder (String stringIn)
  {
    this.i = 0;
    this.jsonString = stringIn;

    parseObject ();
  } // JSONDecoder ()

  // Increments i before returning
  public char
    nextChar ()
  {
    return this.jsonString.charAt (--this.i);
  } // nextChar

  // Post: increments i after returning
  public char
    curCharInc ()
  {
    return this.jsonString.charAt (this.i++);
  }

  public char
    currentChar ()
  {
    return this.jsonString.charAt (this.i);
  } // currentChar

  /*
   * DECIPHER TYPE
   */
  public JSONVal
    parseVal ()
  {
    // Values are always preceeded by a colon.
    if (curCharInc () != ':')
      {
        // Throw error.
        return null;
      } // if

    char ch = currentChar ();

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

    // make sure that the first char is a {
    if (curCharInc () != '{' || currentChar () == '}')
      {
        return null;
      }
    // We have moved i once.
    JSONObject object = new JSONObject ();

    /*
     * JSON objects can have multiple key-value pairs per object. They are
     * seperated by commas. Because of our case at the top, we know it's not
     * null, so there must be at least one key-value pair. Moreover, we cannot
     * check for a common before we parse the key-value pair, and therefore I
     * will use a do-while loop (really!)
     */
    do
      {
        // Add the key
        object.addKey (parseKey ());
        // Add the value
        object.addVal (parseVal ()); // this will recurse if objects are inside
      }
    while (curCharInc () == ',');

    return object;
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

  } // parseFalse()

  public JSONObject
    parseNull ()
  {
    // STUB
    return null;

  } // parseNull()

  public JSONString
    parseString ()
  {
    // The next bit is a string
    return new JSONString (parseStringInner ());
  } // parseString ()

  public JSONArray
    parseArray ()
  {
    // STUB
    return null;
  } // parseArray()

  public JSONNumber
    parseNumber ()
  {
    // STUB
    return null;
  } // parseNumber()

  /*
   * PARSE STRINGS WITHIN QUOTES (KEY AND JSONStrings)
   */
  public String
    parseStringInner ()
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
    return stringOut;
  } // parseStringInner()

  /*
   * PARSE KEY Must only be called when the next peice is definitely a key
   */
  public String
    parseKey ()
  {
    // Get the key
    if (curCharInc () != '"')
      {
        // Throw an error. In JSON, the key has to be a string.
      } // if
    return parseStringInner (); // this is the key
  } // parseKey (String)
}
