package helloWorldAgent;

import oo2apl.agent.AgentBuilder;
import oo2apl.agent.AgentContextInterface;
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger; 
import oo2apl.plan.builtin.FunctionalPlanSchemeInterface;
import oo2apl.plan.builtin.SubPlanInterface; 
/**
 * This class specifies a builder that can be used by the platform to create HelloWorld agents. 
 * It also specifies a single plan scheme that the agent uses. This plan scheme is triggered by 
 * the example HelloWorldExternalTrigger triggers and when executed will greet the addressee of 
 * the trigger using the greeting that was given when the agent's builder was created. 
 * 
 * It is also possible to specify agents using an AgentComponentFactory. However, this is a bit more 
 * complicated and usually the Builder pattern, as applied here, is more concise. 
 * 
 * @author Bas Testerink
 */
public final class HelloWorldAgent {
	/** Create a new builder. The greeting will be used by the agent to greet people.  */
	public static final AgentBuilder makeBuilder(final String greeting){
		return new AgentBuilder()
				.addContext(new HelloWorldContext(greeting))
				.addExternalTriggerPlanScheme(makeGreetingPlanScheme());
	}
	
	/** Create the plan scheme for the agent. The FunctionalPlanSchemeInterface is a short notation that allows the 
	 * programmer to specify a condition on triggers and the appropriate plan when the plan scheme is triggered. For instance, 
	 * this plan scheme is triggered when the trigger is of the type HelloWorldExternalTrigger. This means that the if a trigger is of 
	 * this type, and this rule fires, then the trigger is consumed and the plan is executed. The plan itself is a lamba function that takes 
	 * a PlanToAgentInterface and has no return value. The PlanToAgentInterface exposes all the regular 2APL functionalities (adopt goals, plans, etc.) and 
	 * allows the agent to retrieve contexts (similar to the concept of belief base). An example of how a context is retrieved is provided here.*/
	private static final FunctionalPlanSchemeInterface makeGreetingPlanScheme(){
		return (Trigger trigger, AgentContextInterface contextInterface) -> {
			if(trigger instanceof HelloWorldExternalTrigger){
				HelloWorldExternalTrigger helloWorldTrigger = (HelloWorldExternalTrigger) trigger;
				return (PlanToAgentInterface planInterface) -> {
					// Obtain the contexts that are needed in this plan
					HelloWorldContext context = planInterface.getContext(HelloWorldContext.class);
					
					// Perform the plan
					System.out.println(context.getMyGreeting()+", "+helloWorldTrigger.addressee); 
				};
			} else return SubPlanInterface.UNINSTANTIATED;
		};
	}
}
