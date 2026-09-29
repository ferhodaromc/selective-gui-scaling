package omarch.selectiveguiscaling.fabric.mixin;

import omarch.selectiveguiscaling.fabric.SGSConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.DeltaTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class HudMixin {

    private void pushScale(GuiGraphicsExtractor extractor, int configScale, float anchorX, float anchorY) {
        if (configScale == 0) return;
        double globalScale = Minecraft.getInstance().getWindow().getGuiScale();
        float s = (float) (configScale / globalScale);

        extractor.pose().pushMatrix();
        extractor.pose().translate(anchorX, anchorY);
        extractor.pose().scale(s, s);
        extractor.pose().translate(-anchorX, -anchorY);
    }

    private void popScale(GuiGraphicsExtractor extractor, int configScale) {
        if (configScale == 0) return;
        extractor.pose().popMatrix();
    }

    private float getW() { return Minecraft.getInstance().getWindow().getGuiScaledWidth(); }
    private float getH() { return Minecraft.getInstance().getWindow().getGuiScaledHeight(); }

    @Inject(method = "extractItemHotbar", at = @At("HEAD"))
    private void beforeHotbar(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.hotbarScale, getW() / 2.0f, getH());
    }

    @Inject(method = "extractItemHotbar", at = @At("RETURN"))
    private void afterHotbar(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.hotbarScale);
    }

    @Inject(method = "extractCrosshair", at = @At("HEAD"))
    private void beforeCrosshair(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.crosshairScale, getW() / 2.0f, getH() / 2.0f);
    }

    @Inject(method = "extractCrosshair", at = @At("RETURN"))
    private void afterCrosshair(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.crosshairScale);
    }

    @Inject(method = "extractChat", at = @At("HEAD"))
    private void beforeChat(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.chatScale, 0.0f, getH());
    }

    @Inject(method = "extractChat", at = @At("RETURN"))
    private void afterChat(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.chatScale);
    }

    @Inject(method = "extractBossOverlay", at = @At("HEAD"))
    private void beforeBossOverlay(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.bossOverlayScale, getW() / 2.0f, 0.0f);
    }

    @Inject(method = "extractBossOverlay", at = @At("RETURN"))
    private void afterBossOverlay(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.bossOverlayScale);
    }

    @Inject(method = "extractScoreboardSidebar", at = @At("HEAD"))
    private void beforeScoreboard(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.scoreboardScale, getW(), getH() / 2.0f);
    }

    @Inject(method = "extractScoreboardSidebar", at = @At("RETURN"))
    private void afterScoreboard(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.scoreboardScale);
    }

    @Inject(method = "extractTabList", at = @At("HEAD"))
    private void beforeTabList(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.tabListScale, getW() / 2.0f, 0.0f);
    }

    @Inject(method = "extractTabList", at = @At("RETURN"))
    private void afterTabList(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.tabListScale);
    }

    @Inject(method = "extractEffects", at = @At("HEAD"))
    private void beforeEffects(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.effectsScale, getW(), 0.0f);
    }

    @Inject(method = "extractEffects", at = @At("RETURN"))
    private void afterEffects(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.effectsScale);
    }

    @Inject(method = "extractOverlayMessage", at = @At("HEAD"))
    private void beforeOverlayMessage(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.actionBarScale, getW() / 2.0f, getH());
    }

    @Inject(method = "extractOverlayMessage", at = @At("RETURN"))
    private void afterOverlayMessage(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.actionBarScale);
    }

    @Inject(method = "extractTitle", at = @At("HEAD"))
    private void beforeTitle(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        pushScale(extractor, SGSConfig.titleScale, getW() / 2.0f, getH() / 2.0f);
    }

    @Inject(method = "extractTitle", at = @At("RETURN"))
    private void afterTitle(GuiGraphicsExtractor extractor, DeltaTracker tracker, CallbackInfo ci) {
        popScale(extractor, SGSConfig.titleScale);
    }
}
