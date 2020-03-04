package com.oerOesreveR.cheese;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;


public class TutorialEntity extends CreatureEntity {



    public TutorialEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super((EntityType<? extends CreatureEntity>) TutorialEntities.TUTORIAL_ENTITY, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.1D));
        this.goalSelector.addGoal(2, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(cheeses.swiss), false));
        //this.goalSelector.addGoal(4, new FollowOwnerGoal(this,1.0, 10.0F, 2.0F));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.4d);
    }

}
