package com.martintintin3.Cardboard.generator;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.*;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.biomes.Biome;
import org.jetbrains.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenerator implements ChunkGenerator {
    @Override
    public void generateChunkData(@NotNull ChunkBatch batch, int chunkX, int chunkZ) {
        for(byte y = 0; y < 40; y++) {
            for(byte x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
                for(byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                    final Block block =
                            y == 0 ? Block.BEDROCK :
                            y < 4 ? new Random().nextInt(3) == 2 ? Block.BEDROCK : Block.DEEPSLATE :
                            y < 20 ? Block.DEEPSLATE :
                            y < 30 ? Block.STONE :
                            y < 38 ? Block.DIRT :
                            y < 39 ? Block.GRASS_BLOCK :
                            new Random().nextInt(4) == 2 ? Block.GRASS : Block.AIR;

                    batch.setBlock(x, y, z, block);
                }
            }
        }
    }

    @Override
    public void fillBiomes(@NotNull Biome[] biomes, int chunkX, int chunkZ) {
        Arrays.fill(biomes, MinecraftServer.getBiomeManager().getByName(Biome.PLAINS.getName()));
    }

    @Override
    public @Nullable List<ChunkPopulator> getPopulators() {
        return null;
    }
}
