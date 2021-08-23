package LS_CFG;

import CFG.B;
import CFG.If_B_then_S_else_S;
import CFG.S;

public class If_B_then_S_else_S_LS extends If_B_then_S_else_S implements Node_LS {

	public If_B_then_S_else_S_LS() {
		// TODO Auto-generated constructor stub
		super();
	}

	public If_B_then_S_else_S_LS(B b, S then_S, S else_S) {
		super(b, then_S, else_S);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		B_LS b = new B_LS();
		int aux = budget/2 -1;
		b.sample(1);
		this.setB(b);
		S_LS s1 = new S_LS();
		s1.sample(aux);
		this.setThen_S(s1);
		S_LS s2 = new S_LS();
		s2.sample(aux);
		this.setElse_S(s2);

	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		Node_LS n1 = (Node_LS)this.getB();
		Node_LS n2 = (Node_LS)this.getThen_S();
		Node_LS n3 = (Node_LS)this.getElse_S();
		return 1 + n1.countNode()+ n2.countNode() + n3.countNode();
	}

	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<1)this.sample(budget);
		else {
			node_atual-=1;
			Node_LS n = (Node_LS)this.getB();
			Node_LS n2 = (Node_LS)this.getThen_S();
			Node_LS n3 = (Node_LS)this.getElse_S();
			
			
			int c1 =n.countNode();
			int c2 =n.countNode();
			int c3 =n.countNode();
			
			if(c1 < node_atual) {
				 n.mutation(node_atual, budget);
			}else if(c1 + c2 < node_atual) {
				n2.mutation(node_atual, budget);
			}else  {
				n3.mutation(node_atual, budget);
			}
			
		}
	}

}
