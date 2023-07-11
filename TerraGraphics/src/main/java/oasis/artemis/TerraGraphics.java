package oasis.artemis;

import oasis.artemis.ui.DummyPanel;
import oasis.artemis.ui.TFrame;
import oasis.artemis.ui.TPanel;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <h2>TerraGraphics</h2>
 * <p>The main class of Terra's graphics engine.</p>
 */
public class TerraGraphics {
    public void openViewport() {
        final Container pane = frame.getContentPane();

        pane.add(panel, BorderLayout.CENTER);

        frame.setSize(1920, 1080);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(@Nonnull WindowEvent e) {
                e.getWindow().dispose();
                closeViewport();
            }
        });
    }

    public void closeViewport() {
        final Container pane = frame.getContentPane();

        pane.remove(panel);
        frame.setVisible(false);
    }

    @Nonnull
    public TFrame getFrame() {
        return frame;
    }

    @Nonnull
    public TPanel getPanel() {
        return panel;
    }

    private final TFrame frame = new TFrame("Terra");
    private final TPanel panel = new DummyPanel();
}