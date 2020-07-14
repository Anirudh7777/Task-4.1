package com.h2db.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.h2.engine.SysProperties;

public class H2DemoMain {

	static final String JDBC_Driver = "org.h2.Driver";
	static final String DB_Url = "jdbc:h2:tcp://localhost/~/test";

	static final String url = "sa";
	static final String password = "";

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int option;
		Book book = new Book();
		boolean status;
		char ch;
		do {
			System.out.println("Welcome to H2 Example");
			System.out.println("Enter your options: 1-> New Entry '\'\n 2->Find Book");
			option = input.nextInt();
			if(option==1) {
			System.out.println("Enter Book Details");
			System.out.println("----------------");
			System.out.println("Enter Book Id");
			book.setId(input.nextInt());
			input.nextLine();
			System.out.println("----------------");
			System.out.println("Enter Book Description");
			String des = input.nextLine();
			book.setDescription(des);
			System.out.println("----------------");
			System.out.println("Enter Book Title");
			String title = input.nextLine();
			book.setTitle(title);
			System.out.println("----------------");
			System.out.println("Enter Book ISBN");
			String ISBN = input.nextLine();
			book.setIsbn(ISBN);
			System.out.println("----------------");
			System.out.println("Enter Book Cost");
			book.setUnitCost(input.nextDouble());
			System.out.println("----------------");
			System.out.println("Enter Pages in Book");
			book.setPages(input.nextInt());
			
			status = persistBook(book);
			}
			else if (option==2) {
				System.out.println("Enter The Book Id");
				book = findBook(input.nextInt());
				System.out.println(book.toString());
			}
			System.out.println("Do you continue or Leave: Y | N");
			ch = input.next().charAt(0);
		}while(ch=='Y');

	}

	@SuppressWarnings("finally")
	public static boolean persistBook(Book book) {

		System.out.println("");
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean status = false;
		try {

			Class.forName(JDBC_Driver);
			conn = DriverManager.getConnection(DB_Url, url, password);

			stmt = conn.prepareStatement("insert into book values(?,?,?,?,?,?)");
			stmt.setInt(1, book.getId());
			stmt.setString(2, book.getDescription());
			stmt.setString(3, book.getIsbn());
			stmt.setInt(4, book.getPages());
			stmt.setString(5, book.getTitle());
			stmt.setDouble(6, book.getUnitCost());

			status = stmt.execute();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
		}

	}
	
	public static Book findBook(int id) {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_Driver);
			conn = DriverManager.getConnection(DB_Url, url, password);
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("select * from book where id="+id);
			while(result.next()) {
				return new Book(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),
						result.getString(5),result.getDouble(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
		
	}

}
