package oasis.artemis.graphics.component;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.render.Triangle;
import oasis.artemis.render.Vertex;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.geom.Path2D;

public class DummyPanel extends TPanel {
    public DummyPanel(@Nonnull LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public DummyPanel(@Nonnull LayoutManager layout) {
        super(layout);
    }

    public DummyPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public DummyPanel() {
    }

    @Override
    protected void paintComponent(@Nonnull Graphics g) {
        TList<Triangle> triangles = new TArray<>();

        triangles.add(new Triangle(
                new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(-100, 100, -100),
                Color.WHITE
        ));

        triangles.add(new Triangle(
                new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, -100),
                Color.RED
        ));

        triangles.add(new Triangle(
                new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, 100),
                Color.GREEN
        ));

        triangles.add(new Triangle(
                new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(-100, -100, 100),
                Color.BLUE
        ));

        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
//        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.translate(
                getWidth() / 2,
                getHeight() / 2
        );

        triangles.forEach(t -> {
            g2.setColor(t.color());
            final Path2D path = new Path2D.Double();

            path.moveTo(t.v1().x(), t.v1().y());
            path.moveTo(t.v2().x(), t.v2().y());
            path.moveTo(t.v3().x(), t.v3().y());

            path.closePath();

            g2.draw(path);
        });
    }
}
