package LaiDebug.TopKWords;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SolutionTest {

    public static void main(String[] args) {
        String[] words = new String[] {"a", "a", "b"};
        System.out.println(topKFrequent(words, 2));
    }

    public static String[] topKFrequent(String[] combo, int k) {

        HashMap<String, Integer> pairedSet = new HashMap<>();
        for(int i=0; i<combo.length; i++) {
            Integer size = pairedSet.get(combo[i]);
            pairedSet.put(combo[i], (size==null) ? 1 : size+1);
        }

        PriorityQueue<Pair> reference = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if(p1.count==p2.count) return 0;
                return p1.count < p2.count ? -1 : 1;
            }
        });

        for(int i=0; i<combo.length; i++) {
            Integer element = pairedSet.remove(combo[i]);
            if(element==null) continue;
            if(reference.size()<k) {
                reference.offer(new Pair(combo[i], element));
            }
            else if(reference.peek().count<element) {
                reference.poll();
                reference.offer(new Pair(combo[i], element));
            }
        }

        String[] solution = new String[reference.size()];
        for(int i=0; i<solution.length; i++) {
            solution[i] = reference.poll().word;
        }

        return solution;

    }

    static class Pair {
        String word;
        int count;
        Pair(String theWord, int theCount) {
            word = theWord;
            count = theCount;
        }
    }
}
