package zero.mods.testmod18.common.multiblock;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnaceIOPortTileEntity;
import zero.mods.testmod18.common.multiblock.tile.MightyFurnacePowerTileEntity;
import zero.mods.zerocore.common.multiblock.IMultiblockPart;
import zero.mods.zerocore.common.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.common.multiblock.MultiblockValidationException;
import zero.mods.zerocore.common.multiblock.rectangular.RectangularMultiblockControllerBase;

public class MightyFurnaceController extends RectangularMultiblockControllerBase implements IEnergyReceiver {


    public MightyFurnaceController(World world) {

        super(world);

        this._inputPort = this._outputPort = null;
        this._powerPort = null;
        this._rfStorage = null;
        this._active = false;
    }

    public boolean isActive() {

        return this._active;
    }

    public void switchActive() {

        //this._active = !this._active;
    }

    // IEnergyReceiver begin

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {

        return this.getRFStorage().receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        return (null == this._rfStorage) ? 0 : this.getRFStorage().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        return RF_CAPACITY;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {
        return false; // never called
    }

    // IEnergyReceiver end


    @Override
    protected void onBlockAdded(IMultiblockPart newPart) {

        FMLLog.info("MightyFurnaceController:onBlockAdded : %s", newPart.toString());


    }

    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart) {

        FMLLog.info("MightyFurnaceController:onBlockRemoved : %s", oldPart.toString());

        if (oldPart instanceof MightyFurnacePowerTileEntity) {

            MightyFurnacePowerTileEntity tile = (MightyFurnacePowerTileEntity)oldPart;

            if (this._powerPort == tile)
                this._powerPort = null;

        } else if (oldPart instanceof MightyFurnaceIOPortTileEntity) {

            MightyFurnaceIOPortTileEntity tile = (MightyFurnaceIOPortTileEntity)oldPart;

            if (this._outputPort == tile)
                this._outputPort = null;
            else if (this._inputPort == tile)
                this._inputPort = null;
        }
    }

    @Override
    protected void isMachineWhole() throws MultiblockValidationException {

        FMLLog.info("MightyFurnaceController:isMachineWhole");


        MightyFurnacePowerTileEntity powerPort = null;
        MightyFurnaceIOPortTileEntity inputPort = null;
        MightyFurnaceIOPortTileEntity outputPort = null;

        super.isMachineWhole();

        for (IMultiblockPart part : this.connectedParts) {

            if (part instanceof MightyFurnacePowerTileEntity) {

                if (null != powerPort)
                    throw new MultiblockValidationException("There is already a power port in the machine");

                powerPort = (MightyFurnacePowerTileEntity)part;

            } else if (part instanceof MightyFurnaceIOPortTileEntity) {

                MightyFurnaceIOPortTileEntity io = (MightyFurnaceIOPortTileEntity) part;
                boolean isInput = io.isInput();

                if (isInput) {

                    if (null != inputPort)
                        throw new MultiblockValidationException("There is already an input port in the machine");

                    inputPort = io;

                } else {

                    if (null != outputPort)
                        throw new MultiblockValidationException("There is already an output port in the machine");

                    outputPort = io;
                }
            }
        }

        if (null == outputPort)
            throw new MultiblockValidationException("You need a power port in the machine");

        if (null == inputPort)
            throw new MultiblockValidationException("You need a input port in the machine");

        if (null == outputPort)
            throw new MultiblockValidationException("You need a output port in the machine");
    }

    @Override
    protected void onMachineAssembled() {

        this.lookupPorts();

        FMLLog.info("CONTROLLER - assembled");
    }

    @Override
    protected void onMachineRestored() {

        this.lookupPorts();

        FMLLog.info("CONTROLLER - restored");
    }

    @Override
    protected void onMachinePaused() {

        // pause work?
        FMLLog.info("CONTROLLER - paused");
    }

    @Override
    protected void onMachineDisassembled() {

        FMLLog.info("CONTROLLER - disassembled");
    }


    @Override
    public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data) {

    }

    @Override
    protected void onAssimilate(MultiblockControllerBase assimilated) {

    }

    @Override
    protected void onAssimilated(MultiblockControllerBase assimilator) {

    }


    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine() {

        FMLLog.info("Controller.getMinimumNumberOfBlocksForAssembledMachine called");

        return 27;
    }

    @Override
    protected int getMaximumXSize() {

        return 3;
    }

    @Override
    protected int getMaximumZSize() {

        return 3;
    }

    @Override
    protected int getMaximumYSize() {

        return 3;
    }



    @Override
    protected boolean updateServer() {

        // consume energy
        this._active = this.tryToConsumeEnergy(RF_PER_OPERATION);

        return false;
    }

    @Override
    protected void updateClient() {

    }

    @Override
    public void writeToNBT(NBTTagCompound data) {

    }

    @Override
    public void readFromNBT(NBTTagCompound data) {

    }

    @Override
    public void formatDescriptionPacket(NBTTagCompound data) {

    }

    @Override
    public void decodeDescriptionPacket(NBTTagCompound data) {

    }

    private void lookupPorts() {

        this._outputPort = this._inputPort = null;
        this._powerPort = null;

        for (IMultiblockPart part : this.connectedParts) {

            if (part instanceof MightyFurnacePowerTileEntity)
                this._powerPort = (MightyFurnacePowerTileEntity)part;

            if (part instanceof MightyFurnaceIOPortTileEntity) {

                MightyFurnaceIOPortTileEntity io = (MightyFurnaceIOPortTileEntity)part;

                if (io.isInput())
                    this._inputPort = io;
                else
                    this._outputPort = io;
            }
        }
    }

    protected EnergyStorage getRFStorage() {

        if (null == this._rfStorage)
            this._rfStorage = new EnergyStorage(RF_CAPACITY, RF_PER_OPERATION * 2, RF_PER_OPERATION);

        return this._rfStorage;
    }

    protected boolean tryToConsumeEnergy(int amount) {

        if ((null == this._rfStorage) ||(amount != this._rfStorage.extractEnergy(amount, true)))
            return  false;

        this._rfStorage.extractEnergy(amount, false);
        return true;
    }

    private MightyFurnaceIOPortTileEntity _inputPort;
    private MightyFurnaceIOPortTileEntity _outputPort;
    private MightyFurnacePowerTileEntity _powerPort;
    private boolean _active;

    private EnergyStorage _rfStorage;

    private static final int RF_CAPACITY = 1000;
    private static final int RF_PER_OPERATION = 100;

}
