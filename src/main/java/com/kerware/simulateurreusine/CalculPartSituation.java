package com.kerware.simulateurreusine;

import static com.kerware.simulateurreusine.SituationFamilialeReusine.*;

public class CalculPartSituation implements ICalculateur {
    private SituationFamilialeReusine sitFam;

    public CalculPartSituation() {

    }

    @Override
    public double calculer() {
        switch (sitFam) {
            // Si celibataire, divorcé ou veuf : 1 part
            case CELIBATAIRE:
            case DIVORCE:
            case VEUF:
                return 1;

            // Si marié ou pacsé : 2 parts
            case MARIE:
            case PACSE:
            default:
                return 2;
        }
    }

    public void setSitFam(SituationFamilialeReusine sitFam) {
        this.sitFam = sitFam;
    }
}
