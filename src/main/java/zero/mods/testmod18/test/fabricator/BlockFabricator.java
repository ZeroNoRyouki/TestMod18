package zero.mods.testmod18.test.fabricator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import zero.mods.testmod18.common.blocks.tile.TileObj;
import zero.mods.zerocore.common.blocks.ModBlock;
import zero.mods.zerocore.common.helpers.CodeHelper;

public class BlockFabricator extends ModBlock implements ITileEntityProvider {

    public BlockFabricator(String name) {

        super(name, Material.rock);
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileFabricator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntity te = world.getTileEntity(pos);

        if ((null != te) && (te instanceof TileFabricator) && CodeHelper.calledByServer(world))
            return ((TileFabricator)te).openGui(player);

        return super.onBlockActivated(world, pos, state, player, side, hitX, hitY, hitZ);
    }
}
