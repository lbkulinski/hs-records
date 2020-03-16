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
         * The categories of this serialization proxy.
         */
        private final Set<String> categories;

        /**
         * The mapping from categories to subcategories of this serialization proxy.
         */
        private final Map<String, Set<String>> catsToSubcats;

        static {
            serialVersionUID = 0xCAFEBABEL;
        } //static

        /**
         * Constructs a newly allocated {@code SerializationProxy} object with the specified mapping from IDs to
         * entries, categories, and mapping from categories to subcategories.
         *
         * @param idsToEntries the mapping from IDs to entries to be used in construction
         * @param categories the categories to be used in construction
         * @param catsToSubcats the mapping from categories to subcategories to be used in construction
         * @throws NullPointerException if the specified mapping from IDs to entries, categories, or mapping from
         * categories to subcategories is {@code null}
         */
        public SerializationProxy(Map<String, Entry> idsToEntries, Set<String> categories,
                                  Map<String, Set<String>> catsToSubcats) {
            Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

            Objects.requireNonNull(categories, "the specified categories is null");

            Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

            this.idsToEntries = new HashMap<>(idsToEntries);
            this.categories = new HashSet<>(categories);
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
        private Object readReplace() {
            return new HsRecordsModel(this.idsToEntries, this.categories, this.catsToSubcats);
        } //readReplace

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

            result = prime * result + Objects.hashCode(this.categories);

            result = prime * result + Objects.hashCode(this.catsToSubcats);

            return result;
        } //hashCode

        /**
         * Determines whether or not the specified object is equal to this serialization proxy. {@code true} is
         * returned if and only if the specified object is an instance of {@code SerializationProxy} and its mapping
         * from IDs to entries, categories, and mapping from categories to subcategories are equal to this
         * serialization proxy's.
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

                equal &= Objects.equals(this.categories, ((SerializationProxy) object).categories);

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
            String format = "SerializationProxy[%s, %s %s]";

            return String.format(format, this.idsToEntries, this.categories, this.catsToSubcats);
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
     * The categories of this model.
     */
    private final Set<String> categories;

    /**
     * The mapping from categories to subcategories of this model.
     */
    private final Map<String, Set<String>> catsToSubcats;

    static {
        serialVersionUID = 0xCAFEBABEL;
    } //static

    /**
     * Constructs a newly allocated {@code HsRecordsModel} object with the specified mapping from IDs to entries,
     * categories, and mapping from categories to subcategories.
     *
     * @param idsToEntries the mapping from IDs to entries to be used in construction
     * @param categories the categories to be used in construction
     * @param catsToSubcats the mapping from categories to subcategories to be used in construction
     * @throws NullPointerException if the specified mapping from IDs to entries, categories, or mapping from
     * categories to subcategories is {@code null}
     */
    public HsRecordsModel(Map<String, Entry> idsToEntries, Set<String> categories,
                          Map<String, Set<String>> catsToSubcats) {
        Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

        Objects.requireNonNull(categories, "the specified categories is null");

        Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

        this.idsToEntries = new HashMap<>(idsToEntries);
        this.categories = new HashSet<>(categories);
        this.catsToSubcats = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : catsToSubcats.entrySet()) {
            this.catsToSubcats.put(entry.getKey(), new HashSet<>(entry.getValue()));
        } //end for
    } //HsRecordsModel

    /**
     * Constructs a newly allocated {@code HsRecordsModel} object.
     */
    public HsRecordsModel() {
        this(new HashMap<>(), new HashSet<>(), new HashMap<>());
    } //HsRecordsModel
}