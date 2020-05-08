package com.records.hs.view;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * An edit view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 8, 2020
 */
public final class EditView {
    /**
     * The ID text field of this edit view.
     */
    private final JTextField idTextField;

    /**
     * The field combo box of this edit view.
     */
    private final JComboBox<String> fieldComboBox;

    /**
     * The new ID text field of this edit view.
     */
    private final JTextField newIdTextField;

    /**
     * The new category combo box of this edit view.
     */
    private final JComboBox<String> newCategoryComboBox;

    /**
     * The category combo box of this edit view.
     */
    private final JComboBox<String> categoryComboBox;

    /**
     * The new subcategory combo box of this edit view.
     */
    private final JComboBox<String> newSubcategoryComboBox;

    /**
     * The new tags text field of this edit view.
     */
    private final JTextField newTagsTextField;

    /**
     * The edit button of this edit view.
     */
    private final JButton editButton;

    /**
     * The clear button of this edit view.
     */
    private final JButton clearButton;

    /**
     * The panel of this edit view.
     */
    private final JPanel panel;

    /**
     * Constructs a newly allocated {@code EditView} object.
     */
    private EditView() {
        int length = 15;
        String editName = "Edit";
        String clearName = "Clear";
        GridBagLayout layout = new GridBagLayout();

        this.idTextField = new JTextField(length);
        this.fieldComboBox = new JComboBox<>();
        this.newIdTextField = new JTextField(length);
        this.newCategoryComboBox = new JComboBox<>();
        this.categoryComboBox = new JComboBox<>();
        this.newSubcategoryComboBox = new JComboBox<>();
        this.newTagsTextField = new JTextField(length);
        this.editButton = new JButton(editName);
        this.clearButton = new JButton(clearName);
        this.panel = new JPanel(layout);
    } //EditView

    /**
     * Formats the components of this edit view.
     */
    private void formatComponents() {
        String idName = "ID";
        String fieldName = "Field";
        String newIdName = "New ID";
        String newCategoryName = "New Category";
        String categoryName = "Category";
        String newSubcategoryName = "New Subcategory";
        String newTagsName = "New Tags";

        ViewUtilities.formatComponent(this.idTextField, idName);

        ViewUtilities.formatComponent(this.fieldComboBox, fieldName);

        ViewUtilities.formatComponent(this.newIdTextField, newIdName);

        ViewUtilities.formatComponent(this.newCategoryComboBox, newCategoryName);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryName);

        ViewUtilities.formatComponent(this.newSubcategoryComboBox, newSubcategoryName);

        ViewUtilities.formatComponent(this.newTagsTextField, newTagsName);
    } //formatComponents

    /**
     * Adds the components of this edit view to the panel of this edit view.
     */
    private void addComponentsToPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        int idRow = 0;
        int column = 0;
        int fieldRow = 1;
        int clearRow = 2;

        ViewUtilities.addComponentToPanel(this.panel, this.idTextField, constraints, idRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.fieldComboBox, constraints, fieldRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, constraints, clearRow, column);
    } //addComponentsToPanel

    /**
     * Returns a new {@code EditView} object.
     *
     * @return a new {@code EditView} object
     */
    public static EditView newEditView() {
        EditView editView = new EditView();
        String idName = "ID";
        String categoryName = "Category";
        String subcategoryName = "Subcategory";
        String tagsName = "Tags";

        editView.fieldComboBox.addItem(idName);

        editView.fieldComboBox.addItem(categoryName);

        editView.fieldComboBox.addItem(subcategoryName);

        editView.fieldComboBox.addItem(tagsName);

        editView.formatComponents();

        editView.addComponentsToPanel();

        return editView;
    } //newEditView

    /**
     * Returns the ID text field of this edit view.
     *
     * @return the ID text field of this edit view
     */
    public JTextField idTextField() {
        return this.idTextField;
    } //idTextField

    /**
     * Returns the field combo box of this edit view.
     *
     * @return the field combo box of this edit view
     */
    public JComboBox<String> fieldComboBox() {
        return this.fieldComboBox;
    } //fieldComboBox

    /**
     * Returns the new ID text field of this edit view.
     *
     * @return the new ID text field of this edit view
     */
    public JTextField newIdTextField() {
        return this.newIdTextField;
    } //newIdTextField

    /**
     * Returns the new category combo box of this edit view.
     *
     * @return the new category combo box of this edit view
     */
    public JComboBox<String> newCategoryComboBox() {
        return this.newCategoryComboBox;
    } //newCategoryComboBox

    /**
     * Returns the category combo box of this edit view.
     *
     * @return the category combo box of this edit view
     */
    public JComboBox<String> categoryComboBox() {
        return this.categoryComboBox;
    } //categoryComboBox

    /**
     * Returns the new subcategory combo box of this edit view.
     *
     * @return the new subcategory combo box of this edit view
     */
    public JComboBox<String> newSubcategoryComboBox() {
        return this.newSubcategoryComboBox;
    } //newSubcategoryComboBox

    /**
     * Returns the new tags text field of this edit view.
     *
     * @return the new tags text field of this edit view
     */
    public JTextField newTagsTextField() {
        return this.newTagsTextField;
    } //newTagsTextField

    /**
     * Returns the edit button of this edit view.
     *
     * @return the edit button of this edit view
     */
    public JButton editButton() {
        return this.editButton;
    } //editButton

    /**
     * Returns the clear button of this edit view.
     *
     * @return the clear button of this edit view
     */
    public JButton clearButton() {
        return this.clearButton;
    } //clearButton

    /**
     * Returns the panel of this edit view.
     *
     * @return the panel of this edit view
     */
    public JPanel panel() {
        return this.panel;
    } //panel
}