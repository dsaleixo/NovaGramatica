package Teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CFG.Control;
import CFG.Factory;
import CFG.FactoryBase;
import CFG.Node;
import Evaluations.Playout;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class Test3 {
	static Factory  f = new FactoryBase();
	public Test3() {
		// TODO Auto-generated constructor stub
	}

	static double Avalia(GameState gs,int max_cycle,Node n, List<Node> advs) throws Exception {
		
		
		double r =0;
		int i=0;
		
		for(Node adv : advs) {
			
			double p1=Playout.run(0, n.Clone(f),adv.Clone(f), gs, max_cycle,f,false);
			double p2=Playout.run(1,n.Clone(f),adv.Clone(f), gs, max_cycle,f,false);
			System.out.println("p="+i+" {"+p1+","+p2+"}");
			
			r+=p1+p2;
			i++;
		}
		return  r;
	}
	
	static void ler(String s,List<Node> Script,List<String> Nome) throws IOException {
		
		
		BufferedReader buffRead = new BufferedReader(new FileReader("./"+s));
		String linha = "";
		String script= "";
		linha = buffRead.readLine();
		while (true) {
			if (linha != null ) {
				
				String dados[] = linha.split("\t");
				String aux[] = dados[0].split("=");
				String aux1[] = dados[1].split("=");
			
				Script.add(Control.load(aux1[1], f));
				Nome.add(aux[1]);
				
			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
		//System.out.println(s);
		
	
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				UnitTypeTable utt = new UnitTypeTable();
				String path_map ="./maps/24x24/basesWorkers24x24A.xml";
				PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
				GameState gs2 = new GameState(pgs, utt);
		List<Node> Script= new ArrayList<>();
		List<String> Nome= new ArrayList<>();
		String s = "r14/jogadores.txt";
		ler(s,Script,Nome);
		int jogador=Integer.parseUnsignedInt(args[0]);
		
		/*for(int i=0;i<Nome.size();i++) {
			if(Nome.get(i).equals("script_0_1_0_0_3")) {
				System.out.println(i);
			}
		}
		*/
		Node  n = Script.get(jogador);
		double r = Avalia(gs2,5000,n,Script);
		System.out.println("R="+r);
		System.out.println(Nome.get(jogador));
		System.out.println(n.translateIndentation(0));
		
	}

}
