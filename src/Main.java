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
        
        /*
        WDGraph test = new WDGraph();
        test.addVertex("A");
        test.addVertex("B");
        test.addVertex("C");
        test.addVertex("D");
        test.addVertex("E");
        
        test.addEdge("A", "C", 20);
        test.addEdge("A", "D", 40);
        test.addEdge("B", "D", 20);
        test.addEdge("B", "A", 30);
        test.addEdge("B", "E", 50);
        test.addEdge("D", "C", 10);
        test.addEdge("D", "B", 70);
        test.addEdge("E", "C", 90);
        
        WDGraph sp2 = test.shortestPath("A",true);
        WDGraph sp3 = test.optimalShortestPath("A",true);
        System.out.println("\nShortest Path from 'A' to all the other vertices:");
        System.out.println(sp2);
        System.out.println("\nOptimal Shortest Path from 'A' to all the other vertices:");
        System.out.println(sp3);*/
    }
}
