package com.records.hs.view;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

/**
 * A menu view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 11, 2020
 */
public final class MenuView {
    /**
     * The save menu item of this menu view.
     */
    private final JMenuItem saveMenuItem;

    /**
     * The import menu item of this menu view.
     */
    private final JMenuItem importMenuItem;

    /**
     * The export menu item of this menu view.
     */
    private final JMenuItem exportMenuItem;

    /**
     * The add category menu item of this menu view.
     */
    private final JMenuItem addCategoryMenuItem;

    /**
     * The edit category menu item of this menu view.
     */
    private final JMenuItem editCategoryMenuItem;

    /**
     * The delete category menu item of this menu view.
     */
    private final JMenuItem deleteCategoryMenuItem;

    /**
     * The add subcategory menu item of this menu view.
     */
    private final JMenuItem addSubcategoryMenuItem;

    /**
     * The edit subcategory menu item of this menu view.
     */
    private final JMenuItem editSubcategoryMenuItem;

    /**
     * The delete subcategory menu item of this menu view.
     */
    private final JMenuItem deleteSubcategoryMenuItem;

    /**
     * The file menu of this menu view.
     */
    private final JMenu fileMenu;

    /**
     * The categories menu of this menu view.
     */
    private final JMenu categoriesMenu;

    /**
     * The subcategories menu of this menu view.
     */
    private final JMenu subcategoriesMenu;

    /**
     * The menu bar of this menu view.
     */
    private final JMenuBar menuBar;

    /**
     * Constructs a newly allocated {@code MenuView} object.
     */
    private MenuView() {
        String saveName = "Save";
        String importName = "Import from CSV";
        String exportName = "Export to CSV";
        String addName = "Add";
        String editName = "Edit";
        String deleteName = "Delete";
        String fileName = "File";
        String categoriesName = "Categories";
        String subcategoriesName = "Subcategories";

        this.saveMenuItem = new JMenuItem(saveName);
        this.importMenuItem = new JMenuItem(importName);
        this.exportMenuItem = new JMenuItem(exportName);
        this.addCategoryMenuItem = new JMenuItem(addName);
        this.editCategoryMenuItem = new JMenuItem(editName);
        this.deleteCategoryMenuItem = new JMenuItem(deleteName);
        this.addSubcategoryMenuItem = new JMenuItem(addName);
        this.editSubcategoryMenuItem = new JMenuItem(editName);
        this.deleteSubcategoryMenuItem = new JMenuItem(deleteName);
        this.fileMenu = new JMenu(fileName);
        this.categoriesMenu = new JMenu(categoriesName);
        this.subcategoriesMenu = new JMenu(subcategoriesName);
        this.menuBar = new JMenuBar();
    } //MenuView

    /**
     * Adds the components of this menu view to the menu bar of this menu view.
     */
    private void addComponentsToMenuBar() {
        this.fileMenu.add(this.saveMenuItem);

        this.fileMenu.add(new JSeparator());

        this.fileMenu.add(this.importMenuItem);

        this.fileMenu.add(new JSeparator());

        this.fileMenu.add(this.exportMenuItem);

        this.categoriesMenu.add(this.addCategoryMenuItem);

        this.categoriesMenu.add(new JSeparator());

        this.categoriesMenu.add(this.editCategoryMenuItem);

        this.categoriesMenu.add(new JSeparator());

        this.categoriesMenu.add(this.deleteCategoryMenuItem);

        this.subcategoriesMenu.add(this.addSubcategoryMenuItem);

        this.subcategoriesMenu.add(new JSeparator());

        this.subcategoriesMenu.add(this.editSubcategoryMenuItem);

        this.subcategoriesMenu.add(new JSeparator());

        this.subcategoriesMenu.add(this.deleteSubcategoryMenuItem);
    } //addComponentsToMenuBar

    /**
     * Returns a new {@code MenuView} object.
     *
     * @return a new {@code MenuView} object
     */
    public static MenuView newMenuView() {
        MenuView menuView = new MenuView();

        menuView.addComponentsToMenuBar();

        return menuView;
    } //newMenuView

    /**
     * Returns the save menu item of this menu view.
     *
     * @return the save menu item of this menu view
     */
    public JMenuItem saveMenuItem() {
        return this.saveMenuItem;
    } //saveMenuItem

    /**
     * Returns the import menu item of this menu view.
     *
     * @return the import menu item of this menu view
     */
    public JMenuItem importMenuItem() {
        return this.importMenuItem;
    } //importMenuItem

    /**
     * Returns the export menu item of this menu view.
     *
     * @return the export menu item of this menu view
     */
    public JMenuItem exportMenuItem() {
        return this.exportMenuItem;
    } //exportMenuItem

    /**
     * Returns the add category menu item of this menu view.
     *
     * @return the add category menu item of this menu view
     */
    public JMenuItem addCategoryMenuItem() {
        return this.addCategoryMenuItem;
    } //addCategoryMenuItem

    /**
     * Returns the edit category menu item of this menu view.
     *
     * @return the edit category menu item of this menu view
     */
    public JMenuItem editCategoryMenuItem() {
        return this.editCategoryMenuItem;
    } //editCategoryMenuItem

    /**
     * Returns the delete category menu item of this menu view.
     *
     * @return the delete category menu item of this menu view
     */
    public JMenuItem deleteCategoryMenuItem() {
        return this.deleteCategoryMenuItem;
    } //deleteCategoryMenuItem

    /**
     * Returns the add subcategory menu item of this menu view.
     *
     * @return the add subcategory menu item of this menu view
     */
    public JMenuItem addSubcategoryMenuItem() {
        return this.addSubcategoryMenuItem;
    } //addSubcategoryMenuItem

    /**
     * Returns the edit subcategory menu item of this menu view.
     *
     * @return the edit subcategory menu item of this menu view
     */
    public JMenuItem editSubcategoryMenuItem() {
        return this.editSubcategoryMenuItem;
    } //editSubcategoryMenuItem

    /**
     * Returns the delete subcategory menu item of this menu view.
     *
     * @return the delete subcategory menu item of this menu view
     */
    public JMenuItem deleteSubcategoryMenuItem() {
        return this.deleteSubcategoryMenuItem;
    } //deleteSubcategoryMenuItem

    /**
     * Returns the file menu of this menu view.
     *
     * @return the file menu of this menu view
     */
    public JMenu fileMenu() {
        return this.fileMenu;
    } //fileMenu

    /**
     * Returns the categories menu of this menu view.
     *
     * @return the categories menu of this menu view
     */
    public JMenu categoriesMenu() {
        return this.categoriesMenu;
    } //categoriesMenu

    /**
     * Returns the subcategories menu of this menu view.
     *
     * @return the subcategories menu of this menu view
     */
    public JMenu subcategoriesMenu() {
        return this.subcategoriesMenu;
    } //subcategoriesMenu

    /**
     * Returns the menu bar of this menu view.
     *
     * @return the menu bar of this menu view
     */
    public JMenuBar menuBar() {
        return this.menuBar;
    } //menuBar
}