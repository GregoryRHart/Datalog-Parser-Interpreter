package project2;

import project1.*;

public class Colon extends Node {

	public Colon(LexicalAnalyzer tokenList) throws ParseException{
		Token token = tokenList.getCurrent();
		if (token.getType() == TokenType.COLON){
			tokenList.advance();
		}else{
			throw new ParseException(token);
		}
	}
	
	public String toString(){
		return ":";
	}
}
