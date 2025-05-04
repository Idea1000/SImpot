package com.kerware.simulateurreusine;

import static com.kerware.simulateurreusine.Config.*;

public class CalculContributionExceptionnel implements ICalculateur {
	double revenuFicalReference;
	double nbPartsDeclarants;

    public CalculContributionExceptionnel() {

    }
	
    @Override
    public double calculer() {
    	double contributionExceptionnelle = 0;
    	
    	int i = 0;
        
        do {
            if (this.revenuFicalReference >= limitesCEHR[i] && this.revenuFicalReference < limitesCEHR[i+1]) {
                if (nbPartsDeclarants == 1) {
                	contributionExceptionnelle += (this.revenuFicalReference - limitesCEHR[i]) * tauxCEHRCelibataire[i];
                } else {
                	contributionExceptionnelle += (this.revenuFicalReference - limitesCEHR[i]) * tauxCEHRCouple[i];
                }
                break;
            } else {
                if (nbPartsDeclarants == 1) {
                    contributionExceptionnelle += (limitesCEHR[i+1] - limitesCEHR[i]) * tauxCEHRCelibataire[i];
                } else {
                    contributionExceptionnelle += (limitesCEHR[i+1] - limitesCEHR[i]) * tauxCEHRCouple[i];
                }
            }
            i++;
        } while(i < 5);

        contributionExceptionnelle = Math.round(contributionExceptionnelle);

        System.out.println("Contribution exceptionnelle sur les hauts revenus : " + contributionExceptionnelle);
    	
        return contributionExceptionnelle;
    }

    public void setRevenuFicalReference(double revenuFicalReference) {
        this.revenuFicalReference = revenuFicalReference;
    }

    public void setNbPartsDeclarants(double nbPartsDeclarants) {
        this.nbPartsDeclarants = nbPartsDeclarants;
    }
}
