package java.gupta.list.loop;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;

/**
 * Created on 18/11/17 11:47 AM by bhgupta.
 */
public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void append(int data) {
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

        final Node currentNode = getNode(position);
        final Node prev = currentNode.getPrev();
        final Node next = currentNode.next();
        currentNode.setPrev(null);
        currentNode.setNext(null);

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
        result.setMessage("deleted node at position: " + position);
        result.setNode(currentNode);
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

        final Node currentNode = getNode(position);
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

    private Node getNode(int position) {
        if (position == 1) {
            return head;
        } else if (position == size) {
            return tail;
        }

        Node currentNode = head;
        while (position > 1 && currentNode.hasNext()) {
            currentNode = currentNode.next();
            position--;
        }
        return currentNode;
    }
}
