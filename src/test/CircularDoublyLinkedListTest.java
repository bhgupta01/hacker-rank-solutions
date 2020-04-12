package java.gupta.list.loop;

import java.gupta.list.OperationResult;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created on 02/12/17 4:59 PM by bhgupta.
 */
public class CircularDoublyLinkedListTest {
    @Test
    public void testAddEmptyList() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        assertNull(list.getTail());

        list.add(10, 10);
        assertNotNull(list.getTail());
        assertEquals(list.getTail().getData(), 10);
    }

    @Test
    public void testAddNonEmptyList() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(10, 10);
        list.add(1, 20);
        assertNotNull(list.getTail());
        assertEquals(list.getTail().getData(), 10);
    }

    @Test
    public void testAddNonEmptyListPositionGreaterThanSize() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(10, 10);
        list.add(1, 20);
        list.add(10, 30);
        assertEquals(list.getTail().getData(), 30);
    }

    @Test
    public void testAddNonEmptyListTailUpdate() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(10, 10);
        list.add(1, 20);
        list.add(10, 30);
        list.add(2, 30);
        assertEquals(list.getTail().getData(), 30);
    }

    @Test
    public void testDeleteGivenEmptyList() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        final OperationResult result = list.delete(2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testDeletePositionBeyondListSize() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 10);
        final OperationResult result = list.delete(10);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testDeleteFrontWhenListSizeEqualsOne() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 10);
        final OperationResult result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertEquals(result.getNode().getData(), 10);
        assertNull(list.getTail());
    }

    @Test
    public void testDeleteFrontWhenListSizeGreaterThanOne() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 20);
        list.add(1, 30);
        final OperationResult result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertEquals(result.getNode().getData(), 30);
        assertNotNull(list.getTail());
    }

    @Test
    public void testDeleteTail() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 20);
        list.add(1, 40);
        list.add(1, 50);
        final OperationResult result = list.delete(3);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 3");
        assertEquals(result.getNode().getData(), 20);
        assertEquals(list.getTail().getData(), 40);
    }

    @Test
    public void testDeleteMiddleElement() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 40);
        list.add(1, 50);
        list.add(1, 60);
        final OperationResult result = list.delete(2);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 2");
        assertEquals(result.getNode().getData(), 50);
    }

    @Test
    public void testUpdateGivenEmptyList() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        final OperationResult result = list.update(20, 2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testUpdateGivenNonEmptyList() throws Exception {
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 40);
        list.add(1, 50);
        OperationResult result = list.update(30, 1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "oldData=50 newData=30 position=1");

        list.add(1, 60);
        list.add(1, 70);
        result = list.update(50, 3);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "oldData=30 newData=50 position=3");
    }
}
