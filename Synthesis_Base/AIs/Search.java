package AIs;

import CFG.Node;
import rts.GameState;
import util.Pair;

public interface Search {
	public Pair<Node, Node> run(GameState gs,int max_cicle) throws Exception;
}
