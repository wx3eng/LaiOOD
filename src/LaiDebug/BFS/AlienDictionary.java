package LaiDebug.BFS;

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {
        String[] words = new String[] {"wnrwjuhezuhmauwhgflfmzma","aezarvahlavgfv","zaajzuf","zmzznjuanhaaplwjp","pzarfujmvzufmewljnvhmjrzpjgn","jlrjnefpz","eufvlnzvwu","vpfuereujmufhaghrrjnzvwnaj","vlzgel","mgrzemlmuzmvrza","mzfgmgfhwfrhzere","mjegjw","mlenmjjwhhhvwfpfmehz","mhrujzuuew","lznaggjvnfeeajzumvwphezmjnmv","hwulgnpwjumumn","hlrevzwrvrvrml","unzgjjuprhhwugjamej","rgrhvnnzrhg","rupralvvjlhpelav","fzvjv","fnwjrepzejuzhg"};
        System.out.println(alienOrder(words));
    }

    public static String alienOrder(String[] words) {

        if(words==null || words.length==0)
            return "";

        List<Character> nodes = new ArrayList<>();
        Set<Character> noPreReqNodes = new HashSet<>();
        HashMap<Character, List<Character>> tracker = new HashMap<>();
        HashMap<Character, List<Character>> trackerBack = new HashMap<>();
        Deque<List<String>> queue = new ArrayDeque<>();
        queue.offerLast(Arrays.asList(words));

        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                List<String> current = queue.pollFirst();
                if(current.size()==1) {
                    String string = current.get(0);
                    for(int j=0; j<string.length(); j++)
                        if(!noPreReqNodes.contains(string.charAt(j))) {
                            noPreReqNodes.add(string.charAt(j));
                            nodes.add(string.charAt(j));
                        }
                    continue;
                }
                List<String> newList = new ArrayList<>();
                char ref = ' ';
                Set<Character> deDup = new HashSet<>();
                for (String s : current) {
                    if(count>=s.length())
                        continue;
                    char next = s.charAt(count);
                    if (next!=ref) {
                        if (deDup.contains(next))
                            return "";
                        deDup.add(next);
                        if(ref!=' ') {
                            List<Character> temp = tracker.computeIfAbsent(next, k -> new ArrayList<>());
                            List<Character> tempBack = trackerBack.computeIfAbsent(ref, k -> new ArrayList<>());
                            temp.add(ref);
                            tempBack.add(next);
                        }
                        if (newList.size()>0)
                            queue.offerLast(newList);
                        newList = new ArrayList<>();
                        ref = next;
                    }
                    newList.add(s);
                }
                if(newList.size()>0)
                    queue.offerLast(newList);
            }
            count++;
        }


        for(int i=nodes.size()-1; i>=0; i--)
            if(tracker.containsKey(nodes.get(i)))
                nodes.remove(i);

        StringBuilder solution = new StringBuilder();

        while(!nodes.isEmpty()) {
            int size = nodes.size();
            for(int i=size-1; i>=0; i--) {
                char cur = nodes.remove(i);
                solution.append(cur);
                List<Character> current = trackerBack.get(cur);
                if(current==null)
                    continue;
                for(char character : current) {
                    List<Character> temp = tracker.get(character);
                    boolean removed = temp.remove((Character) cur);
                    if(removed && temp.size()==0)
                        nodes.add(character);
                }
            }
        }

        return solution.toString();
    }

}
