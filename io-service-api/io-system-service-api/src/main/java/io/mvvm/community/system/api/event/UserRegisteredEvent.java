package io.mvvm.community.system.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 用户已注册事件
 * @author: Pan
 **/
@Getter
@AllArgsConstructor
public class UserRegisteredEvent {

    private Long userId;

}
