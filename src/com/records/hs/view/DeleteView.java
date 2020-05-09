package com.records.hs.view;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

/**
 * A delete view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 9, 2020
 */
public final class DeleteView {
    /**
     * The field combo box of this delete view.
     */
    private final JComboBox<String> fieldComboBox;

    /**
     * The ID text field of this delete view.
     */
    private final JTextField idTextField;

    /**
     * The category combo box of this delete view.
     */
    private final JComboBox<String> categoryComboBox;

    /**
     * The subcategory combo box of this delete view.
     */
    private final JComboBox<String> subcategoryComboBox;

    /**
     * The tag text field of this delete view.
     */
    private final JTextField tagTextField;

    /**
     * The delete button of this delete view.
     */
    private final JButton deleteButton;

    /**
     * The clear button of this delete view.
     */
    private final JButton clearButton;

    /**
     * The panel of this delete view.
     */
    private final JPanel panel;

    /**
     * Constructs a newly allocated {@code DeleteView} object.
     */
    private DeleteView() {
        int length = 15;
        String deleteName = "Delete";
        String clearName = "Clear";
        GridBagLayout layout = new GridBagLayout();

        this.fieldComboBox = new JComboBox<>();
        this.idTextField = new JTextField(length);
        this.categoryComboBox = new JComboBox<>();
        this.subcategoryComboBox = new JComboBox<>();
        this.tagTextField = new JTextField(length);
        this.deleteButton = new JButton(deleteName);
        this.clearButton = new JButton(clearName);
        this.panel = new JPanel(layout);
    } //DeleteView

    /**
     * Formats the components of this delete view.
     */
    private void formatComponents() {
        String fieldTitle = "Field";
        String idTitle = "ID";
        String categoryTitle = "Category";
        String subcategoryTitle = "Subcategory";
        String tagTitle = "Tag";
        String deleteTitle = "Delete";
        String clearTitle = "Clear";

        ViewUtilities.formatComponent(this.fieldComboBox, fieldTitle);

        ViewUtilities.formatComponent(this.idTextField, idTitle);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryTitle);

        ViewUtilities.formatComponent(this.subcategoryComboBox, subcategoryTitle);

        ViewUtilities.formatComponent(this.tagTextField, tagTitle);

        ViewUtilities.formatComponent(this.deleteButton, deleteTitle);

        ViewUtilities.formatComponent(this.clearButton, clearTitle);
    } //formatComponents

    /**
     * Adds the components of this delete view to the panel of this delete view.
     */
    private void addComponentsToPanel() {
        int fieldRow = 0;
        int column = 0;
        int clearRow = 1;

        ViewUtilities.addComponentToPanel(this.panel, this.fieldComboBox, fieldRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, clearRow, column);
    } //addComponentsToPanel

    /**
     * Returns a new {@code DeleteView} object.
     *
     * @return a new {@code DeleteView} object
     */
    public static DeleteView newDeleteView() {
        DeleteView deleteView = new DeleteView();
        String idItem = "ID";
        String categoryItem = "Category";
        String subcategoryItem = "Subcategory";
        String tagItem = "Tag";

        deleteView.fieldComboBox.addItem(idItem);

        deleteView.fieldComboBox.addItem(categoryItem);

        deleteView.fieldComboBox.addItem(subcategoryItem);

        deleteView.fieldComboBox.addItem(tagItem);

        deleteView.formatComponents();

        deleteView.addComponentsToPanel();

        return deleteView;
    } //newDeleteView

    /**
     * Returns the field combo box of this delete view.
     *
     * @return the field combo box of this delete view
     */
    public JComboBox<String> fieldComboBox() {
        return this.fieldComboBox;
    } //fieldComboBox

    /**
     * Returns the ID text field of this delete view.
     *
     * @return the ID text field of this delete view
     */
    public JTextField idTextField() {
        return this.idTextField;
    } //idTextField

    /**
     * Returns the category combo box of this delete view.
     *
     * @return the category combo box of this delete view
     */
    public JComboBox<String> categoryComboBox() {
        return this.categoryComboBox;
    } //categoryComboBox

    /**
     * Returns the subcategory combo box of this delete view.
     *
     * @return the subcategory combo box of this delete view
     */
    public JComboBox<String> subcategoryComboBox() {
        return this.subcategoryComboBox;
    } //subcategoryComboBox

    /**
     * Returns the tag text field of this delete view.
     *
     * @return the tag text field of this delete view
     */
    public JTextField tagTextField() {
        return this.tagTextField;
    } //tagTextField

    /**
     * Returns the delete button of this delete view.
     *
     * @return the delete button of this delete view
     */
    public JButton deleteButton() {
        return this.deleteButton;
    } //deleteButton

    /**
     * Returns the clear button of this delete view.
     *
     * @return the clear button of this delete view
     */
    public JButton clearButton() {
        return this.clearButton;
    } //clearButton

    /**
     * Returns the panel of this delete view.
     *
     * @return the panel of this delete view
     */
    public JPanel panel() {
        return this.panel;
    } //Panel
}