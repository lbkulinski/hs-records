package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.AddView;
import java.util.Objects;
import java.time.LocalDate;
import javax.swing.JTextField;
import java.util.Set;
import javax.swing.JComboBox;

/**
 * An add controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 13, 2020
 */
public final class AddController {
    /**
     * The model of this add controller.
     */
    private final Model model;

    /**
     * The add view of this add controller.
     */
    private final AddView addView;

    /**
     * Constructs a newly allocated {@code AddController} object.
     *
     * @param model the model to be used in construction
     * @param addView the add view to be used in construction
     */
    private AddController(Model model, AddView addView) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(addView, "the specified add view is null");

        this.model = model;
        this.addView = addView;
    } //AddController

    /**
     * Fills the ID text field of this controller's add view with the suggested next ID.
     */
    private void fillIdTextField() {
        String latestId;
        LocalDate currentDate;
        int currentYear;
        String nextId;
        String[] idParts;
        int expectedLength = 2;
        String yearPart;
        int year;
        String format;
        JTextField idTextField;

        latestId = this.model.getLatestId();

        currentDate = LocalDate.now();

        currentYear = currentDate.getYear();

        if (latestId == null) {
            format = "0001_%04d";

            nextId = String.format(format, currentYear);

            idTextField = this.addView.getIdTextField();

            idTextField.setText(nextId);

            return;
        } //end if

        idParts = latestId.split("_");

        if (idParts.length != expectedLength) {
            return;
        } //end if

        yearPart = idParts[1];

        if (!yearPart.matches("(\\d)+")) {
            return;
        } //end if

        try {
            year = Integer.parseInt(yearPart);
        } catch (NumberFormatException e) {
            return;
        } //end try catch

        if (year > currentYear) {
            return;
        } else if (year == currentYear) {
            String numberPart = idParts[0];
            int number;
            int nextNumber;

            if (!numberPart.matches("(\\d)+")) {
                return;
            } //end if

            try {
                number = Integer.parseInt(numberPart);
            } catch (NumberFormatException e) {
                return;
            } //end try catch

            nextNumber = number + 1;

            format = "%04d_%04d";

            nextId = String.format(format, nextNumber, year);
        } else {
            format = "0001_%04d";

            nextId = String.format(format, currentYear);
        } //end if

        idTextField = this.addView.getIdTextField();

        idTextField.setText(nextId);
    } //fillIdTextField

    /**
     * Fills the category combo box of this controller's add view with the categories of this controller's model.
     */
    private void fillCategoryComboBox() {
        Set<String> categories;
        JComboBox<String> categoryComboBox;
        String newItem = "Add new category...";

        categories = this.model.getCategories();

        categoryComboBox = this.addView.getCategoryComboBox();

        categories.forEach(categoryComboBox::addItem);

        categoryComboBox.addItem(newItem);

        categoryComboBox.setSelectedIndex(-1);
    } //fillCategoryComboBox

    /**
     * Fills the subcategory combo box of this controller's add view with the subcategories of this controller's model
     * that are mapped from the selected category of this controller's add view.
     */
    private void fillSubcategoryComboBox() {
        JComboBox<String> categoryComboBox;
        String category;
        Set<String> subcategories;
        JComboBox<String> subcategoryComboBox;
        String newItem = "Add new subcategory...";

        categoryComboBox = this.addView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            return;
        } //end if

        subcategories = this.model.getSubcategories(category);

        subcategoryComboBox = this.addView.getSubcategoryComboBox();

        subcategories.forEach(subcategoryComboBox::addItem);

        subcategoryComboBox.addItem(newItem);

        subcategoryComboBox.setSelectedIndex(-1);
    } //fillSubcategoryComboBox
}