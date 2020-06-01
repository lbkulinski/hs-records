package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.EditView;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import com.records.hs.view.Field;
import javax.swing.JComboBox;
import com.records.hs.model.Type;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Optional;
import com.records.hs.model.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import com.records.hs.view.ViewUtilities;

/**
 * An edit controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 1, 2020
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
    } //EditController

    /**
     * Clears the fields of this edit controller's edit view.
     */
    private void clearFields() {
        JPanel panel;
        JButton editButton;
        Window window;
        ActionListener[] editListeners;

        panel = this.editView.getPanel();

        editButton = this.editView.getEditButton();

        window = SwingUtilities.getWindowAncestor(panel);

        editListeners = editButton.getActionListeners();

        panel.removeAll();

        for (ActionListener listener : editListeners) {
            editButton.removeActionListener(listener);
        } //end for

        this.editView.addComponentsToPanel();

        window.revalidate();

        window.repaint();
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

        panel = this.editView.getPanel();

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

        panel = this.editView.getPanel();

        window = SwingUtilities.getWindowAncestor(panel);

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    } //showInformationMessage

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
    private Set<String> getNewTagsInput() {
        JTextField newTagsTextField;
        String tagsString;
        String[] tagArray;
        Set<String> tags;

        newTagsTextField = this.editView.getNewTagsTextField();

        tagsString = newTagsTextField.getText();

        if (tagsString.isBlank()) {
            String message = "Error: The specified set of tags is blank!";

            this.showErrorMessage(newTagsTextField, message);

            return null;
        } //end if

        tagArray = tagsString.split(",");

        tags = Arrays.stream(tagArray)
                     .map(String::trim)
                     .collect(Collectors.toUnmodifiableSet());

        return tags;
    } //getNewTagsInput

    /**
     * Edits the ID of a record of the model of this edit controller using the input of this edit controller's edit
     * view.
     */
    private void editId() {
        String id;
        String newId;
        Optional<Entry> optional;
        JTextField idTextField;
        String message;
        Entry entry;
        JTextField newIdTextField;
        Type type;
        String category;
        String subcategory;
        Set<String> tags;
        Entry newEntry;
        boolean edited;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        newId = this.getNewIdInput();

        if (newId == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        idTextField = this.editView.getIdTextField();

        if (optional.isEmpty()) {
            message = "Error: A record with the specified ID does not exist!";

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        entry = optional.get();

        optional = this.model.findEntryWithId(newId);

        newIdTextField = this.editView.getNewIdTextField();

        if (optional.isPresent()) {
            message = "Error: A record with the specified new ID already exists!";

            this.showErrorMessage(newIdTextField, message);

            return;
        } //end if

        type = entry.getType();

        category = entry.getCategory();

        subcategory = entry.getSubcategory();

        tags = entry.getTags();

        newEntry = new Entry(newId, type, category, subcategory, tags);

        edited = this.model.editEntry(id, newEntry);

        if (edited) {
            message = "The record was successfully edited!";

            this.showInformationMessage(message);
        } else {
            message = "Error: The record could not be edited! Please contact support!";

            this.showErrorMessage(message);
        } //end if
    } //editId

    /**
     * Edits the type of a record of the model of this edit controller using the input of this edit controller's edit
     * view.
     */
    private void editType() {
        String id;
        Type newType;
        Optional<Entry> optional;
        JTextField idTextField;
        String message;
        Entry entry;
        String category;
        String subcategory;
        Set<String> tags;
        Entry newEntry;
        boolean edited;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        newType = this.getNewTypeInput();

        if (newType == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        idTextField = this.editView.getIdTextField();

        if (optional.isEmpty()) {
            message = "Error: A record with the specified ID does not exist!";

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        entry = optional.get();

        category = entry.getCategory();

        subcategory = entry.getSubcategory();

        tags = entry.getTags();

        newEntry = new Entry(id, newType, category, subcategory, tags);

        edited = this.model.editEntry(id, newEntry);

        if (edited) {
            message = "The record was successfully edited!";

            this.showInformationMessage(message);
        } else {
            message = "Error: The record could not be edited! Please contact support!";

            this.showErrorMessage(message);
        } //end if
    } //editType

    /**
     * Edits the category of a record of the model of this edit controller using the input of this edit controller's
     * edit view.
     */
    private void editCategory() {
        String id;
        String newCategory;
        String newSubcategory;
        Optional<Entry> optional;
        JTextField idTextField;
        String message;
        Entry entry;
        Type type;
        Set<String> tags;
        Entry newEntry;
        boolean edited;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        newCategory = this.getNewCategoryInput();

        if (newCategory == null) {
            return;
        } //end if

        newSubcategory = this.getNewSubcategoryInput();

        if (newSubcategory == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        idTextField = this.editView.getIdTextField();

        if (optional.isEmpty()) {
            message = "Error: A record with the specified ID does not exist!";

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        entry = optional.get();

        type = entry.getType();

        tags = entry.getTags();

        newEntry = new Entry(id, type, newCategory, newSubcategory, tags);

        edited = this.model.editEntry(id, newEntry);

        if (edited) {
            message = "The record was successfully edited!";

            this.showInformationMessage(message);
        } else {
            message = "Error: The record could not be edited! Please contact support!";

            this.showErrorMessage(message);
        } //end if
    } //editCategory

    /**
     * Edits the subcategory of a record of the model of this edit controller using the input of this edit controller's
     * edit view.
     */
    private void editSubcategory() {
        String id;
        String category;
        String newSubcategory;
        Optional<Entry> optional;
        JTextField idTextField;
        String message;
        Entry entry;
        Type type;
        Set<String> tags;
        Entry newEntry;
        boolean edited;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        newSubcategory = this.getNewSubcategoryInput();

        if (newSubcategory == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        idTextField = this.editView.getIdTextField();

        if (optional.isEmpty()) {
            message = "Error: A record with the specified ID does not exist!";

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        entry = optional.get();

        type = entry.getType();

        tags = entry.getTags();

        newEntry = new Entry(id, type, category, newSubcategory, tags);

        edited = this.model.editEntry(id, newEntry);

        if (edited) {
            message = "The record was successfully edited!";

            this.showInformationMessage(message);
        } else {
            message = "Error: The record could not be edited! Please contact support!";

            this.showErrorMessage(message);
        } //end if
    } //editSubcategory

    /**
     * Edits the tags of a record of the model of this edit controller using the input of this edit controller's edit
     * view.
     */
    private void editTags() {
        String id;
        Set<String> newTags;
        Optional<Entry> optional;
        JTextField idTextField;
        String message;
        Entry entry;
        Type type;
        String category;
        String subcategory;
        Entry newEntry;
        boolean edited;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        newTags = this.getNewTagsInput();

        if (newTags == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        idTextField = this.editView.getIdTextField();

        if (optional.isEmpty()) {
            message = "Error: A record with the specified ID does not exist!";

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        entry = optional.get();

        type = entry.getType();

        category = entry.getCategory();

        subcategory = entry.getSubcategory();

        newEntry = new Entry(id, type, category, subcategory, newTags);

        edited = this.model.editEntry(id, newEntry);

        if (edited) {
            message = "The record was successfully edited!";

            this.showInformationMessage(message);
        } else {
            message = "Error: The record could not be edited! Please contact support!";

            this.showErrorMessage(message);
        } //end if
    } //editTags

    /**
     * Fills the new category combo box of this edit controller's edit view with the categories of this edit
     * controller's model.
     */
    void fillNewCategoryComboBox() {
        JComboBox<String> newCategoryComboBox;
        Set<String> categories;

        newCategoryComboBox = this.editView.getNewCategoryComboBox();

        categories = this.model.getCategories();

        newCategoryComboBox.removeAllItems();

        if (categories.isEmpty()) {
            newCategoryComboBox.setEnabled(false);
        } else {
            newCategoryComboBox.setEnabled(true);

            categories.forEach(newCategoryComboBox::addItem);
        } //end if

        newCategoryComboBox.setSelectedIndex(-1);
    } //fillCategoryComboBox

    /**
     * Fills the category combo box of this edit controller's edit view with the categories of this edit controller's
     * model.
     */
    void fillCategoryComboBox() {
        JComboBox<String> categoryComboBox;
        Set<String> categories;

        categoryComboBox = this.editView.getCategoryComboBox();

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
     * Fills the new subcategory combo box of this edit controller's edit view with the subcategories of this edit
     * controller's model that are mapped from the user selected category.
     */
    void fillNewSubcategoryComboBox() {
        JComboBox<String> newSubcategoryComboBox;
        JComboBox<String> categoryComboBox;
        String category;
        Set<String> subcategories;

        newSubcategoryComboBox = this.editView.getNewSubcategoryComboBox();

        categoryComboBox = this.editView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            newSubcategoryComboBox.setEnabled(false);

            newSubcategoryComboBox.setSelectedIndex(-1);

            newSubcategoryComboBox.removeAllItems();

            return;
        } //end if

        subcategories = this.model.getSubcategories(category);

        newSubcategoryComboBox.removeAllItems();

        if (subcategories.isEmpty()) {
            newSubcategoryComboBox.setEnabled(false);
        } else {
            newSubcategoryComboBox.setEnabled(true);

            subcategories.forEach(newSubcategoryComboBox::addItem);
        } //end if

        newSubcategoryComboBox.setSelectedIndex(-1);
    } //fillNewSubcategoryComboBox

    /**
     * Displays the edit ID components of this edit controller's edit view.
     */
    private void displayEditIdComponents() {
        JPanel panel;
        JButton editButton;
        JTextField newIdTextField;
        JButton clearButton;
        Window window;
        int expectedCount = 2;
        ActionListener[] editListeners;
        int newIdRow = 2;
        int column = 0;
        int editRow = 3;
        int clearRow = 4;

        panel = this.editView.getPanel();

        editButton = this.editView.getEditButton();

        newIdTextField = this.editView.getNewIdTextField();

        clearButton = this.editView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        editListeners = editButton.getActionListeners();

        for (ActionListener listener : editListeners) {
            editButton.removeActionListener(listener);
        } //end for

        ViewUtilities.addComponentToPanel(panel, newIdTextField, newIdRow, column);

        ViewUtilities.addComponentToPanel(panel, editButton, editRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        editButton.addActionListener(actionEvent -> this.editId());
    } //displayEditIdComponents

    /**
     * Displays the edit type components of this edit controller's edit view.
     */
    private void displayEditTypeComponents() {
        JPanel panel;
        JButton editButton;
        JComboBox<Type> newTypeComboBox;
        JButton clearButton;
        Window window;
        int expectedCount = 2;
        ActionListener[] editListeners;
        int newTypeRow = 2;
        int column = 0;
        int editRow = 3;
        int clearRow = 4;

        panel = this.editView.getPanel();

        editButton = this.editView.getEditButton();

        newTypeComboBox = this.editView.getNewTypeComboBox();

        clearButton = this.editView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        editListeners = editButton.getActionListeners();

        for (ActionListener listener : editListeners) {
            editButton.removeActionListener(listener);
        } //end for

        ViewUtilities.addComponentToPanel(panel, newTypeComboBox, newTypeRow, column);

        ViewUtilities.addComponentToPanel(panel, editButton, editRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        editButton.addActionListener(actionEvent -> this.editType());
    } //displayEditTypeComponents

    /**
     * Displays the edit category components of this edit controller's edit view.
     */
    private void displayEditCategoryComponents() {
        JPanel panel;
        JButton editButton;
        JComboBox<String> newCategoryComboBox;
        JComboBox<String> newSubcategoryComboBox;
        JButton clearButton;
        Window window;
        int expectedCount = 2;
        ActionListener[] editListeners;
        int newCategoryRow = 2;
        int column = 0;
        int newSubcategoryRow = 3;
        int editRow = 4;
        int clearRow = 5;

        panel = this.editView.getPanel();

        editButton = this.editView.getEditButton();

        newCategoryComboBox = this.editView.getNewCategoryComboBox();

        newSubcategoryComboBox = this.editView.getNewSubcategoryComboBox();

        clearButton = this.editView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        editListeners = editButton.getActionListeners();

        for (ActionListener listener : editListeners) {
            editButton.removeActionListener(listener);
        } //end for

        ViewUtilities.addComponentToPanel(panel, newCategoryComboBox, newCategoryRow, column);

        ViewUtilities.addComponentToPanel(panel, newSubcategoryComboBox, newSubcategoryRow, column);

        ViewUtilities.addComponentToPanel(panel, editButton, editRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        this.fillNewCategoryComboBox();

        this.fillNewSubcategoryComboBox();

        newCategoryComboBox.addActionListener(actionEvent -> this.fillNewSubcategoryComboBox());

        editButton.addActionListener(actionEvent -> this.editCategory());
    } //displayEditCategoryComponents

    /**
     * Displays the edit subcategory components of this edit controller's edit view.
     */
    private void displayEditSubcategoryComponents() {
        JPanel panel;
        JButton editButton;
        JComboBox<String> categoryComboBox;
        JComboBox<String> newSubcategoryComboBox;
        JButton clearButton;
        Window window;
        int expectedCount = 2;
        ActionListener[] editListeners;
        int categoryRow = 2;
        int column = 0;
        int newSubcategoryRow = 3;
        int editRow = 4;
        int clearRow = 5;

        panel = this.editView.getPanel();

        editButton = this.editView.getEditButton();

        categoryComboBox = this.editView.getCategoryComboBox();

        newSubcategoryComboBox = this.editView.getNewSubcategoryComboBox();

        clearButton = this.editView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        editListeners = editButton.getActionListeners();

        for (ActionListener listener : editListeners) {
            editButton.removeActionListener(listener);
        } //end for

        ViewUtilities.addComponentToPanel(panel, categoryComboBox, categoryRow, column);

        ViewUtilities.addComponentToPanel(panel, newSubcategoryComboBox, newSubcategoryRow, column);

        ViewUtilities.addComponentToPanel(panel, editButton, editRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        this.fillCategoryComboBox();

        this.fillNewSubcategoryComboBox();

        categoryComboBox.addActionListener(actionEvent -> this.fillNewSubcategoryComboBox());

        editButton.addActionListener(actionEvent -> this.editSubcategory());
    } //displayEditSubcategoryComponents

    /**
     * Displays the edit tags components of this edit controller's edit view.
     */
    private void displayEditTagsComponents() {
        JPanel panel;
        JButton editButton;
        JTextField newTagsTextField;
        JButton clearButton;
        Window window;
        int expectedCount = 2;
        ActionListener[] editListeners;
        int newTagsRow = 2;
        int column = 0;
        int editRow = 3;
        int clearRow = 4;

        panel = this.editView.getPanel();

        editButton = this.editView.getEditButton();

        newTagsTextField = this.editView.getNewTagsTextField();

        clearButton = this.editView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        editListeners = editButton.getActionListeners();

        for (ActionListener listener : editListeners) {
            editButton.removeActionListener(listener);
        } //end for

        ViewUtilities.addComponentToPanel(panel, newTagsTextField, newTagsRow, column);

        ViewUtilities.addComponentToPanel(panel, editButton, editRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        editButton.addActionListener(actionEvent -> this.editTags());
    } //displayEditTagsComponents

    /**
     * Returns a new {@code EditController} object with the specified model and edit view.
     *
     * @param model the model to be used in construction
     * @param editView the edit view to be used in construction
     * @return a new {@code EditController} object with the specified model and edit view
     * @throws NullPointerException if the specified model or edit view is {@code null}
     */
    public static EditController newEditController(Model model, EditView editView) {
        EditController editController;
        JComboBox<Field> fieldComboBox;
        JButton clearButton;

        editController = new EditController(model, editView);

        fieldComboBox = editController.editView.getFieldComboBox();

        clearButton = editController.editView.getClearButton();

        fieldComboBox.addActionListener(actionEvent -> {
            Field field;

            field = (Field) fieldComboBox.getSelectedItem();

            if (field == null) {
                return;
            } //end if

            switch (field) {
                case ID -> editController.displayEditIdComponents();
                case TYPE -> editController.displayEditTypeComponents();
                case CATEGORY -> editController.displayEditCategoryComponents();
                case SUBCATEGORY -> editController.displayEditSubcategoryComponents();
                case TAGS -> editController.displayEditTagsComponents();
            } //end switch
        });

        clearButton.addActionListener(actionEvent -> editController.clearFields());

        return editController;
    } //newEditController
}