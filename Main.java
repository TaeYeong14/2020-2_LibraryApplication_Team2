import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * 도서관 관리 시스템의 6가지 UseCase를 실행시키기 위한 메뉴 선택을 진행하는 Main 클래스
 * 
 * @author (Team2 : 2017210103 백재원 , 2017210017 최창환 , 2017210022 박종민 , 2018210032 김태영) 
 * @version (2020.12.07)
 */
public class Main{
    private static Library lib = new Library("Team2 도서관");
    
    public static void main(String[] args) throws Exception
    {
        Scanner scan = new Scanner(System.in);

        InputStreamReader insr = new InputStreamReader(System.in);
        BufferedReader inbr = new BufferedReader(insr);

        while(true) 
        {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("도서관 관리 시스템 >> 1)이용자 등록 2)책 등록 3)대출 가능한 책 출력 4)대출 중인 책 출력 5)책 대출 6)책 반납 0)시스템 종료");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.print("번호 선택 : ");
            int number = scan.nextInt();

            if(number == 0){
                System.out.println("[0. 시스템 종료]");
                System.out.println("시스템을 종료합니다...");
                break;
            }

            else if(number == 1){
                System.out.println("[1. 이용자 등록]");
                System.out.print("등록할 이용자 이름을 입력해주세요 : ");
                String name = scan.next();
                lib.registerOneBorrower(name);
            }
            else if(number == 2){
                System.out.println("[2. 책 등록]");
                System.out.print("등록할 책의 이름을 입력해주세요 : ");
                String title = scan.next();
                System.out.print("등록할 책의 저자를 입력해주세요 : ");
                String author = inbr.readLine();
                System.out.print("등록할 책의 고유번호를 입력해주세요 : ");
                int catalogueNumber = scan.nextInt();
                lib.registerOneBook(title, author, catalogueNumber);
            }
            else if(number == 3){
                System.out.println("[3. 대출 가능한 책 출력]");
                System.out.println("대여 가능한 책은 다음과 같습니다.");
                lib.displayBooksForLoan();
            }
            else if(number == 4){
                System.out.println("[4. 대출 중인 책 출력]");
                System.out.println("현재 대출 중인 책은 다음과 같습니다.");
                lib.displayBooksOnLoan();
            }
            else if(number == 5){
                System.out.println("[5. 책 대출]");
                System.out.print("대출할 이용자 이름을 입력해주세요 : ");
                String name = scan.next();
                System.out.print("대출할 책의 고유번호를 입력해주세요 : ");
                int catalogueNumber = scan.nextInt();
                lib.lendOneBook(name, catalogueNumber);
            }
            else if(number == 6){
                System.out.println("[6. 책 반납]");
                System.out.print("반납할 이용자 이름을 입력해주세요 : ");
                String name = scan.next();
                System.out.print("반납할 책의 고유번호를 입력해주세요 : ");
                int num = scan.nextInt();
                lib.returnOneBook(name, num);
            }
            else{
                System.out.println("Error!!!");
                System.out.println("올바르지 않은 번호 입력입니다. 다시 입력해주세요.");
            }
        }
        scan.close();
    }
}