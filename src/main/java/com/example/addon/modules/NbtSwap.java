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
        // We use §6 for a gold/gingerbread color in the menu
        super(Categories.Misc, "nbt-swap", "§6Gingerbread §fExperimental Packet Race.");
    }

    @Override
    public void onActivate() {
        if (mc.player == null || mc.getNetworkHandler() == null) {
            toggle();
            return;
        }

        // 1. Send the Survival "Gate" Packet (Recipe Book)
        mc.getNetworkHandler().sendPacket(new RecipeCategoryOptionsC2SPacket(
            RecipeBookCategory.CRAFTING, true, true
        ));

        // 2. The NBT Request (Command Block into Hotbar Slot 1 / index 36)
        ItemStack commandBlock = new ItemStack(Items.COMMAND_BLOCK);
        mc.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(36, commandBlock));

        info("§6[Gingerbread] §fSent swap packets. Check hotbar slot 1.");
        
        // Auto-disable so it doesn't spam
        toggle();
    }
}
