package helloWorldAgent;

import oo2apl.agent.Trigger;
/**
 * This trigger tells the agent that it should greet a user. 
 * 
 * @author Bas Testerink
 */
public final class HelloWorldExternalTrigger implements Trigger {
	public final String addressee;
	
	public HelloWorldExternalTrigger(final String addressee){
		this.addressee = addressee;
	}
	
	public final String getAddressee(){ return this.addressee; }
}
