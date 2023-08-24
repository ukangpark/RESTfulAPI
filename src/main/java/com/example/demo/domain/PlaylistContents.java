package com.example.demo.domain;

import java.util.List;

import lombok.Data;

@Data
public class PlaylistContents {
	
	private Integer playlistId;
	private List<Integer> songId;
	private List<Integer> albumId;

}
