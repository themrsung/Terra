package oasis.artemis.object;

import oasis.artemis.physics.Location;
import oasis.artemis.physics.Mass;
import oasis.artemis.physics.Vector;
import oasis.artemis.physics.Volume;
import oasis.artemis.texture.Texture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>A basic implementation of {@link Object} for easier development.</p>
 */
public abstract class AbstractObject implements TObject {
    /**
     * Default minimal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Location location) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.vector = new Vector();
        this.mass = new Mass();
        this.volume = new Volume();
        this.fluid = false;
        this.obeysPhysics = true;
        this.dragCoefficient = DEFAULT_DRAG_COEFFICIENT;
    }

    /**
     * Default normal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     * @param mass     Mass of this object
     * @param volume   Volume of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.vector = new Vector();
        this.mass = mass;
        this.volume = volume;
        this.fluid = false;
        this.obeysPhysics = true;
        this.dragCoefficient = DEFAULT_DRAG_COEFFICIENT;
    }

    /**
     * Default semi-maximal constructor.
     *
     * @param uniqueId     Unique ID of this object
     * @param location     Location of this object
     * @param mass         Mass of this object
     * @param volume       Volume of this object
     * @param fluid        Whether this object is a fluid
     * @param obeysPhysics Whether this object obeys physics
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.vector = new Vector();
        this.mass = mass;
        this.volume = volume;
        this.fluid = fluid;
        this.obeysPhysics = obeysPhysics;
        this.dragCoefficient = DEFAULT_DRAG_COEFFICIENT;
    }

    /**
     * Default all-args constructor.
     *
     * @param uniqueId        Unique ID of this object
     * @param location        Location of this object
     * @param vector          Vector of this object
     * @param mass            Mass of this object
     * @param volume          Volume of this object
     * @param fluid           Whether this object is a fluid
     * @param obeysPhysics    Whether this object obeys physics
     * @param dragCoefficient The drag coefficient of this object
     */
    public AbstractObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Vector vector, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics, @Positive double dragCoefficient) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.vector = vector;
        this.mass = mass;
        this.volume = volume;
        this.fluid = fluid;
        this.obeysPhysics = obeysPhysics;
        this.dragCoefficient = dragCoefficient;
    }

    /**
     * Default copy constructor.
     *
     * @param other Other object
     */
    public AbstractObject(@Nonnull AbstractObject other) {
        this.uniqueId = other.uniqueId;
        this.location = other.location;
        this.vector = other.vector;
        this.mass = other.mass;
        this.volume = other.volume;
        this.fluid = other.fluid;
        this.obeysPhysics = other.obeysPhysics;
        this.dragCoefficient = other.dragCoefficient;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Location location;
    @Nonnull
    private Vector vector;
    @Nonnull
    private Mass mass;
    @Nonnull
    private Volume volume;
    private boolean fluid;
    private boolean obeysPhysics;
    @Positive
    private double dragCoefficient;

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Location getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector getVector() {
        return vector;
    }

    @Override
    @Nonnull
    public Mass getMass() {
        return mass;
    }

    @Override
    @Nonnull
    public Volume getVolume() {
        return volume;
    }

    @Override
    public boolean isFluid() {
        return fluid;
    }

    @Override
    public boolean obeysPhysics() {
        return obeysPhysics;
    }

    @Positive
    @Override
    public double getDragCoefficient() {
        return dragCoefficient;
    }

    @Override
    public void setLocation(@Nonnull Location location) {
        this.location = location;
    }

    @Override
    public void setVector(@Nonnull Vector vector) {
        this.vector = vector;
    }

    @Override
    public void setMass(@Nonnull Mass mass) {
        this.mass = mass;
    }

    @Override
    public void setVolume(@Nonnull Volume volume) {
        this.volume = volume;
    }

    @Override
    public void setFluid(boolean fluid) {
        this.fluid = fluid;
    }

    @Override
    public void setObeysPhysics(boolean obeysPhysics) {
        this.obeysPhysics = obeysPhysics;
    }

    @Override
    public void setDragCoefficient(@Positive double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    @Nullable
    @Override
    public Texture getTexture() {
        return null;
    }
}
