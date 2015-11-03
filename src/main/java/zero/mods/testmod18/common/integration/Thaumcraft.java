package zero.mods.testmod18.common.integration;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.common.blocks.Blocks;
import zero.mods.testmod18.common.integration.thaumcraft.EntityVisBomb;
import zero.mods.testmod18.common.integration.thaumcraft.ItemVisBomb;
import zero.mods.testmod18.lib.References;
import zero.mods.zerocore.common.IModInitializationHandler;
import zero.mods.zerocore.common.helpers.ModObjects;

public final class Thaumcraft implements IModInitializationHandler {

    public static final String THAUMCRAFT_MODID = "Thaumcraft";


    public static final ItemVisBomb visBomb = new ItemVisBomb("visBomb");


    public static boolean isThaumcraftPresent() {

        return Loader.isModLoaded(THAUMCRAFT_MODID);
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {

        this.addAspects();

        EntityRegistry.registerModEntity(EntityVisBomb.class, "entityVisBom", 1, EntryPoint.getInstance(), 80, 3, false);
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

    }


    @Override
    public void onPostInit(FMLPostInitializationEvent event) {

        this.addResearchs();
    }


    private void addAspects() {

        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.mightyFurnaceWall), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.MECHANISM, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.mightyFurnaceInputPort), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.MECHANISM, 2).add(Aspect.VOID, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.mightyFurnaceOutputPort), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.mightyFurnacePowerPort), (new AspectList()).add(Aspect.METAL, 2).add(Aspect.MECHANISM, 2).add(Aspect.ENERGY, 1));
    }

    private void addResearchs() {

        String modId = References.MOD_ID;

        // add the main book category

        ResourceLocation icon = new ResourceLocation(modId, "textures/thaumcraft/bookIcon.png");
        ResourceLocation background = new ResourceLocation(modId, "textures/thaumcraft/bookBG.png");

        ResearchCategories.registerCategory(Thaumcraft.s_bookCategory, null, icon, background);

        // researchs...

        ResearchItem research;
        AspectList aspects;
        String res1key = this.formatResearchKey("res1");

        // - research 1 (main page)

        research = new ResearchItem(res1key, Thaumcraft.s_bookCategory, null, 10, 20, 1, icon);
        research.setAutoUnlock().setSpecial();
        research.setPages(new ResearchPage("tc.research_text.testmod18:res1_p1"));
        ResearchCategories.addResearch(research);

        // research 2: a torch!

        IRecipe recipe = (IRecipe)CraftingManager.getInstance().getRecipeList().get(10);

        icon = new ResourceLocation(modId, "textures/blocks/mywool_2.png");
        aspects = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1);
        research = new ResearchItem(this.formatResearchKey("res2"), Thaumcraft.s_bookCategory, aspects, 5, 20, 1, icon);
        research.setParents(res1key);
        research.setPages(new ResearchPage("tc.research_text.testmod18:res2_p1"),
                new ResearchPage(recipe),
                new ResearchPage("tc.research_text.testmod18:res2_p3"));
        ResearchCategories.addResearch(research);






    }

    private String formatResearchKey(String name) {

        return ModObjects.formatFullyQualifiedName(References.MOD_ID, name);
    }

    private static final String s_bookCategory = "myThuamcraftTestingCategory";

}
