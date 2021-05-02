package LaiDebug.StringLinear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllAnagrams {

    public static void main(String[] args) {

        String lo = "75126yfjvwdmnsacb87wit3r1f3tuyqkrfhdbcwetadchjfjkuhvyqeteyidvscgfshsashdgayuet";
        String sh = "sh";
        System.out.println(allAnagrams(sh, lo).toString());

    }

    public static List<Integer> allAnagrams(String sh, String lo) {

        List<Integer> solution = new ArrayList<>();
        if(lo==null || lo.length()==0 || sh.length()==0)
            return solution;

        int size = sh.length();
        HashMap<Character, Integer> reference = new HashMap<>();
        for(int i=0; i<size; i++) {
            Integer value = reference.get(sh.charAt(i));
            reference.put(sh.charAt(i), value==null ? 1 : value+1);
        }

        for(int i=0; i<lo.length(); i++) {
            if(i-sh.length()>=0) {
                Integer value = reference.get(lo.charAt(i-sh.length()));
                if(value!=null) {
                    reference.put(lo.charAt(i-sh.length()), value+1);
                    size++;
                }
            }
            Integer value = reference.get(lo.charAt(i));
            if(value!=null) {
                reference.put(lo.charAt(i), value-1);
                size--;
            }
            if(size==0 && reference.get(lo.charAt(i))==0)
                solution.add(i-sh.length()+1);
        }

        return solution;
    }

}
