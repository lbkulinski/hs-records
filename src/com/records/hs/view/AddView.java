package com.records.hs.view;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * An add view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 8, 2020
 */
public final class AddView {
    /**
     * The ID text field of this add view.
     */
    private final JTextField idTextField;

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
        String categoryTitle = "Category";
        String subcategoryTitle = "Subcategory";
        String tagsTitle = "Tags";

        ViewUtilities.formatComponent(this.idTextField, idTitle);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryTitle);

        ViewUtilities.formatComponent(this.subcategoryComboBox, subcategoryTitle);

        ViewUtilities.formatComponent(this.tagsTextField, tagsTitle);
    } //formatComponents

    /**
     * Adds the components of this add view to the panel of this add view.
     */
    private void addComponentsToPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        int idRow = 0;
        int column = 0;
        int categoryRow = 1;
        int subcategoryRow = 2;
        int tagsRow = 3;
        int addRow = 4;
        int clearRow = 5;

        ViewUtilities.addComponentToPanel(this.panel, this.idTextField, constraints, idRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.categoryComboBox, constraints, categoryRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.subcategoryComboBox, constraints, subcategoryRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.tagsTextField, constraints, tagsRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.addButton, constraints, addRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, constraints, clearRow, column);
    } //addComponentsToPanel

    /**
     * Returns a new {@code AddView} object.
     *
     * @return a new {@code AddView} object
     */
    public static AddView newAddView() {
        AddView addView = new AddView();

        addView.formatComponents();

        addView.addComponentsToPanel();

        return addView;
    } //newAddView

    /**
     * Returns the ID text field of this add view.
     *
     * @return the ID text field of this add view
     */
    public JTextField idTextField() {
        return this.idTextField;
    } //idTextField

    /**
     * Returns the category combo box of this add view.
     *
     * @return the category combo box of this add view
     */
    public JComboBox<String> categoryComboBox() {
        return this.categoryComboBox;
    } //categoryComboBox

    /**
     * Returns the subcategory combo box of this add view.
     *
     * @return the subcategory combo box of this add view
     */
    public JComboBox<String> subcategoryComboBox() {
        return this.subcategoryComboBox;
    } //subcategoryComboBox

    /**
     * Returns the tags text field of this add view.
     *
     * @return the tags text field of this add view
     */
    public JTextField tagsTextField() {
        return this.tagsTextField;
    } //tagsTextField

    /**
     * Returns the add button of this add view.
     *
     * @return the add button of this add view
     */
    public JButton addButton() {
        return this.addButton;
    } //addButton

    /**
     * Returns the clear button of this add view.
     *
     * @return the clear button of this add view
     */
    public JButton clearButton() {
        return this.clearButton;
    } //clearButton

    /**
     * Returns the panel of this add view.
     *
     * @return the panel of this add view
     */
    public JPanel panel() {
        return this.panel;
    } //panel
}