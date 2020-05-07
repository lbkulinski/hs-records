package com.records.hs.view;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.Objects;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
     * The category text field of this add view.
     */
    private final JTextField categoryTextField;

    /**
     * The subcategory text field of this add view.
     */
    private final JTextField subcategoryTextField;

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
     * Constructs a newly allocated {@code HsRecordsAddView} object.
     */
    private AddView() {
        int length = 15;
        String submitName = "Submit";
        String clearName = "Clear";
        GridBagLayout layout = new GridBagLayout();

        this.idTextField = new JTextField(length);
        this.categoryTextField = new JTextField(length);
        this.subcategoryTextField = new JTextField(length);
        this.tagsTextField = new JTextField(length);
        this.submitButton = new JButton(submitName);
        this.clearButton = new JButton(clearName);
        this.panel = new JPanel(layout);
    } //HsRecordsAddView

    /**
     * Formats the specified text field with a titled border with the specified title.
     *
     * @param textField the text field to be used in the operation
     * @param title the title to be used in the operation
     * @throws NullPointerException if the specified text field or title is {@code null}
     */
    private void formatTextField(JTextField textField, String title) {
        Border lineBorder;
        TitledBorder titledBorder;

        Objects.requireNonNull(textField, "the specified text field is null");

        Objects.requireNonNull(title, "the specified label name is null");

        lineBorder = BorderFactory.createLineBorder(Color.BLACK);

        titledBorder = BorderFactory.createTitledBorder(lineBorder, title);

        titledBorder.setTitlePosition(TitledBorder.BELOW_TOP);

        textField.setBorder(titledBorder);
    } //formatTextField

    /**
     * Formats the components of this add view.
     */
    private void formatTextFields() {
        String idTitle = "ID";
        String categoryTitle = "Category";
        String subcategoryTitle = "Subcategory";
        String tagsTitle = "Tags";

        this.formatTextField(this.idTextField, idTitle);

        this.formatTextField(this.categoryTextField, categoryTitle);

        this.formatTextField(this.subcategoryTextField, subcategoryTitle);

        this.formatTextField(this.tagsTextField, tagsTitle);
    } //formatTextFields

    /**
     * Adds the specified component to this add view's panel using the specified constraints, row, and column.
     *
     * @param component the component to be used in the operation
     * @param constraints the constraints to be used in the operation
     * @param row the row to be used in the operation
     * @param column the column to be used in the operation
     * @throws NullPointerException if the specified component or constraints are {@code null}
     */
    private void addComponentToPanel(JComponent component, GridBagConstraints constraints, int row, int column) {
        int pixelCount = 5;

        Objects.requireNonNull(component, "the specified component is null");

        Objects.requireNonNull(constraints, "the specified constraints are null");

        constraints.gridx = row;

        constraints.gridy = column;

        constraints.insets = new Insets(pixelCount, pixelCount, pixelCount, pixelCount);

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.weightx = 1.0;

        constraints.weighty = 1.0;

        this.panel.add(component, constraints);
    } //addComponentToPanel

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

        this.addComponentToPanel(this.idTextField, constraints, row, idColumn);

        this.addComponentToPanel(this.categoryTextField, constraints, row, categoryColumn);

        this.addComponentToPanel(this.subcategoryTextField, constraints, row, subcategoryColumn);

        this.addComponentToPanel(this.tagsTextField, constraints, row, tagsColumn);

        this.addComponentToPanel(this.submitButton, constraints, row, submitColumn);

        this.addComponentToPanel(this.clearButton, constraints, row, clearColumn);
    } //addComponentsToPanel

    /**
     * Returns a new {@code HsRecordsAddView} object.
     *
     * @return a new {@code HsRecordsAddView} object
     */
    public static AddView newAddView() {
        AddView addView = new AddView();

        addView.formatTextFields();

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
     * Returns the category text field of this add view.
     *
     * @return the category text field of this add view
     */
    public JTextField getCategoryTextField() {
        return this.categoryTextField;
    } //getCategoryTextField

    /**
     * Returns the subcategory text field of this add view.
     *
     * @return the subcategory text field of this add view
     */
    public JTextField getSubcategoryTextField() {
        return this.subcategoryTextField;
    } //getSubcategoryTextField

    /**
     * Returns the tags text field of this add view.
     *
     * @return the tags text field of this add view
     */
    public JTextField getTagsTextField() {
        return this.tagsTextField;
    } //getTagsTextField

    /**
     * Returns the submit button of this add view.
     *
     * @return the submit button of this add view
     */
    public JButton getSubmitButton() {
        return this.submitButton;
    } //getSubmitButton

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