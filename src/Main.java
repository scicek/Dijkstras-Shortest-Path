/*************************************************************************
 * Written by: Simon Cicek                                               *
 * Last changed: 2012-05-09                                              *
 *                                                                       *
 * The main class of the program, initializing and executing everything. *
 *************************************************************************/

public class Main 
{
    public static void main(String[] args)
    {
        WDGraph g = new WDGraph();
        g.addVertex("Stockholm");
        g.addVertex("Göteborg");
        g.addVertex("Malmö");
        g.addVertex("Uppsala");
        g.addVertex("Västerås");
        
        g.addEdge("Stockholm", "Uppsala", 70);
        g.addEdge("Stockholm", "Malmö", 613);
        g.addEdge("Göteborg", "Uppsala", 453);
        g.addEdge("Göteborg", "Stockholm", 471);
        g.addEdge("Göteborg", "Västerås", 376);
        g.addEdge("Uppsala", "Göteborg", 452);
        g.addEdge("Uppsala", "Malmö", 679);
        g.addEdge("Västerås", "Malmö", 599);
         
        WDGraph sp2 = g.optimalShortestPath("Stockholm",true);
        System.out.println("\nOptimal Shortest Path from 'Stockholm' to all the other vertices:");
        System.out.println(sp2);
    }
}
