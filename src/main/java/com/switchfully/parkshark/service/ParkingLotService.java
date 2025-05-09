package com.switchfully.parkshark.service;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.Contact;
import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.dtos.CreateParkingLotDto;
import com.switchfully.parkshark.domain.dtos.ParkingLotDto;
import com.switchfully.parkshark.repository.AddressRepository;
import com.switchfully.parkshark.repository.ContactRepository;
import com.switchfully.parkshark.repository.ParkingLotRepository;
import com.switchfully.parkshark.repository.PostalCodeRepository;
import com.switchfully.parkshark.service.mappers.ParkingLotMappers;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
@Transactional
public class ParkingLotService {

    private static final Logger log = LoggerFactory.getLogger(ParkingLotService.class);
    private final ParkingLotRepository parkingLotRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final PostalCodeRepository postalCodeRepository;
    private final ParkingLotMappers mappers;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ContactRepository contactRepository, AddressRepository addressRepository, PostalCodeRepository postalCodeRepository, ParkingLotMappers mappers) {
        this.parkingLotRepository = parkingLotRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.postalCodeRepository = postalCodeRepository;
        this.mappers = mappers;
    }

    public ParkingLot createParkingLot(CreateParkingLotDto dto) {

        parkingLotCreationValidation(dto);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(dto.getName());
        parkingLot.setCapacity(dto.getCapacity());
        parkingLot.setCategory(dto.getCategory());
        parkingLot.setPricePerHour(dto.getPricePerHour());
        contactRepository.getContactByEmail(dto.getContact_email()).ifPresentOrElse(
                parkingLot::setContact,
                () -> {
                    Contact obj = new Contact();
                    obj.setEmail(dto.getContact_email());
                    obj.setName(dto.getContact_name());
                    obj.setPhoneNumber(dto.getContact_phoneNumber());
                    obj.setTelephoneNumber(dto.getContact_telNumber());
                    obj.setAddress(checkAddressOrCreate(dto.getContact_streetName(), dto.getContact_streetName(), dto.getContact_postalCode(), dto.getContact_postalCodeLabel()));
                    contactRepository.save(obj);
                    parkingLot.setContact(obj);
                }
        );
        parkingLot.setAddress(checkAddressOrCreate(dto.getStreetName(), dto.getStreetNumber(), dto.getPostalCode(), dto.getContact_postalCodeLabel()));

        return parkingLotRepository.save(parkingLot);

    }

    public ParkingLot getParkingLot(Long id) {
        return parkingLotRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking lot with id " + id + " not found")
        );
    }

    public List<ParkingLotDto> getAllParkingLots() {
        ArrayList<ParkingLot> parkingLots = (ArrayList<ParkingLot>) parkingLotRepository.findAll();

        return parkingLots.stream().map(
                mappers::toDto
        ).toList();
    }

    private void parkingLotCreationValidation(CreateParkingLotDto dto) {
        log.debug("Parking lot creation validation" + 1 );
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name is required");
        }
        log.debug("Parking lot creation validation" + 2);

        if (dto.getCapacity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The capacity cannot be negative or zero");
        }
        log.debug("Parking lot creation validation" + 3 );

        if (dto.getPricePerHour() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The price per hour cannot be negative");
        }
        log.debug("Parking lot creation validation" + 4 );

        if (dto.getStreetName() == null || dto.getStreetName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The street name is required");
        }
        log.debug("Parking lot creation validation" + 5 );

        if (dto.getStreetNumber() == null || dto.getStreetNumber().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The street number is required");
        }
        log.debug("Parking lot creation validation" + 6 );

        if (dto.getPostalCode() == null || dto.getPostalCode().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The postal code is required");
        }
        log.debug("Parking lot creation validation" + 7 );

        if (dto.getContact_name() == null || dto.getContact_name().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contact name is required");
        }
        log.debug("Parking lot creation validation" + dto.getContact_phoneNumber() + " " + dto.getContact_telNumber() );

        if ((dto.getContact_phoneNumber() == null || dto.getContact_phoneNumber().isEmpty()) &&
                (dto.getContact_telNumber() != null && !dto.getContact_telNumber().isEmpty()) ||
                (dto.getContact_telNumber() == null || dto.getContact_telNumber().isEmpty()) &&
                        (dto.getContact_phoneNumber() != null && !dto.getContact_phoneNumber().isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contact mobile phone number or the contact fix phone is required");
        }
        log.debug("Parking lot creation validation" +  9 );

        if (dto.getContact_email() == null || dto.getContact_email().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contact email is required");
        }
        log.debug("Parking lot creation validation" + 10 );

        emailFormatValidation(dto.getContact_email());
        log.debug("Parking lot creation validation" + 11 );

        if (dto.getContact_streetName() == null || dto.getContact_streetName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contact street name is required");
        }
        log.debug("Parking lot creation validation" + 12 );

        if (dto.getContact_streetNumber() == null || dto.getContact_streetNumber().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contact street number is required");
        }
        log.debug("Parking lot creation validation" + 13 );

        if (dto.getContact_postalCode() == null || dto.getContact_postalCode().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contact postal code is required");
        }
        log.debug("Parking lot creation validation" + 14 );


    }

    private void emailFormatValidation(String contactEmail) {

        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(contactEmail).matches()) {
            throw new IllegalArgumentException("The contact email is invalid");
        }
    }

    //Todo : Ajouter une erreur au cas ou le save ne se fait pas
    private PostalCode checkPostalCodeOrCreate(String postalCode, String postalCodeLabel) {
        return postalCodeRepository.getPostalCodeByCode(postalCode).orElseGet(
                () -> {
                    PostalCode postalCodeObj = new PostalCode();
                    postalCodeObj.setLabel(postalCodeLabel);
                    postalCodeObj.setCode(postalCode);
                    return postalCodeRepository.save(postalCodeObj);
                }

        );
    }

    private Address checkAddressOrCreate(String streetName, String streetNumber, String postalCode, String postalCodeLabel) {

        PostalCode postal = checkPostalCodeOrCreate(postalCode, postalCodeLabel);

        return addressRepository.getAddressesByStreetNameAndStreetNumberAndPostalCode(streetName, streetNumber, postal).orElseGet(
                () -> {
                    Address addressObj = new Address();
                    addressObj.setPostalCode(postal);
                    addressObj.setStreetName(streetName);
                    addressObj.setStreetNumber(streetNumber);
                    return addressRepository.save(addressObj);
                }
        );

    }
}
