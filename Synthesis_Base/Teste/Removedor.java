package Teste;

import javax.swing.JFrame;

import AIs.Interpreter;
import CFG.B;
import CFG.C;
import CFG.Empty;
import CFG.For_S;
import CFG.If_B_then_S;
import CFG.*;
import CFG.OpponentPolicy;
import CFG.S;
import CFG.Type;
import CFG_Actions.Attack;
import CFG_Actions.*;
import CFG_Condition.is_Type;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class Removedor {

	
	public static Node monta1() {
		Attack a = new Attack(new OpponentPolicy("Closest"));
		C c = new C(a);
		B b = new B(new is_Type(new Type("Worker")));
		If_B_then_S iff = new  If_B_then_S(b,new S(c));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	public static Node monta2() {
		Attack a = new Attack(new OpponentPolicy("Closest"));
		Train t = new Train(new Type("Heavy"),new Direction("Up"));
		C c = new C(a);
		C ct = new C(t);
		S_S ss1= new S_S(new S(c),new S(ct));
		B b = new B(new is_Type(new Type("Worker")));
		If_B_then_S iff = new  If_B_then_S(b,new S(ss1));
		For_S f = new For_S(new S(iff));
		S s = new S(f);
		return s;
	}
	public Removedor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
UnitTypeTable utt = new UnitTypeTable();
		
		
		String path_map ="./maps/24x24/basesWorkers24x24A.xml";
		
		
		
	
		Node no= monta2();
		//if(true)return ;
		System.out.println(no.translateIndentation(0));
		
		Interpreter ai1 = new Interpreter(utt,no);
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		//AI ai1 = new WorkerRush(utt);
		//AI ai2 = new LightRush(utt);
		Node no2= new S(new Empty());
		Interpreter ai2 =  new Interpreter(utt,no2);
	
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
                	Thread.sleep(10);
                }
              
                

        } while (!gameover && (gs2.getTime() <= 2000));   
		
		Factory f = new FactoryBase();
		no.clear(null,f);
		System.out.println("Limpar");
		System.out.print(no.translateIndentation(0));
		

	}
}
