package ar.com.ovittata.club.domain.clubmembers;

import java.util.Objects;

public class ClubMember {

  Long id;
  private String name;
  private String lastName;
  private String memberNumber;

  public ClubMember() {
  }

  public ClubMember(String name, String lastName, String memberNumber) {
    this.name = name;
    this.lastName = lastName;
    this.memberNumber = memberNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMemberNumber() {
    return memberNumber;
  }

  public void setMemberNumber(String memberNumber) {
    this.memberNumber = memberNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClubMember that = (ClubMember) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "ClubMember{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", lastName='" + lastName + '\'' +
        ", memberNumber='" + memberNumber + '\'' +
        '}';
  }
}
