class Entry {
    Integer key;          // null = empty slot
    boolean deleted;      // tombstone for linear/double hashing
    Entry(int k) { key = k; deleted = false; }
    Entry() { key = null; deleted = false; }
}