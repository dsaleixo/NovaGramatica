package ajuda;

import CFG.B;
import CFG.*;
import CFG.Direction;
import CFG.Empty;
import CFG.For_S;
import CFG.If_B_then_S;
import CFG.If_B_then_S_else_S;
import CFG.N;
import CFG.Node;
import CFG.OpponentPolicy;
import CFG.S;
import CFG.S_S;
import CFG.Type;
import CFG_Actions.Attack;
import CFG_Actions.Build;
import CFG_Actions.Harvest;
import CFG_Actions.Idle;
import CFG_Actions.Train;
import CFG_Condition.HasLessNumberOfUnits;
import CFG_Condition.HasNumberOfUnits;
import CFG_Condition.HasNumberOfWorkersHarvesting;
import CFG_Condition.HasUnitWithinDistanceFromOpponent;
import CFG_Condition.is_Type;
import IAs.Build_LS;
import IAs.C_LS;
import LS_CFG.For_S_LS;
import LS_CFG.S_LS;

public class ScriptsFactory {

	public ScriptsFactory() {
		// TODO Auto-generated constructor stub
	}

	static public Node montarLightRush() {
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
	
	public static Node monta1() {
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
	
	public static Node monta2() {
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
	
	
	static public  Node montaCoac() {
		
		Attack aa = new Attack(new OpponentPolicy("Closest"));
		C caa = new C(aa);
		S saa = new S(caa);
		
		Build bb = new Build( new Type("Barracks") , new  Direction("EnemyDir"));
		C cbb = new C(bb);
		S sbb = new S(cbb);
		B bbb = new B(new HasLessNumberOfUnits(new Type("Barracks") , new N("1"))) ;
		If_B_then_S ibb = new If_B_then_S(bbb, sbb);
		
		
		Harvest h = new Harvest();
		C ch = new C(h);
		S sh = new S(ch);
		B bsh = new B(new HasNumberOfWorkersHarvesting( new N("3"))) ;
		If_B_then_S_else_S ish = new If_B_then_S_else_S(bsh, saa,sh);
	
		
		Train tw = new Train(new Type("Worker") , new  Direction("EnemyDir"));
		C ctw = new C(tw);
		S stw = new S(ctw);
		B bst = new B(new HasNumberOfUnits(new Type("Ranged") , new N("1"))) ;
		If_B_then_S ist = new If_B_then_S(bst, stw);
		
		
		
		Train tr = new Train(new Type("Ranged") , new  Direction("EnemyDir"));
		C ctr = new C(tr);
		S str = new S(ctr);
		
		
		Idle id = new Idle();
		C cid = new C(id);
		S sid = new S(cid);
		
		S_S ss1= new S_S(new S(ibb),new S(ish));
		S_S ss2 = new S_S(str,saa);
		S_S ss3 = new S_S(new S(ss1),new S(ss2));
		
		S_S ss0 = new S_S(new S(ss3),new S(ist));
		S_S ss4 = new S_S(sid,new S(ss0));
		
		For_S f = new For_S(new S(ss4));
		S s = new S(f);
		
		return s;
	}
	
	
	static public  Node monta3() {
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
	
	static public  Node monta4() {
		
		Build b= new Build(new Type("Barracks"), new  Direction("EnemyDir"), new N("10"));
		C c =new C( b);
		For_S f = new For_S(new S(c));
		S s = new S(f);
		return s;
	}
}
