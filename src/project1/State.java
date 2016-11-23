package project1;

enum State {
	START {
		public Transition nextTransition(int c) {
			// The code to determine the next state after the start state is
			// in a separate method that is reused by other states.
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = null;
			return new Transition(nextState, tokenType);
		}
	},
	// New states go here.
	COLON {
		// If colon is followed by white space we have a token else it should be
		// followed by a dash.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if ((char) c == '-'){
				nextState = State.COLON_DASH;
			} else {
				nextState = whatWouldStartDo(c);
				tokenType = TokenType.COLON;
			}
			return new Transition(nextState, tokenType);
		}
	},
	COLON_DASH{
		public Transition nextTransition(int c){
			State nextState = whatWouldStartDo(c);
			return new Transition(nextState,TokenType.COLON_DASH);
		}
	},
	COMMA {
		// A comma is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.COMMA;
			return new Transition(nextState, tokenType);
		}

	},
	LEFT_PAREN {
		// A left parenthesis is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.LEFT_PAREN;
			return new Transition(nextState, tokenType);
		}

	},
	PERIOD {
		// A period is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.PERIOD;
			return new Transition(nextState, tokenType);
		}

	},
	Q_MARK {
		// A question mark is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.Q_MARK;
			return new Transition(nextState, tokenType);
		}
	},
	RIGHT_PAREN {
		// A left parenthesis is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.RIGHT_PAREN;
			return new Transition(nextState, tokenType);
		}
	},
	EQ {
		// An equal sign is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.EQ;
			return new Transition(nextState, tokenType);
		}
	},
	NE {
		// Not equals has two symbols, but the first one tells me I am in the right place.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if((char) c == '='){
				nextState = State.NEEND;
			}else{
				nextState = whatWouldStartDo(c);
				tokenType = TokenType.UNDEFINED;
			}
			return new Transition(nextState, tokenType);
		}

	},
	NEEND{
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			return new Transition(nextState, TokenType.NE);
		}
	},
	GT {
		// This state could be a complete token or lead to GE.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if((char) c == '='){
				nextState = State.GE;
			} else {
				nextState = whatWouldStartDo(c);
				tokenType = TokenType.GT;
			}
			return new Transition(nextState, tokenType);
		}

	},
	GE{
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.GE;
			return new Transition(nextState, tokenType);
		}
	},
	LT {
		// This state could be a complete token or lead to LE.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if((char) c == '='){
				nextState = State.LE;
			} else {
				nextState = whatWouldStartDo(c);
				tokenType = TokenType.LT;
			}
			return new Transition(nextState, tokenType);
		}

	},
	LE{
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.LE;
			return new Transition(nextState, tokenType);
		}
	},
	MULTIPLY {
		// An asterisk is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.MULTIPLY;
			return new Transition(nextState, tokenType);
		}
	},
	ADD {
		// A plus sign is a token by itself. So we have finished a token.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.ADD;
			return new Transition(nextState, tokenType);
		}

	},
	COMMENT {
		// There are two types of comments and many characters instead one.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if((char) c == '\n'){
				nextState = State.WHITESPACE;
				tokenType = TokenType.COMMENT;
			}else if((char) c == '|'){
				nextState = State.MLCOMMENT;
			}else if(c == -1){
				nextState = State.EOF;
				tokenType = TokenType.UNDEFINED;
			}else{
				nextState = State.SLCOMMENT;
			}
			return new Transition(nextState,tokenType);
		}

	},
	SLCOMMENT{
		// State for a single line comment
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if((char) c == '\n'){
				nextState = State.WHITESPACE;
				tokenType = TokenType.COMMENT;
			}else if(c == -1){
				nextState = State.EOF;
				tokenType = TokenType.UNDEFINED;
			}else{
				nextState = State.SLCOMMENT;
			}
			return new Transition(nextState,tokenType);
		}
		
	},
	MLCOMMENT{
		// State for multiple line comment
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if ((char) c == '|'){
				nextState = State.MLCOMMENTEND;
			}else if(c == -1){
				nextState = State.EOF;
				tokenType = TokenType.UNDEFINED;
			}else{
				nextState = State.MLCOMMENT;
			}
			return new Transition(nextState,tokenType);
		}
		
	},
	MLCOMMENTEND{
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if ((char) c == '#'){
				nextState = State.COMMENTEND;
			}else if(c == -1){

			}else{
				nextState = State.MLCOMMENT;
			}
			return new Transition(nextState, tokenType);
		}
	},
	COMMENTEND{
		// Make sure we get both ending characters.
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.COMMENT;
			return new Transition(nextState, tokenType);
		}
	},
	STRING {
		// Strings are a list of character beginning and ending with '.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if ((char) c == '\''){
				nextState = State.STRINGEND;
			}else if(c == -1){
				nextState = State.EOF;
				tokenType = TokenType.UNDEFINED;
			}else{
				nextState = State.STRING;
			}			
			return new Transition(nextState, tokenType);
		}
	},
	STRINGEND{
		// Is it the end of the string or an escape character.
		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if ((char) c == '\''){
				nextState = State.STRING;
			}else{
				nextState = whatWouldStartDo(c);
				tokenType = TokenType.STRING;
			}
			return new Transition(nextState, tokenType);
		}
	},
	IDENTIFIER {

		public Transition nextTransition(int c) {
			State nextState = null;
			TokenType tokenType = null;
			if (Character.isLetterOrDigit((char) c)){
				nextState = State.IDENTIFIER;
			}else{
				nextState = whatWouldStartDo(c);
				tokenType = TokenType.ID;
			}
			return new Transition(nextState, tokenType);
		}

	},
	WHITESPACE{
		// Just consume the characters
		public Transition nextTransition(int c) {
	        State nextState = whatWouldStartDo(c);
			return new Transition(nextState, TokenType.WHITESPACE);
		}	
	},
	EOF {
		public Transition nextTransition(int c) {
			// The EOF state is never exited.
			return null;
		}
	},
	UNDEFINED {
		public Transition nextTransition(int c) {
			State nextState = whatWouldStartDo(c);
			TokenType tokenType = TokenType.UNDEFINED;
			return new Transition(nextState, tokenType);
		}
	}; // End of the list of states.

	/*
	 * Determines which state to transition to on the given input character and
	 * determines which token type to emit. This method must be implemented by
	 * all of the enum constants.
	 */
	public abstract Transition nextTransition(int c);

	public static State whatWouldStartDo(int c) {
		State state = null;
		if (c == -1) {
			state = EOF;
		} else if(Character.isWhitespace((char) c)){
			state = WHITESPACE;
		}else if(Character.isLetter((char) c)){
			state = IDENTIFIER;
		}else{
			char ch = (char) c;
			switch (ch){
			case '\'':
				state = STRING;
				break;
			case ':':
				state = COLON;
				break;
			case ',':
				state = COMMA;
				break;
			case '(':
				state = LEFT_PAREN;
				break;
			case '.':
				state = PERIOD;
				break;
			case '?':
				state = Q_MARK;
				break;
			case ')':
				state = RIGHT_PAREN;
				break;
			case '=':
				state = EQ;
				break;
			case '!':
				state = NE;
				break;
			case '>':
				state = GT;
				break;
			case '<':
				state = LT;
				break;
			case '*':
				state = MULTIPLY;
				break;
			case '+':
				state = ADD;
				break;
			case '#':
				state = COMMENT;
				break;
			default:
				state = UNDEFINED;
			}
		}
		return state;
	}
}
