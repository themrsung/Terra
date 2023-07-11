package oasis.artemis.physics;

import oasis.artemis.exception.physics.DifferentWorldException;
import oasis.artemis.world.World;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>Location</h2>
 * <p>
 * Represents a point in a three-dimensional plane.
 * Since yaw, pitch and roll represent the rotation of an object,
 * their getters are designed to convert them to within the acceptable range.</p>
 *
 * </p>
 * <p>
 * <b>Every coordinate is denoted in meters.</b>
 * </p>
 *
 * @param world World this location is in
 * @param x     X coordinate of this location (scalar of width)
 * @param y     Y coordinate of this location (scalar of height)
 * @param z     Z coordinate of this location (scalar of depth)
 * @param yaw   Yaw (degree on the axis of Y)
 * @param pitch Pitch (degree on the axis of Z)
 * @param roll  Roll (degree on the axis of X)
 */
public record Location(
        @Nonnull World world,
        double x,
        double y,
        double z,
        double yaw, // -180 <= yaw <= 180
        double pitch, // -180 <= pitch <= 180
        double roll // -180 <= roll <= 180
) implements Serializable {
    /**
     * Constructor which ignores yaw and pitch.
     *
     * @param world World this location is in
     * @param x     X coordinate of this location
     * @param y     Y coordinate of this location
     * @param z     Z coordinate of this location
     */
    public Location(@Nonnull World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0, 0);
    }

    /**
     * Gets the builder instance for easier construction.
     * To edit this location, use either the modifier methods,
     * or {@link Builder}.
     * Note that world is null by default and requires to be set.
     *
     * @return Blank builder instance.
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    // Getters

    /**
     * Converts the yaw to fit within the accepted range.
     *
     * @return Yaw
     */
    @Override
    public double yaw() {
        if (yaw > 180 || yaw < -180) {
            return ((yaw + 180) % 360) - 180;
        }

        return yaw;
    }

    /**
     * Converts the pitch to fit within the accepted range.
     *
     * @return Pitch
     */
    @Override
    public double pitch() {
        if (pitch > 180 || pitch < -180) {
            return ((pitch + 180) % 360) - 180;
        }
        return pitch;
    }

    /**
     * Converts the roll to fit within the accepted range.
     *
     * @return Roll
     */
    @Override
    public double roll() {
        if (roll > 180 || roll < -180) {
            return ((roll + 180) % 360) - 180;
        }
        return roll;
    }

    // Modifiers

    /**
     * Adds delta to X coordinate.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusX(double delta) {
        return toBuilder().x(x + delta).build();
    }

    /**
     * Adds delta to Y coordinate.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusY(double delta) {
        return toBuilder().y(y + delta).build();
    }

    /**
     * Adds delta to Z coordinate.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusZ(double delta) {
        return toBuilder().z(z + delta).build();
    }

    /**
     * Adds delta to yaw.
     *
     * @param delta Delta to add
     * @return Resulting yaw
     */
    @Nonnull
    public Location plusYaw(double delta) {
        return toBuilder().yaw(yaw + delta).build();
    }

    /**
     * Adds delta to pitch.
     *
     * @param delta Delta to add
     * @return Resulting pitch
     */
    @Nonnull
    public Location plusPitch(double delta) {
        return toBuilder().pitch(pitch + delta).build();
    }

    /**
     * Changes the X coordinate of this location.
     *
     * @param x X coordinate
     * @return Resulting location
     */
    @Nonnull
    public Location setX(double x) {
        return toBuilder().x(x).build();
    }

    /**
     * Changes the Y coordinate of this location.
     *
     * @param y Y coordinate
     * @return Resulting location
     */
    @Nonnull
    public Location setY(double y) {
        return toBuilder().y(y).build();
    }

    /**
     * Changes the Z coordinate of this location.
     *
     * @param z Z coordinate
     * @return Resulting location
     */
    @Nonnull
    public Location setZ(double z) {
        return toBuilder().z(z).build();
    }

    /**
     * Changes the yaw of this location.
     *
     * @param yaw Yaw
     * @return Resulting location
     */
    @Nonnull
    public Location setYaw(double yaw) {
        return toBuilder().yaw(yaw).build();
    }

    /**
     * Changes the pitch of this location.
     *
     * @param pitch pitch
     * @return Resulting location
     */
    @Nonnull
    public Location setPitch(double pitch) {
        return toBuilder().pitch(pitch).build();
    }

    /**
     * Changes the roll of this location.
     *
     * @param roll Roll
     * @return Resulting location
     */
    @Nonnull
    public Location setRoll(double roll) {
        return toBuilder().roll(roll).build();
    }

    /**
     * Adds delta to the yaw of this location.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location addYaw(double delta) {
        return toBuilder().yaw(yaw + delta).build();
    }

    /**
     * Adds delta to the pitch of this location.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location addPitch(double delta) {
        return toBuilder().pitch(pitch + delta).build();
    }

    /**
     * Adds delta to the roll of this location.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location addRoll(double delta) {
        return toBuilder().roll(roll + delta).build();
    }

    /**
     * Adds another location to this location.
     *
     * @param other Location to add
     * @return Resulting location
     * @throws IllegalArgumentException When the worlds are different
     */
    @Nonnull
    public Location plusLocation(@Nonnull Location other) throws DifferentWorldException {
        if (!world.equals(other.world)) throw new DifferentWorldException(world, other.world);

        return toBuilder()
                .x(x + other.x)
                .y(y + other.y)
                .z(z + other.z)
                .yaw(yaw + other.yaw)
                .pitch(pitch + other.pitch)
                .roll(roll + other.roll)
                .build();
    }

    /**
     * Adds a vector to this location.
     *
     * @param vector Vector to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusVector(@Nonnull Vector vector) {
        return toBuilder()
                .x(x + vector.x())
                .y(y + vector.y())
                .z(z + vector.z())
                .build();
    }

    // Util

    /**
     * Gets the distance between this location and the given location.
     * Distance is calculated in three-dimensional context.
     * For two-dimensional distance, use {@link Location#getDistanceToIgnoreY(Location)}.
     *
     * @param other Other location
     * @return Distance in meters
     * @throws DifferentWorldException When the worlds are different
     */
    @Nonnegative
    public double getDistanceTo(@Nonnull Location other) throws DifferentWorldException {
        if (!world.equals(other.world)) throw new DifferentWorldException(world, other.world);
        return Math.sqrt(Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z));
    }

    /**
     * Gets the distance between this location and the given location.
     * Distance is calculated in two-dimensional context. (delta of Y is ignored)
     *
     * @param other Other location
     * @return Distance in meters
     * @throws DifferentWorldException When the worlds are different
     */
    @Nonnegative
    public double getDistanceToIgnoreY(@Nonnull Location other) throws DifferentWorldException {
        if (!world.equals(other.world)) throw new DifferentWorldException(world, other.world);
        return Math.sqrt(Math.abs(x - other.x) + Math.abs(y - other.y));
    }

    /**
     * Get a builder pre-populated with the data of this location.
     *
     * @return Builder from location
     */
    public Builder toBuilder() {return new Builder(this);}

    private Location(@Nonnull Builder builder) {
        this(builder.world, builder.x, builder.y, builder.z, builder.yaw, builder.pitch, builder.roll);
    }

    /**
     * <h2>Location.Builder</h2>
     * <p>Since locations are immutable records, a builder is used to edit its properties.</p>
     */
    public static final class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.world = null;
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.yaw = 0;
            this.pitch = 0;
        }

        private Builder(@Nonnull Location location) {
            this.world = location.world;
            this.x = location.x;
            this.y = location.y;
            this.z = location.z;
            this.yaw = location.yaw;
            this.pitch = location.pitch;
        }

        @Nonnull
        private World world;
        private double x;
        private double y;
        private double z;
        private double yaw;
        private double pitch;
        private double roll;

        @Nonnull
        public Builder world(@Nonnull World world) {
            this.world = world;
            return this;
        }

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
        public Builder yaw(double yaw) {
            this.yaw = yaw;
            return this;
        }

        @Nonnull
        public Builder pitch(double pitch) {
            this.pitch = pitch;
            return this;
        }

        @Nonnull
        public Builder roll(double roll) {
            this.roll = roll;
            return this;
        }

        /**
         * Finishes the building sequence and builds the {@link Location}.
         *
         * @return new {@link Location}
         * @throws IllegalArgumentException When the required field {@link Builder#world} is null
         */
        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public Location build() throws IllegalArgumentException {
            // Check for null world
            if (world == null) throw new IllegalArgumentException();

            // Correct yaw, pitch, and roll
            if (pitch > 180 || pitch < -180) pitch = ((pitch + 180) % 360) - 180;
            if (yaw > 180 || yaw < -180) yaw = ((yaw + 180) % 360) - 180;
            if (roll > 180 || yaw < -180) roll = ((roll + 180) % 360) - 180;

            return new Location(this);
        }
    }
}
