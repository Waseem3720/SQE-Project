package test;

import library.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class LibraryTest {

    @Test(dataProvider = "bookData", groups = {"bookTests", "addTests"}, priority = 1)
    public void testAddBook(Book book) {
        Library library = new Library();
        library.addBook(book);
        List<Book> books = library.listBooks();
        assertTrue(books.contains(book), "The book should be added to the library");
    }

    @Test(dataProvider = "memberData", groups = {"memberTests", "addTests"}, priority = 2)
    public void testAddMember(Member member) {
        Library library = new Library();
        library.addMember(member);
        List<Member> members = library.listMembers();
        assertTrue(members.contains(member), "The member should be added to the library");
    }

    @Test(dataProvider = "listBooksData", groups = {"bookTests", "listTests"}, priority = 3)
    public void testListBooks(Book book1, Book book2) {
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        List<Book> books = library.listBooks();
        assertEquals(books.size(), 2, "There should be 2 books in the library");
    }

    @Test(dataProvider = "listMembersData", groups = {"memberTests", "listTests"}, priority = 4)
    public void testListMembers(Member member1, Member member2) {
        Library library = new Library();
        library.addMember(member1);
        library.addMember(member2);
        List<Member> members = library.listMembers();
        assertEquals(members.size(), 2, "There should be 2 members in the library");
    }

    @Test(dataProvider = "bookAvailabilityData", groups = {"bookTests", "availabilityTests"}, priority = 5)
    public void testIsBookAvailable(String bookId, boolean expectedAvailability) {
        Library library = new Library();
        // Define books with their availability
        Book book1 = new Book("B001", "City of Hearts", "Ahmed Faraz", true);
        Book book2 = new Book("B002", "Dreams of the World", "Mahmood Khan", false);
        Book book3 = new Book("B003", "World of Sufism", "Abdullah Khan", false);
        Book book4 = new Book("B004", "The Journey", "Nazia Iqbal", true); // Ensure this book is added correctly
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4); // Add book B004 to the library

        // Check the availability of a given bookId
        boolean actualAvailability = library.isBookAvailable(bookId);

        // Assert availability
        assertEquals(actualAvailability, expectedAvailability, "Book availability should match the expected value.");
    }

    @DataProvider
    public Object[][] bookData() {
        return new Object[][] {
            {new Book("B001", "City of Hearts", "Ahmed Faraz", true)},
            {new Book("B002", "Dreams of the World", "Mahmood Khan", true)},
            {new Book("B003", "World of Sufism", "Abdullah Khan", false)}
        };
    }

    @DataProvider
    public Object[][] memberData() {
        return new Object[][] {
            {new Member("M001", "Ali Ahmed")},
            {new Member("M002", "Sara Ali")},
            {new Member("M003", "Aslam Raza")}
        };
    }

    @DataProvider
    public Object[][] listBooksData() {
        return new Object[][] {
            {new Book("B001", "City of Hearts", "Ahmed Faraz", true), new Book("B002", "Dreams of the World", "Mahmood Khan", true)},
            {new Book("B003", "World of Sufism", "Abdullah Khan", false), new Book("B004", "The Journey", "Nazia Iqbal", true)}
        };
    }

    @DataProvider
    public Object[][] listMembersData() {
        return new Object[][] {
            {new Member("M001", "Ali Ahmed"), new Member("M002", "Sara Ali")},
            {new Member("M003", "Aslam Raza"), new Member("M004", "Zainab Khan")}
        };
    }

    @DataProvider
    public Object[][] bookAvailabilityData() {
        return new Object[][] {
            {"B001", true},   
            {"B002", false}, 
            {"B003", false},  
            {"B004", true}    
        };
    }
}
