package library;



public class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) 
	{
        this.memberId = memberId;
        this.name = name;
    }

    public String borrowBook(Book book) 
	{
        if (book.isAvailable()) {
            book.setAvailable(false);
            return "Book borrowed: " + book.getTitle();
        } else {
            return "Book is currently unavailable.";
        }
    }

    public String returnBook(Book book) 
	{
        book.setAvailable(true);
        return "Book returned: " + book.getTitle();
    }

    public String getMemberId() 
	{
        return memberId;
    }

    public String getName() 
	{
        return name;
    }

    public String getDetails() 
	{
        return "Member ID: " + memberId + ", Name: " + name;
    }
}
