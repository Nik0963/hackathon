package tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.app.DateUtil;
import com.app.MovieDao;
import com.app.Movies;
import com.app.ReviewDao;
import com.app.Reviews;
import com.app.SharesDao;
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
				if ((curUser.getEmail() == null && curUser.getEmail() == "")
						|| (curUser.getPassword() == null && curUser.getPassword() == " ")
						|| (curUser.getfName() == null && curUser.getfName() == "")
						|| (curUser.getlName() == null && curUser.getlName() == "")) {
					curUser = signIn();

				}
				if (curUser != null)
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
				curUser = null;
				break;
			case 1: // Edit Profile
				editProfile(curUser);
				break;
			case 2: // Change Password
				changePassword(curUser);
				break;
			case 3: // Write Review
				writeReviewInDb(createReview());
				break;
			case 4: // Edit Review
				editReview(curUser);
				break;
			case 5: // Display All Movies
				displayAllMovies();
				break;
			case 6: // Display all reviews
				displayAllReviews();
				break;
			case 7: // Display my reviews
				displayMyReviews(curUser);
				break;
			case 8: // Display reviews shared with me

				break;
			case 9: // Share a review
				shareReview(curUser);
				break;
			case 10: // Delete a review
				deleteReview(curUser);
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
			if (user != null && password.equals(user.getPassword())) {
				System.out.println(user);
				System.out.println("Sign In succesfull.");
				return user;
			} else {
				System.out.println("Wrong Credentials.");
			}
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
		System.out.println("Enter first name: ");
		String fname = sc.next();
		System.out.println("Enter last name: ");
		String lname = sc.next();
		System.out.println("Enter mobile: ");
		String mobile = sc.next();
		System.out.println("Enter birthdate: ");
		String birth = sc.next();
		user.setfName(fname);
		user.setlName(lname);
		user.setMobile(mobile);
		user.setDob(DateUtil.parse(birth));
		try (UserDao dao = new UserDao()) {
			int count = dao.editProfile(user);
			System.out.println("Rows affetcted: " + count);
			System.out.println("Profile edit succesfull!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changePassword(Users user) {

		System.out.println("Enter new password: ");
		String password = sc.next();
		user.setPassword(password);
		try (UserDao dao = new UserDao()) {
			int count = dao.changePassword(user);
			System.out.println("Rows affected: " + count);
			System.out.println("Password change succesfull!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Reviews createReview() {
		Reviews r = new Reviews();
		System.out.println("Enter movie id: ");
		int movieId = sc.nextInt();
		System.out.println("Enter user id : ");
		int userId = sc.nextInt();
		System.out.println("Enter review: ");
		sc.nextLine();
		String review = sc.nextLine();
		if (review == " ") {
			System.out.println("Enter review: ");
			review = sc.next();
		}
		System.out.println("Enter rating: ");
		int rating = sc.nextInt();

		r.setMovieId(movieId);
		r.setUserId(userId);
		r.setReview(review);
		r.setRating(rating);
		return r;
	}

	public static void writeReviewInDb(Reviews r) {
		try (ReviewDao dao = new ReviewDao()) {
			int count = dao.saveReview(r);
			System.out.println("Rows affected: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void editReview(Users user) {
		int id = user.getUserId();
		try (ReviewDao dao = new ReviewDao()) {
			List<Reviews> list = dao.displayMyReviews(id);
			list.forEach(e -> System.out.println(e));
			System.out.println("Enter review id to be edited: ");
			int reviewId = sc.nextInt();
//			Reviews r = list.get(reviewId);
//			System.out.println("Your review: " + r.toString());
			Reviews r = new Reviews();
			r.setReviewId(reviewId);
			System.out.println("Enter new rating: ");
			int rating = sc.nextInt();
			System.out.println("Enter new review: ");
			String review = sc.next();
			r.setRating(rating);
			r.setReview(review);
			int count = dao.editReview(r);
			System.out.println("Rows affected: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void shareReview(Users user) {
		int id = user.getUserId();
		try (ReviewDao dao = new ReviewDao()) {
			try (UserDao uDao = new UserDao()) {
				try (SharesDao sDao = new SharesDao()) {
					List<Reviews> list = dao.displayMyReviews(id);
					list.forEach(e -> System.out.println(e));
					System.out.println("Enter review id to be shared: ");
					int reviewId = sc.nextInt();

					Reviews r = new Reviews();
					r.setReviewId(reviewId);
					r = dao.displayReviewbyId(reviewId);
					System.out.println(r.toString());
					List<Users> listUser = uDao.displayAllUsers();
					listUser.forEach(e -> System.out.println(e));
					System.out.println("Enter user ids to share review with(enter 0 to end): ");
					listUser.clear();
					int uid = 1;
					while (uid != 0) {
						uid = sc.nextInt();
						if (uid != 0) {
							Users user1 = new Users();
							user1.setUserId(uid);
							if (user.getUserId() != uid)
								listUser.add(user1);
							else {
								System.out.println("Cannot share review with yourself.");
							}
						}
					}
					int count = sDao.shareReview(r, listUser);
					System.out.println("Rows affected: " + count);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void displayAllReviews() {
		try (ReviewDao rDao = new ReviewDao()) {
			List<Reviews> list = rDao.displayAllReviews();
			list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void displayMyReviews(Users user) {
		try (ReviewDao rDao = new ReviewDao()) {
			List<Reviews> list = rDao.displayMyReviews(user.getUserId());
			list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteReview(Users user) {
		try (ReviewDao dao = new ReviewDao()) {
			List<Reviews> list = dao.displayMyReviews(user.getUserId());
			list.forEach(e -> System.out.println(e));
			System.out.println("Enter review id to be deleted: ");
			int reviewId = sc.nextInt();
			int count = dao.deleteById(reviewId);
			System.out.println("Rows affected : " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void displaySharedReviews(Users user) {
		List<Reviews> list = new ArrayList<Reviews>();
		try (ReviewDao rDao = new ReviewDao()) {

			list = rDao.displayReviewsSharedWithMe(user.getUserId());
			list.forEach(e->System.out.println(e));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		userOperations();
	}

}
