package omarch.selectiveguiscaling.fabric.mixin;

import omarch.selectiveguiscaling.fabric.SGScalesScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    protected OptionsScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void addAdvancedGuiScales(CallbackInfo ci) {
        // Add a nice button at the top right corner
        // "Advanced GUI Scales"
        this.addRenderableWidget(Button.builder(Component.literal("Selective GUI Scales"), button -> {
            this.minecraft.gui.setScreen(new SGScalesScreen(this));
        }).bounds(this.width - 155, 5, 150, 20).build());
    }
}
