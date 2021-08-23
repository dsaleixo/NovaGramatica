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

public class Test2 {
	static Factory  f = new FactoryBase();
	public Test2() {
		// TODO Auto-generated constructor stub
	}
	

	
	static Node ler(String s) throws IOException {
		
		
		BufferedReader buffRead = new BufferedReader(new FileReader("./"+s));
		String linha = "";
		String script= "eroo";
		linha = buffRead.readLine();
		while (true) {
			if (linha != null) {
				String dados[] = linha.split("=");
				if (dados[0].equals("Atual")){
					script = dados[1];
					
					
				}
			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
		//System.out.println(s);
		Node n = Control.load(script, f);
	
		return n;
	}

	
	static void carregar(List<Node> Script,List<String> Nome) throws IOException {
		String limpador[] = {"0","{1}"};
		String T0[] = {"0","1","2"};
		String alpha[] = {"0","1","2"};
		String beta[] = {"0","1","2","3"};
		String rep[] = {"0","1","2","3","4"};
		
		for(int a=0;a<limpador.length;a++) {
			for(int b=0;b<T0.length;b++) {
				for(int c=0;c<alpha.length;c++) {
					for(int d=0;d<beta.length;d++) {
						for(int e=1;e<rep.length;e++) {
							String s = "r14/out_"+limpador[a]+"_"+b+"_"+
									c+"_"+d+"_"+e+".txt";
							Node n = ler(s);
							String nome = "script_"+limpador[a]+"_"+b+"_"+
									c+"_"+d+"_"+e;
							Script.add(n);
							Nome.add(nome);
						}
					}
				}
			}
		}
		for(int a=0;a<limpador.length;a++) {
						for(int e=1;e<rep.length;e++) {
							String s = "r14/out_"+a+"_"+e+".txt";
							Node n = ler(s);
							String nome = "script_"+a+"_"+e;
							Script.add(n);
							Nome.add(nome);
						}
		}
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		String path_map ="./maps/24x24/basesWorkers24x24A.xml";
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
		
		
		List<Node> Script= new ArrayList<>();
		List<String> Nome= new ArrayList<>();
		
		carregar(Script,Nome);
		System.out.println("n "+Nome.size());
		for(int i =0;i<Script.size();i++) {
			
			//double r =Avalia(gs2,3000,Script.get(i),Script);
			System.out.println("AI="+Nome.get(i)+"\t"+"traco="+Control.salve(Script.get(i)));
		}
		
	
	}

}
