//package dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.ai;
//
//import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.EnderreeperEntity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.goal.Goal;
//
//import java.util.EnumSet;
//
//public class EnderreeperSwellGoal extends Goal {
//    private final EnderreeperEntity enderreeperEntity;
//    private LivingEntity livingEntity;
//
//    public EnderreeperSwellGoal(EnderreeperEntity entity) {
//        this.enderreeperEntity = entity;
//        this.setFlags(EnumSet.of(Flag.MOVE));
//    }
//
//    @Override
//    public boolean canUse() {
//        LivingEntity player = this.enderreeperEntity.getTarget();
//        return this.enderreeperEntity.getEnderreeperState() > 0 || player != null && this.enderreeperEntity.distanceToSqr(player) < 9.0D;
//    }
//
//    @Override
//    public void start() {
//        this.enderreeperEntity.getNavigation().recomputePath();
//        this.livingEntity = this.enderreeperEntity.getTarget();
//    }
//
//    @Override
//    public void stop() {
//        this.livingEntity = null;
//    }
//
//    @Override
//    public void tick() {
//        if (this.livingEntity == null) {
//            this.enderreeperEntity.setEnderreeperState(-1);
//        } else if (this.enderreeperEntity.distanceToSqr(this.livingEntity) > 49.0D) {
//            this.enderreeperEntity.setEnderreeperState(-1);
//        } else if (!this.enderreeperEntity.getSensing().canSee(this.livingEntity)) {
//            this.enderreeperEntity.setEnderreeperState(-1);
//        } else {
//            this.enderreeperEntity.setEnderreeperState(1);
//        }
//    }
//}
