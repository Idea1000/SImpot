package com.kerware.simulateurreusine;

import static com.kerware.simulateurreusine.Config.limites;
import static com.kerware.simulateurreusine.Config.taux;

public class CalculImpotAvantDecote implements ICalculateur {
    private double revenuFicalReference;
    private double nbParts;

    public CalculImpotAvantDecote(double revenuFicalReference, double nbParts) {
        this.revenuFicalReference = revenuFicalReference;
        this.nbParts = nbParts;
    }

    @Override
    public double calculer() {
        double revenuImposable = revenuFicalReference / nbParts;
        double montantImpot = 0;
        int i = 0;

        do {
            if (revenuImposable >= limites[i] && revenuImposable < limites[i+1]) {
                montantImpot += (revenuImposable - limites[i]) * taux[i];
                break;
            } else {
                montantImpot += (limites[i+1] - limites[i]) * taux[i];
            }
            i++;
        } while(i < 5);

        montantImpot = montantImpot * nbParts;
        return Math.round(montantImpot);
    }

    public void setRevenuFicalReference(double revenuFicalReference) {
        this.revenuFicalReference = revenuFicalReference;
    }

    public void setNbParts(double nbParts) {
        this.nbParts = nbParts;
    }
}
