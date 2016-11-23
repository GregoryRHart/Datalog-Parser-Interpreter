package project1;

public class Token {
	// Domain
	private final String value;
	private final TokenType type;
	private final int lineNumber;

	// Constructor
	public Token(TokenType t, String val, int lineNum) {
		value = val;
		type = t;
		lineNumber = lineNum;
	}

	// Access Methods
	public String getValue() {
		return value;
	}

	public TokenType getType() {
		return type;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	// Print Method
	public String toString() {
		String str = "(" + type + ",\"" + value + "\"," + lineNumber + ")";
		return str;
	}
}
