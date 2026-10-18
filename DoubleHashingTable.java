class DoubleHashingTable {
    private Integer[] table;
    private int capacity;
    private int size;
    private int totalCollisions;
    private final int DELETED = -99999;
    private int R; // Prime smaller than capacity

    public DoubleHashingTable(int capacity) {
        this.capacity = capacity;
        this.table = new Integer[capacity];
        this.size = 0;
        this.totalCollisions = 0;
        this.R = getPrimeSmallerThan(capacity);
    }

    private int hash1(int key) { return Math.abs(key) % capacity; }
    private int hash2(int key) { return R - (Math.abs(key) % R); }

    private int getPrimeSmallerThan(int n) {
        for (int i = n - 1; i >= 2; i--) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) { isPrime = false; break; }
            }
            if (isPrime) return i;
        }
        return 3; // Fallback safe prime
    }

    public boolean insert(int key) {
        if (size >= capacity) return false;

        int homeIndex = hash1(key);
        int step = hash2(key);
        int index = homeIndex;
        int i = 0;
        boolean collisionCounted = false;

        while (table[index] != null && table[index] != DELETED) {
            if (table[index] == key) return true;
            if (!collisionCounted) {
                totalCollisions++;
                collisionCounted = true;
            }
            i++;
            index = (homeIndex + i * step) % capacity;
        }

        table[index] = key;
        size++;
        return true;
    }

    public boolean search(int key, int[] probeHolder) {
        int homeIndex = hash1(key);
        int step = hash2(key);
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
            index = (homeIndex + i * step) % capacity;
        }
        probeHolder[0] = probes;
        return false;
    }

    public boolean delete(int key) {
        int homeIndex = hash1(key);
        int step = hash2(key);
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
            index = (homeIndex + i * step) % capacity;
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
