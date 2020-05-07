package com.ss.lms.entity;
import java.util.Scanner;


public class LibraryInterface {
	
	public LibraryBranch libraryMenu1_1(Scanner userInput, LibraryBranch branch) {
			System.out.println("You have chosen to update the Branch with Branch Id: " +
					branch.getBranchId() + " and Branch Name: " + branch.getBranchName() + ".");
			System.out.println("Enter ‘quit’ at any prompt to cancel operation.\n");
			System.out.println("Please enter new branch name or enter N/A for no change: ");
			String input = userInput.nextLine();
			if(input != "quit") {
				branch.setBranchName(input);
			}else {return null;}
//			System.out.println("Please enter new branch address or enter N/A for no change:");
//			userInput.nextLine();
//			input = userInput.nextLine();
//			if(input != "quit") {
//				branch.setBranchAddress(input);
//			}else {return null;}
		return branch;
	}

}
