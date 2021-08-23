package LS_Condition;

import CFG_Condition.Is_Builder;
import LS_CFG.Node_LS;

public class Is_Builder_LS extends Is_Builder implements Node_LS {

	public Is_Builder_LS() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual==0)this.sample(budget);
	}
}
