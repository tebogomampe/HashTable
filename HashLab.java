import java.util.Scanner;

public class HashLab {
    private static ChainingHashTable chainingTable;
    private static LinearProbingTable linearTable;
    private static DoubleHashingTable doubleTable;
    private static int currentSize = 11; // Default starting capacity

    public static void main(String[] args) {
        resetTables(currentSize);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n================ HashLab ================");
            System.out.println(" 1) Insert one key");
            System.out.println(" 2) Insert several keys");
            System.out.println(" 3) Search for a key");
            System.out.println(" 4) Delete a key");
            System.out.println(" 5) Display all three tables");
            System.out.println(" 6) Statistics");
            System.out.println(" 7) Reset with a new table size");
            System.out.println(" 8) Exit");
            System.out.println("==========================================");
            System.out.print("Choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a valid menu number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    int key = Integer.parseInt(scanner.nextLine().trim());
                    insertAcrossAll(key);
                    break;
                case 2:
                    System.out.print("Enter keys separated by spaces: ");
                    String[] items = scanner.nextLine().trim().split("\\s+");
                    for (String item : items) {
                        if (!item.isEmpty()) {
                            insertAcrossAll(Integer.parseInt(item));
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter key to search for: ");
                    int searchKey = Integer.parseInt(scanner.nextLine().trim());
                    searchAcrossAll(searchKey);
                    break;
                case 4:
                    System.out.print("Enter key to delete: ");
                    int deleteKey = Integer.parseInt(scanner.nextLine().trim());
                    deleteAcrossAll(deleteKey);
                    break;
                case 5:
                    System.out.println("\n--- Separate Chaining Table ---");
                    chainingTable.display();
                    System.out.println("\n--- Linear Probing Table ---");
                    linearTable.display();
                    System.out.println("\n--- Double Hashing Table ---");
                    doubleTable.display();
                    break;
                case 6:
                    System.out.println("\n--- Statistical Report ---");
                    System.out.println("Separate Chaining Total Collisions: " + chainingTable.getCollisions());
                    System.out.println("Linear Probing Total Collisions: " + linearTable.getCollisions());
                    System.out.println("Double Hashing Total Collisions: " + doubleTable.getCollisions());
                    break;
                case 7:
                    System.out.print("Enter new table capacity size: ");
                    currentSize = Integer.parseInt(scanner.nextLine().trim());
                    resetTables(currentSize);
                    System.out.println("Tables reinitialized to capacity: " + currentSize);
                    break;
                case 8:
                    System.out.println("Exiting HashLab. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid selection. Try numbers between 1 and 8.");
            }
        }
    }

    private static void resetTables(int capacity) {
        chainingTable = new ChainingHashTable(capacity);
        linearTable = new LinearProbingTable(capacity);
        doubleTable = new DoubleHashingTable(capacity);
    }

    private static void insertAcrossAll(int key) {
        chainingTable.insert(key);
        linearTable.insert(key);
        doubleTable.insert(key);
        System.out.println("Key " + key + " processed across all data models.");
    }

    private static void searchAcrossAll(int key) {
        int[] chP = new int[1];
        int[] lpP = new int[1];
        int[] dhP = new int[1];

        boolean chFound = chainingTable.search(key, chP);
        boolean lpFound = linearTable.search(key, lpP);
        boolean dhFound = doubleTable.search(key, dhP);

        System.out.println("\nSearch Evaluation for [" + key + "]:");
        System.out.println(" -> Chaining Table: Found = " + chFound + " (Probes: " + chP[0] + ")");
        System.out.println(" -> Linear Probing: Found = " + lpFound + " (Probes: " + lpP[0] + ")");
        System.out.println(" -> Double Hashing: Found = " + dhFound + " (Probes: " + dhP[0] + ")");
    }

    private static void deleteAcrossAll(int key) {
        boolean chDel = chainingTable.delete(key);
        boolean lpDel = linearTable.delete(key);
        boolean dhDel = doubleTable.delete(key);
        System.out.println("Deletion results: Chaining=" + chDel + ", Linear=" + lpDel + ", Double=" + dhDel);
    }
}
