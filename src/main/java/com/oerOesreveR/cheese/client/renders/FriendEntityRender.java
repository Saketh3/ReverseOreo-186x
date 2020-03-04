package com.oerOesreveR.cheese.client.renders;

import com.oerOesreveR.cheese.FriendEntity;
import com.oerOesreveR.cheese.cheeses;
import com.oerOesreveR.cheese.client.models.FriendEntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FriendEntityRender extends LivingRenderer<FriendEntity, FriendEntityModel> {
    public FriendEntityRender(EntityRendererManager manager) {
        super(manager, new FriendEntityModel(0), 0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(FriendEntity entity) {
        return cheeses.location("textures/entity/tutorial_entity.png");
    }

    public static class RenderFactory implements IRenderFactory<FriendEntity> {
        public static final RenderFactory INSTANCE = new RenderFactory();
        @Override
        public EntityRenderer<? super FriendEntity> createRenderFor(EntityRendererManager manager) {
            return new FriendEntityRender(manager);
        }
    }
}
