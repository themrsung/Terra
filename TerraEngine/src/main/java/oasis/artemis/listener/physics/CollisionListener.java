package oasis.artemis.listener.physics;

import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.listener.Listener;
import oasis.artemis.object.TObject;
import oasis.artemis.physics.Physics;

import javax.annotation.Nonnull;

public final class CollisionListener implements Listener<CollisionEvent> {
    @Override
    public void handle(@Nonnull CollisionEvent event) {
        if (event.isCancelled()) return;

        final TObject o1 = event.getObject1();
        final TObject o2 = event.getObject2();

        final double e1 = Physics.kineticEnergy(o1);
        final double e2 = Physics.kineticEnergy(o2);
    }

    @Nonnull
    @Override
    public Class<CollisionEvent> getEventClass() {
        return CollisionEvent.class;
    }
}
