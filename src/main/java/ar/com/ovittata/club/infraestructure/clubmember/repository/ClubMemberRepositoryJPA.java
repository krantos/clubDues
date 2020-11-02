package ar.com.ovittata.club.infraestructure.clubmember.repository;

import ar.com.ovittata.club.application.exceptions.ClubMemberNotFoundException;
import ar.com.ovittata.club.domain.clubmembers.ClubMember;
import ar.com.ovittata.club.domain.clubmembers.ClubMemberRepository;
import ar.com.ovittata.club.infraestructure.clubmember.entity.ClubMemberJPA;
import ar.com.ovittata.club.infraestructure.clubmember.exceptions.DBException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubMemberRepositoryJPA implements ClubMemberRepository {

  private final JpaRepository<ClubMemberJPA, Long> repository;
  private ModelMapper modelMapper;

  @Autowired
  ClubMemberRepositoryJPA(JpaRepository<ClubMemberJPA, Long> repository) {
    this.repository = repository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public ClubMember findById(Long id) {
    ClubMemberJPA clubMemberJPA;
    try {
      clubMemberJPA = repository.findById(id).orElseThrow(() -> new ClubMemberNotFoundException(id));
    } catch (IllegalArgumentException exception) {
      throw new DBException(exception.getMessage());
    }
    modelMapper = new ModelMapper();
    return modelMapper.map(clubMemberJPA, ClubMember.class);
  }

  @Override
  public List<ClubMember> findAll() {
    List<ClubMemberJPA> clubMemberJPAS;
    try {
      clubMemberJPAS = repository.findAll();
    } catch (IllegalArgumentException exception) {
      throw new DBException(exception.getMessage());
    }
    return clubMemberJPAS
        .stream()
        .map(member -> modelMapper.map(member, ClubMember.class))
        .collect(Collectors.toList());
  }

  @Override
  public ClubMember save(ClubMember newMember) {
    ClubMemberJPA clubMember = modelMapper.map(newMember, ClubMemberJPA.class);
    ClubMemberJPA memberJPA;
    try {
      memberJPA = repository.save(clubMember);
    } catch (IllegalArgumentException exception) {
      throw new DBException(exception.getMessage());
    }
    return modelMapper.map(memberJPA, ClubMember.class);
  }

  @Override
  public void deleteById(Long id) {
    try {
      repository.deleteById(id);
    } catch (IllegalArgumentException exception) {
      throw new DBException(exception.getMessage());
    }
  }
}
