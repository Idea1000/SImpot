package com.kerware.simulateurreusine;

public interface ICalculateurImpotReusine {

    public void setRevenusNetDeclarant1(int revenuNet);
    public void setRevenusNetDeclarant2(int revenuNet);
    public void setSituationFamiliale(SituationFamilialeReusine situationFamiliale);
    public void setNbEnfantsACharge(int nbEnfantsACharge);
    public void setNbEnfantsSituationHandicap(int nbEnfSituationHandicap);
    public void setParentIsole(boolean parentIsole);

    public int getRevenuNetDeclatant1();
    public int getRevenuNetDeclatant2();
    public double getContribExceptionnelle();
    public int getRevenuFiscalReference();
    public int getAbattement();
    public double getNbPartsFoyerFiscal();
    public int getImpotAvantDecote();
    public int getDecote();
    public int getImpotSurRevenuNet();

}
