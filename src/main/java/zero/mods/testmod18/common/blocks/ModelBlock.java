package zero.mods.testmod18.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import zero.mods.testmod18.lib.References;

/**
 * Created by marco on 29/05/2015.
 */
public class ModelBlock extends TestModBlock {

    private int counter = 1;
    private ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[0], new IUnlistedProperty[]{ B3DLoader.B3DFrameProperty.instance });


    public ModelBlock(String name) {

        super(name, Material.rock);

    }

    @Override
    public boolean isOpaqueCube() { return false; }

    @Override
    public boolean isFullCube() { return false; }

    @Override
    public boolean isVisuallyOpaque() { return false; }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {

        //return super.getExtendedState(state, world, pos);

        B3DLoader.B3DState newState = new B3DLoader.B3DState(null, counter);
        return ((IExtendedBlockState)this.state.getBaseState()).withProperty(B3DLoader.B3DFrameProperty.instance, newState);


/*
        //B3DLoader.B3DMeshLocation location = new B3DLoader.B3DMeshLocation(References.MOD_ID, "modelBlock.b3d", "modelBlock.png");
        //ResourceLocation location = new ResourceLocation(References.MOD_ID, "modelBlock.b3d");
        //ResourceLocation location = new ResourceLocation(References.MOD_ID, "block/untitled2.b3d");
        ResourceLocation location = new ResourceLocation(References.MOD_ID, "BloodPillar.b3d");


        IModel model = ModelLoaderRegistry.getModel(location);
        B3DLoader.B3DState defaultState = ((B3DLoader.Wrapper)model).getDefaultState();
        B3DLoader.B3DState newState = new B3DLoader.B3DState(defaultState.getAnimation(), counter);
        return ((IExtendedBlockState)this.state.getBaseState()).withProperty(B3DLoader.B3DFrameProperty.instance, newState);
        */
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            System.out.println("click " + counter);
            if(player.isSneaking()) counter--;
            else counter++;
            //if(counter >= model.getNode().getKeys().size()) counter = 0;
            world.markBlockRangeForRenderUpdate(pos, pos);
        }
        return false;
    }


}
