package com.kerware.simulateurreusine;

import static com.kerware.simulateurreusine.Config.*;

public class CalculAbattement implements ICalculateur {
    private double revenuNetDeclarant1;
    private double revenuNetDeclarant2;
    private SituationFamilialeReusine sitFam;

    public CalculAbattement(double revenuNetDeclarant1, double revenuNetDeclarant2, SituationFamilialeReusine sitFam) {
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
        this.sitFam = sitFam;
    }

    @Override
    public double calculer() {
        double abattement;

        long abattement1 = Math.round(revenuNetDeclarant1 * tauxAbattement);
        long abattement2 = Math.round(revenuNetDeclarant2 * tauxAbattement);

        if (abattement1 > limiteAbattementMax) {
            abattement1 = limiteAbattementMax;
        }

        if (sitFam == SituationFamilialeReusine.MARIE || sitFam == SituationFamilialeReusine.PACSE) {
            if (abattement2 > limiteAbattementMax) {
                abattement2 = limiteAbattementMax;
            }
        }

        if (abattement1 < limiteAbattementMin) {
            abattement1 = limiteAbattementMin;
        }

        if (sitFam == SituationFamilialeReusine.MARIE || sitFam == SituationFamilialeReusine.PACSE) {
            if (abattement2 < limiteAbattementMin) {
                abattement2 = limiteAbattementMin;
            }
        }

        abattement = abattement1 + abattement2;

        System.out.println("Abattement : " + abattement);

        return abattement;
    }

    public void setRevenuNetDeclarant1(double revenuNetDeclarant1) {
        this.revenuNetDeclarant1 = revenuNetDeclarant1;
    }

    public void setRevenuNetDeclarant2(double revenuNetDeclarant2) {
        this.revenuNetDeclarant2 = revenuNetDeclarant2;
    }

    public void setSitFam(SituationFamilialeReusine sitFam) {
        this.sitFam = sitFam;
    }
}
