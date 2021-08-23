package Teste;

import javax.swing.JFrame;

import AIs.Interpreter;
import CFG.B;
import CFG.C;
import CFG.Direction;
import CFG.Empty;
import CFG.Factory;
import CFG.FactoryBase;
import CFG.If_B_then_S_else_S;
import CFG.N;
import CFG.For_S;
import CFG.If_B_then_S;
import CFG.Node;
import CFG.OpponentPolicy;
import CFG.S;
import CFG.S_S;
import CFG.TargetPlayer;
import CFG.Type;
import CFG_Actions.Attack;
import CFG_Actions.Build;
import CFG_Actions.Harvest;
import CFG_Actions.Idle;
import CFG_Actions.MoveAway;
import CFG_Actions.Train;
import CFG_Actions.moveToUnit;
import CFG_Condition.CanHarvest;
import CFG_Condition.HasLessNumberOfUnits;
import CFG_Condition.HasNumberOfWorkersHarvesting;
import CFG_Condition.HasUnitWithinDistanceFromOpponent;
import CFG_Condition.Is_Builder;
import CFG_Condition.is_Type;
import ai.abstraction.LightRush;
import ai.abstraction.RangedRush;
import ai.coac.CoacAI;
import ai.core.AI;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class BuildScriptsBasic {

	public BuildScriptsBasic() {
		// TODO Auto-generated constructor stub
	}

	
	
	public static Node monta0() {
		Attack a = new Attack(new OpponentPolicy("Closest"));
		C c = new C(a);
		B b = new B(new is_Type(new Type("Worker")));
		If_B_then_S iff = new  If_B_then_S(b,new S(c));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	
	public static Node monta1() {
		Attack a = new Attack(new OpponentPolicy("Closest"));
		C c = new C(a);
		For_S f = new For_S(new S(c));
		S s = new S(f);
		return s;
	}
	public static Node monta2() {
		Harvest a = new Harvest();
		C c = new C(a);
		For_S f = new For_S(new S(c));
		S s = new S(f);
		return s;
	}
	
	public static Node monta3() {
		Harvest a = new Harvest();
		C c = new C(a);
		B b = new B(new CanHarvest());
		If_B_then_S iff = new  If_B_then_S(b,new S(c));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	
	public static Node monta4() {
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		C c = new C(bb);
		For_S f = new For_S(new S(c));
		S s = new S(f);
		return s;
	}
	
	public static Node monta6() {
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		Harvest a = new Harvest();
		C cc = new C(a);
		C c = new C(bb);
		S_S s_s= new S_S(new S(c),new S(cc));
		B b = new B(new Is_Builder());
		If_B_then_S iff = new  If_B_then_S(b,new S(s_s));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta7() {
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		Harvest a = new Harvest();
		C cc = new C(a);
		C c = new C(bb);
		B b2 = new B(new HasLessNumberOfUnits(new Type("Barracks") , new N("1"))) ;
		If_B_then_S iff2 = new If_B_then_S(b2, new S(c));
		S_S s_s= new S_S(new S(iff2),new S(cc));
		B b = new B(new Is_Builder());
		If_B_then_S iff = new  If_B_then_S(b,new S(s_s));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta5() {
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		C c = new C(bb);
		B b = new B(new Is_Builder());
		If_B_then_S iff = new  If_B_then_S(b,new S(c));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta8() {
		Train t = new Train(new Type("Ranged") , new  Direction("EnemyDir"));
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		Harvest a = new Harvest();
		Attack aa = new Attack(new OpponentPolicy("Closest"));
		C ca = new C(aa);
		C ct = new C(t);
		C cc = new C(a);
		C c = new C(bb);
		S_S t_h = new S_S(new S(ct),new S(cc));
		B b2 = new B(new HasLessNumberOfUnits(new Type("Barracks") , new N("1"))) ;
		If_B_then_S iff2 = new If_B_then_S(b2, new S(c));
		S_S s_s= new S_S(new S(iff2),new S(t_h));
		//B b = new B(new Is_Builder());
		//If_B_then_S iff = new  If_B_then_S(b,new S(s_s));
		For_S f = new For_S(new S(new S_S(new S(s_s),new S(ca))));
		S s = new S(f);
		return s;
	}
	public static Node monta9() {
		System.out.println("Para testar este script desligue o adversario");
		Train t = new Train(new Type("Ranged") , new  Direction("EnemyDir"));
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		Harvest h = new Harvest();
		Train tw = new Train(new Type("Worker") , new  Direction("EnemyDir"));
		S e = new S(new Empty());
		
		C ct = new C(t);
		C ch = new C(h);
		C c = new C(bb);
		C ctw = new C(tw);
		
		B btw = new B(new HasLessNumberOfUnits(new Type("Worker") , new N("5"))) ;
		If_B_then_S_else_S if1= new If_B_then_S_else_S(btw,new S(ctw),e);
		S_S s_s = new S_S(new S(ch),new S(if1));
	
		B b2 = new B(new HasLessNumberOfUnits(new Type("Barracks") , new N("1"))) ;
		If_B_then_S iff2 = new If_B_then_S(b2, new S(c));
		S_S s_s2 = new S_S(new S(iff2),new S(s_s));
		For_S f = new For_S(new S(s_s2));
		S s = new S(f);
		
		return s;
	
	
	}
	
	
	public static Node monta10() {
		Harvest h = new Harvest();
		Attack a = new Attack(new OpponentPolicy("Closest"));
		
		C ca = new C(a);
		C ch = new C(h);
		
		B b1 = new B(new HasUnitWithinDistanceFromOpponent(new N("10")));
		
		If_B_then_S_else_S if_then_else = new If_B_then_S_else_S(b1,new S(ca), new S(ch));
		
				
		B b = new B(new is_Type(new Type("Worker")));
		If_B_then_S iff = new  If_B_then_S(b,new S(if_then_else));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta11() {
		Idle i = new Idle();
		MoveAway ma = new  MoveAway();
		Empty e = new Empty();
		C ci = new C(i);
		C cma = new C(ma);
		S_S s_s = new S_S(new S(e),new S(cma));

		For_S f = new For_S(new S(s_s));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta12() {
		Idle i = new Idle();
		moveToUnit ma = new  moveToUnit(new TargetPlayer("Ally"),new OpponentPolicy("Closest"));
		Empty e = new Empty();
		C ci = new C(i);
		C cma = new C(ma);
		S_S s_s = new S_S(new S(e),new S(cma));

		For_S f = new For_S(new S(s_s));
		S s = new S(f);
		return s;
	}
	
	public static Node monta13() {
		Idle i = new Idle();
		moveToUnit ma = new  moveToUnit(new TargetPlayer("Enemy"),new OpponentPolicy("Closest"));
		Empty e = new Empty();
		C ci = new C(i);
		C cma = new C(ma);
		S_S s_s = new S_S(new S(e),new S(cma));

		For_S f = new For_S(new S(s_s));
		S s = new S(f);
		return s;
	}
	
	
	//Strongest
	//Weakest"
	//Closest
	//Farthest
	//LessHealthy
	//MostHealthy
	//Random
	
	
	public static Node monta14() {
		Idle i = new Idle();
		moveToUnit ma = new  moveToUnit(new TargetPlayer("Ally"),new OpponentPolicy("Random"));
		Empty e = new Empty();
		C ci = new C(i);
		C cma = new C(ma);
		S_S s_s = new S_S(new S(e),new S(cma));

		For_S f = new For_S(new S(s_s));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta15() {
		Train t = new Train(new Type("Light") , new  Direction("EnemyDir"));
		Train tw = new Train(new Type("Worker") , new  Direction("EnemyDir"));
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		Harvest a = new Harvest();
		Attack aa = new Attack(new OpponentPolicy("Closest"));
		C ca = new C(aa);
		C ct = new C(t);
		C cc = new C(a);
		C c = new C(bb);
		S_S t_h = new S_S(new S(ct),new S(cc));
		B b2 = new B(new HasLessNumberOfUnits(new Type("Barracks") , new N("1"))) ;
		B b3 = new B(new HasLessNumberOfUnits(new Type("Worker") , new N("2"))) ;
		B b4 = new B(new HasNumberOfWorkersHarvesting(new N("1")));
		
		If_B_then_S_else_S ite = new If_B_then_S_else_S(b4,new S(ca),new S(cc));
		If_B_then_S iff2 = new If_B_then_S(b2, new S(c));
		If_B_then_S iff3 = new If_B_then_S(b3, new S(new C(tw)));
		
		S_S s_s= new S_S(new S(iff3),new S(t_h));
		S_S s_s2= new S_S(new S(ite),new S(s_s));
		S_S s_s3= new S_S(new S(iff2),new S(s_s2));
		
		//B b = new B(new Is_Builder());
		//If_B_then_S iff = new  If_B_then_S(b,new S(s_s));
		For_S f = new For_S(new S(new S_S(new S(s_s3),new S(ca))));
		S s = new S(f);
		return s;
	}
	
	
	public static Node monta16() {
	
		Train tw = new Train(new Type("Worker") , new  Direction("EnemyDir"));
		S e = new S(new Empty());
		
		
		C ctw = new C(tw);
		
		
	
		For_S f = new For_S(new S(ctw));
		S s = new S(f);
		
		return s;
	
	
	}
	
	public static Node monta17() {
		
		Build tw = new Build(new Type("Barracks") , new  Direction("EnemyDir"));
		S e = new S(new Empty());
		
		
		C ctw = new C(tw);
		
		
	
		For_S f = new For_S(new S(ctw));
		S s = new S(f);
		
		return s;
	
	
	}
	
	
	public static Node monta18() {
		Attack a = new Attack(new OpponentPolicy("Strongest"));
		C c = new C(a);
	
		For_S f = new For_S(new S(c));
		S s = new S(f);
		return s;
	}
	public static Node monta19() {
		Attack a = new Attack(new OpponentPolicy("LessHealthy"));
		Train t = new Train(new Type("Worker"),new Direction("Up"));
		C c = new C(a);
		C ct = new C(t);
		S_S ss = new S_S(new S(c),new S(ct));
		For_S f = new For_S(new S(ss));
		S s = new S(f);
		return s;
	}
	
	public static Node monta20() {
		Attack a = new Attack(new OpponentPolicy("Strongest"));
		Train t = new Train(new Type("Worker"),new Direction("Down"));
		C c = new C(a);
		C ct = new C(t);
		S_S ss = new S_S(new S(c),new S(ct));
		For_S f = new For_S(new S(ss));
		S s = new S(f);
		return s;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		
		
		String path_map ="./maps/24x24/basesWorkers24x24A.xml";
		
		Factory f = new FactoryBase();
		
	
		Node no= monta19();
		//if(true)return ;
		System.out.println(no.translateIndentation(0));
		
		Interpreter ai1 = new Interpreter(utt,no.Clone(f));
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		//AI ai1 = new WorkerRush(utt);
		//AI ai2 = new LightRush(utt);
		Node no2= monta19();
		AI ai2 =  new CoacAI(utt);
	
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
              
                

        } while (!gameover && (gs2.getTime() <= 2000));   
		
		
		
		
		

	}

}
