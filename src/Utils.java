public class Utils
{
  /**
   * Parse JSON code into java and return the object that it represents
   * @pre str is a piece of valid JSON code
   * @pre str is not null
   * @post the object represented by str is returned
   * @param str
   * @return
   *    a Hashtable<String,Object> if str represents a JSON object or a pair
   *    a ArrayList<Object> if str is a JSON array
   *    a String if str is a JSON string
   *    a BigDecimal if str is a JSON number
   *    a boolean if str is a JSON constant true or false
   *    an Object if str is a JSON object null
   */
  public Object parse(String str)
  {
    char c = str.charAt(0);
    if (c == '\"')
     return str; 
    return str;

  }// parse (String)
}// Utils class
