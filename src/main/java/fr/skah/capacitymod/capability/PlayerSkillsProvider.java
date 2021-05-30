package fr.skah.capacitymod.capability;

/*
 *  * @Created on jeudi/mai/2021 - 18:09
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;


public class PlayerSkillsProvider implements ICapabilitySerializable<NBTTagCompound> {

    protected IPlayerSkills playerSkills;

    public PlayerSkillsProvider() {
        this.playerSkills = new DefaultPlayerSkills();
    }

    public PlayerSkillsProvider(IPlayerSkills capability)
    {
        this.playerSkills = capability;
    }

    @Override
    public boolean hasCapability(Capability capability, EnumFacing facing) {
        return capability == PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return this.hasCapability(capability, facing) ? PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY.cast(this.playerSkills) : null;

    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return this.playerSkills.saveNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        this.playerSkills.loadNBT(nbt);
    }

}
