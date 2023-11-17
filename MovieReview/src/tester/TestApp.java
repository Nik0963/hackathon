package tester;

import java.util.List;
import java.util.Scanner;

import com.app.DateUtil;
import com.app.MovieDao;
import com.app.Movies;
import com.app.UserDao;
import com.app.Users;

public class TestApp {
	public static Scanner sc = new Scanner(System.in);

	public static void userOperations() {
		int choice;
		Users curUser = null;
		do {
			System.out.print("\n1. Sign Up\n2. Sign In\nEnter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 0: // Exit
				System.out.println("Bye!");
				break;
			case 1: // Sign Up
				signUp();
				break;
			case 2: // Sign In
				curUser = signIn();
				System.out.println("Sign In successfull!");
				adminOperations(curUser);
				break;
			}
		} while (choice != 0);
	}

	public static void adminOperations(Users curUser) {
		int choice;
		do {
			System.out.print(
					"\n0. Log out\n1. Edit Profile\n2. Change Password\n3. Write Review\n4. Edit Review\n5. Display All Movies\n6. Display all reviews\n7. Display my reviews\n8. Display reviews shared with me\n9. Share a review\n10. Delete a review\nEnter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 0: // Log out
				System.out.println("Bye!");
				break;
			case 1: // Edit Profile

				break;
			case 2: // Change Password
				break;
			case 3: // Write Review
				break;
			case 4: // Edit Review
				break;
			case 5: // Display All Movies
				break;
			case 6: // Display all reviews
				break;
			case 7: // Display my reviews
				break;
			case 8: // Display reviews shared with me
				break;
			case 9: // Share a review
				break;
			case 10: // Delete a review
				break;
			}
		} while (choice != 0);
	}

	public static void signUp() {
		System.out.print("First Name: ");
		String fname = sc.next();
		System.out.print("Last Name: ");
		String lname = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Mobile: ");
		String mobile = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();
		System.out.print("Birth Date (dd-MM-yyyy): ");
		String dateStr = sc.next();
		Users user = new Users(0, fname, lname, email, passwd, mobile, DateUtil.parse(dateStr));
		try (UserDao dao = new UserDao()) {
			int count = dao.save(user);
			System.out.println("User registered: " + count);
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

	public static void displayAllMovies() {
		try (MovieDao dao = new MovieDao()) {
			List<Movies> list = dao.displayAllMovies();
			list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void editProfile(Users user) {
		try (UserDao dao = new UserDao()) {
			int count = dao.editProfile(user);
			System.out.println("Rows affetcted: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changePassword(Users user) {

		try (UserDao dao = new UserDao()) {
			int count = dao.changePassword(user);
			System.out.println("Rows affected: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
