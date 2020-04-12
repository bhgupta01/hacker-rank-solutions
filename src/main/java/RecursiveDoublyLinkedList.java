package java.gupta.list.recursive;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;

/**
 * Created on 03/12/17 12:04 PM by bhgupta.
 */
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    DoublyLinkedList() {
        size = 0;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    void append(int data) {
        final Node newNode = new Node(data);
        newNode.setPrev(tail);
        if (size <= 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size += 1;
    }

    public OperationResult delete(int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage("Position out of bounds!");
            return result;
        }

        final Node node = getNode(position, head);
        final Node prev = node.getPrev();
        final Node next = node.next();
        node.setPrev(null);
        node.setNext(null);
        if (prev != null) {
            prev.setNext(next);
        } else {
            head = next;
        }

        if (next != null) {
            next.setPrev(prev);
        } else {
            tail = prev;
        }
        result.setSuccess(true);
        result.setNode(node);
        result.setMessage("deleted node at position: " + position);
        size -= 1;
        return result;
    }

    public OperationResult update(int position, int newData) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage("Position out of bounds!");
            return result;
        }

        final Node currentNode = getNode(position, head);
        final int oldData = currentNode.getData();
        currentNode.setData(newData);
        result.setSuccess(true);
        result.setMessage(String.format(
                "oldData=%d newData=%d position=%d",
                oldData,
                currentNode.getData(),
                position
        ));
        return result;
    }

    private Node getNode(int position, Node node) {
        if (position == 1) {
            return node;
        }
        return getNode(--position, node.next());
    }
}
