package oasis.artemis.physics;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;

import javax.annotation.Nonnull;
import java.io.Serializable;

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
) implements Serializable {
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
        final double minX = getMinX();
        final double maxX = getMaxX();
        final boolean xContains = point.x() >= minX && point.x() <= maxX;

        final double minY = getMinY();
        final double maxY = getMaxY();
        final boolean yContains = point.y() >= minY && point.y() <= maxY;

        final double minZ = getMinZ();
        final double maxZ = getMaxZ();
        final boolean zContains = point.z() >= minZ && point.z() <= maxZ;

        return xContains && yContains && zContains;
    }

    /**
     * Checks if given point is within the bounds of this TriLocation in two-dimensional context.
     * This ignores the Y value of both this TriLocation and given location.
     *
     * @param point Point to check
     * @return {@code true} if the point's X and Z coordinate are within the bounds of this TriLocation
     */
    public boolean containsIgnoreY(@Nonnull Location point) {
        final double minX = getMinX();
        final double maxX = getMaxX();
        final boolean xContains = point.x() >= minX && point.x() <= maxX;

        final double minZ = getMinZ();
        final double maxZ = getMaxZ();
        final boolean zContains = point.z() >= minZ && point.z() <= maxZ;

        return xContains && zContains;
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
     * Gets the maximum X coordinate of this TriLocation.
     *
     * @return Maximum X
     */
    public double getMaxX() {
        return center.x() + volume.x() / 2;
    }

    /**
     * Gets the minimum X coordinate of this TriLocation.
     *
     * @return Minimum X
     */
    public double getMinX() {
        return center.x() - volume.x() / 2;
    }


    /**
     * Gets the maximum Y coordinate of this TriLocation.
     *
     * @return Maximum Y
     */
    public double getMaxY() {
        return center.y() + volume.y() / 2;
    }

    /**
     * Gets the minimum Y coordinate of this TriLocation.
     *
     * @return Minimum Y
     */
    public double getMinY() {
        return center.y() - volume.y() / 2;
    }


    /**
     * Gets the maximum Z coordinate of this TriLocation.
     *
     * @return Maximum Z
     */
    public double getMaxZ() {
        return center.z() + volume.z() / 2;
    }

    /**
     * Gets the minimum Z coordinate of this TriLocation.
     *
     * @return Minimum Z
     */
    public double getMinZ() {
        return center.z() - volume.z() / 2;
    }

    /**
     * Moves this TriLocation in given direction.
     *
     * @param direction Face to move in
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
        @SuppressWarnings("ConstantConditions")
        public TriLocation build() throws IllegalArgumentException {
            if (center == null || volume == null) throw new IllegalArgumentException();
            return new TriLocation(this);
        }
    }
}
