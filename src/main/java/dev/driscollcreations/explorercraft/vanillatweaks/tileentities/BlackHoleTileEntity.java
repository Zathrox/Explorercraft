package dev.driscollcreations.explorercraft.vanillatweaks.tileentities;

import dev.driscollcreations.explorercraft.setup.ExplorerTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlackHoleTileEntity extends TileEntity implements ITickableTileEntity {

    protected boolean inGravity;
    public static final int TICKS = 1000;
    private int timer;
    private Random random = new Random();

    public BlackHoleTileEntity() {
        super(ExplorerTileEntities.BLACK_HOLE.get());
        inGravity = false;
        timer = TICKS;
    }

    public void load(BlockState state, CompoundNBT compoundNBT) {
        super.load(state, compoundNBT);
        timer = compoundNBT.getInt("Timer");
    }

    public CompoundNBT save(CompoundNBT compoundNBT) {
        super.save(compoundNBT);
        compoundNBT.putInt("Timer", this.timer);
        return compoundNBT;
    }

    public void tick() {
        //Look at bell tile entity for better code, I think the pull code needs to be seperated into a pull entities code
        // with other checks and the tick functionality just needs to do the pull code. trial run with glowing effect later?
        World world = this.getLevel();
        BlockPos blockPos = this.getBlockPos();
        --timer;
        if (timer <= 0) {
            world.removeBlock(blockPos, false);
            world.removeBlockEntity(blockPos);
            MutableBoundingBox mutableboundingbox = new MutableBoundingBox(blockPos.getX()-8, blockPos.getY()-8, blockPos.getZ()-8, blockPos.getX()+8, blockPos.getY()+8, blockPos.getZ()+8);
            AxisAlignedBB bb = AxisAlignedBB.of(mutableboundingbox);
            List<Entity> entities = world.getEntitiesOfClass(Entity.class, bb);
            if(entities.size() > 0){
                for(Entity entity : entities){
                    this.setGravityPull(false);
                }
            }
        }

        renderParticles(world, blockPos);

        MutableBoundingBox mutableboundingbox2 = new MutableBoundingBox(blockPos.getX()-8, blockPos.getY()-8, blockPos.getZ()-8, blockPos.getX()+8, blockPos.getY()+8, blockPos.getZ()+8);
        AxisAlignedBB bb = AxisAlignedBB.of(mutableboundingbox2);
        java.util.List<Entity> entities = world.getEntitiesOfClass(Entity.class, bb);
        if(entities.size() > 0){
            for(Entity entity : entities){
                if (entity instanceof PlayerEntity)
                {
                    PlayerEntity player = (PlayerEntity) entity;
                    if(player.isCreative()) {
                        continue;
                    }
                }
                double distance = entity.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getZ());

                if(distance > 8D){
                    this.setGravityPull(false);
                } else {
                    this.setGravityPull(true);
                }

                if (isInGravityPull()) {
                    double d6 = entity.getX() - blockPos.getX() - 0.5D;
                    double d8 = entity.getY() - blockPos.getY() - 0.5D;
                    double d10 = entity.getZ() - blockPos.getZ() - 0.5D;
                    double d11 = MathHelper.sqrt(d6 / d6 + d8 / d8 + d10 / d10);
                    d6 /= d11;
                    d8 /= d11;
                    d10 /= d11;
                    double d13 = (1.0D - distance) / 0.5 * 15;
                    entity.setDeltaMovement(d6 / d13, d8 / d13, d10 / d13);
                    if(!entity.isOnGround()){
                        entity.setDeltaMovement(0, d8 / d13, 0);
                    }
                }
            }
        }
    }

    public void renderParticles(World world, BlockPos blockPos) {
        int dist = 2;
        for (int l = blockPos.getX() - dist; l <= blockPos.getX() + dist; l++)
        {
            for (int i1 = blockPos.getZ() - dist; i1 <= blockPos.getZ() + dist; i1++)
            {
                if (l > blockPos.getX() - 2 && l < blockPos.getX() + 2 && i1 == blockPos.getZ() - 1)
                {
                    i1 = blockPos.getZ() + 2;
                }
                if (random.nextInt(16) != 0)
                {
                    continue;
                }
                for (int j1 =  blockPos.getY(); j1 <= blockPos.getY() + 1; j1++)
                {
                    for(int i = 0; i < 10; i++){
                        world.addParticle(ParticleTypes.PORTAL, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, (double)((float)(l - blockPos.getX())), (double)((float)(j1 - blockPos.getY())), (double)((float)(i1 - blockPos.getZ()) * 0.5F));
//                        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new EntityBlackHoleFX(worldObj, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 2D, (double)blockPos.getZ() + 0.5D, (double)((float)(l - blockPos.getX())), (double)((float)(j1 - blockPos.getY())), (double)((float)(i1 - blockPos.getZ()) * 0.5F)));
//                        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new EntityPortalFX(worldObj, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, (double)((float)(l - blockPos.getX())), (double)((float)(j1 - blockPos.getY())), (double)((float)(i1 - blockPos.getZ()) * 0.5F)));
                    }
                }
            }
        }
    }

    public boolean isInGravityPull()
    {
        return inGravity;
    }

    public void setGravityPull(boolean inGravityPull)
    {
        inGravity = inGravityPull;
    }

    public Entity getClosestEntity(World world, double par1, double par3, double par5, double par7)
    {
        double d = -1D;
        Entity entity = null;

        BlockPos blockPos = this.getBlockPos();
        MutableBoundingBox mutableboundingbox = new MutableBoundingBox(blockPos.getX()-8, blockPos.getY()-8, blockPos.getZ()-8, blockPos.getX()+8, blockPos.getY()+8, blockPos.getZ()+8);
        AxisAlignedBB bb = AxisAlignedBB.of(mutableboundingbox);

        for (int i = 0; i < world.getEntitiesOfClass(Entity.class, bb).size(); i++)
        {
            Entity entity1 = (world.getEntitiesOfClass(Entity.class, bb).get(i));
            double d1 = entity1.distanceToSqr(par1, par3, par5);

            if ((par7 < 0.0D || d1 < par7 * par7) && (d == -1D || d1 < d))
            {
                d = d1;
                entity = entity1;
            }
        }

        return entity;
    }

}
