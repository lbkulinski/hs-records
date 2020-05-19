package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.MenuView;
import java.util.logging.Logger;
import java.util.Objects;
import java.util.Set;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

/**
 * A menu controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 18, 2020
 */
public final class MenuController {
    /**
     * The model of this menu controller.
     */
    private final Model model;

    /**
     * The menu view of this menu controller.
     */
    private final MenuView menuView;

    /**
     * The logger of this menu controller.
     */
    private final Logger logger;

    /**
     * Constructs a newly allocated {@code MenuController} object with the specified model and menu view.
     *
     * @param model the model to be used in construction
     * @param menuView the menu view to be used in construction
     * @throws NullPointerException if the specified model or menu view is {@code null}
     */
    private MenuController(Model model, MenuView menuView) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(menuView, "the specified menu view is null");

        this.model = model;
        this.menuView = menuView;
        this.logger = Logger.getGlobal();
    } //MenuController

    /**
     * Returns the category input of this menu controller's menu view.
     *
     * @return the category input of this menu controller's menu view
     */
    private String getCategoryInput() {
        Set<String> categories;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        Object[] categoryArray;
        String category;

        categories = this.model.getCategories();

        menuBar = this.menuView.getMenuBar();

        if (categories.isEmpty()) {
            message = "Error: No categories have been previously added!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        message = "Enter the category:";

        categoryArray = categories.toArray();

        category = (String) JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE, null,
                                                        categoryArray, null);

        category = category.toUpperCase();

        return category;
    } //getCategoryInput

    /**
     * Returns the subcategory input of this menu controller's menu view.
     *
     * @return the subcategory input of this menu controller's menu view
     */
    private String getSubcategoryInput() {
        String category;
        Set<String> subcategories;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        Object[] subcategoryArray;
        String subcategory;

        category = this.getCategoryInput();

        subcategories = this.model.getSubcategories(category);

        menuBar = this.menuView.getMenuBar();

        if (subcategories.isEmpty()) {
            message = "Error: No subcategories have been previously added!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        message = "Enter the subcategory:";

        subcategoryArray = subcategories.toArray();

        subcategory = (String) JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE, null,
                                                           subcategoryArray, null);

        subcategory = subcategory.toUpperCase();

        return subcategory;
    } //getSubcategoryInput

    /**
     * Returns the ID input of this menu controller's menu view.
     *
     * @return the ID input of this menu controller's menu view
     */
    private String getIdInput() {
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        String id;

        menuBar = this.menuView.getMenuBar();

        message = "Enter the ID:";

        id = JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE);

        id = id.toUpperCase();

        return id;
    } //getIdInput

    /**
     * Returns the new category input of this menu controller's menu view.
     *
     * @return the new category input of this menu controller's menu view
     */
    private String getNewCategoryInput() {
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        String category;
        Set<String> categories;

        menuBar = this.menuView.getMenuBar();

        message = "Enter the new category:";

        category = JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE);

        category = category.toUpperCase();

        categories = this.model.getCategories();

        if (categories.contains(category)) {
            message = "Error: The specified new category already exists!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        return category;
    } //getNewCategoryInput
}