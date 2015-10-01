package zero.mods.testmod18.common.blocks.tile;

import net.minecraft.nbt.NBTTagCompound;
import zero.mods.testmod18.EntryPoint;
import zero.mods.zerocore.common.IModInstance;
import zero.mods.zerocore.common.blocks.ModTileEntity;

import java.util.Date;

public class TileDate extends ModTileEntity {

    public TileDate() {

        this._creationDate = null;
        this._nameOfUser = null;
    }

    public TileDate(Date creationDate) {

        this._creationDate = creationDate;
        this._nameOfUser = null;
    }

    @Override
    public IModInstance getMod() {

        return EntryPoint.getInstance();
    }

    public Date getCreationDate() {

        return this._creationDate;
    }

    public String getNameOfUser() {

        return this._nameOfUser;
    }

    public void setNameOfUser(String name) {

        if ((null == name) || name.isEmpty())
            throw new IllegalArgumentException("Invalid name!");

        this._nameOfUser = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        super.readFromNBT(compound);
        this._creationDate = new Date(compound.getLong("date"));
        this._nameOfUser = compound.getString("user");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);
        compound.setLong("date", null != this._creationDate ? this._creationDate.getTime() : 0L);
        compound.setString("user", null != this._nameOfUser ? this._nameOfUser : "");
    }

    private Date _creationDate;
    private String _nameOfUser;
}
