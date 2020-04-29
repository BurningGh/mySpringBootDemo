package com.example.swagger.service.impl;

import com.example.swagger.dao.domain.MusicInfo;
import com.example.swagger.dao.mapper.MusicInfoMapper;
import com.example.swagger.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicInfoServiceImpl  implements MusicInfoService {

    private final MusicInfoMapper musicInfoMapper;

    public MusicInfoServiceImpl(MusicInfoMapper musicInfoMapper) {
        this.musicInfoMapper = musicInfoMapper;
    }

    @Override
    public List<MusicInfo> getMusicInfo(Long id) {
        return musicInfoMapper.selectAll(id);
    }

    @Override
    public Long getDeleteId(Long id) {
        return musicInfoMapper.deleteId(id);
    }
}
