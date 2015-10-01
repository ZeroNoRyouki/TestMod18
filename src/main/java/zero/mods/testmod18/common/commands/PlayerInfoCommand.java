package zero.mods.testmod18.common.commands;

import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.common.helpers.CodeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerInfoCommand extends CommandBase {

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {

        FMLLog.info("EXECUTE!");

        int argsCount = args.length;
        EntityPlayerMP player;

        if (argsCount > 0) {

            if (0 == "uuid".compareToIgnoreCase(args[0])) {

                if (2 != argsCount)
                    throw new WrongUsageException("commands.playerinfo.uuid.usage", new Object[0]);

                try {

                    player = this.getPlayer(sender, args[1]);
                    this.onUuidCommand(sender, player);
                    return;

                } catch (PlayerNotFoundException ex) {

                    sender.addChatMessage(new ChatComponentText("Player not found"));
                    return;
                }

            } else if (0 == "position".compareToIgnoreCase(args[0])) {

                try {

                    player = this.getCommandSenderAsPlayer(sender);
                    this.onPositionCommand(sender, player);
                    return;

                } catch (PlayerNotFoundException ex) {

                    sender.addChatMessage(new ChatComponentText("Player not found"));
                    return;
                }
            } else if (0 == "boom".compareToIgnoreCase(args[0])) {

                if (2 != argsCount)
                    throw new WrongUsageException("commands.playerinfo.boom.usage", new Object[0]);

                try {

                    player = this.getCommandSenderAsPlayer(sender);
                    this.onBoomCommand(sender, player, args[1]);
                    return;

                } catch (PlayerNotFoundException ex) {

                    sender.addChatMessage(new ChatComponentText("Player not found"));
                    return;
                }
            }
        }

        throw new WrongUsageException("commands.playerinfo.usage", new Object[0]);
    }

    private void onUuidCommand(ICommandSender sender, EntityPlayerMP targetPlayer) {

        String message = String.format("Player %s UUID is %s", targetPlayer.getName(), targetPlayer.getUniqueID());

        sender.addChatMessage(new ChatComponentText(message));
    }

    private void onPositionCommand(ICommandSender sender, EntityPlayerMP targetPlayer) {

        String message = String.format("Player %s position is %d, %d, %d", targetPlayer.getName(),
                MathHelper.floor_double(targetPlayer.posX),
                MathHelper.floor_double(targetPlayer.posY),
                MathHelper.floor_double(targetPlayer.posZ));

        sender.addChatMessage(new ChatComponentText(message));
    }

    private void onBoomCommand(ICommandSender sender, EntityPlayerMP targetPlayer, String boomType) throws CommandException {

        if ((null != boomType) && !boomType.isEmpty()) {

            boomType = boomType.toLowerCase();

            if (PlayerInfoCommand.s_boomLookup.containsKey(boomType)) {

                EnumParticleTypes boom = PlayerInfoCommand.s_boomLookup.get(boomType);

                sender.addChatMessage(new ChatComponentText("BOOM!"));
                CodeHelper.spawnVanillaParticles(sender.getEntityWorld(), boom, 1, 4,
                        MathHelper.floor_double(targetPlayer.posX),
                        MathHelper.floor_double(targetPlayer.posY),
                        MathHelper.floor_double(targetPlayer.posZ),
                        1, 1, 1);

                return;
            }
        }

        throw new WrongUsageException("commands.playerinfo.boom.usage", new Object[0]);
    }


    @Override
    public String getName() {

        return "playerinfo";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {

        return "commands.playerinfo.usage";
    }

    @Override
    public List getAliases() {

        return PlayerInfoCommand.s_aliases;
    }


    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {

        return args.length == 1 ? this.getListOfStringsMatchingLastWord(args, new String[] {"uuid", "position"}) :
                (args.length == 2 ? this.getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null);

    }

    private static final List s_aliases;// = new ArrayList();
    private static final HashMap<String, EnumParticleTypes> s_boomLookup;// = new HashMap<String, EnumParticleTypes>();

    static {

        s_aliases = new ArrayList();
        s_aliases.add("playerinfo");
        s_aliases.add("pi");

        s_boomLookup = new HashMap<String, EnumParticleTypes>();
        PlayerInfoCommand.s_boomLookup.put("normal", EnumParticleTypes.EXPLOSION_NORMAL);
        PlayerInfoCommand.s_boomLookup.put("large", EnumParticleTypes.EXPLOSION_LARGE);
        PlayerInfoCommand.s_boomLookup.put("huge", EnumParticleTypes.EXPLOSION_HUGE);
    }
}
