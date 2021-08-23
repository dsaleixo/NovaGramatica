package LS_CFG;

import CFG.B;
import CFG.If_B_then_S;
import CFG.S;

public class If_B_then_S_LS extends If_B_then_S implements Node_LS {

	public If_B_then_S_LS() {
		// TODO Auto-generated constructor stub
		super();
	}

	public If_B_then_S_LS(B b, S s) {
		super(b, s);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		B_LS b = new B_LS();
		b.sample(1);
		this.setB(b);
		S_LS s1 = new S_LS();
		s1.sample(budget-2);
		this.setS(s1);
		
	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		Node_LS n1 = (Node_LS)this.getB();
		Node_LS n2 = (Node_LS)this.getS();
		return 1 + n1.countNode()+ n2.countNode();
	}

	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<1)this.sample(budget);
		else {
			node_atual-=1;
			Node_LS n = (Node_LS)this.getB();
			int conutN = n.countNode();
			if(conutN<node_atual) n.mutation(node_atual, budget);
			else {
				Node_LS n2 = (Node_LS)this.getS();
				n2.mutation(node_atual, budget);
			}
		}
	}
	
	
}
