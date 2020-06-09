package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.FindView;
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
import java.util.Optional;
import com.records.hs.model.Entry;
import com.records.hs.view.ViewUtilities;

/**
 * A find controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 9, 2020
 */
public final class FindController {
    /**
     * The model of this find controller.
     */
    private final Model model;

    /**
     * The find view of this find controller.
     */
    private final FindView findView;

    /**
     * Constructs a newly allocated {@code FindController} object with the specified model and find view.
     *
     * @param model the model to be used in construction
     * @param findView the find view to be used in construction
     * @throws NullPointerException if the specified model or find view is {@code null}
     */
    private FindController(Model model, FindView findView) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(findView, "the specified find view is null");

        this.model = model;
        this.findView = findView;
    } //FindController

    /**
     * Fills the category combo box of this find controller's find view with the categories of this find controller's
     * model.
     */
    void fillCategoryComboBox() {
        JComboBox<String> categoryComboBox;
        Set<String> categories;

        categoryComboBox = this.findView.getCategoryComboBox();

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
     * Fills the subcategory combo box of this find controller's find view with the subcategories of this find
     * controller's model that are mapped from the user selected category.
     */
    void fillSubcategoryComboBox() {
        JComboBox<String> subcategoryComboBox;
        JComboBox<String> categoryComboBox;
        String category;
        Set<String> subcategories;

        subcategoryComboBox = this.findView.getSubcategoryComboBox();

        categoryComboBox = this.findView.getCategoryComboBox();

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
     * Clears the fields of this find controller's find view.
     */
    private void clearFields() {
        JPanel panel;
        JButton findButton;
        Window window;
        JComboBox<Field> fieldComboBox;
        ActionListener[] findListeners;

        panel = this.findView.getPanel();

        findButton = this.findView.getFindButton();

        window = SwingUtilities.getWindowAncestor(panel);

        fieldComboBox = this.findView.getFieldComboBox();

        findListeners = findButton.getActionListeners();

        panel.removeAll();

        for (ActionListener listener : findListeners) {
            findButton.removeActionListener(listener);
        } //end for

        this.findView.addComponentsToPanel();

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

        panel = this.findView.getPanel();

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

        panel = this.findView.getPanel();

        window = SwingUtilities.getWindowAncestor(panel);

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    } //showInformationMessage

    /**
     * Returns the field input of this find controller's find view.
     *
     * @return the field input of this find controller's find view
     */
    private Field getFieldInput() {
        JComboBox<Field> fieldComboBox;
        Field field;

        fieldComboBox = this.findView.getFieldComboBox();

        field = (Field) fieldComboBox.getSelectedItem();

        return field;
    } //getFieldInput

    /**
     * Returns the ID input of this find controller's find view.
     *
     * @return the ID input of this find controller's find view
     */
    private String getIdInput() {
        JTextField idTextField;
        String id;

        idTextField = this.findView.getIdTextField();

        id = idTextField.getText();

        if (id.isBlank()) {
            String message = "Error: The specified ID is blank!";

            this.showErrorMessage(idTextField, message);

            return null;
        } //end if

        return id;
    } //getIdInput

    /**
     * Returns the type input of this find controller's find view.
     *
     * @return the type input of this find controller's find view
     */
    private Type getTypeInput() {
        JComboBox<Type> typeComboBox;
        Type type;

        typeComboBox = this.findView.getTypeComboBox();

        type = (Type) typeComboBox.getSelectedItem();

        if (type == null) {
            String message = "Error: The specified type is blank!";

            this.showErrorMessage(typeComboBox, message);

            return null;
        } //end if

        return type;
    } //getTypeInput

    /**
     * Returns the category input of this find controller's find view.
     *
     * @return the category input of this find controller's find view
     */
    private String getCategoryInput() {
        JComboBox<String> categoryComboBox;
        String category;

        categoryComboBox = this.findView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            String message = "Error: The specified category is blank!";

            this.showErrorMessage(categoryComboBox, message);

            return null;
        } //end if

        return category;
    } //getCategoryInput

    /**
     * Returns the subcategory input of this find controller's find view.
     *
     * @return the subcategory input of this find controller's find view
     */
    private String getSubcategoryInput() {
        JComboBox<String> subcategoryComboBox;
        String subcategory;

        subcategoryComboBox = this.findView.getSubcategoryComboBox();

        subcategory = (String) subcategoryComboBox.getSelectedItem();

        if (subcategory == null) {
            String message = "Error: The specified subcategory is blank!";

            this.showErrorMessage(subcategoryComboBox, message);

            return null;
        } //end if

        return subcategory;
    } //getSubcategoryInput

    /**
     * Returns the tag input of this find controller's find view.
     *
     * @return the tag input of this find controller's find view
     */
    private String getTagInput() {
        JTextField tagTextField;
        String tag;

        tagTextField = this.findView.getTagTextField();

        tag = tagTextField.getText();

        if (tag.isBlank()) {
            String message = "Error: The specified tag is blank!";

            this.showErrorMessage(tagTextField, message);

            return null;
        } //end if

        return tag;
    } //getTagInput
}