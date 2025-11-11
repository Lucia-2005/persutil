package net.ausiasmarch.persutil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.persutil.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long>{
    
}
