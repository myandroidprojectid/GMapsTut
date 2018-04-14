package com.selfdev.googlemapstut.linkedlist;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Rahul on 17/03/18.
 * Ref @ https://gist.github.com/es20641/1208340/06d598126d53b048058bc243cbc4e4dd7db9a23a
 */

public class LinkedList {

    private static final String TAG = "LinkedList";

    public class Node<T> implements Comparable<T> {
        public T data;
        public Node<T> nextNode;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNextNode(){
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public int compareTo(@NonNull T t) {

            int returnInt = 0;
            if (this.data instanceof Integer && t instanceof Integer) {
                if ((Integer)this.data < (Integer) t) {
                    returnInt = -1;
                } else if ((Integer)this.data > (Integer) t) {
                    returnInt = 1;
                } else {
                    returnInt = 0;
                }
            }
            return returnInt;
        }
    }

    public class SinglyLinkedList<T> {
        public Node<T> head = null;
        public int nodeSize = 0;


        public void adNodeAtPosition(int position) {
            if (position>=nodeSize) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Node<T> prevNode = null;
            Node<T> currentNode = null;
            Node<T> nextNode = null;

        }





        public void addNewNode(T newNodeData) {
            if (head==null) {
                head = new Node<>();
                head.setData(newNodeData);
            } else {
                Node<T> temp = head;
                while (temp.nextNode !=null) {
                    temp = temp.nextNode;
                }

                temp.nextNode = new Node<T>();
                temp.nextNode.setData(newNodeData);
            }

            nodeSize++;
        }

        public void addNewNodeAtHead(T data) {
            if (head==null) {
                head = new Node<>();
                head.setData(data);
            } else {
                Node<T> temp = new Node<>();
                temp.setData(data);
                temp.setNextNode(head);
                head = temp;
            }
            nodeSize++;
        }

        public void addNewNodeAtTail(T data) {
            if (head==null) {
                head = new Node<>();
                head.setData(data);
            } else {
                Node<T> temp = head;
                while (temp.nextNode !=null) {
                    temp = temp.nextNode;
                }
                temp.nextNode = new Node<>();
                temp.nextNode.setData(data);
            }
            nodeSize++;
        }

        public T getNodeAt(int position) throws ArrayIndexOutOfBoundsException {
            if (position>=nodeSize) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Node<T> temp = head;
            for (int i=0; i<position && temp!=null; i++) {
                temp = temp.getNextNode();
            }
            Log.e(TAG, "getNodeAt: "+position+" = "+temp.getData() );
            return temp.getData();
        }

        public void addNodeAt(int position, T data) {
            Node<T> previousNode = null;
            Node<T> currentNode = head;

            for (int i=0; i<position && currentNode!=null; i++) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }

            if (previousNode == null) {
                Node<T> newNode = new Node<>();

                newNode.setData(data);
                newNode.setNextNode(currentNode);

                head = newNode;
            } else {
                Node<T> newNode = new Node<>();

                newNode.setData(data);
                previousNode.setNextNode(newNode);
                newNode.setNextNode(currentNode);
            }
        }

        public void deleteNodeAt(int position) {
            Node<T> previousNode = null;
            Node<T> temp = head;
            for (int i=0; i<position && temp !=null; i++) {
                previousNode = temp;
                Log.e(TAG, "printList: 1. temp = "+i+" = "+temp);
                temp = temp.getNextNode();
                Log.e(TAG, "printList: 2. temp = "+i+" = "+temp);
            }
            if (previousNode!=null) {
                if (temp!=null) {
                    previousNode.setNextNode(temp.getNextNode());
                } else {
                    Log.e(TAG, "printList: NO NODE");
                }
            } else {
                if (temp!=null) {
                    head = temp.getNextNode();
                } else {
                    Log.e(TAG, "printList: NO NODE");
                }
            }
            nodeSize--;
        }

        public int getListSize() {
            return nodeSize;
        }

