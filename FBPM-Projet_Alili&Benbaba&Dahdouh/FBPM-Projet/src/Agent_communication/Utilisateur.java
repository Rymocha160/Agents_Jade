package Agent_communication;

import java.util.ArrayList;



public class Utilisateur {

	private String nom ; 
	private String prenom ; 
	private String domaine;
	private String mot_de_passe;
    ArrayList<file> messagerecu = new ArrayList<file>();

	//cnstructeur
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Utilisateur(String nom, String prenom, String domaine, String mot_de_passe, ArrayList<file> messagerecu) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.domaine = domaine;
		this.mot_de_passe = mot_de_passe;
		this.messagerecu = messagerecu;
	}



	//getters et setters
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}



	public ArrayList<file> getMessagerecu() {
		return messagerecu;
	}



	public void setMessagerecu(ArrayList<file> messagerecu) {
		this.messagerecu = messagerecu;
	}
	
	 
	
	

}
