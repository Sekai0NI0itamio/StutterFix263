package itamio.stutterfix.mixin.client;

import itamio.stutterfix.StutterFix;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Port of wisecase2's RemoveYieldMixin: optionally skip Thread.yield()
 * in the render loop for more stable FPS. In 26.3 the render loop
 * method name/signature changed; this redirects Thread.yield() calls
 * in Minecraft.render if that method still contains them.
 */
@Mixin(Minecraft.class)
public class RemoveYieldMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "java/lang/Thread.yield()V"))
    private void stutterfix263$removeYield() {
        if (!StutterFix.threadconfig.renderRemoveYield) {
            Thread.yield();
        }
    }
}
