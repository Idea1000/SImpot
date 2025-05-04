package com.kerware.simulateurreusine;

public class CalculBaisseImpot implements ICalculateur {
	private double montantImpotDeclarants;
	private double montantImpot;
	
	public CalculBaisseImpot(double montantImpotDeclarants, double montantImpot) {
		this.montantImpotDeclarants = montantImpotDeclarants;
		this.montantImpot = montantImpot;
	}
	
    @Override
    public double calculer() {
    	return this.montantImpotDeclarants - this.montantImpot;
    }

	public void setMontantImpotDeclarants(double montantImpotDeclarants) {
		this.montantImpotDeclarants = montantImpotDeclarants;
	}

	public void setMontantImpot(double montantImpot) {
		this.montantImpot = montantImpot;
	}
}
