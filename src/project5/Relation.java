//REQUIRED CLASS
package project5;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import project1.*;
import project2.*;

public class Relation{
	private Schema scheme;
	private Set<Tuple> tuples;
	
	public Relation (Schema scheme){
		this.scheme = new Schema(scheme);
		tuples = new TreeSet<Tuple>();
	}
	
	public Relation(Schema scheme, Set<Tuple> tuples) {
		this.scheme = new Schema(scheme);
		this.tuples = new TreeSet<Tuple>(tuples);
	}
	
	public Relation(Relation r){
		scheme = new Schema(r.getSchema());
		tuples = new TreeSet<Tuple>(r.getTuples());
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
		if (r == null){
			return this;
		}else if(this == null){
			return r;
		}
		Set<Tuple> t = r.getTuples();
		Set<Tuple> t2 = new TreeSet<Tuple>();
		Schema s = r.getSchema();
		Schema s1 = scheme.join(s);
		List<List<Integer>> overlap = scheme.overLap(s);
//		System.out.println();
//		System.out.println(scheme);
//		System.out.println(tuples);
//		System.out.println(s);
//		System.out.println(s1);
		for(Tuple cur: tuples){
			for(Tuple cur2: t){
				Tuple first  = cur.getPart(overlap.get(0));
				Tuple second = cur2.getPart(overlap.get(1));
//    			System.out.println("Frist: " + cur);
//    			System.out.println(overlap);
//				System.out.println("Second: " + cur2);
//				System.out.println(first);
//				System.out.println(second);
				if(first.equals(second)){
//					System.out.println("Overlap: " + cur2.getRest(overlap.get(1)));
//					System.out.println("Added: " + (new Tuple(cur)).add(cur2.getRest(overlap.get(1))));
					Tuple c = new Tuple(cur);
					t2.add(c.add(cur2.getRest(overlap.get(1))));
				}
			}
		}
		return new Relation(s1,t2);
	}
	
	public Relation union(Relation r){
		Set<Tuple> t = r.getTuples();
		tuples.addAll(t);
		return this;
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
		for(Tuple t: tuples){
			String temp = "  ";
			for(int i=0;i<t.size();i++){
				temp += scheme.get(i) + "=" + t.get(i) + ", ";
			}
			str += temp.substring(0,temp.length()-2) + "\n";
		}
		return str;
	}
	
	public int size(){
		return tuples.size();
	}
}
