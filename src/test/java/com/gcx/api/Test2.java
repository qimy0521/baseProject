package com.gcx.api;

public class Test2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result=new ListNode(0);
        ListNode cur = result;
        int carry=0;
        while (l1 != null || l2 != null){
            int x = l1 == null ? 0: l1.val;
            int y = l2 == null ? 0: l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            sum= sum % 10;
            cur.next=new ListNode(sum);
            cur=cur.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return result.next;
    }


    public static void main(String[] args) {
        Test2 test2=new Test2();
        ListNode l1=new ListNode(3);
        ListNode l11=new ListNode(6);
        ListNode l12=new ListNode(5);
        l1.next=l11;
        l11.next=l12;

        ListNode l21=new ListNode(3);
        ListNode l22=new ListNode(4);
        ListNode l23=new ListNode(8);
        l21.next=l22;
        l22.next=l23;
        ListNode listNode = test2.addTwoNumbers(l1, l21);
        while (listNode !=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }
}
