package oasis.artemis.graphics;

import oasis.artemis.TerraEngine;
import oasis.artemis.graphics.component.CameraPanel;
import oasis.artemis.graphics.component.TFrame;
import oasis.artemis.graphics.component.TPanel;
import oasis.artemis.util.Tickable;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <h2>TerraGraphics</h2>
 * <p>Handles the rendering of graphics on-screen.</p>
 */
public class TerraGraphics implements Tickable {
    /**
     * Called on engine started.
     */
    public void onEngineStarted() {
        frame.add(panel);
        frame.setVisible(true);

        frame.setSize(1920, 1080);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                TerraEngine.stop();
            }
        });
    }

    /**
     * Called on engine stopped.
     */
    public void onEngineStopped() {
        frame.setVisible(false);
        frame.remove(panel);
    }

    @Override
    public void tick(@Nonnull Duration delta) {
        frame.repaint();
    }

    /**
     * Ensures that graphics will be rendered after every tickable has been executed.
     *
     * @return {@link oasis.artemis.util.Tickable.Priority#POST_MONITOR}
     */
    @Nonnull
    @Override
    public Priority getPriority() {
        return Priority.POST_MONITOR;
    }

    /**
     * Gets the main frame of TerraGraphics.
     *
     * @return Frame
     */
    @Nonnull
    public TFrame getFrame() {
        return frame;
    }

    /**
     * Gets the main panel of TerraGraphics.
     *
     * @return Panel
     */
    @Nonnull
    public TPanel getPanel() {
        return panel;
    }

    private final TFrame frame = new TFrame("Terra");
    private final TPanel panel = new CameraPanel();
}
