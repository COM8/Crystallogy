package de.comeight.crystallogy.commands;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.handler.AiHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandDisableCustomAi extends CommandBase {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private final List aliases;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CommandDisableCustomAi() {
		aliases = new ArrayList<String>();
		aliases.add("disableCustomAis");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		List<String> list = new ArrayList<String>();
		list.add("true");
		list.add("false");
		return list;
	}
	
	@Override
	public String getCommandName() {
		return "disableCustomAis";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "disableCustomAis <true/false>";
	}

	@Override
	public List<String> getCommandAliases() {
		return aliases;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length > 0){
			boolean b = parseBoolean(args[0]);
			AiHandler.isCustomAiEnabled = b;
		}
		if(AiHandler.isCustomAiEnabled){
			sender.addChatMessage(new TextComponentString("Crystallogy custom Ais: " + TextFormatting.DARK_GREEN + "enabled"));
		}
		else{
			sender.addChatMessage(new TextComponentString("Crystallogy custom Ais: " + TextFormatting.RED + "disabled"));
		}
	}
	
}
