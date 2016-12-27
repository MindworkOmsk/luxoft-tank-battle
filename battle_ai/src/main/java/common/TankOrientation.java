package common;


public enum TankOrientation {

    UP("UP"),
    DOWN("DOWN"),
    RIGHT("RIGHT"),
    LEFT("LEFT");

    String orientation;

    TankOrientation(String orientation) {
        this.orientation = orientation;
    }

    public static TankOrientation getTankOrientationForTankSymbol(String tankSymbol) {
        TankOrientation tankOrientation = TankOrientation.UP;

        if (tankSymbol.equals(Constants.TANK_DOWN) || tankSymbol.equals(Constants.OTHER_TANK_DOWN)) {
            tankOrientation = TankOrientation.DOWN;
        }

        if (tankSymbol.equals(Constants.TANK_LEFT) || tankSymbol.equals(Constants.OTHER_TANK_LEFT)) {
            tankOrientation = TankOrientation.LEFT;
        }

        if (tankSymbol.equals(Constants.TANK_RIGHT) || tankSymbol.equals(Constants.OTHER_TANK_RIGHT)) {
            tankOrientation = TankOrientation.RIGHT;
        }

        return tankOrientation;
    }

    public String getOrientation() {
        return orientation;
    }
}
