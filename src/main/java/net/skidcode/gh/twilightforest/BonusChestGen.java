package net.skidcode.gh.twilightforest;

import java.util.Random;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.class_225;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent.ChunkDecoration;

public class BonusChestGen {
	@EventListener
	public static void generateWorld(ChunkDecoration event) {
		if(event.worldSource.method_1808().equals("RandomLevelSource")) {
			World world = event.world;
			Random r = new Random();
			int spawnX = world.method_262().getSpawnX();
	        int spawnZ = world.method_262().getSpawnZ();
	        if (spawnX != 0 && spawnZ != 0 && spawnX >= event.x && spawnX <= event.x + 16 && spawnZ >= event.z && spawnZ <= event.z + 16) {
	            System.out.println("Making supply chest at " + spawnX + ", " + spawnZ);
	            int dx = spawnX + r.nextInt(8) - r.nextInt(8);
	            int dz = spawnZ + r.nextInt(8) - r.nextInt(8);
	            int dy = world.method_228(dx, dz);
	            world.setBlock(dx, dy, dz, Block.CHEST.id);
	            class_225 tec = (class_225)world.method_1777(dx, dy, dz);
	            if (tec != null && tec.size() > 0) {
	                ItemStack is0 = new ItemStack(Item.GOLDEN_APPLE);
	                tec.setStack(0, is0);
	                ItemStack is1 = new ItemStack(Item.DIAMOND_PICKAXE);
	                tec.setStack(1, is1);
	                ItemStack is2 = new ItemStack(Item.DIAMOND_SHOVEL);
	                tec.setStack(2, is2);
	                ItemStack is3 = new ItemStack(Item.DIAMOND_AXE);
	                tec.setStack(3, is3);
	                ItemStack is4 = new ItemStack(Item.IRON_SWORD);
	                tec.setStack(4, is4);
	                ItemStack is5 = new ItemStack(Block.OBSIDIAN, 14);
	                tec.setStack(5, is5);
	                ItemStack is6 = new ItemStack(Item.FLINT_AND_STEEL);
	                tec.setStack(6, is6);
	                ItemStack is7 = new ItemStack(Block.RED_MUSHROOM, 64);
	                tec.setStack(7, is7);
	            }
	        }
		}
	}
	
}
