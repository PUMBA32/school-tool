package login_registr;

import java.util.Scanner;

public class Main {
	public static int loginNumber;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LoginRegistrationSystem LRSystem = new LoginRegistrationSystem();
		
		boolean flow = true;
		
		while(flow) {
			System.out.println("[1] - Registration");
			System.out.println("[2] - Login");
			System.out.println("[3] - Exit");
			
			System.out.print("\n>>> ");
			String choice = scanner.nextLine();
			
			if(choice.equals("1")) {
				LRSystem.registration();
				loginNumber = LRSystem.setAccountIndex();
			}
			else if(choice.equals("2")) {
				int r = LRSystem.login();
				if(r == 1) {
					System.out.println("You don't have account there please register\n");
					continue;
				}
				loginNumber = LRSystem.getAccountIndex();
			}
			else { flow = false; break; }
			
			System.out.println("\nWELCOME!\n");
			
			while(true) {
				StudentList list = new StudentList(loginNumber);
				Scanner scanner2 = new Scanner(System.in);
				
				System.out.println("[1] - Profile");
				System.out.println("[2] - Students List");
				System.out.println("[3] - Update Students List");
				System.out.println("[4] - Exit");
				
				System.out.print("\n>>> ");
				String choice2 = scanner.nextLine();
				
				if(choice2.equals("1")) {
					LRSystem.getInfoByLoginNumber(loginNumber);
					LRSystem.showInfo();
				}
				else if(choice2.equals("2")) {
					list.showInfo();
				}
				else if(choice2.equals("3")) {
					studentListMenu();
				}
				else { flow = false; break; }
			}
		}		
	}
	
	// Отоброжение дочернего меню для удаление или добавление ученика
	public static void studentListMenu() {
		Scanner scanner = new Scanner(System.in);
		StudentList list = new StudentList(loginNumber);
		
		System.out.println("\n[1] - Add new student");
		System.out.println("[2] - Delete student");
		System.out.println("[3] - Exit\n");
		
		System.out.print(">>> ");
		String choice = scanner.nextLine();
		
		if(choice.equals("1")) {
			list.addNewStudent();
		}
		else if(choice.equals("2")) {
			list.deleteStudent();
		}
		System.out.println();
	}
}
