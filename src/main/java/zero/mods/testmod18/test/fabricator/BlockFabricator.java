package zero.mods.testmod18.test.fabricator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
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
        this.isBlockContainer = true; // block container
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);
        
        this.registerBlockTileEntity(TileFabricator.class);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileFabricator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (CodeHelper.calledByLogicalServer(world)) {

            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileFabricator)
                ((TileFabricator)tile).openGui(player);
        }

        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos position, IBlockState state) {

        TileEntity tile = world.getTileEntity(position);
        IInventory inventory = tile instanceof IInventory ? (IInventory)tile : null;

        if (null != inventory) {

            InventoryHelper.dropInventoryItems(world, position, inventory);
            inventory.clear();
        }

        // Super MUST be called last because it removes the tile entity
        super.breakBlock(world, position, state);
    }

    /**
     * Called on both Client and Server when World#addBlockEvent is called
     */
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {

        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }


}
