package oasis.artemis.physics;

import oasis.artemis.collection.set.TSet;
import oasis.artemis.object.TObject;
import oasis.artemis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Physics</h2>
 * <p>
 * Physics contains the basic utilities required for various calculations.
 * This is not intended to be instantiated.
 * </p>
 */
public interface Physics {
    /**
     * Gets the acceleration due to gravity in given world during given duration.
     *
     * @param world    World to get gravity from
     * @param duration Used to calculate the precise amount of acceleration
     * @return Gravity in given world during given duration
     */
    static double gravity(@Nonnull World world, @Nonnull Duration duration) {
        return world.getGravity() / 1000 * duration.getMillis();
    }

    /**
     * Gets the terminal velocity of given object, denoted in meters per second.
     * This also accounts for the fluid's density the object is in.
     * If any of the parameters in the denominator of this equation is non-positive,
     * they will be assumed to be {@link Double#MIN_VALUE}.
     *
     * @param object    Object to get terminal velocity of
     * @param direction The direction in which to get the terminal velocity of
     * @return Terminal velocity of object in given world
     */
    @Nonnegative
    static double terminalVelocity(@Nonnull TObject object, @Nonnull Vector direction) {
        final World world = object.getWorld();
        double fluidDensity = Math.max(world.getAirDensity(), Double.MIN_VALUE);

        final TSet<TObject> fluids = world.getObjects().filter(TObject::isFluid);
        for (TObject fluid : fluids) {
            if (fluid.overlaps(object)) {
                fluidDensity = Math.max(fluid.getDensity(), Double.MIN_VALUE);
                break;
            }
        }

        final double mass = object.getMassKilograms();
        final double crossSection = Math.max(object.getVolume().getCrossSection(direction), Double.MIN_VALUE);
        final double dragCoefficient = Math.max(object.getDragCoefficient(), Double.MIN_VALUE);
        final double gravity = world.getGravity();

        return Math.sqrt((2 * mass * gravity) / (fluidDensity * crossSection * dragCoefficient));
    }

    /**
     * Gets the kinetic energy of an object.
     *
     * @param object Object
     * @return Kinetic energy, denoted in Joules
     */
    static double kineticEnergy(@Nonnull TObject object) {
        return 0.5 * object.getMassKilograms() * Math.pow(object.getVector().getMagnitude(), 2);
    }

    /**
     * Gets the potential energy of an object.
     *
     * @param object Object
     * @return Potential energy, denoted in Joules
     */
    static double potentialEnergy(@Nonnull TObject object) {
        final World world = object.getWorld();
        double heightFromGround = object.getLocation().y() - object.getWorld().getGroundLevel();

        final TSet<TObject> objects = world.getObjects();
        for (TObject o : objects) {
            if (isStandingOn(object, o.getTriLocation()) && o.getTriLocation().getMaxY() > world.getGroundLevel()) {
                heightFromGround = object.getLocation().y() - o.getTriLocation().getMaxY();
            }
        }

        return object.getMassKilograms() * object.getWorld().getGravity() * heightFromGround;
    }

    /**
     * Gets the mechanical energy of an object.
     *
     * @param object Object
     * @return Mechanical energy, denoted in Joules
     */
    static double mechanicalEnergy(@Nonnull TObject object) {return kineticEnergy(object) + potentialEnergy(object);}

    /**
     * Checks if the first object (henceforth o1) is above the second object (henceforth o2).
     * This will check if o1's X and Z coordinates are within the bounds of o2's TriLocation,
     * and whether o1's TriLocation's minimum Y is equal to or smaller than o2's TriLocation's maximum Y.
     *
     * @param o1 Object 1
     * @param o2 Object 2
     * @return {@code true} if o1 is above o2
     */
    static boolean isStandingOn(@Nonnull TObject o1, @Nonnull TObject o2) {
        return isStandingOn(o1.getTriLocation(), o2.getTriLocation());
    }

    /**
     * Checks if the object is above given TriLocation.
     * This will check if the object's X and Z coordinate are within the bounds of given TriLocation,
     * and whether the object's TriLocation's minimum Y is equal to or smaller than the given TriLocation's maximum Y.
     * This method delegates to {@link Physics#isStandingOn(TriLocation, TriLocation)}.
     *
     * @param object      Object
     * @param triLocation TriLocation to check
     * @return {@code true} if the object is on top of given TriLocation
     */
    static boolean isStandingOn(@Nonnull TObject object, @Nonnull TriLocation triLocation) {
        return isStandingOn(object.getTriLocation(), triLocation);
    }

    /**
     * Checks if the first TriLocation (henceforth t1) is on top of the second TriLocation (henceforth t2).
     * This will check if t1's median X and Z coordinate are within the bounds of t2,
     * and whether t1's minimum Y is equal to or smaller than t2's maximum Y.
     *
     * @param t1 TriLocation 1
     * @param t2 TriLocation 2
     * @return {@code true} if t1 is above t2
     */
    static boolean isStandingOn(@Nonnull TriLocation t1, @Nonnull TriLocation t2) {
        return t2.containsIgnoreY(t1.center()) && t1.getMinY() <= t2.getMaxY();
    }
}
