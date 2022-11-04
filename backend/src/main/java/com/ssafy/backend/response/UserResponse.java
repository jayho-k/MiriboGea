package com.ssafy.backend.response;
import com.ssafy.backend.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;
    private int missionProgress;
    private String role;
    private boolean isBanned;

    private String profileURL;
    private int warningCount;

    public static UserResponse of(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .missionProgress(user.getMissionProgress())
                .role(user.getRole())
                .isBanned(user.isBanned())
                .profileURL(user.getProfileURL())
                .warningCount(user.getWarningCount())
                .build();
        }
}
