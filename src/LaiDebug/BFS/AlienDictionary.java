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
        HashMap<Character, List<Character>> tracker = new HashMap<>();
        Deque<List<String>> queue = new ArrayDeque<>();
        queue.offerLast(Arrays.asList(words));

        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                List<String> current = queue.pollFirst();
                List<String> newList = new ArrayList<>();
                char ref = ' ';
                Set<Character> deDup = new HashSet<>();
                for (String s : current) {
                    if(count>=s.length())
                        continue;
                    char next = s.charAt(count);
                    if(!nodes.contains(next))
                        nodes.add(next);
                    if (next!=ref) {
                        if (deDup.contains(next))
                            return "";
                        deDup.add(next);
                        if(ref!=' ') {
                            List<Character> temp = tracker.computeIfAbsent(ref, k -> new ArrayList<>());
                            temp.add(next);
                        }
                        if (newList.size()>=2)
                            queue.offerLast(newList);
                        newList = new ArrayList<>();
                        ref = next;
                    }
                    newList.add(s);
                }
                if(newList.size()>=2)
                    queue.offerLast(newList);
            }
            count++;
        }
        return null;
    }

}
