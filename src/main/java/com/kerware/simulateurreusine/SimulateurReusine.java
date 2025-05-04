package com.kerware.simulateurreusine;

/**
 *  Cette classe permet de simuler le calcul de l'impôt sur le revenu
 *  en France pour l'année 2024 sur les revenus de l'année 2023 pour
 *  des cas simples de contribuables célibataires, mariés, divorcés, veufs
 *  ou pacsés avec ou sans enfants à charge ou enfants en situation de handicap
 *  et parent isolé.
 *
 *  EXEMPLE DE CODE DE TRES MAUVAISE QUALITE FAIT PAR UN DEBUTANT
 *
 *  Pas de lisibilité, pas de commentaires, pas de tests
 *  Pas de documentation, pas de gestion des erreurs
 *  Pas de logique métier, pas de modularité
 *  Pas de gestion des exceptions, pas de gestion des logs
 *  Principe "Single Responsability" non respecté
 *  Pas de traçabilité vers les exigences métier
 *
 *  Pourtant ce code fonctionne correctement
 *  Il s'agit d'un "legacy" code qui est difficile à maintenir
 *  L'auteur n'a pas fourni de tests unitaires
 **/

public class SimulateurReusine {

    // Les limites des tranches de revenus imposables
    private int limite00 = 0 ;
    private int limite01 = 11294;
    private int limite02 = 28797;
    private int limite03 = 82341;
    private int limite04 = 177106;
    private int limite05 = Integer.MAX_VALUE;

    private int[] limites = new int[6];

    // Les taux d'imposition par tranche
    private double taux00 = 0.0;
    private double taux01 = 0.11;
    private double taux02 = 0.3;
    private double taux03 = 0.41;
    private double taux04 = 0.45;

    private double[] taux = new double[5];

    // Les limites des tranches pour la contribution exceptionnelle sur les hauts revenus
    private int limiteCEHR00 = 0;
    private int limiteCEHR01 = 250000;
    private int limiteCEHR02 = 500000;
    private int limiteCEHR03 = 1000000;
    private int limiteCEHR04 = Integer.MAX_VALUE;

    private int[] limitesCEHR = new int[5];

    // Les taux de la contribution exceptionnelle sur les hauts revenus pour les celibataires
    private double tauxCEHRCelibataire00 = 0.0;
    private double tauxCEHRCelibataire01 = 0.03;
    private double tauxCEHRCelibataire02 = 0.04;
    private double tauxCEHRCelibataire03 = 0.04;

    private double[] tauxCEHRCelibataire = new double[4];

    // Les taux de la contribution exceptionnelle sur les hauts revenus pour les couples
    private double tauxCEHRCouple00 = 0.0;
    private double tauxCEHRCouple01 = 0.0;
    private double tauxCEHRCouple02 = 0.03;
    private double tauxCEHRCouple03 = 0.04;

    private double[] tauxCEHRCouple = new double[4];

    // Abattement
    private int limiteAbattementMax = 14171;
    private int limiteAbattementMin = 495;
    private double tauxAbattement = 0.1;

    // Plafond de baisse maximal par demi part
    private double plafondDemiPart = 1759;

    private double seuilDecoteDeclarantSeul = 1929;
    private double seuilDecoteDeclarantCouple = 3191;

    private double decoteMaxDeclarantSeul = 873;
    private double decoteMaxDeclarantCouple = 1444;
    private double tauxDecote = 0.4525;

    // revenu net
    private int revenuNetDeclarant1 = 0;
    private int revenuNetDeclarant2 = 0;
    
    // nb enfants
    private int nbEnfant = 0;
    
    // nb enfants handicapés
    private int nbEnfantHandicape = 0;

    // revenu fiscal de référence
    private double revenuFicalReference = 0;

    // revenu imposable
    private double revenuImposable = 0;

    // abattement
    private double abattement = 0;

    // nombre de parts des  déclarants
    private double nbPartsDeclarants = 0;
    
    // nombre de parts du foyer fiscal
    private double nbParts = 0;

    // decote
    private double decote = 0;
    
    // impôt des déclarants
    private double montantImpotDeclarants = 0;
    
    // impôt du foyer fiscal
    private double montantImpot = 0;
    private double montantImpotAvantDecote = 0;
    
    // parent isolé
    private boolean parentIsole = false;
    
    // Contribution exceptionnelle sur les hauts revenus
    private double contributionExceptionnelle = 0;

    // Getters pour adapter le code legacy pour les tests unitaires

    public double getRevenuReference() {
        return revenuFicalReference;
    }

    public double getDecote() {
        return decote;
    }

    public double getAbattement() {
        return abattement;
    }

    public double getNbParts() {
        return nbParts;
    }

    public double getImpotAvantDecote() {
        return montantImpotAvantDecote;
    }

    public double getImpotNet() {
        return montantImpot;
    }

    public int getRevenuNetDeclatant1() {
        return revenuNetDeclarant1;
    }

    public int getRevenuNetDeclatant2() {
        return revenuNetDeclarant2;
    }

    public double getContribExceptionnelle() {
        return contributionExceptionnelle;
    }

    // Fonction de calcul de l'impôt sur le revenu net en France en 2024 sur les revenu 2023

    public int calculImpot(int revNetDecl1, int revNetDecl2, SituationFamilialeReusine sitFam, int nbEnfants, int nbEnfantsHandicapes, boolean parentIsol) {

        // Préconditions
        if (revNetDecl1 < 0 || revNetDecl2 < 0) {
            throw new IllegalArgumentException("Le revenu net ne peut pas être négatif");
        }

        if (nbEnfants < 0) {
            throw new IllegalArgumentException("Le nombre d'enfants ne peut pas être négatif");
        }

        if (nbEnfantsHandicapes < 0) {
            throw new IllegalArgumentException("Le nombre d'enfants handicapés ne peut pas être négatif");
        }

        if (sitFam == null) {
            throw new IllegalArgumentException("La situation familiale ne peut pas être null");
        }

        if (nbEnfantsHandicapes > nbEnfants) {
            throw new IllegalArgumentException("Le nombre d'enfants handicapés ne peut pas être supérieur au nombre d'enfants");
        }

        if (nbEnfants > 7) {
            throw new IllegalArgumentException("Le nombre d'enfants ne peut pas être supérieur à 7");
        }

        if (parentIsol && (sitFam == SituationFamilialeReusine.MARIE || sitFam == SituationFamilialeReusine.PACSE)) {
            throw new IllegalArgumentException("Un parent isolé ne peut pas être marié ou pacsé");
        }

        boolean seul = sitFam == SituationFamilialeReusine.CELIBATAIRE || sitFam == SituationFamilialeReusine.DIVORCE || sitFam == SituationFamilialeReusine.VEUF;
        if (seul && revNetDecl2 > 0) {
            throw new IllegalArgumentException("Un célibataire, un divorcé ou un veuf ne peut pas avoir de revenu pour le déclarant 2");
        }

        // Initialisation des variables

        revenuNetDeclarant1 = revNetDecl1;
        revenuNetDeclarant2 = revNetDecl2;

        nbEnfant = nbEnfants;
        nbEnfantHandicape = nbEnfantsHandicapes;
        parentIsole = parentIsol;

        limites[0] = limite00;
        limites[1] = limite01;
        limites[2] = limite02;
        limites[3] = limite03;
        limites[4] = limite04;
        limites[5] = limite05;

        taux[0] = taux00;
        taux[1] = taux01;
        taux[2] = taux02;
        taux[3] = taux03;
        taux[4] = taux04;

        limitesCEHR[0] = limiteCEHR00;
        limitesCEHR[1] = limiteCEHR01;
        limitesCEHR[2] = limiteCEHR02;
        limitesCEHR[3] = limiteCEHR03;
        limitesCEHR[4] = limiteCEHR04;

        tauxCEHRCelibataire[0] = tauxCEHRCelibataire00;
        tauxCEHRCelibataire[1] = tauxCEHRCelibataire01;
        tauxCEHRCelibataire[2] = tauxCEHRCelibataire02;
        tauxCEHRCelibataire[3] = tauxCEHRCelibataire03;

        tauxCEHRCouple[0] = tauxCEHRCouple00;
        tauxCEHRCouple[1] = tauxCEHRCouple01;
        tauxCEHRCouple[2] = tauxCEHRCouple02;
        tauxCEHRCouple[3] = tauxCEHRCouple03;

        System.out.println("--------------------------------------------------");
        System.out.println("Revenu net declarant1 : " + revenuNetDeclarant1);
        System.out.println("Revenu net declarant2 : " + revenuNetDeclarant2);
        System.out.println("Situation familiale : " + sitFam.name());

        // Abattement
        // EXIGENCE : EXG_IMPOT_02
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

        revenuFicalReference = revenuNetDeclarant1 + revNetDecl2 - abattement;
        
        if (revenuFicalReference < 0) {
        	revenuFicalReference = 0;
        }

        System.out.println("Revenu fiscal de référence : " + revenuFicalReference);

        // parts déclarants
        // EXIG  : EXG_IMPOT_03
        switch (sitFam) {
            case CELIBATAIRE:
                nbPartsDeclarants = 1;
                break;
            case MARIE:
                nbPartsDeclarants = 2;
                break;
            case DIVORCE:
                nbPartsDeclarants = 1;
                break;
            case VEUF:
                nbPartsDeclarants = 1;
                break;
            case PACSE:
                nbPartsDeclarants = 2;
                break;
        }

        System.out.println("Nombre d'enfants  : " + nbEnfant);
        System.out.println("Nombre d'enfants handicapés : " + nbEnfantHandicape);

        // parts enfants à charge
        if (nbEnfant <= 2) {
            nbParts = nbPartsDeclarants + nbEnfant * 0.5;
        } else if (nbEnfant > 2) {
            nbParts = nbPartsDeclarants + 1.0 + (nbEnfant - 2);
        }

        // parent isolé

        System.out.println("Parent isolé : " + parentIsole);

        if (parentIsole) {
            if (nbEnfant > 0) {
                nbParts = nbParts + 0.5;
            }
        }

        // Veuf avec enfant
        if (sitFam == SituationFamilialeReusine.VEUF && nbEnfant > 0) {
            nbParts = nbParts + 1;
        }

        // enfant handicapé
        nbParts = nbParts + nbEnfantHandicape * 0.5;

        System.out.println("Nombre de parts : " + nbParts);

        // EXIGENCE : EXG_IMPOT_07:
        // Contribution exceptionnelle sur les hauts revenus
        contributionExceptionnelle = 0;
        int i = 0;
        
        do {
            if (revenuFicalReference >= limitesCEHR[i] && revenuFicalReference < limitesCEHR[i+1]) {
                if (nbPartsDeclarants == 1) {
                	contributionExceptionnelle += (revenuFicalReference - limitesCEHR[i]) * tauxCEHRCelibataire[i];
                } else {
                	contributionExceptionnelle += (revenuFicalReference - limitesCEHR[i]) * tauxCEHRCouple[i];
                }
                break;
            } else {
                if (nbPartsDeclarants == 1) {
                    contributionExceptionnelle += (limitesCEHR[i+1] - limitesCEHR[i]) * tauxCEHRCelibataire[i];
                } else {
                    contributionExceptionnelle += (limitesCEHR[i+1] - limitesCEHR[i]) * tauxCEHRCouple[i];
                }
            }
            i++;
        } while(i < 5);

        contributionExceptionnelle = Math.round(contributionExceptionnelle);
        
        System.out.println("Contribution exceptionnelle sur les hauts revenus : " + contributionExceptionnelle);

        // Calcul impôt des declarants
        // EXIGENCE : EXG_IMPOT_04
        revenuImposable = revenuFicalReference / nbPartsDeclarants ;

        montantImpotDeclarants = 0;

        i = 0;
        
        do {
            if (revenuImposable >= limites[i] && revenuImposable < limites[i+1]) {
            	montantImpotDeclarants += (revenuImposable - limites[i]) * taux[i];
                break;
            } else {
            	montantImpotDeclarants += (limites[i+1] - limites[i]) * taux[i];
            }
            i++;
        } while(i < 5);

        montantImpotDeclarants = montantImpotDeclarants * nbPartsDeclarants;
        montantImpotDeclarants = Math.round(montantImpotDeclarants);

        System.out.println("Impôt brut des déclarants : " + montantImpotDeclarants);

        // Calcul impôt foyer fiscal complet
        // EXIGENCE : EXG_IMPOT_04
        revenuImposable = revenuFicalReference / nbParts;
        montantImpot = 0;
        i = 0;

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
        montantImpot = Math.round(montantImpot);

        System.out.println("Impôt brut du foyer fiscal complet : " + montantImpot);

        // Vérification de la baisse d'impôt autorisée
        // EXIGENCE : EXG_IMPOT_05
        // baisse impot

        double baisseImpot = montantImpotDeclarants - montantImpot;

        System.out.println("Baisse d'impôt : " + baisseImpot);

        // dépassement plafond
        double ecartParts = nbParts - nbPartsDeclarants;

        double plafond = (ecartParts / 0.5) * plafondDemiPart;

        System.out.println("Plafond de baisse autorisée " + plafond);

        if (baisseImpot >= plafond) {
        	montantImpot = montantImpotDeclarants - plafond;
        }

        System.out.println("Impôt brut après plafonnement avant decote : " + montantImpot);
        
        montantImpotAvantDecote = montantImpot;

        // Calcul de la decote
        // EXIGENCE : EXG_IMPOT_06

        decote = 0;
        
        // decote
        if (nbPartsDeclarants == 1) {
            if (montantImpot < seuilDecoteDeclarantSeul) {
                 decote = decoteMaxDeclarantSeul - (montantImpot * tauxDecote);
            }
        }
        
        if (nbPartsDeclarants == 2) {
            if (montantImpot < seuilDecoteDeclarantCouple) {
                 decote =  decoteMaxDeclarantCouple - (montantImpot * tauxDecote);
            }
        }
        
        decote = Math.round(decote);

        if (montantImpot <= decote) {
            decote = montantImpot;
        }

        System.out.println("Decote : " + decote);

        montantImpot = montantImpot - decote;

        montantImpot += contributionExceptionnelle;

        montantImpot = Math.round(montantImpot);

        System.out.println("Impôt sur le revenu net final : " + montantImpot);
        
        return (int)montantImpot;
    }
}
