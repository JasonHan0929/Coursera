import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixTree {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Node {
        int[] edge = new int[2]; //[start, length]
        boolean isLeaf = true;
        Node[] next = new int[5]; //[A, C, T, G, $]
        int start;

        Node(int start, int length, int start) {
            edge[0] = start;
            edge[1] = length;
            this.start = start;
        }
    }

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding 
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> result = new ArrayList<String>();
        Node root = new Node(-1, 0, 0), cur = root;
        int n = text.length();
        for (int i = 0; i < n; i++) {
            int len = n - i;
            for (int j = i; j < len; j++) {
                int index = charToInt(text.charAt(j));
                if (cur.next[index] == null) {
                    cur.isLeaf = false;
                    cur.edge[1] = j - i;
                    cur.next[index] = new Node(j, len, i);
                    cur = root;
                    break;
                } else {
                    cur = cur.next[index];
                }
            }
        }
        for ()
        return result;
    }

    public int charToInt(char c) {
        switch(c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'T': return 2;
            case 'G': return 3;
            case '$': return 4;
        }
        return -1;
    }

    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text + "$");
        print(edges);
    }
}
