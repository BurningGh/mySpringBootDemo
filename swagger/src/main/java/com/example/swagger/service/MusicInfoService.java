package com.example.swagger.service;


import com.example.swagger.dao.domain.MusicInfo;

import java.util.List;

public interface MusicInfoService {

    List<MusicInfo> getMusicInfo(Long id);

    Long getDeleteId(Long id);
}
