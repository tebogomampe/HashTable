class LinearProbingTable {
    private Integer[] table;
    private int capacity;
    private int size;
    private int totalCollisions;
    private final int DELETED = -99999;

    public LinearProbingTable(int capacity) {
        this.capacity = capacity;
        this.table = new Integer[capacity];
        this.size = 0;
        this.totalCollisions = 0;
    }

    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    public boolean insert(int key) {
        if (size >= capacity) return false; // Table full

        int homeIndex = hash(key);
        int index = homeIndex;
        int i = 0;
        boolean collisionCounted = false;

        while (table[index] != null && table[index] != DELETED) {
            if (table[index] == key) return true; // Already exists
            if (!collisionCounted) {
                totalCollisions++;
                collisionCounted = true;
            }
            i++;
            index = (homeIndex + i) % capacity;
        }

        table[index] = key;
        size++;
        return true;
    }

    public boolean search(int key, int[] probeHolder) {
        int homeIndex = hash(key);
        int index = homeIndex;
        int i = 0;
        int probes = 0;

        while (i < capacity) {
            probes++;
            if (table[index] == null) {
                probeHolder[0] = probes;
                return false;
            }
            if (table[index] == key) {
                probeHolder[0] = probes;
                return true;
            }
            i++;
            index = (homeIndex + i) % capacity;
        }
        probeHolder[0] = probes;
        return false;
    }

    public boolean delete(int key) {
        int homeIndex = hash(key);
        int index = homeIndex;
        int i = 0;

        while (i < capacity) {
            if (table[index] == null) return false;
            if (table[index] == key) {
                table[index] = DELETED;
                size--;
                return true;
            }
            i++;
            index = (homeIndex + i) % capacity;
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) System.out.println("[" + i + "]: empty");
            else if (table[i] == DELETED) System.out.println("[" + i + "]: DELETED");
            else System.out.println("[" + i + "]: " + table[i]);
        }
    }

    public int getCollisions() { return totalCollisions; }
}
