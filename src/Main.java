import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int adjacency_matrix[][];
		int number_of_vertices;
	    int source = 0;
	    Scanner scan = new Scanner(System.in);
	    char ch;

	    do {
	    	System.out.println("Graph\n");
	        System.out.println("1. Prims(Minimum Spanning Tree) ");
	        System.out.println("2. Dijkstras(Shortest Path Between Two Vertices)");
	       int choice = scan.nextInt();
	       switch(choice)
	       {
	       case 1:
	       try
	        {
	            System.out.println("Enter the number of vertices");
	            number_of_vertices = scan.nextInt();	
	            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
	 
	            System.out.println("Enter the Weighted Matrix for the graph");
	            for (int i = 1; i <= number_of_vertices; i++)
	            {
	            	System.out.println("Enter ROW Number "+i+" of the Matrix ("+number_of_vertices+" inputs)");
	            	for (int j = 1; j <= number_of_vertices; j++)
	                {
	                	adjacency_matrix[i][j] = scan.nextInt();
	                    if (i == j)
	                    {
	                        adjacency_matrix[i][j] = 0;
	                        continue;
	                    }
	                    if (adjacency_matrix[i][j] == 0)
	                    {
	                        adjacency_matrix[i][j] = Integer.MAX_VALUE;
	                    }
	                }
	            }
	 
	            Prims prims = new Prims(number_of_vertices);
	            prims.primsAlgorithm(adjacency_matrix);
	            System.out.println("Enter the source ");
	            source = scan.nextInt();
	            prims.printMST(source);
	 
	        } catch (InputMismatchException inputMismatch)
	        	{
	            System.out.println("Wrong Input Format");
	        	}
	       break;
	       case 2:
	    	   try
	           {
	               System.out.println("Enter the number of vertices");
	               number_of_vertices = scan.nextInt();
	               adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
	    
	               System.out.println("Enter the Weighted Matrix for the graph");
	               for (int i = 1; i <= number_of_vertices; i++)
	               {
	            	   System.out.println("Enter ROW Number "+i+" of the Matrix ("+number_of_vertices+" inputs)");
	            	   for (int j = 1; j <= number_of_vertices; j++)
	                   {
	                       adjacency_matrix[i][j] = scan.nextInt();
	                       if (i == j)
	                       {
	                           adjacency_matrix[i][j] = 0;
	                           continue;
	                       }
	                       if (adjacency_matrix[i][j] == 0)
	                       {
	                           adjacency_matrix[i][j] = Integer.MAX_VALUE;
	                       }
	                   }
	               }
	    
	               System.out.println("Enter the source ");
	               source = scan.nextInt(); 
	               Dijkstras dijkstrasAlgorithm = new Dijkstras(
	                       number_of_vertices);
	               dijkstrasAlgorithm.dijkstra_algorithm(adjacency_matrix, source);
	    
	               for (int i = 1; i <= dijkstrasAlgorithm.getDistances().length - 1; i++)
	               {
	                   if (i <= number_of_vertices)
	                   {	if(dijkstrasAlgorithm.getDistances()[i]==Integer.MAX_VALUE)
	                	   		System.out.println("The Shortest Path from " + source + " to " + i + " is: "+"INF");
	                	   	else System.out.println("The Shortest Path from " + source + " to " + i + " is: "+dijkstrasAlgorithm.getDistances()[i]);
	                   }
	               }
	           } catch (InputMismatchException inputMismatch)
	           {
	               System.out.println("Wrong Input Format");
	           }
	       break;
	       default : 
               System.out.println("Wrong Entry \n ");
               break; 
	       }   
	       System.out.println("\nDo you want to continue (Type y or n) \n");
	       ch = scan.next().charAt(0);
	     }while (ch == 'Y'|| ch == 'y');
	}
}