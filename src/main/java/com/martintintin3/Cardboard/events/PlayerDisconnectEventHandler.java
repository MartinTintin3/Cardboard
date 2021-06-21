package com.martintintin3.Cardboard.events;

import com.martintintin3.Cardboard.Server;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;

public class PlayerDisconnectEventHandler {
    public static void handle(PlayerDisconnectEvent event, Server server) {
        final Player player = event.getPlayer();

        MinecraftServer.LOGGER.info(player.getUsername() + " disconnected");
        server.connectionManager.getOnlinePlayers().forEach(p -> p.sendMessage(Component.text(player.getUsername() + " left the game", NamedTextColor.YELLOW)));
    }
}
