package com.oerOesreveR.cheese;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.particles.IParticleData;

import javax.annotation.Nullable;

public class FriendEntity extends TameableEntity {
    private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.createKey(FriendEntity.class, DataSerializers.VARINT);


    public FriendEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super((EntityType<? extends TameableEntity>) cheeses.Friend, worldIn);
        super.setSitting(false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.1D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.5D, Ingredient.fromItems(cheeses.swiss), false));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ENDER_DRAGON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_DEATH;
    }

    public boolean processInteract(PlayerEntity player, Hand hand){
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if(!this.isTamed()){
            if(!itemstack.isEmpty()){
                if(item == cheeses.swiss){
                    this.setTamedBy(player);
                    super.setTamed(true);
                    itemstack.shrink(1);
                    return true;
                }
                return false;
            }
            return false;
        }else if (item instanceof DyeItem) {
            DyeColor dyecolor = ((DyeItem)item).getDyeColor();
            if (dyecolor != this.getCollarColor()) {
                this.setCollarColor(dyecolor);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return true;
            }
        }
        return false;
    }
    public DyeColor getCollarColor() {
        return DyeColor.byId(this.dataManager.get(COLLAR_COLOR));
    }

    protected void playTameEffect(boolean play) {
        IParticleData iparticledata = ParticleTypes.HEART;
        if (!play) {
            iparticledata = ParticleTypes.SMOKE;
        }
    }
    public void setCollarColor(DyeColor collarcolor) {
        this.dataManager.set(COLLAR_COLOR, collarcolor.getId());
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
