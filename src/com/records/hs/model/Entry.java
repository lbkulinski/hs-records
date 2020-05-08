package com.records.hs.model;

import java.util.Objects;

/**
 * An entry in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 7, 2020
 */
public final class Entry {
    /**
     * The ID of this entry.
     */
    private final String id;

    /**
     * The category of this entry.
     */
    private final String category;

    /**
     * The subcategory of this entry.
     */
    private final String subcategory;

    /**
     * The tags of this entry.
     */
    private final String tags;

    /**
     * Constructs a newly allocated {@code Entry} object with the specified ID, category, subcategory, and tags.
     *
     * @param id the ID to be used in construction
     * @param category the category to be used in construction
     * @param subcategory the subcategory to be used in construction
     * @param tags the tags to be used in construction
     * @throws NullPointerException if the specified ID, category, subcategory, or tags is {@code null}
     */
    public Entry(String id, String category, String subcategory, String tags) {
        Objects.requireNonNull(id, "the specified ID is null");

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        Objects.requireNonNull(tags, "the specified tags is null");

        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.tags = tags;
    } //Entry

    /**
     * Returns the ID of this entry.
     *
     * @return the ID of this entry
     */
    public String id() {
        return this.id;
    } //id

    /**
     * Returns the category of this entry.
     *
     * @return the category of this entry
     */
    public String category() {
        return this.category;
    } //category

    /**
     * Returns the subcategory of this entry.
     *
     * @return the subcategory of this entry
     */
    public String subcategory() {
        return this.subcategory;
    } //subcategory

    /**
     * Returns the tags of this entry.
     *
     * @return the tags of this entry
     */
    public String tags() {
        return this.tags;
    } //tags

    /**
     * Returns the hash code of this entry.
     *
     * @return the hash code of this entry
     */
    @Override
    public int hashCode() {
        int result = 23;
        int prime = 31;

        result = prime * result + Objects.hashCode(this.id);

        result = prime * result + Objects.hashCode(this.category);

        result = prime * result + Objects.hashCode(this.subcategory);

        result = prime * result + Objects.hashCode(this.tags);

        return result;
    } //hashCode

    /**
     * Determines whether or not the specified object is equal to this entry. {@code true} is returned if and only if
     * the specified object is an instance of {@code Entry} and its ID, category, subcategory, and tags are equal to
     * this entry's. Comparisons are case-sensitive.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this entry and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Entry) {
            boolean equal;

            equal = Objects.equals(this.id, ((Entry) object).id);

            equal &= Objects.equals(this.category, ((Entry) object).category);

            equal &= Objects.equals(this.subcategory, ((Entry) object).subcategory);

            equal &= Objects.equals(this.tags, ((Entry) object).tags);

            return equal;
        } else {
            return false;
        } //end if
    } //equals

    /**
     * Returns the {@code String} representation of this entry. The format of the returned {@code String} may change in
     * future versions of this API. If two entries are equal according to {@link Entry#equals(Object)}, though, their
     * {@code toString()} values will be equal according to {@link String#equals(Object)}.
     *
     * @return the {@code String} representation of this entry
     */
    @Override
    public String toString() {
        String format = "Entry[id=%s, category=%s, subcategory=%s, tags=%s]";

        return String.format(format, this.id, this.category, this.subcategory, this.tags);
    } //toString
}