package LS_Actions;

import java.util.List;
import java.util.Random;

import CFG.OpponentPolicy;
import CFG.TargetPlayer;
import CFG_Actions.moveToUnit;
import LS_CFG.ChildC_LS;
import LS_CFG.Node_LS;

public class moveToUnit_LS extends moveToUnit implements Node_LS,ChildC_LS {

	public moveToUnit_LS() {
		// TODO Auto-generated constructor stub
	}

	public moveToUnit_LS(TargetPlayer tagetplayer, OpponentPolicy oP) {
		super(tagetplayer, oP);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		TargetPlayer tagetplayer = new TargetPlayer();
		OpponentPolicy oP = new OpponentPolicy();
		
		List<String> l1 = tagetplayer.Rules();
		Random gerador = new Random();
		int g = gerador.nextInt(l1.size());
		tagetplayer.setValue(l1.get(g));
		this.setTagetplayer(tagetplayer);
		
		List<String> l2 = oP.Rules();
		g = gerador.nextInt(l2.size());
		oP.setOpponentPolicy(l2.get(g));
		this.setOP(oP);
	}

	@Override
	public int countNode() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public void mutation(int node_atual, int budget) {
		// TODO Auto-generated method stub
		if(node_atual<0)this.sample(budget);
	}
	
}
