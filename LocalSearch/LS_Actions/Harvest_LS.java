package LS_Actions;

import java.util.List;
import java.util.Random;

import CFG.N;
import CFG_Actions.Harvest;
import LS_CFG.ChildC_LS;
import LS_CFG.Node_LS;

public class Harvest_LS extends Harvest implements Node_LS,ChildC_LS  {

	public Harvest_LS() {
		// TODO Auto-generated constructor stub
	}

	public Harvest_LS(N n) {
		// TODO Auto-generated constructor stub
		super(n);
	}
	
	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		N n = new N();
		Random gerador = new Random();
		List<String> l3 = n.Rules();
		int g = gerador.nextInt(l3.size());
		n.setN(l3.get(g));
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
		if(node_atual<1)this.sample(budget);
	}
	
}
