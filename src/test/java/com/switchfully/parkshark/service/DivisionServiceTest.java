package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.dtos.CreateDivisionDto;
import com.switchfully.parkshark.domain.dtos.DivisionDto;
import com.switchfully.parkshark.repository.DivisionRepository;
import com.switchfully.parkshark.service.mappers.DivisionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DivisionServiceTest {

    @Mock
    private DivisionRepository divisionRepository;

    @InjectMocks
    private DivisionService divisionService;

    private DivisionMapper divisionMapper;
    private CreateDivisionDto createDivisionDto;
    private Division division;
    private Division parentDivision;
    private DivisionDto divisionDto;

    @BeforeEach
    void setUp() {
        divisionMapper = new DivisionMapper();

        // Setup parent division
        parentDivision = new Division("Parent Division", "Original Parent", "Parent Director", null);
        parentDivision.setId(1L);

        // Setup division with parent
        division = new Division("Test Division", "Original Test", "Test Director", parentDivision);
        division.setId(2L);

        // Setup create DTO
        createDivisionDto = new CreateDivisionDto();
        createDivisionDto.setName("Test Division");
        createDivisionDto.setOriginalName("Original Test");
        createDivisionDto.setDirector("Test Director");

        // Setup division DTO
        divisionDto = new DivisionDto(2L, "Test Division", "Original Test", "Test Director", 1L);
    }

    @Test
    void createDivision_WithoutParent_ReturnsDivisionDto() {
        // Arrange
        createDivisionDto.setParentDivisionId(null);
        Division divisionWithoutParent = new Division("Test Division", "Original Test", "Test Director", null);
        divisionWithoutParent.setId(2L);

        when(divisionRepository.save(any(Division.class))).thenReturn(divisionWithoutParent);

        // Act
        DivisionDto result = divisionService.createDivision(createDivisionDto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Test Division");
        assertThat(result.getOriginalName()).isEqualTo("Original Test");
        assertThat(result.getDirector()).isEqualTo("Test Director");
        assertThat(result.getParentDivisionId()).isNull();

        verify(divisionRepository, times(1)).save(any(Division.class));
    }

    @Test
    void createDivision_WithParent_ReturnsDivisionDto() {
        // Arrange
        createDivisionDto.setParentDivisionId(1L);

        when(divisionRepository.findById(1L)).thenReturn(Optional.of(parentDivision));
        when(divisionRepository.save(any(Division.class))).thenReturn(division);

        // Act
        DivisionDto result = divisionService.createDivision(createDivisionDto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Test Division");
        assertThat(result.getOriginalName()).isEqualTo("Original Test");
        assertThat(result.getDirector()).isEqualTo("Test Director");
        assertThat(result.getParentDivisionId()).isEqualTo(1L);

        verify(divisionRepository, times(1)).findById(1L);
        verify(divisionRepository, times(1)).save(any(Division.class));
    }

    @Test
    void createDivision_WithNonExistentParent_ThrowsException() {
        // Arrange
        createDivisionDto.setParentDivisionId(999L);

        when(divisionRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> divisionService.createDivision(createDivisionDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Parent division does not exist");

        verify(divisionRepository, times(1)).findById(999L);
        verify(divisionRepository, never()).save(any(Division.class));
    }

    @Test
    void getAllDivisions_ReturnsListOfDivisionDto() {
        // Arrange
        Division division2 = new Division("Second Division", "Original Second", "Second Director", null);
        division2.setId(3L);

        List<Division> divisions = Arrays.asList(division, division2);

        when(divisionRepository.findAll()).thenReturn(divisions);

        // Act
        List<DivisionDto> result = divisionService.getAllDivisions();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(2L);
        assertThat(result.get(0).getName()).isEqualTo("Test Division");
        assertThat(result.get(1).getId()).isEqualTo(3L);
        assertThat(result.get(1).getName()).isEqualTo("Second Division");

        verify(divisionRepository, times(1)).findAll();
    }

    @Test
    void getDivisionById_ExistingId_ReturnsDivisionDto() {
        // Arrange
        when(divisionRepository.findById(2L)).thenReturn(Optional.of(division));

        // Act
        DivisionDto result = divisionService.getDivisionById(2L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getName()).isEqualTo("Test Division");
        assertThat(result.getOriginalName()).isEqualTo("Original Test");
        assertThat(result.getDirector()).isEqualTo("Test Director");
        assertThat(result.getParentDivisionId()).isEqualTo(1L);

        verify(divisionRepository, times(1)).findById(2L);
    }

    @Test
    void getDivisionById_NonExistingId_ThrowsException() {
        // Arrange
        when(divisionRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> divisionService.getDivisionById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Division not found");

        verify(divisionRepository, times(1)).findById(999L);
    }
}