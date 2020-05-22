package com.records.hs.controller;

import com.records.hs.model.Model;
import com.records.hs.view.MenuView;
import java.util.logging.Logger;
import java.util.Objects;
import java.util.Set;
import javax.swing.JMenuBar;
import java.awt.Window;
import javax.swing.SwingUtilities;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JMenuItem;

/**
 * A menu controller in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 22, 2020
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
        Window window;
        String message;
        String title = "HS Records";
        Object[] categoryArray;
        String category;

        categories = this.model.getCategories();

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (categories.isEmpty()) {
            message = "Error: No categories have been previously added!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        message = "Enter the category:";

        categoryArray = categories.toArray();

        category = (String) JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE, null,
                                                        categoryArray, null);

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
        Window window;
        String message;
        String title = "HS Records";
        Object[] subcategoryArray;
        String subcategory;

        Objects.requireNonNull(category, "the specified category is null");

        subcategories = this.model.getSubcategories(category);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (subcategories.isEmpty()) {
            message = "Error: No subcategories have been previously added!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        message = "Enter the subcategory:";

        subcategoryArray = subcategories.toArray();

        subcategory = (String) JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE, null,
                                                           subcategoryArray, null);

        return subcategory;
    } //getSubcategoryInput

    /**
     * Returns the ID input of this menu controller's menu view.
     *
     * @return the ID input of this menu controller's menu view
     */
    private String getIdInput() {
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";
        String id;

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        message = "Enter the ID:";

        id = JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE);

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
        Window window;
        String message;
        String title = "HS Records";
        String category;
        String comma = ",";

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        message = "Enter the new category:";

        category = JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE);

        if (category == null) {
            return null;
        } else if (category.isBlank()) {
            message = "Error: The specified category is blank!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } else if (category.contains(comma)) {
            message = "Error: The specified category contains a comma!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } else if (this.model.containsCategory(category)) {
            message = "Error: The specified category already exists!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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
        Window window;
        String message;
        String title = "HS Records";
        String subcategory;
        String comma = ",";

        Objects.requireNonNull(category, "the specified category is null");

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        message = "Enter the new subcategory:";

        subcategory = JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE);

        if (subcategory == null) {
            return null;
        } else if (subcategory.isBlank()) {
            message = "Error: The specified subcategory is blank!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } else if (subcategory.contains(comma)) {
            message = "Error: The specified subcategory contains a comma!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } else if (this.model.containsSubcategory(category, subcategory)) {
            message = "Error: The specified subcategory already exists!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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
        Window window;
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

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (Files.exists(path)) {
            message = "Error: The directory already exists!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: The directory could not be created! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end try catch

        message = "The directory was successfully created! Would you like to open it?";

        choice = JOptionPane.showConfirmDialog(window, message, title, JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            Desktop desktop;
            File file;

            if (!Desktop.isDesktopSupported()) {
                message = "Error: The directory could not be opened! Please contact support!";

                JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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

                JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
            } //end try catch
        } //end if
    } //createDirectory

    /**
     * Opens a record using the input of this menu controller's menu view.
     */
    private void openRecord() {
        int entryCount;
        JMenuBar menuBar;
        Window window;
        String id;
        Optional<Entry> optional;
        String message;
        String title = "HS Records";
        Entry entry;
        String category;
        String subcategory;
        String fileName;
        Path path;
        Desktop desktop;
        File file;

        entryCount = this.model.getEntryCount();

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (entryCount == 0) {
            message = "Error: No records have been previously added!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        id = this.getIdInput();

        optional = this.model.findEntryWithId(id);

        if (optional.isEmpty()) {
            message = "Error: The record does not exist!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        if (!Desktop.isDesktopSupported()) {
            message = "Error: The file associated with the record could not be opened! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
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
        Window window;
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

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (Files.notExists(path)) {
            message = "Error: The directory could not be found!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        if (!Desktop.isDesktopSupported()) {
            message = "Error: The directory could not be opened! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
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
        Window window;
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

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (optional.isPresent()) {
            message = "Error: An entry in the CSV file has an ID that already exists!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return null;
        } //end if

        typeString = typeString.toUpperCase();

        try {
            type = Type.valueOf(typeString);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: An entry in the CSV file has a type that is invalid!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

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
        String description = "CSV Files";
        String extension = "csv";
        String dialogTitle = "HS Records";
        FileNameExtensionFilter filter;
        JMenuBar menuBar;
        Window window;
        JFileChooser fileChooser;
        int state;
        File file;
        Path path;
        List<String> lines;
        String message;
        String title = "HS Records";
        Entry parsedEntry;
        Set<Entry> entries;
        String category;
        String subcategory;
        boolean added;

        filter = new FileNameExtensionFilter(description, extension);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

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

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end try catch

        entries = new LinkedHashSet<>();

        for (String line : lines) {
            parsedEntry = this.parseEntry(line);

            if (parsedEntry == null) {
                return;
            } //end if

            entries.add(parsedEntry);
        } //end if

        for (Entry entry : entries) {
            category = entry.getCategory();

            subcategory = entry.getSubcategory();

            if (!this.model.containsCategory(category)) {
                added = this.model.addCategory(category);

                if (!added) {
                    message = "Error: The CSV file could not be fully imported! Please contact support!";

                    JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

                    return;
                } //end if
            } //end if

            if (!this.model.containsSubcategory(category, subcategory)) {
                added = this.model.addSubcategory(category, subcategory);

                if (!added) {
                    message = "Error: The CSV file could not be fully imported! Please contact support!";

                    JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

                    return;
                } //end if
            } //end if

            added = this.model.addEntry(entry);

            if (!added) {
                message = "Error: The CSV file could not be fully imported! Please contact support!";

                JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

                return;
            } //end if
        } //end for

        this.addController.clearFields();

        message = "The CSV file was successfully imported!";

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
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

    /**
     * Exports a CSV file using the input of this menu controller's menu view.
     */
    private void exportToCsv() {
        String description = "CSV Files";
        String extension = "csv";
        String dialogTitle = "HS Records";
        FileNameExtensionFilter filter;
        JMenuBar menuBar;
        Window window;
        JFileChooser fileChooser;
        int state;
        File file;
        String name;
        String patternString = "(.)*.csv";
        String message;
        String title = "HS Records";
        Pattern pattern;
        Matcher matcher;
        Path path;
        Set<Entry> entries;
        Set<String> strings;

        filter = new FileNameExtensionFilter(description, extension);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        fileChooser = new JFileChooser();

        fileChooser.setDialogTitle(dialogTitle);

        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.setFileFilter(filter);

        state = fileChooser.showSaveDialog(menuBar);

        if (state != JFileChooser.APPROVE_OPTION) {
            return;
        } //end if

        file = fileChooser.getSelectedFile();

        name = file.getName();

        pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);

        matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            message = "Error: The selected file is not a CSV file!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end if

        path = file.toPath();

        entries = this.model.getEntries();

        strings = entries.stream()
                         .map(this::convertToString)
                         .collect(Collectors.toUnmodifiableSet());

        try {
            Files.write(path, strings);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            this.logger.log(Level.INFO, exceptionMessage, e);

            message = "Error: The CSV file could not be exported! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);

            return;
        } //end try catch

        message = "The CSV file was successfully exported!";

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    } //exportToCsv

    /**
     * Saves the model of this menu controller to a file.
     */
    private void save() {
        boolean saved;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        saved = ControllerUtilities.writeModelToFile(this.model);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (saved) {
            message = "The save was successful!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The save was unsuccessful! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //save

    /**
     * Exits the program.
     */
    private void exit() {
        boolean saved;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        saved = ControllerUtilities.writeModelToFile(this.model);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (saved) {
            int success = 0;

            window.dispose();

            System.exit(success);
        } else {
            message = "Error: The save was unsuccessful! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //exit

    /**
     * Shows the latest ID of the model of this menu controller.
     */
    private void showLatestId() {
        String latestId;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        latestId = this.model.getLatestId();

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (latestId == null) {
            message = "Error: No entries have been added!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } else {
            String format = "Latest ID: %s";

            message = String.format(format, latestId);

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } //end if
    } //showLatestId

    /**
     * Shows the entry count of the model of this menu controller.
     */
    private void showEntryCount() {
        int entryCount;
        JMenuBar menuBar;
        Window window;
        String message;
        String format = "Record count: %d";
        String title = "HS Records";

        entryCount = this.model.getEntryCount();

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        message = String.format(format, entryCount);

        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    } //showEntryCount

    /**
     * Adds a category to the model of this menu controller using the input of this menu controller's menu view.
     */
    private void addCategory() {
        String newCategory;
        boolean added;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        newCategory = this.getNewCategoryInput();

        if (newCategory == null) {
            return;
        } //end if

        added = this.model.addCategory(newCategory);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (added) {
            this.addController.fillCategoryComboBox();

            message = "The category was successfully added!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The category could not be added! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //addCategory

    /**
     * Edits a category to the model of this menu controller using the input of this menu controller's menu view.
     */
    private void editCategory() {
        String category;
        String newCategory;
        boolean edited;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        newCategory = this.getNewCategoryInput();

        if (newCategory == null) {
            return;
        } //end if

        edited = this.model.editCategory(category, newCategory);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (edited) {
            this.addController.fillCategoryComboBox();

            message = "The category was successfully edited!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The category could not be edited! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //editCategory

    /**
     * Deletes a category to the model of this menu controller using the input of this menu controller's menu view.
     */
    private void deleteCategory() {
        String category;
        boolean deleted;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        deleted = this.model.deleteCategory(category);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (deleted) {
            this.addController.fillCategoryComboBox();

            message = "The category was successfully deleted!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The category could not be deleted! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //deleteCategory

    /**
     * Adds a subcategory to the model of this menu controller using the input of this menu controller's menu view.
     */
    private void addSubcategory() {
        String category;
        String newSubcategory;
        boolean added;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        newSubcategory = this.getNewSubcategoryInput(category);

        if (newSubcategory == null) {
            return;
        } //end if

        added = this.model.addSubcategory(category, newSubcategory);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (added) {
            this.addController.fillSubcategoryComboBox();

            message = "The subcategory was successfully added!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The subcategory could not be added! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //addSubcategory

    /**
     * Edits a subcategory to the model of this menu controller using the input of this menu controller's menu view.
     */
    private void editSubcategory() {
        String category;
        String subcategory;
        String newSubcategory;
        boolean edited;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput(category);

        if (subcategory == null) {
            return;
        } //end if

        newSubcategory = this.getNewSubcategoryInput(category);

        if (newSubcategory == null) {
            return;
        } //end if

        edited = this.model.editSubcategory(category, subcategory, newSubcategory);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (edited) {
            this.addController.fillSubcategoryComboBox();

            message = "The subcategory was successfully edited!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The subcategory could not be edited! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //editSubcategory

    /**
     * Deletes a subcategory to the model of this menu controller using the input of this menu controller's menu view.
     */
    private void deleteSubcategory() {
        String category;
        String subcategory;
        boolean deleted;
        JMenuBar menuBar;
        Window window;
        String message;
        String title = "HS Records";

        category = this.getCategoryInput();

        if (category == null) {
            return;
        } //end if

        subcategory = this.getSubcategoryInput(category);

        if (subcategory == null) {
            return;
        } //end if

        deleted = this.model.deleteSubcategory(category, subcategory);

        menuBar = this.menuView.getMenuBar();

        window = SwingUtilities.getWindowAncestor(menuBar);

        if (deleted) {
            this.addController.fillSubcategoryComboBox();

            message = "The subcategory was successfully deleted!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Error: The subcategory could not be deleted! Please contact support!";

            JOptionPane.showMessageDialog(window, message, title, JOptionPane.ERROR_MESSAGE);
        } //end if
    } //deleteSubcategory

    /**
     * Returns a new {@code MenuController} with the specified model, menu view, and add controller.
     *
     * @param model the model to be used in the operation
     * @param menuView the menu view to be used in the operation
     * @param addController the add controller to be used in the operation
     * @return a new {@code MenuController} with the specified model, menu view, and add controller
     * @throws NullPointerException if the specified model, menu view, or add controller is {@code null}
     */
    public static MenuController newMenuController(Model model, MenuView menuView, AddController addController) {
        MenuController menuController;
        JMenuItem createDirectoryMenuItem;
        JMenuItem openRecordMenuItem;
        JMenuItem openDirectoryMenuItem;
        JMenuItem importMenuItem;
        JMenuItem exportMenuItem;
        JMenuItem saveMenuItem;
        JMenuItem exitMenuItem;
        JMenuItem latestMenuItem;
        JMenuItem countMenuItem;
        JMenuItem addCategoryMenuItem;
        JMenuItem editCategoryMenuItem;
        JMenuItem deleteCategoryMenuItem;
        JMenuItem addSubcategoryMenuItem;
        JMenuItem editSubcategoryMenuItem;
        JMenuItem deleteSubcategoryMenuItem;

        menuController = new MenuController(model, menuView, addController);

        createDirectoryMenuItem = menuController.menuView.getCreateDirectoryMenuItem();

        openRecordMenuItem = menuController.menuView.getOpenRecordMenuItem();

        openDirectoryMenuItem = menuController.menuView.getOpenDirectoryMenuItem();

        importMenuItem = menuController.menuView.getImportMenuItem();

        exportMenuItem = menuController.menuView.getExportMenuItem();

        saveMenuItem = menuController.menuView.getSaveMenuItem();

        exitMenuItem = menuController.menuView.getExitMenuItem();

        latestMenuItem = menuController.menuView.getLatestMenuItem();

        countMenuItem = menuController.menuView.getCountMenuItem();

        addCategoryMenuItem = menuController.menuView.getAddCategoryMenuItem();

        editCategoryMenuItem = menuController.menuView.getEditCategoryMenuItem();

        deleteCategoryMenuItem = menuController.menuView.getDeleteCategoryMenuItem();

        addSubcategoryMenuItem = menuController.menuView.getAddSubcategoryMenuItem();

        editSubcategoryMenuItem = menuController.menuView.getEditSubcategoryMenuItem();

        deleteSubcategoryMenuItem = menuController.menuView.getDeleteSubcategoryMenuItem();

        createDirectoryMenuItem.addActionListener(actionEvent -> menuController.createDirectory());

        openRecordMenuItem.addActionListener(actionEvent -> menuController.openRecord());

        openDirectoryMenuItem.addActionListener(actionEvent -> menuController.openDirectory());

        importMenuItem.addActionListener(actionEvent -> menuController.importFromCsv());

        exportMenuItem.addActionListener(actionEvent -> menuController.exportToCsv());

        saveMenuItem.addActionListener(actionEvent -> menuController.save());

        exitMenuItem.addActionListener(actionEvent -> menuController.exit());

        latestMenuItem.addActionListener(actionEvent -> menuController.showLatestId());

        countMenuItem.addActionListener(actionEvent -> menuController.showEntryCount());

        addCategoryMenuItem.addActionListener(actionEvent -> menuController.addCategory());

        editCategoryMenuItem.addActionListener(actionEvent -> menuController.editCategory());

        deleteCategoryMenuItem.addActionListener(actionEvent -> menuController.deleteCategory());

        addSubcategoryMenuItem.addActionListener(actionEvent -> menuController.addSubcategory());

        editSubcategoryMenuItem.addActionListener(actionEvent -> menuController.editSubcategory());

        deleteSubcategoryMenuItem.addActionListener(actionEvent -> menuController.deleteSubcategory());

        return menuController;
    } //newMenuController
}