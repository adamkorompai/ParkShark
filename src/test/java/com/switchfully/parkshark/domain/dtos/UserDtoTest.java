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
}
