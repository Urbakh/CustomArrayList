import org.example.CustomArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    private CustomArrayList<Integer> customArrayList;

    @BeforeEach
    public void setUp() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void testDefaultConstructor() {
        assertTrue(customArrayList.isEmpty());
        assertEquals(0, customArrayList.size());
    }

    @Test
    public void testAddElement() {
        customArrayList.add(42);
        assertFalse(customArrayList.isEmpty());
        assertEquals(1, customArrayList.size());
        assertEquals(42, customArrayList.get(0));
    }

    @Test
    public void testAddElementAtIndex() {
        customArrayList.add(0, 42);
        customArrayList.add(1, 30);
        customArrayList.add(1, 20);

        assertFalse(customArrayList.isEmpty());
        assertEquals(3, customArrayList.size());
        assertEquals(42, customArrayList.get(0));
        assertEquals(20, customArrayList.get(1));
        assertEquals(30, customArrayList.get(2));
    }

    @Test
    public void testRemoveElement() {
        customArrayList.add(10);
        customArrayList.add(20);
        customArrayList.add(30);

        customArrayList.remove(1);

        assertEquals(2, customArrayList.size());
        assertEquals(10, customArrayList.get(0));
        assertEquals(30, customArrayList.get(1));
    }

    @Test
    public void testQuickSort() {
        customArrayList.add(50);
        customArrayList.add(30);
        customArrayList.add(70);
        customArrayList.add(20);
        customArrayList.add(40);

        Comparator<Integer> comparator = Integer::compare;
        customArrayList.quickSort(comparator);

        assertEquals(5, customArrayList.size());
        assertEquals(20, customArrayList.get(0));
        assertEquals(30, customArrayList.get(1));
        assertEquals(40, customArrayList.get(2));
        assertEquals(50, customArrayList.get(3));
        assertEquals(70, customArrayList.get(4));
    }

    @Test
    public void testAddWithInitialCapacity() {
        customArrayList = new CustomArrayList<>(5);
        assertTrue(customArrayList.isEmpty());
        assertEquals(0, customArrayList.size());
    }

    @Test
    public void testAddWithIllegalInitialCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            customArrayList = new CustomArrayList<>(-1);
        });
    }

    @Test
    public void testGetWithIndexOutOfBounds() {
        customArrayList = new CustomArrayList<>(0);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.get(0);
        });
    }

    @Test
    public void testAddWithIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.add(1, 42);
        });
    }

    @Test
    public void testRemoveWithIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.remove(0);
        });
    }

    @Test
    public void testRemoveEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.remove(0);
        });
    }
}
