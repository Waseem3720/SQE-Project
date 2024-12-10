package library;



public class LibraryManagementSystem {
    private Library library;

    public LibraryManagementSystem(Library library) 
	{
        this.library = library;
    }

    public String addNewBook(String bookId, String title, String author, boolean isAvailable) 
	{
        Book book = new Book(bookId, title, author, isAvailable);
        library.addBook(book);
        return "New book added: " + title;
    }

    public String addNewMember(String memberId, String name) 
	{
        Member member = new Member(memberId, name);
        library.addMember(member);
        return "New member added: " + name;
    }

    public boolean checkBookAvailability(String bookId) 
	{
        return library.isBookAvailable(bookId);
    }

    public String borrowBook(String bookId, Member member) 
	{
        Book book = null;
        for (Book b : library.listBooks()) 
		{
            if (b.getBookId().equals(bookId)) 
			{
                book = b;
                break;
            }
        }
        if (book != null) 
		{
            return member.borrowBook(book);
        }
        return "Book not found.";
    }

    public String returnBook(String bookId, Member member) 
	{
        Book book = null;
        for (Book b : library.listBooks()) 
		{
            if (b.getBookId().equals(bookId)) 
			{
                book = b;
                break;
            }
        }
        if (book != null) 
		{
            return member.returnBook(book);
        }
        return "Book not found.";
    }
}
