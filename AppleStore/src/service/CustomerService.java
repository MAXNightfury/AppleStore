package src.service;

import src.dao.CustomerDAO;
import src.vo.CustomerVO;

import java.sql.Date;
import java.util.Scanner;

public class CustomerService {
    static Scanner scanner = new Scanner(System.in);
    static CustomerDAO customerDAO = new CustomerDAO(); //TODO static으로  올려 놓는게 맞나..? user id 는 이렇게 갖고 있어야 하는게 맞나?
    static boolean isCustomerLogin = false;
    public void customerLogin() {
        String inputCustomerId;
        String inputCustomerPassword;

        System.out.println("[ 로그인 ]");
        System.out.print("아이디: >");
        inputCustomerId = scanner.nextLine();
        System.out.println();
        System.out.print("비밀번호: > ");
        inputCustomerPassword = scanner.nextLine();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setCustomerId(inputCustomerId);
        customerVO.setCustomerPassword(inputCustomerPassword);
        CustomerVO customerVOrealData = null;
        customerVOrealData = customerDAO.customerLogin(customerVO);

        if(customerVOrealData !=null) {
            if (customerVOrealData.getCustomerDeleteDate() != null) { // 값이 있으면 로그인 실패
                isCustomerLogin = false;
                System.out.println("로그인에 실패했습니다.");
            } else if (customerVOrealData.getCustomerId().equals(inputCustomerId)) {
                isCustomerLogin = true;
                System.out.println("로그인에 성공했습니다.");
            } else {
                isCustomerLogin = false;
                System.out.println("로그인에 실패했습니다."); //TODO 로직 뭐 하나 없앨 수 있지 않아? 조건을 좀 줄일 수 있지 않아?
            }
        }else{
            System.out.println("로그인에 실패했습니다.");
        }
    }

    public void customerJoin() {
        int count = 0;
        String customerId;
        String customerPassword;
        String customerName;
        int customerPhoneNumber;
        String customerAddress;
        String customerBornDate;
        String customerSex;

        System.out.println("회원가입을 시작합니다.");
        System.out.print("아이디 입력 >");
        customerId = scanner.nextLine();
        System.out.println();
        System.out.print("비밀번호 입력> ");
        customerPassword = scanner.nextLine();
        System.out.println();
        System.out.print("이름 입력> ");
        customerName = scanner.nextLine();
        System.out.println();
        System.out.print("전화번호 숫자만 입력> ");
        customerPhoneNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        System.out.print("주소 입력> ");
        customerAddress = scanner.nextLine();
        System.out.println();
        System.out.print("생년월일 입력(1999-10-12)> ");
        customerBornDate = scanner.nextLine();
        System.out.println();
        System.out.print("성별 입력 (F, M)> ");
        customerSex = scanner.nextLine();
        System.out.println();

        CustomerVO customerVO = new CustomerVO();
        customerVO.setCustomerId(customerId);
        customerVO.setCustomerPassword(customerPassword);
        customerVO.setCustomerName(customerName);
        customerVO.setCustomerPhoneNumber(customerPhoneNumber);
        customerVO.setCustomerAddress(customerAddress);
        customerVO.setCustomerBornDate(Date.valueOf(customerBornDate));
        customerVO.setCustomerSex(customerSex);

        count = customerDAO.customerJoin(customerVO);
        System.out.println(customerVO.toString());

        if (count == 0) {
            System.out.println("회원가입이 완료되었습니다.");

        } else {
            System.out.println("회원가입이 실패했습니다.");
            //TODO 회원가입 왜 실패했는지 실패했으면 어떻게 할건지
        }

    }

    public void checkCustomerPassword(CustomerVO customerVO) {
        String customerPassword;
        boolean isConfirmedPassword;
        System.out.print("기존 비밀번호를 입력해주세요> ");
        customerPassword = scanner.nextLine();
        customerVO.setCustomerPassword(customerPassword);
        isConfirmedPassword = customerDAO.checkCustomerPassword(customerVO);

        if (isConfirmedPassword == true) {
            System.out.println("기존 비밀번호를 확인했습니다.");

        } else {
            System.out.println("기존 비밀번호가 일치하지 않습니다.");
            System.exit(0);
            // TODO sys 다운? 아니면 3번까지 다시 받기 ?
        }
        isConfirmedPassword = false;
    }

