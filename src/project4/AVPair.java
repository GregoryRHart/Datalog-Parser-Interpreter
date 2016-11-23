package project4;

public class AVPair extends Pair {
	private int pos;
	private String at;
	
	public AVPair(int pos, String at){
		this.pos = pos;
		this.at = at;
	}
	
	public int getPos() {
		return pos;
	}

	public String getAt() {
		return at;
	}
}
