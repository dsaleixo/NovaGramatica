package LS_CFG;

import java.util.Random;

import CFG.S;
import CFG.S_S;

public class S_S_LS extends S_S implements Node_LS {

	public S_S_LS() {
		// TODO Auto-generated constructor stub
	}

	public S_S_LS(S leftS, S rightS) {
		super(leftS, rightS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		
		
			S_LS s1 = new S_LS();
			s1.sample(budget/2);
			this.setLeftS(s1);
			S_LS s2 = new S_LS();
			s2.sample(budget/2);
			this.setRightS(s2);
		
	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		Node_LS n1 = (Node_LS)this.getLeftS();
		Node_LS n2 = (Node_LS)this.getRightS();
		return 1 + n1.countNode()+ n2.countNode();
	}

	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<1) {
			Random gerador = new Random();
			float g = gerador.nextFloat();
			if(g<0.90) {
				this.sample(budget);
			}else {
				S s1 = (S) this.getLeftS();
				S s2 = (S) this.getRightS();
				this.setRightS(s2);
				this.setLeftS(s1);
			}
		}
		else {
			node_atual-=1;
			Node_LS n = (Node_LS)this.getLeftS();
			if(node_atual<n.countNode()) {
				n.mutation(node_atual, budget);
			}
			else {
				Node_LS n2 = (Node_LS)this.getRightS();
				
				n2.mutation(node_atual, budget);
			}
			
			
			
		}
	}

}
