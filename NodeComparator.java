import java.util.*;
import java.util.Collections;


public class NodeComparator implements Comparator<TSPNode> {
	@Override
	public int compare(TSPNode node1 , TSPNode node2){
		return node1.getNodeCost() - node2.getNodeCost();
	}
}