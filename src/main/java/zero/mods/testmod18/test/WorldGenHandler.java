package zero.mods.testmod18.test;

import net.minecraft.block.BlockHalfWoodSlab;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zero.mods.zerocore.common.world.WorldGenMinableOres;

import java.util.Random;

/**
 * Created by marco on 27/06/2015.
 */
public class WorldGenHandler implements IWorldGenerator {

    public WorldGenHandler() {

        this._overworldOreGenerator = new WorldGenMinableOres();

        //this._overworldOreGenerator.addOre(Blocks.diamond_ore, 70, 80, 1, 5, 6, Blocks.air);
        //this._overworldOreGenerator.addOre(Blocks.emerald_ore, 70, 90, 1, 13, 7, Blocks.air);
        this._overworldOreGenerator.addOre(Blocks.lapis_ore, 80, 130, 10, 25, 4, Blocks.air);
        //this._overworldOreGenerator.addOre(Blocks.gold_ore, 70, 90, 1, 7, 14, Blocks.air);

    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        if (0 == world.provider.getDimensionId())
            this._overworldOreGenerator.generate(world, random, chunkX, chunkZ);
    }

    public void register() {

        GameRegistry.registerWorldGenerator(this, 46);
    }


    private WorldGenMinableOres _overworldOreGenerator;
}
