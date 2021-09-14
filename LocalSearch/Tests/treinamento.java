package Tests;

import java.io.IOException;

import CFG.Node;
import EvaluateGameState.CabocoDagua2;
import EvaluateGameState.Cego;
import EvaluateGameState.EvaluateGS;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import IAs.SABasic;
import IAs.Search;
import Oraculo.EstadoAcoes;
import ai.coac.CoacAI;
import ai.core.AI;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class treinamento {
	static int max_cicle=5000;
	public treinamento() {
		// TODO Auto-generated constructor stub
	}

	
	public static String getMap(String s) {
		
		if(s.equals("0")) return "maps/8x8/basesWorkers8x8A.xml";
		if(s.equals("1")) return "maps/16x16/basesWorkers16x16A.xml";				
		if(s.equals("2")) return "maps/BWDistantResources32x32.xml";
		if(s.equals("3")) {
			max_cicle=15000;
			return "maps/BroodWar/(4)BloodBath.scmB.xml";
		}
		if(s.equals("4")) return "maps/8x8/FourBasesWorkers8x8.xml";
		if(s.equals("5")) return "maps/16x16/TwoBasesBarracks16x16.xml";
		if(s.equals("6")) return "maps/NoWhereToRun9x8.xml";
		if(s.equals("7")) return "maps/DoubleGame24x24.xml";
		return null;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String path_map = getMap(args[0]);
		UnitTypeTable utt = new UnitTypeTable();
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs = new GameState(pgs, utt);
		int lado=-1;
		
		if(Integer.parseInt(args[1]) <10) {lado=0;
		}else {
			lado=1;
		}
		String partida = "CoacvsCoac"+args[0];
		System.out.println("mapa "+path_map+" "+lado);
		
		EstadoAcoes EAs = new EstadoAcoes(partida,true);
		
		
		EvaluateGS eval = new Cego();
		Playout playout = new SimplePlayout(eval);
		
		
		AI adv = new CoacAI(utt);
		Search search = new SABasic(false,adv,playout,1000,0.9,50,false);
		
		Node n = search.run(gs, max_cicle,lado);
		
		
	}

}
