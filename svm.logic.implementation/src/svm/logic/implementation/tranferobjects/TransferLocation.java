package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.ILocation;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferLocation;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class TransferLocation implements ITransferLocation, IHasModel<ILocation> {


    private ILocation location;

    @Override
    public String getCountryCode() {
        return location.getCountryCode();
    }

    @Override
    public String getPostalCode() {
        return location.getPostalCode();
    }

    @Override
    public String getPlaceName() {
        return location.getPlaceName();
    }

    @Override
    public String getDistrict() {
        return location.getDistrict();
    }

    @Override
    public String getProvince() {
        return location.getProvince();
    }

    @Override
    public String getCommunity() {
        return location.getCommunity();
    }

    @Override
    public String getLatitude() {
        return location.getLatitude();
    }

    @Override
    public String getLongitude() {
        return location.getLongitude();
    }

    @Override
    public void setObject(Object o) {
        this.location = (ILocation) o;
    }

    @Override
    public ILocation getModel() {
        return this.location;
    }
}
