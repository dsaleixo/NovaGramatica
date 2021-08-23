package EvaluateGameState;

import Oraculo.EstadoAcoes;
import rts.GameState;

public class Super implements EvaluateGS {

	Perfect perfect;
	CabocoDagua cd;
	CabocoDagua2 cd2;
	LTD3 ltd3;
	
	
	public Super(EstadoAcoes EAs,int play) {
		// TODO Auto-generated constructor stub
		perfect = new Perfect(EAs.gss);
		cd = new CabocoDagua(EAs.gss,play);
		cd2 = new CabocoDagua2(EAs,play);
		ltd3 = new LTD3();
	}

	@Override
	public void evaluate(GameState gs, int play) {
		// TODO Auto-generated method stub
		perfect.evaluate(gs, play);
		cd.evaluate(gs, play);
		cd2.evaluate(gs, play);
		ltd3.evaluate(gs, play);

	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		double cont=0;
		cont += perfect.getValue();
		cont+= cd.getValue();
		cont+= cd2.getValue();
		cont+= ltd3.getValue();
		
		return cont/4;
	}

	@Override
	public void Resert() {
		// TODO Auto-generated method stub
		perfect.Resert();
		cd.Resert();
		cd2.Resert();
		ltd3.Resert();
	}

}
