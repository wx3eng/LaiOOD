package LaiDebug.LinkedListMethods;

public class GenerateListFromArray {

    public static ListNode generateList(int[] array) {

        if(array==null) {
            return null;
        }

        ListNode head = new ListNode(array[0]);
        ListNode temp = head;

        for(int i=1; i<array.length; i++) {
            temp = temp.next = new ListNode(array[i]);
        }

        return head;
    }

}
