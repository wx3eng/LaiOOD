package LaiDebug.BFS;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {

        String[] array = new String[]{"fog", "fet", "ceg", "feg", "fat", "gat", "get", "fot", "got"};
        List<List<String>> solution = findLadders("cog", "get", Arrays.asList(array));
        for(List<String> list : solution)
            System.out.println(list.toString());
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> solutions = new ArrayList<>();

        if(wordList==null || wordList.size()==0 || !isWordInList(wordList, endWord))
            return solutions;

        Map<String, List<String>> visited = new HashMap<>();
        Deque<String> words = new ArrayDeque<>();

        words.offerLast(beginWord);
        visited.put(beginWord, null);

        boolean keepGoing = true;

        while(!words.isEmpty() && keepGoing) {
            Set<String> temp = new HashSet<>();
            int size = words.size();
            for(int i=0; i<size; i++) {
                String current = words.pollFirst();
                for(String word : appendNewWords(visited, wordList, current, temp)) {
                    if(word.equals(endWord))
                        keepGoing = false;
                    List<String> previous = visited.get(word);
                    if(previous==null) {
                        previous = new ArrayList<>();
                        visited.put(word, previous);
                        previous.add(current);
                    }
                    else if(!isWordInList(previous, current))
                        previous.add(current);
                    words.offerLast(word);
                }
            }
        }

        if(visited.containsKey(endWord)) {
            addSolutions(solutions, endWord, visited, new ArrayList<>());
            reverseSolutions(solutions);
        }
        return solutions;
    }

    private static List<String> appendNewWords(Map<String, List<String>> visited, List<String> wordList, String word, Set<String> temp) {

        List<String> solution = new ArrayList<>();

        for(String words : wordList) {
            if(visited.containsKey(words)) {
                if(!temp.contains(words))
                    continue;
            }
            int count = 1;
            int index = 0;
            while(index<word.length()) {
                if(count<0)
                    break;
                if(word.charAt(index)!=words.charAt(index))
                    count--;
                index++;
            }
            if(count==0) {
                solution.add(words);
                temp.add(words);
            }
        }

        return solution;
    }

    private static boolean isWordInList(List<String> wordList, String word) {
        for(String theWord : wordList)
            if(theWord.equals(word))
                return true;
        return false;
    }


    private static void addSolutions(List<List<String>> solutions, String tail, Map<String, List<String>> visited, List<String> current) {

        current.add(tail);
        List<String> reference = visited.get(tail);
        if(reference==null)
            solutions.add(new ArrayList<>(current));
        else
            for(String cur : reference)
                addSolutions(solutions, cur, visited, current);
        current.remove(current.size()-1);
    }

    private static void reverseSolutions(List<List<String>> solutions) {

        Deque<String> queue = new ArrayDeque<>();

        for(int i=0; i<solutions.size(); i++) {
            List<String> solution = solutions.get(i);
            for(int j=solution.size()-1; j>=0; j--)
                queue.offerFirst(solution.remove(j));
            while(!queue.isEmpty())
                solution.add(queue.pollLast());
        }
    }
}
