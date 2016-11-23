//REQUIRED CLASS
package project5;

import java.util.ArrayList;
import java.util.List;

import project1.*;
import project2.*;

public class Schema
{
	protected String name;
	protected List<String> attrib;
	public Schema(String name, List<String> attributes) {
		this.name = name;
		attrib = new ArrayList<String>(attributes);
	}
	
	public Schema(Schema scheme) {
		name = scheme.getName();
		attrib = new ArrayList<String>(scheme.getAttributes());
	}

	public boolean equals(Object o){
		boolean result = name.equals(((Schema) o).getName());
		return result;
	}
	
	protected String getName() {
		return name;
	}

	protected List<String> getAttributes() {
		return new ArrayList<String>(attrib);	
	}

	public String get(int i){
		return attrib.get(i);
	}
	
	protected void rename(AAPair a){
		if(a != null){
			attrib.set(a.getPos(), a.getAt());
		}
	}
	

	protected Schema select(List<Integer> keep){
		List<String> a = new ArrayList<String>();
		for(Integer s: keep){
			a.add(attrib.get(s));
		}
		return new Schema(null, a);
	}
	
	protected Schema join(Schema s){
		String name = null;
		List<String> attrib = new ArrayList<String>(this.attrib);
		List<String> a = s.getAttributes();
		for(int i = 0; i<a.size();i++){
			if(!attrib.contains(a.get(i))){
				attrib.add(a.get(i));
			}
		}
		return new Schema(name, attrib);
	}
	
	public List<List<Integer>> overLap(Schema s){
		List<String> a = s.getAttributes();
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> second = new ArrayList<Integer>();
		for(int i = 0; i<a.size();i++){
			if(attrib.contains(a.get(i))){
				for(int j=0;j<attrib.size();j++){
					if(a.get(i).equals(attrib.get(j)) && !first.contains(j)){
						first.add(j);
						second.add(i);
						break;
					}
				}
			}
		}
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		l.add(first);
		l.add(second);
		return l;
	}
	
	public String toString(){
		String str = name + "(";
		for(int i = 0; i<attrib.size(); i++){
			str += attrib.get(i) + ",";
		}
		str = str.substring(0,str.length()-1) + ")";
		return str;
	}
}
