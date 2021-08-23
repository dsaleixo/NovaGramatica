package Teste;

import AIs.Search;
import CFG.Node;
import Evaluations.Evaluation;
import Evaluations.Kakashi;
import Evaluations.SimplesEvaluations;

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
		String path_map ="./maps/24x24/basesWorkers24x24A.xml";
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		boolean limpador = true;
		if(args[0].equals("1"))limpador=false;
		/*
		double T0 = 0;
		if(args[1].equals("0"))T0=100;
		if(args[1].equals("1"))T0=1000;
		if(args[1].equals("2"))T0=2000;
		double alpha =0;
		if(args[2].equals("0"))alpha=0.6;
		if(args[2].equals("1"))alpha=0.8;
		if(args[2].equals("2"))alpha=0.9;
		double beta =0;
		if(args[3].equals("0"))alpha=1;
		if(args[3].equals("1"))alpha=50;
		if(args[3].equals("2"))alpha=100;
		if(args[3].equals("3"))alpha=150;
		Evaluation eval = new SimulatedAnnealing(T0,alpha,beta);
		*/
		
		Evaluation eval = new SimplesEvaluations();
		Evaluation eval2 = new Kakashi();
		//Search search= new LS1(eval2,true);
		//Pair<Node,Node> result= search.run(gs2,2000);
		System.out.println("\n\n");
		//System.out.println(result.m_a.translateIndentation(0));
		
		
	}

}
