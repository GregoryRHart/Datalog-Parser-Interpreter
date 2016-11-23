//REQUIRED CLASS
package project2;

import project1.*;
import java.util.List;
import java.util.ArrayList;

public class Predicate extends Node {
	private String name;

	public Predicate(LexicalAnalyzer tokenList) throws ParseException {
		Token cur = tokenList.getCurrent();
		if (cur.getType() == TokenType.ID) {
			name = cur.getValue();
			tokenList.advance();
		} else {
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		if (cur.getType() == TokenType.LEFT_PAREN) {
			tokenList.advance();
		} else {
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		switch (cur.getType()) {
		case ID:
		case STRING:
			children.add(new Argument(tokenList));
			break;
		case GT:
		case LT:
		case GE:
		case LE:
		case EQ:
		case NE:
			children.add(new Comparator(tokenList));
			break;
		case LEFT_PAREN:
			tokenList.advance();
			children.add(new Expression(tokenList));
			break;
		default:
			throw new ParseException(cur);
		}
		cur = tokenList.getCurrent();
		while (cur.getType() == TokenType.COMMA) {
			tokenList.advance();
			cur = tokenList.getCurrent();
			switch (cur.getType()) {
			case ID:
			case STRING:
				children.add(new Argument(tokenList));
				break;
			case GT:
			case LT:
			case GE:
			case LE:
			case EQ:
			case NE:
				children.add(new Comparator(tokenList));
				break;
			case LEFT_PAREN:
				tokenList.advance();
				children.add(new Expression(tokenList));
				break;
			default:
				throw new ParseException(cur);
			}
			cur = tokenList.getCurrent();
		}
		if (cur.getType() == TokenType.RIGHT_PAREN) {
			tokenList.advance();
		} else {
			throw new ParseException(cur);
		}
	}

	public List<String> getChildren(){
		List<String> l = new ArrayList<String>();
		for(int i = 0; i<children.size();i++){
			l.add(((Argument)children.get(i)).getValue());
		}
		return l;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString() {
		String str = name + "(" + children.get(0).toString();
		for (int i = 1; i < children.size(); i++) {
			str += "," + children.get(i).toString();
		}
		return str + ")";
	}
}
