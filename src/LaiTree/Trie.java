package LaiTree;

public class Trie {

    private int size;
    private int sizeUnique;
    private final TrieNode root;

    public Trie() {
        this.size = 0;
        this.sizeUnique = 0;
        this.root = new TrieNode();
    }

    public Integer add(String word) {

        if(word == null) {
            return null;
        }

        TrieNode temp = root;

        for(int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if(temp.edges[current - 'a'] == null) {
                temp.edges[current - 'a'] = new TrieNode();
                temp.numOfEdges++;
            }
            temp = temp.edges[current - 'a'];
        }

        if(temp.count == 0) {
            sizeUnique++;
        }
        size++;
        temp.count++;

        return temp.count;
    }

    public Integer delete(String word) {

        if(word == null) {
            return null;
        }

        TrieNode temp = root;
        TrieNode anchor = root;
        char route = '1';

        for(int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if(temp.edges[current - 'a'] == null) {
                return 0;
            }
            if(temp.numOfEdges > 1 || temp.count == 0) {
                anchor = temp;
                route = current;
            }
            temp = temp.edges[current - 'a'];
        }

        temp.count--;
        size--;
        if(temp.count == 0) {
            sizeUnique--;
            if(temp.numOfEdges == 0 && anchor != root) {
                anchor.edges[route - 'a'] = null;
                anchor.numOfEdges--;
            }
        }

        return temp.count;
    }

    public Integer search(String word) {

        if(word == null) {
            return null;
        }

        TrieNode temp = root;

        for(int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if(temp.edges[current - 'a'] == null) {
                return 0;
            }
            temp = temp.edges[current - 'a'];
        }

        return temp.count;
    }

    public int size() {
        return size;
    }

    public int uniqueElements() {
        return sizeUnique;
    }

    private static class TrieNode {

        private final TrieNode[] edges;
        int numOfEdges;
        private int count;

        private TrieNode() {
            this.edges = new TrieNode[26];
            this.numOfEdges = 0;
            this.count = 0;
        }
    }
}
