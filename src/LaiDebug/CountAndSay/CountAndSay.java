package LaiDebug.CountAndSay;

public class CountAndSay {

    public static void main(String[] args) {
        for(int i=0; i<20; i++)
            System.out.println(countAndSay(i).length());
    }

    public static String countAndSay(int n) {
        if(n<1) return new String();
        if(n==1) return "1";
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
        }
        return result.toString();
    }

}
