package hashtable;

public class HashMap {

    public static class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return (value == null) ? "" : key + "=" + value;
        }
    }

    private Entry[] entries;
    private int size;

    public HashMap(int size) {
        entries = new Entry[size];
    }

    // O(n)
    public void put(int key, String value) {
        var entry = getEntry(key);
        if(entry != null) {
            entry.value = value;
            return;
        }

        if(isFull())
            throw new IllegalStateException();

        int index = probe(key);
        entries[index] = new Entry(key, value);
        size++;
    }

    // O(n)
    public String get(int key) {
        var entry = getEntry(key);
        if(entry == null)
            throw new IllegalArgumentException();
        return entry.value;
    }

    // O(n)
    public void remove(int key) {
        var index = indexOf(key);
        if(index < 0 || entries[index] == null)
            throw new IllegalArgumentException();
        entries[index] = null;
        size--;
    }

    // O(n)
    public boolean contains(int key) {
        return getEntry(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == entries.length;
    }

    private Entry getEntry(int key) {
        int index = indexOf(key);
        return (index >= 0) ? entries[index] : null;
    }

    private int indexOf(int key) {
        final int NOT_FOUND = -1;

        int hash = hash(key);
        int index = hash;
        while(true) {
            if(entries[index] == null) return NOT_FOUND;
            if(entries[index].key == key) return index;
            if(index == hash-1) return NOT_FOUND;
            index = nextIndex(hash, index);
        }
    }

    private int hash(int key) {
        return key % entries.length;
    }

    private int nextIndex(int hash, int index) {
        return (hash + index + 1) % entries.length;
    }

    private int probe(int key) {
        int hash = hash(key);
        int index = hash;
        while (entries[index] != null)
            index = nextIndex(hash, index);
        return index;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("{");
        for(int i=0; i<entries.length; i++) {
            if(entries[i] == null) continue;
            builder.append(entries[i].key);
            builder.append("=");
            builder.append(entries[i].value);
            builder.append(", ");
        }
        int length = builder.length();
        builder.delete(length-2, length);
        builder.append("}");
        return builder.toString();
    }
}
