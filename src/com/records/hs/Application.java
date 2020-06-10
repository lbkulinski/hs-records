package com.records.hs;

import com.records.hs.model.Model;
import com.records.hs.view.View;
import javax.swing.JFrame;
import com.records.hs.util.Utilities;
import java.util.Objects;
import com.records.hs.controller.Controller;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

/**
 * An application instance of the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 9, 2020
 */
public class Application {
    /**
     * Shows the frame of the application.
     *
     * @param frame the frame to be used in the operation
     */
    private static void showFrame(JFrame frame) {
        String title = "HS Records";
        Dimension size;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle(title);

        frame.pack();

        size = frame.getSize();

        frame.setMinimumSize(size);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    } //showFrame

    /**
     * Runs the HS Records application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model model;
        View view;
        JFrame frame;

        model = Utilities.readModelFromFile();

        model = Objects.requireNonNullElseGet(model, Model::new);

        view = View.newView();

        Controller.setUpController(model, view);

        frame = view.getFrame();

        SwingUtilities.invokeLater(() -> showFrame(frame));
    } //main
}