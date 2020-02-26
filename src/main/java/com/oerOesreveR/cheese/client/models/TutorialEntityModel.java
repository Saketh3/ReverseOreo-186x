package com.oerOesreveR.cheese.client.models;

import com.oerOesreveR.cheese.TutorialEntity;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.entity.model.PillagerModel;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TutorialEntityModel extends VillagerModel<TutorialEntity> {

    public TutorialEntityModel(float scale) {
        super(scale);
    }
}
