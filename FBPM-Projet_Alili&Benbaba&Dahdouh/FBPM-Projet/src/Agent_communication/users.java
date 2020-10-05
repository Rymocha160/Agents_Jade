/**
 * 
 */
package Agent_communication;
import java.util.ArrayList;

import Domaine.AgentInfo;

import Domaine.Mathematique;


/**
 * @author Administrateur
 *
 */
public class users {
	//public Vector<Utilisateur> a=new Vector<Utilisateur>();
	//private static final ArrayList<Utilisateur> Users= new ArrayList <Utilisateur> ();
	private static ArrayList<AgentInfo> info = new ArrayList<AgentInfo>();
	private static ArrayList<Mathematique> math = new ArrayList<Mathematique>();

	public static int nombre_info=0;
	public static int nombre_math=0;
	
	
	public static ArrayList<AgentInfo> getInfo() {
		return info;
	}

	public static void setInfo(ArrayList<AgentInfo> info) {
		users.info = info;
	}

	public static ArrayList<Mathematique> getMath() {
		return math;
	}

	public static void setMath(ArrayList<Mathematique> math) {
		users.math = math;
	}

	
}
