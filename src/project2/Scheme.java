//REQUIRED CLASS
package project2;

import java.util.List;

import project1.*;

public class Scheme extends Node{
	private String name;
	public Scheme(LexicalAnalyzer tokenList) throws ParseException{
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
		if(cur.getType() == TokenType.ID){
			children.add(new AttributeList(tokenList));
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.RIGHT_PAREN){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		
		}
	
	public String getName(){
		return name;
	}
	
	public List<String> getAttributes(){
		return ((AttributeList) children.get(0)).getValues();
	}
	
	public String toString(){
		return "  " + name + "(" + children.get(0).toString() + ")\n";
	}
}
