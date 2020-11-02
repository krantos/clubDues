package ar.com.ovittata.club.infraestructure.clubmember.repository;

import ar.com.ovittata.club.infraestructure.clubmember.entity.ClubMemberJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaH2Repository extends JpaRepository<ClubMemberJPA, Long> {
}
