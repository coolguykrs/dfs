package ee.ttu.algoritmid.tsp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DepthFirstSearch {

    public static void main(String[] args) {
        try {
            TSP tsp = new TSP();
            tsp.depthFirst(MatrixLoader.loadFile("eesti.in", 4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int nrOfCities;
    private int nrOfNodesChecked;
    private int bestDistance;
    private int[][] adjacencyMatrix;
    private int passedCities[];
    private int bestPath[];

    public List<Integer> depthFirst(int[][] adjacencyMatrix) {
        if (adjacencyMatrix.length == 0) return Collections.emptyList();
        initialize(adjacencyMatrix);
        findBestPath(0, 0, 0);
        List<Integer> optimalRoute = Arrays.stream(bestPath).boxed().collect(Collectors.toList());
//        if (adjacencyMatrix.length > 1) {
            optimalRoute.add(0);
//        }
        return optimalRoute;
    }

    private void initialize(int[][] adjacencyMatrix) {
        nrOfCities = adjacencyMatrix.length;
        nrOfNodesChecked = 0;
        bestDistance = Integer.MAX_VALUE;
        this.adjacencyMatrix = adjacencyMatrix;
        passedCities = new int[nrOfCities];
        bestPath = new int[nrOfCities];
    }

    private void findBestPath(int currentCity, int lastCity, int passedDistance) {
        System.out.println(lastCity);
        nrOfNodesChecked++;
        passedCities[lastCity] = currentCity;
        if (lastCity == (nrOfCities - 1)) {
            finishTravelling(currentCity, passedDistance);
        } else {
            goToNextCity(currentCity, lastCity, passedDistance);
        }
    }

    private void finishTravelling(int currentCity, int passedDistance) {
        int length = passedDistance + adjacencyMatrix[currentCity][0];
        if (length < bestDistance) {
            bestDistance = length;
            System.arraycopy(passedCities, 0, bestPath, 0, passedCities.length);
        }
    }

    private void goToNextCity(int currentCity, int lastCity, int passedDistance) {
        for (int city = 0; city < nrOfCities; city++) {
            if ((passedDistance + adjacencyMatrix[currentCity][city]) < bestDistance) {
                for (int i = 0; i <= lastCity; i++) {
                    if (passedCities[i] == city)
                        break;
                    else if (i == lastCity)
                        findBestPath(city, lastCity + 1, passedDistance + adjacencyMatrix[currentCity][city]);
                }
            }
        }
    }

    public int getNrOfNodesChecked() {
        return nrOfNodesChecked;
    }
}
