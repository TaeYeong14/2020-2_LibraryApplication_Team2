import java.util.ArrayList;
/**
 * 이용자와 관련된 메소드가 실행되는 Borrower 클래스
 * 
 * @author (Team2 : 2017210103 백재원 , 2017210017 최창환 , 2017210022 박종민 , 2018210032 김태영) 
 * @version (2020.12.07)
 */
public class Borrower {
    private String name;
    private boolean checkBorrowerState;
    public Borrower(String name) 
    {
        this.name = name;
        this.checkBorrowerState = true;
    }

    public boolean borrowerNotDuplicated(String name, ArrayList<Borrower> registeredBorrowers) 
    {
        for(int i=0; i < registeredBorrowers.size(); i++) {
            if(registeredBorrowers.get(i).name.equals(name)) {
                return false;
            }
        }
        return true;   
    }

    public void add(Borrower borrower, ArrayList<Borrower> registeredBorrowers)
    {
        registeredBorrowers.add(borrower);
    }

    public static Borrower findBorrower(String name, ArrayList<Borrower> registeredBorrowers) 
    {
        for(int i = 0; i < registeredBorrowers.size(); i++) {
            if(registeredBorrowers.get(i).name.equals(name)) {
                return registeredBorrowers.get(i);
            }
        }
        return null;
    }

    public boolean checkBorrowerState() 
    {
        return checkBorrowerState;
    }

    public void attachBorrower(Loan loan) 
    {
        this.checkBorrowerState = false;
        loan.attachLoanBr();
    }

    public void detachBorrower(Loan loan) 
    {
        this.checkBorrowerState = true;
        loan.detachLoanBr();
    }
}