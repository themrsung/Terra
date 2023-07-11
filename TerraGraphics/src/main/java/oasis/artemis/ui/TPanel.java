package oasis.artemis.ui;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * <h2>TPanel</h2>
 * <p>The base class for Terra panels.</p>
 */
public class TPanel extends JPanel {
    public TPanel(@Nonnull LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public TPanel(@Nonnull LayoutManager layout) {
        super(layout);
    }

    public TPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public TPanel() {
    }
}
