package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.EditView;
import java.util.logging.Logger;
import java.util.Objects;

/**
 * An edit controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 18, 2020
 */
public final class EditController {
    /**
     * The model of this edit controller.
     */
    private final Model model;

    /**
     * The edit view of this edit controller.
     */
    private final EditView editView;

    /**
     * The logger of this edit controller.
     */
    private final Logger logger;

    /**
     * Constructs a newly allocated {@code EditController} object.
     *
     * @param model the model to be used in construction
     * @param editView the edit view to be used in construction
     * @throws NullPointerException if the specified model or edit view is {@code null}
     */
    private EditController(Model model, EditView editView) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(editView, "the specified edit view is null");

        this.model = model;
        this.editView = editView;
        this.logger = Logger.getGlobal();
    } //EditController
}