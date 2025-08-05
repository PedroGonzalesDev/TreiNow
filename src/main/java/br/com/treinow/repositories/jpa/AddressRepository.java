package br.com.treinow.repositories.jpa;

import br.com.treinow.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {

    List<AddressEntity> findByStreetContainingIgnoreCase(String street);
}
