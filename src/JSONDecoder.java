/**
 * A class that decodes strings of JSON code and translates it to Java
 * 
 */

// I'm confused about trim()

public class JSONDecoder
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  // The string to decode

  String jsonString;

  // Current index in the string

  int i;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  public JSONDecoder(String stringIn)
  {
    System.out.println("Init!");
    this.i = 0;
    this.jsonString = stringIn;
  } // JSONDecoder (String)

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Translate a JSON string to Java
   * 
   * @pre this.jsonString is a valid piece of JSON code
   * @return JSONVal the object represented by the string
   * @throws Exception 
   */
  public JSONVal jsonDecode() throws Exception
  {
    return parseObject();
  } // jsonDecode()

  /**
   * Translate a string of JSON code into Java
   * 
   * @throws Exception
   * @pre this.jsonString is a valid piece of JSON code
   * @post the object represented by the string is returned
   * @return null if the string is a JSON constant null
   * @return JSONBoolean true or false if the string is a JSON constant true or false
   * @return JSONString if JSON string
   * @return JSONNumber if JSON number
   * @return JSONArray if JSON array
   * @return JSONObject if JSON object 
   */
  public JSONVal parseVal()
    throws Exception
  {
    // Values are always preceded by a colon.

    System.out.println("Parsing val, ch is " + currentChar());

    incChar();
    trim(); // after '"'
    char ch;
    System.out.println();
    incChar(); // move past ':'
    trim(); // after ':'
    ch = currentChar();
    System.out.println("Made it through");

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
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case '-': // for negative numbers
          System.out.println("Parsing a number! xxxxxxxxxxxxxxxxxxxxx");
          return parseNumber();
          // If it's a string
        case '"':
          System.out.println("About to parse String");
          return parseString();
          // Array
        case '[':
          System.out.println("Parsing array!");
          return parseArray();
          // New object
        case '{':
          System.out.println("About to parse Object, current " + currentChar());
          return parseObject();
          // True or false
        case 't': // has to be true
        case 'f': // has to be false
          return parseBool();
        case 'n': // has to be null
          return null;
        default:
          throw new Exception("Incorrect syntax");
      }// switch
  }// parseVal()

  /**
   * Parse a string of JSON code that represents a JSON object
   * 
   * @pre the string represents a valid JSON object
   * @return a JSONobject represented by the string
   * @throws Exception
   *           if there are syntax errors
   */
  public JSONObject parseObject()
    throws Exception
  {

    
    JSONObject object = new JSONObject();

    /*
     * JSON objects can have multiple key-value pairs per object. They are
     * separated by commas. We cannot
     * check for a comma before we parse the key-value pair, and therefore we
     * will use a do-while loop.
     */
    
    // Number of keys for the JSONObject to be returned
    int numKeys = 0;
    
    char ch;
    do
      {
        System.out.println("Starting a new pair, ch is " + currentChar());
        // Add the key, must be a string
        if (currentChar() == ',' || currentChar() == '{')
          {
            incChar(); // move past ',' or '{'
            trim();
          }//if comma or opening brace
        else if (currentChar() == '}')
          {
            return null;
          }// if closing brace, must be a null object

        if (currentChar() == '"')
          {
            String k = parseKey();
            System.out.println("Adding key " + k);
            object.addKey(k);
          }// if quote

        trim(); // after '"'
        numKeys++;
        System.out.println("Ought to be " + numKeys + " keys");
        // Add the value
        // ch = nextChar ();
        // System.out.println ("Current char is " + ch);
        JSONVal v = parseVal();
        object.addVal(v); // this will recurse if objects are inside
        ch = currentChar(); // reset char to current
        System.out.println("Added value, hash: " + v + ", next val: " + ch);

        if (ch != '}' && ch != ',')
          {
            incChar();
          } //if not a closing brace or a comma
        trim();
      } // do
    while (currentChar() == ',');

    return object;
  } // parseObject()

  /**
   * Parse a JSON boolean
   * @pre the string represents a valid JSON constant true or false
   * @return JSONBoolean
   */
  public JSONBoolean parseBool()
  {
    char ch = currentChar();
    while (currentChar() != '}' && nextChar() != ',')
      // scan past the boolean
      ; // no Operation
    return new JSONBoolean(ch);
  } // parseBool()
  
  /**
   * Parse a JSON string
   * @pre the string represents a valid JSON string
   * @return JSONString
   */
  public JSONString parseString()
  {
    return new JSONString(parseStringInner());
  } // parseString ()
  
  /**
   * Parse a JSON array
   * @pre the string represents a valid JSON array
   * @return JSONArray
   */
  public JSONArray parseArray()
  {
    System.out.println("Doing array, cur: " + currentChar());
    int startingIndex = this.i;
    while (nextChar() != ']')
      // move past array
      ; // no operation

    return new JSONArray(this.jsonString.substring(startingIndex, this.i));
  } // parseArray()
  /**
   * Parse a JSON number
   * @pre the string represents a valid JSON number
   * @return JSONNumber
   */
  public JSONNumber parseNumber()
  {
    System.out.println("Number!");
    // As with string, go until reach a '"' and then get substring
    int startingIndex = this.i;
    char ch;
    boolean decimal = false;
    while ((ch = currentChar()) != ',')
      {
        if (ch == '.')
          {
            decimal = true; // contains a ., therefore is decimal
          } // if
        ch = nextChar();
      } // while
    System.out.println("Parsing number (" + decimal + "), "
                       + this.jsonString.substring(startingIndex, this.i));
    return new JSONNumber(this.jsonString.substring(startingIndex, this.i),
                          decimal);
  } // parseNumber()

  // +---------+---------------------------------------------------------
  // | Helpers |
  // +---------+
  /**
   * Get the next character
   * 
   * @pre ++this.i < this.jsonString.length()
   * @post this.i is incremented
   * @return the next character
   */

  public char nextChar()
  {
    return this.jsonString.charAt(++this.i);
  } // nextChar()

  /**
   * Get the current char and advance the index
   * 
   * @pre none
   * @post this.i is incremented
   * @return the current char
   */
  public char curCharInc()
  {
    return this.jsonString.charAt(this.i++);
  }// curCharInc()

  /**
   * Get the current char
   * 
   * @pre none
   * @post none
   * @return the current char
   */
  public char currentChar()
  {
    return this.jsonString.charAt(this.i);
  } // currentChar()

  /**
   * Increment and the return the index
   * 
   * @pre none
   * @post this.i is incremented
   * @return the incremented index
   */
  public int incChar()
  {
    return ++this.i;
  } // incChar()

  /**
   * Trim whitespace from a string
   * @pre none
   * @post the current index is incremented past all whitespace
   */
  public void trim()
  {
    trimSpace();
    trimLines();
    trimSpace();
  } // trim ()
/**
 * Trim spaces
 * @pre none
 * @post the current index is incremented past all spaces
 */
  public void trimSpace()
  {
    while (currentChar() == ' ')
      {
        System.out.println("Removing whitespace");
        incChar();
      } // while
  }//trimSpace()
  /**
   * Trim newlines
   * @pre none
   * @post the current index is incremented past all newlines
   */
  public void trimLines()
  {
    while (currentChar() == '\n')
      {
        System.out.println("Found newline");
        incChar();
      } // while
  }//trimLines()

  /**
   * Parse strings that are JSON strings or keys
   * @pre the first char is not a quote
   * @post the string is returned
   */
  public String parseStringInner()
  {
    String stringOut = "";
    int startingIndex = incChar(); // skip the first character
    while (nextChar() != '"')
      ; // No operation.
    stringOut = this.jsonString.substring(startingIndex, this.i);

    System.out.println("Returing string " + stringOut);
    return stringOut;
  } // parseStringInner()

  /**
   * Parse a key
   * @pre the string must be a key
   * @return the string that is the key
   */
  public String parseKey()
  {
    // Get the key
    return parseStringInner(); // this is the key
  } // parseKey (String)
  
}// JSONDecode class