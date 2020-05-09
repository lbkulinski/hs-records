package com.records.hs.view;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

/**
 * An edit view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 9, 2020
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
        String idTitle = "ID";
        String fieldTitle = "Field";
        String newIdTitle = "New ID";
        String newCategoryTitle = "New Category";
        String categoryTitle = "Category";
        String newSubcategoryTitle = "New Subcategory";
        String newTagsTitle = "New Tags";

        ViewUtilities.formatComponent(this.idTextField, idTitle);

        ViewUtilities.formatComponent(this.fieldComboBox, fieldTitle);

        ViewUtilities.formatComponent(this.newIdTextField, newIdTitle);

        ViewUtilities.formatComponent(this.newCategoryComboBox, newCategoryTitle);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryTitle);

        ViewUtilities.formatComponent(this.newSubcategoryComboBox, newSubcategoryTitle);

        ViewUtilities.formatComponent(this.newTagsTextField, newTagsTitle);
    } //formatComponents

    /**
     * Adds the components of this edit view to the panel of this edit view.
     */
    private void addComponentsToPanel() {
        int idRow = 0;
        int column = 0;
        int fieldRow = 1;
        int clearRow = 2;

        ViewUtilities.addComponentToPanel(this.panel, this.idTextField, idRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.fieldComboBox, fieldRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, clearRow, column);
    } //addComponentsToPanel

    /**
     * Returns a new {@code EditView} object.
     *
     * @return a new {@code EditView} object
     */
    public static EditView newEditView() {
        EditView editView = new EditView();
        String idItem = "ID";
        String categoryItem = "Category";
        String subcategoryItem = "Subcategory";
        String tagsItem = "Tags";

        editView.fieldComboBox.addItem(idItem);

        editView.fieldComboBox.addItem(categoryItem);

        editView.fieldComboBox.addItem(subcategoryItem);

        editView.fieldComboBox.addItem(tagsItem);

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