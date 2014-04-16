public class JSONExpt
{

  static String test1 = "{ " + "\"name\" : { " + "\"first\": \"Sam\","
                        + " \"last\": \"Rebelsky\" " + "}" + "}";
  static String test2 = "{ " + "\"n\" : { " + "\"f\": \"S\","
                        + " \"l\": \"R\" " + "}" + "}";

  public static void
    main (String[] args)
  {
    String simple = "{\"name\":\"Graeme\"}";
    String simple2 = "{\"name\":\"Graeme\",\"lastName\":\"Boy\"}";
    JSONDecoder decoder = new JSONDecoder (simple2);
    
    JSONObject obj = (JSONObject) decoder.jsonDecode ();
    
    obj.printKeys ();
    // json.parse(test1.substring(1));
    // json.parse(test1.substring(1));
    // paradox
    // json.parse(test1.substring(1, 47));
  } // main (String [])
}
