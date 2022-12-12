package me.david.LearningSpigot;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;


public class OreDistribution extends BlockPopulator {
    BlockPopulator oreGen = new BlockPopulator() {
        public void populate(World world, Random random, Chunk chunk) {
            for (int ChunkY = 0; ChunkY < 64; ChunkY++) {
                for (int ChunkX = 0; ChunkX < 16; ChunkX++) {
                    for (int ChunkZ = 0; ChunkZ < 16; ChunkZ++) {
                        int blockx = ChunkX + (chunk.getX() * 16);
                        int blockz = ChunkZ + (chunk.getZ() * 16);

                        if (random.nextInt(100) < 50 ) {
                            Block block = world.getBlockAt(blockx, random.nextInt(128), blockz);
                            Block block1 = world.getBlockAt(blockx, random.nextInt(128), blockz);
                            Block block2 = world.getBlockAt(blockx, random.nextInt(128), blockz);


                            block.setType(Material.ANCIENT_DEBRIS);
                            block1.setType(Material.NETHER_GOLD_ORE);
                            block2.setType(Material.NETHER_QUARTZ_ORE);
                        }
                    }
                }
            }
        }
    };




}
