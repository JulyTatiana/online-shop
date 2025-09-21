package com.solvd.shop.model;

import com.solvd.shop.interfaces.Identifiable;

public class Customer extends User implements Identifiable<Long> {

    private Address address;

    private Long id;

    public Customer(String name, Address address, int priority) {
        super(priority);
        this.name = name;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int getPriority(){
        return 2;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', address=" + address + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}
