public class JSONDecoder
{
  String jsonString;
  int i;

  // Constructor
  public JSONDecoder(String stringIn)
  {
    System.out.println ("Init!");
    this.i = 0;
    this.jsonString = stringIn;
    if (curCharInc() != '{')
      {
        System.out.println("Malformed error?");
      }

  } // JSONDecoder ()

  public JSONVal jsonDecode() throws Exception
  {
    return parseObject();
  }

  // Increments i before returning
  public char nextChar()
  {
    return this.jsonString.charAt(--this.i);
  } // nextChar

  // Post: increments i after returning
  public char curCharInc()
  {
    return this.jsonString.charAt(this.i++);
  }

  public char currentChar()
  {
    return this.jsonString.charAt(this.i);
  } // currentChar

  public void incChar()
  {
    this.i++;
  }

  /*
   * DECIPHER TYPE
   */
  public JSONVal parseVal() throws Exception
  {
    // Values are always preceeded by a colon.
    System.out.println("Parsing val, ch is " + currentChar());
    if (curCharInc() != ':')
      {
        // Throw error.
        return null;
      } // if
    System.out.println("Made it through");
    char ch = curCharInc();
    System.out.println("Adding value, ch is " + ch);

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


        case '-': // for negative numbers
          return parseNumber ();

          // If it's a string
        case '"':
          System.out.println("About to parse String");
          return parseString();
          // Array
        case '[':
          return parseArray();
          // New object
        case '{':
          System.out.println("About to parse Object, current " + currentChar());
          return parseObject();
          // True
        case 't': // has to be true
        case 'f': // has to be false 
          return parseBool ();
          // Null
        case 'n': // has to be null
          return parseNull();


        default:
          // throw some exception

      }

    return null;
  }

  public JSONObject parseObject()
    throws Exception
  {

    // make sure that the first char is a {
    System.out.println("Before caught, c " + currentChar());
    if (currentChar() == '}')
      {
        System.out.println("Caught null!, current " + currentChar());
        return null;
      }
    // We have moved i once.
    JSONObject object = new JSONObject();

    /*
     * JSON objects can have multiple key-value pairs per object. They are
     * seperated by commas. Because of our case at the top, we know it's not
     * null, so there must be at least one key-value pair. Moreover, we cannot
     * check for a common before we parse the key-value pair, and therefore I
     * will use a do-while loop (really!)
     */
    char ch;
    do
      {
        // Add the key
        String k = parseKey();
        System.out.println("Adding key " + k);
        object.addKey(k);
        // Add the value
        ch = currentChar();
        System.out.println("Current char is " + ch);
        JSONVal v = parseVal();
        object.addVal(v); // this will recurse if objects are inside
        ch = curCharInc();
        System.out.println("Added value, hash: " + v + ", next val: " + ch);

      }
    while (ch == ',');

    return object;
  } // parseObject


  public JSONObject parseTrue()
  {
    // STUB
    return null;
  }

  public JSONObject parseFalse()
  {
    // STUB
    return null;

  } // parseFalse()

  public JSONObject parseNull()
  {
    //STUB?
    return null;
  }

  public JSONBoolean parseBool ()
  {
    return new JSONBoolean (curCharInc ());
  } // parseSpecial

  public JSONString parseString()
  {
    // The next bit is a string
    return new JSONString(parseStringInner());
  } // parseString ()

  public JSONArray parseArray()
  {
    // STUB
    return null;
  } // parseArray()

  public JSONNumber parseNumber()
  {
    // STUB
    return null;
  } // parseNumber()

  /*
   * PARSE STRINGS WITHIN QUOTES (KEY AND JSONStrings)
   */
  public String parseStringInner()
  {
    String stringOut = "";
    char ch;
    int begIndex = this.i;
    int endIndex = this.i;

    while ((ch = curCharInc()) != '"')
      {
        System.out.println("Parsing String, ch is " + ch);
        if (ch == '\\')
          {// this if statement makes sure we read the quotes within the string
            if ((ch = curCharInc()) == '"')
              {
                endIndex++;

              } // if
          } // if
        else
          {
            // Probably a valid part of a string. Add it.
            endIndex++;
          } // else
      } // while not end of key
    stringOut = this.jsonString.substring(begIndex, endIndex);
    System.out.println("Returning string  " + stringOut);
    return stringOut;
  } // parseStringInner()

  /*
   * PARSE KEY Must only be called when the next peice is definitely a key
   */
  public String parseKey()
    throws Exception
  {
    // Get the key
    char ch = curCharInc();
    System.out.println("Parsing, ch is" + ch);
    if (ch != '"')
      {
        throw new Exception();
      } // if
    return parseStringInner(); // this is the key
  } // parseKey (String)
}
