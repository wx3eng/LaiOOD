package LaiDebug.TreeRecursionProblems;

public class PreOrderSerialization {

    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    public static boolean isValidSerialization(String preorder) {

        if(preorder==null || preorder.length()==0) return true;
        if(preorder.charAt(0)=='#') return false;

        int[] reference = new int[preorder.length()];
        int temp = -1;

        for(int i=0; i<reference.length; i+=2) {
            if(preorder.charAt(i)!='#') ++temp;
            else if(temp==-1) return false;
            else if(reference[temp]<2) reference[temp]++;
            while(reference[temp]==2) {
                reference[temp--] = 0;
                if(temp==-1)
                    break;
                reference[temp]++;
            }
        }

        return temp==-1;
    }
}
