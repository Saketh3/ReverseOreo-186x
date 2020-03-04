package com.oerOesreveR.cheese.client.models;

import com.oerOesreveR.cheese.FriendEntity;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendEntityModel extends VillagerModel<FriendEntity> {
    public FriendEntityModel(float scale) {
        super(scale);
    }
}
