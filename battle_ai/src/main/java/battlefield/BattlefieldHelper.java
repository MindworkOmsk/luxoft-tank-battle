package battlefield;


import common.Constants;
import common.Constructions;
import common.GameObjectResolver;
import common.TankOrientation;

import java.util.HashMap;
import java.util.Map;

public class BattlefieldHelper {

    public static final Integer DIMENSION = 34;


    public static BattlefieldHolder getBattlefield(String battlefieldString) {

        BattlefieldHolder battlefieldHolder = new BattlefieldHolder();

        for (int i = 0; i < battlefieldString.length(); i = i + DIMENSION) {
            String line = battlefieldString.substring(i, i + DIMENSION);

            for (int j = 0; j < line.length() + 1; j++) {
                String symbol = line.substring(j, (j + 1) > line.length() ? j : j + 1);

                battlefieldHolder.getBattlefieldMatrix()[j][i / DIMENSION] = symbol;

                //isOurTank?
                if (GameObjectResolver.isOurTank(symbol)) {
                    Tank ourTank = new Tank(i / DIMENSION, j, TankOrientation.getTankOrientationForTankSymbol(symbol));
                    battlefieldHolder.setOurTank(ourTank);
                }

                //isEnemyTank?
                if (GameObjectResolver.isEnemyTank(symbol)) {
                    Tank enemyTank = new Tank(i / DIMENSION, j, TankOrientation.getTankOrientationForTankSymbol(symbol));
                    battlefieldHolder.getEnemyTanks().add(enemyTank);
                }

                //todo need something else?
            }
        }

        return battlefieldHolder;
    }

    public static Tank findNearestTank(BattlefieldHolder battlefield) {
        Map<Tank, Integer[]> distanceMap = new HashMap<Tank, Integer[]>();
        Tank ourTank = battlefield.getOurTank();

        //get distance to all tanks
        for (Tank enemyTank : battlefield.getEnemyTanks()) {
            distanceMap.put(enemyTank,
                    new Integer[]{enemyTank.getPositionX(), enemyTank.getPositionY()});
        }

        Tank nearestTank = battlefield.getEnemyTanks().get(0);

        for (Map.Entry entry : distanceMap.entrySet()) {
            Integer[] distancePair = (Integer[]) entry.getValue();
            Tank tank = (Tank) entry.getKey();

            if (Math.abs(ourTank.getPositionX() - distancePair[0]) + Math.abs(ourTank.getPositionY() - distancePair[1]) <=
                    Math.abs(ourTank.getPositionX() - nearestTank.getPositionX()) + Math.abs(ourTank.getPositionY() - nearestTank.getPositionY())) {
                nearestTank = tank;
            }
        }

        return nearestTank;
    }

    public static String turnTank(BattlefieldHolder battlefield, Tank victim) {
        Tank ourTank = battlefield.getOurTank();

        System.out.println("our coordinates: (" + ourTank.getPositionX() + ":" + ourTank.getPositionY() + ")");
        System.out.println("enemy coordinates: (" + victim.getPositionX() + ":" + victim.getPositionY() + ")");
        String action = "";

        //X DIMENSION decision
        switch (victim.getPositionX().compareTo(ourTank.getPositionX())) {
            case -1:
                action = Constants.GO_LEFT;
                break;
            case 1:
                action = Constants.GO_RIGHT;
        }

        if (action.equals("")) {
            //Y DIMENSION
            switch (victim.getPositionY().compareTo(ourTank.getPositionY())) {
                case -1:
                    action = Constants.GO_UP;
                    break;
                case 1:
                    action = Constants.GO_DOWN;
            }
        }

        return action;
    }

