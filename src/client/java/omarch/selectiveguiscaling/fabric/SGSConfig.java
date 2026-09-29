package omarch.selectiveguiscaling.fabric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import net.fabricmc.loader.api.FabricLoader;

public class SGSConfig {
    public static int hotbarScale = 0;
    public static int crosshairScale = 0;
    public static int chatScale = 0;
    
    public static int bossOverlayScale = 0;
    public static int scoreboardScale = 0;
    public static int tabListScale = 0;
    public static int effectsScale = 0;
    public static int actionBarScale = 0;
    public static int titleScale = 0;

    private static File getConfigFile() {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), "selective_gui_scaling.properties");
    }

    public static void load() {
        File file = getConfigFile();
        if (!file.exists()) return;
        try (FileInputStream in = new FileInputStream(file)) {
            Properties props = new Properties();
            props.load(in);
            hotbarScale = Integer.parseInt(props.getProperty("hotbarScale", "0"));
            crosshairScale = Integer.parseInt(props.getProperty("crosshairScale", "0"));
            chatScale = Integer.parseInt(props.getProperty("chatScale", "0"));
            
            bossOverlayScale = Integer.parseInt(props.getProperty("bossOverlayScale", "0"));
            scoreboardScale = Integer.parseInt(props.getProperty("scoreboardScale", "0"));
            tabListScale = Integer.parseInt(props.getProperty("tabListScale", "0"));
            effectsScale = Integer.parseInt(props.getProperty("effectsScale", "0"));
            actionBarScale = Integer.parseInt(props.getProperty("actionBarScale", "0"));
            titleScale = Integer.parseInt(props.getProperty("titleScale", "0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (FileOutputStream out = new FileOutputStream(getConfigFile())) {
            Properties props = new Properties();
            props.setProperty("hotbarScale", String.valueOf(hotbarScale));
            props.setProperty("crosshairScale", String.valueOf(crosshairScale));
            props.setProperty("chatScale", String.valueOf(chatScale));
            
            props.setProperty("bossOverlayScale", String.valueOf(bossOverlayScale));
            props.setProperty("scoreboardScale", String.valueOf(scoreboardScale));
            props.setProperty("tabListScale", String.valueOf(tabListScale));
            props.setProperty("effectsScale", String.valueOf(effectsScale));
            props.setProperty("actionBarScale", String.valueOf(actionBarScale));
            props.setProperty("titleScale", String.valueOf(titleScale));
            
            props.store(out, "Selective GUI Scales (0 = Match Global, 1-4 = Specific Scale)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
