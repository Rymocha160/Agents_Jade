package Main;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class Main_Container {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Runtime rt=Runtime.instance();
			Properties p= new Properties ();
			p.setProperty("gui", "true");
		//	java jade.Boot -port 1097 -gui
			//p.setProperty(Profile.MAIN_PORT, "1096");
			ProfileImpl pc=new ProfileImpl(p);
			//pc.setParameter(ProfileImpl.MAIN_PORT, "localhost");

			AgentContainer ctr= rt.createMainContainer(pc);
		//System.out.print("koko\n\n");
			ctr.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
