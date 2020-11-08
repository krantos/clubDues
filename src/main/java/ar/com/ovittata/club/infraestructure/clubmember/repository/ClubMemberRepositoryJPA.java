package ar.com.ovittata.club.infraestructure.clubmember.repository;

import ar.com.ovittata.club.application.exceptions.ClubMemberNotFoundException;
import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import ar.com.ovittata.club.domain.clubmembers.ClubMemberRepository;
import ar.com.ovittata.club.infraestructure.clubmember.entity.ClubMemberJPA;
import ar.com.ovittata.club.infraestructure.clubmember.exceptions.DBException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class ClubMemberRepositoryJPA implements ClubMemberRepository {

  private final JpaRepository<ClubMemberJPA, Long> repository;
  private Logger logger = LoggerFactory.getLogger(ClubMemberRepositoryJPA.class);
  private ModelMapper modelMapper;

  @Autowired
  ClubMemberRepositoryJPA(JpaRepository<ClubMemberJPA, Long> repository) {
    this.repository = repository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public ClubMember findById(Long id) {
    try {
      ClubMemberJPA clubMemberJPA = repository.findById(id).orElseThrow();
      return modelMapper.map(clubMemberJPA, ClubMember.class);
    } catch (NoSuchElementException exception) {
      throw new ClubMemberNotFoundException(id);
    } catch (PersistenceException exception) {
      logger.error(exception.getMessage());
      throw new DBException();
    }
  }

  @Override
  public List<ClubMember> findAll() {
    try {
      List<ClubMemberJPA> clubMemberJPAS = repository.findAll();
      return clubMemberJPAS
          .stream()
          .map(member -> modelMapper.map(member, ClubMember.class))
          .collect(Collectors.toList());
    } catch (PersistenceException exception) {
      logger.error(exception.getMessage());
      throw new DBException();
    }
  }

  @Override
  public ClubMember save(ClubMember newMember) {
    ClubMemberJPA newClubMember = modelMapper.map(newMember, ClubMemberJPA.class);
    try {
      ClubMemberJPA memberJPA = repository.save(newClubMember);
      return modelMapper.map(memberJPA, ClubMember.class);
    } catch (PersistenceException exception) {
      logger.error(exception.getMessage());
      throw new DBException();
    }
  }

  @Override
  public void deleteById(Long id) {
    try {
      repository.deleteById(id);
    } catch (PersistenceException exception) {
      logger.error(exception.getMessage());
      throw new DBException();
    }
  }
}
