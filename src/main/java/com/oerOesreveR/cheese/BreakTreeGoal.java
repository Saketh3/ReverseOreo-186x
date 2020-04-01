package com.oerOesreveR.cheese;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
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
    private int range;
    private double treeX,treeY,treeZ;

    public BreakTreeGoal(FriendEntity friend, int detectionRange){
        tameable = friend;
        world = friend.world;
        navigator = friend.getNavigator();
        range = detectionRange;
        owner = friend.getOwner();
    }

    @Override
    public void tick() {
        this.wantsTree = tameable.isHungryForWood();
        boolean tree = findNearestTree();
        Random r = new Random();
        if(tree){
            //goto
            this.navigator.tryMoveToXYZ(treeX, treeY, treeZ, 1);
            //break
            if(isThere(treeX, treeY, treeZ)){
                world.removeBlock(treeBottom, false);
            }
        }else{
            //be sad, move to random location to find more trees
            this.navigator.tryMoveToXYZ(this.tameable.posX + r.nextDouble(), this.tameable.posY + r.nextDouble(), this.tameable.posZ + r.nextDouble(), 1);
        }
        this.wantsTree = tameable.isHungryForWood();
        super.tick();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.navigator.noPath() && foundTree && !this.tameable.isSitting();
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
        for(int i = -this.range; i <= this.range; i++){
            for(int j = -this.range; j <= this.range; j++){
                for(int k = - this.range; k <= this.range; k++){
                    treeBottom = new BlockPos (pX + i, pY + j, pZ + k);
                    BlockState blok = this.world.getBlockState(treeBottom);
                    if(blok.getBlock().isIn(BlockTags.LOGS) && isTree(treeBottom)){
                        foundTree = true;
                        this.treeX = pX;
                        this.treeY = pY;
                        this.treeZ = pZ;
                        return true;
                    }
                }
            }
        }
        foundTree = false;
        return false;
    }

    private boolean isThere (double X, double Y, double Z){
        double e = 0.5;
        if(this.tameable.posX > X - e || this.tameable.posX < X + e){
            if(this.tameable.posY > Y - e || this.tameable.posY < Y + e){
                if(this.tameable.posZ > Z - e || this.tameable.posZ < Z + e){
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
