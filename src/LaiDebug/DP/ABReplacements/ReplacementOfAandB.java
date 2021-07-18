package LaiDebug.DP.ABReplacements;

public class ReplacementOfAandB {

    public static void main(String[] args) {
        String input = new String("abbabababaaabbbababab");
        System.out.println(minReplacements(input));
    }
    //////////////////////////
    // Statement: receive an input of only characters a and b, and return the number of minimum changes on the input required
    // for all a's (if there are any) to appear before all b's (if there are any) in the string.
    /////////////////

    // Complete idea:
    ////////////////////////////////////////////////////////////////////
    //
    // The initial thought on this problem was that there are only n+1 possible states
    // that can be a solution to this problem, where n is the length of the input string:
    // if n=3 (i.e. aaa, aab, aba, abb, baa, bab, bba, bbb), the 4(3+1) possible solutions are
    //      bbb
    //      abb
    //      abb
    //      aaa.
    //
    // Based on what the input is, if the required number of a-to-b changes and the required
    // number of b-to-a changes are known, then the best solution is the one that requires
    // the least a-to-b and b-to-a changes in total.
    //
    // To make an input string of length 3 (n=3) into "bbb" is equivalent to asking
    // "How many character changes do I need to make the beginning 0 characters 'a',
    // and how many character changes do I need to make the ending 3 characters 'b'?".
    // To make an input string of length 3 (n=3) into "abb" is equivalent to asking
    // "How many character changes do I need to make the beginning 1 character 'a',
    // and how many character changes do I need to make the ending 2 characters 'b'?".
    // To make an input string of length 3 (n=3) into "aab" is equivalent to asking
    // "How many character changes do I need to make the beginning 2 characters 'a',
    // and how many character changes do I need to make the ending 1 character 'b'?".
    // To make an input string of length 3 (n=3) into "aaa" is equivalent to asking
    // "How many character changes do I need to make the beginning 3 characters 'a',
    // and how many character changes do I need to make the ending 0 characters 'b'?".
    //
    // To calculate the minimum changes out of all of these possible states,
    // the required number of character changes for the first 0, 1, 2, 3 (i.e. 0 to n for input length n) characters to be 'a'
    // and the last 3, 2, 1, 0 (i.e. n to 0, respectively) characters to be 'b' must be known.
    //
    // Let A[] be an integer array of length one more than the length of the input string, and
    // let A[i] store the number of character changes for the beginning i characters to be 'a'.
    // Similarly, let B[] be an integer array of length one more than the length of the input string, and
    // let B[n-i] store the number of character changes for the ending n-i characters to be 'b'.
    //
    // By this definition, to know the number of character changes for the beginning i characters to be 'a',
    // only the number of character changes for the ending i-1 characters to be 'a' and whether the i-th character is 'a' need to be known.
    // Similarly, to know the number of character changes for the beginning n-i characters to be 'b',
    // only the number of character changes for the ending n-(i-1) characters to be 'b' and whether the (n-i)-th character is 'b' need to be known.
    // i.e.,
    // A[i] = A[i-1] + (input.charAt(i)=='a' ? 0 : 1),
    // B[n-i] = B[n-(i-1)] + (input.charAt(n-i)=='b' ? 0 : 1).
    //
    // Since there are induction relations among the required information,
    // solving this problem with dynamic programming concepts is advisable.
    //
    //  Then, the number of character changes for the beginning i characters to be 'a' and the ending n-i characters to be 'b' is simply
    //  A[i] + B[i].
    // To know the solution of minimum changes, choose min(A[i] + B[i]) over i = [0, n].
    //
    /////////////////
    //
    //  Demonstration:
    //
    //      input: "bba" (length = n = 3)
    //
    //              for increasing i (from 1 to n),
    //
    //        n-i <----------
    //
    //        B=[1, 1, 1, 0]
    //          "b  b  a"
    //     A=[0, 1, 2, 2]
    //
    //     -----------> i
    //
    /////////////
    //
    //  Find minimum:
    //
    //        A=[0, 1, 2, 2]
    //        B=[1, 1, 1, 0]
    //    total: 1  2  3  2
    //
    //      min: 1
    //      The output is 1.
    //
    /////////////////
    //
    // Time: O(n)
    // Space: O(n)
    //
    // Assumption:
    // the input is not null
    public static int minReplacements(String input) {

        // corner base
        if(input == null || input.length() < 2) {
            return 0;
        }

        // use DP array to record the number of required character changes
        int inputLength = input.length();
        int[] countA = new int[inputLength + 1];
        int[] countB = new int[inputLength + 1];

        // the algorithm--induction
        for(int i = 1; i <= inputLength; i++) {
            countA[i] = countA[i - 1] + (input.charAt(i - 1) == 'a' ? 0 : 1);
            countB[inputLength - i] = countB[inputLength - i + 1] + (input.charAt(inputLength - i) == 'b' ? 0 : 1);
        }

        // find the minimum of a-to-b + b-to-a changes
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < countA.length; i++) {
            min = Math.min(min, countA[i] + countB[i]);
        }

        // return it
        return min;
        /////////////////////////////////////////////
        // The code for the "Space: O(1)" solution:
        /*
        int index = input.length()-1;

        int beginWithA = 0;
        int beginWithB = 0;

        while(index>=0) {
          int countA = 0;
          int countB = 0;
          while(index>=0 && input.charAt(index)=='a') {
            countA++;
            index--;
          }
          while(index>=0 && input.charAt(index)=='b') {
            countB++;
            index--;
          }
          beginWithA = countB + Math.min(beginWithA, beginWithB);
          beginWithB += countA;
        }

        return Math.min(beginWithA, beginWithB);
        */
    }

}
