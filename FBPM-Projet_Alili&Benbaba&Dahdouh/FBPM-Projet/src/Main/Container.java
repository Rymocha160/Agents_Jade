package Main;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class Container {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime rt=Runtime.instance();
			ProfileImpl pc=new ProfileImpl(false);
			pc.setParameter(ProfileImpl.MAIN_PORT, "localhost");
			//pc.setParameter(ProfileImpl.MAIN_HOST, "1096");
			AgentContainer ctr= rt.createAgentContainer(pc);
			AgentController agentController=ctr.createNewAgent("Infrmaticien1", "Domaine.Informatique", new Object[] {});
			agentController.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
