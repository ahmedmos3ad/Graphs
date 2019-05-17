

public class Prims {

    public boolean mstSet[];
    public int parent[];
    public int key[];
    public int size;

    Prims(int V, int source) {
        size = V;
        mstSet = new boolean[size];
        parent = new int[size];
        key = new int[size];
        for (int i = 0; i < size; i++) {   //Intialize all keys to INFINITE
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[source] = 0;
        parent[source] = -1;
    }

    public void primMST(int graph[][]) {
        for (int i = 0; i < size - 1; i++) {
            int z = minKey(key, mstSet);
            mstSet[z] = true;

            for (int j = 0; j < size; j++) {
                if (graph[z][j] != 0 && mstSet[j] == false && graph[z][j] < key[j]) {
                    parent[j] = z;
                    key[j] = graph[z][j];
                }
            }
        }

    }

    public int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int xMin = -1;

        for (int i = 0; i < size; i++) {
            if (mstSet[i] == false && key[i] < min) {
                min = key[i];
                xMin = i;
            }
        }
        return xMin;
    }

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
