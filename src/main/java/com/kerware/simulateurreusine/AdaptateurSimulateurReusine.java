package com.kerware.simulateurreusine;

public class AdaptateurSimulateurReusine implements ICalculateurImpotReusine {

    private CalculAbattement calculateurAbattement = new CalculAbattement();
    private CalculBaisseImpot calculateurBaisseImpot = new CalculBaisseImpot();
    private CalculContributionExceptionnel calculateurContributionExceptionnel = new CalculContributionExceptionnel();
    private CalculDecote calculateurDecote = new CalculDecote();
    private CalculImpotAvantDecote calculateurImpotAvantDecoteDeclarant = new CalculImpotAvantDecote();
    private CalculImpotAvantDecote calculateurImpotAvantDecoteTotal = new CalculImpotAvantDecote();
    private CalculImpotNet calculateurImpotNet =  new CalculImpotNet();
    private CalculPart calculateurPart = new CalculPart();
    private CalculPartSituation calculateurPartSituation = new CalculPartSituation();
    private CalculRevenuReference calculateurRevenuDeReference = new CalculRevenuReference();
    private CalculPlafond calculateurPlafond = new CalculPlafond();

    private int revenusNetDecl1 = 0;
    private int revenusNetDecl2 = 0;

    @Override
    public void setRevenusNetDeclarant1(int revenuNet) {
        System.out.println( "Revenu net declarant1 : " + revenuNet );
        calculateurAbattement.setRevenuNetDeclarant1(revenuNet);
        calculateurRevenuDeReference.setRevenuNetDeclarant1(revenuNet);
        this.revenusNetDecl1 = revenuNet;
    }

    @Override
    public void setRevenusNetDeclarant2(int revenuNet) {
        System.out.println( "Revenu net declarant2 : " + revenuNet );
        calculateurAbattement.setRevenuNetDeclarant2(revenuNet);
        calculateurRevenuDeReference.setRevenuNetDeclarant2(revenuNet);
        this.revenusNetDecl2 = revenuNet;
    }

    @Override
    public void setSituationFamiliale(SituationFamilialeReusine situationFamiliale) {
        if (situationFamiliale != null) {
            System.out.println( "Situation familiale : " + situationFamiliale.name() );
        }
        calculateurAbattement.setSitFam(situationFamiliale);
        calculateurPart.setSitFam(situationFamiliale);
        calculateurPartSituation.setSitFam(situationFamiliale);
    }

    @Override
    public void setNbEnfantsACharge(int nbEnfantsACharge) {
        System.out.println( "Nombre d'enfants  : " + nbEnfantsACharge );
        calculateurPart.setNbEnfant(nbEnfantsACharge);
    }

    @Override
    public void setNbEnfantsSituationHandicap(int nbEnfSituationHandicap) {
        System.out.println( "Nombre d'enfants handicapés : " + nbEnfSituationHandicap );
        calculateurPart.setNbEnfantHandicape(nbEnfSituationHandicap);
    }

    @Override
    public void setParentIsole(boolean parentIsole) {
        System.out.println( "Parent isolé : " + parentIsole );
        calculateurPart.setParentIsole(parentIsole);
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
        calculateurContributionExceptionnel.setRevenuFicalReference(getRevenuFiscalReference());
        calculateurContributionExceptionnel.setNbPartsDeclarants(calculateurPartSituation.calculer());
        return calculateurContributionExceptionnel.calculer();
    }

    @Override
    public int getRevenuFiscalReference() {
        calculateurRevenuDeReference.setAbattement(getAbattement());
        return (int)calculateurRevenuDeReference.calculer();
    }

    @Override
    public int getAbattement() {
        return (int)calculateurAbattement.calculer();
    }

    @Override
    public double getNbPartsFoyerFiscal() {
        return calculateurPart.calculer();
    }

    @Override
    public int getImpotAvantDecote() {
        calculateurImpotAvantDecoteDeclarant.setRevenuFicalReference(getRevenuFiscalReference());
        calculateurImpotAvantDecoteTotal.setRevenuFicalReference(getRevenuFiscalReference());

        calculateurImpotAvantDecoteDeclarant.setNbParts(calculateurPartSituation.calculer());
        calculateurImpotAvantDecoteTotal.setNbParts(calculateurPart.calculer());

        calculateurBaisseImpot.setMontantImpotDeclarants(calculateurImpotAvantDecoteDeclarant.calculer());
        calculateurBaisseImpot.setMontantImpot(calculateurImpotAvantDecoteTotal.calculer());

        calculateurPlafond.setBaisseImpot(calculateurBaisseImpot.calculer());
        calculateurPlafond.setNbParts(calculateurPart.calculer());
        calculateurPlafond.setNbPartsDeclarants(calculateurPartSituation.calculer());
        calculateurPlafond.setMontantImpotDeclarants(calculateurImpotAvantDecoteTotal.calculer());

        int test = (int)calculateurPlafond.calculer();

        System.out.println(test);

        return test;
    }

    @Override
    public int getDecote() {
        calculateurDecote.setMontantImpot(getImpotAvantDecote());
        calculateurDecote.setNbPartsDeclarants(calculateurPartSituation.calculer());

        return (int)calculateurDecote.calculer();
    }

    @Override
    public int getImpotSurRevenuNet() {
        calculateurImpotNet.setMontantImpot(getImpotAvantDecote());
        calculateurImpotNet.setDecote(getDecote());
        calculateurImpotNet.setContributionExceptionnelle(getContribExceptionnelle());

        return (int)calculateurImpotNet.calculer();
    }
}
