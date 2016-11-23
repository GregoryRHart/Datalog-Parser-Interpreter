//REQUIRED CLASS
package project2;

import java.util.List;

import project1.*;

public class Fact extends Node{
	private String name;
	
	public Fact(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		name = cur.getValue();
		tokenList.advance();
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.LEFT_PAREN){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.STRING){
			children.add(new ConstantList(tokenList));
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.RIGHT_PAREN){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.PERIOD){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		
	}
	
	public String getName(){
		return name;
	}
	
	public List<String> getValue(){
		return ((ConstantList) children.get(0)).get();
	}
	
	public String toString(){
		
		return "  " + name + "(" + children.get(0).toString() + ").\n";
	}
		
}
