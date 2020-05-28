package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.EditView;
import java.util.logging.Logger;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import com.records.hs.view.Field;
import javax.swing.JComboBox;
import com.records.hs.model.Type;

/**
 * An edit controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 28, 2020
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

    /**
     * Shows the specified error message that is associated with the specified component.
     *
     * @param component the component to be used in the operation
     * @param message the message to be used in the operation
     */
    private void showErrorMessage(JComponent component, String message) {
        Window window;
        String title = "HS Records";

        window = SwingUtilities.getWindowAncestor(component);

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

        component.requestFocus();
    } //showErrorMessage

    /**
     * Returns the ID input of this edit controller's edit view.
     *
     * @return the ID input of this edit controller's edit view
     */
    private String getIdInput() {
        JTextField idTextField;
        String id;

        idTextField = this.editView.getIdTextField();

        id = idTextField.getText();

        if (id.isBlank()) {
            String message = "Error: The specified ID is blank!";

            this.showErrorMessage(idTextField, message);

            return null;
        } //end if

        return id;
    } //getIdInput

    /**
     * Returns the field input of this edit controller's edit view.
     *
     * @return the field input of this edit controller's edit view
     */
    private Field getFieldInput() {
        JComboBox<Field> fieldComboBox;
        Field field;

        fieldComboBox = this.editView.getFieldComboBox();

        field = (Field) fieldComboBox.getSelectedItem();

        if (field == null) {
            String message = "Error: The specified field is blank!";

            this.showErrorMessage(fieldComboBox, message);

            return null;
        } //end if

        return field;
    } //getFieldInput

    /**
     * Returns the new ID input of this edit controller's edit view.
     *
     * @return the new ID input of this edit controller's edit view
     */
    private String getNewIdInput() {
        JTextField newIdTextField;
        String id;
        String message;
        String comma = ",";

        newIdTextField = this.editView.getNewIdTextField();

        id = newIdTextField.getText();

        if (id.isBlank()) {
            message = "Error: The specified ID is blank!";

            this.showErrorMessage(newIdTextField, message);

            return null;
        } else if (id.contains(comma)) {
            message = "Error: The specified ID contains a comma!";

            this.showErrorMessage(newIdTextField, message);

            return null;
        } //end if

        return id;
    } //getNewIdInput

    /**
     * Returns the new type input of this edit controller's edit view.
     *
     * @return the new type input of this edit controller's edit view
     */
    private Type getNewTypeInput() {
        JComboBox<Type> newTypeComboBox;
        Type type;

        newTypeComboBox = this.editView.getNewTypeComboBox();

        type = (Type) newTypeComboBox.getSelectedItem();

        if (type == null) {
            String message = "Error: The specified type is blank!";

            this.showErrorMessage(newTypeComboBox, message);

            return null;
        } //end if

        return type;
    } //getNewTypeInput

    /**
     * Returns the new category input of this edit controller's edit view.
     *
     * @return the new category input of this edit controller's edit view
     */
    private String getNewCategoryInput() {
        JComboBox<String> newCategoryComboBox;
        String category;

        newCategoryComboBox = this.editView.getNewCategoryComboBox();

        category = (String) newCategoryComboBox.getSelectedItem();

        if (category == null) {
            String message = "Error: The specified category is blank!";

            this.showErrorMessage(newCategoryComboBox, message);

            return null;
        } //end if

        return category;
    } //getNewCategoryInput

    /**
     * Returns the category input of this edit controller's edit view.
     *
     * @return the category input of this edit controller's edit view
     */
    private String getCategoryInput() {
        JComboBox<String> categoryComboBox;
        String category;

        categoryComboBox = this.editView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            String message = "Error: The specified category is blank!";

            this.showErrorMessage(categoryComboBox, message);

            return null;
        } //end if

        return category;
    } //getCategoryInput

    /**
     * Returns the new subcategory input of this edit controller's edit view.
     *
     * @return the new subcategory input of this edit controller's edit view
     */
    private String getNewSubcategoryInput() {
        JComboBox<String> newSubcategoryComboBox;
        String subcategory;

        newSubcategoryComboBox = this.editView.getNewSubcategoryComboBox();

        subcategory = (String) newSubcategoryComboBox.getSelectedItem();

        if (subcategory == null) {
            String message = "Error: The specified subcategory is blank!";

            this.showErrorMessage(newSubcategoryComboBox, message);

            return null;
        } //end if

        return subcategory;
    } //getNewSubcategoryInput

    /**
     * Returns the new tags input of this edit controller's edit view.
     *
     * @return the new tags input of this edit controller's edit view
     */
    private String getNewTagsInput() {
        JTextField newTagsTextField;
        String tags;

        newTagsTextField = this.editView.getNewTagsTextField();

        tags = newTagsTextField.getText();

        if (tags.isBlank()) {
            String message = "Error: The specified set of tags is blank!";

            this.showErrorMessage(newTagsTextField, message);

            return null;
        } //end if

        return tags;
    } //getNewTagsInput
}