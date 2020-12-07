import java.util.ArrayList;
/**
 * 책과 관련된 메소드가 실행되는 Book 클래스
 * 
 * @author (Team2 : 2017210103 백재원 , 2017210017 최창환 , 2017210022 박종민 , 2018210032 김태영) 
 * @version (2020.12.07)
 */
public class Book implements Comparable{
    private int catalogueNumber;
    private String author;
    private String title;
    private boolean checkBookState;

    public Book(int catalogueNumber,String author,String title) 
    {
        this.catalogueNumber = catalogueNumber;
        this.author = author;
        this.title = title;
        this.checkBookState = true;
    }

    public boolean bookNotDuplicated(int catalogueNumber, ArrayList<Book> registeredBooks)
    {
        for(int i = 0; i < registeredBooks.size(); i++) {
            if(registeredBooks.get(i).catalogueNumber == catalogueNumber) {
                return false;
            }
        }
        return true;
    }

    public void add(Book book, ArrayList<Book> registeredBooks)
    {
        registeredBooks.add(book);
    }

    public static Book getBook(ArrayList<Book> registeredBooks, int i) 
    {
        return registeredBooks.get(i);
    }

    public boolean checkBookState() 
    {
        return checkBookState;
    }

    public static void display(ArrayList<Book> registeredBooks, int i) 
    {
        System.out.println("책 :" + registeredBooks.get(i).title + " / 저자 : " + registeredBooks.get(i).author + " / 고유번호 : " + registeredBooks.get(i).catalogueNumber);
    }

    public static Book findBook(int catalogueNumber, ArrayList<Book> registeredBooks) 
    {
        for(int i = 0; i < registeredBooks.size(); i++) {
            if(registeredBooks.get(i).catalogueNumber == catalogueNumber) {
                return registeredBooks.get(i);
            }
        }
        return null;
    }

    public void attachBook(Loan loan) 
    {
        this.checkBookState = false;
        loan.attachLoanBk();
    }

    public void detachBook(Loan loan) 
    {
        this.checkBookState = true;
        loan.detachLoanBk();
    }

    @Override
    public int compareTo(Object o)
    {
        return this.catalogueNumber - ((Book)o).catalogueNumber;
    }
}