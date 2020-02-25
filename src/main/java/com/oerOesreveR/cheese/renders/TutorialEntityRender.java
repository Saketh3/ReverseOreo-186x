package com.oerOesreveR.cheese.renders;

import com.oerOesreveR.cheese.TutorialEntities;
import com.oerOesreveR.cheese.TutorialEntity;
import com.oerOesreveR.cheese.cheeses;
import com.oerOesreveR.cheese.models.TutorialEntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class TutorialEntityRender extends LivingRenderer<TutorialEntity, TutorialEntityModel> {
    public TutorialEntityRender(EntityRendererManager manager) {
        super(manager, new TutorialEntityModel(), 0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(TutorialEntity entity) {
        return cheeses.location("textures/entity/tutorial_entity.png");
    }

    public static class RenderFactory implements IRenderFactory<TutorialEntity>
    {

        @Override
        public EntityRenderer<? super TutorialEntity> createRenderFor(EntityRendererManager manager) {
            return new TutorialEntityRender(manager);
        }
    }
}
