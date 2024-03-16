package co.com.sofka.api;

import co.com.sofka.model.Person;
import lombok.Builder;

@Builder(toBuilder = true)
public record PersonResponseDto (

     int id,
     String name,
     String gender,
     int age,
     String documentId,
     String address,
     String phoneNumber,
    boolean state)

{ public static PersonResponseDto from(Person person){
    return PersonResponseDto.builder()
            .id(person.getId())
            .name(person.getName())
            .gender(person.getGender())
            .age(person.getAge())
            .documentId(person.getDocumentId())
            .address(person.getAddress())
            .phoneNumber(person.getPhoneNumber())
            .state(person.isState())
            .build();
}
}
