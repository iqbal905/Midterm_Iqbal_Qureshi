package ca.gbc.midterm_iqbal_qureshi;

import java.util.*;

public class Main {
    private static List<String> table = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int currentNumber = -1;

    public static void main(String[] args) {
        System.out.println("=== Multiplication Table Application ===");
        while (true) {
            System.out.println("\n1. Generate Table");
            System.out.println("2. View History");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = getInt();

            switch (choice) {
                case 1 -> generateTable();
                case 2 -> showHistory();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void generateTable() {
        System.out.print("Enter a number: ");
        currentNumber = getInt();

        table.clear();
        for (int i = 1; i <= 10; i++) {
            table.add(currentNumber + " × " + i + " = " + (currentNumber * i));
        }

        if (!DataStore.history.contains(currentNumber))
            DataStore.history.add(currentNumber);

        printTable();

        while (true) {
            System.out.print("\nEnter row number to delete (or 0 to finish): ");
            int index = getInt();
            if (index == 0) break;
            if (index > 0 && index <= table.size()) {
                String removed = table.remove(index - 1);
                System.out.println("Deleted: " + removed);
                printTable();
            } else {
                System.out.println("Invalid index!");
            }
        }
    }

    private static void printTable() {
        System.out.println("\nMultiplication Table for " + currentNumber + ":");
        for (int i = 0; i < table.size(); i++) {
            System.out.println((i + 1) + ". " + table.get(i));
        }
    }

    private static void showHistory() {
        if (DataStore.history.isEmpty()) {
            System.out.println("No history yet!");
        } else {
            System.out.println("Tables generated for: " + DataStore.history);
        }
    }

    private static int getInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
