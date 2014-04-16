import java.math.BigInteger;

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

    String complex1 = "{\n\"person\":\n{\"first\" :\n \"Graeme\" , \"last\" : \"Boy\","
                      + "\"age\":23,\"class year\":2014,  \"male\":true, \"friends\":[\"Sam\", \"Joe\"], \"hasCat?\":false}}";
    JSONDecoder decoder = new JSONDecoder (complex1);

    JSONObject obj = (JSONObject) decoder.jsonDecode ();

    obj.printKeys ();

    System.out.println ("About to get");
    JSONObject person = (JSONObject) obj.get ("person");

    if (person instanceof JSONObject)
      {
        person.printKeys ();
      }

    System.out.println ("First name is " + person.get ("first"));
    System.out.println ("Last name is " + person.get ("last"));

    System.out.println ("Age is " + person.get ("age"));

    System.out.println ("Class year is " + person.get ("class year"));
    System.out.println ("Is male? " + person.get ("male"));
    
    System.out.println ("Has a cat? " + person.get ("hasCat?"));

    // json.parse(test1.substring(1));
    // json.parse(test1.substring(1));
    // paradox
    // json.parse(test1.substring(1, 47));
  } // main (String [])
}
