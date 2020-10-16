package fr.unice.polytech.si4.conception.l;

import java.util.Objects;

public class AnonymousCustomer {

    private String phoneNumber;

    public AnonymousCustomer(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void makeOrder(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnonymousCustomer)) return false;
        AnonymousCustomer that = (AnonymousCustomer) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
