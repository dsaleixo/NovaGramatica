package LS_Condition;

import CFG_Condition.OpponentHasUnitThatKillsUnitInOneAttack;
import LS_CFG.Node_LS;

public class OpponentHasUnitThatKillsUnitInOneAttack_LS extends OpponentHasUnitThatKillsUnitInOneAttack
		implements Node_LS {

	public OpponentHasUnitThatKillsUnitInOneAttack_LS() {
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
