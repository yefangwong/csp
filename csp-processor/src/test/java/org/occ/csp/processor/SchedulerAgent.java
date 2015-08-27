package org.occ.csp.processor;

import java.io.Serializable;
import java.util.Calendar;

import com.hongfang.ckernel.ia.CIAgent;
import com.hongfang.ckernel.ia.CIAgentEvent;
import com.hongfang.ckernel.ia.CIAgentState;

public class SchedulerAgent extends CIAgent implements Serializable {
	protected int interval = 6000;
	
	public SchedulerAgent() {
		this("Scheduler");
	}
	
	public SchedulerAgent(String name) {
		super(name);
	}
	
	@Override
	public String getTaskDescription() {
		return "Repeating timer: interval = " + (interval / 1000);
	}

	@Override
	public void initialize() {
		setSleepTime(interval);
		setAsyncTime(interval);
		setState(CIAgentState.INITIATED);
	}

	@Override
	public void process() {
		System.out.println("process...");
	}

	public void processCIAgentEvent(CIAgentEvent e) {}
	
	@Override
	public void processTimerPop() {
		System.out.println("processTimerPop...");
		String timeStamp = Calendar.getInstance().getTime().toString();
		notifyCIAgentEventListeners(new CIAgentEvent(this, "notify", timeStamp));
	}

	public static void main(String[] args) {
		CIAgent agent = new SchedulerAgent();
		agent.initialize();
		agent.startAgentProcessing();
	}
}
