package zero.mods.testmod18.common;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.common.blocks.tile.TileSmallChest;
import zero.mods.testmod18.common.enchantment.TestEnchantment;
import zero.mods.testmod18.common.potion.Potions;
import zero.mods.testmod18.test.WorldGenHandler;
import zero.mods.zerocore.common.ISidedProxy;
import zero.mods.testmod18.common.blocks.Blocks;
import zero.mods.testmod18.common.items.Items;
import zero.mods.zerocore.common.ModGuiHandler;
import zero.mods.zerocore.common.helpers.CodeHelper;
import zero.mods.zerocore.common.helpers.ModObjects;

/**
 * Created by marco on 17/05/2015.
 */
public class CommonProxy implements ISidedProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {

        Items.Initialize();
        Blocks.Initialize();


        //GameRegistry.registerTileEntity(TileSmallChest.class, "TileSmallChest"); // FIX

        new ModGuiHandler(EntryPoint.getInstance());
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

        // register recipes

        GameRegistry.addSmelting(Blocks.testOre2, new ItemStack(Blocks.ornatedEmeraldBlock, 1), 0.5f);


        this.addLoot();

        // enchantments

        EntryPoint.testEnchantment = new TestEnchantment(150);


        //new WorldGenHandler().register();

    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event) {

        // init potions

        Potions.Initialize();

    }

    public void spawnTestFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
    }

    void addLoot() {

        // add multiple items in the dungeon chest loot

        ChestGenHooks dungeonLoot = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);

        dungeonLoot.addItem(new WeightedRandomChestContent(Items.simpleItem, 0, 1, 44, 60));
        dungeonLoot.addItem(new WeightedRandomChestContent(net.minecraft.init.Items.diamond, 0, 40,60,  200));
        dungeonLoot.addItem(new WeightedRandomChestContent(new ItemStack(net.minecraft.init.Items.emerald, 20), 50, 70, 90));


        // add a single item in the bonus chest loot
                                                                                        // item, meta, min-chance, max-chance, weight
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(Items.simpleItem, 0, 10, 50, 120));


        // add a single item in the desert temple chest loot
        // item, meta, min-chance, max-chance, weight
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(Items.testItem, 0, 10, 50, 120));


        // add a single item in the blacksmith chest loot
                                                                                                    // item stack,               min-chance, max-chance, weight
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(Items.testItem, 10), 80, 90, 200));

        // add a single item in the blacksmith chest loot
        // item stack,               min-chance, max-chance, weight
        ChestGenHooks.addItem(ChestGenHooks.NETHER_FORTRESS, new WeightedRandomChestContent(new ItemStack(net.minecraft.init.Items.emerald, 10), 80, 90, 200));



        //The Weight is how often the item is chosen(higher number is higher chance(lower is lower))
    }

}
