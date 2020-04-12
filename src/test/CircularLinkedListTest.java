package java.gupta.list.loop;

import java.gupta.list.OperationResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created on 19/11/17 10:50 AM by bhgupta.
 */
public class CircularLinkedListTest {

    @Test
    public void testAddEmptyList() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        assertNull(list.getTail());

        list.add(10, 10);
        assertNotNull(list.getTail());
        Assert.assertEquals(list.getTail().getData(), 10);
    }

    @Test
    public void testAddNonEmptyList() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(10, 10);
        list.add(20, 1);
    }

    @Test
    public void testAddNonEmptyListPositionGreaterThanSize() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(10, 10);
        list.add(20, 1);
        list.add(30, 10);
        Assert.assertEquals(list.getTail().getData(), 30);
    }

    @Test
    public void testAddNonEmptyListTailUpdate() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(10, 10);
        list.add(20, 1);
        list.add(30, 10);
        list.add(30, 2);
        Assert.assertEquals(list.getTail().getData(), 30);
    }

    @Test
    public void testDeleteGivenEmptyList() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        final OperationResult result = list.delete(2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testDeletePositionBeyondListSize() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(10, 1);
        final OperationResult result = list.delete(10);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testDeleteFrontWhenListSizeEqualsOne() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(10, 1);
        final OperationResult result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertEquals(result.getNode().getData(), 10);
        assertNull(list.getTail());
    }

    @Test
    public void testDeleteFrontWhenListSizeGreaterThanOne() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(20, 1);
        list.add(30, 1);
        final OperationResult result = list.delete(1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 1");
        assertEquals(result.getNode().getData(), 30);
        assertNotNull(list.getTail());
    }

    @Test
    public void testDeleteTail() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(20, 1);
        list.add(40, 1);
        list.add(50, 1);
        final OperationResult result = list.delete(3);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 3");
        assertEquals(result.getNode().getData(), 20);
        Assert.assertEquals(list.getTail().getData(), 40);
    }

    @Test
    public void testDeleteMiddleElement() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(40, 1);
        list.add(50, 1);
        list.add(60, 1);
        final OperationResult result = list.delete(2);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "deleted node at position: 2");
        assertEquals(result.getNode().getData(), 50);
    }

    @Test
    public void testUpdateGivenEmptyList() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        final OperationResult result = list.update(20, 2);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessage(), "Position out of bounds!");
    }

    @Test
    public void testUpdateGivenNonEmptyList() throws Exception {
        final CircularLinkedList list = new CircularLinkedList();
        list.add(40, 1);
        list.add(50, 1);
        OperationResult result = list.update(30, 1);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "oldData=50 newData=30 position=1");

        list.add(60, 1);
        list.add(70, 1);
        result = list.update(50, 3);
        assertTrue(result.isSuccess());
        assertEquals(result.getMessage(), "oldData=30 newData=50 position=3");
    }

}
