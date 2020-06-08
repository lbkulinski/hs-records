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
import java.util.Optional;
import com.records.hs.model.Entry;
import com.records.hs.view.ViewUtilities;

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

    /**
     * Deletes a record with the specified ID from the model of this delete controller using the input of this delete
     * controller's delete view.
     */
    private void deleteWithId() {
        String id;
        Optional<Entry> optional;
        JTextField idTextField;
        String message;
        boolean deleted;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        idTextField = this.deleteView.getIdTextField();

        if (optional.isEmpty()) {
            message = "Error: A record with the specified ID does not exist!";

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        deleted = this.model.deleteEntry(id);

        if (deleted) {
            message = "The record was successfully deleted!";

            this.showInformationMessage(message);
        } else {
            message = "Error: The record could not be deleted! Please contact support!";

            this.showErrorMessage(message);
        } //end if

        this.clearFields();
    } //deleteWithId

    /**
     * Deletes records with the specified type from the model of this delete controller using the input of this delete
     * controller's delete view.
     */
    private void deleteWithType() {
        Type type;
        boolean deleted;
        String message;

        type = this.getTypeInput();

        if (type == null) {
            return;
        } //end if

        deleted = this.model.deleteEntriesWithType(type);

        if (deleted) {
            message = "The records were successfully deleted!";

            this.showInformationMessage(message);
        } else {
            message = "Error: No records with the specified type exist!";

            this.showErrorMessage(message);
        } //end if

        this.clearFields();
    } //deleteWithType

    /**
     * Deletes records with the specified category from the model of this delete controller using the input of this
     * delete controller's delete view.
     */
    private void deleteWithCategory() {
        String category;
        boolean deleted;
        String message;

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        deleted = this.model.deleteEntriesWithCategory(category);

        if (deleted) {
            message = "The records were successfully deleted!";

            this.showInformationMessage(message);
        } else {
            message = "Error: No records with the specified category exist!";

            this.showErrorMessage(message);
        } //end if

        this.clearFields();
    } //deleteWithCategory

    /**
     * Deletes records with the specified subcategory from the model of this delete controller using the input of this
     * delete controller's delete view.
     */
    private void deleteWithSubcategory() {
        String category;
        String subcategory;
        boolean deleted;
        String message;

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput();

        if (subcategory == null) {
            return;
        } //end if

        deleted = this.model.deleteEntriesWithSubcategory(category, subcategory);

        if (deleted) {
            message = "The records were successfully deleted!";

            this.showInformationMessage(message);
        } else {
            message = "Error: No records with the specified subcategory exist!";

            this.showErrorMessage(message);
        } //end if

        this.clearFields();
    } //deleteWithSubcategory

    /**
     * Deletes records with the specified tag from the model of this delete controller using the input of this delete
     * controller's delete view.
     */
    private void deleteWithTag() {
        String tag;
        boolean deleted;
        String message;

        tag = this.getTagInput();

        if (tag == null) {
            return;
        } //end if

        deleted = this.model.deleteEntriesWithCategory(tag);

        if (deleted) {
            message = "The records were successfully deleted!";

            this.showInformationMessage(message);
        } else {
            message = "Error: No records with the specified tag exist!";

            this.showErrorMessage(message);
        } //end if

        this.clearFields();
    } //deleteWithTag

    /**
     * Displays the delete with ID components of this delete controller's delete view.
     */
    private void displayDeleteWithIdComponents() {
        JPanel panel;
        JButton deleteButton;
        JTextField idTextField;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] deleteListeners;
        int idRow = 2;
        int column = 0;
        int deleteRow = 3;
        int clearRow = 4;

        panel = this.deleteView.getPanel();

        deleteButton = this.deleteView.getDeleteButton();

        idTextField = this.deleteView.getIdTextField();

        clearButton = this.deleteView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        deleteListeners = deleteButton.getActionListeners();

        for (ActionListener listener : deleteListeners) {
            deleteButton.removeActionListener(listener);
        } //end for

        idTextField.setText(null);

        ViewUtilities.addComponentToPanel(panel, idTextField, idRow, column);

        ViewUtilities.addComponentToPanel(panel, deleteButton, deleteRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        deleteButton.addActionListener(actionEvent -> this.deleteWithId());
    } //displayDeleteWithIdComponents

    /**
     * Displays the delete with type components of this delete controller's delete view.
     */
    private void displayDeleteWithTypeComponents() {
        JPanel panel;
        JButton deleteButton;
        JComboBox<Type> typeComboBox;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] deleteListeners;
        int typeRow = 2;
        int column = 0;
        int deleteRow = 3;
        int clearRow = 4;

        panel = this.deleteView.getPanel();

        deleteButton = this.deleteView.getDeleteButton();

        typeComboBox = this.deleteView.getTypeComboBox();

        clearButton = this.deleteView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        deleteListeners = deleteButton.getActionListeners();

        for (ActionListener listener : deleteListeners) {
            deleteButton.removeActionListener(listener);
        } //end for

        typeComboBox.setSelectedIndex(-1);

        ViewUtilities.addComponentToPanel(panel, typeComboBox, typeRow, column);

        ViewUtilities.addComponentToPanel(panel, deleteButton, deleteRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        deleteButton.addActionListener(actionEvent -> this.deleteWithType());
    } //displayDeleteWithTypeComponents

    /**
     * Displays the delete with category components of this delete controller's delete view.
     */
    private void displayDeleteWithCategoryComponents() {
        JPanel panel;
        JButton deleteButton;
        JComboBox<String> categoryComboBox;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] deleteListeners;
        int categoryRow = 2;
        int column = 0;
        int deleteRow = 3;
        int clearRow = 4;

        panel = this.deleteView.getPanel();

        deleteButton = this.deleteView.getDeleteButton();

        categoryComboBox = this.deleteView.getCategoryComboBox();

        clearButton = this.deleteView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        deleteListeners = deleteButton.getActionListeners();

        for (ActionListener listener : deleteListeners) {
            deleteButton.removeActionListener(listener);
        } //end for

        categoryComboBox.setSelectedIndex(-1);

        ViewUtilities.addComponentToPanel(panel, categoryComboBox, categoryRow, column);

        ViewUtilities.addComponentToPanel(panel, deleteButton, deleteRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        deleteButton.addActionListener(actionEvent -> this.deleteWithCategory());
    } //displayDeleteWithCategoryComponents

    /**
     * Displays the delete with subcategory components of this delete controller's delete view.
     */
    private void displayDeleteWithSubcategoryComponents() {
        JPanel panel;
        JButton deleteButton;
        JComboBox<String> categoryComboBox;
        JComboBox<String> subcategoryComboBox;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] deleteListeners;
        int categoryRow = 2;
        int column = 0;
        int subcategoryRow = 3;
        int deleteRow = 4;
        int clearRow = 5;

        panel = this.deleteView.getPanel();

        deleteButton = this.deleteView.getDeleteButton();

        categoryComboBox = this.deleteView.getCategoryComboBox();

        subcategoryComboBox = this.deleteView.getSubcategoryComboBox();

        clearButton = this.deleteView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        deleteListeners = deleteButton.getActionListeners();

        for (ActionListener listener : deleteListeners) {
            deleteButton.removeActionListener(listener);
        } //end for

        categoryComboBox.setSelectedIndex(-1);

        subcategoryComboBox.setSelectedIndex(-1);

        ViewUtilities.addComponentToPanel(panel, categoryComboBox, categoryRow, column);

        ViewUtilities.addComponentToPanel(panel, subcategoryComboBox, subcategoryRow, column);

        ViewUtilities.addComponentToPanel(panel, deleteButton, deleteRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        deleteButton.addActionListener(actionEvent -> this.deleteWithSubcategory());
    } //displayDeleteWithSubcategoryComponents

    /**
     * Displays the delete with tag components of this delete controller's delete view.
     */
    private void displayDeleteWithTagComponents() {
        JPanel panel;
        JButton deleteButton;
        JTextField tagTextField;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] deleteListeners;
        int tagRow = 2;
        int column = 0;
        int deleteRow = 3;
        int clearRow = 4;

        panel = this.deleteView.getPanel();

        deleteButton = this.deleteView.getDeleteButton();

        tagTextField = this.deleteView.getTagTextField();

        clearButton = this.deleteView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        deleteListeners = deleteButton.getActionListeners();

        for (ActionListener listener : deleteListeners) {
            deleteButton.removeActionListener(listener);
        } //end for

        tagTextField.setText(null);

        ViewUtilities.addComponentToPanel(panel, tagTextField, tagRow, column);

        ViewUtilities.addComponentToPanel(panel, deleteButton, deleteRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        deleteButton.addActionListener(actionEvent -> this.deleteWithTag());
    } //displayDeleteWithTagComponents

    /**
     * Displays the appropriate delete components of this delete controller's delete view.
     */
    private void displayDeleteComponents() {
        Field field;

        field = this.getFieldInput();

        if (field == null) {
            return;
        } //end if

        switch (field) {
            case ID -> this.displayDeleteWithIdComponents();
            case TYPE -> this.displayDeleteWithTypeComponents();
            case CATEGORY -> this.displayDeleteWithCategoryComponents();
            case SUBCATEGORY -> this.displayDeleteWithSubcategoryComponents();
            case TAGS -> this.displayDeleteWithTagComponents();
        } //end switch
    } //displayDeleteComponents

    /**
     * Returns a new {@code DeleteController} object with the specified model and delete view.
     *
     * @param model the model to be used in construction
     * @param deleteView the delete view to be used in construction
     * @return a new {@code DeleteController} object with the specified model and delete view
     * @throws NullPointerException if the specified model or delete view is {@code null}
     */
    public static DeleteController newDeleteController(Model model, DeleteView deleteView) {
        DeleteController deleteController;
        JComboBox<Field> fieldComboBox;
        JComboBox<String> categoryComboBox;
        JButton clearButton;

        deleteController = new DeleteController(model, deleteView);

        fieldComboBox = deleteController.deleteView.getFieldComboBox();

        categoryComboBox = deleteController.deleteView.getCategoryComboBox();

        clearButton = deleteController.deleteView.getClearButton();

        fieldComboBox.addActionListener(actionEvent -> deleteController.displayDeleteComponents());

        categoryComboBox.addActionListener(actionEvent -> deleteController.fillSubcategoryComboBox());

        clearButton.addActionListener(actionEvent -> deleteController.clearFields());

        deleteController.clearFields();

        return deleteController;
    } //newDeleteController
}