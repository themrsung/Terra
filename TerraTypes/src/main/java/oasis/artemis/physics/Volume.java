package oasis.artemis.physics;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>Volume</h2>
 * <p>
 * Represents the volume of an object.
 * </p>
 * <p>
 * All parameters are in meters.
 * </p>
 *
 * @param x
 * @param y
 * @param z
 */
public record Volume(
        @Nonnegative double x,
        @Nonnegative double y,
        @Nonnegative double z
) implements Serializable {
    /**
     * Default volume. All fields are set to zero.
     */
    public Volume() {
        this(0, 0, 0);
    }

    /**
     * Gets a new builder instance.
     *
     * @return Builder
     */
    @Nonnull
    public static Builder builder() {return new Builder();}

    /**
     * Gets the volume in cubic meters.
     *
     * @return Volume
     */
    @Nonnegative
    public double getVolume() {
        return x * y * z;
    }

    /**
     * Gets the maximum possible cross-section of this volume.
     * This will return 0 if the direction is zero.
     * Assumes this is a cuboid unless overridden.
     *
     * @param direction Direction to get cross-section of
     * @return Cross-section of given direction
     */
    @Nonnegative
    public double getCrossSection(@Nonnull Vector direction) {
        // When the cross-section is a square
        if (direction.isFacingOnly(Face.POSITIVE_X) || direction.isFacingOnly(Face.NEGATIVE_X)) {
            return y * z;
        } else if (direction.isFacingOnly(Face.POSITIVE_Y) || direction.isFacingOnly(Face.NEGATIVE_Y)) {
            return x * z;
        } else if (direction.isFacingOnly(Face.POSITIVE_Z) || direction.isFacingOnly(Face.NEGATIVE_Z)) {
            return x * y;
        }

        // Diagonals
        final double diagonalYZ = Math.sqrt(Math.pow(y, 2) + Math.pow(z, 2));
        final double diagonalXZ = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
        final double diagonalXY = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        // When the cross-section is a rectangle
        final double xWithYZDiagonal = x * diagonalYZ;
        final double yWithXZDiagonal = y * diagonalXZ;
        final double zWithXYDiagonal = z * diagonalXY;

        if (direction.isFacingAtLeast(Face.POSITIVE_X, Face.NEGATIVE_X)) {
            if ((direction.isFacingAtLeast(Face.POSITIVE_Y, Face.NEGATIVE_Y)) && direction.z() == 0) {
                return yWithXZDiagonal;
            } else if ((direction.isFacingAtLeast(Face.POSITIVE_Z, Face.NEGATIVE_Z)) && direction.y() == 0) {
                return zWithXYDiagonal;
            }
        } else if (direction.isFacingAtLeast(Face.POSITIVE_Y, Face.NEGATIVE_Y)) {
            if ((direction.isFacingAtLeast(Face.POSITIVE_X, Face.NEGATIVE_X)) && direction.z() == 0) {
                return xWithYZDiagonal;
            } else if ((direction.isFacingAtLeast(Face.POSITIVE_Z, Face.NEGATIVE_Z)) && direction.x() == 0) {
                return zWithXYDiagonal;
            }
        } else if (direction.isFacingAtLeast(Face.POSITIVE_Z, Face.NEGATIVE_Z)) {
            if ((direction.isFacingAtLeast(Face.POSITIVE_X, Face.NEGATIVE_X)) && direction.y() == 0) {
                return xWithYZDiagonal;
            } else if ((direction.isFacingAtLeast(Face.POSITIVE_Y, Face.NEGATIVE_Y)) && direction.x() == 0) {
                return yWithXZDiagonal;
            }
        }

        // When the cross-section is a hexagon.
        final double halfDiagonalXY = diagonalXY / 2;
        final double halfDiagonalXZ = diagonalXZ / 2;
        final double halfDiagonalYZ = diagonalYZ / 2;

        if (
                direction.isFacingAll(Face.POSITIVE_X, Face.POSITIVE_Y, Face.POSITIVE_Z) ||
                        direction.isFacingAll(Face.NEGATIVE_X, Face.NEGATIVE_Y, Face.NEGATIVE_Z)
        ) {

        }

        return 0;
    }

    /**
     * Gets the cross-section of X and Y.
     *
     * @return XY cross-section
     */
    @Nonnegative
    public double getCrossSectionXY() {
        return x * y;
    }

    /**
     * Gets the cross-section of X and Z.
     *
     * @return XZ cross-section
     */
    @Nonnegative
    public double getCrossSectionXZ() {
        return x * z;
    }

    /**
     * Gets the cross-section of Y and Z.
     *
     * @return YZ cross-section
     */
    @Nonnegative
    public double getCrossSectionYZ() {
        return y * z;
    }

    /**
     * Sets the X value of this volume.
     *
     * @param x X
     * @return Resulting vector
     */
    @Nonnull
    public Volume setX(@Nonnegative double x) {
        return toBuilder().x(x).build();
    }

    /**
     * Sets the Y value of this volume.
     *
     * @param y Y
     * @return Resulting vector
     */
    @Nonnull
    public Volume setY(@Nonnegative double y) {
        return toBuilder().y(y).build();
    }

    /**
     * Sets the Z value of this volume.
     *
     * @param z Z
     * @return Resulting vector
     */
    @Nonnull
    public Volume setZ(@Nonnegative double z) {
        return toBuilder().z(z).build();
    }

    /**
     * Converts this volume to builder for modification.
     *
     * @return Builder
     */
    @Nonnull
    public Builder toBuilder() {
        return new Builder(this);
    }

    private Volume(@Nonnull Builder builder) {
        this(builder.x, builder.y, builder.z);
    }

    public static final class Builder {
        private Builder() {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }

        private Builder(@Nonnull Volume volume) {
            this.x = volume.x;
            this.y = volume.y;
            this.z = volume.z;
        }

        private double x;
        private double y;
        private double z;

        @Nonnull
        public Builder x(@Nonnegative double x) {
            this.x = x;
            return this;
        }

        @Nonnull
        public Builder y(@Nonnegative double y) {
            this.y = y;
            return this;
        }

        @Nonnull
        public Builder z(@Nonnegative double z) {
            this.z = z;
            return this;
        }

        @Nonnull
        public Builder xyz(@Nonnegative double x, @Nonnegative double y, @Nonnegative double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        @Nonnull
        public Volume build() {
            return new Volume(this);
        }
    }
}
