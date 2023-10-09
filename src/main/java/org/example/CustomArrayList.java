package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * CustomArrayList is a custom implementation of a dynamic array list.
 *
 * @param <E> The type of elements stored in the list.
 */
public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    /**
     * Constructs an empty CustomArrayList with the default initial capacity of 16.
     */
    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty CustomArrayList with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the list.
     * @throws IllegalArgumentException if the specified initial capacity is negative.
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }
        else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheckForGet(index);

        return (E) elementData[index];
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   The index at which to insert the element.
     * @param element The element to insert.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        growCapacityIfLimitReached();
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index The index of the element to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void remove(int index) {
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element The element to add.
     * @return {@code true} (as specified by the Collection interface).
     */
    public boolean add(E element) {
        growCapacityIfLimitReached();

        elementData[size++] = element;
        return true;
    }

    /**
     * Sorts the elements of the list using the provided comparator.
     *
     * @param comparator The comparator used for sorting.
     */
    @SuppressWarnings("uncheked")
    public void quickSort(Comparator<E> comparator) {
        if (size > 1) {
            quickSortInternal(comparator, (E[]) elementData, 0, size - 1);
        }
    }

    private void rangeCheckForGet(int index) {
        if (index >= elementData.length || index < 0)
            throw new IndexOutOfBoundsException("No such element " + index);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Insert index more then size: " + index);
    }

    private void growCapacityIfLimitReached() {
        if (size > elementData.length - 1) {
            elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2);
        }
    }

    private void quickSortInternal(Comparator<? super E> comparator, E[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(comparator, arr, begin, end);

            quickSortInternal(comparator, arr, begin, partitionIndex - 1);
            quickSortInternal(comparator, arr, partitionIndex + 1, end);
        }
    }

    private int partition(Comparator<? super E> comparator, E[] arr, int begin, int end) {

        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (comparator.compare(arr[j],arr[end]) <= 0) {
                i++;

                E swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        E swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }
}
