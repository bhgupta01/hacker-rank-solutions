package java.gupta.list;

import java.gupta.list.loop.CircularDoublyLinkedList;
import java.gupta.list.loop.CircularLinkedList;
import java.gupta.list.loop.DoublyLinkedList;
import java.gupta.list.loop.SingleLinkedList;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created on 03/12/17 7:47 PM by bhgupta.
 */
public class CheckCircularTest {
    @Test
    public void testIsCircularGivenSingleLinkedList() throws Exception {
        final CheckCircular cut = new CheckCircular();
        final SingleLinkedList list = new SingleLinkedList();
        list.append(20);
        list.append(30);
        list.append(90);
        list.append(50);
        assertFalse(cut.isCircular(list.getHead()));
    }

    @Test
    public void testIsCircularGivenDoublyLinkedList() throws Exception {
        final CheckCircular cut = new CheckCircular();
        final DoublyLinkedList list = new DoublyLinkedList();
        list.append(20);
        list.append(30);
        list.append(90);
        list.append(50);
        assertFalse(cut.isCircular(list.getHead()));
    }

    @Test
    public void testIsCircularGivenCircularLinkedListWithSingleElement() throws Exception {
        final CheckCircular cut = new CheckCircular();
        final CircularLinkedList list = new CircularLinkedList();
        list.add(20, 1);
        assertTrue(cut.isCircular(list.getHead()));
    }

    @Test
    public void testIsCircularGivenCircularLinkedListMoreThanOneElements() throws Exception {
        final CheckCircular cut = new CheckCircular();
        final CircularLinkedList list = new CircularLinkedList();
        list.add(20, 1);
        list.add(30, 1);
        list.add(90, 1);
        list.add(50, 1);
        assertTrue(cut.isCircular(list.getHead()));
    }

    @Test
    public void testIsCircularGivenDoublyCircularLinkedListWithSingleElement() throws Exception {
        final CheckCircular cut = new CheckCircular();
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 10);
        assertTrue(cut.isCircular(list.getHead()));
    }

    @Test
    public void testIsCircularGivenDoublyCircularLinkedListMoreThanOneElements() throws Exception {
        final CheckCircular cut = new CheckCircular();
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.add(1, 10);
        list.add(1, 20);
        list.add(1, 30);
        assertTrue(cut.isCircular(list.getHead()));
    }
}
