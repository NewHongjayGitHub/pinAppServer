package com.yangkw.pin.service;

import com.google.common.base.Preconditions;
import com.yangkw.pin.domain.address.AddressDO;
import com.yangkw.pin.domain.address.Dot;
import com.yangkw.pin.domain.address.GeoAddress;
import com.yangkw.pin.domain.address.PublishResult;
import com.yangkw.pin.infrastructure.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类AddressService.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Service
public class AddressService {
    @Autowired

    private AddressRepository addressRepository;

    public PublishResult publish(GeoAddress start, GeoAddress end) {
        PublishResult result = new PublishResult();
        Integer startId = solve(start, false);
        Integer endId = solve(end, true);
        result.setStartId(startId);
        result.setEndId(endId);
        return result;
    }

    public GeoAddress queryGeoAddress(Integer id) {
        AddressDO addressDO = addressRepository.findById(id);
        Preconditions.checkState(addressDO != null, "address query null");
        return assemble(addressDO);
    }

    private Integer solve(GeoAddress start, Boolean isEnd) {
        AddressDO oldDo = addressRepository.find(start.getDot());
        Integer startId;
        if (oldDo == null) {
            AddressDO addressDO = assemble(start, isEnd);
            addressRepository.insert(addressDO);
            startId = addressDO.getId();
        } else {
            addressRepository.addPoint(oldDo.getId());
            startId = oldDo.getId();
        }
        return startId;
    }

    private AddressDO assemble(GeoAddress address, Boolean isEnd) {
        AddressDO addressDO = new AddressDO();
        addressDO.setName(address.getName());
        addressDO.setAddress(address.getAddress());
        addressDO.setLatitude(address.getDot().getLatitude());
        addressDO.setLongitude(address.getDot().getLongitude());
        addressDO.setType(isEnd);
        return addressDO;
    }

    private GeoAddress assemble(AddressDO addressDO) {
        GeoAddress geoAddress = new GeoAddress();
        geoAddress.setName(addressDO.getName());
        geoAddress.setAddress(addressDO.getAddress());
        geoAddress.setDot(new Dot(addressDO.getLatitude(), addressDO.getLongitude()));
        return geoAddress;

    }
}
