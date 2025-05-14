//package com.switchfully.parkshark.api;
////
////
////import com.switchfully.parkshark.domain.Address;
////import com.switchfully.parkshark.domain.Division;
////import com.switchfully.parkshark.domain.ParkingCategory;
////import com.switchfully.parkshark.domain.ParkingLot;
////import com.switchfully.parkshark.domain.dtos.CreateParkingLotDto;
////import com.switchfully.parkshark.domain.dtos.ParkingLotDto;
////import com.switchfully.parkshark.service.ParkingLotService;
////import io.restassured.RestAssured;
////import io.restassured.module.mockmvc.RestAssuredMockMvc;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.Mockito;
////import org.mockito.MockitoAnnotations;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
////import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
////import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.boot.test.mock.mockito.MockBean;
////import org.springframework.boot.test.web.server.LocalServerPort;
////import org.springframework.http.MediaType;
////import org.springframework.test.web.servlet.MockMvc;
////
////import java.util.List;
////import static io.restassured.RestAssured.given;
////
////import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
////import static org.hamcrest.Matchers.equalTo;
////import static org.hamcrest.Matchers.is;
////import static org.mockito.Mockito.mock;
////import static org.mockito.Mockito.when;
////
////@WebMvcTest(ParkingLotController.class)
////public class ParkingLotControllerTest {
////
////    @LocalServerPort
////    private int port;
////
////
////    @Autowired
////    private MockMvc mockMvc;
////
////    @Mock
////    private ParkingLotService parkingLotService;
////
////    @BeforeEach
////    public void setUp() {
////        RestAssuredMockMvc.mockMvc(mockMvc);
////    }
////
////    @Test
////    void createParkingLot_returnsCreatedParkingLot() throws Exception {
////
////        CreateParkingLotDto inputDto = new CreateParkingLotDto();
////        inputDto.setName("Parking Central");
////        inputDto.setCategory(ParkingCategory.UNDERGROUND);
////        inputDto.setCapacity(150);
////        inputDto.setPricePerHour(2.5);
////        inputDto.setStreetName("Rue de la Loi");
////        inputDto.setStreetNumber("16");
////        inputDto.setPostalCode("1000");
////        inputDto.setPostalCodeLabel("Bruxelles");
////
////        inputDto.setContact_name("Jean Dupont");
////        inputDto.setContact_phoneNumber("0478123456");
////        inputDto.setContact_telNumber("023456789");
////        inputDto.setContact_email("contact@parkingcentral.be");
////
////        inputDto.setContact_streetName("Rue du Contact");
////        inputDto.setContact_streetNumber("5");
////        inputDto.setContact_postalCode("1050");
////        inputDto.setContact_postalCodeLabel("Ixelles");
////
////        inputDto.setDivisionName("Bruxelles Capitale");
////
////
////        ParkingLot expectedLot = new ParkingLot();
////        expectedLot.setName("Lot A");
////        expectedLot.setCategory(ParkingCategory.UNDERGROUND);
////        expectedLot.setAddress(new Address());
////        expectedLot.getAddress().setStreetNumber("123");
////        expectedLot.getAddress().setStreetName("Main St");
////        expectedLot.setCapacity(50);
////
////        Division expectedDivision = new Division();
////        expectedDivision.setName("Bruxelles");
////        expectedDivision.setOriginalName("Bruxelles");
////        expectedDivision.setDirector("Mehdi");
////
////        Mockito.when(parkingLotService.createParkingLot(inputDto)).thenReturn(expectedLot);
////
////        given()
////                .contentType(MediaType.APPLICATION_JSON_VALUE)
////                .body(inputDto)
////                .when()
////                .post("/create")
////                .then()
////                .statusCode(201)
////                .body("name", equalTo("Lot A"));
////
////        Mockito.verify(parkingLotService).createParkingLot(Mockito.any(CreateParkingLotDto.class));
////    }
////
////    @Test
////    void getAllParkingLots_returnsList() {
////        List<ParkingLotDto> lots = List.of(
////                new ParkingLotDto(),
////                new ParkingLotDto()
////        );
////        lots.get(0).setId(1L);
////        lots.get(0).setName("Lot A");
////        lots.get(1).setId(2L);
////        lots.get(1).setName("Lot B");
////
////
////        when(parkingLotService.getAllParkingLots()).thenReturn(lots);
////
////        given()
////                .baseUri("http://localhost:" + port + "/parking-lot")
////                .port(port)
////                .when()
////                .get("/get-all")
////                .then()
////                .statusCode(201)
////                .body("$.size()", is(2))
////                .body("[0].name", equalTo("Lot A"))
////                .body("[1].name", equalTo("Lot B"));
////
////        Mockito.verify(parkingLotService).getAllParkingLots();
////    }
////
////    @Test
////    void getParkingLotById_returnsCorrectLot() {
////        ParkingLot lot = new ParkingLot();
////        lot.setName("Lot A");
////        lot.setCategory(ParkingCategory.UNDERGROUND);
////        lot.setCapacity(500);
////        lot.setId(3L);
////
////        when(parkingLotService.getParkingLot(3L)).thenReturn(lot);
////
////        given()
////                .baseUri("http://localhost:" + port + "/parking-lot")
////                .when()
////                .get("/get/3")
////                .then()
////                .statusCode(201)
////                .body("name", equalTo("Lot C"))
////                .body("capacity", equalTo(60));
////
////        Mockito.verify(parkingLotService).getParkingLot(3L);
////    }
////}
////
//
//import com.switchfully.parkshark.domain.ParkingLot;
//import com.switchfully.parkshark.service.ParkingLotService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ParkingLotControllerTest {
//
//
//    @MockBean  // Spring will inject this mock
//    private ParkingLotService parkingLotService;
//
//    @Test
//    void createParkingLot_endpoint() throws Exception {
//        // 1. Setup mock response
//        ParkingLot mockResponse = new ParkingLot();
//        mockResponse.setName("Test Lot");
//        mockResponse.setId(1L);
//        when(parkingLotService.createParkingLot(any())).thenReturn(mockResponse);
//
//        // 2. Prepare JSON request
//        String jsonRequest = """
//                {
//                    "name": "Central Parking",
//                    "capacity": 100
//                    // add other required fields
//                }
//                """;
//
//        // 3. Execute and verify endpoint
//        mockMvc.perform(post("/parking-lots")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Test Lot"));
//    }
//}