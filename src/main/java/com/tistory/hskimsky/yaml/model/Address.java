package com.tistory.hskimsky.yaml.model;

import java.util.Objects;

/**
 * @author hskimsky
 * @version 1.0
 * @since 2021-02-27
 */
public class Address {

  private String line;
  private String city;
  private String state;
  private Integer zip;

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Integer getZip() {
    return zip;
  }

  public void setZip(Integer zip) {
    this.zip = zip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return line.equals(address.line) &&
      city.equals(address.city) &&
      state.equals(address.state) &&
      zip.equals(address.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(line, city, state, zip);
  }

  @Override
  public String toString() {
    return "Address{" +
      "line='" + line + '\'' +
      ", city='" + city + '\'' +
      ", state='" + state + '\'' +
      ", zip=" + zip +
      '}';
  }
}
