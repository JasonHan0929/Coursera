import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
	}
}

public class TrieMatching implements Runnable {
    int letterToIndex (char letter)
    {
        switch (letter)
        {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: assert (false); return Node.NA;
        }
    }

    List <Integer> solve (String text, int n, List <String> patterns) {
        List <Integer> result = new ArrayList <Integer> ();
        List<Node> trie = buildTrie(patterns, n);
        for (int i = 0; i < text.length(); i++)
            prefixMatch(trie, text, i, result);
        return result;
    }

    List<Node> buildTrie (List<String> patterns, int n) {
        List<Node> trie = new ArrayList<>();
        Node root = new Node();
        trie.add(root);
        int count = 0;
        for (String pattern : patterns) {
            Node cur = root;
            for (char symbol : pattern.toCharArray()) {
                int index = letterToIndex(symbol);
                if (cur.next[index] == Node.NA) {
                    cur.next[index] = ++count;
                    cur = new Node();
                    trie.add(cur);
                }
                else {
                    cur = trie.get(cur.next[index]);
                }
            }
        }
        return trie;
    }

    void prefixMatch (List<Node> trie, String text, int start, List<Integer> result) {
        Node cur = trie.get(0);
        if (trie.size() <= 1)
            return;
        for (int i = start; i < text.length(); i++) {
            if (checkLeaf(cur)) {
                result.add(start);
                return;
            }
            int index = letterToIndex(text.charAt(i));
            if (cur.next[index] != Node.NA)
                cur = trie.get(cur.next[index]);
            else
                return;
            if (i == text.length() - 1 && checkLeaf(cur)) {
                result.add(start);
                return;
            }
        }
    }

    boolean checkLeaf (Node node) {
        for (int index : node.next) {
            if (index != Node.NA)
                return false;
        }
        return true;
    }

    public void run () {
        try {
            BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
            String text = in.readLine ();
            int n = Integer.parseInt (in.readLine ());
            List <String> patterns = new ArrayList <String> ();
            for (int i = 0; i < n; i++) {
                patterns.add (in.readLine ());
            }

            List <Integer> ans = solve (text, n, patterns);

            for (int j = 0; j < ans.size (); j++) {
                System.out.print ("" + ans.get (j));
                System.out.print (j + 1 < ans.size () ? " " : "\n");
            }
        }
        catch (Throwable e) {
            e.printStackTrace ();
            System.exit (1);
        }
    }

    public static void main (String [] args) {
        new Thread (new TrieMatching ()).start ();
    }
}
