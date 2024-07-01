import java.util.Iterator;

/**
 * This ADT supports iteration through a sorted collection.
 * 
 * When a non-null start point is set, the first value returned by this 
 * iterator will be the smallest value in the collection that is smaller than 
 * or equal to the specified start point.  When no (or a null) start point is 
 * set, the iterator will step through all values in the collection.
 */
public interface IterableSortedCollection <T extends Comparable<T>>
    extends SortedCollectionInterface<T>, Iterable<T> {

    public void setIterationStartPoint(Comparable<T> startPoint);

    /**
     * Adds an element to the collection.
     *
     * @param element the element to add
     */
    void add(T element);

    /**
     * Removes an element from the collection.
     *
     * @param element the element to remove
     * @return true if the element was successfully removed, false otherwise
     */
    boolean remove(T element);

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an iterator over the elements in this collection
     */
    @Override
    Iterator<T> iterator();
}
