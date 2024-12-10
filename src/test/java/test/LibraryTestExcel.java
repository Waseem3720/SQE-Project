package test;

import library.Book;
import library.Library;
import library.Member;
import test.LibraryDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LibraryTestExcel {

    @DataProvider(name = "LibraryExcel")
    public Object[][] getLibraryData() throws IOException {
        String filePath = "E:\\SQE Project\\SQEProject\\src\\test\\resources\\LibraryData.xlsx"; // Adjust the file path if necessary
        List<Object[]> testData = LibraryDataReader.readLibraryTestData(filePath);
        return testData.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "LibraryExcel", groups = {"libraryTestExcel"}, priority = 1)
    public void testAddBookAndMember(String bookId, String title, String author, boolean isAvailable, String memberId, String memberName) {
        Library library = new Library();

        // Add book and member to library
        Book book = new Book(bookId, title, author, isAvailable);
        library.addBook(book);
        Member member = new Member(memberId, memberName);
        library.addMember(member);

        // Assertions to verify the library contains the book and member
        Assert.assertTrue(isBookInLibrary(library, bookId, title, author, isAvailable), "Library should contain the added book");
        Assert.assertTrue(isMemberInLibrary(library, memberId, memberName), "Library should contain the added member");
    }

    private boolean isBookInLibrary(Library library, String bookId, String title, String author, boolean isAvailable) {
        return library.listBooks().stream().anyMatch(
            b -> b.getBookId().equals(bookId) &&
                 b.getTitle().equals(title) &&
                 b.getAuthor().equals(author) &&
                 b.isAvailable() == isAvailable
        );
    }

    private boolean isMemberInLibrary(Library library, String memberId, String memberName) {
        return library.listMembers().stream().anyMatch(
            m -> m.getMemberId().equals(memberId) &&
                 m.getName().equals(memberName)
        );
    }
}
