package com.records.hs.view;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.records.hs.model.Type;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Arrays;

/**
 * An add view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 18, 2020
 */
public final class AddView {
    /**
     * The ID text field of this add view.
     */
    private final JTextField idTextField;

    /**
     * The type combo box of this add view.
     */
    private final JComboBox<Type> typeComboBox;

    /**
     * The category combo box of this add view.
     */
    private final JComboBox<String> categoryComboBox;

    /**
     * The subcategory combo box of this add view.
     */
    private final JComboBox<String> subcategoryComboBox;

    /**
     * The tags text field of this add view.
     */
    private final JTextField tagsTextField;

    /**
     * The add button of this add view.
     */
    private final JButton addButton;

    /**
     * The clear button of this add view.
     */
    private final JButton clearButton;

    /**
     * The panel of this add view.
     */
    private final JPanel panel;

    /**
     * Constructs a newly allocated {@code AddView} object.
     */
    private AddView() {
        int length = 15;
        String addName = "Add";
        String clearName = "Clear";
        GridBagLayout layout = new GridBagLayout();

        this.idTextField = new JTextField(length);
        this.typeComboBox = new JComboBox<>();
        this.categoryComboBox = new JComboBox<>();
        this.subcategoryComboBox = new JComboBox<>();
        this.tagsTextField = new JTextField(length);
        this.addButton = new JButton(addName);
        this.clearButton = new JButton(clearName);
        this.panel = new JPanel(layout);
    } //AddView

    /**
     * Formats the components of this add view.
     */
    private void formatComponents() {
        String idTitle = "ID";
        String typeTitle = "Type";
        String categoryTitle = "Category";
        String subcategoryTitle = "Subcategory";
        String tagsTitle = "Tags";

        ViewUtilities.formatComponent(this.idTextField, idTitle);

        ViewUtilities.formatComponent(this.typeComboBox, typeTitle);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryTitle);

        ViewUtilities.formatComponent(this.subcategoryComboBox, subcategoryTitle);

        ViewUtilities.formatComponent(this.tagsTextField, tagsTitle);
    } //formatComponents

    /**
     * Adds the components of this add view to the panel of this add view.
     */
    private void addComponentsToPanel() {
        int idRow = 0;
        int column = 0;
        int typeRow = 1;
        int categoryRow = 2;
        int subcategoryRow = 3;
        int tagsRow = 4;
        int addRow = 5;
        int clearRow = 6;

        ViewUtilities.addComponentToPanel(this.panel, this.idTextField, idRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.typeComboBox, typeRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.categoryComboBox, categoryRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.subcategoryComboBox, subcategoryRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.tagsTextField, tagsRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.addButton, addRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, clearRow, column);
    } //addComponentsToPanel

    /**
     * Returns a new {@code AddView} object.
     *
     * @return a new {@code AddView} object
     */
    public static AddView newAddView() {
        Type[] types;
        AddView addView;

        types = Type.values();

        addView = new AddView();

        Arrays.stream(types)
              .forEach(addView.typeComboBox::addItem);

        addView.typeComboBox.setSelectedIndex(-1);

        addView.formatComponents();

        addView.addComponentsToPanel();

        return addView;
    } //newAddView

    /**
     * Returns the ID text field of this add view.
     *
     * @return the ID text field of this add view
     */
    public JTextField getIdTextField() {
        return this.idTextField;
    } //getIdTextField

    /**
     * Returns the type combo box of this add view.
     *
     * @return the type combo box of this add view
     */
    public JComboBox<Type> getTypeComboBox() {
        return this.typeComboBox;
    } //getTypeComboBox

    /**
     * Returns the category combo box of this add view.
     *
     * @return the category combo box of this add view
     */
    public JComboBox<String> getCategoryComboBox() {
        return this.categoryComboBox;
    } //getCategoryComboBox

    /**
     * Returns the subcategory combo box of this add view.
     *
     * @return the subcategory combo box of this add view
     */
    public JComboBox<String> getSubcategoryComboBox() {
        return this.subcategoryComboBox;
    } //getSubcategoryComboBox

    /**
     * Returns the tags text field of this add view.
     *
     * @return the tags text field of this add view
     */
    public JTextField getTagsTextField() {
        return this.tagsTextField;
    } //getTagsTextField

    /**
     * Returns the add button of this add view.
     *
     * @return the add button of this add view
     */
    public JButton getAddButton() {
        return this.addButton;
    } //getAddButton

    /**
     * Returns the clear button of this add view.
     *
     * @return the clear button of this add view
     */
    public JButton getClearButton() {
        return this.clearButton;
    } //getClearButton

    /**
     * Returns the panel of this add view.
     *
     * @return the panel of this add view
     */
    public JPanel getPanel() {
        return this.panel;
    } //getPanel
}