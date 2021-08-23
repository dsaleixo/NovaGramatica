package IAs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import AIs.Interpreter;

import CFG.Node;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;

import ai.core.AI;
import ai.synthesis.dslForScriptGenerator.DslAI;
import ai.synthesis.dslForScriptGenerator.DSLCommandInterfaces.ICommand;
import ai.synthesis.dslForScriptGenerator.DSLCompiler.IDSLCompiler;
import ai.synthesis.dslForScriptGenerator.DSLCompiler.MainDSLCompiler;
import ai.synthesis.grammar.dslTree.builderDSLTree.BuilderDSLTreeSingleton;
import ai.synthesis.grammar.dslTree.interfacesDSL.iDSL;
import ai.synthesis.grammar.dslTree.utils.ReduceDSLController;
import rts.GameState;
import rts.units.UnitTypeTable;
import util.Pair;

public class SA2 implements Search {

	
	boolean use_cleanr;
	AI adv;
	boolean cego;
	Playout playout;
	double T0;
	double alpha;
	double beta;
	Random r =new Random();
	iDSL best;
	Pair<Double,Double> best_v;
	long tempo_ini;
	int limit_imitacao=360;
	BuilderDSLTreeSingleton builder;
	
	public SA2() {
		// TODO Auto-generated constructor stub
		
		use_cleanr = true;

		UnitTypeTable utt = new UnitTypeTable();
		this.playout = new SimplePlayout();
		this.T0 = 2000;
		this.alpha=0.9;
		this.beta = 1;
		this.best =  builder.buildS1Grammar();
		this.best_v= new Pair<>(-1.0,-1.0);
		builder = BuilderDSLTreeSingleton.getInstance();
	}

	
	public SA2(boolean clear,AI adv,Playout playout,double T0,double alpha,double beta,boolean cego) {
		// TODO Auto-generated constructor stub
		
		builder = BuilderDSLTreeSingleton.getInstance();
		this.use_cleanr = clear;
		this.adv = adv;
		this.playout= playout;
		this.T0=T0;
		this.alpha = alpha;
		this.beta= beta;
		this.best = builder.buildS1Grammar();
		this.best_v = new Pair<>(-1.0,-1.0);
		this.cego = cego;
		
		
	}
	
	
	
	public boolean if_best(Pair<Double,Double> v1 ,Pair<Double,Double>  v2) {
		if(v2.m_a>v1.m_a)return true;
	
		boolean aux = Math.abs(v2.m_a - v1.m_a) <0.1;
		if(aux && v2.m_b > v1.m_b) return true;
		return false;
	}
	
	public boolean accept(Pair<Double,Double> v1 ,Pair<Double,Double>  v2, double temperatura) {
		if(v2.m_a>v1.m_a)return true;
	
		boolean aux = Math.abs(v2.m_a - v1.m_a) <0.1;
		if(aux ) {
			//np.exp(self.beta * (next_score - current_score)/self.current_temperature)
			double aux2 = Math.exp(this.beta*(v2.m_b - v1.m_b)/temperatura);
			aux2 = Math.min(1,aux2);
			if(r.nextFloat()<aux2)return true;
		}
		return false;
	}
	
	
	public boolean if_best2(Pair<Double,Double> v1 ,Pair<Double,Double>  v2) {

		if( v2.m_b > v1.m_b) return true;
		return false;
	}
	
	public boolean accept2(Pair<Double,Double> v1 ,Pair<Double,Double>  v2, double temperatura) {
		
	
			//np.exp(self.beta * (next_score - current_score)/self.current_temperature)
		double aux2 = Math.exp(this.beta*(v2.m_b - v1.m_b)/temperatura);
		aux2 = Math.min(1,aux2);
		if(r.nextFloat()<aux2)return true;
		
		return false;
	}
	
	Pair<Double,Double> Avalia(GameState gs, int max_cicle,int lado,iDSL n) throws Exception{
		UnitTypeTable utt = new UnitTypeTable();
		AI ai =  buildCommandsIA(utt,n);
		Pair<Double,Double> r= this.playout.run(gs, lado, max_cicle, ai, adv, false);
		 ReduceDSLController.removeUnactivatedParts(n, new ArrayList<>(((DslAI) ai).getCommands()));
		return r;
	}
	
	public static AI buildCommandsIA(UnitTypeTable utt, iDSL code) {
        IDSLCompiler compiler = new MainDSLCompiler();
        HashMap<Long, String> counterByFunction = new HashMap<Long, String>();
        List<ICommand> commandsDSL = compiler.CompilerCode(code, utt);
        AI aiscript = new DslAI(utt, commandsDSL, "P1", code, counterByFunction);
        return aiscript;
    }
	
