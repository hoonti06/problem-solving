package boj.p6XXX.p6416;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Map<Integer, List<Integer>> edgeMap;
    static Set<Integer> toSet;
    static Map<Integer, Integer> indegree;
    static Set<Integer> nodes;


    static int findRoot() {
        int root = -1;
        int rootCnt = 0;
        for(int node : nodes) {
            if (indegree.get(node) == null) {
                root = node;
                rootCnt++;
            }
            else if (indegree.get(node) > 1) {
                return -1;
            }
        }
        if (rootCnt != 1) return -1;
        return root;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean isFin = false;
        int k = 1;
        while (true) {
            toSet = new HashSet<>();
            indegree = new HashMap<>();
            nodes = new HashSet<>();
            edgeMap = new HashMap<>();
            while (true) {
//            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                String line = in.readLine();
                if (line.length() == 0) break;
                String[] split = line.split(" ");
                if (split[0].equals("-1") && split[1].equals("-1")) {
                    isFin = true;
                    break;
                }

                int halfLen = split.length / 3 + 1;
                boolean next = false;
                for (int i = 0; i < halfLen; i++) {
                    int from = Integer.parseInt(split[i*3]);
                    int to = Integer.parseInt(split[i*3+1]);
                    if (from == 0 && to == 0) break;

                    List<Integer> list = edgeMap.getOrDefault(from, new ArrayList<>());
                    list.add(to);
                    indegree.put(to, indegree.getOrDefault(to, 0) + 1);
                    edgeMap.put(from, list);
                    toSet.add(to);
                    nodes.add(from);
                    nodes.add(to);
                }
            }

            boolean isTree = true;

            int root = -1;
            if (nodes.size() > 0) {
                root = findRoot();
                if (root == -1) isTree = false;
            }

            if (root != -1) {
                Set<Integer> visitSet = new HashSet<>();
                Queue<Integer> q = new ArrayDeque<>();
                q.offer(root);
                while (!q.isEmpty()) {
                    int from = q.poll();
                    if (visitSet.contains(from)) {
                        isTree = false;
                        break;
                    }

                    visitSet.add(from);
                    List<Integer> fromEdge = edgeMap.get(from);
                    if (fromEdge == null) continue;
                    for (int i = 0; i < fromEdge.size(); i++) {
                        int to = fromEdge.get(i);
                        q.offer(to);
                    }
                }
                if (visitSet.size() != nodes.size())
                    isTree = false;
            }

            sb.append("Case ")
                    .append(k)
                    .append(" is ")
                    .append(isTree? "" : "not ")
                    .append("a tree.\n");
            k++;
            if (isFin) break;
        }
        System.out.println(sb);
    }
}
