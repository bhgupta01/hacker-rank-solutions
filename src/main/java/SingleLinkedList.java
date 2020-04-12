package java.gupta.list.loop;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;

/**
 * Created on 18/11/17 9:40 AM by bhgupta.
 */
public class SingleLinkedList {

    private Node first;
    private Node last;
    private int size;

    public SingleLinkedList() {
        size = 0;
    }

    public Node getHead() {
        return first;
    }

    public void append(int data) {
        final Node newNode = new Node(data);
        if (size <= 0) {
            first = newNode;
            last = first;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size += 1;
    }

    public OperationResult delete(int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage(String.format("Index out of bounds: %d", position));
            return result;
        }

        final Node[] nodes = getNode(position);
        if (nodes[0] == null) {
            first = first.next();
        } else {
            nodes[0].setNext(nodes[1].next());
        }
        nodes[1].setNext(null);
        result.setSuccess(true);
        result.setNode(nodes[1]);
        result.setMessage("deleted node at position: " + position);

        if (last == nodes[1]) {
            last = nodes[0];
        }
        size -= 1;
        return result;
    }

    public OperationResult update(int position, int newData) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage(String.format("Index out of bounds: %d", position));
            return result;
        }

        final Node currentNode = getNode(position)[1];
        final int oldData = currentNode.getData();
        currentNode.setData(newData);
        result.setSuccess(true);
        result.setMessage(String.format("oldData=%d newData=%d position=%d", oldData, currentNode.getData(), position));
        result.setNode(currentNode);

        return result;
    }

    private Node[] getNode(int position) {
        if (position == 1) {
            return new Node[]{null, first};
        }

        Node currentNode = first;
        Node parentNode = currentNode;
        while (position > 1 && currentNode.hasNext()) {
            parentNode = currentNode;
            currentNode = currentNode.next();
            position--;
        }
        return new Node[]{parentNode, currentNode};
    }

}
