package com.kerware.simulateurreusine;

public class CalculRevenuReference implements ICalculateur {
    private double revenuNetDeclarant1;
    private double revenuNetDeclarant2;
    private double abattement;

    public CalculRevenuReference(double revenuNetDeclarant1, double revenuNetDeclarant2, double abattement) {
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
        this.abattement = abattement;
    }

    @Override
    public double calculer() {
        double revenuFicalReference = revenuNetDeclarant1 + revenuNetDeclarant2 - abattement;

        if (revenuFicalReference < 0) {
            revenuFicalReference = 0;
        }

        return revenuFicalReference;
    }

    public void setRevenuNetDeclarant1(double revenuNetDeclarant1) {
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
    }

    public void setRevenuNetDeclarant2(double revenuNetDeclarant2) {
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
    }

    public void setAbattement(double abattement) {
        this.abattement = abattement;
    }
}
