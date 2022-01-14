package com.stargazer.mineframe.entities.thrown;

import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HomingFireworkProjectile extends FireworkRocketEntity {
    public int targetID;

    public HomingFireworkProjectile(Level p_37030_, double p_37031_, double p_37032_, double p_37033_, ItemStack p_37034_, int targetID) {
        super(p_37030_, p_37031_, p_37032_, p_37033_, p_37034_);
        this.targetID = targetID;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 projectileVector = getPosition(1F);
        Vec3 newMovementVector;
        Vec3 targetVector;

        var trackedEntity = this.level.getEntity(targetID);
        if (trackedEntity != null) {
            targetVector = trackedEntity.getPosition(1F);
            newMovementVector=(targetVector.subtract(projectileVector)).normalize();
            this.setDeltaMovement(newMovementVector);
            float newPitch= (float) Math.atan(newMovementVector.x/(-newMovementVector.y));
            float newYaw = (float) (Math.sqrt((newMovementVector.x*newMovementVector.x)+(newMovementVector.y*newMovementVector.y))/newMovementVector.z);
            this.setRot(newYaw,newPitch);
        }
        else {
            this.discard();
        }

    }
    @Override
    public void setNoGravity(boolean pNoGravity) {
        super.setNoGravity(pNoGravity);
    }
}
