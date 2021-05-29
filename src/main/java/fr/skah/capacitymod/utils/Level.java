package fr.skah.capacitymod.utils;

/*
 *  * @Created on jeudi/mai/2021 - 20:46
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

public class Level {

    public static int levelToExp(int level) {
        if (level <= 15) {
            return (level * 15 + 250) * level;
        }
        if (level <= 30) {
            return (level * 20 + 1000) * level;
        }
        return (level * 25 + 2500) * level;
    }


}
