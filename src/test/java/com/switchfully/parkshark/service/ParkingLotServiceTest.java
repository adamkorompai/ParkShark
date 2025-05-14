package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.*;
import com.switchfully.parkshark.domain.dtos.CreateParkingLotDto;
import com.switchfully.parkshark.repository.*;
import com.switchfully.parkshark.service.mappers.ParkingLotMappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class ParkingLotServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ParkingLotServiceTest.class);

    @Mock
    private ParkingLotRepository parkingLotRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private PostalCodeRepository postalCodeRepository;
    @Mock
    private DivisionRepository divisionRepository;

    private ParkingLotMappers mappers;

    @InjectMocks
    private ParkingLotService parkingLotService;

    private CreateParkingLotDto createParkingLotDto;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createParkingLotDto = createAParkingLotDto();

    }
    private Address createAnAddress_1() {
        Address addressObj = new Address();
        addressObj.setStreetName("Avenue de la poudrière");
        addressObj.setStreetNumber("114");
        return addressObj;
    }
    private Address createAnAddress_2() {
        Address addressObj = new Address();
        addressObj.setStreetName("Avenue de la reine");
        addressObj.setStreetNumber("4");
        return addressObj;
    }

    private Contact createAContact() {
        Contact contactObj = new Contact();
        contactObj.setName("John");
        contactObj.setEmail("john@gmail.com");
        contactObj.setPhoneNumber("1234567890");
        contactObj.setTelephoneNumber("0987654321");
        return contactObj;
    }
    private PostalCode createAPostalCode() {
        PostalCode postalCodeObj = new PostalCode();
        postalCodeObj.setCode("4000");
        postalCodeObj.setLabel("Liège");
        return postalCodeObj;
    }
    private PostalCode createAPostalCode_2() {
        PostalCode postalCodeObj = new PostalCode();
        postalCodeObj.setCode("1020");
        postalCodeObj.setLabel("Laeken");
        return postalCodeObj;
    }
    private Division getADivision() {

        Division div = new Division();
        div.setId(14L);
        div.setName("Bruxelles Capitale");
        div.setDirector("Mehdi Sellam");
        div.setOriginalName("Bxl");
        return div;

    }

    private CreateParkingLotDto createAParkingLotDto() {
        CreateParkingLotDto dto = new CreateParkingLotDto();
        dto.setName("Parking Central");
        dto.setCategory(ParkingCategory.UNDERGROUND); // suppose que c'est une enum
        dto.setCapacity(150);
        dto.setPricePerHour(2.50);
        dto.setStreetName("Rue du Centre");
        dto.setStreetNumber("10A");
        dto.setPostalCode("1000");
        dto.setPostalCodeLabel("Bruxelles");

        dto.setContact_name("Jean Dupont");
        //Todo je dois standardiser le format du numéro de téléphone
        dto.setContact_phoneNumber("+32 475 123 456");
        dto.setContact_telNumber("+32 2 123 4567");
        dto.setContact_email("jean.dupont@example.com");

        dto.setContact_streetName("Rue des Contacts");
        dto.setContact_streetNumber("22B");
        dto.setContact_postalCode("1000");
        dto.setContact_postalCodeLabel("Bruxelles");

        dto.setDivisionName("Bruxelles Capitale");
        return dto;
    }

    @Test
    void whenCreatingParkingLot_givingNameEmptyOrNull_thenThrowsBadRequestException() {
        createParkingLotDto.setName("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The name is required\"", exception.getMessage());
        createParkingLotDto.setName(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The name is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_givingCategoryNull_thenThrowsBadRequestException() {
        createParkingLotDto.setCategory(null);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"Category is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_NegativeOrZeroCapacity_thenThrowsBadRequestException() {
        createParkingLotDto.setCapacity(-1);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The capacity cannot be negative or zero\"", exception.getMessage());
        createParkingLotDto.setCapacity(0);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The capacity cannot be negative or zero\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_GivingNegativePricePerHour_thenThrowsBadRequestException() {
        createParkingLotDto.setPricePerHour(-1);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The price per hour cannot be negative\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_givingStreetNameEmptyOrNull_thenThrowsBadRequestException() {
        String fieldLabel = "street name";
        createParkingLotDto.setStreetName("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setStreetName(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_givingStreetNumberEmptyOrNull_thenThrowsBadRequestException() {
        String fieldLabel = "street number";
        createParkingLotDto.setStreetNumber("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        System.out.println(1);
        createParkingLotDto.setStreetNumber(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        System.out.println(2);
        createParkingLotDto.setStreetNumber("-30");
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " must be a positive number\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_PostalCodeEmptyOrNull_thenThrowsBadRequestException() {
        String fieldLabel = "postal code";
        createParkingLotDto.setPostalCode("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setPostalCode(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    //Todo : Check if the postal code and postal name fit
    // And add it to verification

    @Test
    void whenCreatingParkingLot_ContactNameEmptyOrNull_thenThrowsBadRequestException() {
        String fieldLabel = "contact name";
        createParkingLotDto.setContact_name("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_name(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_BothPhoneNumberAndMobilePhoneNumber_thenThrowsBadRequestException() {
        String fieldLabel_1 = " \"The contact mobile phone number or the contact fix phone";
        createParkingLotDto.setContact_phoneNumber("");
        createParkingLotDto.setContact_telNumber("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + fieldLabel_1 + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_phoneNumber(null);
        createParkingLotDto.setContact_telNumber(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + fieldLabel_1 + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_phoneNumber("");
        createParkingLotDto.setContact_phoneNumber(null);
        createParkingLotDto.setContact_telNumber("");
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + fieldLabel_1 + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_phoneNumber("");
        createParkingLotDto.setContact_telNumber(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + fieldLabel_1 + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_ContactEmailEmptyOrNull_thenThrowsBadRequestException() {
        String fieldLabel = "contact email";
        createParkingLotDto.setContact_email("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_email(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_ContactEmailIsBadFormat_thenThrowsIllegalArgumentException() {
        createParkingLotDto.setContact_email("abdozpe");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals("The contact email is invalid", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_ContactStreetName_thenThrowsBadRequestException() {
        String fieldLabel = "contact street name";
        createParkingLotDto.setContact_streetName("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_streetName(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_ContactStreetNumber_thenThrowsBadRequestException() {
        String fieldLabel = "contact street name";
        createParkingLotDto.setContact_streetName("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_streetName(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_ContactPostalCode_thenThrowsBadRequestException() {
        String fieldLabel = "contact postal code";
        createParkingLotDto.setContact_postalCode("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setContact_postalCode(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"The " + fieldLabel + " is required\"", exception.getMessage());
    }

    @Test
    void whenCreatingParkingLot_DivisionNameIsEmpty_thenThrowsBadRequestException() {
        String fieldLabel = " \"Division name";
        createParkingLotDto.setDivisionName("");
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + fieldLabel + " is required\"", exception.getMessage());
        createParkingLotDto.setDivisionName(null);
        exception = Assertions.assertThrows(ResponseStatusException.class, () -> parkingLotService.createParkingLot(createParkingLotDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST + fieldLabel + " is required\"", exception.getMessage());
    }


    @Test
    void whenAddressDoesNotExist_thenCreateItInDB() {
        PostalCode mockPostalCode = createAPostalCode();
        Address mockAddress = createAnAddress_1();

        // Simulation de l'absence de l'adresse dans la DB
        Mockito.when(addressRepository.getAddressesByStreetNameAndStreetNumberAndPostalCode(
                        mockAddress.getStreetName(), mockAddress.getStreetNumber(), mockPostalCode))
                .thenReturn(Optional.empty());

        // Simulation de la récupération du PostalCode
        Mockito.when(postalCodeRepository.getPostalCodeByLabel(mockPostalCode.getLabel()))
                .thenReturn(Optional.of(mockPostalCode));



        // Appel de la méthode réelle à tester
        parkingLotService.checkAddressOrCreate(mockAddress.getStreetName(), mockAddress.getStreetNumber(), mockPostalCode.getCode(), mockPostalCode.getLabel());

        // Vérification que save() a bien été appelé une fois pour enregistrer l'adresse
        Mockito.verify(addressRepository).save(Mockito.any());
    }
    @Test
    void whenAddressDoesExist_thenCreateItInDB() {
        PostalCode mockPostalCode = createAPostalCode();
        Address mockAddress = createAnAddress_1();

        // Simulation de l'absence de l'adresse dans la DB
        Mockito.when(addressRepository.getAddressesByStreetNameAndStreetNumberAndPostalCode(
                        mockAddress.getStreetName(), mockAddress.getStreetNumber(), mockPostalCode))
                .thenReturn(Optional.of(mockAddress));

        // Simulation de la récupération du PostalCode
        Mockito.when(postalCodeRepository.getPostalCodeByLabel(mockPostalCode.getLabel()))
                .thenReturn(Optional.of(mockPostalCode));

        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(mockAddress);



        // Appel de la méthode réelle à tester
        Address result = parkingLotService.checkAddressOrCreate(mockAddress.getStreetName(), mockAddress.getStreetNumber(), mockPostalCode.getCode(), mockPostalCode.getLabel());

        Assertions.assertEquals(mockAddress, result);
        // Vérification que save() a bien été appelé une fois pour enregistrer l'adresse
        Mockito.verify(addressRepository).save(Mockito.any());
    }

    @Test
    void whenCreatingParkingLot_WithExistingContact_thenSaveItInDB() {
        Division div = getADivision();
        Contact con = createAContact();
        createParkingLotDto.setDivisionName(div.getName());
        createParkingLotDto.setContact_email(con.getEmail());
        Mockito.when(divisionRepository.findByName(div.getName())).thenReturn(Optional.of(div));
        Mockito.when(contactRepository.getContactByEmail(con.getEmail())).thenReturn(Optional.of(con));
        parkingLotService.createParkingLot(createParkingLotDto);
        Mockito.verify(parkingLotRepository).save(Mockito.any());
    }

    @Test
    void whenCreatingParkingLot_WithNonExistantContact_thenCreateIt_ThenSaveItInDB() {
        Division div = getADivision();
        Contact con = createAContact();
        createParkingLotDto.setDivisionName(div.getName());
        createParkingLotDto.setContact_email(con.getEmail());
        Mockito.when(divisionRepository.findByName(div.getName())).thenReturn(Optional.of(div));
        Mockito.when(contactRepository.getContactByEmail(con.getEmail())).thenReturn(Optional.empty());
        parkingLotService.createParkingLot(createParkingLotDto);
        Mockito.verify(contactRepository).save(Mockito.any());
        Mockito.verify(parkingLotRepository).save(Mockito.any());
    }




}
