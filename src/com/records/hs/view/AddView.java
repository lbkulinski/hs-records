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
 * @version May 7, 2020
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
     * The submit button of this add view.
     */
    private final JButton submitButton;

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
        String submitName = "Submit";
        String clearName = "Clear";
        GridBagLayout layout = new GridBagLayout();

        this.idTextField = new JTextField(length);
        this.categoryComboBox = new JComboBox<>();
        this.subcategoryComboBox = new JComboBox<>();
        this.tagsTextField = new JTextField(length);
        this.submitButton = new JButton(submitName);
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
        int row = 0;
        int idColumn = 0;
        int categoryColumn = 1;
        int subcategoryColumn = 2;
        int tagsColumn = 3;
        int submitColumn = 4;
        int clearColumn = 5;

        ViewUtilities.addComponentToPanel(this.panel, this.idTextField, constraints, row, idColumn);

        ViewUtilities.addComponentToPanel(this.panel, this.categoryComboBox, constraints, row, categoryColumn);

        ViewUtilities.addComponentToPanel(this.panel, this.subcategoryComboBox, constraints, row, subcategoryColumn);

        ViewUtilities.addComponentToPanel(this.panel, this.tagsTextField, constraints, row, tagsColumn);

        ViewUtilities.addComponentToPanel(this.panel, this.submitButton, constraints, row, submitColumn);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, constraints, row, clearColumn);
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
     * Returns the submit button of this add view.
     *
     * @return the submit button of this add view
     */
    public JButton submitButton() {
        return this.submitButton;
    } //submitButton

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