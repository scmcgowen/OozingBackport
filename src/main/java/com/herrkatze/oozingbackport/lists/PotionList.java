package com.herrkatze.oozingbackport.lists;

import com.herrkatze.oozingbackport.OozingBackport;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.herrkatze.oozingbackport.lists.MobEffectList.MOB_EFFECTS;
import static com.herrkatze.oozingbackport.lists.MobEffectList.OOZING;

public class PotionList {
    public static final DeferredRegister<Potion> POTION_LIST = DeferredRegister.create(ForgeRegistries.POTIONS, OozingBackport.MODID);

    public static final RegistryObject<Potion> OOZING_POTION = POTION_LIST.register("oozing",() -> new Potion(new MobEffectInstance[]{new MobEffectInstance(OOZING.get(), 3*60*20)}));
}
