package exam2025.dto;

import lombok.Data;

@Data
public class UserPointsTransferDTO {
    /**
     * 转出方用户ID
     */
    private Long fromUserId;
    /**
     * 接收方用户ID
     */
    private Long toUserId;
    /**
     * 转账积分
     */
    private Integer points;
}