package test;

import library.Book;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BookDataReader;

import java.io.IOException;
import java.util.List;

public class BookTestExcel {

    @DataProvider(name = "BookExcel")
    public Object[][] getBookData() throws IOException {
        String filePath = "E:\\SQE Project\\SQEProject\\src\\test\\resources\\BookExcel.xlsx"; // Ensure correct file extension
        List<Object[]> testData = BookDataReader.readBookTestData(filePath);
        return testData.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "BookExcel",   groups= {"BookTestExcel"},priority = 1)
    public void testAddNewBook(String bookId, String title, String author, boolean isAvailable) {
      
        Book book = new Book(bookId, title, author, isAvailable);

    }
}
