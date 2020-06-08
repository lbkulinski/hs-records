package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.DeleteView;
import java.util.Objects;
import javax.swing.JComboBox;
import java.util.Set;

/**
 * A delete controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 8, 2020
 */
public final class DeleteController {
    /**
     * The model of this delete controller.
     */
    private final Model model;

    /**
     * The delete view of this delete controller.
     */
    private final DeleteView deleteView;

    /**
     * Constructs a newly allocated {@code DeleteController} object with the specified model and delete view.
     *
     * @param model the model to be used in construction
     * @param deleteView the delete view to be used in construction
     * @throws NullPointerException if the specified model or delete view is {@code null}
     */
    private DeleteController(Model model, DeleteView deleteView) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(deleteView, "the specified delete view is null");

        this.model = model;
        this.deleteView = deleteView;
    } //DeleteController

    /**
     * Fills the category combo box of this delete controller's delete view with the categories of this delete
     * controller's model.
     */
    void fillCategoryComboBox() {
        JComboBox<String> categoryComboBox;
        Set<String> categories;

        categoryComboBox = this.deleteView.getCategoryComboBox();

        categories = this.model.getCategories();

        categoryComboBox.removeAllItems();

        if (categories.isEmpty()) {
            categoryComboBox.setEnabled(false);
        } else {
            categoryComboBox.setEnabled(true);

            categories.forEach(categoryComboBox::addItem);
        } //end if

        categoryComboBox.setSelectedIndex(-1);
    } //fillCategoryComboBox

    /**
     * Fills the subcategory combo box of this delete controller's delete view with the subcategories of this delete
     * controller's model that are mapped from the user selected category.
     */
    void fillSubcategoryComboBox() {
        JComboBox<String> subcategoryComboBox;
        JComboBox<String> categoryComboBox;
        String category;
        Set<String> subcategories;

        subcategoryComboBox = this.deleteView.getSubcategoryComboBox();

        categoryComboBox = this.deleteView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        subcategoryComboBox.removeAllItems();

        if (category == null) {
            subcategoryComboBox.setEnabled(false);

            subcategoryComboBox.setSelectedIndex(-1);

            return;
        } //end if

        subcategories = this.model.getSubcategories(category);

        if (subcategories.isEmpty()) {
            subcategoryComboBox.setEnabled(false);
        } else {
            subcategoryComboBox.setEnabled(true);

            subcategories.forEach(subcategoryComboBox::addItem);
        } //end if

        subcategoryComboBox.setSelectedIndex(-1);
    } //fillSubcategoryComboBox
}