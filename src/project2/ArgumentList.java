//REQUIRED CLASS
package project2;

import project1.*;
import java.util.List;
import java.util.ArrayList;

public class ArgumentList extends Node {
	private List<String> values = new ArrayList<String>();

	public ArgumentList(LexicalAnalyzer tokenList) throws ParseException {
		Token cur = tokenList.getCurrent();
		switch (cur.getType()) {
			case STRING:
			case ID:
				values.add(cur.getValue());
				tokenList.advance();
				break;
			default:
				throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		while (cur.getType() == TokenType.COMMA) {
			tokenList.advance();
			cur = tokenList.getCurrent();
			switch (cur.getType()) {
				case STRING:
				case ID:
					values.add(cur.getValue());
					tokenList.advance();
					break;
				default:
					throw new ParseException(cur);
			}
			cur = tokenList.getCurrent();
		}

	}

	public List<String> getValues(){
		return values;
	}
	
	public String toString(){
		String str = values.get(0);
		for(int i = 1;i<values.size();i++){
			str += "," + values.get(i);
		}
		return str;
	}
}
