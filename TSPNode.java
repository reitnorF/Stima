public class TSPNode {
	private int nodeCost;
	private int visitedNode[];
	private int adjMatrix[][];
	private int nNode;
	private int progress;



	public void duplicate(final TSPNode p){
		nodeCost = p.getNodeCost();
		visitedNode = p.getVisitedNode();
		adjMatrix = p.getAdjMatrix();
		nNode = p.getnNode();
		progress = p.getProgress();

	}


	public int getNodeCost() {
		return nodeCost;
	}

	public int[] getVisitedNode() {
		return visitedNode;
	}

	public int[][] getAdjMatrix(){
		return adjMatrix;
	}

	public int getnNode(){
		return nNode;
	}

	public int getProgress(){
		return progress;
	}

	public boolean isFinish(){
		if ((progress+1) == nNode) {
			return true;
		}
		return false;
	}

	public void connectToMatrix(TSPMatrix x){
		nNode = x.getnNode();
		visitedNode = new int [nNode];
		progress= 1;
		nodeCost = 0;
		adjMatrix = x.getAdjMatrix();

		visitedNode[0] = 0;
		for (int i = 1 ; i < nNode ; i++){
			visitedNode[i] = -1;
		}
		this.calculateCost();
	}


	private int nodeCompleteTour(int nodeidx){
		int min1 = 0;
		int min2 = 0;
		boolean isSet1 = false;
		boolean isSet2 = false;
		boolean isMin1Locked = false;


		int nodeMustVisit = getNodeMustVisit(nodeidx);
		if (nodeMustVisit != -1) {
			min1 = adjMatrix[nodeidx][nodeMustVisit];
			isSet1 = true;
			isMin1Locked = true;
		}


		for(int i = 0 ; i<nNode ;i++) {
			if ((i != nodeidx) && (i != nodeMustVisit)) {
				if (!isSet1) {
					min1 = adjMatrix[nodeidx][i];
					isSet1 = true;
				}
				else {
					if ((adjMatrix[nodeidx][i] < min1) && (!isMin1Locked)) {
						min2 = min1;
						min1 = adjMatrix[nodeidx][i];
						isSet2 = true;
					}
					else if ((!isSet2) || (adjMatrix[nodeidx][i] < min2))  {
						min2 = adjMatrix[nodeidx][i];
						isSet2 = true;
					}
				}
			}
		}

		System.out.println("Node "+nodeidx+ "choosen path :"+min1+" "+min2);
		if(isMin1Locked){
			System.out.println("Must visit from"+ nodeidx+"to "+nodeMustVisit);
		}

		return min1+min2;
	}

	public void showVisitedNode(){
		for (int i = 0 ; i<nNode ;i++){
			System.out.print(visitedNode[i]+" ");
		}
		System.out.println(" ");
	}

	private int getNodeMustVisit(int nodeidx){
		int i = 0;
		int nodeMustVisit;


		while ((i < nNode-2) && (visitedNode[i] != nodeidx)){
			i++;
		}

		if (visitedNode[i] == nodeidx){
			nodeMustVisit = visitedNode[i+1];
		}
		else {
			nodeMustVisit = -1;
		}

		showVisitedNode();
		System.out.println("Node "+nodeidx+" must visit"+ nodeMustVisit );

		return nodeMustVisit;
	}


	public void calculateCost(){
		nodeCost = 0;
		for(int i =0 ; i<nNode ; i++){
			nodeCost = nodeCost + nodeCompleteTour(i);
		}
		nodeCost = nodeCost /2;
	}



	public void addProgress(int nodeVisited){
		visitedNode[progress] = nodeVisited;
		progress++;
	}
	

	

	public boolean[] getUnvisitedNode(){
		boolean isNodeVisited[] = new boolean [nNode];

		for (int i = 0 ; i < nNode ;i++){
			isNodeVisited[i] = false;
		}

		for (int i = 0 ; i<progress ;i++){
			isNodeVisited[visitedNode[i]] = true;
		}

		return isNodeVisited;

	}

	


	public static void main(String args[]){
		TSPMatrix matrix = new TSPMatrix();
		TSPNode node = new TSPNode();

		matrix.inputMatrixFromFile();
		node.connectToMatrix(matrix);
		node.calculateCost();
		System.out.println(node.getNodeCost());
		node.addProgress(3);
		node.calculateCost();
		System.out.println(node.getNodeCost());

	}



}