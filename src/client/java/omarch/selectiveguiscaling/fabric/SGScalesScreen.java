package omarch.selectiveguiscaling.fabric;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class SGScalesScreen extends Screen {
    private final Screen parent;

    public SGScalesScreen(Screen parent) {
        super(Component.literal("Selective GUI Scales"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = this.height / 2 - 80;
        int spacing = 24;
        
        int leftX = centerX - 155;
        int rightX = centerX + 5;
        int w = 150;

        // Left Column
        this.addRenderableWidget(new ScaleSlider(leftX, startY, w, 20, "Hotbar", SGSConfig.hotbarScale, val -> SGSConfig.hotbarScale = val));
        this.addRenderableWidget(new ScaleSlider(leftX, startY + spacing, w, 20, "Crosshair", SGSConfig.crosshairScale, val -> SGSConfig.crosshairScale = val));
        this.addRenderableWidget(new ScaleSlider(leftX, startY + spacing * 2, w, 20, "Chat", SGSConfig.chatScale, val -> SGSConfig.chatScale = val));
        this.addRenderableWidget(new ScaleSlider(leftX, startY + spacing * 3, w, 20, "Boss Bar", SGSConfig.bossOverlayScale, val -> SGSConfig.bossOverlayScale = val));
        this.addRenderableWidget(new ScaleSlider(leftX, startY + spacing * 4, w, 20, "Status Effects", SGSConfig.effectsScale, val -> SGSConfig.effectsScale = val));

        // Right Column
        this.addRenderableWidget(new ScaleSlider(rightX, startY, w, 20, "Scoreboard", SGSConfig.scoreboardScale, val -> SGSConfig.scoreboardScale = val));
        this.addRenderableWidget(new ScaleSlider(rightX, startY + spacing, w, 20, "Player List (Tab)", SGSConfig.tabListScale, val -> SGSConfig.tabListScale = val));
        this.addRenderableWidget(new ScaleSlider(rightX, startY + spacing * 2, w, 20, "Action Bar", SGSConfig.actionBarScale, val -> SGSConfig.actionBarScale = val));
        this.addRenderableWidget(new ScaleSlider(rightX, startY + spacing * 3, w, 20, "Title", SGSConfig.titleScale, val -> SGSConfig.titleScale = val));

        this.addRenderableWidget(Button.builder(Component.literal("Done"), b -> {
            SGSConfig.save();
            this.minecraft.gui.setScreen(this.parent);
        }).bounds(centerX - 100, startY + spacing * 6, 200, 20).build());
    }

    @Override
    public void onClose() {
        SGSConfig.save();
        this.minecraft.gui.setScreen(this.parent);
    }
}
