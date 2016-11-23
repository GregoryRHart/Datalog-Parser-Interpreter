//REQUIRED CLASS
package project2;

import project1.*;

public class RuleList extends Node {
	public RuleList(LexicalAnalyzer tokenList) throws ParseException {
		Token cur = tokenList.getCurrent();
		while (cur.getType() == TokenType.COMMENT) {
			tokenList.advance();
			cur = tokenList.getCurrent();
		}
		while (cur.getType() == TokenType.ID) {
			children.add(new Rule(tokenList));
			cur = tokenList.getCurrent();
			while (cur.getType() == TokenType.COMMENT) {
				tokenList.advance();
				cur = tokenList.getCurrent();
			}
		}
	}

	public int size() {
		return children.size();
	}

	protected Node getList(){
		return this;
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < children.size(); i++) {
			str += children.get(i).toString();
		}
		return str;
	}
}
