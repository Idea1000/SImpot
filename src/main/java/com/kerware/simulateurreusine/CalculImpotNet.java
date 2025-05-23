package com.kerware.simulateurreusine;

public class CalculImpotNet implements ICalculateur {
    private double montantImpot;
    private double decote;
    private double contributionExceptionnelle;

    public CalculImpotNet() {

    }

    @Override
    public double calculer() {
        montantImpot = montantImpot - decote;

        montantImpot += contributionExceptionnelle;

        return Math.round(montantImpot);
    }

    public void setMontantImpot(double montantImpot) {
        this.montantImpot = montantImpot;
    }

    public void setDecote(double decote) {
        this.decote = decote;
    }

    public void setContributionExceptionnelle(double contributionExceptionnelle) {
        this.contributionExceptionnelle = contributionExceptionnelle;
    }
}
