import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}
}

public class TrieMatchingExtended implements Runnable {
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
        for (int i = 0; i < text.length(); i++) {
            prefixMatching(text, trie, i, result);
        }
        return result;
    }

    List<Node> buildTrie (List<String> patterns, int n) {
        List<Node> trie = new ArrayList<>();
        Node root = new Node();
        trie.add(root);
        int count = 0;
        for (String pattern : patterns) {
            Node cur = root;
            for (char curChar : pattern.toCharArray()) {
                int index = letterToIndex(curChar);
                if (cur.next[index] == Node.NA) {
                    cur.next[index] = ++count;
                    cur = new Node();
                    trie.add(cur);
                }
                else
                    cur = trie.get(cur.next[index]);
            }
            cur.patternEnd = true;
        }
        return trie;
    }

    void prefixMatching (String text, List<Node> trie, int start, List<Integer> result) {
        Node cur = trie.get(0);
        if (trie.size() <= 1)
            return;
        for (int i = start; i < text.length(); i++) {
            int index = letterToIndex(text.charAt(i));
            if (cur.next[index] != Node.NA) {
                cur = trie.get(cur.next[index]);
            } else {
                return;
            }
            if (cur.patternEnd) {
                if (!result.isEmpty() && result.get(result.size() - 1) == start)
                    continue;
                result.add(start);
            }
        }
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
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
