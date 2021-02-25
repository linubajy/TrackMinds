package com.example.Campus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Campus.Entity.*;

public interface TrackRepo extends JpaRepository<Track, Integer>
{
	public Track findById(int trackid);
}
