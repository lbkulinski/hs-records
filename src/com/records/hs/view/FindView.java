package com.records.hs.view;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.records.hs.model.Type;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Arrays;

/**
 * A find view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 9, 2020
 */
public final class FindView {
    /**
     * The field combo box of this find view.
     */
    private final JComboBox<Field> fieldComboBox;

    /**
     * The ID text field of this find view.
     */
    private final JTextField idTextField;

    /**
     * The type combo box of this find view.
     */
    private final JComboBox<Type> typeComboBox;

    /**
     * The category combo box of this find view.
     */
    private final JComboBox<String> categoryComboBox;

    /**
     * The subcategory combo box of this find view.
     */
    private final JComboBox<String> subcategoryComboBox;

    /**
     * The tag text field of this find view.
     */
    private final JTextField tagTextField;

    /**
     * The results text pane of this find view.
     */
    private final JTextPane resultsTextPane;

    /**
     * The results scroll pane of this find view.
     */
    private final JScrollPane resultsScrollPane;

    /**
     * The find button of this find view.
     */
    private final JButton findButton;

    /**
     * The clear button of this find view.
     */
    private final JButton clearButton;

    /**
     * The panel of this find view.
     */
    private final JPanel panel;

    /**
     * Constructs a newly allocated {@code FindView} object.
     */
    private FindView() {
        int length = 15;
        String findName = "Find";
        String clearName = "Clear";
        GridBagLayout layout = new GridBagLayout();

        this.fieldComboBox = new JComboBox<>();
        this.idTextField = new JTextField(length);
        this.typeComboBox = new JComboBox<>();
        this.categoryComboBox = new JComboBox<>();
        this.subcategoryComboBox = new JComboBox<>();
        this.tagTextField = new JTextField(length);
        this.resultsTextPane = new JTextPane();
        this.resultsScrollPane = new JScrollPane(this.resultsTextPane);
        this.findButton = new JButton(findName);
        this.clearButton = new JButton(clearName);
        this.panel = new JPanel(layout);
    } //FindView

    /**
     * Formats the components of this find view.
     */
    private void formatComponents() {
        String fieldTitle = "Field";
        String idTitle = "ID";
        String typeTitle = "Type";
        String categoryTitle = "Category";
        String subcategoryTitle = "Subcategory";
        String tagTitle = "Tag";
        String resultsTitle = "Results";

        ViewUtilities.formatComponent(this.fieldComboBox, fieldTitle);

        ViewUtilities.formatComponent(this.idTextField, idTitle);

        ViewUtilities.formatComponent(this.typeComboBox, typeTitle);

        ViewUtilities.formatComponent(this.categoryComboBox, categoryTitle);

        ViewUtilities.formatComponent(this.subcategoryComboBox, subcategoryTitle);

        ViewUtilities.formatComponent(this.tagTextField, tagTitle);

        ViewUtilities.formatComponent(this.resultsScrollPane, resultsTitle);
    } //formatComponents

    /**
     * Adds the components of this find view to the panel of this find view.
     */
    public void addComponentsToPanel() {
        int fieldRow = 0;
        int column = 0;
        int clearRow = 1;

        ViewUtilities.addComponentToPanel(this.panel, this.fieldComboBox, fieldRow, column);

        ViewUtilities.addComponentToPanel(this.panel, this.clearButton, clearRow, column);
    } //addComponentsToPanel

    /**
     * Returns a new {@code FindView} object.
     *
     * @return a new {@code FindView} object
     */
    public static FindView newFindView() {
        Field[] fields;
        Type[] types;
        FindView findView = new FindView();

        fields = Field.values();

        types = Type.values();

        Arrays.stream(fields)
              .forEach(findView.fieldComboBox::addItem);

        findView.fieldComboBox.setSelectedIndex(-1);

        Arrays.stream(types)
              .forEach(findView.typeComboBox::addItem);

        findView.typeComboBox.setSelectedIndex(-1);

        findView.formatComponents();

        findView.addComponentsToPanel();

        return findView;
    } //FindView

    /**
     * Returns the field combo box of this find view.
     *
     * @return the field combo box of this find view
     */
    public JComboBox<Field> getFieldComboBox() {
        return this.fieldComboBox;
    } //getFieldComboBox

    /**
     * Returns the ID text field of this find view.
     *
     * @return the ID text field of this find view
     */
    public JTextField getIdTextField() {
        return this.idTextField;
    } //getIdTextField

    /**
     * Returns the type combo box of this find view.
     *
     * @return the type combo box of this find view
     */
    public JComboBox<Type> getTypeComboBox() {
        return this.typeComboBox;
    } //getTypeComboBox

    /**
     * Returns the category combo box of this find view.
     *
     * @return the category combo box of this find view
     */
    public JComboBox<String> getCategoryComboBox() {
        return this.categoryComboBox;
    } //getCategoryComboBox

    /**
     * Returns the subcategory combo box of this find view.
     *
     * @return the subcategory combo box of this find view
     */
    public JComboBox<String> getSubcategoryComboBox() {
        return this.subcategoryComboBox;
    } //getSubcategoryComboBox

    /**
     * Returns the tag text field of this find view.
     *
     * @return the tag text field of this find view
     */
    public JTextField getTagTextField() {
        return this.tagTextField;
    } //getTagTextField

    /**
     * Returns the results text pane of this find view.
     *
     * @return the results text pane of this find view
     */
    public JTextPane getResultsTextPane() {
        return this.resultsTextPane;
    } //getResultsTextPane

    /**
     * Returns the results scroll pane of this find view.
     *
     * @return the results scroll pane of this find view
     */
    public JScrollPane getResultsScrollPane() {
        return this.resultsScrollPane;
    } //getResultsScrollPane

    /**
     * Returns the find button of this find view.
     *
     * @return the find button of this find view
     */
    public JButton getFindButton() {
        return this.findButton;
    } //getFindButton

    /**
     * Returns the clear button of this find view.
     *
     * @return the clear button of this find view
     */
    public JButton getClearButton() {
        return this.clearButton;
    } //getClearButton

    /**
     * Returns the panel of this find view.
     *
     * @return the panel of this find view
     */
    public JPanel getPanel() {
        return this.panel;
    } //getPanel
}