//REQUIRED CLASS
package project2;

import project1.*;
import java.util.Iterator;

public class DatalogProgram extends Node
{
	private LexicalAnalyzer tokenList;
	public DatalogProgram(LexicalAnalyzer tokenList) throws ParseException {
		this.tokenList = tokenList;
		Iterator i = tokenList.iterator();
		while(i.hasNext()){
		Token t = (Token) i.next();
			if(t.getType() == TokenType.COMMENT){
				i.remove();
			}
		}
		Token cur;
		children.add(new Schemes(tokenList));
		children.add(new Facts(tokenList));
		children.add(new Rules(tokenList));
		children.add(new Queries(tokenList));
		cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMENT){
			tokenList.advance();
			cur = tokenList.getCurrent();
		}
		if(cur.getType() != TokenType.EOF){
			throw new ParseException(cur);
		}
	}
	
	public Node getSchemes(){
		return ((SchemeList) children.get(0).get(0)).getList();
	}
	
	public Node getFacts(){
		return ((FactList) children.get(1).get(0)).getList();
	}
	
	public Node getRules(){
		try{
		return ((RuleList) children.get(2).get(0)).getList();
		} catch(NullPointerException e) {
			return null;
		}
	}
	
	public Node getQueries(){
		return ((QueryList) children.get(3).get(0)).getList();
	}

	public String toString(){
		String str = "";
		for (int i = 0; i < children.size();i++){
			str += children.get(i);
		}
		return "Success!\n" + str + "Domain(" + tokenList.domSize() + "):" + tokenList.domPrint();
	}
}
