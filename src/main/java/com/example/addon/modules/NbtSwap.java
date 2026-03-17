package com.example.addon.modules;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.network.packet.c2s.play.RecipeCategoryOptionsC2SPacket;
import net.minecraft.recipe.book.RecipeBookCategory;

public class NbtSwap extends Module {
    public NbtSwap() {
        super(Categories.Misc, "nbt-swap", "Attempts to race packets for a Command Block.");
    }

    @Override
    public void onActivate() {
        if (mc.player == null || mc.getNetworkHandler() == null) {
            toggle();
            return;
        }

        // Packet 1: The 'Bypass' packet
        mc.getNetworkHandler().sendPacket(new RecipeCategoryOptionsC2SPacket(RecipeBookCategory.CRAFTING, true, true));

        // Packet 2: The Command Block Request
        // Slot 36 is the first slot of your hotbar
        mc.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(36, new ItemStack(Items.COMMAND_BLOCK)));

        info("Packets sent. If it worked, check your first hotbar slot.");
        toggle(); // Turns itself off immediately
    }
}
