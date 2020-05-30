package com.records.hs.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.Collection;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A model in the HS Records application.
 *
 * @author Logan Kulinski, lbkulinski@icloud.com
 * @version May 30, 2020
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
         * The latest ID of this serialization proxy.
         */
        private final String latestId;

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
         * @param latestId the latest ID to be used in construction
         * @param idsToEntries the mapping from IDs to entries to be used in construction
         * @param catsToSubcats the mapping from categories to subcategories to be used in construction
         * @throws NullPointerException if the specified mapping from IDs to entries or mapping from categories to
         * subcategories is {@code null}
         */
        private SerializationProxy(String latestId, Map<String, Entry> idsToEntries, Map<String, Set<String>> catsToSubcats) {
            Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

            Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

            this.latestId = latestId;
            this.idsToEntries = idsToEntries;
            this.catsToSubcats = catsToSubcats;
        } //SerializationProxy

        /**
         * Returns a {@code Model} object in place of this serialization proxy.
         *
         * @return a {@code Model} object in place of this serialization proxy
         */
        private Object readResolve() {
            return new Model(this.latestId, this.idsToEntries, this.catsToSubcats);
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

            result = prime * result + Objects.hashCode(this.latestId);

            result = prime * result + Objects.hashCode(this.idsToEntries);

            result = prime * result + Objects.hashCode(this.catsToSubcats);

            return result;
        } //hashCode

        /**
         * Determines whether or not the specified object is equal to this serialization proxy. {@code true} is
         * returned if and only if the specified object is an instance of {@code SerializationProxy} and its latest ID,
         * mapping from IDs to entries, and mapping from categories to subcategories are equal to this serialization proxy's.
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

                equal = Objects.equals(this.latestId, ((SerializationProxy) object).latestId);

                equal &= Objects.equals(this.idsToEntries, ((SerializationProxy) object).idsToEntries);

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
            String format = "SerializationProxy[latestId=%s, idsToEntries=%s, catsToSubcats=%s]";

            return String.format(format, this.latestId, this.idsToEntries, this.catsToSubcats);
        } //toString
    } //SerializationProxy

    /**
     * The serial version UID of the class.
     */
    private static final long serialVersionUID;

    /**
     * The latest ID of this model.
     */
    private String latestId;

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
     * Constructs a newly allocated {@code Model} object with the specified latest ID, mapping from IDs to entries, and
     * mapping from categories to subcategories.
     *
     * @param latestId the latest ID to be used in construction
     * @param idsToEntries the mapping from IDs to entries to be used in construction
     * @param catsToSubcats the mapping from categories to subcategories to be used in construction
     * @throws NullPointerException if the specified mapping from IDs to entries or mapping from categories to
     * subcategories is {@code null}
     */
    private Model(String latestId, Map<String, Entry> idsToEntries, Map<String, Set<String>> catsToSubcats) {
        Objects.requireNonNull(idsToEntries, "the specified mapping from IDs to entries is null");

        Objects.requireNonNull(catsToSubcats, "the specified mapping from categories to subcategories is null");

        this.latestId = latestId;
        this.idsToEntries = idsToEntries;
        this.catsToSubcats = catsToSubcats;
    } //Model

    /**
     * Constructs a newly allocated {@code Model} object.
     */
    public Model() {
        this(null, new HashMap<>(), new HashMap<>());
    } //Model

    /**
     * Returns the latest ID of this model. If an entry has not been previously added to this model, {@code null} is
     * returned.
     *
     * @return the latest ID of this model
     */
    public String getLatestId() {
        return this.latestId;
    } //getLatestId

    /**
     * Returns the entries of this model.
     *
     * @return the entries of this model
     */
    public Set<Entry> getEntries() {
        Collection<Entry> elements;
        Set<Entry> entries;

        elements = this.idsToEntries.values();

        entries = new HashSet<>(elements);

        return Collections.unmodifiableSet(entries);
    } //getEntries

    /**
     * Returns the categories of this model.
     *
     * @return the categories of this model
     */
    public Set<String> getCategories() {
        Set<String> categories;
        SortedSet<String> sortedCategories;

        categories = this.catsToSubcats.keySet();

        sortedCategories = new TreeSet<>(categories);

        return Collections.unmodifiableSortedSet(sortedCategories);
    } //getCategories

    /**
     * Returns the subcategories of this model that are mapped from the specified category.
     *
     * @param category the category to be used in the operation
     * @return the subcategories of this model that are mapped from the specified category
     * @throws NullPointerException if the specified category is {@code null}
     */
    public Set<String> getSubcategories(String category) {
        Set<String> subcategories;

        Objects.requireNonNull(category, "the specified category is null");

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            return Set.of();
        } else {
            SortedSet<String> sortedSubcategories;

            sortedSubcategories = new TreeSet<>(subcategories);

            return Collections.unmodifiableSortedSet(sortedSubcategories);
        } //end if
    } //getSubcategories

    /**
     * Returns the entry count of this model.
     *
     * @return the entry count of this model
     */
    public int getEntryCount() {
        return this.idsToEntries.size();
    } //getEntryCount

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

        this.latestId = id;

        currentEntry = this.idsToEntries.putIfAbsent(id, entry);

        return currentEntry == null;
    } //addEntry

    /**
     * Attempts to add the specified category to this model. If this model already contains the specified category, the
     * addition will not occur. The specified category will be transformed into all uppercase letters if they are
     * added.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if the specified category was added to this model and {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean addCategory(String category) {
        Set<String> subcategories;
        boolean added;

        Objects.requireNonNull(category, "the specified category is null");

        category = category.toUpperCase();

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            this.catsToSubcats.put(category, new HashSet<>());

            added = true;
        } else {
            added = false;
        } //end if

        return added;
    } //addCategory

    /**
     * Attempts to add the specified subcategory to this model. If this model already contains the specified
     * subcategory that is mapped from the specified category, the addition will not occur. The specified category and
     * subcategory will be transformed into all uppercase letters if they are added.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if the specified subcategory was added to this model and {@code false} otherwise
     * @throws NullPointerException if the specified category or subcategory is {@code null}
     */
    public boolean addSubcategory(String category, String subcategory) {
        Set<String> subcategories;
        boolean added;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        category = category.toUpperCase();

        subcategory = subcategory.toUpperCase();

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            Set<String> newSubcategories = new HashSet<>();

            newSubcategories.add(subcategory);

            this.catsToSubcats.put(category, newSubcategories);

            added = true;
        } else {
            added = subcategories.add(subcategory);
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

        id = id.toUpperCase();

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
        Set<String> subcategories;
        boolean edited;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(newCategory, "the specified new category is null");

        category = category.toUpperCase();

        newCategory = newCategory.toUpperCase();

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            edited = false;
        } else {
            this.catsToSubcats.remove(category);

            this.catsToSubcats.put(newCategory, subcategories);

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
        Set<String> subcategories;
        boolean edited;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        Objects.requireNonNull(newSubcategory, "the specified new subcategory is null");

        category = category.toUpperCase();

        subcategory = subcategory.toUpperCase();

        newSubcategory = newSubcategory.toUpperCase();

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            edited = false;
        }  else {
            boolean deleted = subcategories.remove(subcategory);

            if (deleted) {
                subcategories.add(newSubcategory);

                edited = true;
            } else {
                edited = false;
            } //end if
        } //end if

        return edited;
    } //editSubcategory

    /**
     * Attempts to delete an entry with the specified ID from this model. If an entry with the specified ID has not
     * been previously added to this model, the deletion will not occur.
     *
     * @param id the ID to be used in the operation
     * @return {@code true}, if an entry with the specified ID was deleted from this model and {@code false} otherwise
     * @throws NullPointerException if the specified ID is {@code null}
     */
    public boolean deleteEntry(String id) {
        Entry deletedEntry;

        Objects.requireNonNull(id, "the specified ID is null");

        id = id.toUpperCase();

        deletedEntry = this.idsToEntries.remove(id);

        return deletedEntry != null;
    } //deleteEntry

    /**
     * Attempts to delete all of the entries with the specified type from this model. If an entry with the specified
     * type has not been previously added to this model, no deletions will not occur.
     *
     * @param type the type to be used in the operation
     * @return {@code true}, if at least one entry with the specified type was deleted from this model and
     * {@code false} otherwise
     * @throws NullPointerException if the specified type is {@code null}
     */
    public boolean deleteEntriesWithType(Type type) {
        int previousSize;
        int currentSize;

        Objects.requireNonNull(type, "the specified type is null");

        previousSize = this.idsToEntries.size();

        this.idsToEntries.values()
                         .stream()
                         .filter(entry -> {
                             Type entryType = entry.getType();

                             return Objects.equals(entryType, type);
                         })
                         .map(Entry::getId)
                         .forEach(this.idsToEntries::remove);

        currentSize = this.idsToEntries.size();

        return currentSize != previousSize;
    } //deleteEntriesWithType

    /**
     * Attempts to delete all of the entries with the specified category from this model. If an entry with the
     * specified category has not been previously added to this model, no deletions will not occur.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if at least one entry with the specified category was deleted from this model and
     * {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean deleteEntriesWithCategory(String category) {
        String categoryUpper;
        int previousSize;
        int currentSize;

        Objects.requireNonNull(category, "the specified category is null");

        categoryUpper = category.toUpperCase();

        previousSize = this.idsToEntries.size();

        this.idsToEntries.values()
                         .stream()
                         .filter(entry -> {
                             String entryCategory = entry.getCategory();

                             return entryCategory.equals(categoryUpper);
                         })
                         .map(Entry::getId)
                         .forEach(this.idsToEntries::remove);

        currentSize = this.idsToEntries.size();

        return currentSize != previousSize;
    } //deleteEntriesWithCategory

    /**
     * Attempts to delete all of the entries with the specified subcategory from this model. If an entry with the
     * specified subcategory has not been previously added to this model or the specified subcategory is not mapped
     * from the specified category, no deletions will not occur.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if at least one entry with the specified subcategory was deleted from this model and
     * {@code false} otherwise
     * @throws NullPointerException if the specified subcategory is {@code null}
     */
    public boolean deleteEntriesWithSubcategory(String category, String subcategory) {
        String categoryUpper;
        String subcategoryUpper;
        int previousSize;
        int currentSize;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        categoryUpper = category.toUpperCase();

        subcategoryUpper = subcategory.toUpperCase();

        previousSize = this.idsToEntries.size();

        this.idsToEntries.values()
                         .stream()
                         .filter(entry -> {
                             String entryCategory = entry.getCategory();

                             return entryCategory.equals(categoryUpper);
                         })
                         .filter(entry -> {
                             String entrySubcategory = entry.getSubcategory();

                             return entrySubcategory.equals(subcategoryUpper);
                         })
                         .map(Entry::getId)
                         .forEach(this.idsToEntries::remove);

        currentSize = this.idsToEntries.size();

        return currentSize != previousSize;
    } //deleteEntriesWithSubcategory

    /**
     * Attempts to delete all of the entries with the specified tag from this model. If an entry with the specified tag
     * has not been previously added to this model, no deletions will not occur.
     *
     * @param tag the tag to be used in the operation
     * @return {@code true}, if at least one entry with the specified tag was deleted from this model and {@code false}
     * otherwise
     * @throws NullPointerException if the specified tag is {@code null}
     */
    public boolean deleteEntriesWithTag(String tag) {
        String tagUpper;
        int previousSize;
        int currentSize;

        Objects.requireNonNull(tag, "the specified tag is null");

        tagUpper = tag.toUpperCase();

        previousSize = this.idsToEntries.size();

        this.idsToEntries.values()
                         .stream()
                         .filter(entry -> {
                             Set<String> entryTags = entry.getTags();

                             return entryTags.contains(tagUpper);
                         })
                         .map(Entry::getId)
                         .forEach(this.idsToEntries::remove);

        currentSize = this.idsToEntries.size();

        return currentSize != previousSize;
    } //deleteEntriesWithTag

    /**
     * Attempts to delete the specified category from this model. If the specified category has not been previously
     * added to this model, the deletion will not occur.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if the specified category was deleted from this model and {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean deleteCategory(String category) {
        Set<String> subcategories;

        Objects.requireNonNull(category, "the specified category is null");

        category = category.toUpperCase();

        subcategories = this.catsToSubcats.remove(category);

        return subcategories != null;
    } //deleteCategory

    /**
     * Attempts to delete the specified subcategory from this model. If the specified category has not been previously
     * added to this model or the specified subcategory is not mapped from the specified category, the deletion will
     * not occur.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if the specified subcategory was deleted from this model and {@code false} otherwise
     * @throws NullPointerException if the specified category or subcategory is {@code null}
     */
    public boolean deleteSubcategory(String category, String subcategory) {
        Set<String> subcategories;
        boolean deleted;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        category = category.toUpperCase();

        subcategory = subcategory.toUpperCase();

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            deleted = false;
        } else {
            deleted = subcategories.remove(subcategory);
        } //end if

        return deleted;
    } //deleteSubcategory

    /**
     * Attempts to find an entry with the specified ID in this model.
     *
     * @param id the ID to be used in the operation
     * @return an {@code Optional} containing the found entry or an empty {@code Optional} if an entry with the
     * specified ID was not found
     * @throws NullPointerException if the specified ID is {@code null}
     */
    public Optional<Entry> findEntryWithId(String id) {
        Entry foundEntry;

        Objects.requireNonNull(id, "the specified ID is null");

        id = id.toUpperCase();

        foundEntry = this.idsToEntries.get(id);

        return Optional.ofNullable(foundEntry);
    } //findEntryWithId

    /**
     * Attempts to find entries with the specified type in this model.
     *
     * @param type the type to be used in the operation
     * @return an unmodifiable {@code Set} containing the found entries (if any)
     * @throws NullPointerException if the specified type is {@code null}
     */
    public Set<Entry> findEntriesWithType(Type type) {
        Set<Entry> foundEntries;

        Objects.requireNonNull(type, "the specified type is null");

        foundEntries = this.idsToEntries.values()
                                        .stream()
                                        .filter(entry -> {
                                            Type entryType = entry.getType();

                                            return Objects.equals(entryType, type);
                                        })
                                        .collect(Collectors.toUnmodifiableSet());

        return foundEntries;
    } //findEntriesWithType

    /**
     * Attempts to find entries with the specified category in this model.
     *
     * @param category the category to be used in the operation
     * @return an unmodifiable {@code Set} containing the found entries (if any)
     * @throws NullPointerException if the specified category is {@code null}
     */
    public Set<Entry> findEntriesWithCategory(String category) {
        String categoryUpper;
        Set<Entry> foundEntries;

        Objects.requireNonNull(category, "the specified category is null");

        categoryUpper = category.toUpperCase();

        foundEntries = this.idsToEntries.values()
                                        .stream()
                                        .filter(entry -> {
                                            String entryCategory = entry.getCategory();

                                            return entryCategory.equals(categoryUpper);
                                        })
                                        .collect(Collectors.toUnmodifiableSet());

        return foundEntries;
    } //findEntriesWithCategory

    /**
     * Attempts to find entries with the specified subcategory in this model. The specified subcategory must be mapped
     * from the specified category in order to be included in the set of found entries.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return an unmodifiable {@code Set} containing the found entries (if any)
     * @throws NullPointerException if the specified category or subcategory is {@code null}
     */
    public Set<Entry> findEntriesWithSubcategory(String category, String subcategory) {
        String categoryUpper;
        String subcategoryUpper;
        Set<Entry> foundEntries;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        categoryUpper = category.toUpperCase();

        subcategoryUpper = subcategory.toUpperCase();

        foundEntries = this.idsToEntries.values()
                                        .stream()
                                        .filter(entry -> {
                                            String entryCategory = entry.getCategory();

                                            return entryCategory.equals(categoryUpper);
                                        })
                                        .filter(entry -> {
                                            String entrySubcategory = entry.getSubcategory();

                                            return entrySubcategory.equals(subcategoryUpper);
                                        })
                                        .collect(Collectors.toUnmodifiableSet());

        return foundEntries;
    } //findEntriesWithSubcategory

    /**
     * Attempts to find entries whose tags contain the specified tag in this model.
     *
     * @param tag the tag to be used in the operation
     * @return an unmodifiable {@code Set} containing the found entries (if any)
     * @throws NullPointerException if the specified tag is {@code null}
     */
    public Set<Entry> findEntriesWithTag(String tag) {
        String tagUpper;
        Set<Entry> foundEntries;

        Objects.requireNonNull(tag, "the specified tag is null");

        tagUpper = tag.toUpperCase();

        foundEntries = this.idsToEntries.values()
                                        .stream()
                                        .filter(entry -> {
                                            Set<String> entryTags = entry.getTags();

                                            return entryTags.contains(tagUpper);
                                        })
                                        .collect(Collectors.toUnmodifiableSet());

        return foundEntries;
    } //findEntriesWithTag

    /**
     * Determines whether or not this model contains the specified category.
     *
     * @param category the category to be used in the operation
     * @return {@code true}, if this model contains the specified category and {@code false} otherwise
     * @throws NullPointerException if the specified category is {@code null}
     */
    public boolean containsCategory(String category) {
        Objects.requireNonNull(category, "the specified category is null");

        category = category.toUpperCase();

        return this.catsToSubcats.containsKey(category);
    } //containsCategory

    /**
     * Determines whether or not this model contains the specified subcategory that is mapped from the specified
     * category.
     *
     * @param category the category to be used in the operation
     * @param subcategory the subcategory to be used in the operation
     * @return {@code true}, if this model contains the specified subcategory that is mapped from the specified
     * category and {@code false} otherwise
     * @throws NullPointerException if the specified category or subcategory is {@code null}
     */
    public boolean containsSubcategory(String category, String subcategory) {
        Set<String> subcategories;

        Objects.requireNonNull(category, "the specified category is null");

        Objects.requireNonNull(subcategory, "the specified subcategory is null");

        category = category.toUpperCase();

        subcategory = subcategory.toUpperCase();

        subcategories = this.catsToSubcats.get(category);

        if (subcategories == null) {
            return false;
        } //end if

        return subcategories.contains(subcategory);
    } //containsSubcategory

    /**
     * Returns a {@code SerializationProxy} object in place of this model.
     *
     * @return a {@code SerializationProxy} object in place of this model
     */
    private Object writeReplace() {
        return new SerializationProxy(this.latestId, this.idsToEntries, this.catsToSubcats);
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

        result = prime * result + Objects.hashCode(this.latestId);

        result = prime * result + Objects.hashCode(this.idsToEntries);

        result = prime * result + Objects.hashCode(this.catsToSubcats);

        return result;
    } //hashCode

    /**
     * Determines whether or not the specified object is equal to this model. {@code true} is returned if and only if
     * the specified object is an instance of {@code Model} and its latest ID, mapping from IDs to entries, and mapping
     * from categories to subcategories are equal to this model's.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this model and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Model) {
            boolean equal;

            equal = Objects.equals(this.latestId, ((Model) object).latestId);

            equal &= Objects.equals(this.idsToEntries, ((Model) object).idsToEntries);

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
        String format = "Model[latestId=%s, idsToEntries=%s, catsToSubcats=%s]";

        return String.format(format, this.latestId, this.idsToEntries, this.catsToSubcats);
    } //toString
}