package com.livegoods.commons.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 购买商品的消息类型。
@Data
public class LivegoodsBuyMessage extends LivegoodsMessage {
    // 要购买的商品主键
    private String itemId;
    // 购买商品的用户
    private String username;
}
