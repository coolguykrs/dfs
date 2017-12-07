package ee.ttu.algoritmid.tsp;

import java.math.BigInteger;
import java.util.List;

public class TSP {

    DepthFirstSearch dfs = new DepthFirstSearch();

    /* Depth first search */
    public List<Integer> depthFirst(int[][] adjacencyMatrix) {
        return dfs.depthFirst(adjacencyMatrix);
    }

    /* Best first search */
    public List<Integer> bestFirst(int[][] adjacencyMatrix) {
        return null;
    }

    /* Nodes viewed in last matrix to find the solution (should be zeroed at the beginnig of search) */
    public BigInteger getCheckedNodesCount() {
        return BigInteger.valueOf(dfs.getNrOfNodesChecked());
    }
}