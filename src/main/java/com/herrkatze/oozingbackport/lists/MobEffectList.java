package com.herrkatze.oozingbackport.lists;

import com.herrkatze.oozingbackport.OozingBackport;
import com.herrkatze.oozingbackport.OozingEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffectList {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OozingBackport.MODID);
    public static final RegistryObject<MobEffect> OOZING = MOB_EFFECTS.register("oozing", () -> new OozingEffect(MobEffectCategory.NEUTRAL,0x00ff8b));
}
