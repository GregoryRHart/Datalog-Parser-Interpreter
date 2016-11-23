//REQUIRED CLASS
package project2;

import project1.*;

public class Rule extends Node{
	private String name;
	
	public Rule(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.ID){
			name = cur.getValue();
			tokenList.advance();
		} else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.LEFT_PAREN){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		children.add(new ArgumentList(tokenList));
		cur = tokenList.getCurrent();
		if(cur.getType()==TokenType.RIGHT_PAREN){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.COLON_DASH){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		children.add(new Predicate(tokenList));
		cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMA){
			tokenList.advance();
			children.add(new Predicate(tokenList));
			cur = tokenList.getCurrent();
		}
		if(cur.getType() == TokenType.PERIOD){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		String str = children.get(1).toString();
		for(int i = 2;i<children.size();i++){
			str += "," + children.get(i).toString();
		}
		return "  " + name + "(" + children.get(0).toString() + ") :- " + str + ".\n";
	}
}
