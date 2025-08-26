package br.com.treinow.controller;


import br.com.treinow.dtos.AddressDto;
import br.com.treinow.responsedto.AddressResponseDto;
import br.com.treinow.models.entities.AddressEntity;
import br.com.treinow.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADDRESS_CREATE')")
    public AddressEntity createAddress(@RequestBody @Valid AddressDto addressDto){
        var createdAddress = addressService.createAddress(addressDto);
        return createdAddress;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    public List<AddressEntity> getAllAddress(){
        return addressService.getAllAddress();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    public ResponseEntity<Object> getAddressById(@PathVariable(value = "id") UUID id){
        return addressService.findById(id).<ResponseEntity<Object>>map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found please verify the ID"));
    }


    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ADDRESS_READ')")
    public ResponseEntity<Object> getAddressByStreet(@RequestParam String street){
        List<AddressEntity> addresses = addressService.findByStreet(street);
        List<AddressResponseDto> response = addresses.stream().map(address -> new AddressResponseDto(
                address.getId(), address.getStreet(), address.getNumber(), address.getCity(), address.getCity(), address.getNeighborhood()
        )).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADDRESS_UPDATE')")
    public ResponseEntity<Object> updatedAddress(@PathVariable UUID id,
                                                 @RequestBody @Valid AddressDto addressDto){
        try{
            var addressUpdated = addressService.updateAddress(id, addressDto);
            return ResponseEntity.status(HttpStatus.OK).body(addressUpdated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found please verify the ID");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MANAGE_STAFF')")
    public ResponseEntity<Object> deleteAddress(@PathVariable UUID id){
        try{
            addressService.deleteAddress(id);
            return ResponseEntity.status(HttpStatus.OK).body("Address deleted sucessfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found please verify the ID");
        }
    }
}
