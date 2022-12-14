package me.david.LearningSpigot.generators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class grassPopulator {
    int currentHeight = 50;
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextBoolean()) {
            int amount = random.nextInt(4)+1;
            for (int i = 1; i < amount; i++) {
                int X = random.nextInt(15);
                int Z = random.nextInt(15);
                int Y;
                for (Y = world.getMaxHeight() - 1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--) {
                    chunk.getBlock(X, Y+1, Z).setType(Material.ANCIENT_DEBRIS);
                }
            }
        }
    }
}
