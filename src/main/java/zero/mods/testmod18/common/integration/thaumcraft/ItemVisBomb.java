package zero.mods.testmod18.common.integration.thaumcraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.IRechargable;
import thaumcraft.common.lib.aura.AuraHandler;
import zero.mods.testmod18.common.integration.Thaumcraft;
import zero.mods.testmod18.common.items.TestModItem;
import zero.mods.zerocore.common.helpers.CodeHelper;

import java.util.ArrayList;

/**
 * Created by marco on 03/11/2015.
 */
public class ItemVisBomb extends TestModItem implements IRechargable {

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

    // IRechargable


    @Override
    public AspectList getAspectsInChargable(ItemStack itemStack) {

        return Thaumcraft.getStoredAspects(itemStack, ItemVisBomb.s_ignisAndPerditio, true);
    }

    @Override
    public Aspect handleRecharge(World world, ItemStack itemStack, BlockPos position, EntityPlayer player, int amount) {

        AspectList currentAspects = this.getAspectsInChargable(itemStack);

        for (Aspect aspect : currentAspects.getAspects()) {

            if (null != aspect) {

                int freeSpace = MAX_VIS - currentAspects.getAmount(aspect);
                int drained = AuraHandler.drainAuraAvailable(world, position, aspect, Math.min(amount, freeSpace));

                if (drained > 0) {

                    Thaumcraft.addStoredAspectAmount(itemStack, aspect, drained, MAX_VIS);
                    amount -= drained;

                    if (amount <= 0)
                        return aspect;
                }
            }
        }

        return null;
    }

    private static final ArrayList<Aspect> s_ignisAndPerditio;

    private static final int MAX_VIS = 100;

    static {

        s_ignisAndPerditio = new ArrayList<Aspect>(2);

        s_ignisAndPerditio.add(Aspect.FIRE);
        s_ignisAndPerditio.add(Aspect.ENTROPY);
    }
}
