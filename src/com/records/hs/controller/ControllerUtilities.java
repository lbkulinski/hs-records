package com.records.hs.controller;

import com.records.hs.model.Model;
import java.util.logging.Logger;
import java.nio.file.Path;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.Objects;
import java.io.ObjectOutputStream;

/**
 * A set of utility methods used by the controllers in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 20, 2020
 */
public final class ControllerUtilities {
    /**
     * The file name where the {@code Model} object is stored.
     */
    private static final String FILE_NAME;

    static {
        FILE_NAME = "model.ser";
    } //static

    /**
     * Constructs a newly allocated {@code ControllerUtilities} object.
     *
     * @throws AssertionError if an object of type {@code ControllerUtilities} attempts to be instantiated
     */
    private ControllerUtilities() {
        throw new AssertionError("an object of type ControllerUtilities cannot be instantiated");
    } //ControllerUtilities

    /**
     * Reads a model from the file where it was saved. If the object could not be read, {@code null} is returned.
     *
     * @return the model that was read from the file
     */
    public static Model readModelFromFile() {
        Logger logger;
        Path path;
        Model model;

        logger = Logger.getGlobal();

        path = Path.of(FILE_NAME);

        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
            model = (Model) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            String exceptionMessage = e.getMessage();

            logger.log(Level.INFO, exceptionMessage, e);

            return null;
        } //end try catch

        return model;
    } //readModelFromFile

    /**
     * Writes the specified model to a file.
     *
     * @param model the model to be used in the operation
     * @return {@code true}, if the specified model was successfully written to the file and {@code false} otherwise
     */
    public static boolean writeModelToFile(Model model) {
        Logger logger;
        Path path;

        Objects.requireNonNull(model, "the specified model is null");

        logger = Logger.getGlobal();

        path = Path.of(FILE_NAME);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(model);
        } catch (IOException e) {
            String exceptionMessage = e.getMessage();

            logger.log(Level.INFO, exceptionMessage, e);

            return false;
        } //end try catch

        return true;
    } //writeModelToFile
}