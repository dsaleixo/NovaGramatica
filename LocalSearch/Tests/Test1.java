package Tests;

import java.util.List;
import java.util.Random;

import AIs.Interpreter;
import CFG.Node;
import EvaluateGameState.AcaoPlayout;
import EvaluateGameState.CabocoDagua;
import EvaluateGameState.CabocoDagua2;
import EvaluateGameState.Cego;
import EvaluateGameState.EvaluateGS;
import EvaluateGameState.LTD3;
import EvaluateGameState.Media;
import EvaluateGameState.Perfect;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import EvaluateGameState.Super;
import Evaluations.Evaluation;
import Evaluations.Kakashi;
import Evaluations.SimplesEvaluations;

import IAs.LSBasic;
import IAs.SA2;
import IAs.SABasic;
import IAs.Search;
import LS_CFG.*;
import Oraculo.EstadoAcoes;
import ai.abstraction.LightRush;
import ai.abstraction.RangedRush;
import ai.abstraction.WorkerRush;
import ai.coac.CoacAI;
import ai.core.AI;
import ajuda.ScriptsFactory;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;
import util.Pair;

public class Test1 {

	public Test1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		String path_map ="./maps/24x24/basesWorkers24x24A.xml";;
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
		boolean cego =false;
		double T0=2000;
		//if(args[1].equals("0"))T0=100;
		//if(args[1].equals("1"))T0=1000;
		//if(args[1].equals("2"))T0=2000;
		
		double alpha=0.9;
		//if(args[2].equals("0"))alpha=0.6;
		//if(args[2].equals("1"))alpha=0.8;
		//if(args[2].equals("2"))alpha=0.9;			
						
		double beta=1;
	//	if(args[3].equals("0"))beta=1;
		//if(args[3].equals("1"))beta=50;
	//	if(args[3].equals("2"))beta=100;		
	//	if(args[3].equals("3"))beta=150;	
		
		AI adv = null;
		String partida = null;
		EvaluateGS eval = null;
		Playout playout = null;
		
		System.out.println("antigo2 "+path_map);
		
		int lado=-1;
		
		if(args[0].equals("0")) {
			adv = new WorkerRush(utt);
			partida = "A3NvsWR";
			
			lado =0;
		} else if(args[0].equals("1")) {
			adv = new RangedRush(utt);
			partida = "A3NvsRR";
			lado =0;
		}else if(args[0].equals("2")) {
			adv = new WorkerRush(utt);
			partida = "RRvsWR";
			lado =0;
		}else if(args[0].equals("3")) {
			adv = new RangedRush(utt);
			partida = "RRvsRR";
			lado =0;
		}else if(args[0].equals("4")) {
			adv = new WorkerRush(utt);
			partida = "CoacvsWR";
			lado =0;
		}else if(args[0].equals("5")) {
			adv = new RangedRush(utt);
			partida = "CoacvsRR";
			lado =0;
		}else if(args[0].equals("6")) {
			adv = new CoacAI(utt);
			partida = "CoacvsCoac";
			lado =0;
		}else if(args[0].equals("7")) {
			adv = new CoacAI(utt);
			partida = "CoacvsCoac";
			lado =1;
		}
		
		System.out.println(partida);
		System.out.println(lado);
		if(args[1].equals("0")) {
			EstadoAcoes EAs = new EstadoAcoes(partida,false);
			List<GameState> gss2= EAs.gss;
			eval = new Perfect(gss2);
			playout = new SimplePlayout(eval);
			System.out.println("Perfect");
		}
		if(args[1].equals("1")) {
			eval = new LTD3();
			playout = new SimplePlayout(eval);
			System.out.println("LTD3");
		}
		if(args[1].equals("2")) {
			eval = new Cego();
			cego =true;
			playout = new SimplePlayout(eval);
			System.out.println("Cego");
		}
		if(args[1].equals("3")) {
			EstadoAcoes EAs = new EstadoAcoes(partida,false);
			List<GameState> gss2= EAs.gss;
			
			eval = new CabocoDagua(gss2,lado);
			playout = new SimplePlayout(eval);
			((CabocoDagua)eval).oraculo.imprimir();
			System.out.println("Caboco "+gss2.size());
		}
		if(args[1].equals("4")) {
			EstadoAcoes EAs = new EstadoAcoes(partida,true);
		
			playout = new AcaoPlayout(EAs);
			System.out.println("Acao");
		}
		
		if(args[1].equals("5")) {
			EstadoAcoes EAs = new EstadoAcoes(partida,true);
			
			
			eval = new CabocoDagua2(EAs,lado);
			playout = new SimplePlayout(eval);
			((CabocoDagua2)eval).imprimir();
			System.out.println("Marca2 ");
		}if(args[1].equals("6")) {
			EstadoAcoes EAs = new EstadoAcoes(partida,true);
			
			
			eval = new Super(EAs,lado);
			playout = new SimplePlayout(eval);
			
			System.out.println("Super ");
		}
		//if(true)return ;
		Search search = new SABasic(false,adv,playout,1000,0.9,50,cego);
		
		Node n = search.run(gs2, 8000,lado);
		System.out.println("FIM");
		System.out.println(n.translateIndentation(0));
	}

}
