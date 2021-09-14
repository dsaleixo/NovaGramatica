package Tests;

import java.util.HashMap;
import java.util.List;

import AIs.Interpreter;
import CFG.Control;
import CFG.Node;
import LS_CFG.FactoryLS;
import Oraculo.EstadoAcoes;
import ai.abstraction.HeavyRush;
import ai.abstraction.RangedRush;
import ai.abstraction.WorkerRush;
import ai.coac.CoacAI;
import ai.core.AI;
import ai.synthesis.dslForScriptGenerator.DslAI;
import ai.synthesis.dslForScriptGenerator.DSLCommandInterfaces.ICommand;
import ai.synthesis.dslForScriptGenerator.DSLCompiler.IDSLCompiler;
import ai.synthesis.dslForScriptGenerator.DSLCompiler.MainDSLCompiler;
import ai.synthesis.grammar.dslTree.builderDSLTree.BuilderDSLTreeSingleton;
import ai.synthesis.grammar.dslTree.interfacesDSL.iDSL;
import ai.synthesis.grammar.dslTree.interfacesDSL.iNodeDSLTree;
import ai.synthesis.twophasessa.TradutorDSL;
import ajuda.ScriptsFactory;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class Visualizar {

	public Visualizar() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		String path_map ="./maps/16x16/TwoBasesBarracks16x16.xml";;
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		//build(Barrack,1,Up) harvest(2) train(Worker,4,Right) for(u) (build(Base,1,Left,u) train(Heavy,3,Down) train(Worker,6,Down) train(Ranged,8,Right) moveaway(Heavy,u) attack(Light,weakest,u) train(Worker,6,Right)) for(u) (if(HaveQtdEnemiesbyType(Worker,3)) then(moveaway(Ranged,u) attack(Worker,strongest,u)) if(!HaveQtdEnemiesbyType(Worker,1)) then(harvest(2,u)) else(train(Ranged,3,Down) moveaway(Worker,u) attack(Light,lessHealthy,u)) harvest(3,u) moveaway(Heavy,u)) if(!HaveUnitsStrongest(Ranged)) then(moveToUnit(Heavy,Enemy,farthest) train(Heavy,7,Left))
		String s= "harvest(3) train(Worker,4,Right) build(Barrack,1,Left) " + 
				"";
		TradutorDSL td = new TradutorDSL(s);
		iDSL A = td.getAST();
		//BuilderDSLTreeSingleton.fullPreOrderPrint((iNodeDSLTree) A);
		AI ai = buildCommandsIA(utt, A);
		String s2 = "S;S_S;S;For_S;S;S_S;S;S_S;S;S_S;S;S_S;S;C;Train;Worker;Up;7;S;S_S;S;C;Build;Barracks;EnemyDir;3;S;C;Train;Ranged;Left;10;S;C;Idle;S;C;Harvest;7;S;C;Attack;MostHealthy;S;Empty";
		Node n = Control.load(s2,new FactoryLS());
		//Node n = ScriptsFactory.monta4();
		//System.out.print(A.);
		AI oraculo = new HeavyRush(utt);
		AI adv = new CoacAI(utt);
		EstadoAcoes EAs = new EstadoAcoes(gs2,1,4000,oraculo,adv,true,true);
		n.clear(null, new FactoryLS());
		System.out.println(n.translateIndentation(0));
	}
	  private static AI buildCommandsIA(UnitTypeTable utt, iDSL code) {
	        IDSLCompiler compiler = new MainDSLCompiler();
	        HashMap<Long, String> counterByFunction = new HashMap<Long, String>();
	        List<ICommand> commandsDSL = compiler.CompilerCode(code, utt);
	        AI aiscript = new DslAI(utt, commandsDSL, "P1", code, counterByFunction);
	        return aiscript;
	    }
}


