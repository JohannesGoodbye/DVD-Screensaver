package de.johnadept.dvd_screensaver.events;

import de.johnadept.dvd_screensaver.commands.DVDCommand;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void registerCommands(RegisterClientCommandsEvent event){
        DVDCommand.register(event.getDispatcher());
    }
}
