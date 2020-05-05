package com.records.hs;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;

/**
 * A model in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 4, 2020
 */
public final class Model implements Serializable {
    /**
     * The serialization proxy of the class.
     */
    private static final class SerializationProxy implements Serializable {
        /**
         * The serial version UID of the class.
         */
        private static final long serialVersionUID;

        /**
         * The mapping from IDs to entries of this serialization proxy.
         */
        private final Map<String, Entry> idsToEntries;

        /**
         * The mapping from categories to subcategories of this serialization proxy.
         */
        private final Map<String, Set<String>> catsToSubcats;

        static {
            serialVersionUID = 0xCAFEBABEL;
        } //static

        /**
         * Constructs a newly allocated {@code SerializationProxy} object with the specified mapping from IDs to
         * entries and mapping from categories to subcategories.
         *
         * @param idsToEntries the mapping from IDs to entries to be used in construction
         * @param catsToSubcats the mapping from categories to subcategories to be used in construction
         * @throws NullPointerException if the specified mapping from IDs to entries or mapping from categories to
         * subcategories is {@code null}
         */
        public SerializationProxy(Map<String, Entry> idsToEntries, Map<String, Set<String>> catsToSubcats) {
            Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

            Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

            this.idsToEntries = new HashMap<>(idsToEntries);
            this.catsToSubcats = new HashMap<>();

            for (Map.Entry<String, Set<String>> entry : catsToSubcats.entrySet()) {
                this.catsToSubcats.put(entry.getKey(), new HashSet<>(entry.getValue()));
            } //end for
        } //SerializationProxy

        /**
         * Returns a {@code Model} object in place of this serialization proxy.
         *
         * @return a {@code Model} object in place of this serialization proxy
         */
        private Object readResolve() {
            return new Model(this.idsToEntries, this.catsToSubcats);
        } //readResolve

        /**
         * Returns the hash code of this serialization proxy.
         *
         * @return the hash code of this serialization proxy
         */
        @Override
        public int hashCode() {
            int result = 23;
            int prime = 31;

            result = prime * result + Objects.hashCode(this.idsToEntries);

            result = prime * result + Objects.hashCode(this.catsToSubcats);

            return result;
        } //hashCode

        /**
         * Determines whether or not the specified object is equal to this serialization proxy. {@code true} is
         * returned if and only if the specified object is an instance of {@code SerializationProxy} and its mapping
         * from IDs to entries and mapping from categories to subcategories are equal to this serialization proxy's.
         *
         * @param object the object to be used in the comparisons
         * @return {@code true}, if the specified object is equal to this serialization proxy and {@code false}
         * otherwise
         */
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (object instanceof SerializationProxy) {
                boolean equal;

                equal = Objects.equals(this.idsToEntries, ((SerializationProxy) object).idsToEntries);

                equal &= Objects.equals(this.catsToSubcats, ((SerializationProxy) object).catsToSubcats);

                return equal;
            } else {
                return false;
            } //end if
        } //equals

