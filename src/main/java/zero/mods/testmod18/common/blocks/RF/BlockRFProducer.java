package zero.mods.testmod18.common.blocks.RF;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import zero.mods.zerocore.common.blocks.ModBlock;

public class BlockRFProducer extends ModBlock implements ITileEntityProvider {

    public BlockRFProducer(String name) {

        super(name, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return null;
    }

    @Override
    protected void initBlock() {

        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(this.soundTypePiston);
    }
}
