package java.gupta.list.recursive;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;

/**
 * Created on 03/12/17 10:36 AM by bhgupta.
 */
public class SingleLinkedList {
    private Node head;
    private int size = 0;

    public Node getHead() {
        return head;
    }

    void append(int data) {
        if (head == null) {
            head = new Node(data);
        } else {
            append(data, head);
        }
        size += 1;
    }

    private Node append(int data, Node node) {
        if (node.hasNext()) {
            return append(data, node.next());
        }
        final Node newNode = new Node(data);
        node.setNext(newNode);
        return newNode;
    }

    public OperationResult delete(int position) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage(String.format("Index out of bounds: %d", position));
            return result;
        }

        final Node deletedNode;
        if (position == 1) {
            deletedNode = head;
            head = head.next();
        } else {
            deletedNode = delete(position, head);
        }
        result.setSuccess(true);
        result.setNode(deletedNode);
        result.setMessage("deleted node at position: " + position);
        size -= 1;
        return result;
    }

    private Node delete(int position, Node node) {
        if (position - 1 == 1) {
            final Node nodeToBeDeleted = node.next();
            node.setNext(nodeToBeDeleted.next());
            nodeToBeDeleted.setNext(null);
            return nodeToBeDeleted;
        }
        return delete(--position, node.next());
    }

    public OperationResult update(int position, int data) {
        final OperationResult result = new OperationResult();
        if (size <= 0 || position < 1 || position > size) {
            result.setSuccess(false);
            result.setMessage(String.format("Index out of bounds: %d", position));
            return result;
        }

        final Node node = getNode(position, head);
        final int oldData = node.getData();
        node.setData(data);
        result.setSuccess(true);
        result.setMessage(String.format("oldData=%d newData=%d position=%d", oldData, node.getData(), position));
        result.setNode(node);

        return result;
    }

    private Node getNode(int position, Node node) {
        if (position == 1) {
            return node;
        }
        return getNode(--position, node.next());
    }
}
