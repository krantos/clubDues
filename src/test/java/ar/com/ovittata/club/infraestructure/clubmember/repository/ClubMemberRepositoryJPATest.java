package ar.com.ovittata.club.infraestructure.clubmember.repository;

import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import ar.com.ovittata.club.infraestructure.clubmember.entity.ClubMemberJPA;
import ar.com.ovittata.club.infraestructure.clubmember.exceptions.DBException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

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
  void findById_DBFails_DbException_IsThrown() {
    when(mockRepository.findById(anyLong())).thenThrow(new PersistenceException());
    Throwable throwable = catchThrowable(() -> repositoryJPA.findById(5L));
    assertThat(throwable).isInstanceOfAny(DBException.class);
  }

  @Test
  void findAll_Returns_2Elements() {
    List<ClubMemberJPA> members = List.of(new ClubMemberJPA(), new ClubMemberJPA());
    when(mockRepository.findAll()).thenReturn(members);

    assertThat(repositoryJPA.findAll()).isInstanceOfAny(List.class);
    assertThat(repositoryJPA.findAll().size()).isEqualTo(2);
  }

  @Test
  void findAll_ReturnsEmptyList() {
    List<ClubMemberJPA> members = List.of();
    when(mockRepository.findAll()).thenReturn(members);

    assertThat(repositoryJPA.findAll()).isInstanceOfAny(List.class);
    assertThat(repositoryJPA.findAll()).isEmpty();
  }

  @Test
  void findAll_DBFailsThrowsDBException() {
    when(mockRepository.findAll()).thenThrow(new PersistenceException());
    Throwable throwable = catchThrowable(() -> repositoryJPA.findAll());

    assertThat(throwable).isOfAnyClassIn(DBException.class);
  }

  @Test
  void save_Returns_ClubMember() {
    ClubMember clubMember = new ClubMember();
    when(mockRepository.save(any(ClubMemberJPA.class))).thenReturn(new ClubMemberJPA());
    repositoryJPA.save(clubMember);
    verify(mockRepository, times(1)).save(any(ClubMemberJPA.class));
  }

  @Test
  void save_ThrowsDBException() {
    when(mockRepository.save(any())).thenThrow(new PersistenceException());
    Throwable throwable = catchThrowable(() -> repositoryJPA.save(new ClubMember()));
    assertThat(throwable).isInstanceOfAny(DBException.class);
  }

  @Test
  void deleteById() {
    repositoryJPA.deleteById(1L);
    verify(mockRepository, times(1)).deleteById(anyLong());
  }

  @Test
  void deleteById_ThrowsDBException() {
    doThrow(new PersistenceException()).when(mockRepository).deleteById(anyLong());
    Throwable throwable = catchThrowable(() -> repositoryJPA.deleteById(1L));
    assertThat(throwable).isInstanceOfAny(DBException.class);
  }
}
