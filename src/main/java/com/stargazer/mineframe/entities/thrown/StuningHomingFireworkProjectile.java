package com.stargazer.mineframe.entities.thrown;

import com.stargazer.mineframe.setups.Registrations;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class StuningHomingFireworkProjectile extends HomingFireworkProjectile{
    public StuningHomingFireworkProjectile(Level p_37030_, double p_37031_, double p_37032_, double p_37033_, ItemStack p_37034_, int targetID) {
        super(p_37030_, p_37031_, p_37032_, p_37033_, p_37034_, targetID);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        var hitEntity = pResult.getEntity();
        if (hitEntity instanceof FlyingMob flyingMob){
            flyingMob.addEffect(new MobEffectInstance(Registrations.HEAVY_WEIGHT.get(),200));
        }
    }
}
