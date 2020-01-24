package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/HH/yyyy HH:ss");

		System.out.println("Enter rental date");
		System.out.print("Car Model: ");
		String carModel = sc.nextLine();
		System.out.print("Pickpup: (dd/MM/yyyy hh:ss)");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:ss)");
		Date finish = sdf.parse(sc.nextLine());

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();

		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

		rentalService.processInvoice(cr);
		
		System.out.println("INVOICE: ");
		System.out.println("Basic payment: "+ String.format("%.2f",  cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: "+ String.format("%.2f",  cr.getInvoice().getTax()));
		System.out.println("Total payment: "+ String.format("%.2f",  cr.getInvoice().getTotalPayment()));

		sc.close();

	}

}
