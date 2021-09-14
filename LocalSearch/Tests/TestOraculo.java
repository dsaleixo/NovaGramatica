package Tests;

import java.util.ArrayList;
import java.util.List;

import AIs.Interpreter;
import Oraculo.EstadoAcoes;

import ai.RandomBiasedAI;
import ai.CMAB.A3NWithin;
import ai.abstraction.LightRush;
import ai.abstraction.RangedRush;
import ai.abstraction.WorkerRush;
import ai.coac.CoacAI;
import ai.competition.capivara.Capivara;
import ai.competition.tiamat.Tiamat;
import ai.configurablescript.BasicExpandedConfigurableScript;
import ai.configurablescript.ScriptsCreator;
import ai.core.AI;
import ai.evaluation.SimpleSqrtEvaluationFunction3;
import ajuda.ScriptsFactory;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class TestOraculo {

	public TestOraculo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable();
		//String path_map ="./maps/32x32/basesWorkersBarracks32x32.xml";;
		//PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		EstadoAcoes EA = new EstadoAcoes("A3NvsCoac24",false);
		EA.reproduz();
	//	Oraculo.salvar(gss, "CoacAIxCoacAI");
	//	Oraculo.reproduz(gss);
		
	}
	
	
	
	public static List<AI> decodeScripts(UnitTypeTable utt, String sScripts) {

		//decompõe a tupla
		ArrayList<Integer> iScriptsAi1 = new ArrayList<>();
		String[] itens = sScripts.split(";");

		for (String element : itens) {
			iScriptsAi1.add(Integer.decode(element));
		}

		List<AI> scriptsAI = new ArrayList<>();

		ScriptsCreator sc = new ScriptsCreator(utt, 300);
		ArrayList<BasicExpandedConfigurableScript> scriptsCompleteSet = sc.getScriptsMixReducedSet();

		iScriptsAi1.forEach((idSc) -> {
			scriptsAI.add(scriptsCompleteSet.get(idSc));
		});

		return scriptsAI;
	}

}
