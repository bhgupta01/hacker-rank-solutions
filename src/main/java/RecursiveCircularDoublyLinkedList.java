package java.gupta.list.recursive;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;

/**
 * Created on 02/12/17 3:38 PM by bhgupta.
 */
public class CircularDoublyLinkedList {
    private Node tail;
    private int size;

    CircularDoublyLinkedList() {
        this.size = 0;
    }

    public Node getTail() {
        return tail;
    }

    public Node getHead() {
        return tail.next();
    }

    public void add(int position, int data) {
        final Node newNode = new Node(data);
        if (size <= 0) {
            tail = newNode;
            newNode.setNext(tail);
            newNode.setPrev(tail);
        } else {
            final Node currentNode = getNode(position);
            newNode.setPrev(currentNode.getPrev());
            newNode.setNext(currentNode);
            currentNode.setPrev(newNode);
            newNode.getPrev().setNext(newNode);
        }

        if (position > size) {
            tail = newNode;
        }
        size += 1;
    }

    public OperationResult delete(int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position > size) {
            result.setSuccess(false);
            result.setMessage("Position out of bounds!");
            return result;
        }

        final Node currentNode = getNode(position);
        if (currentNode == tail) {
            tail = currentNode.getPrev();
        }
        final Node prev = currentNode.getPrev();
        final Node next = currentNode.next();
        prev.setNext(next);
        next.setPrev(prev);
        currentNode.setPrev(null);
        currentNode.setNext(null);

        result.setSuccess(true);
        result.setMessage("deleted node at position: " + position);
        result.setNode(currentNode);
        size -= 1;

        if (size < 1) {
            tail = null;
        }
        return result;
    }

    public OperationResult update(int data, int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position > size) {
            result.setSuccess(false);
            result.setMessage("Position out of bounds!");
            return result;
        }
        final Node currentNode = getNode(position);
        final int oldData = currentNode.getData();
        currentNode.setData(data);
        result.setSuccess(true);
        result.setMessage(String.format("oldData=%d newData=%d position=%d", oldData, currentNode.getData(), position));
        return result;
    }

    private Node getNode(int position) {
        if (position <= 1 || position > size) {
            return tail.next();
        } else if (position == size) {
            return tail;
        }
        return getNode(position, tail.next());
    }

    private Node getNode(int position, Node node) {
        if (position == 1) {
            return node;
        }
        return getNode(--position, node.next());
    }
}
