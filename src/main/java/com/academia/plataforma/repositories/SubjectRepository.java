package com.academia.plataforma.repositories;

import org.springframework.stereotype.Repository;
import com.academia.plataforma.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>
{

}
