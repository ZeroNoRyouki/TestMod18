package zero.mods.testmod18.client;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.client.particles.TestFX;
import zero.mods.testmod18.client.particles.TestFX2;
import zero.mods.testmod18.common.CommonProxy;
import zero.mods.testmod18.common.blocks.Blocks;
import zero.mods.testmod18.common.items.Items;

/**
 * Created by marco on 19/05/2015.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void onInit(FMLInitializationEvent event) {

        super.onInit(event);

        Items.RegisterModels();
        Blocks.RegisterModels();


        // renders for ModelBlock

        //B3DLoader.instance.addDomain(References.MOD_ID);

        /*


        String modelLocation = References.MOD_ID + ":custom/modelBlock.b3d";
        ModelBakery.addVariantName(Item.getItemFromBlock(Blocks.MODELBLOCK), modelLocation);
        Item item = Item.getItemFromBlock(Blocks.MODELBLOCK);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(modelLocation, "inventory"));
        */


    }

    @Override
    public void spawnTestFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {

        //TestFX fx = new TestFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, 1);
        TestFX2 fx = new TestFX2(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

/*
        TestFX fx = new TestFX(worldIn, xCoordIn, yCoordIn, zCoordIn,
                MathHelper.getRandomDoubleInRange(worldIn.rand, -0.03, 0.03), -0.02D,
                MathHelper.getRandomDoubleInRange(worldIn.rand, -0.03, 0.03), 3);
*/

        fx.spawn();
    }




}
