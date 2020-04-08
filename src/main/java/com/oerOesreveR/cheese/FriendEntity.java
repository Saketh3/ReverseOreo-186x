package com.oerOesreveR.cheese;

import com.oerOesreveR.cheese.util.handlers.SoundsHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nullable;

public class FriendEntity extends TameableEntity {
    private boolean hungryForWood = false;
    public Inventory pants;
    public FriendContainer pockets;

    public FriendEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super((EntityType<? extends TameableEntity>) cheeses.Friend, worldIn);
        pants = new Inventory(36);
        super.setSitting(false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreakTreeGoal(this, 20));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 0.1D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 0.5D, Ingredient.fromItems(cheeses.swiss), false));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
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
                    pockets = new FriendContainer(player.inventory, this.pants, pockets.windowId, this);
                    super.setTamed(true);
                    itemstack.shrink(1);
                    this.playTameEffect(true);
                    pants.isUsableByPlayer(player);
                    return true;
                }
                return false;
            }
            return false;
        } else {
            if (!itemstack.isEmpty()) {
                if (item == cheeses.swill) {
                    this.hungryForWood = !this.hungryForWood;
                    itemstack.shrink(1);
                    return true;
                }
                return false;
            }else if(itemstack.isEmpty()){
                this.checkPockets(player, this);
                return true;
            }
            return false;
        }
    }

    public boolean isHungryForWood () {
        return !hungryForWood;
    }

    public void checkPockets(PlayerEntity player, FriendEntity friend){
        player.openContainer(new SimpleNamedContainerProvider((p_213701_1_, p_213701_2_, p_213701_3_) -> {
            return new FriendContainer(p_213701_2_, this.getInventory(),p_213701_1_,this);
        }, this.getDisplayName()));;
    }
    public Inventory getInventory (){
        return this.pants;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
class FriendContainer extends Container{
    private PlayerInventory pInv;
    private Inventory fInv;
    private TameableEntity friend;

    protected FriendContainer(PlayerInventory playerInventory, Inventory friendInventory, int id, TameableEntity friend) {
        super(ContainerType.GENERIC_9X4, id);
        this.pInv = playerInventory;
        this.fInv = friendInventory;
        this.friend = friend;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
       return friend.isOwner(playerIn);
    }

}
