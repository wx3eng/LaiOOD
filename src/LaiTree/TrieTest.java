package LaiTree;

public class TrieTest {

    public static void main(String[] args) {

        Trie trie = new Trie();

        System.out.println("ADDING WORDS");

        System.out.println(trie.add("apple"));
        System.out.println(trie.add("pair"));
        System.out.println(trie.add("bear"));
        System.out.println(trie.add("app"));
        System.out.println(trie.add("apple"));
        System.out.println(trie.add("application"));
        System.out.println(trie.add(null));
        System.out.println(trie.add(""));
        System.out.println(trie.add(""));

        System.out.println(trie.size());
        System.out.println(trie.uniqueElements());

        System.out.println("SEARCHING WORDS");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("pair"));
        System.out.println(trie.search("bear"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("application"));
        System.out.println(trie.search(null));
        System.out.println(trie.search(""));
        System.out.println(trie.search(""));

        System.out.println(trie.size());
        System.out.println(trie.uniqueElements());

        System.out.println("DELETING WORDS");

        System.out.println(trie.delete("apple"));
        System.out.println(trie.delete("pair"));
        System.out.println(trie.delete("bear"));
        System.out.println(trie.delete("app"));
        System.out.println(trie.delete("apple"));
        System.out.println(trie.delete("application"));
        System.out.println(trie.delete(null));
        System.out.println(trie.delete(""));
        System.out.println(trie.delete(""));

        System.out.println(trie.size());
        System.out.println(trie.uniqueElements());
    }

}
