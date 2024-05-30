package login_registr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentList {
	private String path; 
	private int numOfNotes = 0; 
	private ArrayList<String> list;  // Тут хранятся строки с инфой о студентах из текущего файла
	
	
	// Установка актуального пути к файлу с инфой на основе порядкового номера текущего аккаунта
	public StudentList(int accountIndex) {  
		this.path = "D:\\Coding\\JAVA\\login_registr\\src\\login_registr\\students\\students" + accountIndex + ".txt";
	}	
	
	public int getNumOfNotes() {
		return numOfNotes;
	}
	
	public void addNewStudent() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter first name of student: ");
		String f_name = scan.nextLine();
		
		System.out.print("Enter second name of student: ");
		String s_name = scan.nextLine();
	
		System.out.print("Enter age of student: ");
		String age = scan.nextLine();
		
		String text = f_name + " " + s_name + " " + age + "\n";
		
		try {				
			FileWriter writer = new FileWriter(path, true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.write(text);
			bWriter.close();
			
			System.out.println("\nNew student was written successfully!\n");
		}
		catch(IOException e) {
			System.out.println("Write error!\n");
		}
	}
	
	// Очищение всего листа
	private void clearAllList() {
		try {
			PrintWriter writer = new PrintWriter(path);
			writer.println();
			writer.close();
		}
		catch(IOException e) {
			System.out.println("\n!!! Cleaning list error\n");
		}
	}
	
	// Удаление студента по его имени
	public boolean deleteStudent() {
	    updateList();

	    Scanner scanner = new Scanner(System.in);
	    File file = new File(path);

	    boolean studentHere = false;

	    if(list.size() == 0) {  // Проверка является ли список пустым
	        System.out.println("\nYou don't have students");
	    } else {
	        System.out.print("\nEnter first name of student that you want to delete: ");
	        String choice = scanner.nextLine().trim();
	        
	        // Проверка на наличие имени ученика в списке
	        for(String stroke : list) {
	            String name = (stroke.split(" "))[0].trim();
	            System.out.println(name + " - " + choice);
	            if(choice.equals(name)) {
	                studentHere = true;
	                break;
	            }
	        }
	        
	        if(!studentHere) {
	            System.out.println("\nError: There is no such student in list!\n");
	            return false;
	        }
	        
	        // Создание нового листа со студентами (без удаленного студента)
	        ArrayList<String> newList = new ArrayList<>();
	        
	        for(String stroke : list) {
	            String[] data = stroke.split(" ");
	            String student_name = data[0];
	            if(!choice.equals(student_name)) {
	                newList.add(stroke);
	            }
	        }

	        clearAllList();  // Очистка прежнего файла с инфой о студентах
	        
	        // Запись обновленногол листа в файл
	        try (FileWriter writer = new FileWriter(file);
	             BufferedWriter bWriter = new BufferedWriter(writer)) {

	            for(String stroke : newList) {
	                bWriter.write(stroke);
	                bWriter.newLine();
	            }

	        } catch (IOException e) {
	            System.out.println("\n!!! student was not deleted error\n");
	            return false;
	        }

	        list = newList;
	    }
	    System.out.println("\nStudent was deleted successfully!\n");
	    return true;
	}
	
	// Отображение списка всех имеющихся студентов
	public void showInfo() {
		System.out.println();
		
		updateList();
		System.out.println("=====================================");
		
		if(list.isEmpty()) {
			System.out.println("\nYou dont't have any students...\n");
		} else {
			for(int i = 0; i < list.size(); i++) {
				String[] arr = list.get(i).split(" ");
				if(arr.length >= 3) 
					System.out.println((i+1) + " : " + arr[0] + " " + arr[1] + " : " + arr[2] + " лет");
			}
		}
		
		System.out.println("=====================================");
		System.out.println();
	}	
	
	// Обновляет список студентов
	public void updateList() {
		/*
		 * Функция создает новый лист студентов,
		 * проходится пострококов по файлу с инфой о студентах
		 * и записывает инфу о них в строку
		 */
		
		File file = new File(path);
		this.list = new ArrayList<>();
		this.numOfNotes = 0;
		
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				this.numOfNotes++;
				String stroke = scanner.nextLine();
				list.add(stroke);
			}
			scanner.close();
		}
		catch(IOException ex) {}
	}
}
