package LS_CFG;

import java.util.Random;

import CFG.C;
import CFG.ChildC;
import LS_Actions.*;

public class C_LS extends C implements Node_LS, NoTerminal_LS {

	public C_LS() {
		// TODO Auto-generated constructor stub
	}

	public C_LS(ChildC childC) {
		super(childC);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node_LS sorteiaFilho(int budget) {
		// TODO Auto-generated method stub
		
		Random gerador = new Random();
		
		int g = gerador.nextInt(7);

		if(g==0) return new Attack_LS();
		if(g==1) return new Build_LS();
		if(g==2) return new Harvest_LS();
		if(g==3) return new Idle_LS();
		if(g==4) return new MoveAway_LS();
		if(g==5) return new moveToUnit_LS();
		if(g==6) return new Train_LS();
		
		
		return null;
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		Node_LS child = this.sorteiaFilho(budget);
		child.sample(budget );
		this.setChildC((ChildC)child);
	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		Node_LS n2 = (Node_LS)this.getChildC();
		return 3 + n2.countNode();
	}

	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<3)this.sample(budget);
		else {
			node_atual-=3;
			Node_LS n2 = (Node_LS)this.getChildC();
			
			n2.mutation(node_atual, budget);
		}
	}

	

}
