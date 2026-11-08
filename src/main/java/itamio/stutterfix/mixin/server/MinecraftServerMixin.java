package itamio.stutterfix.mixin.server;

import itamio.stutterfix.StutterFix;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Port of wisecase2's MinecraftServerMixin: capture the server thread
 * when the server starts running.
 */
@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "runServer()V", at = @At("HEAD"))
    private void stutterfix263$loadThread(CallbackInfo ci) {
        StutterFix.loadServerThread(Thread.currentThread());
        StutterFix.configPriorityServerThread();
    }
}
