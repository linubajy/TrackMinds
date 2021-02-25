package com.example.Campus.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.Campus.Entity.Mind;
import com.example.Campus.Entity.Track;
import com.example.Campus.Exception.IdNotFoundException;
import com.example.Campus.Service.CampusService;


@RequestMapping("/Campus")
@RestController
public class CampusController {
 

	@Autowired
	private CampusService campusservice;
	
	//create track
	@PostMapping("/addTrack")
	public ResponseEntity<?> createTrack(@RequestBody Track track )
	{
			Map<String,Object> map= new LinkedHashMap<>();
			try
			{
				Track t=campusservice.createTrack(track);
				map.put("Track added", t);
				map.put("message", "ok");
				return new ResponseEntity<>(map,HttpStatus.OK);
			}
			catch(Exception e)
			{
				System.out.println(e.getLocalizedMessage());
			}

			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
	}
	
	//create mind
	@PostMapping("/addMind/{trackid}")
	public ResponseEntity<?> createMind(@RequestBody Mind mind , @PathVariable(name="trackid") int trackid)
	{
		Map<String,Object> map= new LinkedHashMap<>();
		try 
		{
			Mind m=campusservice.createMind(mind,trackid);
			map.put("Mind added", m);
			map.put("message", "ok");
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}

		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/retrieveAllByTrackName/{trackName}")
	public ResponseEntity<?> getAllDetails(@PathVariable(name="trackName") String trackName) throws IdNotFoundException
	{
	
		Map<String,Object> map= new LinkedHashMap<>();
		
		try 
		{
			System.out.println("hi");
			List<Mind> m=campusservice.getAllDetails(trackName);
			System.out.println("post:"+trackName);
			map.put("All details", m);
			map.put("message", "ok");
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		catch(IdNotFoundException e)
		{
			System.out.println(e.getLocalizedMessage());
			map.put("Message","Trackname not found!");
			
		}

		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	
	
	//retrievebyTrackID
		@GetMapping("/retrieveByTrackID/{trackID}")
		public ResponseEntity<?> getTrackDetails(@PathVariable(name="trackID") int trackid) throws IdNotFoundException
		{
		
			Map<String,Object> map= new LinkedHashMap<>();
			try 
			{
				Track t=campusservice.getTrackDetails(trackid);
				map.put("Track details", t);
				map.put("message", "ok");
				return new ResponseEntity<>(map,HttpStatus.OK);
			}
			catch(IdNotFoundException e)
			{
				System.out.println(e.getLocalizedMessage());
				map.put("Message","ID not found!");
				
			}

			
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}
	
		//retrievebyMindID
				@GetMapping("/retrieveByMindID/{MindID}")
				public ResponseEntity<?> getAllDetailsMind(@PathVariable(name="MindID") int id) throws IdNotFoundException
				{
				
					Map<String,Object> map= new LinkedHashMap<>();
					try 
					{
						Mind m=campusservice.getMindDetails(id);
						map.put("Mind details", m);
						map.put("message", "ok");
						return new ResponseEntity<>(map,HttpStatus.OK);
					}
					catch(IdNotFoundException e)
					{
						System.out.println(e.getLocalizedMessage());
						map.put("Message","ID not found!");
						
					}

					
					return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
				}
	
		
		
		
		
		
		
		
	//update mind phone
	@PutMapping("/updateMindPhone/{id}")
	public ResponseEntity<?> updatePhoneMind(@RequestBody String phone,@PathVariable(name="id") int id) throws IdNotFoundException
	{
		Map<String,Object> map= new LinkedHashMap<>();
		try {
			Mind m=campusservice.updatePhoneMind(phone,id);
			map.put("updated", m);
			map.put("message", "updated phone");
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		catch(IdNotFoundException e)
		{
			System.out.println(e.getLocalizedMessage());
			map.put("Message","ID not found!");
		}
		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
	}
	
	//update track strength 
		@PutMapping("/updateTrackStrength/{trackid}")
	public ResponseEntity<?> updateStrengthTrack(@RequestBody int strength,@PathVariable(name="trackid") int trackid) throws IdNotFoundException
	{
		Map<String,Object> map= new LinkedHashMap<>();
		try {
			Track track=campusservice.updateStrengthTrack(strength,trackid);
			map.put("updated", track);
			map.put("message", "updated strength");
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		catch(IdNotFoundException e)
		{
			System.out.println(e.getLocalizedMessage());
			map.put("Message","ID not found!");
		}
		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
	}
	
	//delete mind id
	@DeleteMapping("/deleteMindByID/{id}")
	public ResponseEntity<?> deleteMind(@PathVariable(name="id") int id)
	{
		
		Map<String,Object> map= new LinkedHashMap<>();
		try {
			Mind m=campusservice.deleteMind(id);
			
			map.put("Message","Deleted mind");
			return new ResponseEntity<>(map,HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}

		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
	}
	
	
	//delete track
	@DeleteMapping("/deleteTrackByID/{trackid}")
	public ResponseEntity<?> deleteTrack(@PathVariable(name="trackid") int trackid)
	{
		
		Map<String,Object> map= new LinkedHashMap<>();
		try {
			Track t=campusservice.deleteTrack(trackid);
			map.put("Message","Deleted track");
			return new ResponseEntity<>(map,HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}

		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
	}
	
}