	boolean stop(Pair<Double,Double> v1 ) {
		return false;
	}
	
	
	public iDSL bus_imitacao(GameState gs, int max_cicle,int lado) throws Exception {
		// TODO Auto-generated method stub
		iDSL atual =  builder.buildS1Grammar();
		Pair<Double,Double> v = new Pair<>(-1.0,-1.0);
		long Tini = System.currentTimeMillis();
		long paraou = System.currentTimeMillis()-Tini;
	
		int cont=0;
		while( (paraou*1.0)/1000.0 <120) {
			double T = this.T0/(1+cont*this.alpha);
			iDSL melhor_vizinho = null ;
			Pair<Double,Double> v_vizinho = new Pair<>(-1.0,-1.0);
			for(int i= 0;i<200;i++) {
				
				iDSL aux =  (iDSL) atual.clone();
				for(int ii=0;ii<1;ii++) {
					aux =BuilderDSLTreeSingleton.changeNeighbourPassively(aux);;
				}
				Pair<Double,Double> v2 = this.Avalia(gs,max_cicle,lado,aux);
				//	System.out.println(v2.m_b+" "+aux.translate());
				if(if_best(v_vizinho,v2)) {
					
					melhor_vizinho = (iDSL) aux.clone();
					v_vizinho=v2;
				}
				
				
				paraou = System.currentTimeMillis()-Tini;
				if((paraou*1.0)/1000.0 <120)break;
			}
		
			
		
			if(accept2(v,v_vizinho,T)) {
				atual=(iDSL) melhor_vizinho.clone();
				v = v_vizinho;
			}
			
			paraou = System.currentTimeMillis()-Tini;
			
			
			if(this.if_best(this.best_v,v_vizinho)) {
				this.best = (iDSL) melhor_vizinho.clone();
				this.best_v = v_vizinho;
				long paraou2 = System.currentTimeMillis()-this.tempo_ini;
				System.out.println("atual\t"+((paraou2*1.0)/1000.0)+"\t"+best_v.m_a+"\t"+best_v.m_b+"\t"+
						best.translate()+"\t");
				
				
			}
			
			cont++;
			
			
			
		}
		
		
		return atual;
	}

	public iDSL bus_adv(GameState gs, int max_cicle,int lado, iDSL aux2) throws Exception {
		// TODO Auto-generated method stub
		iDSL atual =  (iDSL) aux2.clone();
		Pair<Double,Double> v = this.best_v;
		long Tini = System.currentTimeMillis();
		long paraou = System.currentTimeMillis()-Tini;
	
		int cont=0;
		while( (paraou*1.0)/1000.0 <840) {
			double T = this.T0/(1+cont*this.alpha);
			iDSL melhor_vizinho = null ;
			Pair<Double,Double> v_vizinho = new Pair<>(-1.0,-1.0);
			for(int i= 0;i<200;i++) {
				
				iDSL aux = (iDSL) (atual.clone());
				for(int ii=0;ii<1;ii++) {
					aux =BuilderDSLTreeSingleton.changeNeighbourPassively(aux);;
				}
				Pair<Double,Double> v2 = this.Avalia(gs, max_cicle,lado,aux);
					//System.out.println(v2.m_b+" "+aux.translate());
		
				
				if(if_best(v_vizinho,v2)) {
				
						melhor_vizinho = (iDSL) aux.clone();
						v_vizinho=v2;	
				}
				paraou = System.currentTimeMillis()-Tini;
				if((paraou*1.0)/1000.0 <840)break;
			}
		
			

				if(accept(v,v_vizinho,T)) {
					atual=(iDSL) melhor_vizinho.clone();
					v = v_vizinho;
				}
			
			paraou = System.currentTimeMillis()-this.tempo_ini;
			
			
			if(this.if_best(this.best_v,v_vizinho)) {
				this.best = (iDSL) melhor_vizinho.clone();
				this.best_v = v_vizinho;
				long paraou2 = System.currentTimeMillis()-this.tempo_ini;
				System.out.println("atual\t"+((paraou2*1.0)/1000.0)+"\t"+best_v.m_a+"\t"+best_v.m_b+"\t"+
							best.translate()+"\t");
			}
			
			cont++;
			
			
			
		}
		
		
		return this.best;
	}
	
	@Override
	public Node run(GameState gs, int max_cicle, int lado) throws Exception {
		// TODO Auto-generated method stub
		this.tempo_ini = System.currentTimeMillis();
		
		long paraou = System.currentTimeMillis()-this.tempo_ini;
		while(true) {
			iDSL n=null;
			if(this.cego) {
				n = this.best =  builder.buildS1Grammar();
			}else {
				
				n=	this.bus_imitacao(gs, max_cicle,lado);
			}
			this.bus_adv(gs, max_cicle,lado,n);
		}
		
		
		
	}

}
