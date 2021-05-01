package com.records.hs.model;

import java.io.Serializable;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * An entry in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 1, 2021
 */
public record Entry(String id, Type type, String category, String subcategory, Set<String> tags)
        implements Serializable {
    /**
     * Constructs a newly allocated {@code Entry} object with the specified ID, type, category, subcategory, and tags.
     * Each {@code String} will be transformed to use all uppercase letters.
     *
     * @param id the ID to be used in construction
     * @param type the type to be used in construction
     * @param category the category to be used in construction
     * @param subcategory the subcategory to be used in construction
     * @param tags the tags to be used in construction
     * @throws NullPointerException if the specified ID, type, category, subcategory, or set of tags is {@code null}
     */
    public Entry {
        Objects.requireNonNull(id, "the specified ID is null");

        Objects.requireNonNull(type, "the specified type is null");

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        Objects.requireNonNull(tags, "the specified set of tags is null");

        id = id.toUpperCase();

        category = category.toUpperCase();

        subcategory = subcategory.toUpperCase();

        tags = tags.stream()
                   .map(String::toUpperCase)
                   .collect(Collectors.toUnmodifiableSet());
    } //Entry
}