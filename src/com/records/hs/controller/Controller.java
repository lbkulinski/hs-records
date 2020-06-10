package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.View;
import javax.swing.JFrame;
import com.records.hs.view.AddView;
import com.records.hs.view.EditView;
import com.records.hs.view.DeleteView;
import com.records.hs.view.FindView;
import com.records.hs.view.MenuView;
import java.util.Objects;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.records.hs.util.Utilities;

/**
 * A controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 9, 2020
 */
public final class Controller {
    /**
     * Constructs a newly allocated {@code Controller} object.
     *
     * @throws AssertionError if an object of type {@code Controller} attempts to be instantiated
     */
    private Controller() {
        throw new AssertionError("an object of type Utilities cannot be instantiated");
    } //Controller

    /**
     * Sets up a controller.
     */
    public static void setUpController(Model model, View view) {
        JFrame frame;
        AddView addView;
        EditView editView;
        DeleteView deleteView;
        FindView findView;
        MenuView menuView;
        AddController addController;
        EditController editController;
        DeleteController deleteController;
        FindController findController;

        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(view, "the specified view is null");

        frame = view.getFrame();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Utilities.writeModelToFile(model);
            } //windowClosing
        });

        addView = view.getAddView();

        editView = view.getEditView();

        deleteView = view.getDeleteView();

        findView = view.getFindView();

        menuView = view.getMenuView();

        addController = AddController.newAddController(model, addView);

        editController = EditController.newEditController(model, editView);

        deleteController = DeleteController.newDeleteController(model, deleteView);

        findController = FindController.newFindController(model, findView);

        MenuController.newMenuController(model, menuView, addController, editController, deleteController,
                                         findController);
    } //setUpController
}