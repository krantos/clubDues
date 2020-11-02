package ar.com.ovittata.club.domain.clubmembers;

import java.util.List;

public interface ClubMemberService {

  ClubMember findById(Long id);

  List<ClubMember> all();

  ClubMember save(ClubMember newMember);

  ClubMember update(ClubMember clubMember, Long id);

  void delete(Long id);
}
