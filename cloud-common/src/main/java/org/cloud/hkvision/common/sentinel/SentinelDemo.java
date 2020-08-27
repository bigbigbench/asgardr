package org.cloud.hkvision.common.sentinel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

public class SentinelDemo {

	public static void main(String[] args) throws InterruptedException {
		initFlowRules();
		while(true) {
			Thread.sleep(100);
			Entry entry = null;
			try {
				entry = SphU.entry("HelloWord");
				/********  start  ********/
				System.out.println("hello world");
				/********  end  ********/
			}catch(BlockException e1) {
				System.out.println("block!");
			}finally {
				if(entry != null) {
					entry.exit();
				}
			}
		}
	}
	
	public static void initFlowRules() {
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		rule.setResource("HelloWord");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setCount(20);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}























































