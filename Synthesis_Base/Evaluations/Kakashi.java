package Evaluations;

import CFG.Control;
import CFG.Factory;
import CFG.Node;
import rts.GameState;
import util.Pair;

public class Kakashi implements Evaluation {

	double melhor=-1;
	public Kakashi() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evaluation(GameState gs, Pair<Node, Node> ais, int max_cycle,Factory f) throws Exception {
		// TODO Auto-generated method stub
		double r = Sharingan.run(0, ais.m_a, gs, max_cycle,f);
		
	
		if(r>melhor) {
			
			//System.out.println(ais.m_a.translate()+" "+r+"  "+this.best.m_a.translate());
	
			String sss = Control.salve(ais.m_a);
			System.out.println(r+" Atual="+sss);
			melhor = r;
			return true;
		}
		return false;
	}

	@Override
	public Pair<Node, Node> getAIS() {
		// TODO Auto-generated method stub
		return null;
	}

}
