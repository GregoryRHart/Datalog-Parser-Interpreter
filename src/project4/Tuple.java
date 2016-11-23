//REQUIRED CLASS
package project4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import project1.*;
import project2.*;

public class Tuple extends Object implements Comparable<Tuple> {
	private List<String> values;

	public Tuple(List<String> value) {
		this.values = value;
	}

	public boolean equals(Tuple o) {
		List<String> a = o.getValue();
		boolean result = true;
		if (a.size() != values.size()) {
			result = false;
		} else {
			for (int i = 0; i < a.size(); i++) {
				if (!a.get(i).equals(values.get(i))) {
					return false;
				}
			}
		}
		return result;
	}

	public boolean equals(Object o) {
		List<String> a = ((Tuple)o).getValue();
		boolean result = true;
		if (a.size() != values.size()) {
			result = false;
		} else {
			for (int i = 0; i < a.size(); i++) {
				if (!a.get(i).equals(values.get(i))) {
					return false;
				}
			}
		}
		return result;
	}

	protected Tuple getRest(List<Integer> l) {
		List<String> t = new ArrayList<String>(values);
		for (Integer s : l) {
			t.remove(s);
		}
		return new Tuple(t);
	}

	protected Tuple getPart(List<Integer> keep) {
		List<String> a = new ArrayList<String>();
		for (Integer s : keep) {
			a.add(values.get(s));
		}
		return new Tuple(a);
	}

	private List<String> getValue() {
		return values;
	}

	public void add(String s) {
		values.add(s);
	}

	public void add(Collection s) {
		values.addAll(s);
	}

	public int compareTo(Tuple t) {
		List<String> a = t.getValue();
		String str1 = "";
		String str2 = "";
		if (a.size() != values.size()) {
		} else {
			for (int i = 0; i < a.size(); i++) {
				str1 += values.get(i).substring(1, values.get(i).length() - 1);
				str2 += a.get(i).substring(1, a.get(i).length() - 1);
			}
		}
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str1.compareTo(str2));
		return str1.compareTo(str2);
	}

	protected Tuple add(Tuple t) {
		List<String> v = values;
		v.addAll(t.getValue());
		return new Tuple(v);

	}

	public boolean has(List<Pair> l) {
		boolean result = true;
		for(Pair p: l){
			if(p  instanceof AVPair){
				AVPair q = (AVPair)p;
				if (!values.get(q.getPos()).equals(q.getAt())){
				return false;
				}
			}else{
				VVPair q = (VVPair)p;
				if (!values.get(q.getPos()).equals(values.get(q.getRep()))){
					return false;
					}
			}
		}
		return result;
	}

	public int hashCode(){
	      if(values.size()==0){
		return 0;
	      }
	      return values.get(0).hashCode();
	}

	public int size() {
		return values.size();
	}

	public String get(int i) {
		return values.get(i);
	}

	public String toString() {
		return values.toString();
	}
}
