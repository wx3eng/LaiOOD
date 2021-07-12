package LaiDebug.LinkedListMethods;

public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] array = new int[]{1,1,2,2,2,3,4,4,4};
        ListNode list = GenerateListFromArray.generateList(array);
        ListNode result = removeDup(list);
        System.out.println(result);
    }

    public static ListNode removeDup(ListNode head) {

        if(head==null || head.next==null) {
            return head;
        }

        ListNode temp = head;

        while(temp.next!=null) {
            if(temp.value==temp.next.value) {
                ListNode node = temp.next;
                temp.next = temp.next.next;
                node.next = null;
            } else {
                temp = temp.next;
            }
        }

        return head;
    }
}
