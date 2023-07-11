package oasis.artemis.ui;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * <h2>TFrame</h2>
 * <p>A base class for Terra frames.</p>
 */
public class TFrame extends JFrame {
    /**
     * Creates a new frame.
     *
     * @throws HeadlessException I have no idea what this means
     */
    public TFrame() throws HeadlessException {
    }

    /**
     * Creates a new frame.
     *
     * @param gc Configuration of graphics I guess?
     */
    public TFrame(@Nonnull GraphicsConfiguration gc) {
        super(gc);
    }

    /**
     * Creates a new frame.
     *
     * @param title Title of this frame
     * @throws HeadlessException What the hell is this
     */
    public TFrame(@Nonnull String title) throws HeadlessException {
        super(title);
    }

    /**
     * Creates a new frame.
     *
     * @param title Title of this frame
     * @param gc    Configuration of graphics I think
     */
    public TFrame(@Nonnull String title, @Nonnull GraphicsConfiguration gc) {
        super(title, gc);
    }
}
