package com.switchfully.parkshark.api;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.PostalCode;
import com.switchfully.parkshark.domain.dtos.CreateUserDTO;
import com.switchfully.parkshark.domain.dtos.UserDTO;
import com.switchfully.parkshark.domain.license_plate.CountryCode;
import com.switchfully.parkshark.domain.license_plate.LicensePlate;
import com.switchfully.parkshark.domain.membership.MembershipLevel;
import com.switchfully.parkshark.domain.membership.MembershipType;
import com.switchfully.parkshark.domain.user.User;
import com.switchfully.parkshark.domain.user.UserRole;
import com.switchfully.parkshark.repository.AddressRepository;
import com.switchfully.parkshark.repository.PostalCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.CountryCodeRepository;
import com.switchfully.parkshark.repository.license_plate_repositories.LicensePlateRepository;
import com.switchfully.parkshark.repository.user_repositories.MembershipLevelRepository;
import com.switchfully.parkshark.repository.user_repositories.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Base64;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private MembershipLevelRepository membershipLevelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LicensePlateRepository licensePlateRepository;

    @Autowired
    private CountryCodeRepository countryCodeRepository;

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @LocalServerPort
    private int port;

    private final String MANAGER_EMAIL = "linda.green@example.com";
    private final String MANAGER_PASSWORD = "password";


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        membershipLevelRepository.saveAll(List.of(
                new MembershipLevel(MembershipType.BRONZE, 0, 0, 4),
                new MembershipLevel(MembershipType.SILVER, 10, 20, 6),
                new MembershipLevel(MembershipType.GOLD, 40, 30, 24)
        ));

        PostalCode postcode = postalCodeRepository.save(new PostalCode("1060", "Brussels"));
        Address address = addressRepository.save(new Address("Rue Manager", "1", postcode));

        CountryCode countryCode = countryCodeRepository.save(new CountryCode("BE", "Belgium"));
        LicensePlate licensePlate = licensePlateRepository.save(new LicensePlate("M-ANA-GER", countryCode));

        MembershipLevel bronze = membershipLevelRepository.findById(MembershipType.BRONZE).orElseThrow();

        User manager = new User(
                "Linda",
                "Green",
                "linda.green@example.com",
                "password",
                "02-000000",
                "0499-000000",
                address,
                licensePlate,
                bronze
        );
        manager.setRole(UserRole.MANAGER);

        userRepository.save(manager);
    }

    private String basicAuth(String email, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
    }

    @Test
    void testCreateUserAndGetUserListWithManager() {
        String managerAuth = basicAuth(MANAGER_EMAIL, MANAGER_PASSWORD);

        CreateUserDTO memberDTO = new CreateUserDTO(
                "h2", "h2", "h2@h2.com", "password",
                "027455436h2", "047658345h2", "1-RTC-SVGh2", "BRONZE",
                "Rue Haute", "32", "1000", "Brussels", "BE", "Belgium"
        );

        given()
                .contentType(ContentType.JSON)
                .body(memberDTO)
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("email", equalTo("h2@h2.com"))
                .body("firstName", equalTo("h2"));

        given()
                .header("Authorization", managerAuth)
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    void testGetUserByIdWithManager() {
        String managerAuth = basicAuth(MANAGER_EMAIL, MANAGER_PASSWORD);

        CreateUserDTO memberDTO = new CreateUserDTO(
                "h2", "h2", "h2@h2.com", "password",
                "027455436h2", "047658345h2", "1-RTC-SVGh2", "BRONZE",
                "Rue Haute", "32", "1000", "Brussels", "BE", "Belgium"
        );

        UserDTO createdUser = given()
                .contentType(ContentType.JSON)
                .body(memberDTO)
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDTO.class);

        given()
                .header("Authorization", managerAuth)
                .when()
                .get("/users/" + createdUser.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("email", equalTo("h2@h2.com"))
                .body("firstName", equalTo("h2"));
    }

    @Test
    void testGetAllUsersWithNonManagerFails() {
        String memberAuth = basicAuth("wrong@email.com", "wrongpass");

        given()
                .header("Authorization", memberAuth)
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
