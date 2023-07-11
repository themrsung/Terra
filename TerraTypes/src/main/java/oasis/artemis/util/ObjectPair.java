package oasis.artemis.util;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.object.TObject;

import javax.annotation.Nonnull;

/**
 * An object pair represents a relationship between two objects.
 *
 * @param object1 First object
 * @param object2 Second object
 */
public record ObjectPair(
        @Nonnull TObject object1,
        @Nonnull TObject object2
) {
    /**
     * Converts this pair to a list.
     *
     * @return {@link TList}
     */
    public TList<Object> list() {
        return new TArray<>(object1, object2);
    }

    /**
     * Checks if given object is either the first or second object of this pair.
     *
     * @param object Object to check
     * @return {@code true} if either object 1 or object 2 equals given object
     */
    public boolean contains(@Nonnull TObject object) {
        return list().contains(object);
    }

    /**
     * Checks if this object pair equals the other.
     * Order of the objects is ignored, and will return {@code true} if both objects in both pairs are equal.
     *
     * @param pair Pair to compare to
     * @return {@code true} if both objects in both pairs are equal
     */
    public boolean equals(@Nonnull ObjectPair pair) {
        final boolean case1 = object1.equals(pair.object1) && object2.equals(pair.object2);
        final boolean case2 = object1.equals(pair.object2) && object2.equals(pair.object1);
        return case1 || case2;
    }
}
