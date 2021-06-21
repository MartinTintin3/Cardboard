package com.martintintin3.Cardboard.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.command.*;
import net.minestom.server.command.builder.*;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.command.builder.exception.ArgumentSyntaxException;
import net.minestom.server.entity.*;
import net.minestom.server.utils.entity.EntityFinder;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");

        this.setCondition(Conditions::playerOnly);

        this.setDefaultExecutor(this::usage);

        ArgumentEntity player = ArgumentType.Entity("player")
                .onlyPlayers(true)
                .singleEntity(true);

        ArgumentEnum<GameMode> mode = ArgumentType.Enum("gamemode", GameMode.class)
                .setFormat(ArgumentEnum.Format.LOWER_CASED);

        this.setArgumentCallback(this::targetCallback, player);
        this.setArgumentCallback(this::gameModeCallback, mode);

        this.addSyntax(this::executeOnSelf, mode);
        this.addSyntax(this::executeOnOther, player, mode);
    }

    private void usage(CommandSender sender, CommandContext context) {
        sender.sendMessage(Component.text("Usage: /gamemode [player] <gamemode>")
                .hoverEvent(Component.text("Click to get this command"))
                .clickEvent(ClickEvent.suggestCommand("/gamemode player gamemode")));
    }

    private void executeOnSelf(CommandSender sender, CommandContext context) {
        Player player = (Player) sender;

        GameMode gamemode = context.get("gamemode");
        assert gamemode != null; // mode is not supposed to be null, because gamemodeName will be valid
        player.setGameMode(gamemode);
        player.sendMessage(Component.text("You are now playing in " + gamemode.toString().toLowerCase()));
    }

    private void executeOnOther(CommandSender sender, CommandContext context) {
        GameMode gamemode = context.get("gamemode");
        EntityFinder targetFinder = context.get("player");
        Player target = targetFinder.findFirstPlayer(sender);
        assert gamemode != null; // mode is not supposed to be null, because gamemodeName will be valid
        assert target != null;
        target.setGameMode(gamemode);
        target.sendMessage(Component.text("You are now playing in " + gamemode.toString().toLowerCase()));
    }

    private void targetCallback(CommandSender sender, ArgumentSyntaxException exception) {
        sender.sendMessage(Component.text("'" + exception.getInput() + "' is not a valid player name."));
    }

    private void gameModeCallback(CommandSender sender, ArgumentSyntaxException exception) {
        sender.sendMessage(Component.text("'" + exception.getInput() + "' is not a valid gamemode!"));
    }
}
