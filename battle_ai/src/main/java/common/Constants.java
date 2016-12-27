package common;

public interface Constants {
    String GROUND = " "; //Неразрушаемая стена
    String WALL = "☼";
    String DEAD = "Ѡ"; //Уничтоженный танк игрока  = уже в следующую секунду появится новый)
    String BULLET = "•";
        
    //Танк игрока
    String TANK_UP = "▲";
    String TANK_RIGHT = "►";
    String TANK_DOWN = "▼";
    String TANK_LEFT = "◄";
        
    //Вражеский танк  = автобот или игрок-соперник)
    String OTHER_TANK_UP = "˄";
    String OTHER_TANK_RIGHT = "˃";
    String OTHER_TANK_DOWN = "˅";
    String OTHER_TANK_LEFT = "˂";

    String BOT_TANK_UP = "¿";
    String BOT_TANK_RIGHT = "»";
    String BOT_TANK_DOWN = "¿";
    String BOT_TANK_LEFT = "«";

    //commands
    String SHOOT = "ACT";
    String GO_RIGHT = "RIGHT";
    String GO_LEFT = "LEFT";
    String GO_UP = "UP";
    String GO_DOWN = "DOWN";
}
