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
  
  @Test
  public void testTrue() throws Exception
  {
    JSONDecoder decoder = new JSONDecoder("true");
    assertEquals("testing true", decoder.parseVal(),t.get());
  }//testTrue

}
