package project2;

import project1.LexicalAnalyzer;
import project1.Token;
import project1.TokenType;

public class Expression extends Node {
	private String operation;

	public Expression(LexicalAnalyzer tokenList) throws ParseException {
		Token cur = tokenList.getCurrent();
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
		if (cur.getType() == TokenType.ADD
				|| cur.getType() == TokenType.MULTIPLY) {
			operation = cur.getValue();
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
		if (cur.getType() == TokenType.RIGHT_PAREN) {
			tokenList.advance();
		} else {
			throw new ParseException(cur);
		}
	}

	public String toString() {
		return "(" + children.get(0).toString() + operation + children.get(1).toString() + ")";
	}

}
