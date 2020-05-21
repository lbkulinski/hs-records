package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.AddView;
import java.util.logging.Logger;
import java.util.Objects;
import java.util.logging.Level;
import java.time.LocalDate;
import javax.swing.JTextField;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import com.records.hs.model.Type;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.records.hs.model.Entry;
import javax.swing.JButton;

/**
 * An add controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 20, 2020
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
     * The logger of this add controller.
     */
    private final Logger logger;

    /**
     * Constructs a newly allocated {@code AddController} object with the specified model and add view.
     *
     * @param model the model to be used in construction
     * @param addView the add view to be used in construction
     * @throws NullPointerException if the specified model or add view is {@code null}
     */
    private AddController(Model model, AddView addView) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(addView, "the specified add view is null");

        this.model = model;
        this.addView = addView;
        this.logger = Logger.getGlobal();
    } //AddController

    /**
     * Returns the ID that comes after the specified ID. If the specified ID is improperly formatted, {@code null} is
     * returned.
     *
     * @param id the ID to be used in the operation
     * @return the ID that comes after the specified ID
     */
    private String getNextId(String id) {
        LocalDate currentDate;
        int currentYear;
        int nextNumber;
        int nextYear;
        String format = "%04d_%04d";
        String[] parts;
        int expectedLength = 2;
        String numberString;
        String yearString;
        int number;
        int year;
        String nextId;

        currentDate = LocalDate.now();

        currentYear = currentDate.getYear();

        if (id == null) {
            nextNumber = 1;

            nextYear = currentYear;

            nextId = String.format(format, nextNumber, nextYear);

            return nextId;
        } //end if

        parts = id.split("_");

        if (parts.length != expectedLength) {
            return null;
        } //end if

        numberString = parts[0];

        yearString = parts[1];

        if (!numberString.matches("(\\d)+")) {
            return null;
        } //end if

        if (!yearString.matches("(\\d)+")) {
            return null;
        } //end if

        try {
            number = Integer.parseInt(numberString);

            year = Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            String message = e.getMessage();

            this.logger.log(Level.INFO, message, e);

            return null;
        } //end try catch

        if (year > currentYear) {
            return null;
        } //end if

        if (year == currentYear) {
            nextNumber = number + 1;

            nextYear = year;
        } else {
            nextNumber = 1;

            nextYear = currentYear;
        } //end if

        nextId = String.format(format, nextNumber, nextYear);

        return nextId;
    } //getNextId

    /**
     * Fills the ID text field of this add controller's add view with the next ID.
     */
    private void fillIdTextField() {
        String id;
        String nextId;
        JTextField idTextField;

        id = this.model.getLatestId();

        nextId = this.getNextId(id);

        idTextField = this.addView.getIdTextField();

        idTextField.setText(nextId);
    } //fillIdTextField

    /**
     * Fills the category combo box of this add controller's add view with the categories of this add controller's
     * model.
     */
    void fillCategoryComboBox() {
        JComboBox<String> categoryComboBox;
        Set<String> categories;

        categoryComboBox = this.addView.getCategoryComboBox();

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
     * Fills the subcategory combo box of this add controller's add view with the subcategories of this add
     * controller's model that are mapped from the user selected category.
     */
    void fillSubcategoryComboBox() {
        JComboBox<String> subcategoryComboBox;
        JComboBox<String> categoryComboBox;
        String category;
        Set<String> subcategories;

        subcategoryComboBox = this.addView.getSubcategoryComboBox();

        categoryComboBox = this.addView.getCategoryComboBox();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            return;
        } //end if

        subcategories = this.model.getSubcategories(category);

        subcategoryComboBox.setEnabled(true);

        subcategoryComboBox.removeAllItems();

        subcategories.forEach(subcategoryComboBox::addItem);

        subcategoryComboBox.setSelectedIndex(-1);
    } //fillSubcategoryComboBox

    /**
     * Clears the fields of this add controller's add view.
     */
    private void clearFields() {
        JTextField idTextField;
        JComboBox<Type> typeComboBox;
        JComboBox<String> subcategoryComboBox;
        JTextField tagsTextField;

        idTextField = this.addView.getIdTextField();

        typeComboBox = this.addView.getTypeComboBox();

        subcategoryComboBox = this.addView.getSubcategoryComboBox();

        tagsTextField = this.addView.getTagsTextField();

        this.fillIdTextField();

        typeComboBox.setSelectedIndex(-1);

        this.fillCategoryComboBox();

        subcategoryComboBox.removeAllItems();

        subcategoryComboBox.setSelectedIndex(-1);

        subcategoryComboBox.setEnabled(false);

        tagsTextField.setText(null);

        idTextField.requestFocus();
    } //clearFields

    /**
     * Returns the ID input of this add controller's add view.
     *
     * @return the ID input of this add controller's add view
     */
    private String getIdInput() {
        JTextField idTextField;
        JPanel panel;
        String id;
        String message;
        String title = "HS Records";
        String comma = ",";

        idTextField = this.addView.getIdTextField();

        panel = this.addView.getPanel();

        id = idTextField.getText();

        if (id.isBlank()) {
            message = "Error: The specified ID is blank!";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);

            idTextField.requestFocus();

            return null;
        } else if (id.contains(comma)) {
            message = "Error: The specified ID contains a comma!";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);

            idTextField.requestFocus();

            return null;
        } //end if

        return id;
    } //getIdInput

    /**
     * Returns the type input of this add controller's add view.
     *
     * @return the type input of this add controller's add view
     */
    private Type getTypeInput() {
        JComboBox<Type> typeComboBox;
        JPanel panel;
        Type type;
        String message;
        String title = "HS Records";

        typeComboBox = this.addView.getTypeComboBox();

        panel = this.addView.getPanel();

        type = (Type) typeComboBox.getSelectedItem();

        if (type == null) {
            message = "Error: The specified type is blank!";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);

            typeComboBox.requestFocus();

            return null;
        } //end if

        return type;
    } //getTypeInput

    /**
     * Returns the category input of this add controller's add view.
     *
     * @return the category input of this add controller's add view
     */
    private String getCategoryInput() {
        JComboBox<String> categoryComboBox;
        JPanel panel;
        String category;

        categoryComboBox = this.addView.getCategoryComboBox();

        panel = this.addView.getPanel();

        category = (String) categoryComboBox.getSelectedItem();

        if (category == null) {
            String message = "Error: The specified category is blank!";
            String title = "HS Records";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);

            categoryComboBox.requestFocus();

            return null;
        } //end if

        return category;
    } //getCategoryInput

    /**
     * Returns the subcategory input of this add controller's add view.
     *
     * @return the subcategory input of this add controller's add view
     */
    private String getSubcategoryInput() {
        JComboBox<String> subcategoryComboBox;
        JPanel panel;
        String subcategory;

        subcategoryComboBox = this.addView.getSubcategoryComboBox();

        panel = this.addView.getPanel();

        subcategory = (String) subcategoryComboBox.getSelectedItem();

        if (subcategory == null) {
            String message = "Error: The specified subcategory is blank!";
            String title = "HS Records";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);

            subcategoryComboBox.requestFocus();

            return null;
        } //end if

        return subcategory;
    } //getSubcategoryInput

    /**
     * Returns the tags input of this add controller's add view.
     *
     * @return the tags input of this add controller's add view
     */
    private Set<String> getTagsInput() {
        JTextField tagsTextField;
        JPanel panel;
        String tagsString;
        String[] tagArray;
        Set<String> tags;

        tagsTextField = this.addView.getTagsTextField();

        panel = this.addView.getPanel();

        tagsString = tagsTextField.getText();

        if (tagsString.isBlank()) {
            String message = "Error: The specified set of tags is blank!";
            String title = "HS Records";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);

            tagsTextField.requestFocus();

            return null;
        } //end if

        tagArray = tagsString.split(",");

        tags = Arrays.stream(tagArray)
                     .map(String::trim)
                     .collect(Collectors.toUnmodifiableSet());

        return tags;
    } //getTagsInput

    /**
     * Adds an entry to this add controller's model using the input of this add controller's add view.
     */
    private void addEntry() {
        String id;
        Type type;
        String category;
        String subcategory;
        Set<String> tags;
        Entry entry;
        boolean added;
        JPanel panel;
        String message;
        String title = "HS Records";

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        type = this.getTypeInput();

        if (type == null) {
            return;
        } //end if

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput();

        if (subcategory == null) {
            return;
        } //end if

        tags = this.getTagsInput();

        if (tags == null) {
            return;
        } //end if

        entry = new Entry(id, type, category, subcategory, tags);

        added = this.model.addEntry(entry);

        panel = this.addView.getPanel();

        if (added) {
            message = "The record was successfully added!";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.INFORMATION_MESSAGE);

            this.clearFields();
        } else {
            message = "Error: The record could not be added! Please contact support!";

            JOptionPane.showMessageDialog(panel, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //addEntry

    /**
     * Returns a new {@code AddController} object with the specified model and add view.
     *
     * @param model the model to be used in construction
     * @param addView the add view to be used in construction
     * @return a new {@code AddController} object with the specified model and add view
     * @throws NullPointerException if the specified model or add view is {@code null}
     */
    public static AddController newAddController(Model model, AddView addView) {
        AddController addController;
        JComboBox<String> categoryComboBox;
        JButton addButton;
        JButton clearButton;

        addController = new AddController(model, addView);

        categoryComboBox = addController.addView.getCategoryComboBox();

        addButton = addController.addView.getAddButton();

        clearButton = addController.addView.getClearButton();

        categoryComboBox.addActionListener(actionEvent -> addController.fillSubcategoryComboBox());

        addButton.addActionListener(actionEvent -> addController.addEntry());

        clearButton.addActionListener(actionEvent -> addController.clearFields());

        addController.clearFields();

        return addController;
    } //newAddController
}