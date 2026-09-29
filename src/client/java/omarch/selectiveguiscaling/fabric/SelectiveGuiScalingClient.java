package omarch.selectiveguiscaling.fabric;

import net.fabricmc.api.ClientModInitializer;

public class SelectiveGuiScalingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SGSConfig.load();
    }
}
