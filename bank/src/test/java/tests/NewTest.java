package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

public class NewTest {
  @Test
  public void f() throws IOException {
		  assertEquals(false,1==2);
		  }

  @Test
  public void g() {
		  assertEquals(false,1==2-1);
  }


}
