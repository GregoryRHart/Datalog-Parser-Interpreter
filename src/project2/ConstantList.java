package project2;

import project1.*;
import java.util.List;
import java.util.ArrayList;

public class ConstantList extends Node {
	private List<String> attributes = new ArrayList<String>();
	
	public ConstantList(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		attributes.add(cur.getValue());
		tokenList.advance();
		cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMA){
			tokenList.advance();
			cur = tokenList.getCurrent();
			if(cur.getType() == TokenType.STRING){
				attributes.add(cur.getValue());
				tokenList.advance();
			}else{
				throw new ParseException(cur);
			}
			cur = tokenList.getCurrent();
		}
	}
	
	protected List<String> get(){
		return attributes;
	}
	
	public String toString(){
		String str = attributes.get(0);
		for(int i = 1;i<attributes.size();i++){
			str += "," + attributes.get(i);
		}
		return str;
	}
}
