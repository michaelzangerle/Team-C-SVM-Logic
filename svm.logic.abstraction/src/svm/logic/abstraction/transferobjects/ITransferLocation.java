package svm.logic.abstraction.transferobjects;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public interface ITransferLocation extends ITransfer {

    String getCountryCode();

    String getPostalCode();

    String getPlaceName();

    String getDistrict();

    String getProvince();

    String getCommunity();

    String getLatitude();

    String getLongitude();
}
