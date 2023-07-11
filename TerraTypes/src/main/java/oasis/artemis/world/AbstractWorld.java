package oasis.artemis.world;

import oasis.artemis.collection.set.THashSet;
import oasis.artemis.collection.set.TSet;
import oasis.artemis.object.TObject;
import oasis.artemis.string.TString;

import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * <h2>AbstractWorld</h2>
 * <p>
 * An abstract world is the base platform for easier development.
 * There are no built-in restrictions to parameters.
 * </p>
 */
public abstract class AbstractWorld implements World {
    /**
     * Default minimal constructor.
     *
     * @param uniqueId Unique ID of this world
     * @param name     Name of this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull TString name) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = new THashSet<>();
        this.gravity = World.DEFAULT_GRAVITY;
        this.airDensity = World.DEFAULT_AIR_DENSITY;
        this.groundLevel = World.DEFAULT_GROUND_LEVEL;
    }

    /**
     * Default maximal constructor.
     *
     * @param uniqueId    Unique ID of this world
     * @param name        Name of this world
     * @param gravity     Gravity of this world
     * @param airDensity  Air density of this world
     * @param groundLevel Ground level of this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull TString name, double gravity, double airDensity, double groundLevel) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = new THashSet<>();
        this.gravity = gravity;
        this.airDensity = airDensity;
        this.groundLevel = groundLevel;
    }

    /**
     * Default all-args constructor.
     *
     * @param uniqueId    Unique ID of this world
     * @param name        Name of this world
     * @param objects     Objects contained in this world
     * @param gravity     Gravity of this world
     * @param airDensity  Air density of this world
     * @param groundLevel Ground level of this world
     */
    public AbstractWorld(@Nonnull UUID uniqueId, @Nonnull TString name, @Nonnull TSet<TObject> objects, double gravity, double airDensity, double groundLevel) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = objects;
        this.gravity = gravity;
        this.airDensity = airDensity;
        this.groundLevel = groundLevel;
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private TString name;
    @Nonnull
    private final TSet<TObject> objects;
    private double gravity;
    @Positive
    private double airDensity;
    private double groundLevel;

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public TString getName() {
        return name;
    }

    @Override
    @Nonnull
    public TSet<TObject> getObjects() {
        return new THashSet<>(objects);
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public double getGroundLevel() {
        return groundLevel;
    }

    @Override
    public void setName(@Nonnull TString name) {
        this.name = name;
    }

    @Override
    public void addObject(@Nonnull TObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull TObject object) {
        objects.remove(object);
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }

    @Override
    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }
}
