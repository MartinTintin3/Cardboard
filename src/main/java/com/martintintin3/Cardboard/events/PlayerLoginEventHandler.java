package com.martintintin3.Cardboard.events;

import com.martintintin3.Cardboard.Server;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.utils.Position;

public class PlayerLoginEventHandler {
    public static void handle(PlayerLoginEvent event, Server server) {
        final Player player = event.getPlayer();

        event.setSpawningInstance(server.instanceContainer);
        player.setRespawnPoint(server.worldSpawnPosition);

        server.connectionManager.getOnlinePlayers().forEach(p -> p.sendMessage(Component.text(player.getUsername() + " joined the game", NamedTextColor.YELLOW)));
    }
}
