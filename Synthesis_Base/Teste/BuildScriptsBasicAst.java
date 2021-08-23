package Teste;

import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import ai.abstraction.LightRush;
import ai.core.AI;
import ai.synthesis.dslForScriptGenerator.DslAI;
import ai.synthesis.dslForScriptGenerator.DSLCommandInterfaces.ICommand;
import ai.synthesis.dslForScriptGenerator.DSLCompiler.IDSLCompiler;
import ai.synthesis.dslForScriptGenerator.DSLCompiler.MainDSLCompiler;
import ai.synthesis.grammar.dslTree.*;
import ai.synthesis.grammar.dslTree.interfacesDSL.iDSL;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class BuildScriptsBasicAst {

	public BuildScriptsBasicAst() {
		// TODO Auto-generated constructor stub
	}

	
	iDSL montar() {
		EmptyDSL empty = new EmptyDSL();
		CommandDSL c1 = new CommandDSL("harvest(1)");
		CommandDSL c2 = new CommandDSL("train(Worker,50,EnemyDir)");
		CommandDSL c3 = new CommandDSL("attack(Worker,closest)");
		BooleanDSL b1 = new BooleanDSL("HaveUnitsToDistantToEnemy(Worker,3)");
		CDSL C1 = new CDSL(c1);
		CDSL C2 = new CDSL(c2);
		CDSL C3 = new CDSL(c3);
	
		S2DSL S2 = new S2DSL(b1, C3);
		S1DSL S13 = new S1DSL(S2, new S1DSL(empty));
		S1DSL S12 = new S1DSL(C2, S13);
		iDSL root = new S1DSL(C1, S12);
		return root;
	}
	
	
	static iDSL montar2() {
		EmptyDSL empty = new EmptyDSL();
		CommandDSL c1 = new CommandDSL("harvest(1)");
		
		CommandDSL c3 = new CommandDSL("attack(Worker,closest)");
		
		CDSL C1 = new CDSL(c1);

		CDSL C3 = new CDSL(c3);
		BooleanDSL b1 = new BooleanDSL("HaveUnitsToDistantToEnemy(Worker,15)");
		S2DSL S2 = new S2DSL(b1, C3,C1);
		iDSL root = new S1DSL(S2, new S1DSL(empty));
	
		
		return root;
	}
	static iDSL montar3() {
		S1DSL empty = new S1DSL( new EmptyDSL());
		CommandDSL c1 = new CommandDSL("moveaway(Worker)");
		CommandDSL c2 = new CommandDSL("moveOnceToCoord(Worker,1,15,15)");
		
		
		CDSL C1 = new CDSL(c1);
		CDSL C2 = new CDSL(c2);
		
		
		
		iDSL root = new S1DSL(C1, C2);
	
		
		return root;
	}
	
	
	static iDSL montar4() {
		S1DSL empty = new S1DSL( new EmptyDSL());
		CommandDSL c1 = new CommandDSL("moveaway(Worker)");
		CommandDSL c2 = new CommandDSL("moveOnceToCoord(Worker,1,15,15)");
		
		
		CDSL C1 = new CDSL(c1);
		
		
		
		
		iDSL root = new S1DSL( C1);
	
		
		return root;
	}
	private static AI buildCommandsIA(UnitTypeTable utt, iDSL code) {
		IDSLCompiler compiler = new MainDSLCompiler();   
        HashMap<Long, String> counterByFunction = new HashMap<Long, String>();
        List<ICommand> commandsDSL = compiler.CompilerCode(code, utt);
        AI aiscript = new DslAI(utt, commandsDSL, "P1", code, counterByFunction);
        return aiscript;
    }
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
		UnitTypeTable utt = new UnitTypeTable();
		
		
		String path_map ="./maps/24x24/basesWorkers24x24A.xml";
		iDSL ai = montar3();
		AI ai1 = buildCommandsIA(utt,ai);
		System.out.print(ai.translate());
		
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
	
		AI ai2 = new LightRush(utt);
		
	
		GameState gs2 = new GameState(pgs, utt);
		boolean gameover = false;
		JFrame w=null;
		if(true) w = PhysicalGameStatePanel.newVisualizer(gs2,640,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);  
        do {
      
                PlayerAction pa1 = ai1.getAction(0, gs2);
                PlayerAction pa2 = ai2.getAction(1, gs2);
                gs2.issueSafe(pa1);
                gs2.issueSafe(pa2);
             
                gameover = gs2.cycle();
                if(true) {
                	w.repaint();
                	Thread.sleep(20);
                }
              
                

        } while (!gameover && (gs2.getTime() <= 5000));   
		


	}

}
