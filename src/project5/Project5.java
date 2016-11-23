//REQUIRED CLASS - KEEP THIS FILE IN THE 'project5' DIRECTORY WITH 'package project5'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project5;

public class Project5
{
	public static String body(String[] args){
		Database db = new Database(args[0]);
		String output = db.toString();
		System.out.println("Schemes populated after "+ db.getIt() +" passes through the Rules.");
		return output +"\nDone!";
	}
}
