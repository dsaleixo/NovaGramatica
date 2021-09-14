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
	static int max=6000;
	public Test1() {
		// TODO Auto-generated constructor stub
	}

	public static String getMap(String s) {
		if(s.equals("0")) return "./maps/16x16/TwoBasesBarracks16x16.xml";
		if(s.equals("1")) return "./maps/16x16/TwoBasesBarracks16x16.xml";				
		if(s.equals("2")) return "./maps/16x16/TwoBasesBarracks16x16.xml";

		
		if(s.equals("3")) return "maps/24x24/basesWorkers24x24A.xml";
		if(s.equals("4")) return "maps/24x24/basesWorkers24x24A.xml";
		if(s.equals("5")) return "maps/24x24/basesWorkers24x24A.xml";

		
		if(s.equals("6")) return "maps/32x32/basesWorkers32x32A.xml";
		if(s.equals("7")) return "maps/32x32/basesWorkers32x32A.xml";
		if(s.equals("8")) return "maps/32x32/basesWorkers32x32A.xml";
	
		
		if(s.equals("9")) { max =15000;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
		if(s.equals("10")) {max =15000;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
		if(s.equals("11")) {max =15000;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		String path_map = getMap(args[0]);
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
		
		AI adv = new CoacAI(utt);
		String partida = null;
		EvaluateGS eval = null;
		Playout playout = null;
		int lado=-1;
		System.out.println("antigo2 "+path_map);
		if(Integer.parseInt(args[2]) <10) {
			lado=0;
		}else {
			lado=1;
		}
		
		
		if(args[0].equals("0")) {
		
			if(lado==0) {
				partida = "A3NvsCoac16";
			}else {
				partida ="CoacvsA3N16";
			}
		
		} else if(args[0].equals("1")) {
			
			if(lado==0) {
				partida = "RRvsCoac16";
			}else {
				partida ="CoacvsRR16";
			}
		}else if(args[0].equals("2")) {
			
			if(lado==0) {
				partida = "CoacvsCoac16";
			}else {
				partida ="CoacvsCoac16";
			}
		} else if(args[0].equals("3")) {
			
			if(lado==0) {
				partida = "A3NvsCoac24";
			}else {
				partida ="CoacvsA3N24";
			}
		
		} else if(args[0].equals("4")) {
	
			if(lado==0) {
				partida = "RRvsCoac24";
			}else {
				partida ="CoacvsRR24";
			}
		}else if(args[0].equals("5")) {
			
			if(lado==0) {
				partida = "CoacvsCoac24";
			}else {
				partida ="CoacvsCoac24";
			}
		}else if(args[0].equals("6")) {
			
			if(lado==0) {
				partida = "A3NvsCoac32";
			}else {
				partida ="CoacvsA3N32";
			}
		
		} else if(args[0].equals("7")) {
			
			if(lado==1) {
				partida = "CoacvsRR32";
			}else {
				partida ="RRvsCoac32";
			}
		}else if(args[0].equals("8")) {
		
			if(lado==0) {
				partida = "CoacvsCoac32";
			}else {
				partida ="CoacvsCoac32";
			}
		}else if(args[0].equals("9")) {
			
			if(lado==0) {
				partida = "A3NvsCoac128";
			}else {
				partida ="CoacvsA3N128";
			}
		
		} else if(args[0].equals("10")) {
			
			if(lado==1) {
				partida = "CoacvsRR128";
			}else {
				partida ="RRvsCoac128";
			}
		}else if(args[0].equals("11")) {
			
			if(lado==0) {
				partida = "CoacvsCoac128";
			}else {
				partida ="CoacvsCoac128";
			}
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
			
			
			eval = new CabocoDagua2(EAs,0);
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
		
		Node n = search.run(gs2, max,lado);
		System.out.println("FIM");
		System.out.println(n.translateIndentation(0));
	}

}
