package com.kerware.simulateurreusine;

public class AdaptateurSimulateurReusine implements ICalculateurImpotReusine {

    private SimulateurReusine simulateur = new SimulateurReusine();

    private int revenusNetDecl1 = 0;
    private int revenusNetDecl2 = 0;
    private SituationFamilialeReusine situationFamiliale;
    private int nbEnfantsACharge;
    private int nbEnfantsSituationHandicap;
    private boolean parentIsole;


    @Override
    public void setRevenusNetDeclarant1(int revenuNet) {
        this.revenusNetDecl1 = revenuNet;
    }

    @Override
    public void setRevenusNetDeclarant2(int revenuNet) {
        this.revenusNetDecl2 = revenuNet;
    }

    @Override
    public void setSituationFamiliale(SituationFamilialeReusine situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    @Override
    public void setNbEnfantsACharge(int nbEnfantsACharge) {
        this.nbEnfantsACharge = nbEnfantsACharge;
    }

    @Override
    public void setNbEnfantsSituationHandicap(int nbEnfSituationHandicap) {
        this.nbEnfantsSituationHandicap = nbEnfSituationHandicap;
    }

    @Override
    public void setParentIsole(boolean parentIsole) {
        this.parentIsole = parentIsole;
    }

    @Override
    public void calculImpotSurRevenuNet() {
         simulateur.calculImpot(revenusNetDecl1, revenusNetDecl2, situationFamiliale, nbEnfantsACharge, nbEnfantsSituationHandicap, parentIsole);
    }

    @Override
    public int getRevenuNetDeclatant1() {
        return revenusNetDecl1;
    }

    @Override
    public int getRevenuNetDeclatant2() {
        return revenusNetDecl2;
    }

    @Override
    public double getContribExceptionnelle() {
        return simulateur.getContribExceptionnelle();
    }

    @Override
    public int getRevenuFiscalReference() {
        return (int)simulateur.getRevenuReference();
    }

    @Override
    public int getAbattement() {
        return (int)simulateur.getAbattement();
    }

    @Override
    public double getNbPartsFoyerFiscal() {
        return simulateur.getNbParts();
    }

    @Override
    public int getImpotAvantDecote() {
        return (int)simulateur.getImpotAvantDecote();
    }

    @Override
    public int getDecote() {
        return (int)simulateur.getDecote();
    }

    @Override
    public int getImpotSurRevenuNet() {
        return (int)simulateur.getImpotNet();
    }
}
