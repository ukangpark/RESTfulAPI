package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.PlaylistContents;
import com.example.demo.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
	
	@Autowired
	private PlaylistService service;
	
	@PostMapping("")
	public ResponseEntity<String> createPlaylist(@RequestBody Playlist playlist) {
		
		Boolean createResult = service.createPlaylist(playlist);
		
		if(createResult) {
			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.toString());
		}
		
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getPlaylistByUserId(@RequestParam("userId") String userId) {
		
		List<Playlist> getResult = service.getPlaylistByUserId(userId);
		
		if(!getResult.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(getResult);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userId+"님의 플레이 리스트가 존재하지 않습니다.");
		}
		
	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<Object> updatePlaylistByUserId(@PathVariable("userId") String userId,
														@RequestParam("playlistId") Integer playlistId,
														@RequestBody Playlist playlist){
		
		Boolean updatePlaylistResult = service.updatePlaylistByUserId(userId, playlistId, playlist);
		
		if(updatePlaylistResult) {
			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 아이디가 다릅니다.");
		}
		
	}
	
	@PostMapping("/{playlistId}")
	public ResponseEntity<String> insertContents(@PathVariable("playlistId") Integer playlistId,
												@RequestBody PlaylistContents playlistContents) {
		
		Integer insertResult = service.insertContents(playlistId, playlistContents);
		
		if(insertResult > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.toString());
		}
	}
	
	@DeleteMapping("/{playlistId}")
	public ResponseEntity<String> deletePlaylist(@PathVariable("playlistId") Integer playlistId) {
		
		Boolean deleteResult = service.deletePlaylist(playlistId);
		
		if(deleteResult) {
			return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.toString());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST.toString());
		}
	}
	
	

}
