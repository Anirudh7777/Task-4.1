package com.demo.pojo;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistanseBookDemo {

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
			if (option == 1) {
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
				
				persistBook(book);

			} else if (option == 2) {
				System.out.println("Enter The Book Id");
				int i = input.nextInt();
				book = findBook(i);
				System.out.println(book.toString());
			}
			System.out.println("Do you continue or Leave: Y | N");
			ch = input.next().charAt(0);
		} while (ch == 'Y');

	}

	public static Book findBook(int id) {
		
		Book book = null;
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JAPProjectEx");
			EntityManager em = emf.createEntityManager();
			
			//em.getTransaction().begin();
			book = em.find(Book.class,id);
			System.out.println(book);
			//em.getTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
		} 
		return book;
	}

	public static void persistBook(Book book) {
		
		try{
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JAPProjectEx");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(book);
			System.out.println("Successfully Inserted");
			em.getTransaction().commit();  
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
		
	}

}
