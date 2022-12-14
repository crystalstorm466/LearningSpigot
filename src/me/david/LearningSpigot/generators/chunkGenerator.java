package me.david.LearningSpigot.generators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class chunkGenerator extends ChunkGenerator {
    int currentHeight = 50;
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                currentHeight = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true) + 1) * 15D + 50D);
                chunk.setBlock(x, currentHeight, z, Material.GRASS_BLOCK);
                chunk.setBlock(x, currentHeight - 1, z, Material.DIRT);
                for (int i = currentHeight - 2; i > 0; i--) {
                    chunk.setBlock(x, i, z, Material.STONE);
                    chunk.setBlock(x, 0, z, Material.BEDROCK);


                }

            }
        }
        return chunk;
    }


}
