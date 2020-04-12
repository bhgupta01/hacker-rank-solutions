package java.gupta.list.recursive;

import java.gupta.list.OperationResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Created on 03/12/17 12:33 PM by bhgupta.
 */
public class DoublyLinkedListTest {

    @Test
    public void testAppendGivenEmptyList() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();
        list.append(20);
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertEquals(list.getHead(), list.getTail());
        assertEquals(list.getHead().getData(), 20);
    }

    @Test
    public void testAppendGivenNonEmptyList() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();

        list.append(10);
        list.append(20);
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertEquals(list.getHead().getData(), 10);
        assertEquals(list.getTail().getData(), 20);
    }

    @Test
    public void testDeleteGivenEmptyList() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();
        final OperationResult result = list.delete(2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testDeleteGivenNonEmptyListFrontPosition() throws Exception {
        // Delete Front when java.gupta.list size == 1
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(10);
        OperationResult result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertEquals(result.getNode().getData(), 10);

        // Delete Front when java.gupta.list size > 1
        list.append(20);
        list.append(30);
        result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertEquals(result.getNode().getData(), 20);
    }

    @Test
    public void testDeleteGivenNonEmptyListValidPosition() throws Exception {
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(10);
        list.append(20);
        OperationResult result = list.delete(2);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 2");
        assertEquals(result.getNode().getData(), 20);

        list.append(40);
        list.append(30);
        result = list.delete(2);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 2");
        assertEquals(result.getNode().getData(), 40);
    }

    @Test
    public void testDeleteGivenNonEmptyListInvalidPosition() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();

        list.append(10);
        final OperationResult result = list.delete(2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testUpdateGivenEmptyList() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();

        final OperationResult result = list.update(1, 40);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testUpdateGivenNonEmptyListValidPosition() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();

        list.append(10);
        list.append(20);
        list.append(90);
        list.append(40);

        final OperationResult result = list.update(3, 30);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "oldData=90 newData=30 position=3");
    }

    @Test
    public void testUpdateGivenNonEmptyListInvalidPosition() throws Exception {
        final DoublyLinkedList list = new DoublyLinkedList();

        list.append(10);
        list.append(20);

        final OperationResult result = list.update(3, 30);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }
}
