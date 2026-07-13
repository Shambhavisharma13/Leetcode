/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow=head;
         ListNode fast=head;
        while(fast!=null && fast.next!=null){
             slow = slow.next;          // Move 1 step
            fast = fast.next.next;     // Move 2 step 
         if(slow==fast){  //there is cycle in this linkedlist
                         return true;
            }
        }
        return false;
    }
}