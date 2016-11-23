//REQUIRED CLASS
package project5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project1.*;
import project2.*;

public class Database {
	private List<Node> rules;
	private List<Relation> queries;
	private List<Relation> relations;
	private List<Boolean> ans;
	private List<List<AAPair>> var;
	private Node queryList;
	private int it;

	public Database(String string) {
		BufferedReader reader = null;
		LexicalAnalyzer tokenList = null;
		DatalogProgram datalog = null;
		queries = new ArrayList<Relation>();
		relations = new ArrayList<Relation>();
		ans = new ArrayList<Boolean>();
		var = new ArrayList<List<AAPair>>();
		Node tempList;
		Node predList;

		try{
			reader = new BufferedReader(new FileReader(string));
		} catch (IOException e){
			System.out.println("Bad File");
		}
		try {
			tokenList = new LexicalAnalyzer(reader);
		} catch (IOException e) {
			System.out.println("Read failed");
			e.printStackTrace();
		}
		try{
		datalog = new DatalogProgram(tokenList);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		tempList = datalog.getSchemes();
		for(int i = 0; i<tempList.size(); i++){
			Scheme temp = (Scheme) tempList.get(i);
			Schema s = new Schema(temp.getName(),temp.getAttributes());
			Relation r = new Relation(s);
			relations.add(r);
		}
		tempList = datalog.getFacts();
		for(int i = 0; i<tempList.size(); i++){
			Fact temp = (Fact) tempList.get(i);
			for(int j = 0; j<relations.size();j++){
				String str = temp.getName();
				if(relations.get(j).getSchemaName().equals(str)){
					relations.get(j).add(new Tuple(temp.getValue()));
					break;
				}
			}
		}
		
		tempList = datalog.getRules();
		it = 0;
		boolean changed = true;
		while(changed){
			changed = false;
			it++;
			int size;
			if(tempList == null){
				size = 0;
			}else{
				size = tempList.size();
			}
			for(int k = 0;k<size;k++){
				Relation joined = null;
				Rule temp = (Rule) tempList.get(k);
				String name = temp.getName();
				int index = 0;
				for(int j = 0; j<relations.size();j++){
					if(relations.get(j).getSchemaName().equals(name)){
						index = j;
						break;
					}
				}
				Schema s = new Schema(name ,((ArgumentList)temp.get(0)).getValues());
				for(int i = 1; i<temp.size(); i++){
					Predicate pred = (Predicate) temp.get(i);
					String pname = pred.getName();
					List<String> arguments = pred.getChildren();
					List<AAPair> aap = new ArrayList<AAPair>();
					List<Pair> avp = new ArrayList<Pair>();
					List<Integer> keep = new ArrayList<Integer>();
					for(int j = 0; j<arguments.size();j++){
						String str = arguments.get(j);
						if(str.charAt(0)== '\''){
							avp.add(new AVPair(j,str));
						}else{
							int ind = 0;
							if(arguments.subList(0,j).contains(str)){
								ind = arguments.indexOf(str);
								avp.add(new VVPair(ind,j));
							}else{
							aap.add(new AAPair(j,str));
							keep.add(j);
							}
						}
					}
					for(int j = 0; j<relations.size();j++){
						Relation r = new Relation(relations.get(j));
						if(r.getSchemaName().equals(pname)){
//							System.out.println("Before " + r);
							r = (r.select(avp).rename(aap).project(keep));
//    						System.out.println("After " + r);
//							System.out.println("Joined B " + joined);
							joined = r.join(joined);
//							System.out.println("Joined A " + joined.size() + "\n" + joined);
							break;
						}
					}

				}
				if(joined != null){
					joined = joined.project(joined.getSchema().overLap(s).get(0));
					int sizer = relations.get(index).size();
					relations.set(index,relations.get(index).union(joined));
					if(relations.get(index).size() > sizer){
						changed = true;
					}
				}
			}
		}
		

		
		tempList = datalog.getQueries();
		for(int i = 0; i<tempList.size(); i++){
			Query temp = (Query) tempList.get(i);
			String name = temp.getName();
			List<String> arguments = temp.getVaules();
			List<AAPair> aap = new ArrayList<AAPair>();
			List<Pair> avp = new ArrayList<Pair>();
			List<Integer> keep = new ArrayList<Integer>();
			for(int j = 0; j<arguments.size();j++){
				String str = arguments.get(j);
				if(str.charAt(0)== '\''){
					avp.add(new AVPair(j,str));
				}else{
					int ind = 0;
					if(arguments.subList(0,j).contains(str)){
						ind = arguments.indexOf(str);
						avp.add(new VVPair(ind,j));
					}else{
					aap.add(new AAPair(j,str));
					keep.add(j);
					}
				}
			}
			var.add(aap);
			int size = queries.size();
			for(int j = 0; j<relations.size();j++){
				Relation r = relations.get(j);
				if(r.getSchemaName().equals(name)){
					queries.add(r.select(avp).rename(aap).project(keep));
					break;
				}
			}
			if(size > queries.size()){
				ans.add(true);
			}else{
				ans.add(false);
			}
		}
		queryList = tempList;
	}

	public int getIt() {
		return it;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < queryList.size(); i++) {
			str += queryList.get(i).toString();
			Relation r = queries.get(i);
			if (r.size() == 0) {
				str += "No\n";
			} else {
				str += "Yes(" + r.size() + ")\n";
			}
			if (var.get(i).size() == 0) {

			} else {
				str += r.toString();
			}
		}
		return str;
	}

}
