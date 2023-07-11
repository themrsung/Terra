package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.collection.set.TSet;
import oasis.artemis.object.TObject;
import oasis.artemis.physics.Physics;
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

               final double kineticEnergy = Math.max(Physics.kineticEnergy(object), Double.MIN_VALUE);

               final double crossSection = object.getVolume().getCrossSection(object.getVector().modifyAll(-1));
               final double forceConstant = fluidDensity * object.getDragCoefficient() * crossSection;
               final double dragForce = forceConstant * Math.pow(object.getVelocity(), 2);
               final double decelerationRatio = Math.max(Double.MIN_VALUE, 1 - (dragForce / kineticEnergy));

//               if (object instanceof RealisticObject) {
//
//                   System.out.println(kineticEnergy);
//                   System.out.println(crossSection);
//                   System.out.println(forceConstant);
//                   System.out.println(dragForce);
//                   System.out.println(decelerationRatio);
//
//                   System.out.println("===");
//               }

               object.setVector(object.getVector().modifyAll(decelerationRatio));
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
