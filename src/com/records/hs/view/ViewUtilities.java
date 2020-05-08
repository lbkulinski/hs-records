package com.records.hs.view;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.Objects;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * A set of utility methods used by the views in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 7, 2020
 */
public final class ViewUtilities {
    /**
     * Constructs a newly allocated {@code ViewUtilities} object.
     *
     * @throws AssertionError if an object of type {@code ViewUtilities} attempts to be instantiated
     */
    private ViewUtilities() {
        throw new AssertionError("an object of type ViewUtilities cannot be instantiated");
    } //ViewUtilities

    /**
     * Formats the specified component with a titled border with the specified title.
     *
     * @param component the component to be used in the operation
     * @param title the title to be used in the operation
     * @throws NullPointerException if the specified component or title is {@code null}
     */
    public static void formatComponent(JComponent component, String title) {
        Border lineBorder;
        TitledBorder titledBorder;

        Objects.requireNonNull(component, "the specified component is null");

        Objects.requireNonNull(title, "the specified title is null");

        lineBorder = BorderFactory.createLineBorder(Color.BLACK);

        titledBorder = BorderFactory.createTitledBorder(lineBorder, title);

        titledBorder.setTitlePosition(TitledBorder.BELOW_TOP);

        component.setBorder(titledBorder);
    } //formatComponent

    /**
     * Adds the specified component to the specified panel using the specified constraints, row, and column.
     *
     * @param panel the panel to be used in the operation
     * @param component the component to be used in the operation
     * @param constraints the constraints to be used in the operation
     * @param row the row to be used in the operation
     * @param column the column to be used in the operation
     * @throws NullPointerException if the specified panel, component, or constraints is {@code null}
     * @throws IllegalArgumentException if the specified row or column is negative
     */
    public static void addComponentToPanel(JPanel panel, JComponent component, GridBagConstraints constraints, int row,
                                           int column) {
        int pixelCount = 5;

        Objects.requireNonNull(panel, "the specified panel is null");

        Objects.requireNonNull(component, "the specified component is null");

        Objects.requireNonNull(constraints, "the specified constraints are null");

        if (row < 0) {
            throw new IllegalArgumentException("the specified row is negative");
        } //end if

        if (column < 0) {
            throw new IllegalArgumentException("the specified column is negative");
        } //end if

        constraints.gridx = row;

        constraints.gridy = column;

        constraints.insets = new Insets(pixelCount, pixelCount, pixelCount, pixelCount);

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.weightx = 1.0;

        constraints.weighty = 1.0;

        panel.add(component, constraints);
    } //addComponentToPanel
}