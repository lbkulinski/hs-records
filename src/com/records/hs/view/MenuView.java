package com.records.hs.view;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

/**
 * A menu view in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 21, 2020
 */
public final class MenuView {
    /**
     * The create directory menu item of this menu view.
     */
    private final JMenuItem createDirectoryMenuItem;

    /**
     * The open record menu item of this menu view.
     */
    private final JMenuItem openRecordMenuItem;

    /**
     * The open directory menu item of this menu view.
     */
    private final JMenuItem openDirectoryMenuItem;

    /**
     * The import menu item of this menu view.
     */
    private final JMenuItem importMenuItem;

    /**
     * The export menu item of this menu view.
     */
    private final JMenuItem exportMenuItem;

    /**
     * The save menu item of this menu view.
     */
    private final JMenuItem saveMenuItem;

    /**
     * The latest menu item of this menu view.
     */
    private final JMenuItem latestMenuItem;

    /**
     * The count menu item of this menu view.
     */
    private final JMenuItem countMenuItem;

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
     * The open menu of this menu view.
     */
    private final JMenu openMenu;

    /**
     * The display menu of this menu view.
     */
    private final JMenu displayMenu;

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
        String createDirectoryName = "Create Directory";
        String openRecordName = "Record";
        String openDirectoryName = "Directory";
        String saveName = "Save";
        String latestName = "Latest ID";
        String countName = "Record count";
        String importName = "Import from CSV";
        String exportName = "Export to CSV";
        String addName = "Add";
        String editName = "Edit";
        String deleteName = "Delete";
        String fileName = "File";
        String openName = "Open";
        String displayName = "Display";
        String categoriesName = "Categories";
        String subcategoriesName = "Subcategories";

