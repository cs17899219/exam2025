package exam2025.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPointsEntity {


    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 当前积分
     */
    private Integer points;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
}
