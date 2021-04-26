package org.example;

/**
 * Hello world!
 *
 */
public class Moim
{
    int maxNumberOfAttendees;
    int numberOfEnrollment;
    public boolean isEnrollementFull(){
        if(maxNumberOfAttendees == 0){
            return false;
        }
        if(numberOfEnrollment < maxNumberOfAttendees){
            return false;
        }
        return true;
    }
}
