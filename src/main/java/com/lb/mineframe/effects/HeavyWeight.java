package com.lb.mineframe.effects;

import com.lb.mineframe.MineFrame;
import com.lb.mineframe.setups.Registrations;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class HeavyWeight extends MobEffect {
    public HeavyWeight(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void applyEffectTick(LivingEntity livingEntity, int level) {
        if (livingEntity instanceof FlyingMob flyingMob) {
            if (flyingMob.hasEffect(Registrations.HEAVY_WEIGHT.get())) {
                Vec3 forceDownVector = new Vec3(flyingMob.getDeltaMovement().x, -0.5D, flyingMob.getDeltaMovement().z);
                flyingMob.setDeltaMovement(forceDownVector);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) { //k is the interval duration
        return true;
    }
}
