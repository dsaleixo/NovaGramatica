package Evaluations;

import javax.swing.JFrame;

import AIs.Interpreter;
import CFG.*;
import CFG_Actions.*;
import CFG_Condition.HasLessNumberOfUnits;
import CFG_Condition.HasNumberOfWorkersHarvesting;
import CFG_Condition.HasUnitWithinDistanceFromOpponent;
import CFG_Condition.is_Type;
import ai.abstraction.WorkerRush;
import ai.core.AI;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class Sharingan {

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
	
	
	public static Node monta9() {
		//System.out.println("Para testar este script desligue o adversario");
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
	
	static public  Node monta1() {
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
	
	static Node Montar() {
		Attack a = new Attack(new OpponentPolicy("Closest"));
		Train t = new Train(new Type("Light"),new Direction("EnemyDir"));
		Harvest h = new Harvest();
		Build b = new Build(new Type("Barracks"),new Direction("EnemyDir"));
		C ca = new C(a);
		C ct = new C(t);
		C ch = new C(h);
		C cb = new C(b);
		S_S ss1 = new S_S(new S(ct),new S(ca));
		S_S ss2 = new S_S(new S(cb),new S(ch));
		S_S ss3 = new S_S(new S(ss2),new S(ss1));
		For_S forS = new For_S(new S(ss3));
		
		return new S(forS);
	}
	
	public Sharingan() {
		// TODO Auto-generated constructor stub
		
	
	}

	
	static double run( int player,Node s1,GameState gs,int max_cycle,Factory f) throws Exception {
		
		UnitTypeTable utt = new UnitTypeTable();
		
		boolean exibe =false;
		
	
		
		AI ai1 = new Interpreter(utt,s1);
		AI Kakashi = new Interpreter(utt,Montar());
		AI ai2 = new WorkerRush(utt);
		AI ai22 = new WorkerRush(utt);
		//GameState gs2 = new GameState(pgs, utt);
		GameState gs2 = gs.cloneChangingUTT(utt);
		GameState gs3 = gs.cloneChangingUTT(utt);
		boolean gameover2 = false;
		boolean gameover3 = false;
		JFrame w=null;
		if(exibe) w = PhysicalGameStatePanel.newVisualizer(gs2,640,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);
		boolean itbroke=false ;
		int cont=0;
        do {
        	PlayerAction pa1=null;
        	PlayerAction pa11=null;
        	try {
                pa1 = ai1.getAction(player, gs2);
                pa11 = Kakashi.getAction(player, gs3);
        	}catch(Exception e) {
        		itbroke=true;
        		break;
        	}
                PlayerAction pa2 = ai2.getAction(1-player, gs2);
                PlayerAction pa22 = ai22.getAction(1-player, gs2);
                
                gs2.issueSafe(pa1);
              gs2.issueSafe(pa2);
             
                gs3.issueSafe(pa11);
                gs3.issueSafe(pa22);
                
                if(exibe) {
                	w.repaint();
                	Thread.sleep(20);
                }
                
                gameover2 = gs2.cycle();
                gameover3 = gs3.cycle();
                if(gs3.equals(gs2)) {
                	cont++;
                }else {
                	break;
                }
                

        } while (!(gameover3 || gameover2) && (gs2.getTime() <= max_cycle)); 
        
        
       
        return cont;
        
	}
}
