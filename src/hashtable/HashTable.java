package hashtable;

import java.util.LinkedList;

public class HashTable {

    public static class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    private static final int ARRAY_SIZE = 10;

    private LinkedList<Entry>[] entries;

    public HashTable() {
        entries = new LinkedList[ARRAY_SIZE];
    }

    public void put(int key, String value) {
        var entry = getEntry(key);

        if(entry == null) {
            var bucket = getOrCreateBucket(key);
            bucket.addLast(new Entry(key, value));
        } else {
            entry.setValue(value);
        }
    }

    public String get(int key) {
        var entry = getEntry(key);
        if(entry == null)
            throw new IllegalArgumentException();

        return entry.getValue();
    }

    public void remove(int key) {
        var entry = getEntry(key);
        if(entry == null)
            throw new IllegalArgumentException();

        getBucket(key).remove(entry);
    }

    public boolean contains(int key) {
        return getEntry(key) != null;
    }

    private Entry getEntry(int key) {
        var bucket = getBucket(key);

        if(bucket != null) {
            for(var entry : bucket) {
                if(entry.getKey() == key)
                    return entry;
            }
        }

        return null;
    }

    private LinkedList<Entry> getBucket(int key) {
        return entries[hash(key)];
    }

    private LinkedList<Entry> getOrCreateBucket(int key) {
        int index = hash(key);
        if(entries[index] == null)
            entries[index] = new LinkedList<>();

        return entries[index];
    }

    private int hash(int key) {
        return key % entries.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        for(var list : entries) {
            if(list == null) continue;
            list.forEach(item -> {
                builder.append(item.getKey());
                builder.append("=");
                builder.append(item.getValue());
                builder.append(", ");
            });
        }
        builder.delete(builder.length()-2, builder.length());
        builder.append("}");

        return builder.toString();
    }
}
