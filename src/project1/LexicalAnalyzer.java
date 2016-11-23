package project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class LexicalAnalyzer implements Iterable<Token> {
	private ArrayList<Token> tokenList = new ArrayList<Token>();
	private Set<String> domain = new TreeSet<String>();
	private int index = 0;
	private final int newline = 10;

	public LexicalAnalyzer(BufferedReader reader) throws IOException {
		State state = State.START;       
		int lineNum = 1;
		int line = 1;
		String str = "";

		while (state != State.EOF) {
			int c = reader.read();
			Transition transition = state.nextTransition(c);
			state = transition.getNextState();

			// If the transition's output token type is non-null, then there
			// is a complete token to emit.  If it is null, then the value is
			// still being built, and there is nothing to emit.
			if (transition.getTokenType() != null) {
				if(transition.getTokenType() == TokenType.STRING){
					domain.add(str);
				}
				if(transition.getTokenType() == TokenType.WHITESPACE){
					str = "";
					lineNum = line;
				}else {
					if(transition.getTokenType() == TokenType.ID){
						if(str.equals("Schemes")){
							transition.setTokenType(TokenType.SCHEMES);
						}else if(str.equals("Facts")){
							transition.setTokenType(TokenType.FACTS);
						}else if(str.equals("Rules")){
							transition.setTokenType(TokenType.RULES);
						}else if(str.equals("Queries")){
							transition.setTokenType(TokenType.QUERIES);
						}
					}
					tokenList.add(new Token(transition.getTokenType(), str, lineNum));
					lineNum = line;
					str = "";
				}
			}
			if (c == newline){
				line += 1;
			}
			str += (char) c;
		}
		tokenList.add(new Token(TokenType.EOF,"",line));
	}

	// This allows code that uses a LexicalAnalyzer called lexer to do:
	//   for (Token token: lexer)
	public Iterator<Token> iterator() {
		return tokenList.iterator();
	}
	
	public int size(){
	 	return tokenList.size();
	 }
	
	public Token getCurrent(){
		return tokenList.get(index);
	}
	
	public void remove(Token t){
		tokenList.remove(t);
	}
	
	public void advance(){
		index++;
	}
	
	public int domSize(){
		return domain.size();
	}
	
	public String domPrint(){
		String str = "";
		for (String t : domain)
		{
			str += "\n  " + t;
		}
		return str;
	}
}
