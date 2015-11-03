package zero.mods.testmod18.common.integration.thaumcraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zero.mods.testmod18.common.items.TestModItem;
import zero.mods.zerocore.common.helpers.CodeHelper;

/**
 * Created by marco on 03/11/2015.
 */
public class ItemVisBomb extends TestModItem {

    public ItemVisBomb(String name) {

        super(name);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode)
            --itemStack.stackSize;

        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (CodeHelper.calledByLogicalServer(world))
            world.spawnEntityInWorld(new EntityVisBomb(world, player));

        return itemStack;
    }


}
