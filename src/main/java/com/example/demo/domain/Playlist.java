package com.example.demo.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Playlist {
	
	private Integer playlistId;
	private String userId;
	private String title;
	private LocalDate createDate;

}
