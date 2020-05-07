package com.ss.lms.entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Format {
	public static void mainMenu() {
		System.out.println(" Welcome to the GCIT Library Management System.");
		System.out.println("------------------------------------------------");
		System.out.println(" Which category of a user are you?\n");
		System.out.println("\t1)Librarian");
		System.out.println("\t2)Administrator");
		System.out.println("\t3)Borrower");
	}
	
	public static void libMenu() {
		System.out.println("			Librarian");
		System.out.println("------------------------------------------------");
		System.out.println("\t1)Enter Branch you manage");
		System.out.println("\t2)Quite to previous ");
	}
	
	public static void libMenu1() {
		System.out.println("\t1)Update the details of the Library ");
		System.out.println("\t2)Add copies of Book to the Branch ");
		System.out.println("\t3)Quite to previous ");
	}
	public static void libMenu1_1() {
		System.out.println("\t1)Update the details of the Library ");
		System.out.println("\t2)Add copies of Book to the Branch ");
		System.out.println("\t3)Quite to previous ");
	}
	

	
	public static void adminMenu() {
		System.out.println("			Administrator");
		System.out.println("------------------------------------------------");
		System.out.println("\t1)Book and Author");
		System.out.println("\t2)Genres");
		System.out.println("\t3)Publishers");
		System.out.println("\t4)Library Branches");
		System.out.println("\t5)Borrowers");
		System.out.println("\t6)Over-ride Due Date for a Book Loan");
	}
	
	public static void adminMenu(int type) {
		System.out.println("			" + adminType(type));
		System.out.println("\t1)Add");
		System.out.println("\t2)Update");
		System.out.println("\t3)Delete");
		System.out.println("\t4)Read");
		System.out.println("\t5)Quite to previous");
	}
	
	public static String adminType(int type) {	
		String adminType ="";
		switch (type) {
			case 1:
				adminType = "Book";
				break;
			case 2:
				adminType = "Genre";
				break;
			case 3:
				adminType = "Publisher";
				break;
			case 4:
				adminType = "LibraryBranch";
				break;
			case 5:
				adminType = "Borrower";
				break;
		}
		return adminType;
	}
	
//	public static Boolean checkDate(String date ) {
//		SimpleDateFormat df = new SimpleDateFormat("yy/mm/dd");
//		Date testDate = null;
//		try{
//		    testDate = (Date) df.parse(date);
//		} catch (ParseException e){ System.out.println("invalid format");}
//		 
//		if (!df.format(testDate).equals(date)){
//		    System.out.println("Invalid date!");
//		    return false;
//		} else {
//			return true;
//		}
//
//	}
	
	public static void borrowerMenu() {
		System.out.println("			Borrower");
		System.out.println("------------------------------------------------");
		System.out.println(" Enter the your Card Number: \n");
	}
	
	public static void borrowerMenu2() {
		System.out.println("\t1)Check out a book");
		System.out.println("\t2)Return a Book");
		System.out.println("\t3)Quit to Previous \n");
	}

}
