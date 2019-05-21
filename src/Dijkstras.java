import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
 
public class Dijkstras
{
    private int          distances[];
    private Set<Integer> settled;
    private Set<Integer> unsettled;
    private int          number_of_nodes;
    private int          adjacencyMatrix[][];
 
    public Dijkstras(int number_of_nodes)
    {
        this.number_of_nodes = number_of_nodes;
        setDistances(new int[number_of_nodes + 1]);
        settled = new HashSet<Integer>();
        unsettled = new HashSet<Integer>();
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
    }
 
    public void dijkstra_algorithm(int adjacency_matrix[][], int source)
    {
        int evaluationNode;
        //copy adjaceny matrix given by user
        for (int i = 1; i <= number_of_nodes; i++)
            for (int j = 1; j <= number_of_nodes; j++)
                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
 
        //initialize all nodes distances to infinity
        for (int i = 1; i <= number_of_nodes; i++)
        {
            getDistances()[i] = Integer.MAX_VALUE;
        }
 
        //add starting vetex to the unvisited list
        unsettled.add(source);
        //set starting vertex distance to 0
        getDistances()[source] = 0;
        //while there are still unvisited nodes
        while (!unsettled.isEmpty())
        {
            //Visit the edges leaving from source node and choose the one with lowest distance
        	evaluationNode = getNodeWithMinimumDistanceFromUnsettled();
            //remove the chosen node from the unvisited list
        	//as we know the lowest distance to visit it from source 
        	unsettled.remove(evaluationNode);
            //add the chosen node to the visited list
        	settled.add(evaluationNode);
            //visit the neighbours of the new chosen node and check their distance now from Source and chose lowest and repeat
        	evaluateNeighbours(evaluationNode);
        }
    }
 
    /** check for the minimum distance from source to unsettled nodes **/
    private int getNodeWithMinimumDistanceFromUnsettled()
    {
        int min;
        int node = 0;
 
        Iterator<Integer> iterator = unsettled.iterator();
        node = iterator.next();
        min = getDistances()[node];
        //check for the lowest distance to reach the next unvisited node
        for (int i = 1; i <= getDistances().length; i++)
        {
            if (unsettled.contains(i))
            {
            	if (getDistances()[i] <= min)
                {
                    min = getDistances()[i];
                    node = i;
                }
            }
        }
        return node;
    }
 
    private void evaluateNeighbours(int evaluationNode)
    {
        int edgeDistance = -1;
        int newDistance = -1;
 
        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++)
        {
            if (!settled.contains(destinationNode))
            {
                //if there is a path between the 2 nodes
            	if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
                {
                    //set edgeDistance to current weight
            		edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
                    newDistance = getDistances()[evaluationNode] + edgeDistance;
                    if (newDistance < getDistances()[destinationNode])
                    {
                        getDistances()[destinationNode] = newDistance;
                    }
                    unsettled.add(destinationNode);
                }
            }
        }
    }

	/**
	 * @return the distances
	 */
	public int[] getDistances() {
		return distances;
	}

	/**
	 * @param distances the distances to set
	 */
	public void setDistances(int distances[]) {
		this.distances = distances;
	}
}