        this.createDirectoryMenuItem = new JMenuItem(createDirectoryName);
        this.openRecordMenuItem = new JMenuItem(openRecordName);
        this.openDirectoryMenuItem = new JMenuItem(openDirectoryName);
        this.saveMenuItem = new JMenuItem(saveName);
        this.latestMenuItem = new JMenuItem(latestName);
        this.countMenuItem = new JMenuItem(countName);
        this.importMenuItem = new JMenuItem(importName);
        this.exportMenuItem = new JMenuItem(exportName);
        this.addCategoryMenuItem = new JMenuItem(addName);
        this.editCategoryMenuItem = new JMenuItem(editName);
        this.deleteCategoryMenuItem = new JMenuItem(deleteName);
        this.addSubcategoryMenuItem = new JMenuItem(addName);
        this.editSubcategoryMenuItem = new JMenuItem(editName);
        this.deleteSubcategoryMenuItem = new JMenuItem(deleteName);
        this.fileMenu = new JMenu(fileName);
        this.openMenu = new JMenu(openName);
        this.displayMenu = new JMenu(displayName);
        this.categoriesMenu = new JMenu(categoriesName);
        this.subcategoriesMenu = new JMenu(subcategoriesName);
        this.menuBar = new JMenuBar();
    } //MenuView

    /**
     * Adds the components of this menu view to the menu bar of this menu view.
     */
    private void addComponentsToMenuBar() {
        this.openMenu.add(this.openRecordMenuItem);

        this.openMenu.add(new JSeparator());

        this.openMenu.add(this.openDirectoryMenuItem);

        this.fileMenu.add(this.createDirectoryMenuItem);

        this.fileMenu.add(new JSeparator());

        this.fileMenu.add(this.openMenu);

        this.fileMenu.add(new JSeparator());

        this.fileMenu.add(this.importMenuItem);

        this.fileMenu.add(new JSeparator());

        this.fileMenu.add(this.exportMenuItem);

        this.fileMenu.add(new JSeparator());

        this.fileMenu.add(this.saveMenuItem);

        this.displayMenu.add(this.latestMenuItem);

        this.displayMenu.add(new JSeparator());

        this.displayMenu.add(this.countMenuItem);

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

        this.menuBar.add(this.fileMenu);

        this.menuBar.add(this.displayMenu);

        this.menuBar.add(this.categoriesMenu);

        this.menuBar.add(this.subcategoriesMenu);
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
     * Returns the create directory menu item of this menu view.
     *
     * @return the create directory menu item of this menu view
     */
    public JMenuItem getCreateDirectoryMenuItem() {
        return this.createDirectoryMenuItem;
    } //getCreateDirectoryMenuItem

    /**
     * Returns the open record menu item of this menu view.
     *
     * @return the open record menu item of this menu view
     */
    public JMenuItem getOpenRecordMenuItem() {
        return this.openRecordMenuItem;
    } //getOpenRecordMenuItem

    /**
     * Returns the open directory menu item of this menu view.
     *
     * @return the open directory menu item of this menu view
     */
    public JMenuItem getOpenDirectoryMenuItem() {
        return this.openDirectoryMenuItem;
    } //getOpenDirectoryMenuItem

    /**
     * Returns the import menu item of this menu view.
     *
     * @return the import menu item of this menu view
     */
    public JMenuItem getImportMenuItem() {
        return this.importMenuItem;
    } //getImportMenuItem

    /**
     * Returns the export menu item of this menu view.
     *
     * @return the export menu item of this menu view
     */
    public JMenuItem getExportMenuItem() {
        return this.exportMenuItem;
    } //getExportMenuItem

    /**
     * Returns the save menu item of this menu view.
     *
     * @return the save menu item of this menu view
     */
    public JMenuItem getSaveMenuItem() {
        return this.saveMenuItem;
    } //getSaveMenuItem

    /**
     * Returns the latest menu item of this menu view.
     *
     * @return the latest menu item of this menu view
     */
    public JMenuItem getLatestMenuItem() {
        return this.latestMenuItem;
    } //getLatestMenuItem

    /**
     * Returns the count menu item of this menu view.
     *
     * @return the count menu item of this menu view
     */
    public JMenuItem getCountMenuItem() {
        return this.countMenuItem;
    } //getCountMenuItem

    /**
     * Returns the add category menu item of this menu view.
     *
     * @return the add category menu item of this menu view
     */
    public JMenuItem getAddCategoryMenuItem() {
        return this.addCategoryMenuItem;
    } //getAddCategoryMenuItem

    /**
     * Returns the edit category menu item of this menu view.
     *
     * @return the edit category menu item of this menu view
     */
    public JMenuItem getEditCategoryMenuItem() {
        return this.editCategoryMenuItem;
    } //getEditCategoryMenuItem

    /**
     * Returns the delete category menu item of this menu view.
     *
     * @return the delete category menu item of this menu view
     */
    public JMenuItem getDeleteCategoryMenuItem() {
        return this.deleteCategoryMenuItem;
    } //getDeleteCategoryMenuItem

    /**
     * Returns the add subcategory menu item of this menu view.
     *
     * @return the add subcategory menu item of this menu view
     */
    public JMenuItem getAddSubcategoryMenuItem() {
        return this.addSubcategoryMenuItem;
    } //getAddSubcategoryMenuItem

    /**
     * Returns the edit subcategory menu item of this menu view.
     *
     * @return the edit subcategory menu item of this menu view
     */
    public JMenuItem getEditSubcategoryMenuItem() {
        return this.editSubcategoryMenuItem;
    } //getEditSubcategoryMenuItem

    /**
     * Returns the delete subcategory menu item of this menu view.
     *
     * @return the delete subcategory menu item of this menu view
     */
    public JMenuItem getDeleteSubcategoryMenuItem() {
        return this.deleteSubcategoryMenuItem;
    } //getDeleteSubcategoryMenuItem

    /**
     * Returns the file menu of this menu view.
     *
     * @return the file menu of this menu view
     */
    public JMenu getFileMenu() {
        return this.fileMenu;
    } //getFileMenu

    /**
     * Returns the open menu of this menu view.
     *
     * @return the open menu of this menu view
     */
    public JMenu getOpenMenu() {
        return this.openMenu;
    } //getOpenMenu

    /**
     * Returns the display menu of this menu view.
     *
     * @return the display menu of this menu view
     */
    public JMenu getDisplayMenu() {
        return this.displayMenu;
    } //getDisplayMenu

    /**
     * Returns the categories menu of this menu view.
     *
     * @return the categories menu of this menu view
     */
    public JMenu getCategoriesMenu() {
        return this.categoriesMenu;
    } //getCategoriesMenu

    /**
     * Returns the subcategories menu of this menu view.
     *
     * @return the subcategories menu of this menu view
     */
    public JMenu getSubcategoriesMenu() {
        return this.subcategoriesMenu;
    } //getSubcategoriesMenu

    /**
     * Returns the menu bar of this menu view.
     *
     * @return the menu bar of this menu view
     */
    public JMenuBar getMenuBar() {
        return this.menuBar;
    } //getMenuBar
}