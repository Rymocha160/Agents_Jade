package Domaine;

import java.io.IOException;
import java.util.Vector;

import Agent_communication.Utilisateur;
import Agent_communication.file;
import Agent_communication.users;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Mathematique extends Agent{
	
	public Mathematique(Utilisateur user) {
		super();
		this.user = user;
	}

	String nom = "";
	static file Files = new file();

	public file getFiles() {
		return Files;
	}

	public static void setFiles(file file) {
		Files = file;
	}

	private static Vector<AID> mesAgents = new Vector<AID>();
	public static AID rec = null;

	public static Vector<AID> getMesAgents() {
		return mesAgents;
	}

	public void setMesAgents(Vector<AID> mesAgents) {
		Mathematique.mesAgents = mesAgents;
	}



	Utilisateur user;

	

	// getter et setter
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	@Override
	protected void setup() {
		// register in yellw page DF
				DFAgentDescription dfd = new DFAgentDescription();
				dfd.setName(getAID());
				ServiceDescription sd = new ServiceDescription();
				sd.setType("Mathématicien");
				sd.setName("Mathématicien Agent");
				dfd.addServices(sd);
				try {
					DFService.register(this, dfd);
				} catch (FIPAException fe) {
					fe.printStackTrace();
				}
				addBehaviour(new OneShotBehaviour() {

					@SuppressWarnings("deprecation")
					@Override
					public void action() {
						DFAgentDescription template = new DFAgentDescription();
						ServiceDescription sd = new ServiceDescription();
						// sd.setType("Analiste");
						template.addServices(sd);
						try {
							DFAgentDescription[] result = DFService.search(myAgent, template);
							for (int i = 0; i < result.length; ++i) {
								mesAgents.add(result[i].getName());
								System.out.println("element " + mesAgents.elementAt(i));
							}
							for (int i = 0; i < mesAgents.size(); i++) {
								// System.out.println("hiiiiiiiiiiiiii");

								if (!mesAgents.elementAt(i).getName().equals(myAgent.getAID().getName())) {
									System.out.println("mesAgents " + mesAgents.elementAt(i));

									System.out.println("monAgent " + mesAgents.elementAt(i));
									rec = new AID(myAgent.getAID().getName());
									System.out.println("monAgent " + rec);

								}
							}

						} catch (FIPAException fe) {
							fe.printStackTrace();
						}
					}
				});
				// Object[] args=getArguments();

				System.out.println("Je suis Mathématicien : " + this.getAID().getName());
				// comportement
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage message = new ACLMessage(ACLMessage.INFORM);
				if (Files.getF() != (null) ) {
					System.out.println("file " + Files.getF());
					try {
						message.setContentObject(Files);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i = 0; i < users.getInfo().size(); i++) {
						if (!users.getInfo().get(i).getUser().getNom().equals(myAgent.getAID().getName()))
							nom = users.getInfo().get(i).getUser().getNom();
						// System.out.println("cccccc "+nom);

					}
					message.addReceiver(new AID(nom, AID.ISLOCALNAME));
					send(message);
					Files.setF(null);
				}
				
			}
		});
	}

}
