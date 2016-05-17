package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "defaultgamemode";
    }

    /**
     * Gets the usage string for the command.
     */
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.defaultgamemode.usage";
    }

    /**
     * Callback for when the command is executed
     *  
     * @param server The Minecraft server instance
     * @param sender The source of the command invocation
     * @param args The arguments that were passed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length <= 0)
        {
            throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
        }
        else
        {
            WorldSettings.GameType worldsettings$gametype = this.getGameModeFromCommand(sender, args[0]);
            this.setDefaultGameType(worldsettings$gametype, server);
            notifyOperators(sender, this, "commands.defaultgamemode.success", new Object[] {new TextComponentTranslation("gameMode." + worldsettings$gametype.getName(), new Object[0])});
        }
    }

    /**
     * Set the default game type for the server. Also propogate the changes to all players if the server is set to force
     * game mode
     *  
     * @param gameType The game type to set
     * @param server The Minecraft server instance
     */
    protected void setDefaultGameType(WorldSettings.GameType gameType, MinecraftServer server)
    {
        server.setGameType(gameType);

        if (server.getForceGamemode())
        {
            for (EntityPlayerMP entityplayermp : server.getPlayerList().getPlayerList())
            {
                entityplayermp.setGameType(gameType);
            }
        }
    }
}