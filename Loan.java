import java.util.ArrayList;
/**
 * 대출과 반납이 관련된 메소드가 실행되는 Loan 클래스
 * 
 * @author (Team2 : 2017210103 백재원 , 2017210017 최창환 , 2017210022 박종민 , 2018210032 김태영) 
 * @version (2020.12.07)
 */
public class Loan {
    private Book book;
    private Borrower borrower;
    private boolean loanBkState;
    private boolean loanBrState;

    public Loan(Book book, Borrower borrower)
    {
        this.book = book;
        this.borrower = borrower;
        this.loanBkState = true;
        this.loanBrState = true;
    }

    public void orderAttach(Loan loan)
    {
        book.attachBook(loan);
        borrower.attachBorrower(loan);
    }
    
    public void addLoan(Loan loan, ArrayList<Loan> registeredLoans)
    {
        registeredLoans.add(loan);
    }
    
    public static Loan findLoan(Book book, Borrower borrower, ArrayList<Loan> registeredLoans)
    {
        for(int i = 0; i < registeredLoans.size(); i++) {
            if((registeredLoans.get(i).book == book) && (registeredLoans.get(i).borrower == borrower)) {
                return registeredLoans.get(i);
            }
        }
        return null;
    } 
    
    public boolean checkLoanBkState() 
    {
        return loanBkState;
    }

    public boolean checkLoanBrState() 
    {
        return loanBrState;
    }
    
    public void addLoanReturn(Loan loan, ArrayList<Loan> deletedLoans)
    {
        deletedLoans.add(loan);
    }
    
    public void orderDetach(Loan loan)
    {
        book.detachBook(loan);
        borrower.detachBorrower(loan);
    }

    public void attachLoanBk()
    {
        this.loanBkState = false;
    }

    public void attachLoanBr()
    {
        this.loanBrState = false;
    }
    
    public void detachLoanBk()
    {
        this.loanBkState = true;
    }

    public void detachLoanBr()
    {
        this.loanBrState = true;
    }

    public void deleteLoan(Loan loan, ArrayList<Loan> registeredLoans)
    {
        registeredLoans.remove(loan);
    }
}
