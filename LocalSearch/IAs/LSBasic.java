package IAs;

import java.util.Random;

import AIs.Interpreter;
import CFG.Empty;
import CFG.Factory;
import CFG.Node;
import CFG.S;
import EvaluateGameState.CabocoDagua;
import EvaluateGameState.Perfect;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import Evaluations.Evaluation;

import Evaluations.SimplesEvaluations;
import LS_CFG.Empty_LS;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import LS_CFG.S_LS;
import ai.abstraction.LightRush;
import ai.core.AI;
import ajuda.ScriptsFactory;
import rts.GameState;
import rts.units.UnitTypeTable;
import util.Pair;

public class LSBasic implements Search {


	Factory f;
	boolean use_cleanr;
	AI adv;
	Playout playout;
	public LSBasic() {
		// TODO Auto-generated constructor stub
		
		f = new FactoryLS();
		use_cleanr = true;
		Node_LS n =new S_LS(new Empty_LS());
		UnitTypeTable utt = new UnitTypeTable();
		this.adv = new Interpreter(utt,n);
		this.playout = new SimplePlayout();
	}
	
	public LSBasic(boolean clear,AI adv,Playout playout) {
		// TODO Auto-generated constructor stub
		
		this.f = new FactoryLS();
		this.use_cleanr = clear;
		this.adv = adv;
		this.playout= playout;
	}

	public boolean accept(Pair<Double,Double> v1 ,Pair<Double,Double>  v2) {
		if(v2.m_a>v1.m_a)return true;
	
		boolean aux = Math.abs(v2.m_a - v1.m_a) <0.1;
		if(aux && v2.m_b > v1.m_b) return true;
		return false;
	}
	
	Pair<Double,Double> Avalia(GameState gs, int max_cicle,Node_LS n) throws Exception{
		UnitTypeTable utt = new UnitTypeTable();
		AI ai = new Interpreter(utt,n);
		return this.playout.run(gs, 0, max_cicle, ai, adv, false);
		
	}
	
	boolean stop(Pair<Double,Double> v1 ) {
		return false;
	}
	
	@Override
	public Node run(GameState gs, int max_cicle) throws Exception {
		// TODO Auto-generated method stub
		Node_LS best = new S_LS(new Empty_LS());
		Pair<Double,Double> v = new Pair<>(-1.0,-1.0);
		
		Random r =new Random();
	
		
		long Tini = System.currentTimeMillis();
		long paraou = System.currentTimeMillis()-Tini;
		while( (paraou*1.0)/1000.0 <3600) {
			Node_LS atual = (Node_LS) (best.Clone(f));
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			for(int i= 0;i<1000;i++) {
				if(this.stop(v))break;
				Node_LS aux = (Node_LS) (atual.Clone(f));
				int n = r.nextInt(aux.countNode());
				int custo = r.nextInt(9)+1;
				aux.mutation(n, custo);
			
				Pair<Double,Double> v2 = this.Avalia(gs, max_cicle,aux);
				//System.out.println(v2.m_b+" "+aux.translate());
				boolean b = accept(v,v2);
			//	System.out.println("atual "+v2.m_a+" "+v2.m_b+" "+aux.translate());
				
				if(b) {
					System.out.println("Sujo "+aux.translate());
					if(this.use_cleanr)aux.clear(null, f);
					System.out.println("Limpo "+aux.translate());
					//System.out.println("Ciclo "+i+":\n\t"+aux.translate()+"\n\t"+m_ais.m_a.translate());
					best = (Node_LS) aux.Clone(f);
				
					
					v=v2;
					System.out.println("atual\t"+((paraou*1.0)/1000.0)+"\t"+v.m_a+"\t"+v.m_b+"\t"+
									best.translate()+"\t"+aux.translate());
					((CabocoDagua)((SimplePlayout)this.playout).eval).imprimir();
					
				}
				
			}
			//System.out.println(rep+" Ciclo "+i+": "+m_ais.m_a.translate());
			paraou = System.currentTimeMillis()-Tini;
		
		}
		return best;
	}

}
