package risk.components;

import risk.logique.carte.Region;
import risk.logique.carte.Territoire;
import risk.panes.GamePane;

import java.util.ArrayList;
import java.util.Objects;

public class CarteUIMonde extends CarteUI {

    public CarteUIMonde() {
        super("Monde");
    }

    public void initialisationCarte(GamePane game) {
        this.game = game;
        // ---------------------------------------------------------------------------------------------------------
        // J1 - V6
        this.lines.add(this.newLine(-40, 130, 25, 130));
        this.lines.add(this.newLine(1113, 130, 1300, 130));
        // J5 - J6
        this.lines.add(this.newLine(311, 73, 375, 65));
        // J5 - J7
        this.lines.add(this.newLine(255, 140, 385, 70));
        // J5 - J8
        this.lines.add(this.newLine(353, 140, 408, 80));
        // R2 - O5
        this.lines.add(this.newLine(400, 410, 490, 350));
        // J5 - B2
        this.lines.add(this.newLine(485, 100, 495, 109));
        // B1 - B2
        this.lines.add(this.newLine(510, 120, 537, 148));
        // B2 - B4
        this.lines.add(this.newLine(518, 115, 575, 125));
        // B1 - B4
        this.lines.add(this.newLine(553, 155, 575, 133));
        // B1 - B3
        this.lines.add(this.newLine(553, 155, 575, 160));
        // B3 - B4
        this.lines.add(this.newLine(575, 160, 575, 133));
        // B1 - B7
        this.lines.add(this.newLine(550, 171, 552, 178));
        // B7 - O5
        this.lines.add(this.newLine(540, 231, 542, 240));
        // B5 - O5
        this.lines.add(this.newLine(590, 229, 600, 210));
        // B5 - O3
        this.lines.add(this.newLine(640, 233, 640, 250));
        // O2 - V7
        this.lines.add(this.newLine(711, 325, 722, 315));
        // O2 - O4
        this.lines.add(this.newLine(712, 420, 745, 455));
        // O4 - O6
        this.lines.add(this.newLine(691, 490, 723, 490));
        // V5 - V6
        this.lines.add(this.newLine(1086, 225, 1059, 200));
        // V5 - V9
        this.lines.add(this.newLine(1086, 225, 1048, 225));
        // V9 - M2
        this.lines.add(this.newLine(990, 355, 1005, 380));
        // M2 - M4
        this.lines.add(this.newLine(1023, 435, 1023, 480));
        // M2 - M3
        this.lines.add(this.newLine(1045, 413, 1100, 413));
        // M3 - M4
        this.lines.add(this.newLine(1100, 413, 1050, 463));
        // M1 - M3
        this.lines.add(this.newLine(1100, 445, 1100, 413));
        // ---------------------------------------------------------------------------------------------------------
        Region.addRegion(new Region("R", "Amérique du Sud"));
        Region.addRegion(new Region("B", "Europe"));
        Region.addRegion(new Region("J", "Amérique du Nord"));
        Region.addRegion(new Region("V", "Asie"));
        Region.addRegion(new Region("O", "Afrique"));
        Region.addRegion(new Region("M", "Océanie"));
        // ---------------------------------------------------------------------------------------------------------
        // Amerique du sud
        this.texts.add(this.newText(185,490, "Amérique"));
        this.texts.add(this.newText(195,510, "du Sud"));
        this.territoiresUI.add(new TerritoireUI(this.game, "R1", "Argentine", 197, 457));
        this.territoiresUI.add(new TerritoireUI(this.game, "R2", "Brésil", 218, 352));
        this.territoiresUI.add(new TerritoireUI(this.game, "R3", "Pérou", 155, 352));
        this.territoiresUI.add(new TerritoireUI(this.game, "R4", "Vénézuela", 165, 273));
        // Europe
        this.texts.add(this.newText(600,75, "Europe"));
        this.territoiresUI.add(new TerritoireUI(this.game, "B1", "Grande-Bretagne", 430, 67));
        this.territoiresUI.add(new TerritoireUI(this.game, "B2", "Islande", 392, 17));
        this.territoiresUI.add(new TerritoireUI(this.game, "B3", "Europe du Nord", 482, 70));
        this.territoiresUI.add(new TerritoireUI(this.game, "B4", "Scandinavie", 506, 21));
        this.territoiresUI.add(new TerritoireUI(this.game, "B5", "Europe du Sud", 506, 104));
        this.territoiresUI.add(new TerritoireUI(this.game, "B6", "Ukraine", 582, 60));
        this.territoiresUI.add(new TerritoireUI(this.game, "B7", "Europe de l'Ouest", 439, 110));
        // Amerique du Nord
        this.texts.add(this.newText(260,250, "Amérique"));
        this.texts.add(this.newText(265,270, "du Nord"));
        this.territoiresUI.add(new TerritoireUI(this.game, "J1", "Alaska", -39, 17));
        this.territoiresUI.add(new TerritoireUI(this.game, "J2", "Alberta", 39, 65));
        this.territoiresUI.add(new TerritoireUI(this.game, "J3", "Amérique Centrale", 79, 214));
        this.territoiresUI.add(new TerritoireUI(this.game, "J4", "Etat de l'Est", 99, 132));
        this.territoiresUI.add(new TerritoireUI(this.game, "J5", "Groënland", 332, -5));
        this.territoiresUI.add(new TerritoireUI(this.game, "J6", "Territoire du Nord-Ouest", 91, 7));
        this.territoiresUI.add(new TerritoireUI(this.game, "J7", "Ontario", 128, 72));
        this.territoiresUI.add(new TerritoireUI(this.game, "J8", "Québec", 191, 69));
        this.territoiresUI.add(new TerritoireUI(this.game, "J9", "Etat de l'Ouest", 30, 125));
        //Asie
        this.texts.add(this.newText(1065,275, "Asie"));
        this.territoiresUI.add(new TerritoireUI(this.game, "V1", "Afghanistan", 686, 104));
        this.territoiresUI.add(new TerritoireUI(this.game, "V2", "Chine", 821, 159));
        this.territoiresUI.add(new TerritoireUI(this.game, "V3", "Inde", 754, 204));
        this.territoiresUI.add(new TerritoireUI(this.game, "V4", "Tchita", 841, 52));
        this.territoiresUI.add(new TerritoireUI(this.game, "V5", "Japon", 973, 129));
        this.territoiresUI.add(new TerritoireUI(this.game, "V6", "Kamchaka", 962, 41));
        this.territoiresUI.add(new TerritoireUI(this.game, "V7", "Moyen-Orient", 613, 175));
        this.territoiresUI.add(new TerritoireUI(this.game, "V8", "Mongolie", 849, 101));
        this.territoiresUI.add(new TerritoireUI(this.game, "V9", "Siam", 856, 239));
        this.territoiresUI.add(new TerritoireUI(this.game, "V10", "Sibérie", 744, 19));
        this.territoiresUI.add(new TerritoireUI(this.game, "V11", "Oural", 688, 33));
        this.territoiresUI.add(new TerritoireUI(this.game, "V12", "Yakoutie", 845, 15));
        //Afrique
        this.texts.add(this.newText(515,410, "Afrique"));
        this.territoiresUI.add(new TerritoireUI(this.game, "O1", "Congo", 520, 299));
        this.territoiresUI.add(new TerritoireUI(this.game, "O2", "Afrique Orientale", 579, 284));
        this.territoiresUI.add(new TerritoireUI(this.game, "O3", "Egypte", 524, 188));
        this.territoiresUI.add(new TerritoireUI(this.game, "O4", "Madagascar", 623, 382));
        this.territoiresUI.add(new TerritoireUI(this.game, "O5", "Afrique du Nord", 445, 216));
        this.territoiresUI.add(new TerritoireUI(this.game, "O6", "Afrique du Sud", 542, 395));
        // Oceanie
        this.texts.add(this.newText(925,500, "Océanie"));
        this.territoiresUI.add(new TerritoireUI(this.game, "M1", "Océanie Orientale", 988, 415));
        this.territoiresUI.add(new TerritoireUI(this.game, "M2", "Indonésie", 870, 271));
        this.territoiresUI.add(new TerritoireUI(this.game, "M3", "Nouvelle Guinée", 1007, 314));
        this.territoiresUI.add(new TerritoireUI(this.game, "M4", "Océanie Occidentale", 932, 412));
        // ---------------------------------------------------------------------------------------------------------

        //Argentine
        this.getTerritoireUIById("R1").addTerritoireAdjacent(this.getTerritoireUIById("R2"));
        this.getTerritoireUIById("R1").addTerritoireAdjacent(this.getTerritoireUIById("R3"));

        //Brésil
        this.getTerritoireUIById("R2").addTerritoireAdjacent(this.getTerritoireUIById("R1"));
        this.getTerritoireUIById("R2").addTerritoireAdjacent(this.getTerritoireUIById("R3"));
        this.getTerritoireUIById("R2").addTerritoireAdjacent(this.getTerritoireUIById("R4"));
        this.getTerritoireUIById("R2").addTerritoireAdjacent(this.getTerritoireUIById("O5"));

        //Pérou
        this.getTerritoireUIById("R3").addTerritoireAdjacent(this.getTerritoireUIById("R1"));
        this.getTerritoireUIById("R3").addTerritoireAdjacent(this.getTerritoireUIById("R2"));
        this.getTerritoireUIById("R3").addTerritoireAdjacent(this.getTerritoireUIById("R4"));

        //Vénézuela
        this.getTerritoireUIById("R4").addTerritoireAdjacent(this.getTerritoireUIById("R2"));
        this.getTerritoireUIById("R4").addTerritoireAdjacent(this.getTerritoireUIById("R3"));

        //--------

        //Grande-Bretagne
        this.getTerritoireUIById("B1").addTerritoireAdjacent(this.getTerritoireUIById("B7"));
        this.getTerritoireUIById("B1").addTerritoireAdjacent(this.getTerritoireUIById("B3"));
        this.getTerritoireUIById("B1").addTerritoireAdjacent(this.getTerritoireUIById("B4"));
        this.getTerritoireUIById("B1").addTerritoireAdjacent(this.getTerritoireUIById("B2"));

        //Islande
        this.getTerritoireUIById("B2").addTerritoireAdjacent(this.getTerritoireUIById("J5"));
        this.getTerritoireUIById("B2").addTerritoireAdjacent(this.getTerritoireUIById("B1"));
        this.getTerritoireUIById("B2").addTerritoireAdjacent(this.getTerritoireUIById("B4"));

        //Europe du Nord
        this.getTerritoireUIById("B3").addTerritoireAdjacent(this.getTerritoireUIById("B1"));
        this.getTerritoireUIById("B3").addTerritoireAdjacent(this.getTerritoireUIById("B4"));
        this.getTerritoireUIById("B3").addTerritoireAdjacent(this.getTerritoireUIById("B6"));
        this.getTerritoireUIById("B3").addTerritoireAdjacent(this.getTerritoireUIById("B5"));
        this.getTerritoireUIById("B3").addTerritoireAdjacent(this.getTerritoireUIById("B7"));

        //Scandinavie
        this.getTerritoireUIById("B4").addTerritoireAdjacent(this.getTerritoireUIById("B6"));
        this.getTerritoireUIById("B4").addTerritoireAdjacent(this.getTerritoireUIById("B3"));
        this.getTerritoireUIById("B4").addTerritoireAdjacent(this.getTerritoireUIById("B1"));
        this.getTerritoireUIById("B4").addTerritoireAdjacent(this.getTerritoireUIById("B2"));

        //Europe du Sud
        this.getTerritoireUIById("B5").addTerritoireAdjacent(this.getTerritoireUIById("B7"));
        this.getTerritoireUIById("B5").addTerritoireAdjacent(this.getTerritoireUIById("B3"));
        this.getTerritoireUIById("B5").addTerritoireAdjacent(this.getTerritoireUIById("V7"));
        this.getTerritoireUIById("B5").addTerritoireAdjacent(this.getTerritoireUIById("O3"));
        this.getTerritoireUIById("B5").addTerritoireAdjacent(this.getTerritoireUIById("O5"));

        //Ukraine
        this.getTerritoireUIById("B6").addTerritoireAdjacent(this.getTerritoireUIById("B4"));
        this.getTerritoireUIById("B6").addTerritoireAdjacent(this.getTerritoireUIById("B3"));
        this.getTerritoireUIById("B6").addTerritoireAdjacent(this.getTerritoireUIById("B5"));
        this.getTerritoireUIById("B6").addTerritoireAdjacent(this.getTerritoireUIById("V7"));
        this.getTerritoireUIById("B6").addTerritoireAdjacent(this.getTerritoireUIById("V1"));
        this.getTerritoireUIById("B6").addTerritoireAdjacent(this.getTerritoireUIById("V11"));

        //Europe de l'Ouest
        this.getTerritoireUIById("B7").addTerritoireAdjacent(this.getTerritoireUIById("B1"));
        this.getTerritoireUIById("B7").addTerritoireAdjacent(this.getTerritoireUIById("O5"));
        this.getTerritoireUIById("B7").addTerritoireAdjacent(this.getTerritoireUIById("B5"));
        this.getTerritoireUIById("B7").addTerritoireAdjacent(this.getTerritoireUIById("B3"));

        //--------

        //Alaska
        this.getTerritoireUIById("J1").addTerritoireAdjacent(this.getTerritoireUIById("J2"));
        this.getTerritoireUIById("J1").addTerritoireAdjacent(this.getTerritoireUIById("J6"));
        this.getTerritoireUIById("J1").addTerritoireAdjacent(this.getTerritoireUIById("V6"));

        //Alberta
        this.getTerritoireUIById("J2").addTerritoireAdjacent(this.getTerritoireUIById("J1"));
        this.getTerritoireUIById("J2").addTerritoireAdjacent(this.getTerritoireUIById("J6"));
        this.getTerritoireUIById("J2").addTerritoireAdjacent(this.getTerritoireUIById("J7"));
        this.getTerritoireUIById("J2").addTerritoireAdjacent(this.getTerritoireUIById("J9"));

        //Amérique Centrale
        this.getTerritoireUIById("J3").addTerritoireAdjacent(this.getTerritoireUIById("J9"));
        this.getTerritoireUIById("J3").addTerritoireAdjacent(this.getTerritoireUIById("J4"));
        this.getTerritoireUIById("J3").addTerritoireAdjacent(this.getTerritoireUIById("R4"));

        //Etat de l'Est
        this.getTerritoireUIById("J4").addTerritoireAdjacent(this.getTerritoireUIById("J7"));
        this.getTerritoireUIById("J4").addTerritoireAdjacent(this.getTerritoireUIById("J8"));
        this.getTerritoireUIById("J4").addTerritoireAdjacent(this.getTerritoireUIById("J2"));
        this.getTerritoireUIById("J4").addTerritoireAdjacent(this.getTerritoireUIById("J9"));
        this.getTerritoireUIById("J4").addTerritoireAdjacent(this.getTerritoireUIById("J3"));

        //Groënland
        this.getTerritoireUIById("J5").addTerritoireAdjacent(this.getTerritoireUIById("J6"));
        this.getTerritoireUIById("J5").addTerritoireAdjacent(this.getTerritoireUIById("J7"));
        this.getTerritoireUIById("J5").addTerritoireAdjacent(this.getTerritoireUIById("J8"));
        this.getTerritoireUIById("J5").addTerritoireAdjacent(this.getTerritoireUIById("B2"));

        //Territoire du Nord-Ouest
        this.getTerritoireUIById("J6").addTerritoireAdjacent(this.getTerritoireUIById("J1"));
        this.getTerritoireUIById("J6").addTerritoireAdjacent(this.getTerritoireUIById("J2"));
        this.getTerritoireUIById("J6").addTerritoireAdjacent(this.getTerritoireUIById("J7"));
        this.getTerritoireUIById("J6").addTerritoireAdjacent(this.getTerritoireUIById("J5"));

        //Ontario
        this.getTerritoireUIById("J7").addTerritoireAdjacent(this.getTerritoireUIById("J5"));
        this.getTerritoireUIById("J7").addTerritoireAdjacent(this.getTerritoireUIById("J6"));
        this.getTerritoireUIById("J7").addTerritoireAdjacent(this.getTerritoireUIById("J2"));
        this.getTerritoireUIById("J7").addTerritoireAdjacent(this.getTerritoireUIById("J9"));
        this.getTerritoireUIById("J7").addTerritoireAdjacent(this.getTerritoireUIById("J4"));
        this.getTerritoireUIById("J7").addTerritoireAdjacent(this.getTerritoireUIById("J8"));

        //Québec
        this.getTerritoireUIById("J8").addTerritoireAdjacent(this.getTerritoireUIById("J5"));
        this.getTerritoireUIById("J8").addTerritoireAdjacent(this.getTerritoireUIById("J7"));
        this.getTerritoireUIById("J8").addTerritoireAdjacent(this.getTerritoireUIById("J4"));

        //Etats de l'Ouest
        this.getTerritoireUIById("J9").addTerritoireAdjacent(this.getTerritoireUIById("J2"));
        this.getTerritoireUIById("J9").addTerritoireAdjacent(this.getTerritoireUIById("J7"));
        this.getTerritoireUIById("J9").addTerritoireAdjacent(this.getTerritoireUIById("J4"));
        this.getTerritoireUIById("J9").addTerritoireAdjacent(this.getTerritoireUIById("J3"));

        //--------

        //Congo
        this.getTerritoireUIById("O1").addTerritoireAdjacent(this.getTerritoireUIById("O2"));
        this.getTerritoireUIById("O1").addTerritoireAdjacent(this.getTerritoireUIById("O5"));
        this.getTerritoireUIById("O1").addTerritoireAdjacent(this.getTerritoireUIById("O6"));

        //Afrique Orientale
        this.getTerritoireUIById("O2").addTerritoireAdjacent(this.getTerritoireUIById("O1"));
        this.getTerritoireUIById("O2").addTerritoireAdjacent(this.getTerritoireUIById("O6"));
        this.getTerritoireUIById("O2").addTerritoireAdjacent(this.getTerritoireUIById("O4"));
        this.getTerritoireUIById("O2").addTerritoireAdjacent(this.getTerritoireUIById("V7"));

        //Egypte
        this.getTerritoireUIById("O3").addTerritoireAdjacent(this.getTerritoireUIById("O2"));
        this.getTerritoireUIById("O3").addTerritoireAdjacent(this.getTerritoireUIById("O5"));
        this.getTerritoireUIById("O3").addTerritoireAdjacent(this.getTerritoireUIById("V7"));
        this.getTerritoireUIById("O3").addTerritoireAdjacent(this.getTerritoireUIById("B5"));

        //Madagascar
        this.getTerritoireUIById("O4").addTerritoireAdjacent(this.getTerritoireUIById("O2"));
        this.getTerritoireUIById("O4").addTerritoireAdjacent(this.getTerritoireUIById("O6"));

        //Afrique du Nord
        this.getTerritoireUIById("O5").addTerritoireAdjacent(this.getTerritoireUIById("R2"));
        this.getTerritoireUIById("O5").addTerritoireAdjacent(this.getTerritoireUIById("B7"));
        this.getTerritoireUIById("O5").addTerritoireAdjacent(this.getTerritoireUIById("B5"));
        this.getTerritoireUIById("O5").addTerritoireAdjacent(this.getTerritoireUIById("O3"));
        this.getTerritoireUIById("O5").addTerritoireAdjacent(this.getTerritoireUIById("O2"));
        this.getTerritoireUIById("O5").addTerritoireAdjacent(this.getTerritoireUIById("O1"));

        //Afrique du Sud
        this.getTerritoireUIById("O6").addTerritoireAdjacent(this.getTerritoireUIById("O1"));
        this.getTerritoireUIById("O6").addTerritoireAdjacent(this.getTerritoireUIById("O2"));
        this.getTerritoireUIById("O6").addTerritoireAdjacent(this.getTerritoireUIById("O4"));

        //--------

        //Océanie orientale
        this.getTerritoireUIById("M1").addTerritoireAdjacent(this.getTerritoireUIById("M3"));
        this.getTerritoireUIById("M1").addTerritoireAdjacent(this.getTerritoireUIById("M4"));

        //Indonésie
        this.getTerritoireUIById("M2").addTerritoireAdjacent(this.getTerritoireUIById("M4"));
        this.getTerritoireUIById("M2").addTerritoireAdjacent(this.getTerritoireUIById("M3"));
        this.getTerritoireUIById("M2").addTerritoireAdjacent(this.getTerritoireUIById("V9"));

        //Nouvelle Guinée
        this.getTerritoireUIById("M3").addTerritoireAdjacent(this.getTerritoireUIById("M1"));
        this.getTerritoireUIById("M3").addTerritoireAdjacent(this.getTerritoireUIById("M2"));
        this.getTerritoireUIById("M3").addTerritoireAdjacent(this.getTerritoireUIById("M4"));

        //Océanie Occidentale
        this.getTerritoireUIById("M4").addTerritoireAdjacent(this.getTerritoireUIById("M1"));
        this.getTerritoireUIById("M4").addTerritoireAdjacent(this.getTerritoireUIById("M2"));
        this.getTerritoireUIById("M4").addTerritoireAdjacent(this.getTerritoireUIById("M3"));

    }

    private TerritoireUI getTerritoireUIById(String id) {
        for (TerritoireUI territoireUI : this.territoiresUI) {
            if (Objects.equals(territoireUI.getId(), id)) {
                return territoireUI;
            }
        }
        return null;
    }
}
