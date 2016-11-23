package project5;

public class AAPair extends Pair{
	private int pos;
	private String at;
	
	public AAPair(int pos, String at){
		this.pos = pos;
		this.at = at;
	}
	
	public int getPos() {
		return pos;
	}

	public String getAt() {
		return at;
	}
	
	public String toString(){
		return "Postion: " + pos + " Name: " + at;
	}
}
