//REQUIRED CLASS
package project4;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import project1.*;
import project2.*;

public class Relation{
	private Schema scheme;
	private Set<Tuple> tuples;
	
	public Relation (Schema scheme){
		this.scheme = new Schema(scheme);
		tuples = new HashSet<Tuple>();
	}
	
	public Relation(Schema scheme, Set<Tuple> tuples) {
		this.scheme = new Schema(scheme);
		this.tuples = new HashSet<Tuple>(tuples);
	}

	public Relation select(List<Pair> l){
		Relation r = new Relation(scheme);
		for(Tuple t: tuples){
			if(t.has(l)){
				r.add(t);
			}
		}
		return r;
	}

	public Relation rename(List<AAPair> l){		
		Relation r = new Relation(scheme, tuples);
		for(int i = 0; i<l.size(); i++){
			r.scheme.rename(l.get(i));
		}
		return r;
	}
	
	public Relation project(List<Integer> keep){
		Schema s = scheme.select(keep);
		
		Relation r = new Relation(s);
		for(Tuple t: tuples){
			r.add(t.getPart(keep));
		}
		return r;
	}
	
	public Relation join(Relation r){
		Set<Tuple> t = r.getTuples();
		Set<Tuple> t2 = new HashSet<Tuple>();
		Schema s = r.getSchema();
		s = scheme.join(s);
		List<List<Integer>> overlap = scheme.overLap(s);
		for(Tuple cur: tuples){
			for(Tuple cur2: t){
				if(cur.getPart(overlap.get(0)).equals(cur2.getPart(overlap.get(1)))){
					t2.add(cur.add(cur2.getRest(overlap.get(1))));
				}
			}
		}
		return new Relation(s,t2);
	}
	
	public boolean add(Tuple t){
		return tuples.add(t);
	}
	
	public String getSchemaName(){
		return scheme.getName();
	}
	
	public Schema getSchema(){
		return scheme;
	}
	
	public Set<Tuple> getTuples(){
		return tuples;
	}
	
	public boolean equals(Relation o){
		return tuples.equals(o.getTuples());
	}
	
	public String toString(){
		String str = "";
		List<String> a = new ArrayList<String>();
		for(Tuple t: tuples){
			String temp = "  ";
			for(int i=0;i<t.size();i++){
				temp += scheme.get(i) + "=" + t.get(i) + ", ";
			}
			a.add(temp.substring(0,temp.length()-2) + "\n");
		}
		Collections.sort(a);
		for(String s: a){
			str+= s;
		}
		return str;
	}
	
	public int size(){
		return tuples.size();
	}
}
