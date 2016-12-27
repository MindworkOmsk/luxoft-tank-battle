package battlefield;


import java.util.ArrayList;
import java.util.List;

public class BattlefieldHolder {
    private String[][] battlefieldMatrix;
    private List<Tank> enemyTanks;
    private Tank ourTank;

    public List<Tank> getEnemyTanks() {
        if (enemyTanks == null) {
            enemyTanks = new ArrayList<Tank>();
        }

        return enemyTanks;
    }

    public Tank getOurTank() {
        return ourTank;
    }

    public void setOurTank(Tank ourTank) {
        this.ourTank = ourTank;
    }

    public String[][] getBattlefieldMatrix() {
        if (battlefieldMatrix == null) {
            battlefieldMatrix = new String[BattlefieldHelper.DIMENSION + 1][BattlefieldHelper.DIMENSION + 1];
        }
        return battlefieldMatrix;
    }
}
