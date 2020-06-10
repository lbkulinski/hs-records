package com.records.hs.view;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import java.awt.GridBagConstraints;

/**
 * A view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version June 9, 2020
 */
public final class View {
    /**
     * The menu view of this view.
     */
    private final MenuView menuView;

    /**
     * The add view of this view.
     */
    private final AddView addView;

    /**
     * The edit view of this view.
     */
    private final EditView editView;

    /**
     * The delete view of this view.
     */
    private final DeleteView deleteView;

    /**
     * The find view of this view.
     */
    private final FindView findView;

    /**
     * The tabbed pane of this view.
     */
    private final JTabbedPane tabbedPane;

    /**
     * The panel of this view.
     */
    private final JPanel panel;

    /**
     * The frame of this view.
     */
    private final JFrame frame;

    /**
     * Constructs a newly allocated {@code View} object.
     */
    private View() {
        GridBagLayout layout = new GridBagLayout();

        this.menuView = MenuView.newMenuView();
        this.addView = AddView.newAddView();
        this.editView = EditView.newEditView();
        this.deleteView = DeleteView.newDeleteView();
        this.findView = FindView.newFindView();
        this.tabbedPane = new JTabbedPane();
        this.panel = new JPanel(layout);
        this.frame = new JFrame();
    } //View

    /**
     * Adds the components of this view to the frame of this view.
     */
    private void addComponentsToFrame() {
        String addTitle = "Add";
        JPanel addPanel = this.addView.getPanel();
        String editTitle = "Edit";
        JPanel editPanel = this.editView.getPanel();
        String deleteTitle = "Delete";
        JPanel deletePanel = this.deleteView.getPanel();
        String findTitle = "Find";
        JPanel findPanel = this.findView.getPanel();
        GridBagConstraints constraints = new GridBagConstraints();
        JMenuBar menuBar = this.menuView.getMenuBar();

        this.tabbedPane.addTab(addTitle, addPanel);

        this.tabbedPane.addTab(editTitle, editPanel);

        this.tabbedPane.addTab(deleteTitle, deletePanel);

        this.tabbedPane.addTab(findTitle, findPanel);

        constraints.gridx = 0;

        constraints.gridy = 0;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 1.0;

        constraints.weighty = 1.0;

        this.panel.add(this.tabbedPane, constraints);

        this.frame.setJMenuBar(menuBar);

        this.frame.setContentPane(this.panel);
    } //addComponentsToFrame

    /**
     * Returns a new {@code View} object.
     *
     * @return a new {@code View} object
     */
    public static View newView() {
        View view = new View();

        view.addComponentsToFrame();

        return view;
    } //newView

    /**
     * Returns the menu view of this view.
     *
     * @return the menu view of this view
     */
    public MenuView getMenuView() {
        return this.menuView;
    } //getMenuView

    /**
     * Returns the add view of this view.
     *
     * @return the add view of this view
     */
    public AddView getAddView() {
        return this.addView;
    } //getAddView

    /**
     * Returns the edit view of this view.
     *
     * @return the edit view of this view
     */
    public EditView getEditView() {
        return this.editView;
    } //getEditView

    /**
     * Returns the delete view of this view.
     *
     * @return the delete view of this view
     */
    public DeleteView getDeleteView() {
        return this.deleteView;
    } //getDeleteView

    /**
     * Returns the find view of this view.
     *
     * @return the find view of this view
     */
    public FindView getFindView() {
        return this.findView;
    } //getFindView

    /**
     * Returns the tabbed pane of this view.
     *
     * @return the tabbed pane of this view
     */
    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    } //getTabbedPane

    /**
     * Returns the panel of this view.
     *
     * @return the panel of this view
     */
    public JPanel getPanel() {
        return this.panel;
    } //getPanel

    /**
     * Returns the frame of this view.
     *
     * @return the frame of this view
     */
    public JFrame getFrame() {
        return this.frame;
    } //getFrame
}