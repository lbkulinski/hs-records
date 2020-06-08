package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.DeleteView;
import java.util.Objects;
import javax.swing.JComboBox;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Window;
import com.records.hs.view.Field;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.records.hs.model.Type;

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

    /**
     * Clears the fields of this delete controller's delete view.
     */
    private void clearFields() {
        JPanel panel;
        JButton deleteButton;
        Window window;
        JComboBox<Field> fieldComboBox;
        ActionListener[] deleteListeners;

        panel = this.deleteView.getPanel();

        deleteButton = this.deleteView.getDeleteButton();

        window = SwingUtilities.getWindowAncestor(panel);

        fieldComboBox = this.deleteView.getFieldComboBox();

        deleteListeners = deleteButton.getActionListeners();

        panel.removeAll();

        for (ActionListener listener : deleteListeners) {
            deleteButton.removeActionListener(listener);
        } //end for

        this.deleteView.addComponentsToPanel();

        window.revalidate();

        window.repaint();

        fieldComboBox.setSelectedIndex(-1);

        this.fillCategoryComboBox();

        this.fillSubcategoryComboBox();

        fieldComboBox.requestFocus();
    } //clearFields

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
     * Shows the specified error message.
     *
     * @param message the message to be used in the operation
     */
    private void showErrorMessage(String message) {
        JPanel panel;
        Window window;
        String title = "HS Records";

        panel = this.deleteView.getPanel();

        window = SwingUtilities.getWindowAncestor(panel);

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
    } //showErrorMessage

    /**
     * Shows the specified information message.
     *
     * @param message the message to be used in the operation
     */
    private void showInformationMessage(String message) {
        JPanel panel;
        Window window;
        String title = "HS Records";

        panel = this.deleteView.getPanel();

        window = SwingUtilities.getWindowAncestor(panel);

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    } //showInformationMessage

    /**
     * Returns the field input of this delete controller's delete view.
     *
     * @return the field input of this delete controller's delete view
     */
    private Field getFieldInput() {
        JComboBox<Field> fieldComboBox;
        Field field;

        fieldComboBox = this.deleteView.getFieldComboBox();

        field = (Field) fieldComboBox.getSelectedItem();

        return field;
    } //getFieldInput

    /**
     * Returns the ID input of this delete controller's delete view.
     *
     * @return the ID input of this delete controller's delete view
     */
    private String getIdInput() {
        JTextField idTextField;
        String id;

        idTextField = this.deleteView.getIdTextField();

        id = idTextField.getText();

        if (id.isBlank()) {
            String message = "Error: The specified ID is blank!";

            this.showErrorMessage(idTextField, message);

            return null;
        } //end if

        return id;
    } //getIdInput

    /**
     * Returns the type input of this delete controller's delete view.
     *
     * @return the type input of this delete controller's delete view
     */
    private Type getTypeInput() {
        JComboBox<Type> typeComboBox;
        Type type;

        typeComboBox = this.deleteView.getTypeComboBox();

        type = (Type) typeComboBox.getSelectedItem();

        if (type == null) {
            String message = "Error: The specified type is blank!";

            this.showErrorMessage(typeComboBox, message);

            return null;
        } //end if

        return type;
    } //getTypeInput

    /**
     * Returns the category input of this delete controller's delete view.
     *
     * @return the category input of this delete controller's delete view
     */
    private String getCategoryInput() {
        JComboBox<String> categoryComboBox;
        String category;

        categoryComboBox = this.deleteView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            String message = "Error: The specified category is blank!";

            this.showErrorMessage(categoryComboBox, message);

            return null;
        } //end if

        return category;
    } //getCategoryInput

    /**
     * Returns the subcategory input of this delete controller's delete view.
     *
     * @return the subcategory input of this delete controller's delete view
     */
    private String getSubcategoryInput() {
        JComboBox<String> subcategoryComboBox;
        String subcategory;

        subcategoryComboBox = this.deleteView.getSubcategoryComboBox();

        subcategory = (String) subcategoryComboBox.getSelectedItem();

        if (subcategory == null) {
            String message = "Error: The specified subcategory is blank!";

            this.showErrorMessage(subcategoryComboBox, message);

            return null;
        } //end if

        return subcategory;
    } //getSubcategoryInput

    /**
     * Returns the tag input of this delete controller's delete view.
     *
     * @return the tag input of this delete controller's delete view
     */
    private String getTagInput() {
        JTextField tagTextField;
        String tag;

        tagTextField = this.deleteView.getTagTextField();

        tag = tagTextField.getText();

        if (tag.isBlank()) {
            String message = "Error: The specified tag is blank!";

            this.showErrorMessage(tagTextField, message);

            return null;
        } //end if

        return tag;
    } //getTagInput
}