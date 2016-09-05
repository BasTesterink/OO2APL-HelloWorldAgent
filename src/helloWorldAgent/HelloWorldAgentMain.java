package helloWorldAgent;

import java.util.Scanner;

import oo2apl.agent.ExternalProcessToAgentInterface;
import oo2apl.defaults.messenger.DefaultMessenger; 
import oo2apl.platform.AdminToPlatformInterface;
import oo2apl.platform.Platform; 
/**
 * The HelloWorld agent is a demo application for OO2APL (object-oriented 2APL). 
 * <p>
 * This application shows how to create a platform, add an agent component factory and 
 * create a new agent. The agent itself greets any person who's name you enter when 
 * the agent is running. 
 * 
 * @author Bas Testerink
 *
 */
public final class HelloWorldAgentMain {
	public static void main(String[] args){
		// Create the platform. Returns an interface to the platform to register new factories and create new agents
		AdminToPlatformInterface adminInterface = Platform.newPlatform(1, new DefaultMessenger()); 
		
		// Create a new agent. The platform will return an interface to the agent so that we can send it triggers
		ExternalProcessToAgentInterface agent = adminInterface.newAgent(HelloWorldAgent.makeBuilder("Hi"));
		
		// Our external process to interact with the agent
		Scanner scanner = new Scanner(System.in);
		String input = ""; 
		System.out.println("Enter names: (type 'halt' to exit)");
		while(!input.equals("halt")){
			input = scanner.next();
			if(!input.equals("halt")){
				// This is how you interact with an agent. You send external triggers through the ExternalProcessToAgentInterface
				agent.addExternalTrigger(new HelloWorldExternalTrigger(input));		
			}
		}		
		scanner.close();
		
		// Halt the platform to release resources
		adminInterface.haltPlatform(); 
	} 
}