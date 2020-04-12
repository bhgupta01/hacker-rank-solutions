package java.gupta.list.loop;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;

/**
 * Created on 18/11/17 6:48 PM by bhgupta.
 */
public class CircularLinkedList {
    private Node tail;
    private int size;

    public CircularLinkedList() {
        size = 0;
    }

    public Node getTail() {
        return tail;
    }

    public Node getHead() {
        return tail.next();
    }

    public void add(int data, int position) {
        final Node newNode = new Node(data);
        if (size <= 0) {
            tail = newNode;
            newNode.setNext(tail);
        } else {
            final Node parentNode = getNodes(position)[0];
            newNode.setNext(parentNode.next());
            parentNode.setNext(newNode);
        }

        if (position > size) {
            tail = newNode;
        }
        size += 1;
    }

    public OperationResult delete(int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage("Position out of bounds!");
            return result;
        }

        final Node[] nodes = getNodes(position);
        nodes[0].setNext(nodes[1].next());
        nodes[1].setNext(null);
        size -= 1;

        if (size < 1) {
            tail = null;
        } else if (tail == nodes[1]) {
            tail = nodes[0];
        }

        result.setSuccess(true);
        result.setMessage("deleted node at position: " + position);
        result.setNode(nodes[1]);
        return result;
    }

    public OperationResult update(int newData, int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage("Position out of bounds!");
            return result;
        }

        Node currentNode = getNodes(position)[1];
        final int oldData = currentNode.getData();
        currentNode.setData(newData);
        result.setSuccess(true);
        result.setMessage(String.format("oldData=%d newData=%d position=%d", oldData, currentNode.getData(), position));

        return result;
    }

    private Node[] getNodes(int position) {
        if (position <= 1 || position > size) {
            return new Node[]{tail, tail.next()};
        }

        Node currentNode = tail.next();
        Node parentNode = currentNode;
        while (position > 1 && currentNode.hasNext()) {
            parentNode = currentNode;
            currentNode = currentNode.next();
            position--;
        }
        return new Node[]{parentNode, currentNode};
    }
}
