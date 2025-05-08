package com.switchfully.parkshark.domain.dtos;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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
              "telephoneNumber": "0123456789",
              "mobileNumber": "0487654321",
              "addressId": 1,
              "plateNumber": "1-XYZ-123",
              "membershipLevel": "SILVER"
              }
              """;

        CreateUserDTO dto = createUserJson.parseObject(json);

        assertThat(dto.getFirstName()).isEqualTo("a");
        assertThat(dto.getAddressId()).isEqualTo(1);
        assertThat(dto.getMembershipLevel()).isEqualTo("SILVER");
    }

    @Test
    void testSerializeUserDTO() throws Exception {
        UserDTO dto = new UserDTO(
                1L,
                "Alice",
                "Smith",
                "alice@example.com",
                "0123456789",
                "0487654321",
                "2025-05-08 16:30",
                "1-XYZ-123",
                "SILVER"
        );

        String expected = """
              {
              "id": 1,
              "firstName": "Alice",
              "lastName": "Smith",
              "email": "alice@example.com",
              "telephoneNumber": "0123456789",
              "mobileNumber": "0487654321",
              "registrationDate": "2025-05-08 16:30",
              "licensePlate": "1-XYZ-123",
              "membershipLevel": "SILVER"
              }
              """;

//        assertThat(userJson.write(dto)).isEqualToJson(expected);
    }
}
