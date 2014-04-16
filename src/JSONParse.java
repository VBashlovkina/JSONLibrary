public class JSONParse
{
  String type;

  /*
   * DECIPHER TYPE
   */
  public JSONVal
    decipherType (String stringIn)
  {
    char ch = stringIn.charAt (i);

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
    // STUB
    return null;
  }

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
    parseKey (String stringIn)
  {
    String stringOut = "";
    int stringLen = stringIn.length ();
    char ch;
    i = 0;
    while ((ch = stringIn.charAt (i)) != '"')
      {
        if (ch == '\\')
          {
            // then skip the next
            i++;
          } // if
        else
          {
            stringOut += ch;
          }
        i++; // move on to next char
      } // while not end of key

    return stringOut; // this is the key
  } // parseKey (String)
}
