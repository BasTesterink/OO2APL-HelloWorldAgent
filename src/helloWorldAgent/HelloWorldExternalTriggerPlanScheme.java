package helloWorldAgent;

import oo2apl.agent.AgentContextInterface; 
import oo2apl.agent.PlanToAgentInterface;
import oo2apl.agent.Trigger; 
import oo2apl.plan.Plan;
import oo2apl.plan.PlanExecutionError;
import oo2apl.plan.PlanScheme;
/**
 * Plan schemes implement the decision logic of agents. This plan scheme is triggered 
 * by a newly entered name which comes in as a HelloWorldExternalTrigger.
 * 
 * @author Bas Testerink
 */
public final class HelloWorldExternalTriggerPlanScheme extends PlanScheme {
	/**
	 * This plan scheme is instantiated iff the provided trigger is of the type 
	 * HelloWorldExternalTrigger. 
	 */
	public Plan instantiate(final Trigger trigger, final AgentContextInterface contextInterface){
		if(trigger instanceof HelloWorldExternalTrigger){
			return new HelloWorldExternalTriggerPlan(((HelloWorldExternalTrigger)trigger).getAddressee());
		}
		return null;
	}
	
	/**
	 * The actual plan. The plan itself consists upon picking the greeting and 
	 * then greeting the user. 
	 * 
	 * @author Bas Testerink
	 */
	public final class HelloWorldExternalTriggerPlan extends Plan {
		/** Person to be greeted */
		private final String addressee;
		
		public HelloWorldExternalTriggerPlan(final String addressee){
			this.addressee = addressee;
		}
	 
		public final void execute(final PlanToAgentInterface planInterface)
				throws PlanExecutionError {
			// Obtain the contexts that are needed in this plan
			HelloWorldContext context = planInterface.getContext(HelloWorldContext.class);
			
			// Perform the plan
			System.out.println(context.getMyGreeting()+", "+this.addressee); 
			
			// Determine whether the next deliberation cycle the plan should be executed 
			// again. If not, then set the finished flag to true. 
			setFinished(true);
		}
	} 
}
