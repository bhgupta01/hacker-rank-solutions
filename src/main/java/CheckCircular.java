package java.gupta.list;

/**
 * Created on 03/12/17 7:41 PM by bhgupta.
 */
class CheckCircular {
    boolean isCircular(Node head) {
        return head != null && isCircular(head, head.next());
    }

    private boolean isCircular(Node head, Node currentNode) {
        return currentNode != null && (head == currentNode || isCircular(head, currentNode.next()));
    }
}
