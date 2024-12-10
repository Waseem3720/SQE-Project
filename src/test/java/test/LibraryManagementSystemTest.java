package test;

import library.LibraryManagementSystem;
import library.Library;
import library.Book;
import library.Member;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LibraryManagementSystemTest {

    @Test(dataProvider = "newBookData", priority = 1, groups = "Book")
    public void testAddNewBook(String bookId, String title, String author, boolean isAvailable) {
        Library library = new Library();
        LibraryManagementSystem lms = new LibraryManagementSystem(library);
        String result = lms.addNewBook(bookId, title, author, isAvailable);
        assertEquals(result, "New book added: " + title);

        boolean bookExists = library.listBooks().stream()
                                    .anyMatch(book -> book.getBookId().equals(bookId) &&
                                                      book.getTitle().equals(title) &&
                                                      book.isAvailable() == isAvailable);
        assertTrue(bookExists, "Book should be added to the library.");
    }

    @Test(dataProvider = "newMemberData", priority = 2, groups = "Member")
    public void testAddNewMember(String memberId, String name) {
        Library library = new Library();
        LibraryManagementSystem lms = new LibraryManagementSystem(library);
        String result = lms.addNewMember(memberId, name);
        assertEquals(result, "New member added: " + name);
        
        boolean memberExists = library.listMembers().stream()
                                       .anyMatch(member -> member.getMemberId().equals(memberId) &&
                                                           member.getName().equals(name));
        assertTrue(memberExists, "Member should be added to the library.");
    }

    @Test(dataProvider = "checkBookAvailabilityData", priority = 3, groups = "Book")
    public void testCheckBookAvailability(String bookId, boolean expectedAvailability) {
        Library library = new Library();
        LibraryManagementSystem lms = new LibraryManagementSystem(library);
        Book book = new Book(bookId, "Book Title", "Author Name", expectedAvailability);
        library.addBook(book);
        boolean isAvailable = lms.checkBookAvailability(bookId);
        assertEquals(isAvailable, expectedAvailability);
    }

    // DataProviders

    @DataProvider
    public Object[][] newBookData() {
        return new Object[][] {
            {"B001", "City of Hearts", "Ahmed Faraz", true},
            {"B002", "Dreams of the World", "Mahmood Khan", false},
            {"B003", "The World Beyond", "Ayesha Ali", true}
        };
    }

    @DataProvider
    public Object[][] newMemberData() {
        return new Object[][] {
            {"M001", "Ali Ahmed"},
            {"M002", "Sara Ali"},
            {"M003", "Aslam Raza"}
        };
    }

    @DataProvider
    public Object[][] checkBookAvailabilityData() {
        return new Object[][] {
            {"B001", true},
            {"B002", false},
            {"B003", true}
        };
    }
}
