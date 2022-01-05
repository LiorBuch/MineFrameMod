package com.lb.mineframe.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

import static com.lb.mineframe.MineFrame.MODID;

public class KeyMaps {

    private KeyMaps() {
    }

    public static KeyMapping reload_key;


    public static void init() {
        reload_key = registerKey("reload_key", InputConstants.KEY_R, reload_key.CATEGORY_GAMEPLAY);

    }

    private static KeyMapping registerKey(String name, int keycode, String catagory) {
        final var key = new KeyMapping("key." + MODID + "." + name, keycode, catagory);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
