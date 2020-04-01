package com.oerOesreveR.cheese;

import com.oerOesreveR.cheese.util.handlers.SoundsHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class FriendEntity extends TameableEntity {

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

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsHandler.ENTITY_FRIEND_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundsHandler.ENTITY_FRIEND_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsHandler.ENTITY_FRIEND_DEATH;
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (!this.isTamed()) {
            if (!itemstack.isEmpty()) {
                if (item == cheeses.swiss) {
                    this.setTamedBy(player);
                    super.setTamed(true);
                    itemstack.shrink(1);
                    this.playTameEffect(true);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
