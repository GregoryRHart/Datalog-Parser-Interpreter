package project2;

import project1.*;

public class Comparator extends Node {
	private String name;
	
	public Comparator(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		name = cur.getValue();
		tokenList.advance();
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.LEFT_PAREN){
			tokenList.advance();
			cur = tokenList.getCurrent();
		}else{
			throw new ParseException(cur);
		}
		if(cur.getType() == TokenType.ID || cur.getType() == TokenType.STRING){
			children.add(new Argument(tokenList));
			cur = tokenList.getCurrent();
		}else{
			throw new ParseException(cur);
		}
		if(cur.getType() == TokenType.COMMA){
			tokenList.advance();
			cur = tokenList.getCurrent();
		}else{
			throw new ParseException(cur);
		}
		if(cur.getType() == TokenType.ID || cur.getType() == TokenType.STRING){
			children.add(new Argument(tokenList));
			tokenList.advance();
			cur = tokenList.getCurrent();
		}else{
			throw new ParseException(cur);
		}
		if(cur.getType() == TokenType.RIGHT_PAREN){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
	}

	public String toString() {
		String str = name + "(" + children.get(0).toString();
		for(int i = 1;i<children.size();i++){
			str += "," + children.get(i).toString();
		}
		return str + ")";
	}

}
