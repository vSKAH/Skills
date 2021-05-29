package fr.skah.capacitymod.capability;

/*
 *  * @Created on jeudi/mai/2021 - 17:56
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerSkillsStorage implements Capability.IStorage<IPlayerSkills> {

    @CapabilityInject(IPlayerSkills.class)
    public static final Capability<IPlayerSkills> PLAYER_SKILLS_CAPABILITY = null;

    @Override
    public NBTBase writeNBT(Capability<IPlayerSkills> capability, IPlayerSkills instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("experience", instance.getExperience());
        nbt.setInteger("globalLevel", instance.getGlobalLevel());
        nbt.setInteger("points", instance.getPoints());

        nbt.setInteger("vitalityLevel", instance.getVitalityLevel());
        nbt.setInteger("sageLevel", instance.getSageLevel());
        nbt.setInteger("strenghtLevel", instance.getStrengthLevel());
        nbt.setInteger("intelligenceLevel", instance.getIntelligenceLevel());
        nbt.setInteger("luckLevel", instance.getLuckLevel());
        nbt.setInteger("airLevel", instance.getAirLevel());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerSkills> capability, IPlayerSkills instance, EnumFacing side, NBTBase base) {
        if (base instanceof NBTTagCompound) {
            NBTTagCompound nbt = (NBTTagCompound) base;

            instance.setExperience(nbt.getInteger("experience"));
            instance.setGlobalLevel(nbt.getInteger("globalLevel"));
            instance.setPoints(nbt.getInteger("points"));

            instance.setVitalityLevel(nbt.getInteger("vitalityLevel"));
            instance.setSageLevel(nbt.getInteger("sageLevel"));
            instance.setStrengthLevel(nbt.getInteger("strenghtLevel"));
            instance.setIntelligenceLevel(nbt.getInteger("intelligenceLevel"));
            instance.setLuckLevel(nbt.getInteger("luckLevel"));
            instance.setExperience(nbt.getInteger("airLevel"));

        }
    }

}
