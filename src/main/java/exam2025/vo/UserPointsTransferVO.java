package exam2025.vo;

import lombok.Data;

@Data
public class UserPointsTransferVO {
    /**
     * 转出方用户ID
     */
    private Long fromUserId;
    /**
     * 转出方最新积分
     */
    private Integer fromUserPoints;
    /**
     * 接收方用户ID
     */
    private Long toUserId;
    /**
     * 接收方最新积分
     */
    private Integer toUserPoints;
}
