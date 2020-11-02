package ar.com.ovittata.club.infraestructure.clubmember.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CLUB_MEMBERS")
public class ClubMemberJPA {

  private @Id
  @GeneratedValue
  Long id;
  private String name;
  private String lastName;
  private String memberNumber;
  public ClubMemberJPA() {
  }
  public ClubMemberJPA(String name, String lastName, String memberNumber) {
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
    ClubMemberJPA that = (ClubMemberJPA) o;
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
