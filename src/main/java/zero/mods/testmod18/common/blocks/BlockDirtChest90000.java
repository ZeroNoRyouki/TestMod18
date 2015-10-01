package zero.mods.testmod18.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import zero.mods.testmod18.common.blocks.tile.TileDirtChest90000;
import zero.mods.zerocore.common.helpers.CodeHelper;

public class BlockDirtChest90000 extends TestModBlock implements ITileEntityProvider {

    public BlockDirtChest90000(String name) {

        super(name, Material.rock);
    }

    @Override
    protected void initBlock() {

        super.initBlock();

        this.registerBlockTileEntity(TileDirtChest90000.class);
        this.isBlockContainer = true; // block container
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileDirtChest90000();
    }

    /**
     * Called on both Client and Server when World#addBlockEvent is called
     */
    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {

        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (CodeHelper.calledByLogicalServer(world)) {

            TileDirtChest90000 tile = this.getTileEntity(world, position);

            if (null != tile)
                tile.openGui(player);
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


    @Override
    public boolean isOpaqueCube() {

        return false;
    }


    @Override
    public boolean isFullCube() {
        return false;
    }


    private TileDirtChest90000 getTileEntity(World world, BlockPos position) {

        TileEntity te = (null != world) && (null != position) ? world.getTileEntity(position) : null;

        return (te instanceof TileDirtChest90000) ? (TileDirtChest90000)te : null;
    }

}
