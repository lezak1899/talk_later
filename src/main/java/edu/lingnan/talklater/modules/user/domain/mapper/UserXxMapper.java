/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.modules.user.domain.mapper;


import edu.lingnan.talklater.modules.user.domain.dto.request.CreateUserXxRequestDTO;
import edu.lingnan.talklater.modules.user.domain.dto.request.QueryUserXxRequestDTO;
import edu.lingnan.talklater.modules.user.domain.dto.request.UpdateUserXxRequestDTO;
import edu.lingnan.talklater.modules.user.domain.dto.response.CreateUserXxResponseDTO;
import edu.lingnan.talklater.modules.user.domain.dto.response.QueryUserXxResponseDTO;
import edu.lingnan.talklater.modules.user.domain.dto.response.UpdateUserXxResponseDTO;
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
     * CreateUserXxRequestDTO 转化为 UserXx
     *
     * @param createUserXxRequestDTO 转换前对象
     * @return 转换后对象
     */
    public static UserXx createUserXxRequestDTOToUserXx(CreateUserXxRequestDTO createUserXxRequestDTO) {
        if (createUserXxRequestDTO == null) {
            return null;
        }

        UserXx userXx = new UserXx();
        userXx.setId(createUserXxRequestDTO.getId());
        userXx.setUsertype(createUserXxRequestDTO.getUsertype());
        userXx.setUsername(createUserXxRequestDTO.getUsername());
        userXx.setSex(createUserXxRequestDTO.getSex());
        userXx.setPassword(createUserXxRequestDTO.getPassword());
        userXx.setPhone(createUserXxRequestDTO.getPhone());
        userXx.setFaceImg(createUserXxRequestDTO.getFaceImg());
        userXx.setFaceImgWhole(createUserXxRequestDTO.getFaceImgWhole());
        userXx.setNicename(createUserXxRequestDTO.getNicename());
        userXx.setQrcode(createUserXxRequestDTO.getQrcode());
        userXx.setPlusId(createUserXxRequestDTO.getPlusId());
        userXx.setFunSignature(createUserXxRequestDTO.getFunSignature());
        userXx.setLastLoginDate(createUserXxRequestDTO.getLastLoginDate());
        userXx.setLastLoginLocation(createUserXxRequestDTO.getLastLoginLocation());
        userXx.setLastLoginEquipment(createUserXxRequestDTO.getLastLoginEquipment());
        userXx.setValid(createUserXxRequestDTO.getValid());
        userXx.setDeletedDate(createUserXxRequestDTO.getDeletedDate());

        return userXx;
    }
    /**
     * UpdateUserXxRequestDTO 转化为 UserXx
     *
     * @param updateUserXxRequestDTO 转换前对象
     * @return 转换后对象
     */
    public static UserXx updateUserXxRequestDTOToUserXx(UpdateUserXxRequestDTO updateUserXxRequestDTO) {
        if (updateUserXxRequestDTO == null) {
            return null;
        }

        UserXx userXx = new UserXx();
        userXx.setId(updateUserXxRequestDTO.getId());
        userXx.setUsertype(updateUserXxRequestDTO.getUsertype());
        userXx.setUsername(updateUserXxRequestDTO.getUsername());
        userXx.setSex(updateUserXxRequestDTO.getSex());
        userXx.setPassword(updateUserXxRequestDTO.getPassword());
        userXx.setPhone(updateUserXxRequestDTO.getPhone());
        userXx.setFaceImg(updateUserXxRequestDTO.getFaceImg());
        userXx.setFaceImgWhole(updateUserXxRequestDTO.getFaceImgWhole());
        userXx.setNicename(updateUserXxRequestDTO.getNicename());
        userXx.setQrcode(updateUserXxRequestDTO.getQrcode());
        userXx.setPlusId(updateUserXxRequestDTO.getPlusId());
        userXx.setFunSignature(updateUserXxRequestDTO.getFunSignature());
        userXx.setLastLoginDate(updateUserXxRequestDTO.getLastLoginDate());
        userXx.setLastLoginLocation(updateUserXxRequestDTO.getLastLoginLocation());
        userXx.setLastLoginEquipment(updateUserXxRequestDTO.getLastLoginEquipment());
        userXx.setValid(updateUserXxRequestDTO.getValid());
        userXx.setDeletedDate(updateUserXxRequestDTO.getDeletedDate());

        return userXx;
    }
    /**
     * QueryUserXxRequestDTO 转化为 UserXx
     *
     * @param queryUserXxRequestDTO 转换前对象
     * @return 转换后对象
     */
    public static UserXx queryUserXxRequestDTOToUserXx(QueryUserXxRequestDTO queryUserXxRequestDTO) {
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
        userXx.setNicename(queryUserXxRequestDTO.getNicename());
        userXx.setQrcode(queryUserXxRequestDTO.getQrcode());
        userXx.setPlusId(queryUserXxRequestDTO.getPlusId());
        userXx.setFunSignature(queryUserXxRequestDTO.getFunSignature());
        userXx.setLastLoginDate(queryUserXxRequestDTO.getLastLoginDate());
        userXx.setLastLoginLocation(queryUserXxRequestDTO.getLastLoginLocation());
        userXx.setLastLoginEquipment(queryUserXxRequestDTO.getLastLoginEquipment());
        userXx.setValid(queryUserXxRequestDTO.getValid());
        userXx.setDeletedDate(queryUserXxRequestDTO.getDeletedDate());

        return userXx;
    }
    /**
     * UserXx 转化为 CreateUserXxResponseDTO
     *
     * @param userXx 转换前对象
     * @return 转换后对象
     */
    public static CreateUserXxResponseDTO userXxToCreateUserXxResponseDTO(UserXx userXx) {
        if (userXx == null) {
            return null;
        }

        CreateUserXxResponseDTO createUserXxResponseDTO = new CreateUserXxResponseDTO();
        createUserXxResponseDTO.setId(userXx.getId());
        createUserXxResponseDTO.setUsertype(userXx.getUsertype());
        createUserXxResponseDTO.setUsername(userXx.getUsername());
        createUserXxResponseDTO.setSex(userXx.getSex());
        createUserXxResponseDTO.setPassword(userXx.getPassword());
        createUserXxResponseDTO.setPhone(userXx.getPhone());
        createUserXxResponseDTO.setFaceImg(userXx.getFaceImg());
        createUserXxResponseDTO.setFaceImgWhole(userXx.getFaceImgWhole());
        createUserXxResponseDTO.setNicename(userXx.getNicename());
        createUserXxResponseDTO.setQrcode(userXx.getQrcode());
        createUserXxResponseDTO.setPlusId(userXx.getPlusId());
        createUserXxResponseDTO.setFunSignature(userXx.getFunSignature());
        createUserXxResponseDTO.setLastLoginDate(userXx.getLastLoginDate());
        createUserXxResponseDTO.setLastLoginLocation(userXx.getLastLoginLocation());
        createUserXxResponseDTO.setLastLoginEquipment(userXx.getLastLoginEquipment());
        createUserXxResponseDTO.setValid(userXx.getValid());
        createUserXxResponseDTO.setDeletedDate(userXx.getDeletedDate());

        return createUserXxResponseDTO;
    }
    /**
     * UserXx 转化为 UpdateUserXxResponseDTO
     *
     * @param userXx 转换前对象
     * @return 转换后对象
     */
    public static UpdateUserXxResponseDTO userXxToUpdateUserXxResponseDTO(UserXx userXx) {
        if (userXx == null) {
            return null;
        }

        UpdateUserXxResponseDTO updateUserXxResponseDTO = new UpdateUserXxResponseDTO();
        updateUserXxResponseDTO.setId(userXx.getId());
        updateUserXxResponseDTO.setUsertype(userXx.getUsertype());
        updateUserXxResponseDTO.setUsername(userXx.getUsername());
        updateUserXxResponseDTO.setSex(userXx.getSex());
        updateUserXxResponseDTO.setPassword(userXx.getPassword());
        updateUserXxResponseDTO.setPhone(userXx.getPhone());
        updateUserXxResponseDTO.setFaceImg(userXx.getFaceImg());
        updateUserXxResponseDTO.setFaceImgWhole(userXx.getFaceImgWhole());
        updateUserXxResponseDTO.setNicename(userXx.getNicename());
        updateUserXxResponseDTO.setQrcode(userXx.getQrcode());
        updateUserXxResponseDTO.setPlusId(userXx.getPlusId());
        updateUserXxResponseDTO.setFunSignature(userXx.getFunSignature());
        updateUserXxResponseDTO.setLastLoginDate(userXx.getLastLoginDate());
        updateUserXxResponseDTO.setLastLoginLocation(userXx.getLastLoginLocation());
        updateUserXxResponseDTO.setLastLoginEquipment(userXx.getLastLoginEquipment());
        updateUserXxResponseDTO.setValid(userXx.getValid());
        updateUserXxResponseDTO.setDeletedDate(userXx.getDeletedDate());

        return updateUserXxResponseDTO;
    }
    /**
     * UserXx 转化为 QueryUserXxResponseDTO
     *
     * @param userXx 转换前对象
     * @return 转换后对象
     */
    public static QueryUserXxResponseDTO userXxToQueryUserXxResponseDTO(UserXx userXx) {
        if (userXx == null) {
            return null;
        }

        QueryUserXxResponseDTO queryUserXxResponseDTO = new QueryUserXxResponseDTO();
        queryUserXxResponseDTO.setId(userXx.getId());
        queryUserXxResponseDTO.setUsertype(userXx.getUsertype());
        queryUserXxResponseDTO.setUsername(userXx.getUsername());
        queryUserXxResponseDTO.setSex(userXx.getSex());
        queryUserXxResponseDTO.setPassword(userXx.getPassword());
        queryUserXxResponseDTO.setPhone(userXx.getPhone());
        queryUserXxResponseDTO.setFaceImg(userXx.getFaceImg());
        queryUserXxResponseDTO.setFaceImgWhole(userXx.getFaceImgWhole());
        queryUserXxResponseDTO.setNicename(userXx.getNicename());
        queryUserXxResponseDTO.setQrcode(userXx.getQrcode());
        queryUserXxResponseDTO.setPlusId(userXx.getPlusId());
        queryUserXxResponseDTO.setFunSignature(userXx.getFunSignature());
        queryUserXxResponseDTO.setLastLoginDate(userXx.getLastLoginDate());
        queryUserXxResponseDTO.setLastLoginLocation(userXx.getLastLoginLocation());
        queryUserXxResponseDTO.setLastLoginEquipment(userXx.getLastLoginEquipment());
        queryUserXxResponseDTO.setValid(userXx.getValid());
        queryUserXxResponseDTO.setDeletedDate(userXx.getDeletedDate());

        return queryUserXxResponseDTO;
    }
}

