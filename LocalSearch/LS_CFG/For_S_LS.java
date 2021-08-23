package LS_CFG;

import java.util.Random;

import CFG.For_S;
import CFG.S;

public class For_S_LS extends For_S implements Node_LS {

	public For_S_LS() {
		// TODO Auto-generated constructor stub
		super();
	}

	public For_S_LS(S child) {
		super(child);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int buget) {
		// TODO Auto-generated method stub
		S_LS s = new S_LS();
		s.sample(buget-1);
		this.setChild(s);

	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		Node_LS n2 = (Node_LS)this.getChild();
		return 1 + n2.countNode();
	}

	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<0)this.sample(budget);
		else {
			node_atual-=1;
			Node_LS n2 = (Node_LS)this.getChild();
			
			n2.mutation(node_atual, budget);
		}
	}

	public Node_LS sorteiaFilho(int budget) {
		// TODO Auto-generated method stub
		int op=0;
		if(budget>=0)op=1;
		if(budget>=1)op=3;
		if(budget>=3)op=4;
		if(budget>=4)op=5;
		
		
		if(op==0)return  new Empty_LS();
		
		Random gerador = new Random();
		
		int g = gerador.nextInt(op);

		if(g==0) return new C_LS();
		if(g==1) return new S_S_LS();
		if(g==2) return new For_S_LS();
		if(g==3) return new If_B_then_S_LS();
		if(g==4) return new If_B_then_S_else_S_LS();

		return  new Empty_LS();
		
		
	}
	
	

}
