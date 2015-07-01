package zero.mods.testmod18.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

        GameRegistry.registerTileEntity(TileObj.class, this.getFullyQualifiedName());
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

}
