package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Member {
    private String name;
    private int age;
    public boolean isSameAge(Member member){
        return this.age == member.age;
    }
}