    public static String aprroveNextCellMoving(BattlefieldHolder battlefield, String actionString) {
        String validatedActionString;
        Tank ourTank = battlefield.getOurTank();
        Integer[] nextCellCoordinates = new Integer[]{0, 0};

        if (actionString.equals(Constants.GO_DOWN)) {
            nextCellCoordinates = new Integer[]{ourTank.getPositionX(), ourTank.getPositionY() + 1};
        }

        if (actionString.equals(Constants.GO_UP)) {
            nextCellCoordinates = new Integer[]{ourTank.getPositionX(), ourTank.getPositionY() - 1};
        }

        if (actionString.equals(Constants.GO_RIGHT)) {
            nextCellCoordinates = new Integer[]{ourTank.getPositionX() + 1, ourTank.getPositionY()};
        }

        if (actionString.equals(Constants.GO_LEFT)) {
            nextCellCoordinates = new Integer[]{ourTank.getPositionX() - 1, ourTank.getPositionY()};
        }

        String symbol = battlefield.getBattlefieldMatrix()[nextCellCoordinates[0]][nextCellCoordinates[1]];

        if (!Constructions.isConstruction(symbol) || symbol.equals(Constants.WALL)) {
            validatedActionString = actionString;
        } else {
            validatedActionString = "";
        }

        return validatedActionString;
    }

    public static Tank findNearestTankOnTheCross(BattlefieldHolder battlefield) {
        Map<Tank, Integer[]> distanceMap = new HashMap<Tank, Integer[]>();
        Tank ourTank = battlefield.getOurTank();

        //get distance to all tanks
        for (Tank enemyTank : battlefield.getEnemyTanks()) {
            if (ourTank.getPositionX() - enemyTank.getPositionX() == 0) {
                //check top
                //walls between our tank and enemy are exists?
                for (int i = ourTank.getPositionY() - 1; i > enemyTank.getPositionY(); i--) {
                    if (Constructions.isConstruction(battlefield.getBattlefieldMatrix()[ourTank.getPositionX()][i])
                            || Constants.WALL.equals(battlefield.getBattlefieldMatrix()[ourTank.getPositionX()][i])) {
                        break;
                    } else {
                        distanceMap.put(enemyTank, new Integer[]{enemyTank.getPositionX(), enemyTank.getPositionY()});
                    }
                }

                //check bottom
                for (int i = ourTank.getPositionY() + 1; i < BattlefieldHelper.DIMENSION; i++) {
                    if (Constructions.isConstruction(battlefield.getBattlefieldMatrix()[ourTank.getPositionX()][i])
                            || Constants.WALL.equals(battlefield.getBattlefieldMatrix()[ourTank.getPositionX()][i])) {
                        break;
                    } else {
                        distanceMap.put(enemyTank, new Integer[]{enemyTank.getPositionX(), enemyTank.getPositionY()});
                    }
                }
            }

            if (ourTank.getPositionY() - enemyTank.getPositionY() == 0) {
                //check right
                for (int i = ourTank.getPositionX() + 1; i < BattlefieldHelper.DIMENSION; i++) {
                    if (Constructions.isConstruction(battlefield.getBattlefieldMatrix()[i][ourTank.getPositionY()])
                            || Constants.WALL.equals(battlefield.getBattlefieldMatrix()[i][ourTank.getPositionY()])) {
                        break;
                    } else {
                        distanceMap.put(enemyTank, new Integer[]{enemyTank.getPositionX(), enemyTank.getPositionY()});
                    }
                }

                //check left
                for (int i = ourTank.getPositionX() - 1; i >= 0; i--) {
                    if (Constructions.isConstruction(battlefield.getBattlefieldMatrix()[i][ourTank.getPositionY()])
                            || Constants.WALL.equals(battlefield.getBattlefieldMatrix()[i][ourTank.getPositionY()])) {
                        break;
                    } else {
                        distanceMap.put(enemyTank, new Integer[]{enemyTank.getPositionX(), enemyTank.getPositionY()});
                    }
                }
            }
        }

        Tank nearestTank = null;

        if (!distanceMap.isEmpty()) {
            nearestTank = battlefield.getEnemyTanks().get(0);

            for (Map.Entry entry : distanceMap.entrySet()) {
                Integer[] distancePair = (Integer[]) entry.getValue();
                Tank tank = (Tank) entry.getKey();

                if (Math.abs(ourTank.getPositionX() - distancePair[0]) + Math.abs(ourTank.getPositionY() - distancePair[1]) <=
                        Math.abs(ourTank.getPositionX() - nearestTank.getPositionX()) + Math.abs(ourTank.getPositionY() - nearestTank.getPositionY())) {
                    nearestTank = tank;
                }
            }
        }

        return nearestTank;
    }
}
