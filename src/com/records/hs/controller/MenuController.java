package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.MenuView;
import java.util.logging.Logger;
import java.util.Objects;
import java.util.Set;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.logging.Level;
import java.io.File;
import java.awt.Desktop;
import java.util.Optional;
import com.records.hs.model.Entry;

/**
 * A menu controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 19, 2020
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
     * @param category the category to be used in the operation
     * @return the subcategory input of this menu controller's menu view
     * @throws NullPointerException if the specified category is {@code null}
     */
    private String getSubcategoryInput(String category) {
        Set<String> subcategories;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        Object[] subcategoryArray;
        String subcategory;

        Objects.requireNonNull(category, "the specified category is null");

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

        category = category.toUpperCase();

        return category;
    } //getNewCategoryInput

    /**
     * Returns the new subcategory input of this menu controller's menu view.
     *
     * @param category the category to be used in the operation
     * @return the new subcategory input of this menu controller's menu view
     * @throws NullPointerException if the specified category is {@code null}
     */
    private String getNewSubcategoryInput(String category) {
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        String subcategory;
        Set<String> subcategories;

        Objects.requireNonNull(category, "the specified category is null");

        menuBar = this.menuView.getMenuBar();

        message = "Enter the new subcategory:";

        subcategory = JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE);

        subcategory = subcategory.toUpperCase();

        subcategories = this.model.getSubcategories(category);

        if (subcategories.contains(subcategory)) {
            message = "Error: The specified new subcategory already exists!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        subcategory = subcategory.toUpperCase();

        return subcategory;
    } //getNewSubcategoryInput

    /**
     * Creates a directory in the file system using the input of this menu controller's menu view.
     */
    private void createDirectory() {
        String category;
        String subcategory;
        Path path;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        int choice;

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput(category);

        if (subcategory == null) {
            return;
        } //end if

        path = Path.of(category, subcategory);

        menuBar = this.menuView.getMenuBar();

        if (Files.exists(path)) {
            message = "Error: The directory already exists!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: The directory could not be created! Please contact support!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end try catch

        message = "The directory was successfully created! Would you like to open it?";

        choice = JOptionPane.showConfirmDialog(menuBar, message, title, JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            Desktop desktop;
            File file;

            if (!Desktop.isDesktopSupported()) {
                message = "Error: The directory could not be opened! Please contact support!";

                JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

                return;
            } //end if

            desktop = Desktop.getDesktop();

            file = path.toFile();

            try {
                desktop.open(file);
            } catch (IOException e) {
                String exceptionMessage = e.getMessage();

                this.logger.log(Level.INFO, exceptionMessage, e);

                message = "Error: The directory could not be opened! Please contact support!";

                JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);
            } //end try catch
        } //end if
    } //createDirectory

    /**
     * Opens a record using the input of this menu controller's menu view.
     */
    private void openRecord() {
        String id;
        Optional<Entry> optional;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        Entry entry;
        String category;
        String subcategory;
        String fileName;
        Path path;
        Desktop desktop;
        File file;

        id = this.getIdInput();

        optional = this.model.findEntryWithId(id);

        menuBar = this.menuView.getMenuBar();

        if (optional.isEmpty()) {
            message = "Error: The record does not exist!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        entry = optional.get();

        category = entry.getCategory();

        subcategory = entry.getSubcategory();

        fileName = id;

        fileName += ".png";

        path = Path.of(category, subcategory, fileName);

        if (Files.notExists(path)) {
            message = "Error: The file associated with the record could not be found!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        if (!Desktop.isDesktopSupported()) {
            message = "Error: The file associated with the record could not be opened! Please contact support!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        desktop = Desktop.getDesktop();

        file = path.toFile();

        try {
            desktop.open(file);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: The file associated with the record could not be opened! Please contact support!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);
        } //end try catch
    } //openRecord

    /**
     * Opens a directory using the input of this menu controller's menu view.
     */
    private void openDirectory() {
        String category;
        String subcategory;
        Path path;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        Desktop desktop;
        File file;

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput(category);

        if (subcategory == null) {
            return;
        } //end if

        path = Path.of(category, subcategory);

        menuBar = this.menuView.getMenuBar();

        if (Files.notExists(path)) {
            message = "Error: The directory could not be found!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        if (!Desktop.isDesktopSupported()) {
            message = "Error: The directory could not be opened! Please contact support!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        desktop = Desktop.getDesktop();

        file = path.toFile();

        try {
            desktop.open(file);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: The directory could not be opened! Please contact support!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);
        } //end try catch
    } //openDirectory
}