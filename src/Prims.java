

public class Prims {

    public boolean mstSet[];
    public int parent[];
    public int key[];
    public int size;

    Prims(int V, int source) {
        size = V;
        //To represent set of vertices not yet included in MST 
        mstSet = new boolean[size];
        //Array to store constructed MST 
        parent = new int[size];
        //Key values used to pick minimum weight edge in cut
        key = new int[size];
        //Intialize all keys to INFINITE
        for (int i = 0; i < size; i++) {   
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
     //Setting Source as first vertex in MST. 
        key[source] = 0;
        parent[source] = -1;
    }

    public void primMST(int graph[][]) {
        for (int i = 0; i < size - 1; i++) {
        	 //Pick the minimum key vertex from the set of vertices 
            //not yet included in MST
        	int z = minKey(key, mstSet);
            //Add the picked vertex to the MST Set 
            mstSet[z] = true;

            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int j = 0; j < size; j++) {
            	// graph[z][j] is non zero only for adjacent vertices of m 
                // mstSet[j] is false for vertices not yet included in MST 
                // Update the key only if graph[z][j] is smaller than key[j] 
            	if (graph[z][j] != 0 && mstSet[j] == false && graph[z][j] < key[j]) {
                    parent[j] = z;
                    key[j] = graph[z][j];
                }
            }
        }

    }
    // A utility function to find the vertex with minimum key 
    // value, from the set of vertices not yet included in MST
    public int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int xMin = -1;

        //Initalize Minimum Value
        for (int i = 0; i < size; i++) {
            if (mstSet[i] == false && key[i] < min) {
                min = key[i];
                xMin = i;
            }
        }
        return xMin;
    }

    //Function to Print the MST
    public void printMST(int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 0; i < size; i++) {
            if (parent[i] == -1) {
                System.out.println(i + " -(" + parent[i] + ")\t" + "0");
                continue;
            }
            System.out.println(i + " - " + parent[i] + "\t" + graph[i][parent[i]]);
        }
    }

}
