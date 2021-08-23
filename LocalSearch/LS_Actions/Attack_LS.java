package LS_Actions;

import java.util.List;
import java.util.Random;

import CFG.OpponentPolicy;
import CFG_Actions.Attack;
import LS_CFG.ChildC_LS;
import LS_CFG.Node_LS;

public class Attack_LS extends Attack implements Node_LS,ChildC_LS {

	public Attack_LS() {
		// TODO Auto-generated constructor stub
	}

	public Attack_LS(OpponentPolicy oP) {
		super(oP);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		OpponentPolicy op = new OpponentPolicy();
		List<String> l = op.Rules();
	
		Random gerador = new Random();
		int g = gerador.nextInt(l.size());
		
		op.setOpponentPolicy(l.get(g));
		this.setOP(op);
		
		
		
	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<1)this.sample(budget);
	}

}
