package com.example.addon;

import com.example.addon.modules.NbtSwap;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger(Addon.class);

    @Override
    public void onInitialize() {
        LOG.info("Initializing NBT Swap Addon...");

        // This is the ONLY way Meteor will see your button
        Modules.get().add(new NbtSwap());
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
