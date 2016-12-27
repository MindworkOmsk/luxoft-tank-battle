package battlefield;


import common.TankOrientation;

public class Tank {
    private Integer positionY;
    private Integer positionX;
    private TankOrientation tankOrientation;

    public Tank(Integer positionY, Integer positionX, TankOrientation tankOrientation) {
        this.positionY = positionY;
        this.positionX = positionX;
        this.tankOrientation = tankOrientation;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public TankOrientation getTankOrientation() {
        return tankOrientation;
    }

    public void setTankOrientation(TankOrientation tankOrientation) {
        this.tankOrientation = tankOrientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tank tank = (Tank) o;

        if (!positionY.equals(tank.positionY)) return false;
        if (!positionX.equals(tank.positionX)) return false;
        return tankOrientation == tank.tankOrientation;
    }

    @Override
    public int hashCode() {
        int result = positionY.hashCode();
        result = 31 * result + positionX.hashCode();
        result = 31 * result + tankOrientation.hashCode();
        return result;
    }
}
