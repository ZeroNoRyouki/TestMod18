package zero.mods.testmod18.common.blocks;


import net.minecraft.block.BlockContainer;
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
import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.common.blocks.tile.TileSmallChest;
import zero.mods.zerocore.common.blocks.ModBlock;
import zero.mods.zerocore.common.helpers.CodeHelper;

/**
 * Created by marco on 20/07/2015.
 */
public class BlockSmallChest extends ModBlock implements ITileEntityProvider {

    public BlockSmallChest(String name) {

        super(name, Material.wood);

    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);

        this.registerBlockTileEntity(TileSmallChest.class);
        this.isBlockContainer = true; // block container
    }

    ////////////////// block container
/*
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        //super.breakBlock(worldIn, pos, state); // inutile

        // non dovremmo droppare items se la TE ha un inventario?

        worldIn.removeTileEntity(pos);
    }*/

    /**
     * Called on both Client and Server when World#addBlockEvent is called
     */
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {

        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }


    ////////////////////////////////////////////


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileSmallChest();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        // Uses the gui handler registered to your mod to open the gui for the given gui id
        // open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)

        if (CodeHelper.calledByLogicalServer(world)) {

            TileEntity tile = world.getTileEntity(position);

            if (tile instanceof TileSmallChest)
                ((TileSmallChest)tile).openGui(player);

            //player.openGui(EntryPoint.getInstance(), 1, world, position.getX(), position.getY(), position.getZ());
        }

        return true;
    }

    // This is where you can do something when the block is broken. In this case drop the inventory's contents
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


    // used by the renderer to control lighting and visibility of other blocks.
    // set to false because this block doesn't fill the entire 1x1x1 space
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    // used by the renderer to control lighting and visibility of other blocks, also by
    // (eg) wall or fence to control whether the fence joins itself to this block
    // set to false because this block doesn't fill the entire 1x1x1 space
    @Override
    public boolean isFullCube() {
        return false;
    }


}
