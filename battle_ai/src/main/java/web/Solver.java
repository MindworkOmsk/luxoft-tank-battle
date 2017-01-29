package web;

import battlefield.BattlefieldHelper;
import battlefield.BattlefieldHolder;
import battlefield.Tank;
import common.Constants;

public class Solver {

    public String answer(String battlefieldString) {
        String actionString = "";
        BattlefieldHolder battlefield = BattlefieldHelper.getBattlefield(battlefieldString);

        //попробовать почаще идти вверх

        if (battlefield.getOurTank() != null) {
            //find nearest victim on the cross
            Tank tankOnTheCross = BattlefieldHelper.findNearestTankOnTheCross(battlefield);

            if (tankOnTheCross != null) {
                //если уже видим танк и повернуты к нему - не едем, только стреляем
                if (BattlefieldHelper.weLookAtEnemies(battlefield, tankOnTheCross)) {
                    actionString = "ACT";

                    //на нас летит снаряд? если да, отбегаем в сторону
                    String bulletAlarmAction = BattlefieldHelper.bulletAlarm(battlefield);
                    if (! bulletAlarmAction.equals("")) {
                        actionString = bulletAlarmAction;
                        System.out.println("You missed, retard!");
                    }
                } else {
                    //иначе едем
                    actionString = BattlefieldHelper.turnTank(battlefield, tankOnTheCross);
                    actionString = actionString + ", ACT";
                }

                System.out.println("targetA: " + tankOnTheCross.getPositionX() + ":" + tankOnTheCross.getPositionY());
            } else {
                //find nearest victim not on the cross, hunt mode
                Tank victim = BattlefieldHelper.findNearestTank(battlefield);
                actionString = BattlefieldHelper.turnTank(battlefield, victim);

                //determinate next cell
                //it works
                actionString = BattlefieldHelper.aprroveNextCellMoving(battlefield, actionString);

                if (actionString.equals("")) {
                    actionString = Constants.GO_UP;
                }

                System.out.println("targetB: " + victim.getPositionX() + ":" + victim.getPositionY());

                //на нас летит снаряд? если да, отбегаем в сторону
                String bulletAlarmAction = BattlefieldHelper.bulletAlarm(battlefield);
                if (! bulletAlarmAction.equals("")) {
                    actionString = bulletAlarmAction;
                    System.out.println("You missed, retard!");
                }

                //shoot?
                if (BattlefieldHelper.weLookAtEnemies(battlefield, victim)) {
                    actionString = actionString + ", ACT";
                }
            }
        } else {
            System.out.println("YOU DEAD");
        }

        //System.out.println(actionString);
        //in case of troubles
        //actionString = "DOWN, ACT";
        //actionString = "LEFT, ACT";
        //actionString = "RIGHT, ACT";
        //actionString = "UP, ACT";

        return actionString;
    }
}
