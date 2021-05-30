package fr.skah.capacitymod.capability;

/*
 *  * @Created on jeudi/mai/2021 - 17:41
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import net.minecraft.nbt.NBTTagCompound;

public interface IPlayerSkills {

    int getExperience();
    void setExperience(int amount);

    int getGlobalLevel();
    void setGlobalLevel(int amount);

    int getPoints();
    void setPoints(int amount);

    int getVitalityLevel();
    void setVitalityLevel(int level);

    int getSageLevel();
    void setSageLevel(int level);

    int getStrengthLevel();
    void setStrengthLevel(int level);

    int getIntelligenceLevel();
    void setIntelligenceLevel(int level);

    int getLuckLevel();
    void setLuckLevel(int level);

    int getAirLevel();
    void setAirLevel(int level);

    NBTTagCompound saveNBT();
    void loadNBT(NBTTagCompound compound);
}
