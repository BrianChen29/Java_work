import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Process {
	private static Invoice[] invoices = { 
			new Invoice(83, "Electric sander", 7, 57.98),
			new Invoice(24, "Power saw", 18, 99.99),
			new Invoice(7, "Sledge hammer", 11, 21.50),
			new Invoice(77, "Hammer", 76, 11.99),
			new Invoice(39, "Lawn mower", 3, 79.50),
			new Invoice(68, "Screwdriver", 106, 6.99),
			new Invoice(56, "Jig saw", 21, 11.00),
			new Invoice(3, "Wrench", 34, 7.50),
			new Invoice(45, "Wrench", 13, 7.50),
			new Invoice(22, "Hammer", 47, 11.99)
	};

	public Process() {

	}
	
	public static String sort(String attribute) {
		Stream<Invoice> stream;
		switch (attribute.toLowerCase()) {
		case "id":
			stream = Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPartNumber));
			break;
		case "quantity":
			stream = Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getQuantity));
			break;
		case "price":
			stream = Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPrice));
			break;
		default:
			stream = Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPartNumber));
			break;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("\nInvoices sorted by " + attribute + ":\n");
		stream.forEachOrdered((invoice) -> builder.append(invoice.toString() + "\n"));
		return builder.toString();
	}

	public static String report() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nInvoices group by description: \n");
		Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getAmount)).forEachOrdered((invoice) -> {
			String description = invoice.getPartDescription();
			Double amount = invoice.getQuantity() * invoice.getPrice();
			builder.append(String.format("Description: %-20sInvoice amount:%-9.2f%n", description, amount));
		});
		return builder.toString();
	}

	public static String select(String min, String max) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format(
				"\nInvoices mapped to description and invoice amount for invoices in the range %s-%s:\n", min, max));
		try {
			Double minDouble = Double.parseDouble(min);
			Double maxDouble = Double.parseDouble(max);
			Arrays.stream(invoices)
					.filter((invoice) -> invoice.getAmount() >= minDouble && invoice.getAmount() <= maxDouble)
					.sorted(Comparator.comparing(Invoice::getAmount)).forEachOrdered((invoice) -> {
						String description = invoice.getPartDescription();
						Double amount = invoice.getQuantity() * invoice.getPrice();
						builder.append(String.format("Description: %-20sInvoice amount:%-9.2f%n", description, amount));
					});
		} catch (NumberFormatException e) {
			Arrays.stream(invoices)
					.filter((invoice) -> invoice.getPartDescription().compareToIgnoreCase(min) >= 0
							&& invoice.getPartDescription().compareToIgnoreCase(max) <= 0)
					.sorted(Comparator.comparing(Invoice::getAmount)).forEachOrdered((invoice) -> {
						String description = invoice.getPartDescription();
						Double amount = invoice.getQuantity() * invoice.getPrice();
						builder.append(String.format("Description: %-20sInvoice amount:%-9.2f%n", description, amount));
					});
		}
		return builder.toString();
	}


}
