//REQUIRED CLASS
package project2;

import project1.*;

public class Queries extends Node {
	private Token title;

	public Queries(LexicalAnalyzer tokenList) throws ParseException {
		Token cur = tokenList.getCurrent();
		while (cur.getType() == TokenType.COMMENT) {
			tokenList.advance();
			cur = tokenList.getCurrent();
		}
		if (cur.getType() == TokenType.QUERIES) {
			title = cur;
			tokenList.advance();
		} else {
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if (cur.getType() == TokenType.COLON) {
			new Colon(tokenList);
		}
		cur = tokenList.getCurrent();
		while (cur.getType() == TokenType.COMMENT) {
			tokenList.advance();
			cur = tokenList.getCurrent();
		}
		if (cur.getType() == TokenType.ID) {
			children.add(new QueryList(tokenList));
		}
	}

	public String toString() {
		String str = "";
		int size = 0;
		for (int i = 0; i < children.size(); i++) {
			str += children.get(i).toString();
		}
		if (children.size() > 0)
			size = children.get(0).size();
		return "Queries(" + size + "):\n" + str;
	}
	
//	protected Node get(int i){
//		retrun children.get(i);
//	}
}
