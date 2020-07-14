import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserPage {
	public static void main(String args[]) throws IOException, SQLException {

		UserPage userPage = new UserPage();
		UserConstant userConstant = new UserConstant();

		System.out.println(
				"Please Enter Choice: \n 1.Sign Up for New User \n 2.Sign in For Existing User \n 3.Delete User Information");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if (choice == 1) {
			userPage.signUpUser(userConstant, userPage);
		}
		if(choice==2) {
			System.out.println("please enter username");
			String username=sc.next();
			System.out.println("please enter password");
			String password=sc.next();
			userPage.viewStudent(username,password,userPage,userConstant);
		}	
		if(choice==3) {
			System.out.println("please enter username to be deleted");
			String username=sc.next();
			System.out.println("please enter password");
			String password=sc.next();
			userPage.deleteStudent(username,password,userPage,userConstant);
		}
	}

	private void viewStudent(String username, String password, UserPage userPage, UserConstant userConstant) throws SQLException{
		Connection con = null;
		PreparedStatement ps=null;
		try {
			con = DriverManager.getConnection(userConstant.url, userConstant.user, userConstant.pass);
			Boolean validationPass=userPage.validateUsernameDelete(username,password,ps,con);
			if(validationPass) {
				ps=con.prepareStatement(" select * from studentinformation where Username=? and pass_word=?");
				ps.setString(1,username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					rs.getString(1);
					System.out.println("Username--> "+rs.getString(1)+"\nTotal marks--> "+rs.getInt(16)+"\nTotal marks obtained--> "+rs.getInt(17)+"\nGrade--> "+rs.getString(18));
				}
			}else if(!validationPass) {
				System.out.println("Data not found");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteStudent(String username, String password, UserPage userPage, UserConstant userConstant) throws SQLException {
		
		Connection con = null;
		PreparedStatement ps=null;
		try {
			con = DriverManager.getConnection(userConstant.url, userConstant.user, userConstant.pass);
			Boolean validationPass=userPage.validateUsernameDelete(username,password,ps,con);
			if(validationPass) {
				ps=con.prepareStatement(" delete from studentinformation where Username=? and pass_word=?");
				ps.setString(1,username);
				ps.setString(2, password);
				int rs = ps.executeUpdate();
				if(rs!=0) {
					System.out.println("Data deleted successfully");
				}
			}else if(!validationPass) {
				System.out.println("Data not found");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private Boolean validateUsernameDelete(String username,String password,PreparedStatement ps,Connection con) throws SQLException {
		Boolean validationPass = false;
		
		ps=con.prepareStatement("select * from studentinformation where Username=? and pass_word=?");
		ps.setString(1,username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			validationPass=true;
		}
		return validationPass;
	}

	public void signUpUser(UserConstant userConstant, UserPage userPage) throws IOException, SQLException {

		Connection con = null;
		Statement stmt = null;
		PreparedStatement ps=null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(userConstant.url, userConstant.user, userConstant.pass);
			stmt = con.createStatement();
			Scanner sc = new Scanner(System.in);

			System.out.println("Please Enter Username");
			String username = sc.next();
			System.out.println("Please Enter Password");
			String pass_word = sc.next();
			System.out.println("Please Enter First Name");
			String fname = sc.next();
			System.out.println("Please Last Name");
			String lname = sc.next();
			System.out.println("Please Enter Date of Birth");
			String dob = sc.next();

			System.out.println("Please Enter Math Marks Obtained");
			int mathMarksObtained = sc.nextInt();
			System.out.println("Please Enter Science Marks Obtained");
			int scienceMarksObtained = sc.nextInt();
			System.out.println("Please Enter English Marks Obtained");
			int englishMarksObtained = sc.nextInt();
			System.out.println("Please Enter Hindi Marks Obtained");
			int hindiMarksObtained = sc.nextInt();
			System.out.println("Please Enter Social science Marks Obtained");
			int socialScienceMarksObtained = sc.nextInt();
			System.out.println("Please Enter Math Total Marks");
			int mathMarks = sc.nextInt();
			System.out.println("Please Enter Science Total Marks");
			int scienceMarks = sc.nextInt();
			System.out.println("Please Enter English Total Marks");
			int englishMarks = sc.nextInt();
			System.out.println("Please Enter Hindi Total Marks");
			int hindiMarks = sc.nextInt();
			System.out.println("Please Enter Social science Total Marks");
			int socialScienceMarks = sc.nextInt();

			int totalMarksObtained = mathMarksObtained + scienceMarksObtained + englishMarksObtained
					+ hindiMarksObtained + socialScienceMarksObtained;
			int totalMarks = mathMarks + scienceMarks + englishMarks + hindiMarks + socialScienceMarks;

			Boolean vlidationPass = userPage.validateMarks(mathMarksObtained, scienceMarksObtained,
					englishMarksObtained, hindiMarksObtained, socialScienceMarksObtained, totalMarksObtained, mathMarks,
					scienceMarks, englishMarks, hindiMarks, socialScienceMarks, totalMarks);
			vlidationPass = validateUsername(username, ps,con);
			String grade = calculateGrade(totalMarks, totalMarksObtained);

			if (vlidationPass) {
				ps=con.prepareStatement("INSERT into studentinformation Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, username);
				ps.setString(2, pass_word);
				ps.setString(3, fname);
				ps.setString(4, lname);
				ps.setString(5, dob);
				ps.setInt(6, mathMarksObtained);
				ps.setInt(7, scienceMarksObtained);
				ps.setInt(8, englishMarksObtained);
				ps.setInt(9, hindiMarksObtained);
				ps.setInt(10, socialScienceMarksObtained);
				ps.setInt(11, mathMarks);
				ps.setInt(12, scienceMarks);
				ps.setInt(13, englishMarks);
				ps.setInt(14, hindiMarks);
				ps.setInt(15, socialScienceMarks);
				ps.setInt(16, totalMarks);
				ps.setInt(17,totalMarksObtained);
				ps.setString(18, grade);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String calculateGrade(int totalMarks, int totalMarksObtained) {
		String Grade;
		double m = (totalMarksObtained*100)/totalMarks;
		if (m >= 80) {
			Grade = "AA";
			System.out.println("A");
		} else if (m >= 72 && m < 80) {
			Grade = "A";
			System.out.println("AA");
		} else if (m >= 64 && m < 72) {
			Grade = "BB";
			System.out.println("BB");
		} else if (m >= 56 && m < 64) {
			Grade = "B";
			System.out.println("B");
		} else if (m >= 48 && m < 56) {
			Grade = "CC";
			System.out.println("CC");
		} else if (m >= 40 && m < 48) {
			Grade = "C";
			System.out.println("C-");
		} else if (m >= 32 && m < 40) {
			Grade = "DD";
			System.out.println("DD");
		} else {
			Grade = "F";
			System.out.println("F"); 
		}
		return Grade;
	}

	private Boolean validateUsername(String username, PreparedStatement ps,Connection con) throws SQLException {
        Boolean validationPass = true;
		ps=con.prepareStatement("select * from studentinformation where Username=?");
		ps.setString(1,username);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			validationPass=true;
		}
		return validationPass;
	}

	private Boolean validateMarks(int mathMarksObtained, int scienceMarksObtained, int englishMarksObtained,
			int hindiMarksObtained, int socialScienceMarksObtained, int totalMarksObtained, int mathMarks,
			int scienceMarks, int englishMarks, int hindiMarks, int socialScienceMarks, int totalMarks) {

		Boolean validationPass = true;

		if (totalMarksObtained > totalMarks) {
			System.out.println(
					"Total sum of marks obtained of each subject can not be greater than the Total sum of marks of the subject");
			validationPass = false;
		}
		if (mathMarksObtained > mathMarks || scienceMarksObtained > scienceMarks || englishMarksObtained > englishMarks
				|| hindiMarksObtained > hindiMarks || socialScienceMarksObtained > socialScienceMarks) {
			System.out.println("Marks obtained can not be greater than the Total marks of the subject");
			validationPass = false;
		}
		return validationPass;

	}

}
