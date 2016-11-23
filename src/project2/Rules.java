//REQUIRED CLASS
package project2;

import project1.*;

public class Rules extends Node{
	private Token title;
	
	public Rules(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMENT){
			tokenList.advance();
			cur = tokenList.getCurrent();
		}
		if (cur.getType() == TokenType.RULES){
			title = cur;
			tokenList.advance();
		}else {
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.COLON){
			new Colon(tokenList);
		}
		cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMENT){
				tokenList.advance();
				cur = tokenList.getCurrent();
		 }
		 if(cur.getType() == TokenType.ID){
			 children.add(new RuleList(tokenList));
		}
	}
	
	public String toString(){
		String str = "";
		int size = 0;
		for(int i = 0;i<children.size();i++){
			str += children.get(i).toString();
		}
		if(children.size() > 0)
			size = children.get(0).size();
		return "Rules(" + size + "):\n" + str;
	}
}
