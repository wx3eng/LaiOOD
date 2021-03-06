package LaiDebug.LinkedListMethods;

public class QuickSortLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode temp = head;
        temp.next = new ListNode(4);
        temp = temp.next;
        temp.next = new ListNode(1);
        temp = temp.next;
        temp.next = new ListNode(2);
        temp = temp.next;
        temp.next = new ListNode(6);
        temp = temp.next;
        temp.next = new ListNode(3);
        ListNode returnList = quickSort(head);
        System.out.println(returnList);

    }

    public static ListNode quickSort(ListNode head) {
        return partition(head, new ListNode(0), new ListNode(0));
    }

    private static ListNode partition(ListNode node, ListNode small, ListNode large) {

        if(node==null || node.next==null)
            return node;

        ListNode smallTemp = small;
        ListNode largeTemp = large;
        ListNode temp = node.next;

        while(temp!=null) {
            if(temp.value<node.value)
                smallTemp = smallTemp.next = temp;
            else
                largeTemp = largeTemp.next = temp;
            temp = temp.next;
        }

        smallTemp.next = largeTemp.next = node.next = null;
        smallTemp = small.next;
        largeTemp = large.next;
        small.next = large.next = null;

        smallTemp = partition(smallTemp, small, large);
        largeTemp = partition(largeTemp, small, large);

        if(smallTemp==null)
            if(node==null)
                smallTemp = largeTemp;
            else {
                smallTemp = node;
                node.next = largeTemp;
            }
        else {
            temp = smallTemp;
            while(temp.next!=null)
                temp = temp.next;
            if(node==null)
                temp.next = largeTemp;
            else {
                temp.next = node;
                node.next = largeTemp;
            }
        }

        return smallTemp;
    }

    private static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

}
