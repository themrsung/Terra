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

        o1.setVector(o1.getVector().plus(getDeltaDueToCollision(o1, o2)));
        o2.setVector(o2.getVector().plus(getDeltaDueToCollision(o2, o1)));
    }

    @Nonnull
    private Vector getDeltaDueToCollision(@Nonnull TObject object, @Nonnull TObject opposingObject) {

        return new Vector();
    }

    @Nonnull
    @Override
    public Class<CollisionEvent> getEventClass() {
        return CollisionEvent.class;
    }
}
