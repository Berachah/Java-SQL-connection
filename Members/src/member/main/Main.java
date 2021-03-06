package member.main;

import java.util.ArrayList;
import java.util.Scanner;
import member.store.*;
import member.info.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) {
		/*
		Scanner in = new Scanner(System.in);;
		int i =0; 
		String name="";
		String addy="";
		double don = 0.0;
		
		
		int amountInTable = showWholeTable();
		int count= 0;
		System.out.println("You have " + amountInTable + " donations");
		int idCount= amountInTable;
		System.out.print("How many new members: ");
		int total = in.nextInt();
		ArrayList<info> list2 = new ArrayList<info>();
		
		while(count < total)
		{
			System.out.print("Enter your name:");
			name=in.nextLine();
			name+=in.nextLine();
			System.out.print("Here is your Id:");
			System.out.println(++idCount);
			System.out.print("Enter your Donation Amount:");
			don=in.nextDouble();
			System.out.print("Enter your Address:");
			addy=in.next();
			addy+=in.nextLine();
			
	
		
			
			store val= new store();
			list2.add(count, val.Description(idCount,name,addy,don));
			count++;
		}
	 
		insertTable(list2);
		
		*/
		mostDonations();
		smallestDonation();
		removeDonations();
	//	showWholeTable();
	}

	public static int showWholeTable()
	{
		String url ="jdbc:mysql://127.0.0.1:3306/donations? useSSL=false&allowPublicKeyRetrieval=true";
		  String username="root";
		  String password="root";
		  int count2=0; 
		  try {
				Connection connection = DriverManager.getConnection(url,username,password);
				
				String sql= "SELECT * FROM info";
				
				Statement statement= connection.createStatement();
				
				ResultSet result = statement.executeQuery(sql);
				
				
				while(result.next()) {
					count2++;
					int id= result.getInt("Id");
					String names = result.getString("Name");
					Double amot= result.getDouble("Amount");
					String addy2 = result.getString("Address");
					
					System.out.printf("Student %d: %s- %.2f %s\n", id, names, amot, addy2);
				}
				connection.close();
			}catch(SQLException e) {
				System.out.println("Oops, theres an error:");
				e.printStackTrace();
			}
		  
		  return count2;
	}
	
	public static void smallestDonation() {
		 String url ="jdbc:mysql://127.0.0.1:3306/donations? useSSL=false&allowPublicKeyRetrieval=true";
		  String username="root";
		  String password="root";
		
		  
		  try {
			  Connection connection = DriverManager.getConnection(url,username,password);
			  String sql= "SELECT * FROM info ORDER BY Amount ASC";
				
				Statement statement= connection.createStatement();
				
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
					
					int id= result.getInt("Id");
					String names = result.getString("Name");
					Double amot= result.getDouble("Amount");
					String addy2 = result.getString("Address");
					
			//		System.out.printf("Student %d: %s- %.2f %s\n", id, names, amot, addy2);
					System.out.printf("%s donated the least amount, he/she donated %.2f, he/she lives at %s.", names,amot,addy2);
					break;
				}
				
				connection.close();
				
				
	}catch(SQLException e) {
		System.out.println("Oops, theres an error:");
		e.printStackTrace();
		}
	}
	public static void insertTable(ArrayList<info> list)
	{
		 String url ="jdbc:mysql://127.0.0.1:3306/donations? useSSL=false&allowPublicKeyRetrieval=true";
		  String username="root";
		  String password="root";
		
		  
		  try {
			  Connection connection = DriverManager.getConnection(url,username,password);
			  
			  for(int j=0; j<list.size(); j++)
			  {
				  int id2=list.get(j).getId();
				  String name2 =list.get(j).getName();
				  String addy2 =list.get(j).getAddress();
				  double amt = list.get(j).getDonation();
				  String sql= "INSERT INTO info (Id, Name, Amount, Address) VALUES(?, ?, ?, ?)";
					
					
					PreparedStatement statement = connection.prepareStatement(sql);
					
					
					statement.setInt(1, id2);
					statement.setString(2, name2);
					statement.setDouble(3, amt);
					statement.setString(4, addy2);

					int rows = statement.executeUpdate();
					
					if(rows > 0) {
						System.out.println("Row has been inserted.");
						
					}
			  }
			
		  }catch(SQLException e) {
				System.out.println("Oops, theres an error:");
				e.printStackTrace();
			}
	}
	
	public static void mostDonations(){
		 String url ="jdbc:mysql://127.0.0.1:3306/donations? useSSL=false&allowPublicKeyRetrieval=true";
		  String username="root";
		  String password="root";
		
		  
		  try {
			  Connection connection = DriverManager.getConnection(url,username,password);
			  String sql= "SELECT * FROM info ORDER BY Amount DESC";
				
				Statement statement= connection.createStatement();
				
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
					
					int id= result.getInt("Id");
					String names = result.getString("Name");
					Double amot= result.getDouble("Amount");
					String addy2 = result.getString("Address");
					
			//		System.out.printf("Student %d: %s- %.2f %s\n", id, names, amot, addy2);
					System.out.printf("%s donated the most amount, he/she donated %.2f, he/she lives at %s.", names,amot,addy2);
					System.out.println();
					break;
				}
				
				connection.close();
				
				
	}catch(SQLException e) {
		System.out.println("Oops, theres an error:");
		e.printStackTrace();
		}
	}
	
	public static void removeDonations() {
		
		 String url ="jdbc:mysql://127.0.0.1:3306/donations? useSSL=false&allowPublicKeyRetrieval=true";
		  String username="root";
		  String password="root";
		
		  
		  try {
			  Connection connection = DriverManager.getConnection(url,username,password);
			  String sql= "DELETE FROM info WHERE Amount=12.87";
			  
			 
			  PreparedStatement statement = connection.prepareStatement(sql);

				int rows = statement.executeUpdate();
				
				if(rows > 0) {
					System.out.println();
					System.out.println("Row has been Deleted.");
					
				}
	}catch(SQLException e) {
		System.out.println("Oops, theres an error:");
		e.printStackTrace();
	}
	}
}