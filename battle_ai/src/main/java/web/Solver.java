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
                actionString = BattlefieldHelper.turnTank(battlefield, tankOnTheCross);

                actionString = actionString + ", ACT";
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

                //shoot?
            }
        } else {
            System.out.println("YOU DEAD");
        }

        System.out.println(actionString);

        return actionString;
    }
}
