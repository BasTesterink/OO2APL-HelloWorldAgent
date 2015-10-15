package helloWorldAgent;

import oo2apl.agent.Context;
 /**
  * Contexts provide for an agent all the necessary data that is needed for decision 
  * making and executing actions in the environment. Hence it is wise to design a new 
  * context together with a set of plan schemes and triggers for each environment 
  * module with which the agent interacts, or specific task.
  *  <p>
  * The hello world agent only decides on which greeting it uses. For this it uses 
  * the greeting that was provided upon its creation. 
  * @author Bas Testerink
  */
public final class HelloWorldContext implements Context {
	/** The greeting that this agent uses. */
	private final String myGreeting;
	
	public HelloWorldContext(final String myGreeting){
		this.myGreeting = myGreeting;
	}
	
	public final String getMyGreeting(){  
		return this.myGreeting; 
	}
}
