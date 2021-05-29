package fr.skah.capacitymod.capability;

/*
 *  * @Created on jeudi/mai/2021 - 18:09
 *  * @Project Xemnys
 *  * @Author Jimmy
 */

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;


public class PlayerSkillsProvider implements ICapabilitySerializable<NBTBase> {

    protected IPlayerSkills playerSkills;

    public PlayerSkillsProvider() {
        this.playerSkills = new DefaultPlayerSkills();
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
    public NBTBase serializeNBT() {
        return PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY.writeNBT(this.playerSkills, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        PlayerSkillsStorage.PLAYER_SKILLS_CAPABILITY.readNBT(this.playerSkills, null, nbt);
    }
}
