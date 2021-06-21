package com.martintintin3.Cardboard.commands;

import com.martintintin3.Cardboard.Server;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.entity.fakeplayer.FakePlayerOption;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class SteveCommand extends Command {
    private static HashMap<Integer, FakePlayer> steveList = new HashMap<Integer, FakePlayer>();

    public SteveCommand(Server server) {
        super("steve");

        this.setCondition(Conditions::playerOnly);

        this.addSyntax((sender, executor) -> {
            Player playerSender = sender.asPlayer();
            FakePlayer.initPlayer(UUID.randomUUID(), "Steve", steve -> {
                if(steveList.size() >= 100) {
                    sender.sendMessage(Component.text("You have reached the maximum steve limit(100)", NamedTextColor.RED));
                } else {
                    Integer steveId = 0;
                    Boolean foundUniqueId = false;
                    while(!foundUniqueId) {
                        final Integer random = new Random().nextInt(100) + 1;
                        if(steveList.get(random) == null) {
                            steveId = random;
                            foundUniqueId = true;
                        }
                    }

                    FakePlayerOption option = steve.getOption();
                    steveList.put(steveId, steve);

                    option.setInTabList(false);
                    option.setRegistered(false);

                    steve.setCustomNameVisible(true);
                    steve.setCustomName(Component.text("Steve"));
                    steve.teleport(playerSender.getPosition());

                    sender.sendMessage(Component.text("Summoned a steve with id: " + steveId.toString() + ", current steve count: " + steveList.size()));
                }
            });
        });
    }
}