        public int findData(T data) {
            Node<T> temp = head;
            for (int i = 0; i < nodeSize && temp!=null; i++) {
                if (temp.compareTo(data) == 1) {
                    return i;
                }
                temp = temp.getNextNode();
            }
            return -1;
        }

        public void reverseList() {}

        public Node reverse(Node list) {
            if (head == null || head.nextNode == null) return head;
            Node first   = list;
            Node reverse = null;
            while (first != null) {
                Node second = first.nextNode;
                first.nextNode = reverse;
                reverse     = first;
                first       = second;
            }
            return reverse;
        }

        public void reverseLL() {
            if (head == null || head.nextNode == null) return;
            Node prev = null;
            Node current = head;
            Node next = null;
            while (current != null) {
                next = current.nextNode;
                current.nextNode = prev;
                prev = current;
                current = next;
            }
            head = prev;
        }

        public void makeLastFirst() {
            Node<T> prev = null;
            Node<T> curr = head;
            while (curr.nextNode !=null) {
                prev = curr;
                curr = curr.nextNode;
            }

            curr.nextNode = head;
            head = curr;
            prev.nextNode = null;
        }

        public void makeFirstLast() {
            Node<T> curr = head;
            while (curr.nextNode !=null) {
                curr = curr.nextNode;
            }

            curr.nextNode = head;
            head = head.nextNode;
            curr.nextNode.nextNode = null;
        }

        public void swapPairWise() {
            Node<T> currentNode = head;

            while (currentNode!=null && currentNode.nextNode !=null) {
                T data = currentNode.data;
                currentNode.data = currentNode.nextNode.data;
                currentNode.nextNode.data = data;
                currentNode = currentNode.nextNode.nextNode;
            }
        }

        public void printReverseWithoutReversing() {
            printReverseWithoutReversing(head);
        }

        // @Ref https://www.youtube.com/watch?v=jY-EUKXYT20
        public void reverseLinkedListIteratively() {
            Node<T> currentNode = head;
            Node<T> prev = null;
            Node<T> next = null;

            while (currentNode!=null) {
                next = currentNode.nextNode;
                currentNode.nextNode = prev;
                prev = currentNode;
                currentNode = next;
            }

            head = prev;
        }

        public void printReverseWithoutReversing(Node<T> node) {
            if (node == null) return;

            // print list of head node
            printReverseWithoutReversing(node.nextNode);

            // After everything else is printed
            Log.e(TAG, "printList printReverseWithoutReversing: "+node.data );
        }


        // @Ref https://www.youtube.com/watch?v=GbQT1zCUmN4
        public void reverseListRecursively() {
            head = reverseLinkedListRecursively(head);
        }

        public Node<T> reverseLinkedListRecursively(Node<T> node) {
            if (node.nextNode==null) {
                return node;
            }

            Node<T> returnNode = reverseLinkedListRecursively(node.nextNode);

            node.nextNode.nextNode = node;
            node.nextNode = null;

            return returnNode;
        }

        public void printList() {
            Node temp = head;
            while(temp != null) {
                Log.e(TAG, "printList: "+temp.getData() );
                temp = temp.getNextNode();
            }
            Log.e(TAG, "printList: ------------------------------" );
        }



        public void printListHeadData() {
            Node temp = head;

            Log.e(TAG, "printList: ------> DATA = "+head.data );
        }

        public boolean checkPalindrome() {

            Node<T> newHead = new Node<>();
            newHead.data = head.data;
            newHead.nextNode = head.nextNode;

            Node<T> middleNode = findMiddleNode(newHead);

            Node<T> headSecondNode = middleNode.nextNode;
            middleNode.nextNode = null;

            Node<T> reverseSecondHead = reverse(headSecondNode);

            Node<T> temp = head;
            while (temp!=null && reverseSecondHead!=null) {
                if (temp.data == reverseSecondHead.data) {
                    temp = temp.nextNode;
                    reverseSecondHead = reverseSecondHead.nextNode;
                    continue;
                } else {
                    return false;
                }
            }
            return true;

        }

