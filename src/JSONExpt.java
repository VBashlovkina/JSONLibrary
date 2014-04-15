public class JSONExpt
{
  
  static String test1 = 
  "{ " +
  	"\"name\" : { " +
  	  "\"first\": \"Sam\"," +
  	  " \"last\": \"Rebelsky\" " +
  	"}" +
  "}";
  static String test2 = 
      "{ " +
            "\"n\" : { " +
              "\"f\": \"S\"," +
              " \"l\": \"R\" " +
            "}" +
      "}";
  public static void main(String[] args)
  {
    JSONObject json = new JSONObject(test1);
    
    //json.parse(test1.substring(1));
    json.parse(test1.substring(1));
    //paradox
    json.parse(test1.substring(1, 47));
  } // main (String [])
}
