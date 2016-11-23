package project2;

import project1.*;
import java.util.List;
import java.util.ArrayList;

public class AttributeList extends Node {
	private List<String> attributes = new ArrayList<String>();
	
	public AttributeList(LexicalAnalyzer tokenList) throws ParseException{
		Token cur = tokenList.getCurrent();
		attributes.add(cur.getValue());
		tokenList.advance();
		cur = tokenList.getCurrent();
		while(cur.getType() == TokenType.COMMA){
			tokenList.advance();
			cur = tokenList.getCurrent();
			if(cur.getType() == TokenType.ID){
				attributes.add(cur.getValue());
				tokenList.advance();
			}else{
				throw new ParseException(cur);
			}
			cur = tokenList.getCurrent();
		}
	}
	
	public String toString(){
		String str = attributes.get(0);
		for(int i = 1;i<attributes.size();i++){
			str += "," + attributes.get(i);
		}
		return str;
	}
	
	protected List<String> getValues(){
		return attributes;
	}
}
