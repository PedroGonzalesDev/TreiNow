package br.com.treinow.service;

import br.com.treinow.dtos.MuscleGroupDto;
import br.com.treinow.models.entities.MuscleGroupEntity;
import br.com.treinow.repositories.jpa.MuscleGroupRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MuscleGroupService {

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    public MuscleGroupEntity createMuscleGroup(@Valid MuscleGroupDto muscleGroupDto){
        var muscleGroupEntity = new MuscleGroupEntity();
        BeanUtils.copyProperties(muscleGroupDto, muscleGroupEntity);
        return muscleGroupRepository.save(muscleGroupEntity);
    }

    public List<MuscleGroupEntity> getAllMuscleGroup(){
        return muscleGroupRepository.findAll();
    }

    public Optional<MuscleGroupEntity> findMuscleGroupById(UUID id){
        return muscleGroupRepository.findById(id);
    }

    public MuscleGroupEntity updateMuscleGroup(UUID id, @Valid MuscleGroupDto muscleGroupDto){
        var muscleGroup = muscleGroupRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(muscleGroupDto, muscleGroup, "id");
        return muscleGroupRepository.save(muscleGroup);
    }

    public void deleteMuscleGroup(UUID id){
        var muscleGroup = muscleGroupRepository.findById(id).orElseThrow();
        muscleGroupRepository.delete(muscleGroup);
    }
}
