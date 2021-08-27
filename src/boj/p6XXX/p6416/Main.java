package boj.p6XXX.p6416;

import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> edges;
    static Map<Integer, Integer> indegree;
    static Set<Integer> nodes;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("in.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean isFin = false;
        int cnt = 1;
        while (true) {
            indegree = new HashMap<>();
            nodes = new HashSet<>();
            edges = new HashMap<>();

            boolean testcaseFin = false;
            while (!testcaseFin) {
                String line = in.readLine();
                if (line == null) {
                    isFin = true;
                    break;
                } else if (line.length() == 0) {
                    continue;
                }

                StringTokenizer st = new StringTokenizer(line, " ");
                do {
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    if (from < 0 && to < 0) {
                        testcaseFin = true;
                        isFin = true;
                        break;
                    } else if (from == 0 && to == 0) {
                        testcaseFin = true;
                        break;
                    }

                    indegree.put(to, indegree.getOrDefault(to, 0) + 1);

                    List<Integer> list = edges.getOrDefault(from, new ArrayList<>());
                    list.add(to);
                    edges.put(from, list);

                    nodes.add(from);
                    nodes.add(to);

                } while (st.hasMoreTokens());
            }
            if (isFin) {
                break;
            }

            boolean isTree = true;
            int root = -1;
            if (nodes.size() > 0) {
                int rootCnt = 0;
                for(int node : nodes) {
                    int indegreeCnt = indegree.getOrDefault(node, 0);
                    if (indegreeCnt == 0) {
                        root = node;
                        rootCnt++;
                    } else if (indegreeCnt > 1) {
                        isTree = false;
                        break;
                    }
                }
                if (rootCnt != 1) {
                    isTree = false;
                }

                if (isTree) {
                    Set<Integer> visited = new HashSet<>();
                    Queue<Integer> q = new ArrayDeque<>();
                    q.offer(root);
                    while (!q.isEmpty()) {
                        int from = q.poll();
                        if (visited.contains(from)) {
                            isTree = false;
                            break;
                        }
                        visited.add(from);

                        for (Integer to : edges.getOrDefault(from, new ArrayList<>())) {
                            q.offer(to);
                        }
                    }
                    if (visited.size() != nodes.size()) {
                        isTree = false;
                    }
                }
            }

            sb.append("Case ")
                    .append(cnt)
                    .append(" is ")
                    .append(isTree? "" : "not ")
                    .append("a tree.\n");
            cnt++;
        }
        System.out.print(sb);
    }
}
