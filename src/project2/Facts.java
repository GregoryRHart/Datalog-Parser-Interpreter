//REQUIRED CLASS
package project2;

import project1.*;

public class Facts extends Node{
	private Token title;

	public Facts(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMENT){
			tokenList.advance();
			cur = tokenList.getCurrent();
		}
		if (cur.getType() == TokenType.FACTS){
			title = cur;
			tokenList.advance();
		}else {
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if(cur.getType() == TokenType.COLON){
			new Colon(tokenList);
		}else{
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		children.add(new FactList(tokenList));
	}
	
	public String toString(){
		String str = "";
		int size = 0;
		for(int i = 0;i<children.size();i++){
			str += children.get(i).toString();
		}
		if(children.size() > 0)
			size = children.get(0).size();
		return "Facts(" + size + "):\n" + str;
	}
	
//	protected Node get(int i){
//		return children.get(i);
//	}
}
