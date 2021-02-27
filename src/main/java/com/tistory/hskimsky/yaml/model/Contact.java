package com.tistory.hskimsky.yaml.model;

import java.util.Objects;

/**
 * @author hskimsky
 * @version 1.0
 * @since 2021-02-27
 */
public class Contact {

  private String type;
  private int number;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return number == contact.number && type.equals(contact.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, number);
  }

  @Override
  public String toString() {
    return "Contact{" +
      "type='" + type + '\'' +
      ", number=" + number +
      '}';
  }
}
