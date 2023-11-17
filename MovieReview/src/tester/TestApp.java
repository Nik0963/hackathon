package tester;

import java.util.Scanner;

import com.app.UserDao;
import com.app.Users;

public class TestApp {
	public static Scanner sc = new Scanner(System.in);

	public static void signUp() {
		System.out.print("First Name: ");
		String fname = sc.next();
		System.out.print("Last Name: ");
		String lname = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();
		System.out.print("Birth Date (dd-MM-yyyy): ");
		String dateStr = sc.next();
		Users voter = new Users();
		try (UserDao dao = new UserDao()) {
			int count = dao.save(voter);
			System.out.println("Voter registered: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Users signIn() {
		String email;
		String password;
		System.out.print("Enter email: ");
		email = sc.next();
		System.out.print("Enter password: ");
		password = sc.next();
		try (UserDao dao = new UserDao()) {
			Users user = dao.findByEmail(email);
			if (user != null && password.equals(user.getPassword()))
				return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

	}

}