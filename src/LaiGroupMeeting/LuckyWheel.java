package LaiGroupMeeting;

import java.util.*;

import java.util.Random;

public class LuckyWheel {

    public static void main(String[] args){

        String[] names = new String[] {
                "Tim",
                "Boxuan",
                "Zeyu",
                "Youqi",
                "Gaoyuan",
                "Yuewei",
                "Jingyi",
                "Wensi"//,
                //"Wenxuan"
        };

        printGrouping(grouping(names));
    }

    public static void printGrouping(List<List<String>> groups) {

        System.out.println("Coding Practice Groups For This Week\n");

        for(int i=0; i<groups.size(); i++) {
            System.out.println("Group " + (i+1));
            System.out.println("-------");
            for(String name : groups.get(i))
                System.out.print(name + " ");
            System.out.println("\n");
        }
    }

    public static List<List<String>> grouping(String[] names) {

        List<List<String>> groups = new ArrayList<>();

        int left = names.length;
        Scanner scanner = new Scanner(System.in);

        while(left>0) {
            System.out.println(left + " people are left for grouping.\n");
            List<String> group = new ArrayList<>();
            System.out.print("Enter group size for group " + (groups.size()+1) + ": ");
            int count = Math.min(left, Math.max(1, scanner.nextInt()));
            while(count>0) {
                int who = new Random().nextInt(left);
                group.add(names[who]);
                swap(names, who, --left);
                count--;
            }
            System.out.println();
            groups.add(group);
        }

        System.out.println();
        return groups;
    }

    private static void swap(String[] names, int a, int b) {
        String temp = names[a];
        names[a] = names[b];
        names[b] = temp;
    }

}

