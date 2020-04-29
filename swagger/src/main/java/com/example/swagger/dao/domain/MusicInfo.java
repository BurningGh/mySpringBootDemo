package com.example.swagger.dao.domain;


public class MusicInfo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 歌手名
     */
    private String singerName;

    /**
     * 歌曲大小
     */
    private String musicSize;

    /**
     * 歌曲名
     */
    private String musicName;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取歌手名
     *
     * @return singer_name - 歌手名
     */
    public String getSingerName() {
        return singerName;
    }

    /**
     * 设置歌手名
     *
     * @param singerName 歌手名
     */
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    /**
     * 获取歌曲大小
     *
     * @return music_size - 歌曲大小
     */
    public String getMusicSize() {
        return musicSize;
    }

    /**
     * 设置歌曲大小
     *
     * @param musicSize 歌曲大小
     */
    public void setMusicSize(String musicSize) {
        this.musicSize = musicSize;
    }

    /**
     * 获取歌曲名
     *
     * @return music_name - 歌曲名
     */
    public String getMusicName() {
        return musicName;
    }

    /**
     * 设置歌曲名
     *
     * @param musicName 歌曲名
     */
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
}