    public void customerUpdatePassword(CustomerVO customerVO) {
        int count = 0;
        System.out.println("비밀번호를 변경합니다.");
        //checkCustomerPassword 호출할때 ID 가주가야하는데
        // 아이디를 파라미터가 가지고 있는 상태임
        checkCustomerPassword(customerVO);

        String newPassword;
        System.out.print("새 비밀번호를 입력해주세요> ");
        newPassword = scanner.nextLine();
        customerVO.setCustomerPassword(newPassword);
        count = customerDAO.customerUpdatePassword(customerVO);

        if (count == 1) {
            System.out.println("비밀번호변경이 완료되었습니다.");
        } else {
            // TODO 비밀번호 조건?
        }

    }


    public void customerUpdateName(CustomerVO customerVO) {
        int count = 0;
        System.out.println("이름을 변경합니다.");
        //checkCustomerPassword 호출할때 ID 가주가야하는데
        // 아이디를 파라미터가 가지고 있는 상태임
        checkCustomerPassword(customerVO);


        String newCsutomerName;
        System.out.print("새 이름을 입력해주세요> ");
        newCsutomerName = scanner.nextLine();
        customerVO.setCustomerName(newCsutomerName);
        count = customerDAO.customerUpdateName(customerVO);

        if (count == 1) {
            System.out.println("이름 변경이 완료되었습니다.");
        } else {
            System.out.println("이름 변경이 실패했습니다.");
        }
    }


    public void customerUpdatePhoneNumber(CustomerVO customerVO) {
        int count = 0;
        System.out.println("전화번호를 변경합니다.");
        //checkCustomerPassword 호출할때 ID 가주가야하는데
        // 아이디를 파라미터가 가지고 있는 상태임
        checkCustomerPassword(customerVO);

        int newCsutomerPhoneNumber;
        System.out.print("새 전화번호를 입력해주세요> ");
        newCsutomerPhoneNumber = scanner.nextInt();
        scanner.nextLine();
        customerVO.setCustomerPhoneNumber(newCsutomerPhoneNumber);
        count = customerDAO.customerUpdatePhoneNumber(customerVO);

        if (count == 1) {
            System.out.println("전화번호 변경이 완료되었습니다.");
        } else {
            //TODO 전화번호 형식 ?
            System.out.println("전화번호 변경이 실패했습니다.");
        }
    }

    public void customerUpdateAddress(CustomerVO customerVO) {
        int count = 0;
        System.out.println("주소를 변경합니다.");
        //checkCustomerPassword 호출할때 ID 가주가야하는데
        // 아이디를 파라미터가 가지고 있는 상태임
        checkCustomerPassword(customerVO);

        String newCsutomerAddress;
        System.out.print("새 주소를 입력해주세요> ");
        newCsutomerAddress = scanner.nextLine();

        customerVO.setCustomerAddress(newCsutomerAddress);
        count = customerDAO.customerUpdateAddress(customerVO);

        if (count == 1) {
            System.out.println("주소 변경이 완료되었습니다.");
        } else {
            //TODO 주소 형식 ?
            System.out.println("주소 변경이 실패했습니다.");
        }
    }

    public void customerDelete(CustomerVO customerVO) { // delete 쿼리를 날리는게 아니라 delete-date 에 timestamp 삽입
        int count = 0;
        System.out.println("회원탈퇴를 진행하시겠습니까?");
        //checkCustomerPassword 호출할때 ID 가주가야하는데
        // 아이디를 파라미터가 가지고 있는 상태임
        checkCustomerPassword(customerVO);
        count = customerDAO.customerDelete(customerVO);

        if (count == 1) {
            System.out.println("탈퇴처리가 완료되었습니다.");
        } else {
            System.out.println("탈퇴 처리가 실패되었습니다.");
        }
    }


}
