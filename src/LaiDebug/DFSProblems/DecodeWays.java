package LaiDebug.DFSProblems;

public class DecodeWays {

    public static void main(String[] args) {

        String input = "62";
        System.out.println(numDecodeWay(input));

    }

    public static int numDecodeWay(String input) {

        if(input==null || input.length()==0)
            return 0;

        int[] code = new int[input.length()];
        for(int i=0; i<code.length; i++)
            code[i] = Character.getNumericValue(input.charAt(i));

        int[] count = new int[]{0};
        decode(code, 0, input.length(), count);
        return count[0];
    }

    private static void decode(int[] code, int depth, int levels, int[] count) {

        if(depth==levels) {
            count[0]++;
            return;
        }

        decode(code, depth+1, levels, count);
        if(depth+1>=levels || (code[depth]>2 || code[depth]==2 && code[depth+1]>6))
            return;
        decode(code, depth+2, levels, count);
    }

}
