package com.jimikongjian.service;



import com.jimikongjian.service.message.ResponseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



/**
 * Created by fuxiuyang on 17/3/13.
 */
@Service("albumService")
public class AlbumService{

    @Autowired
    @Qualifier("responseMessageService")
    private ResponseMessageService responseMessageService;



}
