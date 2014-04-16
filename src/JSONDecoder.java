public class JSONDecoder
{
  String jsonString;
  int i;

  // Constructor
  public JSONDecoder (String stringIn)
  {
    System.out.println ("Init!");
    this.i = 0;
    this.jsonString = stringIn;
  } // JSONDecoder ()

  public JSONVal
    jsonDecode ()
  {
    return parseObject ();
  } // jsonDecode()

  // Increments i before returning
  public char
    nextChar ()
  {
    return this.jsonString.charAt (++this.i);
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

  public int
    incChar ()
  {
    return ++this.i;
  }

  /*
   * DECIPHER TYPE
   */
  public JSONVal
    parseVal ()
  {
    // Values are always preceeded by a colon.

    System.out.println ("Parsing val, ch is " + currentChar ());

    incChar ();
    trim (); // after '"'
    char ch;
    System.out.println ();
    incChar (); // move past ':'
    trim (); // after ':'
    ch = currentChar ();
    System.out.println ("Made it through");

    System.out.println ("Adding value, ch is " + ch);

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
          System.out.println ("About to parse String");
          return parseString ();
          // Array
        case '[':
          return parseArray ();
          // New object
        case '{':
          System.out.println ("About to parse Object, current "
                              + currentChar ());
          return parseObject ();
          // True
        case 't': // has to be true
        case 'f': // has to be false
          return parseBool ();
        case 'n': // has to be null
          return null;
        default:
          // throw some exception
      }

    return null;
  }

  public JSONObject
    parseObject ()
  {

    // make sure that the first char is a {
    System.out.println ("Before caught, c " + currentChar ());
    // if (nextChar () == '}')
    // {
    // System.out.println ("Caught null!, current " + currentChar ());
    // return null;
    // }
    // We have moved i once.
    JSONObject object = new JSONObject ();

    /*
     * JSON objects can have multiple key-value pairs per object. They are
     * seperated by commas. Because of our case at the top, we know it's not
     * null, so there must be at least one key-value pair. Moreover, we cannot
     * check for a common before we parse the key-value pair, and therefore I
     * will use a do-while loop (really!)
     */
    int numKeys = 0;
    char ch;
    do
      {
        System.out.println ("Starting a new pair, ch is " + currentChar ());
        // Add the key, must be a string
        if (currentChar () == ',' || currentChar () == '{')
          {
            incChar (); // move past ',' or '{'
            trim ();
          }
        else if (currentChar () == '}')
          {
            return null;
          }

        if (currentChar () == '"')
          {
            String k = parseKey ();
            System.out.println ("Adding key " + k);
            object.addKey (k);
          }

        trim (); // after '"'
        numKeys++;
        System.out.println ("Ought to be " + numKeys + " keys");
        // Add the value
        // ch = nextChar ();
        // System.out.println ("Current char is " + ch);
        JSONVal v = parseVal ();
        object.addVal (v); // this will recurse if objects are inside
        ch = currentChar (); // reset char to current
        System.out.println ("Added value, hash: " + v + ", next val: " + ch);
        incChar ();
        trim ();
      } // do
    while (currentChar () == ',');

    return object;
  } // parseObject

  /**
   * While there is whitespace ahead, increment char
   */
  public void
    trim ()
  {
    trimSpace ();
    trimLines ();
    trimSpace ();
  } // trim ()

  public void
    trimSpace ()
  {
    while (currentChar () == ' ')
      {
        System.out.println ("Removing whitespace");
        incChar ();
      } // while
  }

  public void
    trimLines ()
  {
    while (currentChar () == '\n')
      {
        System.out.println ("Found newline");
        incChar ();
      } // while
  }

  public JSONBoolean
    parseBool ()
  {
    return new JSONBoolean (curCharInc ());
  } // parseSpecial

  public JSONString
    parseString ()
  {
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
    // As with string, go until reach a '"' and then get substring
    int startingIndex = this.i;
    char ch;
    boolean decimal = false;
    while ((ch = nextChar ()) != '"')
      {
        if (ch == '.')
          {
            decimal = true; // contains a ., therefore is decimal
          } // if
      } // while

    return new JSONNumber (this.jsonString.substring (startingIndex, this.i),
                           decimal);
  } // parseNumber()

  /*
   * PARSE STRINGS WITHIN QUOTES (KEY AND JSONStrings)
   */
  public String
    parseStringInner ()
  {
    String stringOut = "";
    int startingIndex = incChar (); // skip the first character
    while (nextChar () != '"')
      ; // No operation.
    stringOut = this.jsonString.substring (startingIndex, this.i);

    System.out.println ("Returing string  " + stringOut);
    return stringOut;
  } // parseStringInner()

  /*
   * PARSE KEY Must only be called when the next peice is definitely a key
   */
  public String
    parseKey ()
  {
    // Get the key
    return parseStringInner (); // this is the key
  } // parseKey (String)
}
