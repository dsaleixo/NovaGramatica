package LS_Condition;

import java.util.List;
import java.util.Random;

import CFG.N;
import CFG.Type;
import CFG_Condition.OpponentHasNumberOfUnits;
import LS_CFG.Node_LS;

public class OpponentHasNumberOfUnits_LS extends OpponentHasNumberOfUnits implements Node_LS {

	public OpponentHasNumberOfUnits_LS() {
		// TODO Auto-generated constructor stub
	}

	public OpponentHasNumberOfUnits_LS(Type type, N n) {
		super(type, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		Type type = new Type();
		N n = new N();
		
		List<String> l1 = type.Rules();
		Random gerador = new Random();
		int g = gerador.nextInt(l1.size());
		type.setType(l1.get(g));
		this.setType(type);
		
		List<String> l2 = n.Rules();
		g = gerador.nextInt(l2.size());
		n.setN(l2.get(g));
		this.setN(n);
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
