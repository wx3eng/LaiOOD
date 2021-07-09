package LaiQStackDeque;

import java.util.*;

public class MaxStack {

    // MaxStack operates like a normal stack, but the stack now also implements popMax() and peekMax() function. With a conventional stack,
    // it is impossible to popMax or peekMax because the max value might be somewhere in the middle of a stack, and a conventional stack
    // does not have functions to take data out from the middle of the stack.

    // All functions to implement are
    //      push(),
    //      peek(),
    //      pop(),
    //      peekMax(),
    //      popMax()

    // The data structures used to implement the stack with max function are
    //      a bidirectional linked list
    //      a hash map
    //      a max heap

    // The bidirectional linked list is defined by a head node and a tail node.
    // Whenever push() is called, the tail appends a new node, the hash map adds that new node,
    // and the max heap also adds that new node (if there are no duplicates of the value).
    // Whenever pop() is called, the tail node is removed from the linked list and the hash map.
    // Whenever popMax() is called, a node of max value is removed from the linked list, the hash map, and possibly the max heap.
    // Whenever peek() is called, the value of the tail node is displayed.

    // The hash map holds the references of all nodes in the linked list in the form of lists, and so duplicate values of nodes are allowed.
    // whenever a value is pushed to the stack, the hash map adds the node that is generated with the value to the corresponding list.
    // whenever popMax() or pop() is called, the hash map removes one of the nodes from the end of the corresponding list
    // in reference to either the node of the linked list or the node of max value of the entire linked list.

    // The max heap holds all the values that have been inputted so far, no duplicates allowed.
    // whenever push() is called, the push value is put into the max heap (although duplicates will fail).
    // whenever popMax() is called, the node that will be removed should have the same value as the top element in the max heap.
    // if the top element in the max heap does not have a corresponding value in the hash map, then that means there are no nodes
    // containing that top-element value. Continue remove such values from the top of the max heap until there is a value that
    // has a corresponding value in the hash map. Then delete a most recent node that has the max value.

    //////
    // Data structures
    private BiDirNode head;
    private BiDirNode tail;
    private final Map<Integer, List<BiDirNode>> reference;
    private final PriorityQueue<Integer> heap;

    // constructor
    public MaxStack() {
        head = null;
        tail = null;
        reference = new HashMap<>();
        heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer value1, Integer value2) {
                return value1>value2 ? -1 : 1;
            }
        });
    }

    public Integer pop() {
        if (tail==null) {
            return null;
        }
        BiDirNode temp = tail.previous;
        Integer result = tail.value;
        List<BiDirNode> nodes = reference.get(result);
        nodes.remove(nodes.size()-1);
        if(nodes.size()==0) {
            reference.remove(result);
        }
        if(temp==null) {
            head = tail = null;
        } else {
            temp.next = null;
            tail.previous = null;
            tail = temp;
        }
        return result;
    }

    public Integer peek() {
        return tail==null ? null : tail.value;
    }

    public void push(int value) {
        BiDirNode newNode = new BiDirNode(value);
        List<BiDirNode> nodes = reference.get(value);
        if(nodes==null) {
            heap.offer(value);
            nodes = new ArrayList<>();
            reference.put(value, nodes);
        }
        nodes.add(newNode);
        if(tail==null) {
            head = tail = new BiDirNode(value);
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    public Integer peekMax() {
        return heap.size()==0 ? null : heap.peek();
    }

    public Integer popMax() {
        if(heap.size()==0) {
            return null;
        }
        while(heap.size()>0 && reference.get(heap.peek())==null) {
            heap.poll();
        }
        int result = heap.peek();
        List<BiDirNode> nodes = reference.get(result);
        BiDirNode temp = nodes.get(nodes.size()-1);
        nodes.remove(nodes.size()-1);

        if(nodes.size()==0) {
            heap.poll();
            reference.remove(result);
        }

        if(temp!=head) {
            temp.previous.next = temp.next;
        } else {
            head = temp.next;
        }
        if(temp!=tail) {
            temp.next.previous = temp.previous;
        } else {
            tail = temp.previous;
        }
        temp.previous = temp.next = null;

        while(heap.size()>0 && reference.get(heap.peek())==null) {
            heap.poll();
        }

        return result;
    }

    private static class BiDirNode {
        private final int value;
        BiDirNode previous;
        BiDirNode next;
        BiDirNode(int value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }
}
