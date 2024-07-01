import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortedSongCollection<T extends Comparable<T>> implements IterableSortedCollection<T> {

    private List<T> collection;

    public SortedSongCollection() {
        collection = new ArrayList<>();
    }

    @Override
    public boolean insert(T data) {
        if (data == null) {
            throw new NullPointerException("Cannot insert null data into the collection.");
        }
        return collection.add(data);
    }

    @Override
    public boolean contains(Comparable<T> data) {
        return collection.contains(data);
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public void setIterationStartPoint(Comparable<T> startPoint) {
        // Not needed for this implementation
    }

    @Override
    public void add(T element) {
        insert(element);
    }

    @Override
    public boolean remove(T element) {
        return collection.remove(element);
    }

    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }
}