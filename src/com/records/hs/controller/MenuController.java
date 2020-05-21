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
import com.records.hs.model.Type;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A menu controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 20, 2020
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
     * The add controller of this menu controller.
     */
    private final AddController addController;

    /**
     * The logger of this menu controller.
     */
    private final Logger logger;

    /**
     * Constructs a newly allocated {@code MenuController} object with the specified model and menu view.
     *
     * @param model the model to be used in construction
     * @param menuView the menu view to be used in construction
     * @param addController the add controller to be used in construction
     * @throws NullPointerException if the specified model, menu view, or add controller is {@code null}
     */
    private MenuController(Model model, MenuView menuView, AddController addController) {
        Objects.requireNonNull(model, "the specified model is null");

        Objects.requireNonNull(menuView, "the specified menu view is null");

        Objects.requireNonNull(addController, "the specified add controller is null");

        this.model = model;
        this.menuView = menuView;
        this.addController = addController;
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

        menuBar = this.menuView.getMenuBar();

        message = "Enter the new category:";

        category = JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE);

        category = category.toUpperCase();

        if (this.model.containsCategory(category)) {
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

        Objects.requireNonNull(category, "the specified category is null");

        menuBar = this.menuView.getMenuBar();

        message = "Enter the new subcategory:";

        subcategory = JOptionPane.showInputDialog(menuBar, message, title, JOptionPane.QUESTION_MESSAGE);

        subcategory = subcategory.toUpperCase();

        if (this.model.containsSubcategory(category, subcategory)) {
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

    /**
     * Parses the specified {@code String} as an {@code Entry} object. If the specified {@code String} is not properly
     * formatted, {@code null} is returned.
     *
     * @param string the {@code String} to be used in the operation
     * @return the specified {@code String} as an {@code Entry} object.
     * @throws NullPointerException if the specified {@code String} is {@code null}
     */
    private Entry parseEntry(String string) {
        String[] parts;
        String delimiter = ",";
        int limit = 5;
        String id;
        String typeString;
        String category;
        String subcategory;
        String tagsString;
        Optional<Entry> optional;
        JMenuBar menuBar;
        String message;
        String title = "HS Records";
        Type type;
        String[] tagArray;
        Set<String> tags;
        Entry entry;

        Objects.requireNonNull(string, "the specified String is null");

        parts = string.split(delimiter, limit);

        if (parts.length != limit) {
            return null;
        } //end if

        id = parts[0];

        typeString = parts[1];

        category = parts[2];

        subcategory = parts[3];

        tagsString = parts[4];

        optional = this.model.findEntryWithId(id);

        menuBar = this.menuView.getMenuBar();

        if (optional.isPresent()) {
            message = "Error: An entry in the CSV file has an ID that already exists!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        typeString = typeString.toUpperCase();

        try {
            type = Type.valueOf(typeString);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: An entry in the CSV file has a type that is invalid!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end try catch

        tagArray = tagsString.split(delimiter);

        tags = Arrays.stream(tagArray)
                     .map(String::trim)
                     .collect(Collectors.toUnmodifiableSet());

        entry = new Entry(id, type, category, subcategory, tags);

        return entry;
    } //parseEntry

    /**
     * Imports a CSV file using the input of this menu controller's menu view.
     */
    private void importFromCsv() {
        String dialogTitle = "HS Records";
        FileNameExtensionFilter filter;
        JMenuBar menuBar;
        JFileChooser fileChooser;
        int state;
        File file;
        Path path;
        List<String> lines;
        String message;
        String title = "HS Records";
        List<Entry> entries;
        boolean added;
        String category;
        String subcategory;

        filter = new FileNameExtensionFilter("CSV Files", "csv");

        menuBar = this.menuView.getMenuBar();

        fileChooser = new JFileChooser();

        fileChooser.setDialogTitle(dialogTitle);

        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.setFileFilter(filter);

        state = fileChooser.showOpenDialog(menuBar);

        if (state != JFileChooser.APPROVE_OPTION) {
            return;
        } //end if

        file = fileChooser.getSelectedFile();

        path = file.toPath();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: The CSV file could not be imported! Please contact support!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end try catch

        entries = lines.stream()
                       .map(this::parseEntry)
                       .collect(Collectors.toList());

        if (entries.contains(null)) {
            message = "The CSV file was could not be imported, as it contains improperly formatted entries!";

            JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if

        for (Entry entry : entries) {
            added = this.model.addEntry(entry);

            category = entry.getCategory();

            subcategory = entry.getSubcategory();

            if (!added) {
                message = "Error: The CSV file could not be fully imported! Please contact support!";

                JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.ERROR_MESSAGE);

                return;
            } //end if

            if (!this.model.containsCategory(category)) {
                this.model.addCategory(category);
            } //end if

            if (!this.model.containsSubcategory(category, subcategory)) {
                this.model.addSubcategory(category, subcategory);
            } //end if
        } //end for

        message = "The CSV file was successfully imported!";

        JOptionPane.showMessageDialog(menuBar, message, title, JOptionPane.INFORMATION_MESSAGE);
    } //importFromCsv

    /**
     * Returns the conversion of the specified entry to a {@code String}. The returned {@code String} will be of the
     * form of a line in a CSV file.
     *
     * @param entry the entry to be used in the operation
     * @return the conversion of the specified entry to a {@code String}
     */
    private String convertToString(Entry entry) {
        String id;
        Type type;
        String category;
        String subcategory;
        Set<String> tags;
        StringBuilder stringBuilder;
        String delimiter = ",";
        int length;
        int lastIndex;

        Objects.requireNonNull(entry, "the specified entry is null");

        id = entry.getId();

        type = entry.getType();

        category = entry.getCategory();

        subcategory = entry.getSubcategory();

        tags = entry.getTags();

        stringBuilder = new StringBuilder();

        stringBuilder.append(id);

        stringBuilder.append(delimiter);

        stringBuilder.append(type);

        stringBuilder.append(delimiter);

        stringBuilder.append(category);

        stringBuilder.append(delimiter);

        stringBuilder.append(subcategory);

        stringBuilder.append(delimiter);

        for (String tag : tags) {
            stringBuilder.append(tag);

            stringBuilder.append(delimiter);
        } //end for

        length = stringBuilder.length();

        lastIndex = length - 1;

        stringBuilder.deleteCharAt(lastIndex);

        return stringBuilder.toString();
    } //convertToString
}