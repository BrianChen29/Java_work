//資管三B 107403046 陳柏澔


import static java.lang.System.out;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		out.print("Welcome to invoices management system.\n\n");
		Scanner scanner = new Scanner(System.in);
		String input;
		do {
			out.print("Functions: Sort/Report/Select\nChoice (-1 to exit): ");
			input = scanner.nextLine();
			switch (input.toLowerCase()) {
			case "sort":
				out.print("Input the attribute to sort.\nID/Quantity/Price: ");
				out.println(Process.sort(scanner.nextLine()));
				break;
			case "report":
				out.println(Process.report());
				break;
			case "select":
				out.print("Input the range to show (min, max): ");
				String range = scanner.nextLine();
				String[] minMax = range.split(",");
				out.println(Process.select(minMax[0], minMax[1]));
				break;
			case "-1":
				out.println("exit.");
				System.exit(0);
				break;

			default:
				out.println("Wrong Input. Eneter Again!!\nChoice (-1 to exit): ");
				break;
			}
		} while (!input.equals("-1"));
	}
}