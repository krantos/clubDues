package ar.com.ovittata.club.domain.clubmembers;

import ar.com.ovittata.club.application.exceptions.ClubMemberNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ClubMemberServiceImpl implements ClubMemberService {

  private final ClubMemberRepository clubMemberRepository;

  public ClubMemberServiceImpl(ClubMemberRepository clubMemberRepository) {
    this.clubMemberRepository = clubMemberRepository;
  }

  @Override
  public ClubMember findById(Long id) {
    return clubMemberRepository.findById(id);
  }

  @Override
  public List<ClubMember> all() {
    return clubMemberRepository.findAll();
  }

  @Override
  public ClubMember save(ClubMember newMember) {
    return clubMemberRepository.save(newMember);
  }

  @Override
  public ClubMember update(ClubMember newClubMember, Long id) {
    ClubMember clubMember = clubMemberRepository.findById(id);
    clubMember.setName(newClubMember.getName());
    clubMember.setLastName(newClubMember.getLastName());
    clubMember.setMemberNumber(newClubMember.getMemberNumber());
    return clubMemberRepository.save(clubMember);
  }

  @Override
  public void delete(Long id) {
    try {
      clubMemberRepository.deleteById(id);
    } catch (EmptyResultDataAccessException exception) {
      throw new ClubMemberNotFoundException(id);
    }
  }
}
