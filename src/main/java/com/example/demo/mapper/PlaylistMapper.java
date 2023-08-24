package com.example.demo.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.PlaylistContent;

@Mapper
public interface PlaylistMapper {

	@Insert("""
			INSERT INTO Playlist (userId, title, createDate)
			VALUES (#{playlist.userId}, #{playlist.title}, #{date})
			""")
	Boolean createPlaylist(Playlist playlist, LocalDate date);

	@Select("""
			SELECT * FROM Playlist
			WHERE userId = #{userId}
			""")
	List<Playlist> getPlaylistByUserId(String userId);

	@Update("""
			UPDATE Playlist SET
				userId = #{playlist.userId},
				title = #{playlist.title}
			WHERE playlistId = #{playlistId}
			""")
	Boolean updatePlaylistByUserId(Integer playlistId, Playlist playlist);

	@Insert("""
			INSERT INTO PlaylistContents (playlistId, songId, albumId)
			VALUES (#{playlistId}, #{content.songId}, #{content.albumId})
			""")
	Integer insertContents(PlaylistContent content, Integer playlistId);

	@Delete("""
			DELETE FROM Playlist
			WHERE playlistId = #{playlistId}
			""")
	Boolean deletePlaylist(Integer playlistId);

	@Select("""
			SELECT * FROM Playlist
			WHERE playlistId = #{playlistId}
			""")
	Playlist getPlaylistByPlaylistId(Integer playlistId);

	@Delete("""
			DELETE FROM PlaylistContents
			WHERE playlistId = #{playlistId}
			""")
	void deletePlaylistContents(Integer playlistId);

}
