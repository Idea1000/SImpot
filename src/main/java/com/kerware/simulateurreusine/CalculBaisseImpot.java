package com.kerware.simulateurreusine;

public class CalculBaisseImpot implements ICalculateur {
	private double montantImpotDeclarants;
	private double montantImpot;

	public CalculBaisseImpot() {

	}
	
    @Override
    public double calculer() {
		System.out.println("baisse impot : " + this.montantImpotDeclarants + " - " + this.montantImpot + " = " + (this.montantImpotDeclarants - this.montantImpot));
    	return this.montantImpotDeclarants - this.montantImpot;
    }

	public void setMontantImpotDeclarants(double montantImpotDeclarants) {
		this.montantImpotDeclarants = montantImpotDeclarants;
	}

	public void setMontantImpot(double montantImpot) {
		this.montantImpot = montantImpot;
	}
}
