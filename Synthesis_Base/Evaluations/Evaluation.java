package Evaluations;

import CFG.Factory;
import CFG.Node;
import rts.GameState;
import util.Pair;

public interface Evaluation {
	boolean evaluation(GameState gs, Pair<Node,Node> ais, int max_cycle,Factory f) throws Exception;
	Pair<Node,Node> getAIS();
	
}
