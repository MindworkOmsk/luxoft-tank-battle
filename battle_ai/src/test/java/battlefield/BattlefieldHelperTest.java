package battlefield;

import common.Constants;
import common.TankOrientation;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;


public class BattlefieldHelperTest {

    private String battlefieldString =
                      "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼"
                    + "☼                         Ѡ «    ☼"
                    + "☼                           ˄    ☼"
                    + "☼  ╥╥╬  ╦╬╬  ╬╦╥  ╥╥╬  ╦╥╬  ╥╥╥  ☼"
                    + "☼  ╬┌╦  ╦╥╬  ╦╬╥  ┌╬╦  ╬╥╥  ╥╬╥  ☼"
                    + "☼  ╬╬╬  ╞╬╣ •╬╬╣˄ ╠╬╣  ╞╬╡ ¿╬╬╬  ☼"
                    + "☼  ╬╬╣  ╠╣╬ ˄╬╬╣  ╬╬╬  ╠╩╡  ╞╬╡  ☼"
                    + "☼  ╠╬┐  ╬╬╬  ╠╬╬☼☼╬╬╬  ╬╬╡  ╞╬╣  ☼"
                    + "☼  ╬╣╡  ╬╣╬  ╞╬╬☼☼╬╬╬  ╬╩╡  ╠╬╬  ☼"
                    + "☼  ╬╬╬  ╬╬╡  │╡╣  └╬╬  ╬╬╬ •╬╬╬  ☼"
                    + "☼  ╠╩┘  ╞╨╡  ╬╩┘  ╬╠╬  ╠╬╬  ┌╬╣  ☼"
                    + "☼  ╠╬╬  ╞╣╡            ╠╬╣  ╠╨┘  ☼"
                    + "☼  ╬╨╬  │╬╬            ╠└╨  ╩╨╬  ☼"
                    + "☼            ╞╞╣  ╡╬╬            ☼"
                    + "☼  ˅         ╬╞˅  ╞╬╣            ☼"
                    + "☼    »┌╦┌╬╡  ╩╬╣  ╬╬╦  ╬╬╦˅╬     ☼"
                    + "☼☼☼   ╩╨╬┘╡            ╦╥╩╦╬   ☼☼☼"
                    + "☼˂                               ☼"
                    + "☼            ╞╬╬  ╠╬╣            ☼"
                    + "☼  ╦╬╣  ┌╬┐  ╬╩╡  ╬╞╬  ╞╬╦  ╦╥╦  ☼"
                    + "☼  ╬╬╥  ╞╬╣  ˅╣► ˅╠╦╬  ╬╬╬  ╠╬╬  ☼"
                    + "☼  ╦╬╬  ╬╬┘  ╞╬╬╬╥╠╬╡  ╬╬╬  ╬╬╡  ☼"
                    + "☼  ┌╬╣  ╬╬╬  ╬╞╣┘╬╬╬╬  ╬╬╬  ╬╬│  ☼"
                    + "☼  ╦╬╬  ╬╬╣  ╬╬╣  ╬╬╬  ╠╬╬  ╠╬╬  ☼"
                    + "☼  ╩╬╣  ╬╬╞  ╬╬┐ ˅│╬╡  ╬╬╣  ╠╬╬  ☼"
                    + "☼  ╬╬╬  ╬╬┘  ╣╬╣  ╬╡╬  ╞╬╨  ╞╡╬˅ ☼"
                    + "☼  ╬╬│  ╩╩╨  ╬╩╣  ╬╬╡¿ ╨╣┘  ╬╬╣  ☼"
                    + "☼  ╥╬╡                      ╞╞╡  ☼"
                    + "☼    ╬                      ╬╬│  ☼"
                    + "☼  ╬╣╬       ╬╬╬╦╦╦╥┐       ╠╬╣  ☼"
                    + "☼  └╬╣       ╬╬╩╦ ╬╬╣       ╞╩╨  ☼"
                    + "☼          ˄ ╡╬    ╬╬•           ☼"
                    + "☼        •   ╞╞˅   ╬╡     »      ☼"
                    + "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼";
    //our coordinates: (15:20)
    //enemy coordinates: (4:14)
    //LEFT, ACT;

