//REQUIRED CLASS
package project2;

import project1.*;
import java.util.List;
import java.util.ArrayList;

public class Argument extends Node {
	private String values;

	public Argument(LexicalAnalyzer tokenList) throws ParseException {
		Token cur = tokenList.getCurrent();
		switch (cur.getType()) {
			case STRING:
			case ID:
				values = cur.getValue();
				tokenList.advance();
				break;
			default:
				throw new ParseException(cur);
		}

	}

	protected String getValue(){
		return values;
	}
	
	public String toString(){
		return values;
	}
}
