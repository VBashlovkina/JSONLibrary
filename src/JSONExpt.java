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

    String complex1 = "{\n\"name\":\n{\"first\" :\n \"Graeme\" , \"last\" : \"Boy\",\"age\":\"23\"}}";
    JSONDecoder decoder = new JSONDecoder (complex1);

    JSONObject obj = (JSONObject) decoder.jsonDecode ();

    obj.printKeys ();

    System.out.println ("About to get");
    JSONVal name = obj.get ("name");
    
    if (name instanceof JSONObject)
      {
        ((JSONObject) name).printKeys ();
      }

    // json.parse(test1.substring(1));
    // json.parse(test1.substring(1));
    // paradox
    // json.parse(test1.substring(1, 47));
  } // main (String [])
}
