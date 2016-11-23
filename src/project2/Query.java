//REQUIRED CLASS
package project2;

import java.util.List;

import project1.*;

public class Query extends Node{
	private String name;
	
	public Query(LexicalAnalyzer tokenList) throws ParseException{
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
			cur = tokenList.getCurrent();
		}else{
			throw new ParseException(cur);
		}
		if(cur.getType() == TokenType.Q_MARK){
			tokenList.advance();
		}else{
			throw new ParseException(cur);
		}
	}
	
	public String getName(){
		return name;
	}
	
	public List<String> getVaules(){
		return ((ArgumentList)children.get(0)).getValues();
	}
	
//	public String toString(){
//		return  "  " + name + "(" + children.get(0).toString() + ")?\n";
//	}
	
	public String toString(){
		return  name + "(" + children.get(0).toString() + ")? ";
	}
}
