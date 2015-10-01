package zero.mods.testmod18.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.client.render.TileEnchanterTESR;
import zero.mods.testmod18.common.blocks.tile.TileEnchanter;
import zero.mods.zerocore.common.helpers.CodeHelper;

public class BlockEnchanter extends TestModBlock implements ITileEntityProvider {

    public BlockEnchanter(String name) {

        super(name);
        this.registerBlockTileEntity(TileEnchanter.class);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {

        super.registerModels();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEnchanter.class, new TileEnchanterTESR());
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEnchanter();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (CodeHelper.calledByLogicalServer(worldIn)) {

            TileEnchanter te = this.getTileEntity(worldIn, pos);

            if (null != te)
                te.openGui(playerIn);
        }

        return true;
    }

    private TileEnchanter getTileEntity(World word, BlockPos position) {

        TileEntity te = (null != word) && (null != position) ? word.getTileEntity(position) : null;

        return te instanceof TileEnchanter ? (TileEnchanter)te : null;
    }
}
