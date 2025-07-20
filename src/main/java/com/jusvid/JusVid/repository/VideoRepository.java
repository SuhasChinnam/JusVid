package com.jusvid.JusVid.repository;

import com.jusvid.JusVid.model.Person;
import com.jusvid.JusVid.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    List<Video> findByUploadedBy(Person person);

}

