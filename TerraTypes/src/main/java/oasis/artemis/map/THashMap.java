package oasis.artemis.map;

import oasis.artemis.collection.TCollection;
import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.collection.set.THashSet;
import oasis.artemis.collection.set.TSet;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>THashMap</h2>
 * <p>A specialized version of {@link HashMap} used in Terra modules.</p>
 */
public class THashMap<K, V> extends HashMap<K, V> implements TMap<K, V> {
    /**
     * Creates a new empty THashMap.
     */
    public THashMap() {}

    /**
     * Creates a new THashMap with given initial capacity.
     *
     * @param initialCapacity Initial capacity
     */
    public THashMap(@Nonnegative int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new THashMap with given capacity and load factor.
     *
     * @param initialCapacity Initial capacity
     * @param loadFactor      Load factor
     */
    public THashMap(@Nonnegative int initialCapacity, @Positive float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Creates a new THashMap from a {@link Map}.
     *
     * @param map Map
     */
    public THashMap(@Nonnull Map<? extends K, ? extends V> map) {
        super(map);
    }

    /**
     * Creates a new THashMap from a {@link TCollection} of entries.
     *
     * @param collection Collection of entries
     */
    public THashMap(@Nonnull TCollection<Entry<K, V>> collection) {
        collection.forEach(e -> put(e.getKey(), e.getValue()));
    }

    @Nonnull
    @Override
    public V getOrDefaultPointer(@Nonnull K key, @Nonnull V value) {
        if (containsKey(key)) return get(key);
        else {
            put(key, value);
            return value;
        }
    }

    @Override
    public TSet<K> keySet() {
        return new THashSet<>(super.keySet());
    }

    @Override
    public TSet<Entry<K, V>> entrySet() {
        return new THashSet<>(super.entrySet());
    }

    @Override
    public TCollection<V> values() {
        return new TArray<>(super.values());
    }

    @Nonnull
    @Override
    public Stream<Entry<K, V>> stream() {
        return entrySet().stream();
    }

    @Nonnull
    @Override
    public TMap<K, V> filter(@Nonnull Predicate<Entry<K, V>> filter) {
        return new THashMap<>(entrySet().filter(filter));
    }

    @Nonnull
    @Override
    public <L extends K, W extends V> TMap<L, W> filter(@Nonnull Class<L> subKey, @Nonnull Class<W> subValue) {
        final TSet<L> keys = keySet().filter(subKey);
        final TList<W> values = new TArray<>(values().filter(subValue));

        final TMap<L, W> result = new THashMap<>();
        final Iterator<L> keyIterator = keys.iterator();
        for (int i = 0; i < keys.size(); i++) {
            result.put(keyIterator.next(), values.get(i));
        }

        return result;
    }
}
