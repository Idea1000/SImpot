package com.kerware.simulateurreusine;

import static com.kerware.simulateurreusine.Config.*;

public class CalculDecote implements ICalculateur {
	private double nbPartsDeclarants;
	private double montantImpot;

	public CalculDecote() {

	}
	
    @Override
    public double calculer() {
    	double decote = 0;
    	
    	if(this.nbPartsDeclarants == 1) {
    		if(this.montantImpot < seuilDecoteDeclarantSeul) {
    			decote = seuilDecoteDeclarantSeul - (this.montantImpot * tauxDecote);
    		}
    	}
    	
    	if (this.nbPartsDeclarants == 2) {
            if (this.montantImpot < seuilDecoteDeclarantCouple) {
                 decote =  decoteMaxDeclarantCouple - (montantImpot * tauxDecote);
            }
        }
    	
    	decote = Math.round(decote);
    	
    	if (this.montantImpot <= decote) {
            decote = this.montantImpot;
        }
		System.out.println("decote : " + decote);
        return decote;
    }

	public void setNbPartsDeclarants(double nbPartsDeclarants) {
		this.nbPartsDeclarants = nbPartsDeclarants;
	}

	public void setMontantImpot(double montantImpot) {
		this.montantImpot = montantImpot;
	}
}
