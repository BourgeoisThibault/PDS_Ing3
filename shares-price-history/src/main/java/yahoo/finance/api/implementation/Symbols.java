package yahoo.finance.api.implementation;

import lombok.Data;

import java.util.HashMap;

@Data
public class Symbols {
    private final HashMap<String, String> mappingCompaniesSymbols = new HashMap<>();

    public Symbols() {
        fillList();
    }

    private void fillList() {
        mappingCompaniesSymbols.put("Accor" ,"AC.PA");
        mappingCompaniesSymbols.put("Air Liquide" ,"AI.PA");
        mappingCompaniesSymbols.put("Alstom" ,"ALO.PA");
        mappingCompaniesSymbols.put("Arcelor-Mittal" ,"ARRB.F");
        mappingCompaniesSymbols.put("AXA" ,"CS.PA");
        mappingCompaniesSymbols.put("BNP Paribas" ,"BNP.PA");
        mappingCompaniesSymbols.put("Bouygues","EN.PA");
        mappingCompaniesSymbols.put("Cap Gemini","CAP.PA");
        mappingCompaniesSymbols.put("Carrefour","CA.PA");
        mappingCompaniesSymbols.put("Crédit agricole","CAF.PA");
        mappingCompaniesSymbols.put("Danone" ,"BN.PA");
        mappingCompaniesSymbols.put("EADS(AIRBUS","AIR.PA");
        mappingCompaniesSymbols.put("EDF","EDF.PA");
        mappingCompaniesSymbols.put("Essilor","EI.PA");
        mappingCompaniesSymbols.put("France Télécom" ,"F2TEZ1.EX");
        mappingCompaniesSymbols.put("GDF Suez","GSZ.PA");
        mappingCompaniesSymbols.put("Gemalto","LDV.F");
        mappingCompaniesSymbols.put("L'Oréal","OR.PA");
        mappingCompaniesSymbols.put("Lafarge","LHN.PA");
        mappingCompaniesSymbols.put("Legrand","LR.PA");
        mappingCompaniesSymbols.put("LVMH","MC.PA");
        mappingCompaniesSymbols.put("Michelin","ML.PA");
        mappingCompaniesSymbols.put("Pernod Ricard","RI.PA");
        mappingCompaniesSymbols.put("Kering (ex PPR","KER.PA");
        mappingCompaniesSymbols.put("Publicis","PUB.PA");
        mappingCompaniesSymbols.put("Renault","RNO.PA");
        mappingCompaniesSymbols.put("Safran","SAF.PA");
        mappingCompaniesSymbols.put("Saint-Gobain","SGO.PA");
        mappingCompaniesSymbols.put("Sanofi","SAN.PA");
        mappingCompaniesSymbols.put("Schneider Electric","SU.PA");
        mappingCompaniesSymbols.put("Société Générale","GLE.PA");
        mappingCompaniesSymbols.put("Solvay","SOL.F");
        mappingCompaniesSymbols.put("STMicroelectronics","STM.PA");
        mappingCompaniesSymbols.put("Technip","TEC.PA");
        mappingCompaniesSymbols.put("Total","FP.PA");
        mappingCompaniesSymbols.put("Unibail-Rodamco" ,"UBL.F");
        mappingCompaniesSymbols.put("Vallourec","VK.PA");
        mappingCompaniesSymbols.put("Véolia Environnement","VIE.PA");
        mappingCompaniesSymbols.put("Vinci","DG.PA");
        mappingCompaniesSymbols.put("Vivendi","VIV.PA");
    }
}
