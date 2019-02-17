package com.gzhc365.test;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Hello
{
	public static void main(String[] args) throws IOException
	{
		InputStreamReader ir = new InputStreamReader(System.in);
		System.out.println("Hello");
		ir.read();
	}
}