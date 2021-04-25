package LaiDebug.SpaceDelete;

public class SolutionTest {

    public static void main(String[] args) {
        String test = new String("    I     love     yahoo    ");
        System.out.println(removeSpaces(test));
    }

    public static String removeSpaces(String input) {
        char[] solution = input.toCharArray();
        int temp = 0;
        for(int i=0; i<solution.length; i++) {
            while(solution[i]==' ' && i!=solution.length-1) i++;
            if(i==0 || solution[i]!=' ' || solution[i-1]!=' ') solution[temp++] = solution[i];
        }
        return new String(solution, 0, (solution[solution.length-1]==' ') ? temp : temp-1);
    }
}
