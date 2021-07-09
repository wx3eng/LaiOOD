package LaiDebug.StringLinear;

public class longestValidParentheses {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(new String("))(()))(()()()(())(()")));
    }

    public static int longestValidParentheses(String input) {

        if(input==null || input.length()==0)
            return 0;

        int current = 0;
        int temp = -1;
        int max = 0;

        for(int i=0; i<input.length(); i++) {
            current += input.charAt(i)=='(' ? 1 : -1;
            if(current==-1) {
                current = 0;
                temp = i;
            }
            else if(current==0) {
                max = Math.max(max, i-temp);
            }
        }

        return max;
    }

}
