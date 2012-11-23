package svm.logic.implementation.transferobject;

import svm.domain.abstraction.modelInterfaces.ILocation;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferLocation;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class TransferLocation implements ITransferLocation, IHasModel<ILocation> {

    private ILocation location;
    private String countryCode;
    private String postalCode;
    private String placeName;
    private String district;
    private String province;
    private String community;
    private String latitude;
    private String longitude;

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getPlaceName() {
        return placeName;
    }

    @Override
    public String getDistrict() {
        return district;
    }

    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public String getCommunity() {
        return community;
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public void setObject(Object o) {
        this.location = (ILocation) o;
        this.countryCode = location.getCountryCode();
        this.postalCode = location.getPostalCode();
        this.placeName = location.getPlaceName();
        this.district = location.getDistrict();
        this.province = location.getProvince();
        this.community = location.getCommunity();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public ILocation getModel() {
        return this.location;
    }

    @Override
    public String toString() {
        return this.location.getPostalCode() + " " + this.location.getPlaceName() + " " + this.location.getCountryCode();
    }

}
