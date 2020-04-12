package java.gupta.list.recursive;

import java.gupta.list.Node;
import java.gupta.list.OperationResult;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created on 03/12/17 11:57 AM by bhgupta.
 */
public class SingleLinkedListTest {
    @Test
    public void testAppendGivenEmptyList() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();

        list.append(20);
        assertNotNull(list.getHead());
        assertEquals(list.getHead().getData(), 20);
        assertNull(list.getHead().next());
    }

    @Test
    public void testAppendGivenNonEmptyList() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);
        list.append(40);
        final Node appendedNode = list.getHead().next();
        assertNotNull(appendedNode);
        assertEquals(appendedNode.getData(), 40);
    }

    @Test
    public void testDeleteGivenEmptyList() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        final OperationResult result = list.delete(2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Index out of bounds: 2");
        assertNull(result.getNode());
    }

    @Test
    public void testDeleteGivenEmptyOneElementInList() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);
        final OperationResult result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertNotNull(result.getNode());
        assertEquals(result.getNode().getData(), 20);
    }

    @Test
    public void testDeleteLastElementInList() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);
        list.append(30);
        list.append(40);
        list.append(50);
        final OperationResult result = list.delete(4);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 4");
        assertNotNull(result.getNode());
        assertEquals(result.getNode().getData(), 50);
    }

    @Test
    public void testDeleteGivenNonEmptyListValidPosition() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);
        list.append(40);
        list.append(60);
        list.append(80);

        OperationResult result = list.delete(2);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 2");
        assertNotNull(result.getNode());
        assertEquals(result.getNode().getData(), 40);


        result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertNotNull(result.getNode());
        assertEquals(result.getNode().getData(), 20);
    }

    @Test
    public void testDeleteGivenNonEmptyListInvalidPosition() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);

        final OperationResult result = list.delete(2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Index out of bounds: 2");
    }

    @Test
    public void testUpdateGivenEmptyList() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();

        final OperationResult result = list.update(1, 50);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Index out of bounds: 1");
        assertNull(result.getNode());
    }

    @Test
    public void testUpdateGivenNonEmptyListInvalidPosition() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);

        final OperationResult result = list.update(2, 10);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Index out of bounds: 2");
        assertNull(result.getNode());
    }

    @Test
    public void testUpdateGivenNonEmptyListValidPosition() throws Exception {
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);
        list.append(30);
        list.append(90);
        list.append(50);

        final OperationResult result = list.update(3, 40);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "oldData=90 newData=40 position=3");
        assertNotNull(result.getNode());
        assertEquals(result.getNode().getData(), 40);
    }
}
