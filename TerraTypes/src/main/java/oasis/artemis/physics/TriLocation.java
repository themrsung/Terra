package oasis.artemis.physics;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;

import javax.annotation.Nonnull;

/**
 * <h2>TriLocation</h2>
 * <p>
 * A TriLocation represents a three dimensional space.
 * TriLocations are defined by a center point and a volume.
 * </p>
 *
 * @param center The center of this TriLocation
 * @param volume The volume of this TriLocation
 */
public record TriLocation(
        @Nonnull Location center,
        @Nonnull Volume volume
) {
    /**
     * Gets a new builder instance.
     *
     * @return Builder
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the eight corners of this TriLocation.
     *
     * @return List of eight corners
     */
    @Nonnull
    public TList<Location> getCorners() {
        return new TArray<>(
                center.plusX(volume.x() / 2).plusY(volume.y() / 2).plusZ(volume.z() / 2),
                center.plusX(volume.x() / 2).plusY(volume.y() / 2).plusZ(-volume.z() / 2),
                center.plusX(volume.x() / 2).plusY(-volume.y() / 2).plusZ(volume.z() / 2),
                center.plusX(volume.x() / 2).plusY(-volume.y() / 2).plusZ(-volume.z() / 2),
                center.plusX(-volume.x() / 2).plusY(volume.y() / 2).plusZ(volume.z() / 2),
                center.plusX(-volume.x() / 2).plusY(volume.y() / 2).plusZ(-volume.z() / 2),
                center.plusX(-volume.x() / 2).plusY(-volume.y() / 2).plusZ(volume.z() / 2),
                center.plusX(-volume.x() / 2).plusY(-volume.y() / 2).plusZ(-volume.z() / 2)
        );
    }

    /**
     * Checks if given point is within the bounds of this TriLocation.
     *
     * @param point Point to check
     * @return {@code true} if the point is within this TriLocation
     */
    public boolean contains(@Nonnull Location point) {
        final double minX = center.x() - volume.x() / 2;
        final double maxX = center.x() + volume.x() / 2;
        final boolean xContains = point.x() >= minX && point.x() <= maxX;

        final double minY = center.y() - volume.y() / 2;
        final double maxY = center.y() + volume.y() / 2;
        final boolean yContains = point.y() >= minY && point.y() <= maxY;

        final double minZ = center.z() - volume.z() / 2;
        final double maxZ = center.z() + volume.z() / 2;
        final boolean zContains = point.z() >= minZ && point.z() <= maxZ;

        return xContains && yContains && zContains;
    }

    /**
     * Checks if this TriLocation contains another.
     *
     * @param other TriLocation to check
     * @return {@code true} if all eight corners are within the bounds of this TriLocation
     */
    public boolean contains(@Nonnull TriLocation other) {
        for (Location corner : other.getCorners()) {
            if (!contains(corner)) return false;
        }

        return true;
    }

    /**
     * Checks if this TriLocation overlaps another.
     *
     * @param other TriLocation to check
     * @return {@code true} if at least one of the eight corners are within the bounds of this TriLocation
     */
    public boolean overlaps(@Nonnull TriLocation other) {
        for (Location corner : other.getCorners()) {
            if (contains(corner)) return true;
        }

        return false;
    }

    /**
     * Moves this TriLocation in given direction.
     *
     * @param direction Direction to move in
     * @return Resulting TriLocation
     */
    @Nonnull
    public TriLocation move(@Nonnull Vector direction) {
        return toBuilder().center(center.plusVector(direction)).build();
    }

    /**
     * Converts to builder for modification.
     *
     * @return Builder
     */
    @Nonnull
    public Builder toBuilder() {
        return new Builder(this);
    }

    private TriLocation(@Nonnull Builder builder) {
        this(builder.center, builder.volume);
    }

    public static final class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.center = null;
            this.volume = null;
        }

        private Builder(@Nonnull TriLocation triLocation) {
            this.center = triLocation.center;
            this.volume = triLocation.volume;
        }

        @Nonnull
        private Location center;
        @Nonnull
        private Volume volume;

        @Nonnull
        public Builder center(@Nonnull Location center) {
            this.center = center;
            return this;
        }

        @Nonnull
        public Builder volume(@Nonnull Volume volume) {
            this.volume = volume;
            return this;
        }

        /**
         * Builds a TriLocation.
         *
         * @return Built TriLocation
         * @throws IllegalArgumentException When at least one of the required parameters are null
         */
        @Nonnull
        public TriLocation build() throws IllegalArgumentException {
            return new TriLocation(this);
        }
    }
}
