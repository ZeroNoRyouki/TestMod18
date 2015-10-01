package zero.mods.testmod18.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import zero.mods.zerocore.common.helpers.CodeHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealTimeCommand extends CommandBase /* implements ICommand */ {


    @Override
    public String getName() {

        return "realtime";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {

        return "commands.realtime.usage";
    }

    @Override
    public List getAliases() {

        return RealTimeCommand.s_aliases;
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {

        if (!CodeHelper.calledByLogicalServer(sender.getEntityWorld()))
            return;

        Date now = new Date();

        sender.addChatMessage(new ChatComponentText("The real time is:"));
        sender.addChatMessage(new ChatComponentText(now.toString()));

    }

    @Override
    public boolean canCommandSenderUse(ICommandSender sender) {

        return sender.getName().startsWith("Zero") && super.canCommandSenderUse(sender);
    }

    private static final List s_aliases = new ArrayList();

    static {

        RealTimeCommand.s_aliases.add("realtime");
        RealTimeCommand.s_aliases.add("rt");
    }
}
