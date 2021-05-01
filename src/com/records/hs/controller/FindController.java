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
import com.records.hs.model.Entry;
import javax.swing.JTextArea;
import java.util.Optional;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import com.records.hs.view.ViewUtilities;

/**
 * A find controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 1, 2021
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

    /**
     * Adds the specified entries to the text area of this find controller's find view.
     *
     * @param entries the entries to be used in the operation
     */
    private void addEntriesToTextArea(Entry... entries) {
        JTextArea resultsTextArea;
        StringBuilder stringBuilder;
        String id;
        Type type;
        String category;
        String subcategory;
        Set<String> tags;
        StringBuilder tagsStringBuilder;
        int length;
        int startIndex;
        String tagsString;
        String entryString;
        String format = "ID: %s%nType: %s%nCategory: %s%nSubcategory: %s%nTags: %s%n%n";
        String resultsString;

        Objects.requireNonNull(entries, "the specified set of entries is null");

        resultsTextArea = this.findView.getResultsTextArea();

        stringBuilder = new StringBuilder();

        for (Entry entry : entries) {
            id = entry.id();

            type = entry.type();

            category = entry.category();

            subcategory = entry.subcategory();

            tags = entry.tags();

            tagsStringBuilder = new StringBuilder();

            for (String tag : tags) {
                tagsStringBuilder.append(tag);

                tagsStringBuilder.append(", ");
            } //end if

            if (tagsStringBuilder.length() > 0) {
                length = tagsStringBuilder.length();

                startIndex = length - 2;

                tagsStringBuilder.delete(startIndex, length);
            } //end if

            tagsString = tagsStringBuilder.toString();

            entryString = String.format(format, id, type, category, subcategory, tagsString);

            stringBuilder.append(entryString);
        } //end for

        length = stringBuilder.length();

        if (length > 0) {
            startIndex = length - 2;

            stringBuilder.delete(startIndex, length);
        } //end if

        resultsString = stringBuilder.toString();

        resultsTextArea.setText(resultsString);

        resultsTextArea.setCaretPosition(0);
    } //addEntriesToTextArea

    /**
     * Searches for a record with the specified ID in the model of this find controller using the input of this find
     * controller's find view.
     */
    private void findWithId() {
        String id;
        Optional<Entry> optional;
        Entry foundEntry;
        JTextArea resultsTextArea;

        id = this.getIdInput();

        if (id == null) {
            return;
        } //end if

        optional = this.model.findEntryWithId(id);

        resultsTextArea = this.findView.getResultsTextArea();

        resultsTextArea.setText(null);

        if (optional.isEmpty()) {
            JTextField idTextField;
            String message = "Error: A record with the specified ID does not exist!";

            idTextField = this.findView.getIdTextField();

            this.showErrorMessage(idTextField, message);

            return;
        } //end if

        foundEntry = optional.get();

        this.addEntriesToTextArea(foundEntry);
    } //findWithId

    /**
     * Searches for records with the specified type in the model of this find controller using the input of this find
     * controller's find view.
     */
    private void findWithType() {
        Type type;
        Set<Entry> foundEntries;
        Entry[] entryArray;
        JTextArea resultsTextArea;

        type = this.getTypeInput();

        if (type == null) {
            return;
        } //end if

        foundEntries = this.model.findEntriesWithType(type);

        resultsTextArea = this.findView.getResultsTextArea();

        resultsTextArea.setText(null);

        if (foundEntries.isEmpty()) {
            JComboBox<Type> typeComboBox;
            String message = "Error: A record with the specified type does not exist!";

            typeComboBox = this.findView.getTypeComboBox();

            this.showErrorMessage(typeComboBox, message);

            return;
        } //end if

        entryArray = new Entry[foundEntries.size()];

        foundEntries.toArray(entryArray);

        this.addEntriesToTextArea(entryArray);
    } //findWithType

    /**
     * Searches for records with the specified category in the model of this find controller using the input of this
     * find controller's find view.
     */
    private void findWithCategory() {
        String category;
        Set<Entry> foundEntries;
        Entry[] entryArray;
        JTextArea resultsTextArea;

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        foundEntries = this.model.findEntriesWithCategory(category);

        resultsTextArea = this.findView.getResultsTextArea();

        resultsTextArea.setText(null);

        if (foundEntries.isEmpty()) {
            JComboBox<String> categoryComboBox;
            String message = "Error: A record with the specified category does not exist!";

            categoryComboBox = this.findView.getCategoryComboBox();

            this.showErrorMessage(categoryComboBox, message);

            return;
        } //end if

        entryArray = new Entry[foundEntries.size()];

        foundEntries.toArray(entryArray);

        this.addEntriesToTextArea(entryArray);
    } //findWithCategory

    /**
     * Searches for records with the specified subcategory in the model of this find controller using the input of this
     * find controller's find view.
     */
    private void findWithSubcategory() {
        String category;
        String subcategory;
        Set<Entry> foundEntries;
        Entry[] entryArray;
        JTextArea resultsTextArea;

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput();

        if (subcategory == null) {
            return;
        } //end if

        foundEntries = this.model.findEntriesWithSubcategory(category, subcategory);

        resultsTextArea = this.findView.getResultsTextArea();

        resultsTextArea.setText(null);

        if (foundEntries.isEmpty()) {
            JComboBox<String> subcategoryComboBox;
            String message = "Error: A record with the specified subcategory does not exist!";

            subcategoryComboBox = this.findView.getSubcategoryComboBox();

            this.showErrorMessage(subcategoryComboBox, message);

            return;
        } //end if

        entryArray = new Entry[foundEntries.size()];

        foundEntries.toArray(entryArray);

        this.addEntriesToTextArea(entryArray);
    } //findWithSubcategory

    /**
     * Searches for records with the specified tag in the model of this find controller using the input of this find
     * controller's find view.
     */
    private void findWithTag() {
        String tag;
        Set<Entry> foundEntries;
        Entry[] entryArray;
        JTextArea resultsTextArea;

        tag = this.getTagInput();

        if (tag == null) {
            return;
        } //end if

        foundEntries = this.model.findEntriesWithTag(tag);

        resultsTextArea = this.findView.getResultsTextArea();

        resultsTextArea.setText(null);

        if (foundEntries.isEmpty()) {
            JTextField tagTextField;
            String message = "Error: A record with the specified tag does not exist!";

            tagTextField = this.findView.getTagTextField();

            this.showErrorMessage(tagTextField, message);

            return;
        } //end if

        entryArray = new Entry[foundEntries.size()];

        foundEntries.toArray(entryArray);

        this.addEntriesToTextArea(entryArray);
    } //findWithTag

    /**
     * Adds the results scroll pane of this find controller's find view to the panel of this find controller's find
     * view at the specified row and column.
     *
     * @param row the row to be used in the operation
     * @param column the column to be used in the operation
     */
    private void addScrollPaneToPanel(int row, int column) {
        GridBagConstraints constraints;
        JPanel panel;
        JScrollPane resultsScrollPane;
        Dimension size;

        if (row < 0) {
            throw new IllegalArgumentException("the specified row is negative");
        } //end if

        if (column < 0) {
            throw new IllegalArgumentException("the specified column is negative");
        } //end if

        constraints = new GridBagConstraints();

        constraints.gridx = column;

        constraints.gridy = row;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 2.0;

        constraints.weighty = 2.0;

        panel = this.findView.getPanel();

        resultsScrollPane = this.findView.getResultsScrollPane();

        panel.add(resultsScrollPane, constraints);

        size = resultsScrollPane.getMinimumSize();

        resultsScrollPane.setPreferredSize(size);
    } //addScrollPaneToPanel

    /**
     * Displays the find with ID components of this find controller's find view.
     */
    private void displayFindWithIdComponents() {
        JPanel panel;
        JButton findButton;
        JTextField idTextField;
        JTextArea resultsTextArea;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] findListeners;
        int idRow = 2;
        int column = 0;
        int resultsRow = 3;
        int findRow = 4;
        int clearRow = 5;

        panel = this.findView.getPanel();

        findButton = this.findView.getFindButton();

        idTextField = this.findView.getIdTextField();

        resultsTextArea = this.findView.getResultsTextArea();

        clearButton = this.findView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        findListeners = findButton.getActionListeners();

        for (ActionListener listener : findListeners) {
            findButton.removeActionListener(listener);
        } //end for

        idTextField.setText(null);

        resultsTextArea.setText(null);

        ViewUtilities.addComponentToPanel(panel, idTextField, idRow, column);

        this.addScrollPaneToPanel(resultsRow, column);

        ViewUtilities.addComponentToPanel(panel, findButton, findRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        findButton.addActionListener(actionEvent -> this.findWithId());
    } //displayFindWithIdComponents

    /**
     * Displays the find with type components of this find controller's find view.
     */
    private void displayFindWithTypeComponents() {
        JPanel panel;
        JButton findButton;
        JComboBox<Type> typeComboBox;
        JTextArea resultsTextArea;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] findListeners;
        int typeRow = 2;
        int column = 0;
        int resultsRow = 3;
        int findRow = 4;
        int clearRow = 5;

        panel = this.findView.getPanel();

        findButton = this.findView.getFindButton();

        typeComboBox = this.findView.getTypeComboBox();

        resultsTextArea = this.findView.getResultsTextArea();

        clearButton = this.findView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        findListeners = findButton.getActionListeners();

        for (ActionListener listener : findListeners) {
            findButton.removeActionListener(listener);
        } //end for

        typeComboBox.setSelectedIndex(-1);

        resultsTextArea.setText(null);

        ViewUtilities.addComponentToPanel(panel, typeComboBox, typeRow, column);

        this.addScrollPaneToPanel(resultsRow, column);

        ViewUtilities.addComponentToPanel(panel, findButton, findRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        findButton.addActionListener(actionEvent -> this.findWithType());
    } //displayFindWithTypeComponents

    /**
     * Displays the find with category components of this find controller's find view.
     */
    private void displayFindWithCategoryComponents() {
        JPanel panel;
        JButton findButton;
        JComboBox<String> categoryComboBox;
        JTextArea resultsTextArea;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] findListeners;
        int categoryRow = 2;
        int column = 0;
        int resultsRow = 3;
        int findRow = 4;
        int clearRow = 5;

        panel = this.findView.getPanel();

        findButton = this.findView.getFindButton();

        categoryComboBox = this.findView.getCategoryComboBox();

        resultsTextArea = this.findView.getResultsTextArea();

        clearButton = this.findView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        findListeners = findButton.getActionListeners();

        for (ActionListener listener : findListeners) {
            findButton.removeActionListener(listener);
        } //end for

        categoryComboBox.setSelectedIndex(-1);

        resultsTextArea.setText(null);

        ViewUtilities.addComponentToPanel(panel, categoryComboBox, categoryRow, column);

        this.addScrollPaneToPanel(resultsRow, column);

        ViewUtilities.addComponentToPanel(panel, findButton, findRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        findButton.addActionListener(actionEvent -> this.findWithCategory());
    } //displayFindWithCategoryComponents

    /**
     * Displays the find with subcategory components of this find controller's find view.
     */
    private void displayFindWithSubcategoryComponents() {
        JPanel panel;
        JButton findButton;
        JComboBox<String> categoryComboBox;
        JComboBox<String> subcategoryComboBox;
        JTextArea resultsTextArea;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] findListeners;
        int categoryRow = 2;
        int column = 0;
        int subcategoryRow = 3;
        int resultsRow = 4;
        int findRow = 5;
        int clearRow = 6;

        panel = this.findView.getPanel();

        findButton = this.findView.getFindButton();

        categoryComboBox = this.findView.getCategoryComboBox();

        subcategoryComboBox = this.findView.getSubcategoryComboBox();

        resultsTextArea = this.findView.getResultsTextArea();

        clearButton = this.findView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        findListeners = findButton.getActionListeners();

        for (ActionListener listener : findListeners) {
            findButton.removeActionListener(listener);
        } //end for

        categoryComboBox.setSelectedIndex(-1);

        resultsTextArea.setText(null);

        ViewUtilities.addComponentToPanel(panel, categoryComboBox, categoryRow, column);

        ViewUtilities.addComponentToPanel(panel, subcategoryComboBox, subcategoryRow, column);

        this.addScrollPaneToPanel(resultsRow, column);

        ViewUtilities.addComponentToPanel(panel, findButton, findRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        findButton.addActionListener(actionEvent -> this.findWithSubcategory());
    } //displayFindWithSubcategoryComponents

    /**
     * Displays the find with tag components of this find controller's find view.
     */
    private void displayFindWithTagComponents() {
        JPanel panel;
        JButton findButton;
        JTextField tagTextField;
        JTextArea resultsTextArea;
        JButton clearButton;
        Window window;
        int expectedCount = 1;
        ActionListener[] findListeners;
        int tagRow = 2;
        int column = 0;
        int resultsRow = 3;
        int findRow = 4;
        int clearRow = 5;

        panel = this.findView.getPanel();

        findButton = this.findView.getFindButton();

        tagTextField = this.findView.getTagTextField();

        resultsTextArea = this.findView.getResultsTextArea();

        clearButton = this.findView.getClearButton();

        window = SwingUtilities.getWindowAncestor(panel);

        while (panel.getComponentCount() > expectedCount) {
            panel.remove(expectedCount);
        } //end while

        findListeners = findButton.getActionListeners();

        for (ActionListener listener : findListeners) {
            findButton.removeActionListener(listener);
        } //end for

        tagTextField.setText(null);

        resultsTextArea.setText(null);

        ViewUtilities.addComponentToPanel(panel, tagTextField, tagRow, column);

        this.addScrollPaneToPanel(resultsRow, column);

        ViewUtilities.addComponentToPanel(panel, findButton, findRow, column);

        ViewUtilities.addComponentToPanel(panel, clearButton, clearRow, column);

        window.revalidate();

        window.repaint();

        findButton.addActionListener(actionEvent -> this.findWithTag());
    } //displayFindWithTagComponents

    /**
     * Displays the appropriate find components of this find controller's find view.
     */
    private void displayFindComponents() {
        Field field;

        field = this.getFieldInput();

        if (field == null) {
            return;
        } //end if

        switch (field) {
            case ID -> this.displayFindWithIdComponents();
            case TYPE -> this.displayFindWithTypeComponents();
            case CATEGORY -> this.displayFindWithCategoryComponents();
            case SUBCATEGORY -> this.displayFindWithSubcategoryComponents();
            case TAGS -> this.displayFindWithTagComponents();
        } //end switch
    } //displayFindComponents

    /**
     * Returns a new {@code newFindController} object with the specified model and find view.
     *
     * @param model the model to be used in construction
     * @param findView the find view to be used in construction
     * @return a new {@code newFindController} object with the specified model and find view
     * @throws NullPointerException if the specified model or find view is {@code null}
     */
    public static FindController newFindController(Model model, FindView findView) {
        FindController findController;
        JComboBox<Field> fieldComboBox;
        JComboBox<String> categoryComboBox;
        JButton clearButton;

        findController = new FindController(model, findView);

        fieldComboBox = findController.findView.getFieldComboBox();

        categoryComboBox = findController.findView.getCategoryComboBox();

        clearButton = findController.findView.getClearButton();

        fieldComboBox.addActionListener(actionEvent -> findController.displayFindComponents());

        categoryComboBox.addActionListener(actionEvent -> findController.fillSubcategoryComboBox());

        clearButton.addActionListener(actionEvent -> findController.clearFields());

        findController.clearFields();

        return findController;
    } //newFindController
}