package com.ice.leetcode;

/**
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *  * 输出：7 -> 0 -> 8
 *  * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedListTest test = new LinkedListTest();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode listNode = test.addTwoNumbers(l1, l2);
        System.out.println(listNode.val);
        while ((listNode = listNode.next)!=null){
            System.out.println(listNode.val);
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addNode(null,null,0, l1,l2);
    }

    /**
     * 利用递归计算
     * @param result
     * @param carry
     * @param l
     * @param r
     * @return
     */
    public ListNode addNode(ListNode root,ListNode result,int carry,ListNode l,ListNode r){
        if(l==null&&r==null){
            if(carry!=0){
                result.next = new ListNode(carry);
            }
            return root;
        }else {
            int sum = (l != null ? l.val : 0) + (r != null ? r.val : 0) + carry;
            int num = sum % 10;
                carry = sum / 10;

            if (result == null) {
                result = new ListNode(num);
                root = result;
            } else {
                result.next = new ListNode(num);
                result = result.next;
            }
            return addNode(root,result,carry,l!=null?l.next:null,r!=null?r.next:null);
        }
    }

    
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
