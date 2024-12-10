package library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Book> listBooks() {
        return books;
    }

    public List<Member> listMembers() 
    {
        return members;
    }

    public boolean isBookAvailable(String bookId) 
    {
        for (Book book : books) 
        {
            if (book.getBookId().equals(bookId) && book.isAvailable()) 
            {
                return true;
            }
        }
        return false;
    }
}
