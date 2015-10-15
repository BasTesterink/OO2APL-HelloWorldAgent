package helloWorldAgent;
 
import java.util.ArrayList;
import java.util.List;

import oo2apl.agent.AgentType;
import oo2apl.agent.Context;
import oo2apl.agent.ContextArguments;
import oo2apl.agent.ContextContainer;
import oo2apl.defaults.DefaultAgentComponentFactory;
import oo2apl.plan.PlanScheme;
import oo2apl.plan.PlanSchemeBase;
import oo2apl.plan.PlanSchemeBaseArguments;
 /**
  * This is a demo agent component factory. Such a factory has to create for a specific 
  * type of agents the contexts, plan scheme bases, deliberation cycles, and ID's. This 
  * demo factory extends the default agent component factory which produces a default 
  * agent ID for each agent (which is specified by the agent's type + instantiation number 
  * of the factory. Hence this ID is NOT unique if multiple factories produce agents of 
  * the same type) and a default deliberation cycle that is conform the 2APL standard. 
  * 
  * @author Bas Testerink
  */
public final class HelloWorldAgentComponentFactory extends DefaultAgentComponentFactory {
	/** The type of agents that this factory produces. */
	private final AgentType myType;
	
	/** Agents from this factory share the same plan scheme base. */
	private final PlanSchemeBase planSchemeBase;
	
	public HelloWorldAgentComponentFactory(){
		// Make the new type
		this.myType = new HelloWorldAgentType();

		// Initiate plan scheme lists for the plan scheme base
		List<PlanScheme> goalPlanSchemes = new ArrayList<>();
		List<PlanScheme> internalTriggerPlanSchemes = new ArrayList<>();
		List<PlanScheme> externalTriggerPlanSchemes = new ArrayList<>();
		List<PlanScheme> messagePlanSchemes = new ArrayList<>();
		
		// Add the relevant schemes to the appropriate plan scheme lists
		externalTriggerPlanSchemes.add(new HelloWorldExternalTriggerPlanScheme());
		
		// Create the plan scheme base
		this.planSchemeBase = new PlanSchemeBase(goalPlanSchemes, internalTriggerPlanSchemes, externalTriggerPlanSchemes, messagePlanSchemes);
	}
	
	public final AgentType getAgentType(){ 
		return this.myType;
	}

	/**
	 * The hello world agent only has one context, which is the HelloWorldContext. 
	 * To get this context  through interfaces to the agent that allow context retrieval, 
	 * use x.getContext(HelloWorldContext.class) where x is for instance a PlanToAgentInterface. 
	 */
	public final ContextContainer produceContextContainer(final ContextArguments contextArgs){
		if(contextArgs instanceof HelloWorldContextArguments){
			// Make contexts
			Context context = new HelloWorldContext(((HelloWorldContextArguments)contextArgs).getGreeting());
			
			// Make the container and add all contexts
			ContextContainer result = new ContextContainer();
			result.addContext(context);
			return result;
		} else throw new IllegalArgumentException("HelloWorldContextArguments required for the HelloWorldContextContainer production.");
	}

	/**
	 * For the hello world agent no new plan scheme base is produced because all agents of this type 
	 * share the same base. Therefore the plan scheme base that is constructed at the constructor call 
	 * of this factory is returned. 
	 */
	public final PlanSchemeBase producePlanSchemeBase(final PlanSchemeBaseArguments planSchemeBaseArgs){ 
		return this.planSchemeBase; 
	}
	 
	/**
	 * The hello world agent context is initiated with the greeting that it will use towards 
	 * users. Therefore the context arguments for the hello world factory force the user 
	 * to provide the greeting string.
	 * 
	 * @param greeting The greeting that the agent will use. For instance "Hi" or "Hallo"
	 * @return
	 */
	public final HelloWorldContextArguments produceContextArguments(final String greeting){
		return new HelloWorldContextArguments(greeting);
	}
	
	/**
	 * The context arguments provide all necessary data that is needed to produce the 
	 * contexts that agents from this factory use. 
	 * 
	 * @author Bas Testerink
	 */
	public final class HelloWorldContextArguments implements ContextArguments {
		/** The greeting that the agent will use. */
		private final String greeting;
		
		private HelloWorldContextArguments(final String greeting){
			this.greeting = greeting;
		}

		public final String getGreeting(){ return this.greeting; }
	} 
}