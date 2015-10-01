package zero.mods.testmod18.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zero.mods.testmod18.common.blocks.tile.TileDate;
import zero.mods.zerocore.common.blocks.ModBlock;
import zero.mods.zerocore.common.helpers.CodeHelper;

import java.util.Date;

public class BlockDate extends TestModBlock implements ITileEntityProvider {

    public BlockDate(String name) {

        super(name, Material.rock);

        GameRegistry.registerTileEntity(TileDate.class, this.getFullyQualifiedName());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileDate(new Date());
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        if (!CodeHelper.calledByLogicalServer(worldIn))
            return;

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (null != placer) {

            TileEntity tile = worldIn.getTileEntity(pos);

            if (tile instanceof TileDate)
                ((TileDate)tile).setNameOfUser(placer.getName());
        }
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {

        if (!CodeHelper.calledByLogicalServer(worldIn))
            return;

        TileEntity tile = worldIn.getTileEntity(pos);

        if (tile instanceof TileDate) {

            TileDate tileDate = (TileDate)tile;

            Date date = tileDate.getCreationDate();
            String user = tileDate.getNameOfUser();
            String message;

            if ((null != date) && (null != user))
                message = String.format("Block placed on %s by %s", date.toString(), user);
            else
                message = "Unable to load block informations";

            playerIn.addChatMessage(new ChatComponentText(message));
        }

    }


}
