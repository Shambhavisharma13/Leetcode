/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode removeNodes(ListNode head) {
        //step1:Reverse the Linked list
        head=reverse(head);
        ListNode curr=head;
        int max=curr.val;

        //step2:Remove nodes smaller thn max
 while(curr!=null&& curr.next!=null){
    if(curr.next.val<max){
        curr.next=curr.next.next;
    }
    else{
        curr=curr.next;
        max=curr.val;
    }
 }
 //step3:Reverse again
 return reverse(head);
    }
    private ListNode reverse(ListNode head){
        ListNode prev=null;
        ListNode curr=head;

        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
}