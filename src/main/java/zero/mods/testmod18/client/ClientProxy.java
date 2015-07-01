package zero.mods.testmod18.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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


}
