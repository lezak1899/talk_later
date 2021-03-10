/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.modules.user.domain.mapper;


import edu.lingnan.talklater.modules.user.domain.dto.request.UserXxRequestDTO;

import edu.lingnan.talklater.modules.user.domain.UserXx;

/**
 * UserXx领域模型转换类，负责dto跟实体互相转换
 * @author likunzhu
 */
public final class UserXxMapper {

    /**
     * 静态类方法,不支持new对象
     */
    private UserXxMapper() {
    }


    /**
     * QueryUserXxRequestDTO 转化为 UserXx
     *
     * @param queryUserXxRequestDTO 转换前对象
     * @return 转换后对象
     */
    public static UserXx userXxRequestDTOToUserXx(UserXxRequestDTO queryUserXxRequestDTO) {
        if (queryUserXxRequestDTO == null) {
            return null;
        }

        UserXx userXx = new UserXx();
        userXx.setId(queryUserXxRequestDTO.getId());
        userXx.setUsertype(queryUserXxRequestDTO.getUsertype());
        userXx.setUsername(queryUserXxRequestDTO.getUsername());
        userXx.setSex(queryUserXxRequestDTO.getSex());
        userXx.setPassword(queryUserXxRequestDTO.getPassword());
        userXx.setPhone(queryUserXxRequestDTO.getPhone());
        userXx.setFaceImg(queryUserXxRequestDTO.getFaceImg());
        userXx.setFaceImgWhole(queryUserXxRequestDTO.getFaceImgWhole());
        userXx.setNickname(queryUserXxRequestDTO.getNickname());
        userXx.setQrcode(queryUserXxRequestDTO.getQrcode());
        userXx.setPlusId(queryUserXxRequestDTO.getPlusId());
        userXx.setFunSignature(queryUserXxRequestDTO.getFunSignature());
        userXx.setLastLoginDate(queryUserXxRequestDTO.getLastLoginDate());
        userXx.setLastLoginLocation(queryUserXxRequestDTO.getLastLoginLocation());
        userXx.setLastLoginEquipment(queryUserXxRequestDTO.getLastLoginEquipment());
        userXx.setValid(queryUserXxRequestDTO.getIsValid());
        userXx.setDeletedDate(queryUserXxRequestDTO.getDeletedDate());

        return userXx;
    }

}

