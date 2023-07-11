package oasis.artemis.physics;

import oasis.artemis.math.TMath;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>Vector</h2>
 * <p>
 * Represents a direction in three-dimensional context.
 * This is used to represent acceleration.
 * </p>
 * <p>
 * All parameters are values per tick.
 * </p>
 *
 * @param x The velocity of the X coordinate per tick
 * @param y The velocity of the Y coordinate per tick
 * @param z The velocity of the Z coordinate per tick
 */
public record Vector(
        double x,
        double y,
        double z
) implements Serializable {
    /**
     * Creates a new vector with default values {@code 0, 0, 0}.
     */
    public Vector() {
        this(0, 0, 0);
    }

    /**
     * Represents a vector with no velocity. Every value is 0.
     */
    public static final Vector ZERO = new Vector();

    public static final Vector UP = new Vector(0, 1, 0);
    public static final Vector DOWN = new Vector(0, -1, 0);
    public static final Vector FRONT = new Vector(1, 0, 0);
    public static final Vector REAR = new Vector(-1, 0, 0);
    public static final Vector RIGHT = new Vector(0, 0, 1);
    public static final Vector LEFT = new Vector(0, 0, -1);

    /**
     * Gets a new empty builder instance.
     *
     * @return Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the magnitude of this vector.
     *
     * @return Magnitude
     */
    @Nonnegative
    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Gets the diagonal magnitude between X and Y of this vector.
     * @return Diagonal magnitude
     */
    @Nonnegative
    public double getMagnitudeXY() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Gets the diagonal magnitude between X and Z of this vector.
     * @return Diagonal magnitude
     */
    @Nonnegative
    public double getMagnitudeXZ() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
    }

    /**
     * Gets the diagonal magnitude between Y and Z of this vector.
     * @return Diagonal magnitude
     */
    @Nonnegative
    public double getMagnitudeYZ() {
        return Math.sqrt(Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Checks if this vector has a positive magnitude towards given facing.
     * @param facing Facing to check
     * @return {@code true} if this vector is facing given face
     */
    public boolean isFacing(@Nonnull Face facing) {
        return switch (facing) {
            case POSITIVE_X -> x > 0;
            case POSITIVE_Y -> y > 0;
            case POSITIVE_Z -> z > 0;
            case NEGATIVE_X -> x < 0;
            case NEGATIVE_Y -> y < 0;
            case NEGATIVE_Z -> z < 0;
        };
    }

    /**
     * Checks if this vector has a positive magnitude towards all given facings.
     * @param facings Facings to check
     * @return {@code true} if this vector is facing all given faces
     */
    public boolean isFacingAll(@Nonnull Face... facings) {
        for (Face f : facings) if (!isFacing(f)) return false;
        return true;
    }

    /**
     * Checks if this vector has a positive magnitude to at least one of the given facings.
     * @param facings Facings to check
     * @return {@code true} if this vector is facing at least one of the given faces
     */
    public boolean isFacingAtLeast(@Nonnull Face... facings) {
        for (Face f : facings) if (isFacing(f)) return true;
        return false;
    }

    /**
     * Checks if this vector is only facing towards given facing.
     * @param facing Facing to check
     * @return {@code true} if this vector is facing given face, and all other magnitudes are zero
     */
    public boolean isFacingOnly(@Nonnull Face facing) {
        return isFacing(facing) && switch (facing) {
            case POSITIVE_X, NEGATIVE_X -> y == 0 && z == 0;
            case POSITIVE_Y, NEGATIVE_Y -> x == 0 && z == 0;
            case POSITIVE_Z, NEGATIVE_Z -> x == 0 && y == 0;
        };
    }

    /**
     * Checks if this all fields of this vector equal to zero.
     * @return {@code true} if all fields are zero
     */
    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    /**
     * Gets the yaw of this vector. (degree on axis of Y)
     * @return Yaw
     */
    public double getYaw() {
        if (isFacing(Face.POSITIVE_X)) {
            return TMath.itan(x / z);
        } else if (isFacing(Face.NEGATIVE_X)) {
            return TMath.addMagnitude(TMath.itan(-x / z), 90);
        } else {
            return isFacing(Face.POSITIVE_Z) ? 90 : (isFacing(Face.NEGATIVE_Z) ? -90 : 0);
        }
    }

    /**
     * Gets the pitch of this vector. (degree on axis of Z)
     * @return Pitch
     */
    public double getPitch() {
        if (isFacing(Face.POSITIVE_X)) {
            return TMath.itan(x / y);
        } else if (isFacing(Face.NEGATIVE_X)) {
            return TMath.addMagnitude(TMath.itan(-x / y), 90);
        } else {
            return isFacing(Face.POSITIVE_Y) ? 90 : (isFacing(Face.NEGATIVE_Y) ? -90 : 0);
        }
    }

    /**
     * Gets the roll of this vector. (degree on axis of X)
     * @return Roll
     */
    public double getRoll() {
        if (isFacing(Face.POSITIVE_Z)) {
            return TMath.itan(z / y);
        } else if (isFacing(Face.NEGATIVE_Z)) {
            return TMath.addMagnitude(TMath.itan(-z / y), 90);
        } else {
            return isFacing(Face.POSITIVE_Y) ? 90 : (isFacing(Face.NEGATIVE_Y) ? -90 : 0);
        }
    }

    /**
     * Sets the X value of this vector.
     *
     * @param x X
     * @return Resulting vector
     */
    @Nonnull
    public Vector setX(double x) {
        return toBuilder().x(x).build();
    }

    /**
     * Sets the Y value of this vector.
     *
     * @param y Y
     * @return Resulting vector
     */
    @Nonnull
    public Vector setY(double y) {
        return toBuilder().y(y).build();
    }

    /**
     * Sets the Z value of this vector.
     *
     * @param z Z
     * @return Resulting vector
     */
    @Nonnull
    public Vector setZ(double z) {
        return toBuilder().z(z).build();
    }

    /**
     * Adds delta to the X value of this vector.
     *
     * @param delta Delta
     * @return Resulting vector
     */
    @Nonnull
    public Vector plusX(double delta) {
        return toBuilder().x(x + delta).build();
    }

    /**
     * Adds delta to the Y value of this vector.
     *
     * @param delta Delta
     * @return Resulting vector
     */
    @Nonnull
    public Vector plusY(double delta) {
        return toBuilder().y(y + delta).build();
    }

    /**
     * Adds delta to the z value of this vector.
     *
     * @param delta Delta
     * @return Resulting vector
     */
    @Nonnull
    public Vector plusZ(double delta) {
        return toBuilder().z(z + delta).build();
    }

    /**
     * Adds another vector to this vector.
     *
     * @param other Other vector
     * @return Resulting vector
     */
    @Nonnull
    public Vector plus(@Nonnull Vector other) {
        return plusX(other.x).plusY(other.y).plusZ(other.z);
    }

    /**
     * Modifies the X value of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyX(double modifier) {
        return toBuilder().x(x * modifier).build();
    }

    /**
     * Modifies the Y value of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyY(double modifier) {
        return toBuilder().y(y * modifier).build();
    }

    /**
     * Modifies the Z value of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyZ(double modifier) {
        return toBuilder().z(z * modifier).build();
    }

    /**
     * Modifies all values of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyAll(double modifier) {
        return toBuilder()
                .x(x * modifier)
                .y(y * modifier)
                .z(z * modifier)
                .build();
    }

    /**
     * Converts this vector to builder for modification.
     *
     * @return Builder
     */
    @Nonnull
    public Builder toBuilder() {
        return new Builder(this);
    }

    private Vector(@Nonnull Builder builder) {
        this(builder.x, builder.y, builder.z);
    }

    public static final class Builder {
        private Builder() {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }

        private Builder(@Nonnull Vector vector) {
            this.x = vector.x;
            this.y = vector.y;
            this.z = vector.z;
        }

        private double x;
        private double y;
        private double z;

        @Nonnull
        public Builder x(double x) {
            this.x = x;
            return this;
        }

        @Nonnull
        public Builder y(double y) {
            this.y = y;
            return this;
        }

        @Nonnull
        public Builder z(double z) {
            this.z = z;
            return this;
        }

        @Nonnull
        public Builder xyz(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        @Nonnull
        public Vector build() {
            return new Vector(this);
        }
    }
}
