package com.kjq.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kjq.kjqcommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 86175
* @description 针对表【user_interface_info】的数据库操作Mapper
* @createDate 2024-03-20 16:53:49
* @Entity com.kjq.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(Integer limit);
}




