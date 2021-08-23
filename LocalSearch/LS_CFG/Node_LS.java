package LS_CFG;

import CFG.Node;

public interface Node_LS extends Node {
	void sample(int budget);
	int countNode();
	void mutation(int node_atual,int budget);
	
}
