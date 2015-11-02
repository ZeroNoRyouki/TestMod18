package zero.mods.testmod18.common.blocks.RF;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import zero.mods.zerocore.common.blocks.ModBlock;
import zero.mods.zerocore.common.helpers.CodeHelper;

public class BlockRFProducer extends ModBlock implements ITileEntityProvider {

    public BlockRFProducer(String name) {

        super(name, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileRFProducer();
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);

        this.registerBlockTileEntity(TileRFProducer.class);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (CodeHelper.calledByLogicalServer(worldIn)) {

            TileEntity te = worldIn.getTileEntity(pos);

            if (te instanceof TileRFProducer) {

                TileRFProducer producer = (TileRFProducer)te;
                int stored = producer.getEnergyStored(EnumFacing.UP);

                playerIn.addChatMessage(new ChatComponentText(String.format("RF Producer - energy stored = %d RF", stored)));
                return true;
            }

        }


        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
}
