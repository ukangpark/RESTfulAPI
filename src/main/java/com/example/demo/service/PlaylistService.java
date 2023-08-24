package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.PlaylistContent;
import com.example.demo.domain.PlaylistContents;
import com.example.demo.mapper.PlaylistMapper;

@Service
public class PlaylistService {
	
	@Autowired
	private PlaylistMapper mapper;

	public Boolean createPlaylist(Playlist playlist) {
		
		LocalDate date = LocalDate.now();
		
		return mapper.createPlaylist(playlist, date);
	}

	public List<Playlist> getPlaylistByUserId(String userId) {
		return mapper.getPlaylistByUserId(userId);
	}

	public Boolean updatePlaylistByUserId(String userId, Integer playlistId, Playlist playlist) {
		
		if (userId.equals(playlist.getUserId())) {
			return mapper.updatePlaylistByUserId(playlistId, playlist);
		}else {
			return false;
		}
	}

	public Integer insertContents(Integer playlistId, PlaylistContents playlistContents) {
		
		Integer insertResult = 0;
		PlaylistContent content = new PlaylistContent();
		List<Integer> songs = playlistContents.getSongId();
		List<Integer> albums = playlistContents.getAlbumId();
		
		for(Integer song : songs) {
			content.setPlaylistId(playlistId);
			content.setSongId(song);
			insertResult += mapper.insertContents(content, playlistId);
		}
		
		for(Integer album : albums) {
			content.setPlaylistId(playlistId);
			content.setAlbumId(album);
			insertResult += mapper.insertContents(content, playlistId);
		}
		
		return insertResult;
	}

	public Boolean deletePlaylist(Integer playlistId) {
		
		Playlist originPlaylist = mapper.getPlaylistByPlaylistId(playlistId);
		
		if(playlistId.equals(originPlaylist.getPlaylistId())) {
			mapper.deletePlaylistContents(playlistId);
			return mapper.deletePlaylist(playlistId);
		}
		return false;
	}

}
