package de.johnadept.dvd_screensaver.commands;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DVDCommand{
    public static boolean showOverlay = false;
    public static final Map<UUID, Boolean> entityOverlayMap = new HashMap<>();
    public static void register(CommandDispatcher<CommandSourceStack> pDispatcher) {
        pDispatcher.register(Commands.literal("dvdtoggle").executes((p_137817_) -> {
            return toggleDVD(p_137817_.getSource(), ImmutableList.of(p_137817_.getSource().getEntityOrException()));
        }));
    }

    private static int toggleDVD(CommandSourceStack pSource, Collection<? extends Entity> pTargets) {
        showOverlay = !showOverlay;
        return pTargets.size();
    }
}
