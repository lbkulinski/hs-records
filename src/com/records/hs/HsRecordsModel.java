package com.records.hs;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A model in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version March 16, 2020
 */
public final class HsRecordsModel implements Serializable {
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
         * Returns a {@code HsRecordsModel} object in place of this serialization proxy.
         *
         * @return a {@code HsRecordsModel} object in place of this serialization proxy
         */
        private Object readResolve() {
            return new HsRecordsModel(this.idsToEntries, this.catsToSubcats);
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
         * {@link Entry#equals(Object)}, though, their {@code toString()} values will be equal according to
         * {@link String#equals(Object)}.
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
     * Constructs a newly allocated {@code HsRecordsModel} object with the specified mapping from IDs to entries and
     * mapping from categories to subcategories.
     *
     * @param idsToEntries the mapping from IDs to entries to be used in construction
     * @param catsToSubcats the mapping from categories to subcategories to be used in construction
     * @throws NullPointerException if the specified mapping from IDs to entries, categories, or mapping from
     * categories to subcategories is {@code null}
     */
    public HsRecordsModel(Map<String, Entry> idsToEntries, Map<String, Set<String>> catsToSubcats) {
        Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

        Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

        this.idsToEntries = new HashMap<>(idsToEntries);
        this.catsToSubcats = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : catsToSubcats.entrySet()) {
            this.catsToSubcats.put(entry.getKey(), new HashSet<>(entry.getValue()));
        } //end for
    } //HsRecordsModel

    /**
     * Constructs a newly allocated {@code HsRecordsModel} object.
     */
    public HsRecordsModel() {
        this(new HashMap<>(), new HashMap<>());
    } //HsRecordsModel

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
     * Attempts to edit the entry with the specified ID of this model by replacing it with the specified new entry. If
     * the ID of the specified new entry does not equal the specified ID or an entry with the specified ID has not been
     * previously added to this model, the edit will not occur.
     *
     * @param id the ID to be used in the operation
     * @param newEntry the new entry to be used in the operation
     * @return {@code true}, if the entry with the specified of the model ID was edited and {@code false} otherwise
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
}