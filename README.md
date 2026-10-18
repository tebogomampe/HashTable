HashLab - Collision Handling Simulator

A console-based Java application that demonstrates and compares three distinct hash table collision-handling techniques concurrently: **Open Hashing (Separate Chaining)**, **Linear Probing**, and **Double Hashing**. 

Every inserted key is mapped across all three data architectures at the same time to accurately track and compare individual probe behavior and collision statistics under identical data streams.

Implementation Constraints

To fulfill strict academic requirements, this project is built entirely from scratch:
- **No Built-in Utilities:** Zero usage of Java's internal collections (`HashMap`, `HashSet`, `ArrayList`, etc.).
- **Pure Custom Structures:** Implemented entirely using primitive standard arrays and a manually written Linked List node.
- **Pure Terminal Interface:** 100% text-based command-line interface via the standard terminal. No Swing, JavaFX, web components, file systems, or database frameworks are used.

Functional Requirements Covered

The interactive program provides a menu containing the following utilities:
1. **Insert one key:** Maps a single integer across all three systems.
2. **Insert several keys:** Iterates and adds a batch of space-separated integers.
3. **Search for a key:** Tests lookup speeds and outputs exact probe metrics per structure.
4. **Delete a key:** Removes an integer (uses Tombstone markers for open-addressing tables).
5. **Display all three tables:** Outputs visual text maps of all current array indices.
6. **Statistics:** Displays total recorded structural collisions.
7. **Reset with a new table size:** Flushes active memory and prompts for a fresh capacity limit.
8. **Exit:** Safely terminates the runner.

Getting Started

Prerequisites
- Java Development Kit (JDK) 8 or higher installed.

### Compilation
Open your system's Command Prompt (CMD) or terminal, navigate to the directory containing your project source file, and execute:
```bash
javac HashLab.java
```

### Execution
Run the compiled byte-code driver instantly without any extra arguments or GUI setups:
```bash
java HashLab
```

Validated Test Scenarios
- **Standard Insertion:** Handles routine keys smoothly inside bounds.
- **Existing vs. Missing Keys:** Accurately steps through loops to calculate probe counts.
- **Collision Handling:** Verifies pointer grouping and secondary hash increments.
- **Delete and Search:** Validates that searches don't break after an item is removed.
- **Duplicates & Capacities:** Prevents duplicate keys and intercepts overflows gracefully.
