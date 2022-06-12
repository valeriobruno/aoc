package it.valeriobruno.aoc2021;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Day12Caves {

    Node startNode;
    Node endNode;

    Map<String, Node> allNodes;

    public Day12Caves() {
        startNode = new Node("start");
        endNode = new Node("end");
        allNodes = new HashMap<>();

        allNodes.put("start", startNode);
        allNodes.put("end", endNode);

    }

    class Node implements Comparable<Node> {
        String name;
        TreeSet<Node> linkedNodes;

        public Node(String name) {
            this.name = name;
            linkedNodes = new TreeSet<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node)
                return this.name.equals(((Node) obj).name);
            else return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public int compareTo(@NotNull Node o) {
            return this.name.compareTo(o.name);
        }

        public boolean linkNode(Node node) {
            return linkedNodes.add(node);
        }

        @Override
        public String toString() {
            return "[" + name + "]";
        }

        public Iterator<Node> linksIterator() {
            return linkedNodes.iterator();
        }

        public boolean isBigCave() {
            return name.charAt(0) >= 'A' && name.charAt(0) <= 'Z';
        }
    }

    public void parseFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] labels = line.split("-");
            Node lastNode = null;

            for (String label : labels) {
                Node node = new Node(label);
                allNodes.putIfAbsent(label, node);
                node = allNodes.get(label);

                if (lastNode != null) {
                    lastNode.linkNode(node);
                    node.linkNode(lastNode);


                }
                lastNode = node;
            }

        }
        System.out.println(allNodes);

    }

    public long visitNode(Node node, List<Node> currentPath, long metPaths) {
        Iterator<Node> it = node.linksIterator();

        while (it.hasNext()) {
            Node nextNode = it.next();

            if (startNode.equals(nextNode))
                continue;
            if (canBeAddedToPath2(nextNode, currentPath)) {
                currentPath.add(nextNode);
                if (endNode.equals(nextNode)) {
                    System.out.println("Found path: " + currentPath);
                    metPaths++;
                    currentPath.remove(currentPath.size()-1);
                    continue;
                } else {
                    metPaths = visitNode(nextNode, currentPath, metPaths);
                    currentPath.remove(currentPath.size() - 1);
                }
            }
        }

        return metPaths;
    }

    private boolean canBeAddedToPath(Node nextNode, List<Node> currentPath) {
        if (nextNode.isBigCave() || !currentPath.contains(nextNode))
            return true;
        else return false;
    }

    private boolean canBeAddedToPath2(Node nextNode, List<Node> currentPath) {
        if (nextNode.isBigCave())
            return true;
        else{
            Map<String,Integer> counters = new HashMap<>();
            currentPath.stream()
                    .filter( node -> !node.isBigCave())
                    .forEach(node ->
                    {
                        int value = counters.getOrDefault(node.name,0);
                        counters.put(node.name,value+1);
                    });

            return counters.values().stream().allMatch(value -> value < 2) || !counters.containsKey(nextNode.name);
        }

    }

    public static void main(String[] args) throws IOException {

        Day12Caves day12 = new Day12Caves();
        day12.parseFile(new File("input-2021-12.txt"));
        long metPaths = day12.visitNode(day12.startNode, new LinkedList<>(Lists.newArrayList(day12.startNode)), 0L);
        System.out.println(metPaths);
    }


}
