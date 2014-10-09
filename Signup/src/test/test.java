package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

public class test {
  @Test
  public void f() throws FileNotFoundException, UnsupportedEncodingException {
	  System.out.println(System.getProperty("user.dir"));
  	PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/XSLT_Reports/output/endtoend.txt", "UTF-8");
  	writer.println("message");
  	writer.close();
  }
}
