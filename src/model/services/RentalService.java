package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	
	private Double pricePerDay;
	private Double pricePerHour;
	
	private TaxService taxService;

	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	
	//gerar a nota de pagamento
	public void processInvoice(CarRental carRental) {
		//getTime pega o valor em mili segundo na data
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double)(t2 - t1) / 1000 / 60 / 60; //converte para hora
		
		double basicPayment;
		if(hours <= 12.0){
			//Math.ceil(hours) -> arredonda o valor para cima
			 basicPayment = Math.ceil(hours) * pricePerHour; // valor por hora
		}
		else {
			basicPayment = Math.ceil(hours/24) * pricePerDay;
		}
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
		
	}
	
	
}
