package fr.skah.capacitymod.utils;

/*
 *  * @Created on samedi/mai/2021 - 21:02
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

public enum Skills {
    VITALITY, SAGE, LUCK, STRENGHT, INTELLIGENCE, AIR;

    public static Skills fromName(String name) {
        for (Skills skills : Skills.values()) {
            if (skills.name().equalsIgnoreCase(name)) {
                return skills;
            }
        }
        return null;
    }

}
