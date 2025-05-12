package com.switchfully.parkshark.domain.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class UserDtoTest {


    @Autowired
    private JacksonTester<CreateUserDTO> createUserJson;

    @Autowired
    private JacksonTester<UserDTO> userJson;

    @Test
    void testDeserializeCreateUserDTO() throws Exception {
        String json = """
                {
                "firstName": "a",
                "lastName": "b",
                "email": "a@b.co",
                "password": "password",
                "telephoneNumber": "0123456789",
                "mobileNumber": "0487654321",
                "streetName": "Nerviens",
                "streetNumber": "123",
                "postalCode": "1040",
                "city": "Brussels",
                "countryCode": "BE",
                "countryName": "Belgium",
                "plateNumber": "1-XYZ-123",
                "membershipLevel": "SILVER"
                }
                """;

        CreateUserDTO dto = createUserJson.parseObject(json);

        assertThat(dto.getFirstName()).isEqualTo("a");
        assertThat(dto.getMembershipLevel()).isEqualTo("SILVER");
        assertThat(dto.getCountryCode()).isEqualTo("BE");
        assertThat(dto.getPostalCode()).isEqualTo("1040");
    }

    @Test
    void testSerializeUserDTO() throws Exception {
        UserDTO user = new UserDTO(
                1L,
                "a",
                "b",
                "a@b.co",
                "0123456789",
                "0487654321",
                "2025-05-12 10:00",
                "1-ABC-999",
                "GOLD"
        );

        String expectedJson = """
        {
            "id": 1,
            "firstName": "a",
            "lastName": "b",
            "email": "a@b.co",
            "telephoneNumber": "0123456789",
            "mobileNumber": "0487654321",
            "registrationDate": "2025-05-12 10:00",
            "licensePlate": "1-ABC-999",
            "membershipLevel": "GOLD"
        }
        """;

        assertThat(userJson.write(user)).isEqualToJson(expectedJson);
    }

}
