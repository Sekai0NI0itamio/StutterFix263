package itamio.stutterfix.mixin.client;

import itamio.stutterfix.StutterFix;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Port of wisecase2's MinecraftClientMixin: capture the render thread
 * once the client starts running. Forge 1.20.1 official mappings: run() on
 * net.minecraft.client.Minecraft.
 */
@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Inject(method = "run()V", at = @At("HEAD"))
    private void stutterfix263$loadThread(CallbackInfo ci) {
        if (!StutterFix.isClientInitialized) {
            StutterFix.loadRenderThread(Thread.currentThread());
            StutterFix.configPriorityRenderThread();
            StutterFix.isClientInitialized = true;
        }
    }
}
