package com.stargazer.mineframe.entities.thrown;

import com.stargazer.mineframe.MineFrame;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PersonalHomingFirework extends FireworkRocketEntity {
    private int fireTick;
    private int targetID;


    public PersonalHomingFirework(Level p_37030_, double p_37031_, double p_37032_, double p_37033_, ItemStack p_37034_, int targetID) {
        super(p_37030_, p_37031_, p_37032_, p_37033_, p_37034_);
        this.targetID=targetID;
        this.fireTick = 10;
    }

    @Override
    public void tick() {
        super.tick();
        if (fireTick==10) {
            Vec3 projectileVector = getPosition(1F);
            Vec3 newMovementVector;
            Vec3 targetVector;
            var trackedEntity = this.level.getEntity(targetID);
            if (trackedEntity != null) {
                targetVector = trackedEntity.getPosition(1F);
                newMovementVector = (targetVector.subtract(projectileVector)).normalize();
                Vec3 upperMovementVector = new Vec3(newMovementVector.x, newMovementVector.y+2, newMovementVector.z);
                upperMovementVector.normalize();
                this.setDeltaMovement(upperMovementVector);
                float newPitch = (float) Math.atan(upperMovementVector.x / (-upperMovementVector.y));
                float newYaw = (float) (Math.sqrt((upperMovementVector.x * upperMovementVector.x) + (upperMovementVector.y * upperMovementVector.y)) / upperMovementVector.z);
                this.setRot(newYaw, newPitch);
            }
        }
        MineFrame.LOGGER.info(fireTick);
        fireTick--;

        if (fireTick<0){
            Vec3 projectileVector = getPosition(1F);
            Vec3 newMovementVector;
            Vec3 targetVector;

            var trackedEntity = this.level.getEntity(targetID);
            if (trackedEntity != null) {
                targetVector = trackedEntity.getPosition(1F);
                newMovementVector = (targetVector.subtract(projectileVector)).normalize();
                this.setDeltaMovement(newMovementVector);
                float newPitch = (float) Math.atan(newMovementVector.x / (-newMovementVector.y));
                float newYaw = (float) (Math.sqrt((newMovementVector.x * newMovementVector.x) + (newMovementVector.y * newMovementVector.y)) / newMovementVector.z);
                this.setRot(newYaw, newPitch);
            } else {
                this.discard();
            }
        }

    }

    @Override
    public void setNoGravity(boolean pNoGravity) {
        super.setNoGravity(pNoGravity);
    }
}
