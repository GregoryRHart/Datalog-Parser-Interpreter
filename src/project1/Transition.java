package project1;

class Transition {
	// Domain
	private final State nextState;
	private TokenType tokenType;

	// Constructor
	Transition(State nextState, TokenType tokenType) {
		this.nextState = nextState;
		this.tokenType = tokenType;
	}

	// Access Methods
	public State getNextState() {
		return nextState;
	}

	public TokenType getTokenType() {
		return tokenType;
	}
	
	public void setTokenType(TokenType input) {
		tokenType = input;
	}

	// Print Method
	public String toString() {
		return null;
	}
}
