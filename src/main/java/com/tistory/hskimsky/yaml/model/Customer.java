package com.tistory.hskimsky.yaml.model;

import java.util.List;
import java.util.Objects;

/**
 * @author hskimsky
 * @version 1.0
 * @since 2021-02-27
 */
public class Customer {

  private String firstName;
  private String lastName;
  private int age;
  private List<Contact> contactDetails;
  private Address homeAddress;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<Contact> getContactDetails() {
    return contactDetails;
  }

  public void setContactDetails(List<Contact> contactDetails) {
    this.contactDetails = contactDetails;
  }

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return age == customer.age && firstName.equals(customer.firstName) && lastName.equals(customer.lastName) && contactDetails.equals(customer.contactDetails) && homeAddress.equals(customer.homeAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, age, contactDetails, homeAddress);
  }

  @Override
  public String toString() {
    return "Customer{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", age=" + age +
      ", contactDetails=" + contactDetails +
      ", homeAddress=" + homeAddress +
      '}';
  }
}
