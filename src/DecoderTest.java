import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A set of simple unit tests for JSONDecode 
 */
public class DecoderTest
{

  JSONDecoder decoder = new JSONDecoder("");
  static JSONBoolean t = new JSONBoolean('t');
  static JSONBoolean f = new JSONBoolean('f');
  
 // @Test
  public void testTrue() throws Exception
  {
    JSONDecoder decoder = new JSONDecoder("true");
    assertTrue("testing true", t.equals((JSONBoolean) decoder.parseVal()));
  }//testTrue
 // @Test
  public void testFalse() throws Exception
  {
    JSONDecoder decoder = new JSONDecoder("false");
    assertTrue("testing flase", f.equals((JSONBoolean) decoder.parseVal()));
  }//testFasle
 // @Test
  public void testNull() throws Exception
  {
    JSONDecoder decoder = new JSONDecoder("null");
    assertEquals("testing null", decoder.parseVal(), null);
  }//testNull
  @Test
  public void testNumber() throws Exception
  {
    JSONDecoder decoder = new JSONDecoder("15");
    assertEquals("testing 15", ((JSONNumber)decoder.parseVal()).toString(), "15");
  }//testNumber

}
