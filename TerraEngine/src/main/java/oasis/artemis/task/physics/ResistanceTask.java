package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.collection.set.TSet;
import oasis.artemis.object.TObject;
import oasis.artemis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>ResistanceTask</h2>
 * <p>
 *     Handles the resistance of objects in worlds.
 *     Resistance is calculated using the density of the fluid the object is in.
 *     If the object is in multiple fluids,
 *     the fluid with the highest density is used.
 * </p>
 */
public final class ResistanceTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(world -> {
           world.getObjects().forEach(object -> {
               final TSet<TObject> fluids = world.getObjects()
                       .filter(o -> !o.equals(object))
                       .filter(o -> o.isFluid() && object.overlaps(o));

               double fluidDensity = fluids.size() == 0 ? world.getAirDensity() : 0;
               for (TObject fluid : fluids) {
                   if (fluid.getDensity() > fluidDensity) fluidDensity = fluid.getDensity();
               }

               final double crossSection = object.getVolume().getCrossSection(object.getVector().modifyAll(-1));
               final double forceConstant = fluidDensity * object.getDragCoefficient() * crossSection;
               final double dragForce = forceConstant * Math.pow(object.getVelocity(), 2);
               final double deceleration = -1 * dragForce / object.getMassKilograms();

//               System.out.println(crossSection);
//               System.out.println(forceConstant);
//               System.out.println(dragForce);
//               System.out.println(deceleration);
//
//               System.out.println("===");

//               object.setVector(object.getVector().plusY(deceleration));
           });
        });
    }

    @Nonnull
    @Override
    public Duration getDelay() {
        return Duration.ZERO;
    }

    @Nonnull
    @Override
    public Duration getInterval() {
        return Duration.ZERO;
    }
}
