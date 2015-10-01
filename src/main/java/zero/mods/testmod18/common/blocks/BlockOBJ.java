package zero.mods.testmod18.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.client.render.TileObjTESR;
import zero.mods.testmod18.common.blocks.tile.TileObj;

/**
 * Created by marco on 20/06/2015.
 */
public class BlockOBJ extends TestModBlock implements ITileEntityProvider {

    public BlockOBJ(String name) {

        super(name);


        this.registerBlockTileEntity(TileObj.class);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {

        super.registerModels();

        ClientRegistry.bindTileEntitySpecialRenderer(TileObj.class, new TileObjTESR());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileObj();
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

        boolean logicalSideIsServer = !worldIn.isRemote;
        boolean physicalSideIsServer = FMLClientHandler.instance().getSide() == Side.SERVER;

        FMLLog.info("-------------------------------------------------------------------------------------------");
        FMLLog.info("log-side:%s - phy-side:%s", logicalSideIsServer ? "SERVER" : "CLIENT", physicalSideIsServer ? "SERVER" : "CLIENT" );

        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
}
