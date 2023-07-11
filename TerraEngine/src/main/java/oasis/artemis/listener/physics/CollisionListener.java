package oasis.artemis.listener.physics;

import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.listener.Listener;
import oasis.artemis.object.TObject;
import oasis.artemis.physics.Vector;

import javax.annotation.Nonnull;

public final class CollisionListener implements Listener<CollisionEvent> {
    @Override
    public void handle(@Nonnull CollisionEvent event) {
        if (event.isCancelled()) return;

        final TObject o1 = event.getObject1();
        final TObject o2 = event.getObject2();

//        System.out.println("Collision!");
//
//        System.out.println("o1 = " + o1.getUniqueId());
//
//        System.out.println(getFinalVelocity(o1, o2));
//        System.out.println(getFinalVelocity(o2, o1));

        o1.setVector(getFinalVelocity(o1, o2));
        o2.setVector(getFinalVelocity(o2, o1));
    }

    @Nonnull
    private Vector getFinalVelocity(@Nonnull TObject victim, @Nonnull TObject collider) {
        final double m1 = victim.getMassKilograms();
        final double m2 = collider.getMassKilograms();

        final Vector v1 = victim.getVector();
        final Vector v2 = collider.getVector();

        final double x1 = v1.x();
        final double y1 = v1.y();
        final double z1 = v1.z();

        final double x2 = v2.x();
        final double y2 = v2.y();
        final double z2 = v2.z();

        final double dx = getFinalVelocity(x1, x2, m1, m2);
        final double dy = getFinalVelocity(y1, y2, m1, m2);
        final double dz = getFinalVelocity(z1, z2, m1, m2);

        return new Vector(dx, dy, dz).modifyAll(-1);
    }

    private double getFinalVelocity(double u1, double u2, double m1, double m2) {
        final double ratioOfMass = m1 / m2;
        final double v1 = 0;

        final double zero = (1 - ratioOfMass) * Math.pow(v1, 2) - 2;
        return u1 * 0.5; // TODO TODO TODO TODO THIS IS A BODGED SOLUTION
        // FIGURE OUT HOW TO SOLVE THE DAMN QUADRATIC AND IMPLEMENT THIS
    }

    @Nonnull
    @Override
    public Class<CollisionEvent> getEventClass() {
        return CollisionEvent.class;
    }
}
