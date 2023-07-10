package oasis.artemis.collection.list;

import oasis.artemis.collection.set.THashSet;
import oasis.artemis.collection.set.TSet;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <h2>TArray</h2>
 * <p>A specialized version of {@link ArrayList} used in Terra modules.</p>
 */
public class TArray<E> extends ArrayList<E> implements TList<E> {
    /**
     * Creates a new empty TArray.
     */
    public TArray() {}

    /**
     * Creates a new TArray containing given entries.
     *
     * @param entries Entries
     */
    @SafeVarargs
    public TArray(@Nonnull E... entries) {
        this(List.of(entries));
    }

    /**
     * Creates a new TArray with given capacity.
     *
     * @param initialCapacity Initial capacity
     */
    public TArray(@Nonnegative int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new TArray from a collection.
     *
     * @param collection Collection
     */
    public TArray(@Nonnull Collection<? extends E> collection) {
        super(collection);
    }

    @Nonnull
    @Override
    public TList<E> filter(@Nonnull Predicate<E> filter) {
        return new TArray<>(stream().filter(filter).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public <F extends E> TList<F> filter(@Nonnull Class<F> subtype) {
        return new TArray<>(filter(subtype::isInstance).map(subtype::cast));
    }

    @Nonnull
    @Override
    public <F extends E> TList<F> map(@Nonnull Function<? super E, ? extends F> mapper) {
        return new TArray<>(stream().map(mapper).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public TSet<E> set() {
        return new THashSet<>(this);
    }

    @Override
    public int uniqueSize() {
        return set().size();
    }
}
