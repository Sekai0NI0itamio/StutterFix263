package itamio.stutterfix.mixin.client;

import itamio.stutterfix.StutterFix;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Port of wisecase2's MinecraftClientMixin: capture the render thread
 * once the client starts running. In 26.3 the method is run() on
 * net.minecraft.client.Minecraft (was MinecraftClient.run()V).
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