        // @Ref https://java2blog.com/how-to-check-if-linked-list-is/
        public Node findMiddleNode(Node<T> _head) {
            // step 1
            Node slowPointer, fastPointer;
            slowPointer = fastPointer = _head;

            while(fastPointer !=null) {
                fastPointer = fastPointer.nextNode;
                if(fastPointer != null && fastPointer.nextNode != null) {
                    slowPointer = slowPointer.nextNode;
                    fastPointer = fastPointer.nextNode;
                }
            }

            Log.e(TAG, "printList findMiddleNode: "+slowPointer.data );
            return slowPointer;
        }


        // Merge Sort

        public void mergeSort() {
            Node<T> sortedlist = split(head);

            while (sortedlist!=null) {
                Log.e(TAG, "printList mergeSort: "+sortedlist.data );
                sortedlist = sortedlist.nextNode;
            }
        }

        public Node<T> split(Node<T> node) {
            if (node == null || node.nextNode == null) {
                return node;
            }
            Node<T> middleNode = findMiddleNode(node);

            Node<T> headMiddle = middleNode.nextNode;
            middleNode.nextNode = null;

            Node<T> left = split(node);
            Node<T> right = split(headMiddle);

            // Merge the left and right lists
            Node<T> sortedlist = sortAndMerge(left, right);
            return sortedlist;
        }

        public Node<T> sortAndMerge(Node<T> left, Node<T> right) {
            Node<T> result = null;
            /* Base cases */
            if (left == null)
                return right;
            if (right == null)
                return left;

            /* Pick either a or b, and recur */
            if (left.compareTo(right.data) == 0) {
                result = left;
                result.nextNode = sortAndMerge(left.nextNode, right);
            } else {
                result = right;
                result.nextNode = sortAndMerge(left, right.nextNode);
            }
            return result;

        }


        public void mergeSortArray() {
            int[] intArray = {2,5,1,6,7,2,9,10,15,12};
            mergeSort(intArray);

            for (int i=0; i<intArray.length;i++) {
                Log.e(TAG, "mergeAndSort-------->: = "+intArray[i] );
            }
        }

        public void mergeSort(int[] inputArray) {
            int size = inputArray.length;
            if (size < 2) {
                return;
            }

            int mid = size / 2;
            int leftSize = mid;
            int rightSize = size - mid;
            int[] left = new int[leftSize];
            int[] right = new int[rightSize];
            for (int i = 0; i < mid; i++) {
                left[i] = inputArray[i];

            }
            for (int i = mid; i < size; i++) {
                right[i - mid] = inputArray[i];
            }
            mergeSort(left);
            mergeSort(right);
            merge(left, right, inputArray);
        }

        public int[] merge(int[] left, int[] right, int[] arr) {
            int leftSize = left.length;
            int rightSize = right.length;
            int i = 0, j = 0, k = 0;
            while (i < leftSize && j < rightSize) {
                if (left[i] <= right[j]) {
                    arr[k] = left[i];
                    i++;
                    k++;
                } else {
                    arr[k] = right[j];
                    k++;
                    j++;
                }
            }
            while (i < leftSize) {
                arr[k] = left[i];
                k++;
                i++;
            }
            while (j < leftSize) {
                arr[k] = right[j];
                k++;
                j++;
            }

            for (int q=0; q<arr.length;q++) {
                Log.e(TAG, "mergeAndSort-------->: = "+arr[q] );
            }
            return arr;
        }

        public int[] mergeSortArray(int[] intArray, int low, int high) {
            if ((high-low) <= 1) {
                return intArray;
            }
            int mid = low + (high-low)/2;

            int[] left = mergeSortArray(intArray,low,mid);
            int[] right = mergeSortArray(intArray,mid,high);
            return merge(left,right,intArray);

        }
    }
}