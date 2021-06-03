package fr.skah.capacitymod.capability;

/*
 *  * @Created on jeudi/mai/2021 - 17:51
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

public class DefaultPlayerSkills implements IPlayerSkills {

    protected int experience;
    protected int globalLevel;
    protected int points;

    protected int vitalityLevel;
    protected int sageLevel;
    protected int strenghtLevel;
    protected int intelligenceLevel;
    protected int luckLevel;
    protected int airLevel;


    public DefaultPlayerSkills() {
        this.experience = 1;
        this.globalLevel = 1;
        this.points = 0;
        this.vitalityLevel = 0;
        this.sageLevel = 0;
        this.strenghtLevel = 0;
        this.intelligenceLevel = 0;
        this.luckLevel = 0;
        this.airLevel = 0;
    }


    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public void setExperience(int amount) {
        this.experience = amount;
    }

    @Override
    public int getGlobalLevel() {
        return this.globalLevel;
    }

    @Override
    public void setGlobalLevel(int amount) {
        this.globalLevel = amount;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(int amount) {
        this.points = amount;
    }

    @Override
    public int getVitalityLevel() {
        return this.vitalityLevel;
    }

    @Override
    public void setVitalityLevel(int level) {
        this.vitalityLevel = level;
    }

    @Override
    public int getSageLevel() {
        return this.sageLevel;
    }

    @Override
    public void setSageLevel(int level) {
        this.sageLevel = level;
    }

    @Override
    public int getStrengthLevel() {
        return this.strenghtLevel;
    }

    @Override
    public void setStrengthLevel(int level) {
        this.strenghtLevel = level;
    }

    @Override
    public int getIntelligenceLevel() {
        return this.intelligenceLevel;
    }

    @Override
    public void setIntelligenceLevel(int level) {
        this.intelligenceLevel = level;
    }

    @Override
    public int getLuckLevel() {
        return this.luckLevel;
    }

    @Override
    public void setLuckLevel(int level) {
        this.luckLevel = level;
    }

    @Override
    public int getAirLevel() {
        return this.airLevel;
    }

    @Override
    public void setAirLevel(int level) {
        this.airLevel = level;
    }

    @Override
    public IPlayerSkills get() {
        return this;
    }

    @Override
    public void set(IPlayerSkills skills) {
        setExperience(skills.getExperience());
        setGlobalLevel(skills.getGlobalLevel());
        setPoints(skills.getPoints());

        setVitalityLevel(skills.getVitalityLevel());
        setSageLevel(skills.getSageLevel());
        setStrengthLevel(skills.getStrengthLevel());
        setIntelligenceLevel(skills.getIntelligenceLevel());
        setLuckLevel(skills.getLuckLevel());
        setAirLevel(skills.getAirLevel());
    }
}
