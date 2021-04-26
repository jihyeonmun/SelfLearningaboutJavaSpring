package org.example;

import org.junit.Assert;
import org.junit.Test;

public class MemberTest {
    @Test
    public void getterSetter(){
        Member member = new Member();
        member.setName("jihyeon");

        Assert.assertEquals(member.getName(),"jihyeon");
    }
}
