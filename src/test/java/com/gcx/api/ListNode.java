package com.gcx.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }