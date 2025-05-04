package com.kerware.simulateurreusine;

public class Config {
    // Les limites des tranches de revenus imposables
    public static final int limite00 = 0 ;
    public static final int limite01 = 11294;
    public static final int limite02 = 28797;
    public static final int limite03 = 82341;
    public static final int limite04 = 177106;
    public static final int limite05 = Integer.MAX_VALUE;

    public static final int[] limites = new int[6];

    // Les taux d'imposition par tranche
    public static final double taux00 = 0.0;
    public static final double taux01 = 0.11;
    public static final double taux02 = 0.3;
    public static final double taux03 = 0.41;
    public static final double taux04 = 0.45;

    public static final double[] taux = new double[5];

    // Les limites des tranches pour la contribution exceptionnelle sur les hauts revenus
    public static final int limiteCEHR00 = 0;
    public static final int limiteCEHR01 = 250000;
    public static final int limiteCEHR02 = 500000;
    public static final int limiteCEHR03 = 1000000;
    public static final int limiteCEHR04 = Integer.MAX_VALUE;

    public static final int[] limitesCEHR = new int[5];

    // Les taux de la contribution exceptionnelle sur les hauts revenus pour les celibataires
    public static final double tauxCEHRCelibataire00 = 0.0;
    public static final double tauxCEHRCelibataire01 = 0.03;
    public static final double tauxCEHRCelibataire02 = 0.04;
    public static final double tauxCEHRCelibataire03 = 0.04;

    public static final double[] tauxCEHRCelibataire = new double[4];

    // Les taux de la contribution exceptionnelle sur les hauts revenus pour les couples
    public static final double tauxCEHRCouple00 = 0.0;
    public static final double tauxCEHRCouple01 = 0.0;
    public static final double tauxCEHRCouple02 = 0.03;
    public static final double tauxCEHRCouple03 = 0.04;

    public static final double[] tauxCEHRCouple = new double[4];

    // Abattement
    public static final int limiteAbattementMax = 14171;
    public static final int limiteAbattementMin = 495;
    public static final double tauxAbattement = 0.1;

    // Plafond de baisse maximal par demi part
    public static final double plafondDemiPart = 1759;

    public static final double seuilDecoteDeclarantSeul = 1929;
    public static final double seuilDecoteDeclarantCouple = 3191;

    public static final double decoteMaxDeclarantSeul = 873;
    public static final double decoteMaxDeclarantCouple = 1444;
    public static final double tauxDecote = 0.4525;
}
