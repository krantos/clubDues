package ar.com.ovittata.club.domain.clubmembers;

import java.util.List;

public interface ClubMemberRepository {

  ClubMember findById(Long id);

  List<ClubMember> findAll();

  ClubMember save(ClubMember newMember);

  void deleteById(Long id);
}
