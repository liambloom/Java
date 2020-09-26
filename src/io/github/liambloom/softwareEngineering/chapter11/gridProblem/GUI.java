package io.github.liambloom.softwareEngineering.chapter11.gridProblem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    public static final int RECT_DEFAULT_SIZE = 10;
    public static final int INSTRUCTION_HEIGHT = 20;

    public final Dimension dimensions = new Dimension(RECT_DEFAULT_SIZE, RECT_DEFAULT_SIZE);
    public final Grid grid;

    private final JFrame frame = new JFrame();
    private final JPanel gridPanel = new JPanel();
    private final JLabel instructions = new JLabel("Click a black object to remove it");
    private final JLabel[] xLabels;
    private final JLabel[] yLabels;
    //private final Rectangle[][] rects;

    public void main() {
        //new GUI(Grid.checkerBoard(9));
    }

    public GUI() {
        this(new Grid());
    }
    public GUI(Grid grid) {
        this.grid = grid;
        xLabels = new JLabel[grid.width];
        yLabels = new JLabel[grid.height];

        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(new Dimension((grid.width + 1) * RECT_DEFAULT_SIZE, (grid.height + 1) * RECT_DEFAULT_SIZE + INSTRUCTION_HEIGHT));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                // Component c = (Component) evt.getSource();
                resize();
            }
        });

        for (boolean[] row : grid.grid()) {
            for (boolean cell : row) {
                gridPanel.add(new Square(0, 0, cell ? Color.BLACK : Color.WHITE));
            }
        }

        frame.add(instructions);
        frame.add(gridPanel);

        frame.setVisible(true);

        resize();
    }

    protected void resize() {
        instructions.setBounds(0, frame.getHeight() - INSTRUCTION_HEIGHT, frame.getWidth(), INSTRUCTION_HEIGHT);
        instructions.setHorizontalAlignment(SwingConstants.CENTER);

        gridPanel.setLayout(new GridLayout(grid.width + 1, grid.height + 1));
        final int rectSize = Math.min(frame.getWidth() / (grid.width + 1), (frame.getHeight() - INSTRUCTION_HEIGHT) / (grid.height + 1));
        final int width = rectSize * (grid.width + 1);
        gridPanel.setBounds((frame.getWidth() - width) / 2, 0, width, rectSize * (grid.height + 1));
    }
    /*public final 

    public void draw() {

    }*/
}