import java.util.ArrayList;
import java.util.Collections;
/**
 * Main 클래스의 입력을 바탕으로 6가지 UseCase가 실행되는 Library 클래스
 * 
 * @author (Team2 : 2017210103 백재원 , 2017210017 최창환 , 2017210022 박종민 , 2018210032 김태영) 
 * @version (2020.12.07)
 */
public class Library{
    private String name;
    private ArrayList<Book> registeredBooks;
    private ArrayList<Borrower> registeredBorrowers;
    private ArrayList<Loan> registeredLoans;
    private ArrayList<Loan> deletedLoans;

    public Library(String name) 
    {
        this.name = name;
        registeredBooks = new ArrayList<Book>();
        registeredBorrowers = new ArrayList<Borrower>();
        registeredLoans = new ArrayList<Loan>();
        deletedLoans = new ArrayList<Loan>();
    } 

    public void registerOneBorrower(String name) 
    {
        Borrower borrower = new Borrower(name);
        boolean answer = borrower.borrowerNotDuplicated(name,registeredBorrowers);
        if(answer == true) {
            borrower.add(borrower, registeredBorrowers);
            System.out.println("등록이 완료되었습니다.");
        }
        else if(answer == false) {
            System.out.println(name+"은 이미 등록된 이용자입니다.");
        }
    }

    public void registerOneBook(String title, String author , int catalogueNumber) 
    {
        Book book = new Book(catalogueNumber, author, title);
        boolean answer = book.bookNotDuplicated(catalogueNumber, registeredBooks);
        if(answer == true) {
            book.add(book, registeredBooks);
            System.out.println("등록이 완료되었습니다.");
        }
        else if(answer == false) {
            System.out.println(catalogueNumber +"은 이미 등록된 고유번호입니다. 다시 시도해주세요.");
        }
    }

    public void displayBooksForLoan() 
    {
        for(int i = 0; i < registeredBooks.size(); i++){
            Book returnBook = Book.getBook(registeredBooks, i);
            boolean checkBookState = returnBook.checkBookState();
            if(checkBookState) {
                Collections.sort(registeredBooks);
                Book.display(registeredBooks, i);
            }
        }
    }

    public void displayBooksOnLoan() 
    {
        for(int i = 0; i < registeredBooks.size(); i++){
            Book returnBook = Book.getBook(registeredBooks, i);
            boolean checkBookState = returnBook.checkBookState();
            if(!checkBookState) {
                Collections.sort(registeredBooks);
                Book.display(registeredBooks, i);
            }
        }
    }

    public void lendOneBook(String name , int catalogueNumber) 
    {
        Book book = Book.findBook(catalogueNumber, registeredBooks);
        Borrower borrower = Borrower.findBorrower(name, registeredBorrowers);
        if(book == null && borrower == null) {
            System.out.println("이용자와 책이 존재하지 않습니다. 다시 시도해주세요.");  
        } else if(book == null && borrower != null) {
            System.out.println("책이 존재하지 않습니다. 다시 시도해주세요.");  
        } else if(book != null && borrower == null) {
            System.out.println("이용자가 존재하지 않습니다. 다시 시도해주세요.");  
        } else if(book != null && borrower != null) {
            boolean checkBookState = book.checkBookState();
            boolean checkBorrowerState = borrower.checkBorrowerState();
            if(checkBookState && checkBorrowerState) {
                Loan loan = new Loan(book, borrower);
                loan.orderAttach(loan);
                loan.addLoan(loan, registeredLoans);
                System.out.println("대출이 완료되었습니다."); 
            }else if(checkBookState && !checkBorrowerState) {
                System.out.println("이용자가 대출 불가능 상태입니다."); 
            }else if(!checkBookState && checkBorrowerState) {
                System.out.println("책이 대출 불가능 상태입니다."); 
            }else{
                System.out.println("이용자와 책 모두 대출 불가능 상태입니다."); 
            }
        }
    }

    public void returnOneBook(String name , int catalogueNumber) 
    {
        Book book = Book.findBook(catalogueNumber, registeredBooks);
        Borrower borrower = Borrower.findBorrower(name, registeredBorrowers);
        if(book == null && borrower == null) {
            System.out.println("이용자와 책이 존재하지 않습니다. 대출을 다시 시도해주세요.");  
        } else if(book == null && borrower != null) {
            System.out.println("책이 존재하지 않습니다. 대출을 다시 시도해주세요.");  
        } else if(book != null && borrower == null) {
            System.out.println("이용자가 존재하지 않습니다. 대출을 다시 시도해주세요.");  
        } else if(book != null && borrower != null){
            boolean checkBookState = book.checkBookState();
            boolean checkBorrowerState = borrower.checkBorrowerState();
            if(!checkBookState && !checkBorrowerState) {
                Loan loan = Loan.findLoan(book, borrower, registeredLoans);
                if(loan == null){
                    System.out.println("이용자와 대출중인 책의 정보가 일치하지 않습니다. 다시 시도해주세요.");
                }
                else{
                    boolean loanBkState = loan.checkLoanBkState();
                    boolean loanBrState = loan.checkLoanBrState();
                    if(!loanBkState && !loanBrState)
                    {
                        loan.addLoanReturn(loan, deletedLoans);
                        loan.orderDetach(loan);
                        loan.deleteLoan(loan, registeredLoans);
                        System.out.println("반납이 완료되었습니다.");
                    }
                }
            }else if(checkBookState && !checkBorrowerState) {
                System.out.println("책이 대출중이 아닙니다. 반납을 다시 시도해주세요."); 
            }else if(!checkBookState && checkBorrowerState) {
                System.out.println("이용자가 대출중이 아닙니다. 반납을 다시 시도해주세요."); 
            }else{
                System.out.println("이용자와 책 모두 대출중이 아닙니다. 반납을 다시 시도해주세요."); 
            }
        }
    }
}