package com.herrkatze.oozingbackport;

import com.herrkatze.oozingbackport.lists.MobEffectList;
import com.herrkatze.oozingbackport.lists.PotionList;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;

public class OozingEffect extends MobEffect {
    public OozingEffect(MobEffectCategory category, int color){
        super(category,color);
    }
    @Override
    public void applyEffectTick(LivingEntity entity,int amplifier){
        if (entity instanceof Slime) {
            OozingBackport.LOGGER.debug(entity.toString());
            entity.removeEffect(MobEffectList.OOZING.get());
        }

    }

}
