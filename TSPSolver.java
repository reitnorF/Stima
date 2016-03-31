import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TSPSolver {
	private TSPMatrix matrix;
	private int nNode;
	private List<TSPNode> listOfNode;
	private TSPNode node;
	private TSPNode nodeSuccededSoFar;
	private boolean finish;


	public TSPSolver() {
		matrix = new TSPMatrix();
		node = new TSPNode();
		listOfNode = new ArrayList<TSPNode>();
		finish = false;

		matrix.inputMatrixFromFile();
		nNode = matrix.getnNode();
		node.connectToMatrix(matrix);
		listOfNode.add(node);
		System.out.println(node.getNodeCost());
	}


	public void expandNode(TSPNode targetNode) {
		boolean[] isNodeVisited = targetNode.getUnvisitedNode();
		for (int i = 0 ; i <nNode ; i++) {
			if (isNodeVisited[i] == false) {
				TSPNode clonedNode = new TSPNode();
				clonedNode.duplicate(targetNode);
				clonedNode.addProgress(i);
				clonedNode.calculateCost();
				System.out.println(clonedNode.getNodeCost());
				listOfNode.add(clonedNode);
			}
		}
		Collections.sort(listOfNode, new NodeComparator());
	}

	public void processNode(){
		TSPNode targetNode = listOfNode.get(0);
		listOfNode.remove(0);

		System.out.println("Processing node with cost :" + targetNode.getNodeCost());

		if (targetNode.isFinish()) {
			nodeSuccededSoFar = targetNode;
			System.out.println("Node succedeed!! :" + targetNode.getNodeCost());
			if (listOfNode.size() != 0) {
				targetNode = listOfNode.get(0);
				if (targetNode.getNodeCost() <=  nodeSuccededSoFar.getNodeCost()) {
					expandNode(targetNode);
				}
				else {
					listOfNode.clear();
					finish = true;
				}
			}
		}
		else {
			expandNode(targetNode);
		}
	}

	public void showResult(){
		int visitedNode[] = nodeSuccededSoFar.getVisitedNode();

		for (int i=0 ; i< nNode ; i++){
			System.out.print(visitedNode[i]+"->");
		}
		System.out.println(visitedNode[0]);


	}

	public void solve(){
		while (!finish) {
			processNode();
		}
		showResult();
	}

	public static void main(String args[]){
		TSPSolver solver = new TSPSolver();
		solver.solve();	
	}




}