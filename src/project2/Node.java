package project2;

import java.util.List;
import java.util.ArrayList;

public abstract class Node{
	List<Node> children = new ArrayList<Node>();

	public int size(){
		return children.size();
	}
	
	public Node get(int i){
		if(i < children.size()){
		return children.get(i);
		}else{
			return null;
		}
	}
	
public abstract String toString();
}