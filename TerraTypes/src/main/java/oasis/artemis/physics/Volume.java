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
     * Gets the cross-section of this volume.
     *
     * @param facing Facing to get
     * @return Cross-section of given facing
     */
    @Nonnegative
    public double getCrossSection(@Nonnull Face facing) {
        return switch (facing) {
            case POSITIVE_X, NEGATIVE_X -> getCrossSectionYZ();
            case POSITIVE_Y, NEGATIVE_Y -> getCrossSectionXZ();
            case POSITIVE_Z, NEGATIVE_Z -> getCrossSectionXY();
        };
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
