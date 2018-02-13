package pds.esibank.dataaccess.services;

import pds.esibank.models.shares.SharePrice;

import java.util.List;

/**
 * Created by maria on 16/01/2018.
 */
public interface SharePriceHistorisationInterface {

    List<SharePrice> getAllSharePrice();
}
