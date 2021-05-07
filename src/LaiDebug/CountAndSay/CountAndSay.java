package LaiDebug.CountAndSay;

import java.util.ArrayList;
import java.util.List;

public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(countAndSay(9));
    }

    public static List<String> countAndSay(int n) {
        List<String> solution = new ArrayList<>();
        if(n<1) return solution;
        solution.add("1");
        StringBuilder result = new StringBuilder();
        result.append(1);
        for(int i=2; i<=n; i++) {
            StringBuilder current = new StringBuilder();
            int slow = 0;
            int fast = 1;
            while(fast<result.length()) {
                if(result.charAt(fast)!=result.charAt(slow)) {
                    current.append(fast-slow);
                    current.append(result.charAt(slow));
                    slow = fast++;
                }
                while(fast<result.length() && result.charAt(fast)==result.charAt(slow))
                    fast++;
            }
            current.append(fast-slow);
            current.append(result.charAt(slow));
            result = current;
            solution.add(result.toString());
        }
        return solution;
    }

}
