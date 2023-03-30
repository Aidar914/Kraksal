import java.util.*;

class Node{
    int number;
    public Node(int number) {
        this.number = number;
    }
}
class Edge implements Comparable<Edge> {
    int first, second, length;

    public Edge(int first, int second, int length) {
        this.length = length;
        this.first = first;
        this.second = second;
    }

    public int compareTo(Edge arr) {
        if (this.length > arr.length) {
            return 1;
        } else if (this.length == arr.length) {
            return 0;
        } else {
            return -1;
        }
    }
}


public class Main {
    public static int MST(List<Edge> edges, List<Node> nodes){
        Collections.sort(edges);
        int result = 0;
        int i = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Node j: nodes){
            map.put(j.number, 0);
        }
        while (i != edges.size()) {
            int f = edges.get(i).first;
            int s = edges.get(i).second;
            if (map.get(f) == 0 && map.get(s) == 0){
                map.put(f, f);
                map.put(s, f);
                result += edges.get(i).length;
            } else if (map.get(f) == 0) {
                map.put(f, map.get(s));
                result += edges.get(i).length;
            } else if (map.get(s) == 0) {
                map.put(s, map.get(f));
                result += edges.get(i).length;
            } else if (!Objects.equals(map.get(f), map.get(s))) {
                for (int j: map.keySet()){
                    if (map.get(j) == s){
                        map.put(j, f);
                    }
                }
            }
            i++;
        }
        return result;
    }
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 7), new Edge(1, 2, 8), new Edge(0, 3, 5),
                new Edge(1, 3, 9), new Edge(1, 4, 7), new Edge(2, 4, 5),
                new Edge(3, 4, 15), new Edge(3, 5, 6), new Edge(4, 5, 8),
                new Edge(4, 6, 9), new Edge(5, 6, 11));
        List<Node> nodes = Arrays.asList(new Node(0), new Node(1),new Node(2),new Node(3),new Node(4),
                new Node(5),new Node(6));
        System.out.println(MST(edges, nodes));
    }

}