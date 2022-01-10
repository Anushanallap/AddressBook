package com.java.AddressBook;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

class AddressBook {

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	static ArrayList<Address> arr_list = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	private ArrayList<Address> data;

	public void AddPreson() {

		System.out.println("Enter the First name :");
		String First_name = sc.next();
		System.out.println("Enter the Last name :");
		String Last_name = sc.next();
		System.out.println("Enter the address :");
		String address = sc.next();
		System.out.println("Enter the phone number :");
		String phoneNum = sc.next();
		System.out.println("Enter the zip code: ");
		String zip = sc.next();
		System.out.println("Enter the city name : ");
		String city = sc.next();
		System.out.println("Enter the state name : ");
		String state = sc.next();
		System.out.println("Enter the email : ");
		String email = sc.next();

		Address adress = new Address(First_name, Last_name, address, phoneNum, zip, city, state, email);

		arr_list.add(adress);
		for (Address add : arr_list) {
			add.display();
		}
	}

	public void EditPerson(String name, String name1) {
		for (Address a : arr_list) {
			if (name.equals(a.First_name)) {
				a.First_name = name1;
				a.display();
			}

		}
	}

	public void DeletePerson(String name) {
		for (int i = 0; i < arr_list.size(); i++) {
			Address p = arr_list.get(i);
			if (name.equals(p.First_name)) {
				arr_list.remove(i);
				System.out.println("Person  details are deleted");
				System.out.println(arr_list);
			} else
				System.out.println("Person name not found");
		}
	}

	public void Multipleperson() {

		Scanner sc = new Scanner(System.in);
		System.out.print("How many person you want to add :");
		int Count = sc.nextInt();
		for (int i = 1; i <= Count; i++) {
			AddPreson();
		}
		for (Address p : arr_list) {
			p.display();
		}
	}


	public void searchcity() {

		HashMap<Integer, String> hash = new HashMap<>();
		HashMap<Integer, String> hash1 = new HashMap<>();

		String choice, city, personname;
		System.out.println(
				"search \n 1)choose City  (press 1) \n 2)Press city or state  (press 2) : \n 3)Enter the city name (press 3) \n 4)Press 4 sort by name : \n 5)Sort by state pres 5 :\n 6)(press 6)Person contact details are :");
		choice = sc.next();
		switch (choice) {

		case "1":
			System.out.println("Enter the person first name and last name without space ");
			personname = sc.next();
			List<Address> list = arr_list.stream()
					.filter(person_name -> person_name.First_name.concat(person_name.Last_name).equals(personname))
					.collect(Collectors.toList());
			for (Address addr : list) {
				System.out.println("Name of the city is : " + addr.city);
			}
			break;
		case "2":
			System.out.println("Enter the city or state name");
			city = sc.next();

			List<Address> list1 = arr_list.stream().filter(city_name -> city_name.city.equals(city))
					.collect(Collectors.toList());
			for (Address addr : list1) {
				System.out.println("the deatils of the person : " + addr.First_name + " " + addr.Last_name);
			}
			break;

		case "3":
			System.out.println("Enter the city name :");
			String cityname = sc.next();
			List<Address> list2 = arr_list.stream().filter(p_number -> p_number.city.equals(cityname))
					.collect(Collectors.toList());
			for (Address addr : list2) {
				System.out.println("The person phone number : " + addr.phoneNum + " name : " + addr.First_name);

			}
			break;

		case "4":
			List<String> sorted = arr_list.stream().map(a -> a.First_name.concat(a.Last_name)).sorted()
					.collect(Collectors.toList());
			sorted.forEach(System.out::println);
			break;

		case "5":
			List<String> sortedzip = arr_list.stream().map(s -> s.zip).sorted().collect(Collectors.toList());
			sortedzip.forEach(System.out::println);
			break;

		
		default:
			System.out.println("Invalid user input please enter valid");
			break;
		}

	}

	public void writedataAtOnce() {
		File file = new File("c:\\users\\Dell\\eclipse-workspace\\txtfile.csv");

		try {
			FileWriter outputfile = new FileWriter( file);
			CSVWriter writer = new CSVWriter(outputfile);
			List<String[]> data = new ArrayList<String[]>();
					 data.add(new String[] { "Name", "Class", "Marks" });
				        data.add(new String[] { "Aman", "10", "620" });
				        data.add(new String[] { "Suraj", "10", "630" });
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}

	public static void readDataLineByLine() {

		try {
			FileReader filereader = new FileReader("c:\\\\users\\\\Dell\\\\eclipse-workspace\\\\txtfile.csv" );
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;

			// we are going to read data line by line
			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writejsondatafile() {

		try {
			// creating a writer
			Writer writer = new FileWriter("c:\\users\\Dell\\eclipse-workspace\\txtfile.Gson");
			new Gson().toJson(arr_list, writer);
			System.out.println("Done");
			writer.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public void readjsondatafile() {

		Gson gson = new Gson();		
		try {
			
			Address[] jsondata = gson.fromJson(new FileReader("c:\\users\\Dell\\eclipse-workspace\\txtfile.Gson"), Address[].class);

			System.out.println(gson.toJson(jsondata));
		} catch (IOException e) {
			System.err.println("File not found");
		}
	}

}


class AddressHashMap {
	HashMap<String, Address> map = new HashMap<>();

	public void AddressHashmap() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of persons to add :");
		int count = sc.nextInt();
		for (int i = 1; i <= count; i++) {
			System.out.println("Enter the First name :");
			String First_name = sc.next();
			System.out.println("Enter the Last name :");
			String Last_name = sc.next();
			System.out.println("Enter the address :");
			String address = sc.next();
			System.out.println("Enter the phone number :");
			String phoneNum = sc.next();
			System.out.println("Enter the zip code: ");
			String zip = sc.next();
			System.out.println("Enter the city name : ");
			String city = sc.next();
			System.out.println("Enter the state name : ");
			String state = sc.next();
			System.out.println("Enter the email : ");
			String email = sc.next();

			Address data = new Address(First_name, Last_name, address, phoneNum, zip, city, state, email);
			// map.put(email, data);

			if (map.containsKey(First_name.concat(Last_name))) {
				System.out
						.println("\nError : " + First_name + " " + Last_name + " already exists on this address book.");
				break;
			}
			map.put(First_name.concat(Last_name), data);
		}
		displaymapdata(map);
	}

	public void displaymapdata(HashMap<String, Address> map) {
		for (String map1 : map.keySet()) {
			System.out.println("Key: " + map1 + " Value: " + map.get(map1));
		}

	}
}

public class AddressBookMain {

	public static void main(String[] args) {
//
	//	 Address s1  = new Address("Anusha", "Nallapu", "Khammam near ROTARY nagar",
//		 "9658214314", "507001",
//		 "khammam", "Telangana", "anusha.n468@gmail.com");

		// s1.display();
//
	AddressBook address = new AddressBook();
//		address.AddPreson();
//		address.AddPreson();
//		address.EditePeson("Anusha", "anusha");
//		address.DeletePerson("Anusha");

		address.Multipleperson(); // UC5
		// address.searchcity();
//		AddressHashMap add = new AddressHashMap();
//		address.AddressHashmap();
		//address.writedataAtOnce();
		//address.readDataLineByLine();
		//address.writejsondatafile();
		//address.readjsondatafile();
		
	}

}
