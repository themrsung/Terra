package oasis.artemis.map;

import oasis.artemis.collection.TCollection;
import oasis.artemis.collection.set.TSet;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <h2>TMap</h2>
 * <p>
 * A specialized version of {@link Map} used in Terra modules.
 * TerraMaps add custom methods for increased usability.
 * </p>
 */
public interface TMap<K, V> extends Map<K, V> {
    /**
     * Gets the value of a given key.
     * <p>
     * If a corresponding value is not found, the default value will be
     * put into this map, then a pointer to the newly put value will be returned.
     * </p>
     * <p>
     * Changes in the return value will be reflected in this map.
     * </p>
     *
     * @param key   Key to query
     * @param value Default value to use
     * @return Value of the key if found, default value if not
     */
    @Nonnull
    V getOrDefaultPointer(@Nonnull K key, @Nonnull V value);

    /**
     * This delegates to {@link Map#keySet()},
     * then converts the return value into a TSet.
     *
     * @return {@link TSet}
     */
    @Override
    TSet<K> keySet();

    /**
     * This delegates to {@link Map#entrySet()},
     * then converts the return value into a TSet.
     *
     * @return {@link TSet}
     */
    @Override
    TSet<Entry<K, V>> entrySet();

    /**
     * This delegates to {@link Map#values()},
     * then converts the return value into a TCollection.
     *
     * @return {@link TCollection}
     */
    @Override
    TCollection<V> values();

    /**
     * Converts this map into a {@link Stream} of entries.
     *
     * @return Stream of entries
     */
    @Nonnull
    Stream<Entry<K, V>> stream();

    /**
     * Gets a filtered map of this map.
     *
     * @param filter Filter to apply
     * @return Filtered map
     */
    @Nonnull
    TMap<K, V> filter(@Nonnull Predicate<Entry<K, V>> filter);

    /**
     * Gets a filtered map by subtype of entry and key.
     *
     * @param subKey   Subtype of entry to get
     * @param subValue Subtype of key to get
     * @param <L>      Subtype of {@link K}
     * @param <W>      Subtype of {@link V}
     * @return Filtered map
     */
    @Nonnull
    <L extends K, W extends V> TMap<L, W> filter(@Nonnull Class<L> subKey, @Nonnull Class<W> subValue);
}
