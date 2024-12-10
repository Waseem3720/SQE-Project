package library;

import java.util.Scanner;

public class LibraryManagementSystemMain {

    public static void main(String[] args) {
        
        Library library = new Library();
        LibraryManagementSystem system = new LibraryManagementSystem(library);
        Scanner scanner = new Scanner(System.in);

       
        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add New Book");
            System.out.println("2. Add New Member");
            System.out.println("3. Display Available Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Check Book Availability");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Is the book available (true/false): ");
                    boolean isAvailable = scanner.nextBoolean();
                    System.out.println(system.addNewBook(bookId, title, author, isAvailable));
                    break;

                case 2:
                    
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.nextLine();
                    System.out.println(system.addNewMember(memberId, memberName));
                    break;

                case 3:
                    
                    System.out.println("Available Books:");
                    for (Book book : library.listBooks()) {
                        String availability = book.isAvailable() ? "Available" : "Not Available";
                        System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() + " by " + book.getAuthor() + " (" + availability + ")");
                    }
                    break;

                case 4:
                    
                    System.out.print("Enter Member ID to borrow a book: ");
                    String borrowerId = scanner.nextLine();
                    Member borrower = library.listMembers().stream()
                            .filter(m -> m.getMemberId().equals(borrowerId))
                            .findFirst()
                            .orElse(null);
                    if (borrower == null) {
                        System.out.println("Member not found.");
                        break;
                    }
                    System.out.print("Enter Book ID to borrow: ");
                    String borrowBookId = scanner.nextLine();
                    System.out.println(system.borrowBook(borrowBookId, borrower));
                    break;

                case 5:
                    
                    System.out.print("Enter Member ID to return a book: ");
                    String returnerId = scanner.nextLine();
                    Member returner = library.listMembers().stream()
                            .filter(m -> m.getMemberId().equals(returnerId))
                            .findFirst()
                            .orElse(null);
                    if (returner == null) {
                        System.out.println("Member not found.");
                        break;
                    }
                    System.out.print("Enter Book ID to return: ");
                    String returnBookId = scanner.nextLine();
                    System.out.println(system.returnBook(returnBookId, returner));
                    break;

                case 6:
                   
                    System.out.print("Enter Book ID to check availability: ");
                    String checkBookId = scanner.nextLine();
                    boolean available = system.checkBookAvailability(checkBookId);
                    System.out.println("Is book available? " + available);
                    break;

                case 7:
                    
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
