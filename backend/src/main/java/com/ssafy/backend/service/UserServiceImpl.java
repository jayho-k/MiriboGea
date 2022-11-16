package com.ssafy.backend.service;

import com.ssafy.backend.common.util.scheduleJob.BannedJobData;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User registerUser(UserRegisterRequest userRegisterInfo) {
        User user = User.of(userRegisterInfo);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public int missionProgress(Long id,int progress) {
        User user = userRepository.findById(id).orElseGet(() -> new User());

        int userProgress = user.getMissionProgress();

        // 현재 미션이 클리어 됐는지 확인
        boolean missionClear=userProgress/(int)Math.pow(2,progress)%2==1;

        // 미션이 클리어 안됐다면 클리어 시켜줌
        if(!missionClear)
            userProgress+=Math.pow(2,progress);
        
        user.setMissionProgress(userProgress);
        userRepository.save(user);
        return user.getMissionProgress();
    }

    @Override
    public void release(BannedJobData jobData) {
        User user = userRepository.findById(jobData.getUserId()).orElseGet(() -> new User());
        user.setBanned(false);
        userRepository.save(user);
    }

    @Override
    public boolean nicknameValid(String nickname) {
        if(userRepository.countAllByNickname(nickname) != 0){
            return false;
        }
        return true;
    }
}
