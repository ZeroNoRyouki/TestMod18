package zero.mods.testmod18.common.blocks.tile;


import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.testmod18.EntryPoint;
import zero.mods.testmod18.client.gui.GuiEnchanter;
import zero.mods.testmod18.common.blocks.ContainerEnchanter;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntityContainer;
import zero.mods.zerocore.common.helpers.CodeHelper;

import java.util.Random;

public class TileEnchanter extends ModTileEntityContainer implements IUpdatePlayerListBox {

    public TileEnchanter() {


        super(3, 1);
        this.reset();

        FMLLog.info("TileEnchanter created");
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    @Override
    public String getName() {

        return "tile.container.enchanther";
    }

    @Override
    public void update() {

        if (CodeHelper.calledByLogicalServer(this.worldObj)) {

            if (!this._enchanting) {

                if (this.canStartEnchanting()) {

                    this._enchanting = true;
                    this._processingTime = 0;
                }

            } else if (this._enchanting) {

                ++this._processingTime;

                if (TileEnchanter.ENCH_TIME == this._processingTime) {

                    this.enchantItem();
                    this.reset();
                }

            }
        } else {

            // meanwhile.. on the client...

            if (this._enchanting) {

                ++this._processingTime;

                if (0 == this._processingTime % 5)
                    CodeHelper.spawnVanillaParticles(this.worldObj, EnumParticleTypes.CRIT_MAGIC, 20, 40, this.pos.getX(), this.pos.getY() + 1, this.pos.getZ(), 2, 2, 2);

                if (TileEnchanter.ENCH_TIME == this._processingTime)
                    this.reset();

            }
        }


    }

    public int getMaxProcessingTime() {

        return TileEnchanter.ENCH_TIME;
    }

    public int getCurrentProcessingTime() {

        return this._processingTime;
    }

    public EnchantType getEnchantType() {

        return this._enchantType;
    }


    public boolean isEnchanting() {

        return this._enchanting;
    }


    @Override
    public int getFieldCount() {

        return 3;
    }

    @Override
    public int getField(int id) {

        switch (id) {

            case 0:
                return this._enchanting ? 1 : 0;

            case 1:
                return this._processingTime;

            case 2:
                return this._enchantType.ordinal();
        }

        return super.getField(id);
    }


    @Override
    public void setField(int id, int value) {

        switch (id) {

            case 0:
                this._enchanting = 1 == value;
                break;

            case 1:
                this._processingTime = value;
                break;

            case 2:
                this._enchantType = EnchantType.getTypeFromOrdinal(value);
                break;
        }
    }



    public final static int SLOT_INDEX_ITEM = 0;
    public final static int SLOT_INDEX_ESSENCE = 1;
    public final static int SLOT_INDEX_OUTPUT = 2;

    private final static int ENCH_TIME = 20*3;

    private void reset() {

        this._enchanting = false;
        this._processingTime = 0;
        this._enchantType = EnchantType.A;
    }

    private boolean canStartEnchanting() {

        ItemStack stack;
        Item item;

        if (null != this.getStackInSlot(TileEnchanter.SLOT_INDEX_OUTPUT))
            return false;

        stack = this.getStackInSlot(TileEnchanter.SLOT_INDEX_ESSENCE);
        if ((null == stack) || (Items.dye != stack.getItem()) || (EnumDyeColor.BLUE.getDyeDamage() != stack.getItemDamage()))
            return false;

        stack = this.getStackInSlot(TileEnchanter.SLOT_INDEX_ITEM);
        item = null != stack ? stack.getItem() : null;

        if (!((item instanceof ItemTool) || (item instanceof ItemSword)) || (item instanceof ItemHoe))
            return false;


        // FAKE
        Random rnd = new Random();
        int randomOrdinal = MathHelper.getRandomIntegerInRange(rnd, 0, 3);

        this._enchantType = EnchantType.getTypeFromOrdinal(randomOrdinal);

        return true;
    }

    private void enchantItem() {

        ItemStack tool = this.getStackInSlot(TileEnchanter.SLOT_INDEX_ITEM);
        ItemStack essence = this.getStackInSlot(TileEnchanter.SLOT_INDEX_ESSENCE);

        if (null == tool || null == essence || essence.stackSize < 1)
            return;



        //tool.addEnchantment(Enchantment.unbreaking, 2);
        tool.addEnchantment(EntryPoint.testEnchantment, 2);

        --essence.stackSize;
        if (0 == essence.stackSize)
            this.setInventorySlotContents(TileEnchanter.SLOT_INDEX_ESSENCE, null);

        this.setInventorySlotContents(TileEnchanter.SLOT_INDEX_ITEM, null);
        this.setInventorySlotContents(TileEnchanter.SLOT_INDEX_OUTPUT, tool);
    }

    @Override
    public Object getServerGuiElement(InventoryPlayer playerInventory) {

        return new ContainerEnchanter(playerInventory, this);
    }

    @Override
    public Object getClientGuiElement(InventoryPlayer playerInventory) {

        return new GuiEnchanter(playerInventory, this);
    }

    @Override
    public boolean canOpenGui() {
        return true;
    }

    private int _processingTime;
    private boolean _enchanting;
    private EnchantType _enchantType;

    public enum EnchantType {
        A,
        B,
        C,
        D;

        public static EnchantType getTypeFromOrdinal(int ordinal) {

            EnchantType[] types = EnchantType.values();

            return (ordinal >= 0 && ordinal < types.length) ? types[ordinal] : EnchantType.A;
        }
    }


}
