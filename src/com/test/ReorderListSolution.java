package com.test;

import java.util.*;
/*
    Given 1->2->3->4, reorder it to 1->4->2->3.

    Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderListSolution{

    public void reorderList(ListNode head) {
        if(head== null)return;
        List<ListNode> list = new ArrayList();
        while(head!= null){
            ListNode node = head;
            head = head.next;
            node.next = null;
            list.add(node);
        }
        int len=list.size()-1;
        int low=1;
        ListNode res=list.get(0);
        ListNode n=res;
        while(low<len){
            res.next=list.get(len);
            res=res.next;
            res.next=list.get(low);
            res=(res.next!=null)?res.next:res;
            low++;
            len--;
        }
        if(low==len)res.next=list.get(low);
        head=n;
    }

}
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}