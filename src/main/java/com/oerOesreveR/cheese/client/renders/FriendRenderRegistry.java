package com.oerOesreveR.cheese.client.renders;

import com.oerOesreveR.cheese.FriendEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class FriendRenderRegistry {
    public static void registryEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(FriendEntity.class, FriendEntityRender.RenderFactory.INSTANCE);
    }
}
