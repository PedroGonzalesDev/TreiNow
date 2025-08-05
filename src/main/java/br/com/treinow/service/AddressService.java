package br.com.treinow.service;

import br.com.treinow.dtos.AddressDto;
import br.com.treinow.models.entities.AddressEntity;
import br.com.treinow.repositories.jpa.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;
    
    public AddressEntity createAddress(@Valid AddressDto addressDto){
        var addressEntity = new AddressEntity();
        BeanUtils.copyProperties(addressDto, addressEntity);
        return addressRepository.save(addressEntity);
    }
    
    public List<AddressEntity> getAllAddress(){
        return addressRepository.findAll();
    }

    public Optional<AddressEntity> findById(UUID id){
        return addressRepository.findById(id);
    }

    public List<AddressEntity> findByStreet(String street){
        return addressRepository.findByStreetContainingIgnoreCase(street);
    }

    public AddressEntity updateAddress (UUID id, @Valid AddressDto addressDto){
        var address = addressRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(addressDto, address, "id");
        return addressRepository.save(address);
    }

    public void deleteAddress(UUID id){
        var address = addressRepository.findById(id).orElseThrow();
        addressRepository.delete(address);
    }
}
