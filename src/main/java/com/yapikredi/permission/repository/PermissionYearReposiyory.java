package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PermissionYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionYearReposiyory extends JpaRepository<PermissionYear, Long> {

    @Query("select p from PermissionYear p where  p.startYear <=:year")
    List<PermissionYear> getPermissionYearListRangeBy(@Param("year") Integer year);

}
