package com.oerOesreveR.cheese;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class BreakTreeGoal extends Goal {
    protected final FriendEntity tameable;
    protected final IWorld world;
    private final PathNavigator navigator;
    private final LivingEntity owner;
    private BlockPos treeBottom = new BlockPos(0,0,0);
    private boolean foundTree = false;
    private boolean wantsTree = false;
    private int range, yRange;
    private double treeX,treeY,treeZ;

    public BreakTreeGoal(FriendEntity friend, int detectionRange){
        tameable = friend;
        world = friend.world;
        navigator = friend.getNavigator();
        range = detectionRange;
        owner = friend.getOwner();
        yRange = range/3;
    }

    @Override
    public void tick() {
        this.wantsTree = tameable.isHungryForWood();
        Random r = new Random();
        if(foundTree && wantsTree && !this.navigator.noPath()){
            //goto
            this.navigator.tryMoveToXYZ(treeX, treeY, treeZ, 1);
            //break
            if(isThere(treeX, treeY, treeZ)){
                BlockState blok = this.world.getBlockState(treeBottom);
                Block b = blok.getBlock();
                tameable.pants.addItem(new ItemStack(b.asItem(), 1));
                world.removeBlock(treeBottom, false);
                foundTree = false;
            }
        }else{
            //be sad, move to random location to find more trees
            this.navigator.tryMoveToXYZ(this.tameable.posX + r.nextDouble(), this.tameable.posY + r.nextDouble(), this.tameable.posZ + r.nextDouble(), 1);
            findNearestTree();
        }
        super.tick();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return wantsTree && foundTree && !this.tameable.isSitting();
    }

    @Override
    public boolean shouldExecute() {
        return this.tameable.isHungryForWood();
    }

    private boolean findNearestTree(){
        double pX, pY, pZ;
        pX = this.tameable.posX;
        pY = this.tameable.posY;
        pZ = this.tameable.posZ;
        treeBottom = new BlockPos(pX,pY,pZ);
        for(int i = 0; i <= this.range; i++){
            for(int k = 0; k <= yRange; k++) {
                for(int p = -i; p <= i; p++) {
                    //front row of blocks
                    treeBottom = new BlockPos (pX + i, pY + k, pZ + p);
                    if(foundTree(treeBottom)){ return true;}

                    //back row of blocks
                    treeBottom = new BlockPos (pX - i, pY + k, pZ + p);
                    if(foundTree(treeBottom)){ return true;}
                    //left row of blocks
                    treeBottom = new BlockPos (pX + p, pY + k, pZ + i);
                    if(foundTree(treeBottom)){ return true;}
                    //right row of blocks
                    treeBottom = new BlockPos (pX + p, pY + k, pZ - i);
                    if(foundTree(treeBottom)){ return true;}

                    //do it all again but down (for trees underneath)
                    treeBottom = new BlockPos (pX + i, pY - k, pZ + p);
                    if(foundTree(treeBottom)){ return true;}
                    //back row of blocks
                    treeBottom = new BlockPos (pX - i, pY - k, pZ + p);
                    if(foundTree(treeBottom)){ return true;}
                    //left row of blocks
                    treeBottom = new BlockPos (pX + p, pY - k, pZ + i);
                    if(foundTree(treeBottom)){ return true;}
                    //right row of blocks
                    treeBottom = new BlockPos (pX + p, pY - k, pZ - i);
                    if(foundTree(treeBottom)){ return true;}
                }
            }
        }
        foundTree = false;
        return false;
    }

    private boolean foundTree (BlockPos b){
        BlockState blok = this.world.getBlockState(b);
        if(blok.getBlock().isIn(BlockTags.LOGS) && isTree(b)) {
            foundTree = true;
            this.treeX = b.getX();
            this.treeY = b.getY();
            this.treeZ = b.getZ();
            return true;
        }
        return false;
    }

    private boolean isThere (double X, double Y, double Z){
        double e = 3;
        if(this.tameable.posX > X - e && this.tameable.posX < X + e){
            if(this.tameable.posY > Y - yRange && this.tameable.posY < Y + yRange){
                if(this.tameable.posZ > Z - e && this.tameable.posZ < Z + e){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isTree (BlockPos blockPos){
        return true;
    }
}
