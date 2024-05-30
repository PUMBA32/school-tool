package login_registr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class LoginRegistrationSystem {
	private String path = "D:\\Coding\\JAVA\\login_registr\\src\\login_registr\\data.txt";	
	
	private String name;
	private String pass;
	private String subject;
	private String className;
	
	private int accountIndex;  // кол-во аккаунтов 
	
	public LoginRegistrationSystem(String path) {
		this.path = path;
	}
	
	public LoginRegistrationSystem() {}
	
	// Вход в систему
	public int login() {
		File file = new File(path);
		Scanner scanner = new Scanner(System.in);
		
		boolean isThere = false;
		
		System.out.println("Enter name: ");
		String name = scanner.next();
		
		System.out.println("Enter pass: ");
		String pass = scanner.next();
	
		try {
			Scanner fileScanner = new Scanner(file);
			
			int k = 1;
			
			while(fileScanner.hasNextLine()) {
				String[] data = fileScanner.nextLine().split(" ");
				if(data[0].equals(name) && data[1].equals(pass)) {
					accountIndex = k;
					return 0;
				}
				k++;
			}
		}
		catch(IOException e) {
			System.out.println("log: Login Error\n");
		}
		return 1;
	}
	
	// Очевидно регистрация
	public void registration() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter name: ");
		String name = s.nextLine();
		
		System.out.print("Enter pass: ");
		String pass = s.nextLine();
		
		System.out.print("Enter class name (for example 8A): ");
		String className = s.nextLine();

		System.out.print("Enter subject: ");
		String subject = s.nextLine();
		
		String stroke = name + " " + pass + " " + subject + " " + className + "\n";
		
		try {
			FileWriter writer = new FileWriter(path, true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.write(stroke);
			bWriter.close();
		}
		catch (IOException e) {
			System.out.println("log: Registration Error");
		}
		
		this.name = name;
		this.pass = pass;
		this.subject = subject;
		this.className = className;
	}
	
	public int getAccountIndex() {
		return accountIndex;
	}
	
	
	public String getclassName() {
		return className;
	}
	
	// Получение информации по индексу аккаунта (порядкового номера в списке аккаунтов)
	public void getInfoByLoginNumber(int lNumber) {
		File file = new File(path);
		int i = 1;
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String data = scan.nextLine();
				if(i == lNumber) {
					String[] arr_data = data.split(" ");
					this.name = arr_data[0];
					this.pass = arr_data[1];
					this.subject = arr_data[2];
					this.className = arr_data[3];
					break;
				}
				i++;
			}
		}
		catch(IOException e) {}
	}
	
	public void showInfo() {
		System.out.println("\n=======YOUR-PROFILE=======");
		System.out.println("> Name: \t" + name);
		System.out.println("> Pass: \t" + pass);
		System.out.println("> Subj: \t" + subject);
		System.out.println("> Class:\t" + className);
		System.out.println("==========================\n");
	}
	
	// Установка актуального индекса аккунта
	public int setAccountIndex() {
		File file = new File(path);
		int k = 0;
		try {
			Scanner scanner = new Scanner(file);

			while(scanner.hasNextLine()) { 
				scanner.nextLine();
				k++; 
			}
		}
		catch(IOException e) { 
			System.out.println("\nError setting account index :(\n"); 
		} 
		return k;
	}
}
