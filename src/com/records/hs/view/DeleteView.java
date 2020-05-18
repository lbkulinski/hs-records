package com.records.hs.view;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.records.hs.model.Type;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Arrays;

/**
 * A delete view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 18, 2020
 */
public final class DeleteView {
    /**
     * The field combo box of this delete view.
     */
    private final JComboBox<Field> fieldComboBox;

    /**
     * The ID text field of this delete view.
     */
    private final JTextField idTextField;

    /**
     * The type combo box of this delete view.
     */
    private final JComboBox<Type> typeComboBox;

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
        this.typeComboBox = new JComboBox<>();
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
        String typeTitle = "Type";
        String categoryTitle = "Category";
        String subcategoryTitle = "Subcategory";
        String tagTitle = "Tag";

        ViewUtilities.formatComponent(this.fieldComboBox, fieldTitle);

        ViewUtilities.formatComponent(this.idTextField, idTitle);

        ViewUtilities.formatComponent(this.typeComboBox, typeTitle);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryTitle);

        ViewUtilities.formatComponent(this.subcategoryComboBox, subcategoryTitle);

        ViewUtilities.formatComponent(this.tagTextField, tagTitle);
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
        Field[] fields;
        Type[] types;
        DeleteView deleteView = new DeleteView();

        fields = Field.values();

        types = Type.values();

        Arrays.stream(fields)
              .forEach(deleteView.fieldComboBox::addItem);

        deleteView.fieldComboBox.setSelectedIndex(-1);

        Arrays.stream(types)
              .forEach(deleteView.typeComboBox::addItem);

        deleteView.typeComboBox.setSelectedIndex(-1);

        deleteView.formatComponents();

        deleteView.addComponentsToPanel();

        return deleteView;
    } //newDeleteView

    /**
     * Returns the field combo box of this delete view.
     *
     * @return the field combo box of this delete view
     */
    public JComboBox<Field> getFieldComboBox() {
        return this.fieldComboBox;
    } //getFieldComboBox

    /**
     * Returns the ID text field of this delete view.
     *
     * @return the ID text field of this delete view
     */
    public JTextField getIdTextField() {
        return this.idTextField;
    } //getIdTextField

    /**
     * Returns the type combo box of this delete view.
     *
     * @return the type combo box of this delete view
     */
    public JComboBox<Type> getTypeComboBox() {
        return this.typeComboBox;
    } //getTypeComboBox

    /**
     * Returns the category combo box of this delete view.
     *
     * @return the category combo box of this delete view
     */
    public JComboBox<String> getCategoryComboBox() {
        return this.categoryComboBox;
    } //getCategoryComboBox

    /**
     * Returns the subcategory combo box of this delete view.
     *
     * @return the subcategory combo box of this delete view
     */
    public JComboBox<String> getSubcategoryComboBox() {
        return this.subcategoryComboBox;
    } //getSubcategoryComboBox

    /**
     * Returns the tag text field of this delete view.
     *
     * @return the tag text field of this delete view
     */
    public JTextField getTagTextField() {
        return this.tagTextField;
    } //getTagTextField

    /**
     * Returns the delete button of this delete view.
     *
     * @return the delete button of this delete view
     */
    public JButton getDeleteButton() {
        return this.deleteButton;
    } //getDeleteButton

    /**
     * Returns the clear button of this delete view.
     *
     * @return the clear button of this delete view
     */
    public JButton getClearButton() {
        return this.clearButton;
    } //getClearButton

    /**
     * Returns the panel of this delete view.
     *
     * @return the panel of this delete view
     */
    public JPanel getPanel() {
        return this.panel;
    } //getPanel
}