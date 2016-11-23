package project2;

import project1.*;

public class ParseException extends Exception {
	private Token error;
	
	ParseException(Token token){
		error = token;
	}
	
	public String print(){
		return "Failure!\n" + error;
	}
	
	public String toString(){
		
		return "Failure!\n  " + error;
	}
}
