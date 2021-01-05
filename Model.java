/**
 * A package model that contains class Model which implements the Dijkstra and Bellman Ford algorithms 
 */
package lab5Algorithms.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The class that is responsible for the algorithms
 * @author Emilia Gosiewska
 * @version 5.0
 */
public class Model {

    /**
     * creating new linked list 
     */
    List<List<Node>> adjList = new LinkedList<>();

    /**
     * The constructor that takes al as a parameter which is signed to adjList
     *
     * @param al is a adjacency list
     */
    public Model(List<List<Node>> al) {
        adjList = al;
    }

//Bellman-Ford algorithm
    /**
     * This is a method which is responsible for performing Bellman Ford
     * algorithm
     *
     * @param src is a source point
     * @return returns the distans between nodes
     * @throws NegativeNodeException throws an exception in case of the negative
     * cycle is found
     */
    public List<Integer> bellmanFord(int src) throws NegativeNodeException {
        int numberOfVertices = adjList.size();
        List<Integer> distance = new ArrayList<>(numberOfVertices );
        for (int i = 0; i < numberOfVertices ; i++) 
            {
            distance.add(Integer.MAX_VALUE);
            }
        // initialize distance of source as 0 
        distance.set(src, 0);
        for (int i = 0; i < numberOfVertices  - 1; i++) {
            if (adjList.get(i).isEmpty()) {
                continue;
            }
            for (int j = 0; j < numberOfVertices ; j++) {
                for (Node edge : adjList.get(j)) {
                    int sum = distance.get(j) + edge.getCost();
                    if (distance.get(j) != Integer.MAX_VALUE && sum < distance.get(edge.getNode())) {
                        distance.set(edge.getNode(), sum);
                    }

                }
            }
        }

// check for negative-weight cycles. 
        for (int j = 0; j < numberOfVertices ; j++) {
            for (Node edge : adjList.get(j)) {
                if (distance.get(j) != Integer.MAX_VALUE && distance.get(j) + edge.getCost() < distance.get(edge.getNode())) {
                    throw new NegativeNodeException("The given graph contains negative cycle");
                }
                return distance;
            }
        }
        return distance;
    }

    /**
     * This is a method which is responsible for performing Dijkstra algorithm
     *
     * @param source is a source point where we start
     * @return returns the distance between nodes
     * @throws NegativeNodeException negative exception in case of negative cycle
     */
    public List<Integer> algoDijkstra(int source) throws NegativeNodeException {
        int verticesNumbers = adjList.size();
        List<Integer> distance = new ArrayList<>(verticesNumbers );
        for (int i = 0; i < verticesNumbers ; i++) 
            {
            distance.add(Integer.MAX_VALUE);
            }

        //using lambda expression to compare nodes
        PriorityQueue<Node> pqueue = new PriorityQueue<>(verticesNumbers, (n1, n2) -> n1.compare(n2));

        pqueue.add(new Node(source, 0));

        while (!pqueue.isEmpty()) {
            Node node = pqueue.remove();

            if (node.getCost() >= distance.get(node.getNode())) {
                continue;
            }

            distance.set(node.getNode(), node.getCost());

            List<Node> edges = adjList.get(node.getNode());

            for (int i = 0; i < edges.size(); i++) {
                int dist = distance.get(node.getNode()) + edges.get(i).getCost();
                if(dist > 0)
                {
                if (dist < distance.get(edges.get(i).getNode())) {
                    Node candidate = new Node(edges.get(i).getNode(), dist);
                    pqueue.add(candidate);
                    }
                }
                else 
                {
                    throw new NegativeNodeException("Negative cycle has occured!");
                }
            }   
        }
        return distance;
    
}
}
