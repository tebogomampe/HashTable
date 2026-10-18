class ChainingHashTable {
    private Node[] table;
    private int capacity;
    private int totalCollisions;

    public ChainingHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Node[capacity];
        this.totalCollisions = 0;
    }

    private int hash(int key) {
        return Math.abs(key) % capacity;
    }

    public void insert(int key) {
        int index = hash(key);
        Node newNode = new Node(key);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            // Collision occurred
            totalCollisions++;
            Node current = table[index];
            // Walk to the end of the chain
            while (current.next != null) {
                if (current.key == key) return; // Prevent duplicates if required
                current = current.next;
            }
            if (current.key != key) {
                current.next = newNode;
            }
        }
    }

    public boolean search(int key, int[] probeHolder) {
        int index = hash(key);
        Node current = table[index];
        int probes = 0;

        while (current != null) {
            probes++;
            if (current.key == key) {
                probeHolder[0] = probes;
                return true;
            }
            current = current.next;
        }
        probeHolder[0] = probes == 0 ? 1 : probes; // Minimal 1 probe to check empty index
        return false;
    }

    public boolean delete(int key) {
        int index = hash(key);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("[" + i + "]: ");
            Node current = table[i];
            while (current != null) {
                System.out.print(current.key + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public int getCollisions() { return totalCollisions; }
}
