package com.kerware.simulateurreusine;

import static com.kerware.simulateurreusine.Config.plafondDemiPart;

public class CalculPlafond implements ICalculateur {
    private double nbParts;
    private double nbPartsDeclarants;
    private double baisseImpot;
    private double montantImpotDeclarants;

    public CalculPlafond() {

    }

    @Override
    public double calculer() {
        double ecartParts = nbParts - nbPartsDeclarants;

        double plafond = (ecartParts / 0.5) * plafondDemiPart;

        System.out.println("Plafond de baisse autorisée " + plafond);

        if (baisseImpot >= plafond) {
            return montantImpotDeclarants - plafond;
        }

        System.out.println("Plafond : " + montantImpotDeclarants);

        return montantImpotDeclarants;
    }

    public void setNbParts(double nbParts) {
        this.nbParts = nbParts;
    }

    public void setNbPartsDeclarants(double nbPartsDeclarants) {
        this.nbPartsDeclarants = nbPartsDeclarants;
    }

    public void setBaisseImpot(double baisseImpot) {
        this.baisseImpot = baisseImpot;
    }

    public void setMontantImpotDeclarants(double montantImpotDeclarants) {
        this.montantImpotDeclarants = montantImpotDeclarants;
    }
}
