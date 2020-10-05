package Domaine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import Agent_communication.Utilisateur;
import Agent_communication.file;
import Agent_communication.users;
import Interface.Index;
import Interface.Inscription;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ControllerException;

@SuppressWarnings("serial")
public class AgentInfo extends Agent {

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
		AgentInfo.mesAgents = mesAgents;
	}

	public AgentInfo(Utilisateur user) {
		super();
		this.user = user;
	}

	Utilisateur user;

	public AgentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	// getter et setter
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	// initialiser agent
	@Override
	protected void setup() {

		// register in yellw page DF
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Informaticien");
		sd.setName("Informaticien Agent");
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

		System.out.println("Je suis Informaticien : " + this.getAID().getName());
		// comportement
		addBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {

				/*ACLMessage messageRecu = receive();
				if (messageRecu != null) {
					ArrayList<file> FilesListe = new ArrayList<file>();
					file f;
					try {
						f = (file) messageRecu.getContentObject();
						FilesListe.add(f);
						System.out.println("nom de fichier " + f.getFileName());
						user.getMessagerecu().add(f);
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else {
					block();
				}
				/*
				 * ACLMessage aclMessage = receive(); if (aclMessage != null) {
				 * System.out.println("Réception d'un nouveau message "); // contenu de message
				 * System.out.println("Contenu de message reçu : " + aclMessage.getContent());
				 * System.out.println( "Acte de communication : " +
				 * ACLMessage.getPerformative(aclMessage.getPerformative()));
				 * System.out.println("Langage : " + aclMessage.getLanguage());
				 * System.out.println("Onthologie : " + aclMessage.getOntology()); if
				 * (aclMessage.getOntology().equals("informatique")) {
				 * System.out.println("Onthologie Informaique"); // repondre l'emmeteur
				 * ACLMessage reponse = aclMessage.createReply();
				 * reponse.setContent("Message bien reçu"); send(reponse); } else {
				 * System.out.println("Onthologie autre");
				 * 
				 * }
				 * 
				 * } else {block();}
				 */

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

				/*
				 * ACLMessage message = new ACLMessage(ACLMessage.INFORM);
				 * message.setContent("eeeeeeeee"); message.addReceiver(new AID("rma",
				 * AID.ISLOCALNAME)); send(message);
				 */

			}
		});

	}

	@Override
	protected void beforeMove() {
		try {
			System.out.println("Avant migration de l'agent" + this.getAID().getName());
			System.out.println("de container" + this.getContainerController().getContainerName());
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void afterMove() {
		try {
			System.out.println("Aprés migration de l'agent" + this.getAID().getName());
			System.out.println("vers container" + this.getContainerController().getContainerName());
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// lors la migration entre conteneurs
	@Override
	public void doMove(Location loca) {
		System.out.println("Migration vers " + loca.getName());

	}

	// lors la destruction de l'agent
	@Override
	protected void takeDown() {
		System.out.println("l'agent " + this.getAID().getName() + " va mourir");
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.takeDown();

	}

}
