//REQUIRED CLASS - KEEP THIS FILE IN THE 'project1' DIRECTORY WITH 'package project1'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'LexicalAnalyzer' and return an output string
//DO NOT ADD METHODS

package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project1
{
	
	public static String body(String[] args)
	{
		BufferedReader reader = null;
		LexicalAnalyzer tokenList = null;
		try{
			reader = new BufferedReader(new FileReader(args[0]));
		} catch (IOException e){
			System.out.println("Bad File");
		}
		try {
			tokenList = new LexicalAnalyzer(reader);
		} catch (IOException e) {
			System.out.println("Read failed");
			e.printStackTrace();
		}
		
		for (Token t : tokenList)
		{
			System.out.println(t);
		}
		
		System.out.println("Total Tokens = " + tokenList.size());
		String output = "";
		return output;
	}
}