        /**
         * Returns the {@code String} representation of this serialization proxy. The format of the returned
         * {@code String} may change in future versions of this API. If two entries are equal according to
         * {@link SerializationProxy#equals(Object)}, though, their {@code toString()} values will be equal according
         * to {@link String#equals(Object)}.
         *
         * @return the {@code String} representation of this serialization proxy
         */
        @Override
        public String toString() {
            String format = "SerializationProxy[%s, %s]";

            return String.format(format, this.idsToEntries, this.catsToSubcats);
        } //toString
    } //SerializationProxy

    /**
     * The serial version UID of the class.
     */
    private static final long serialVersionUID;

    /**
     * The mapping from IDs to entries of this model.
     */
    private final Map<String, Entry> idsToEntries;

    /**
     * The mapping from categories to subcategories of this model.
     */
    private final Map<String, Set<String>> catsToSubcats;

    static {
        serialVersionUID = 0xCAFEBABEL;
    } //static

    /**
     * Constructs a newly allocated {@code Model} object with the specified mapping from IDs to entries and mapping
     * from categories to subcategories.
     *
     * @param idsToEntries the mapping from IDs to entries to be used in construction
     * @param catsToSubcats the mapping from categories to subcategories to be used in construction
     * @throws NullPointerException if the specified mapping from IDs to entries, categories, or mapping from
     * categories to subcategories is {@code null}
     */
    public Model(Map<String, Entry> idsToEntries, Map<String, Set<String>> catsToSubcats) {
        Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

        Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

        this.idsToEntries = new HashMap<>(idsToEntries);
        this.catsToSubcats = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : catsToSubcats.entrySet()) {
            this.catsToSubcats.put(entry.getKey(), new HashSet<>(entry.getValue()));
        } //end for
    } //Model

    /**
     * Constructs a newly allocated {@code Model} object.
     */
    public Model() {
        this(new HashMap<>(), new HashMap<>());
    } //Model

    /**
     * Attempts to add the specified entry to this model. If this model already contains an entry with the ID of the
     * specified entry, the addition will not occur.
     *
     * @param entry the entry to be used in the operation
     * @return {@code true}, if the specified entry was added to this model and {@code false} otherwise
     * @throws NullPointerException if the specified entry is {@code null}
     */
    public boolean addEntry(Entry entry) {
        String id;
        Entry currentEntry;

        Objects.requireNonNull(entry, "the specified entry is null");

        id = entry.getId();

        currentEntry = this.idsToEntries.putIfAbsent(id, entry);

        return currentEntry == null;
    } //addEntry

    /**
     * Attempts to add the specified category to this model. If this model already contains the specified category, the
     * addition will not occur.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if the specified category was added to this model and {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean addCategory(String category) {
        Set<String> currentSubcats;
        boolean added;

        Objects.requireNonNull(category, "the specified category is null");

        currentSubcats = this.catsToSubcats.get(category);

        if (currentSubcats == null) {
            this.catsToSubcats.put(category, new HashSet<>());

            added = true;
        } else {
            added = false;
        } //end if

        return added;
    } //addCategory

    /**
     * Attempts to add the specified subcategory to this model. If this model already contains the specified
     * subcategory that is mapped from the specified category, the addition will not occur.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if the specified subcategory was added to this model and {@code false} otherwise
     * @throws NullPointerException if the specified category or subcategory is {@code null}
     */
    public boolean addSubcategory(String category, String subcategory) {
        Set<String> currentSubcats;
        boolean added;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        currentSubcats = this.catsToSubcats.get(category);

        if (currentSubcats == null) {
            Set<String> newSubcats = new HashSet<>();

            newSubcats.add(subcategory);

            this.catsToSubcats.put(category, newSubcats);

            added = true;
        } else {
            added = currentSubcats.add(subcategory);
        } //end if

        return added;
    } //addSubcategory

    /**
     * Attempts to edit the entry of this model with the specified ID by replacing it with the specified new entry. If
     * the ID of the specified new entry does not equal the specified ID or an entry with the specified ID has not been
     * previously added to this model, the edit will not occur.
     *
     * @param id the ID to be used in the operation
     * @param newEntry the new entry to be used in the operation
     * @return {@code true}, if the entry of this model with the specified ID was edited and {@code false} otherwise
     * @throws NullPointerException if the specified ID or new entry is {@code null}
     */
    public boolean editEntry(String id, Entry newEntry) {
        boolean edited;

        Objects.requireNonNull(id, "the specified ID is null");

        Objects.requireNonNull(newEntry, "the specified new entry is null");

        if (Objects.equals(newEntry.getId(), id)) {
            Entry currentEntry = this.idsToEntries.get(id);

            if (currentEntry == null) {
                edited = false;
            } else {
                this.idsToEntries.put(id, newEntry);

                edited = true;
            } //endif
        } else {
            edited = false;
        } //end if

        return edited;
    } //editEntry

    /**
     * Attempts to edit the specified category of this model by replacing it with the specified new category. If the
     * specified category has not been previously added to this model, the edit will not occur.
     *
     * @param category the category to be used in the operation
     * @param newCategory the new category to be used in the operation
     * @return {@code true}, if the specified category of this model was edited and {@code false} otherwise
     * @throws NullPointerException if the specified category or new category is {@code null}
     */
    public boolean editCategory(String category, String newCategory) {
        Set<String> currentSubcats;
        boolean edited;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(newCategory, "the specified new category is null");

        currentSubcats = this.catsToSubcats.get(category);

        if (currentSubcats == null) {
            edited = false;
        } else {
            this.catsToSubcats.remove(category);

            this.catsToSubcats.put(newCategory, currentSubcats);

            edited = true;
        } //end if

        return edited;
    } //editCategory

    /**
     * Attempts to edit the specified subcategory of this model by replacing it with the specified new subcategory. If
     * the specified category has not been previously added to this model or the specified subcategory is not mapped
     * from the specified category, the edit will not occur.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @param newSubcategory the new subcategory to be used in the operation
     * @return {@code true}, if the specified subcategory of this model was edited and {@code false} otherwise
     * @throws NullPointerException if the specified category, subcategory, or new subcategory is {@code null}
     */
    public boolean editSubcategory(String category, String subcategory, String newSubcategory) {
        Set<String> currentSubcats;
        boolean edited;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        Objects.requireNonNull(newSubcategory, "the specified new subcategory is null");

        currentSubcats = this.catsToSubcats.get(category);

        if (currentSubcats == null) {
            edited = false;
        }  else {
            boolean removed = currentSubcats.remove(subcategory);

            if (removed) {
                currentSubcats.add(newSubcategory);

                edited = true;
            } else {
                edited = false;
            } //end if
        } //end if

        return edited;
    } //editSubcategory

    /**
     * Attempts to remove an entry with the specified ID from this model. If an entry with the specified ID has not
     * been previously added to this model, the removal will not occur.
     *
     * @param id the ID to be used in the operation
     * @return {@code true}, if an entry with the specified ID was removed from this model and {@code false} otherwise
     * @throws NullPointerException if the specified ID is {@code null}
     */
    public boolean removeEntry(String id) {
        Entry removedEntry;

        Objects.requireNonNull(id, "the specified ID is null");

        removedEntry = this.idsToEntries.remove(id);

        return removedEntry != null;
    } //removeEntry

    /**
     * Attempts to remove all of the entries with the specified category from this model. If an entry with the
     * specified category has not been previously added to this model, no removals will not occur.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if at least one entry with the specified category was removed from this model and
     * {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean removeAllEntriesWithCategory(String category) {
        Collection<Entry> values;
        Set<Entry> entries;
        String entryCategory;
        String entryId;
        boolean removed = false;

        Objects.requireNonNull(category, "the specified category is null");

        values = this.idsToEntries.values();

        entries = new HashSet<>(values);

        for (Entry entry : entries) {
            entryCategory = entry.getCategory();

            if (Objects.equals(entryCategory, category)) {
                entryId = entry.getId();

                this.idsToEntries.remove(entryId);

                removed = true;
            } //end if
        } //end for

        return removed;
    } //removeAllEntriesWithCategory

    /**
     * Attempts to remove all of the entries with the specified subcategory from this model. If an entry with the
     * specified subcategory has not been previously added to this model, no removals will not occur.
     *
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if at least one entry with the specified subcategory was removed from this model and
     * {@code false} otherwise
     * @throws NullPointerException if the specified subcategory is {@code null}
     */
    public boolean removeAllEntriesWithSubcategory(String subcategory) {
        Collection<Entry> values;
        Set<Entry> entries;
        String entrySubcategory;
        String entryId;
        boolean removed = false;

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        values = this.idsToEntries.values();

        entries = new HashSet<>(values);

        for (Entry entry : entries) {
            entrySubcategory = entry.getSubcategory();

            if (Objects.equals(entrySubcategory, subcategory)) {
                entryId = entry.getId();

                this.idsToEntries.remove(entryId);

                removed = true;
            } //end if
        } //end for

        return removed;
    } //removeAllEntriesWithSubcategory

    /**
     * Attempts to remove the specified category from this model. If the specified category has not been previously
     * added to this model, the removal will not occur.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if the specified category was removed from this model and {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean removeCategory(String category) {
        Set<String> removedSubcats;

        Objects.requireNonNull(category, "the specified category is null");

        removedSubcats = this.catsToSubcats.remove(category);

        return removedSubcats != null;
    } //removeCategory

    /**
     * Attempts to remove the specified subcategory from this model. If the specified category has not been previously
     * added to this model or the specified subcategory is not mapped from the specified category, the removal will not
     * occur.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if the specified subcategory was removed from this model and {@code false} otherwise
     * @throws NullPointerException if the specified category or subcategory is {@code null}
     */
    public boolean removeSubcategory(String category, String subcategory) {
        Set<String> currentSubcats;
        boolean removed;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        currentSubcats = this.catsToSubcats.get(category);

        if (currentSubcats == null) {
            removed = false;
        } else {
            removed = currentSubcats.remove(subcategory);
        } //end if

        return removed;
    } //removeSubcategory

    /**
     * Returns a new {@code SerializationProxy} object in place of this model.
     *
     * @return a new {@code SerializationProxy} object in place of this model
     */
    private Object writeReplace() {
        return new SerializationProxy(this.idsToEntries, this.catsToSubcats);
    } //writeReplace

    /**
     * Returns the hash code of this model.
     *
     * @return the hash code of this model
     */
    @Override
    public int hashCode() {
        int result = 23;
        int prime = 31;

        result = prime * result + Objects.hashCode(this.idsToEntries);

        result = prime * result + Objects.hashCode(this.catsToSubcats);

        return result;
    } //hashCode

    /**
     * Determines whether or not the specified object is equal to this model. {@code true} is returned if and only if
     * the specified object is an instance of {@code Model} and its mapping from IDs to entries and mapping from
     * categories to subcategories are equal to this model's.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this model and {@code false}
     * otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Model) {
            boolean equal;

            equal = Objects.equals(this.idsToEntries, ((Model) object).idsToEntries);

            equal &= Objects.equals(this.catsToSubcats, ((Model) object).catsToSubcats);

            return equal;
        } else {
            return false;
        } //end if
    } //equals

    /**
     * Returns the {@code String} representation of this model. The format of the returned {@code String} may change in
     * future versions of this API. If two entries are equal according to {@link Model#equals(Object)}, though, their
     * {@code toString()} values will be equal according to {@link String#equals(Object)}.
     *
     * @return the {@code String} representation of this model
     */
    @Override
    public String toString() {
        String format = "Model[%s, %s]";

        return String.format(format, this.idsToEntries, this.catsToSubcats);
    } //toString
}