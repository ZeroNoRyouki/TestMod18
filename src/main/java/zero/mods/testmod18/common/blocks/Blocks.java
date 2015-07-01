package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.client.render.TileObjTESR;
import zero.mods.testmod18.common.blocks.tile.TileObj;
import zero.mods.zerocore.common.blocks.ModOreBlock;
import zero.mods.testmod18.common.items.Items;

/**
 * Created by marco on 17/05/2015.
 */
@SuppressWarnings("unused")
public class Blocks {

    public static final TestModBlock testCube = new TestModBlock("testCube");
    public static final TestModBlock simpleBlock = new TestModBlock("simpleBlock");
    public static final TestModBlock testOre = new TestModBlock("testOre");
    public static final ModOreBlock testOre2 = new ModOreBlock("testOre2", Material.rock, Items.simpleItem, 0, 1, 10);
    public static final BlockWithState blockWithState = new BlockWithState("blockWithState");
    public static final OrnatedEmeraldBlock ornatedEmeraldBlock = new OrnatedEmeraldBlock("ornatedEmeraldBlock");
    public static final ModelBlock modelBlock = new ModelBlock("modelBlock");
    public static final BlockOBJ blockOBJ = new BlockOBJ("blockOBJ");
    public static final MyWool mywool = new MyWool("mywool");

    public static void Initialize() {
        // nothing to do here
    }

    @SideOnly(Side.CLIENT)
    public static void RegisterModels() {

        Blocks.simpleBlock.registerModels();
        Blocks.testCube.registerModels();
        Blocks.testOre.registerModels();
        Blocks.testOre2.registerModels();
        Blocks.blockWithState.registerModels();
        Blocks.mywool.registerModels();
        Blocks.blockOBJ.registerModels();

        /*
        Item item = Item.getItemFromBlock(Blocks.modelBlock);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Blocks.modelBlock.getFullyQualifiedName(), "inventory"));
*/


    }

}
