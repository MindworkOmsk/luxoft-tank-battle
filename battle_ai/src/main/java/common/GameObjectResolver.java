package common;


public class GameObjectResolver {

    public static boolean isGameBorder(String symbol) {
        return symbol.equals(Constants.WALL);
    }

    public static boolean isDeadTank(String symbol) {
        return symbol.equals(Constants.DEAD);
    }

    public static boolean isEmptyField(String symbol) {
        return symbol.equals(Constants.GROUND);
    }

    public static boolean isConstruction(String symbol) {
        return Constructions.isConstruction(symbol);
    }

    public static Boolean isOurTank(String symbol) {
        return symbol.equals(Constants.TANK_UP)
                || symbol.equals(Constants.TANK_DOWN)
                || symbol.equals(Constants.TANK_RIGHT)
                || symbol.equals(Constants.TANK_LEFT);
    }

    public static Boolean isEnemyTank(String symbol) {
        return symbol.equals(Constants.OTHER_TANK_UP)
                || symbol.equals(Constants.OTHER_TANK_RIGHT)
                || symbol.equals(Constants.OTHER_TANK_LEFT)
                || symbol.equals(Constants.OTHER_TANK_DOWN)
                || symbol.equals(Constants.BOT_TANK_DOWN)
                || symbol.equals(Constants.BOT_TANK_UP)
                || symbol.equals(Constants.BOT_TANK_LEFT)
                || symbol.equals(Constants.BOT_TANK_RIGHT);
    }
}
