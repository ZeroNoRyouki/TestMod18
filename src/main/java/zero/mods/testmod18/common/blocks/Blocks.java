package zero.mods.testmod18.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.mods.testmod18.common.blocks.RF.BlockRFProducer;
import zero.mods.testmod18.common.multiblock.MightyFurnaceBlockPort;
import zero.mods.testmod18.common.multiblock.MightyFurnaceBlockType;
import zero.mods.testmod18.common.multiblock.MightyFurnaceBlockWall;
import zero.mods.testmod18.test.fabricator.BlockFabricator;
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
    public static final BlockFabricator fabricator = new BlockFabricator("fabricator");
    public static final BlockSmallChest smallChest = new BlockSmallChest("smallChest");
    public static final BlockWithFacing blockWithFacing = new BlockWithFacing("blockWithFacing");
    public static final BlockDate blockDate = new BlockDate("blockDate");
    public static final BlockDirtChest90000 dirtchest90000 = new BlockDirtChest90000("dirtchest90000");
    public static final BlockEnchanter enchanter = new BlockEnchanter("enchanter");
    public static final BlockOutwardFacingTest outwardFacing = new BlockOutwardFacingTest("structureBlock");

    public static final MightyFurnaceBlockWall mightyFurnaceWall = new MightyFurnaceBlockWall("mightyFurnaceWall");
    public static final MightyFurnaceBlockPort mightyFurnacePowerPort = new MightyFurnaceBlockPort("mightyFurnacePowerPort", MightyFurnaceBlockType.Power);
    public static final MightyFurnaceBlockPort mightyFurnaceInputPort = new MightyFurnaceBlockPort("mightyFurnaceInputPort", MightyFurnaceBlockType.Input);
    public static final MightyFurnaceBlockPort mightyFurnaceOutputPort = new MightyFurnaceBlockPort("mightyFurnaceOutputPort", MightyFurnaceBlockType.Output);

    public static final BlockRFProducer rfProducer = new BlockRFProducer("rfProducer");


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
        Blocks.fabricator.registerModels();
        Blocks.smallChest.registerModels();
        //Blocks.blockWithFacing.registerModels();
        Blocks.blockDate.registerModels();
        Blocks.enchanter.registerModels();
        Blocks.outwardFacing.registerModels();

        Blocks.mightyFurnaceWall.registerModels();
        Blocks.mightyFurnacePowerPort.registerModels();
        Blocks.mightyFurnaceInputPort.registerModels();
        Blocks.mightyFurnaceOutputPort.registerModels();

        Blocks.rfProducer.registerModels();




        /*
        Item item = Item.getItemFromBlock(Blocks.modelBlock);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Blocks.modelBlock.getFullyQualifiedName(), "inventory"));
*/


    }

}
