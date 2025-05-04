package com.kerware.simulateurreusine;

public class CalculPart implements ICalculateur {
    private SituationFamilialeReusine sitFam;
    private int nbEnfant;
    private int nbEnfantHandicape;
    private boolean parentIsole;

    public CalculPart(int nbEnfant, int nbEnfantHandicape, boolean parentIsole) {
        this.nbEnfant = nbEnfant;
        this.nbEnfantHandicape = nbEnfantHandicape;
        this.parentIsole = parentIsole;
    }

    @Override
    public double calculer() {
        double nbParts;

        nbParts = calculerPartSituation();

        System.out.println("Nombre d'enfants  : " + nbEnfant);
        System.out.println("Nombre d'enfants handicapés : " + nbEnfantHandicape);
        nbParts += calculerPartEnfant();

        System.out.println("Parent isolé : " + parentIsole);
        nbParts += calculerPartParentIsole();

        nbParts += calculerPartVeuf();

        nbParts += calculerPartEnfantHandicape();

        System.out.println("Nombre de parts : " + nbParts);

        return nbParts;
    }

    /**
     * Calcule les parts basé sur la situation
     * @return
     */
    public int calculerPartSituation() {
        switch (sitFam) {
            // Si celibataire, divorcé ou veuf : 1 part
            case CELIBATAIRE:
            case DIVORCE:
            case VEUF:
                return 1;

            // Si marié ou pacsé : 2 parts
            case MARIE:
            case PACSE:
                return 2;

            // Si aucune situation (??) : 0
            default:
                return 0;
        }
    }

    /**
     * Calcule les parts basé sur les enfants
     * @return
     */
    private double calculerPartEnfant() {
        if (nbEnfant <= 2) {
            return nbEnfant * 0.5;
        } else if (nbEnfant > 2) {
            return 1.0 + (nbEnfant - 2);
        }
        return 0;
    }

    /**
     * Calcule les parts basé sur le fait que ce soit un parent isolé
     * @return
     */
    private double calculerPartParentIsole() {
        if (parentIsole && nbEnfant > 0) {
            return 0.5;
        }
        return 0;
    }

    /**
     * Calcule les parts basé sur le fait que ce soit un veuf(ve) avec enfant(s)
     * @return
     */
    private double calculerPartVeuf() {
        if (sitFam == SituationFamilialeReusine.VEUF && nbEnfant > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Calcule les parts basé sur la presence d'un enfant handicapé
     * @return
     */
    private double calculerPartEnfantHandicape() {
        return nbEnfantHandicape * 0.5;
    }

    public void setSitFam(SituationFamilialeReusine sitFam) {
        this.sitFam = sitFam;
    }

    public void setNbEnfant(int nbEnfant) {
        this.nbEnfant = nbEnfant;
    }

    public void setNbEnfantHandicape(int nbEnfantHandicape) {
        this.nbEnfantHandicape = nbEnfantHandicape;
    }

    public void setParentIsole(boolean parentIsole) {
        this.parentIsole = parentIsole;
    }
}
