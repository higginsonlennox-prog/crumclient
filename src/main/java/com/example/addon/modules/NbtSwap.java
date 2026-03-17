package com.example.addon.modules;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.network.packet.c2s.play.RecipeCategoryOptionsC2SPacket;
import net.minecraft.recipe.book.RecipeBookCategory;

public class NbtSwap extends Module {
    public NbtSwap() {
        super(Categories.Misc, "nbt-swap", "Experimental NBT-Swap packet race.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        // This is where you trigger the experimental logic
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        // Automatically sends the packets when the module is toggled ON
        // Sending the legitimate 'Gate' packet
        mc.getNetworkHandler().sendPacket(new RecipeCategoryOptionsC2SPacket(RecipeBookCategory.CRAFTING, true, true));
        
        // Sending the 'Experimental' packet
        mc.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(36, new ItemStack(Items.COMMAND_BLOCK)));
        
        // Toggle itself off so it doesn't spam the server
        toggle();
    }
}
