package omarch.selectiveguiscaling.fabric;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class ScaleSlider extends AbstractSliderButton {
    private final String label;
    private final Consumer<Integer> onApply;

    public ScaleSlider(int x, int y, int width, int height, String label, int initialValue, Consumer<Integer> onApply) {
        super(x, y, width, height, Component.empty(), initialValue / 4.0);
        this.label = label;
        this.onApply = onApply;
        this.updateMessage();
    }

    @Override
    protected void updateMessage() {
        int scale = getScale();
        if (scale == 0) {
            this.setMessage(Component.literal(this.label + ": Match Global"));
        } else {
            this.setMessage(Component.literal(this.label + ": " + scale));
        }
    }

    @Override
    protected void applyValue() {
        // Snap the visual slider to exactly the stepped value
        this.value = getScale() / 4.0;
        this.updateMessage();
        this.onApply.accept(getScale());
    }

    private int getScale() {
        return (int) Math.round(this.value * 4.0);
    }
}
