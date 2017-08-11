package com.music.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "song_info_mapping")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SongInfoMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "album_id")
	private AlbumInfo albumInfo;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "artist_id")
	private ArtistInfo artistInfo;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "song_id")
	private SongInfo songInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AlbumInfo getAlbumInfo() {
		return albumInfo;
	}

	public void setAlbumInfo(AlbumInfo albumInfo) {
		this.albumInfo = albumInfo;
	}

	public ArtistInfo getArtistInfo() {
		return artistInfo;
	}

	public void setArtistInfo(ArtistInfo artistInfo) {
		this.artistInfo = artistInfo;
	}

	public SongInfo getSongInfo() {
		return songInfo;
	}

	public void setSongInfo(SongInfo songInfo) {
		this.songInfo = songInfo;
	}
	
	
	

}