    private String battlefieldString2 =
            "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
            "☼?      Ѡ        •«              ☼" +
            "☼                      ˃       « ☼" +
            "☼  ╥╦╥  ╦╬╦  ╥╥╥  ╥╦╬  ╦ ╬  ╬┌╥  ☼" +
            "☼  ╦╦╥  ╬╥╬  ╬╥╬¿ ╬╬╥  ╞╬╥  ╬╥╦  ☼" +
            "☼  ╦╬╡  ╬╬╬  ╞╣╣  ╬╥╦  ╠╡╣  ╬╥╣  ☼" +
            "☼  ╬╬╬  ╠╣╣  ╠╬╬¿ ╞╠╣  ╬╬╣  ╠╡╬  ☼" +
            "☼¿ ╬╬╩  ╠╬╬  ╠╬╬☼☼╬╬╣  ╬╬╬  ╞╬╡  ☼" +
            "☼  ╠╬╬  ╞╬┘  ╠╬╬☼☼╬╬╣  ╬╣╬  ╬╣╬ ˂☼" +
            "☼  ╠╬╬  ╞╩╬  ╬╬╣▲ ╞└╠  ╬╞╬˄ ╠╬╡  ☼" +
            "☼  ╞╣╬  ╬╬╡  ╞╬╦  ╨╬╡  ╬╬╬  ╡╬╡  ☼" +
            "☼  └╨╬  ╬╬╣   ˃        ╬╬╬  ╠╬╣  ☼" +
            "☼  ╬╬╩  ╬╣╬            ╞╠┘  ╨╬╬  ☼" +
            "☼            ╬╠╠  ╬╬╬ •          ☼" +
            "☼            ╬╞╡  │╬╣            ☼" +
            "☼     ¿╦┌╣┐  └╩╬  ╩╣╬  ┌╥╬╨╦     ☼" +
            "☼☼☼   ╬╩│╬╡            ╬╬╩ ╨   ☼☼☼" +
            "☼ ˃                      ˄       ☼" +
            "☼            ╞╠╠  ╬╬╣            ☼" +
            "☼  ╦╬╬  ╞╥╣  ╬╦╬  ╬╬╬  ╥╬╣  ╬╦┐  ☼" +
            "☼  ╬╥╦  ╞╬╬  ╠╣╣  ╞╣╬  ╬╦╦   ╥┐  ☼" +
            "☼  ╠╬┐  ╬╬╡  ╠╬╥╦╨╠╣╬ ˄╬╬╣  ╬╬╦  ☼" +
            "☼  ╠╬╣  ╬╬╬  ╞╬╥╬╬╬╣╦  │╬╡  ┌╬╣  ☼" +
            "☼• ╠╬╬  ╞╬╬  ╠╬╬  ╬╣╣  ╬╨╡  ╬╣╬  ☼" +
            "☼  ╠╬╬  ╬╬╬  ╩╬╣  ╬╩╬  ╬╬╬  ╞╣╣  ☼" +
            "☼  ╬╬╬  ╬╬╣• ╠╡╣  ╠╬╣  ╩╬╬  ╬╬╬  ☼" +
            "☼  ╬╣╬  ╩╨╨  ╩╩╬  ╩╩╩  ╬╬╨  ╞╣╣  ☼" +
            "☼  ╬╣╬           •          ╞╬╣  ☼" +
            "☼  ╬╣╣                      ╠╬╠  ☼" +
            "☼  ╠╬╣       ╬┌╩╬ ╩╬╡       ╠╨╬  ☼" +
            "☼  ╬╣╬       ╞╠╩╬ ╩╬╬       ╬╬╣  ☼" +
            "☼      ˃     ╬╞    ╠╡            ☼" +
            "☼            ╬╡   ˅╣╡            ☼" +
            "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼";

    @Test
    public void getOurTank() {
        Tank ourTank = BattlefieldHelper.getBattlefield(battlefieldString).getOurTank();

        Assert.assertTrue(ourTank.getPositionX().equals(15));
        Assert.assertTrue(ourTank.getPositionY().equals(20));
        Assert.assertTrue(ourTank.getTankOrientation().equals(TankOrientation.LEFT));
    }

    @Test
    public void getEnemyTanks() {
        List<Tank> enemyTanks = BattlefieldHelper.getBattlefield(battlefieldString).getEnemyTanks();

        Assert.assertTrue(enemyTanks.size() == 10);
        Assert.assertTrue(enemyTanks.get(0).getPositionX() == 28);
        Assert.assertTrue(enemyTanks.get(0).getPositionY() == 2);
    }

    @Test
    public void findNearestTankTest() {
        Tank tank = BattlefieldHelper.findNearestTank(BattlefieldHelper.getBattlefield(battlefieldString));

        Assert.assertTrue(tank.getPositionX() == 17);
        Assert.assertTrue(tank.getPositionY() == 24);
    }

    @Test
    public void determinateNextCellTest() {
        Assert.assertEquals(BattlefieldHelper.aprroveNextCellMoving(BattlefieldHelper.getBattlefield(battlefieldString), Constants.GO_RIGHT), Constants.GO_RIGHT);
    }

    @Test
    public void findNearestTankOnTheCrossTest() {
        Assert.assertNotNull(BattlefieldHelper.findNearestTankOnTheCross(BattlefieldHelper.getBattlefield(battlefieldString)));
    }

    @Test
    public void findNearestTankOnTheCrossTest2() {
        Assert.assertNull(BattlefieldHelper.findNearestTankOnTheCross(BattlefieldHelper.getBattlefield(battlefieldString2)));
    }

    @Test
    public void weLookAtEnemiesTest() {
        Assert.assertTrue(BattlefieldHelper.weLookAtEnemies(BattlefieldHelper.getBattlefield(battlefieldString), new Tank(20, 17, TankOrientation.DOWN)));
    }
}