package common;

import java.util.HashMap;
import java.util.Map;


public enum Constructions {
    //Разрушаемые стены. Цифры указывают на количество выстрелов, необходимых для разрушения стен полностью. 
    //Cтены со временем восстанавливаются
    CONSTRUCTION("╬", 3),
    CONSTRUCTION_DESTROYED_DOWN("╩", 2),
    CONSTRUCTION_DESTROYED_UP("╦", 2),
    CONSTRUCTION_DESTROYED_LEFT("╠", 2),
    CONSTRUCTION_DESTROYED_RIGHT("╣", 2),
    CONSTRUCTION_DESTROYED_DOWN_TWICE("╨", 1),
    CONSTRUCTION_DESTROYED_UP_TWICE("╥", 1),
    CONSTRUCTION_DESTROYED_LEFT_TWICE("╞", 1),
    CONSTRUCTION_DESTROYED_RIGHT_TWICE("╡", 1),
    CONSTRUCTION_DESTROYED_LEFT_RIGHT("│", 1),
    CONSTRUCTION_DESTROYED_UP_DOWN("─", 1),
    CONSTRUCTION_DESTROYED_UP_LEFT("┌", 1),
    CONSTRUCTION_DESTROYED_RIGHT_UP("┐", 1),
    CONSTRUCTION_DESTROYED_DOWN_LEFT("└", 1),
    CONSTRUCTION_DESTROYED_DOWN_RIGHT("┘", 1);

    private String constructionSymbol;
    private Integer constructionHealth;
    private static Map<String, Integer> valuesMap = new HashMap<String, Integer>();

    static {
        for (Constructions construction : Constructions.values()) {
            valuesMap.put(construction.getConstructionSymbol(), construction.getConstructionHealth());
        }
    }

    Constructions(String constructionSymbol, Integer constructionHealth) {
        this.constructionSymbol = constructionSymbol;
        this.constructionHealth = constructionHealth;
    }

    public static boolean isConstruction(String symbol) {
         return valuesMap.containsKey(symbol);
    }

    public String getConstructionSymbol() {
        return constructionSymbol;
    }

    public Integer getConstructionHealth() {
        return constructionHealth;
    }
}
