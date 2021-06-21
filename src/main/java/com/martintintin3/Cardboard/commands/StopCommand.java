package com.martintintin3.Cardboard.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.*;
import org.jetbrains.annotations.NotNull;

public class StopCommand extends Command {
    public StopCommand() {
        super("stop");

        this.addSyntax(this::execute);
    }

    private void execute(@NotNull CommandSender commandSender, @NotNull CommandContext commandContext) {
        MinecraftServer.stopCleanly();
    }
}
