package ar.com.ovittata.club.infraestructure.clubmember.repository;

import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import ar.com.ovittata.club.infraestructure.clubmember.entity.ClubMemberJPA;
import ar.com.ovittata.club.infraestructure.clubmember.exceptions.DBException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@MockitoSettings
class ClubMemberRepositoryJPATest {

  @Mock
  JpaH2Repository mockRepository;

  @InjectMocks
  ClubMemberRepositoryJPA repositoryJPA;

  @Test
  void findById() {
    ClubMemberJPA member = new ClubMemberJPA("Ron", "Lando", "983M");
    when(mockRepository.findById(anyLong())).thenReturn(Optional.of(member));

    assertThat(repositoryJPA.findById(1L)).isOfAnyClassIn(ClubMember.class);
  }

  @Test
  void findByIdFailsAndThrowsDBException() {

    when(mockRepository.findById(anyLong())).thenThrow(IllegalArgumentException.class);
    Throwable throwable = catchThrowable(() -> repositoryJPA.findById(1L));
    assertThat(throwable).isOfAnyClassIn(DBException.class);
  }

  @Test
  void findAll() {
    List<ClubMemberJPA> members = List.of(new ClubMemberJPA(), new ClubMemberJPA());
    when(mockRepository.findAll()).thenReturn(members);

    assertThat(repositoryJPA.findAll()).isInstanceOfAny(List.class);
    assertThat(repositoryJPA.findAll().size()).isEqualTo(2);

  }

  @Test
  void findAllReturnsEmptyList() {
    List<ClubMemberJPA> members = List.of();
    when(mockRepository.findAll()).thenReturn(members);

    assertThat(repositoryJPA.findAll()).isInstanceOfAny(List.class);
    assertThat(repositoryJPA.findAll().size()).isZero();
  }

  @Test
  void findAllDBFailsThrowsDBException() {
    when(mockRepository.findAll()).thenThrow(new IllegalArgumentException());
    Throwable throwable = catchThrowable(() -> repositoryJPA.findAll());
    assertThat(throwable).isOfAnyClassIn(DBException.class);
  }

//  @Test
//  void save() {
//  }
//
//  @Test
//  void deleteById() {
//  }
}