package com.example.Campus.Service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.NoSuchAttributeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Campus.Entity.*;
import com.example.Campus.Exception.IdNotFoundException;
import com.example.Campus.Repository.MindRepo;
import com.example.Campus.Repository.TrackRepo;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;




@Service
public class CampusService 
{
	@Autowired
	private MindRepo mr;
	
	@Autowired
	private TrackRepo tr;
	
	public Track createTrack(Track track) 
	{
//		String name=track.getTrackname();
//		try 
//		{
//			List<Mind> minds= getAllDetails(name);
//			track.setMinds(minds);
//		} 
//		catch (IdNotFoundException e) 
//		{
//			System.out.println(e.getMessage());
//		}
//		
		
		
		return tr.save(track);
	}
	
	public Mind createMind(Mind mind, int trackid) 
	{
		// TODO Auto-generated method stub
	
		int id;
		//List<Track> tracks =new ArrayList<Track>();
		List<Track> alltracks=tr.findAll();		//existing
		List<Mind> newmind=new ArrayList<Mind>();
		
		for(Track t:alltracks)
		{
			System.out.println("inside for loop");
			id=t.getTrackid();
			if(id==trackid)
			{
				newmind.add(mind);
				System.out.println("inside if ");
				//t.setMinds(newmind);
				
			}	
		}	
		return mr.save(mind);
	}

	public List<Mind> getAllDetails(String trackname) throws IdNotFoundException
	{
		
		
		
		int flag=0;
		String nameTrack="";
		List<Mind> mind =new ArrayList<Mind>();
		List<Mind> alltrackMinds=mr.findAll();		//existing
		for(Mind m:alltrackMinds)
		{
			
			nameTrack=m.getTname();
			if(nameTrack.equals(trackname))
			{
				mind.add(m);
				flag=1;
			
			}
		}	
		if(flag!=1)
			throw new IdNotFoundException("Trackname not found!");
		
		return mind;
		
	}
	
	public Track getTrackDetails(int trackid) throws IdNotFoundException
	{
		// TODO Auto-generated method stub
		int flag=0;
		List<Track> track =new ArrayList<Track>();
		tr.findAll().forEach(n ->track.add(n));
		for(Track t:track)
		{
			if(t.getTrackid()==trackid)
			{
				flag=1;
				return t;
			}
		}
		if(flag!=1)
			throw new IdNotFoundException("TrackID Not Found!");
		
		
		return null;
	}
	
	
	public Mind getMindDetails(int id) throws IdNotFoundException
	{
		// TODO Auto-generated method stub
		int flag=0;
		List<Mind> mind =new ArrayList<Mind>();
		mr.findAll().forEach(n ->mind.add(n));
		for(Mind m:mind)
		{
			if(m.getId()==id)
			{
				flag=1;
				return m;
			}
		}
		if(flag!=1)
			throw new IdNotFoundException("MindID Not Found!");
		
		
		return null;
	}

	public Mind updatePhoneMind(String phone, int id) throws IdNotFoundException
	{
		// TODO Auto-generated method stub
		int flag=0;
		List<Mind> minds =new ArrayList<Mind>();
		mr.findAll().forEach(n ->minds.add(n));
		
		for(Mind m:minds)
		{
			if(m.getId()==id)
			{
				
				m.setPhone(phone);
				mr.save(m);
				flag=1;
				return m;
			}
		}
		
		
		
		if(flag!=1)
			throw new IdNotFoundException("MindID not found!");
		
		return null;		
	}
	
	
	public Track updateStrengthTrack(int strength, int trackid) throws IdNotFoundException
	{
		// TODO Auto-generated method stub
		int flag=0;
		List<Track> track =new ArrayList<Track>();
		tr.findAll().forEach(n ->track.add(n));
		
		for(Track t:track)
		{
			if(t.getTrackid()==trackid)
			{
				
			
				t.setStrength(strength);
				tr.save(t);
				flag=1;
				return t;
			}
		}
		
		
		
		if(flag!=1)
			throw new IdNotFoundException("TrackID not found!");
		
		return null;
	}

	
	
	public Mind deleteMind(int id) throws IdNotFoundException
	{
		 
		
		
		 Mind mi=this.mr.findById(id);
		 mr.delete(mi);
				
		return mi;
		

	}

	public Track deleteTrack(int trackid) throws IdNotFoundException
	{
		// TODO Auto-generated method stub
		Track t=tr.findById(trackid);
		tr.delete(t);
		
		return t;
	}

	
	
	

	
	
}
