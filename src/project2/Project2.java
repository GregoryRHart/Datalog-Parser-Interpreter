//REQUIRED CLASS - KEEP THIS FILE IN THE 'project2' DIRECTORY WITH 'package project2'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'DatalogProgram' and return an ouput string
//DO NOT ADD METHODS

package project2;

import project1.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project2
{
	public static String body(String[] args)
	{
		BufferedReader reader = null;
		LexicalAnalyzer tokenList = null;
		DatalogProgram datalog = null;
		String output;
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
		try{
		datalog = new DatalogProgram(tokenList);
		}
		catch (ParseException e) {
			output = e.print();
			e.printStackTrace();
			return output;
		}
		output = datalog.toString();
		return output;
	}
}
