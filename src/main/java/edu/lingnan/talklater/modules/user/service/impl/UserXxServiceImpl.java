package edu.lingnan.talklater.modules.user.service.impl;

import edu.lingnan.talklater.modules.user.repository.UserXxRepository;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.lingnan.talklater.modules.user.domain.UserXx;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2020/12/27 20:14
 * author likunzhu
 * version
 * since JDK 1.8
 */
@Service
public class UserXxServiceImpl implements UserXxService {

    @Autowired
    private UserXxRepository userXxRepository;


    @Override
    public List<UserXx> findAll() {
        return userXxRepository.findAllUser();

    }
}
